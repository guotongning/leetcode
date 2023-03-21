package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.api.PokerGroupFactory;
import com.ning.poker.base.enums.PokerFace;
import com.ning.poker.base.exception.NoSuchPokerGroupException;
import com.ning.poker.ddz.DDZPoker;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class DDZPokerGroupFactory implements PokerGroupFactory {

    private static DDZPokerGroupFactory SINGLE_INSTANCE;

    private static final Map<Class<? extends PokerGroup>, Function<Poker[], Boolean>> JUDGES = new LinkedHashMap<>();
    private static final Map<Class<? extends PokerGroup>, Function<Poker[], PokerGroup>> CREATORS = new LinkedHashMap<>();

    private static final Function<Poker[], Boolean> SINGLE_JUDGE = pokers -> pokers != null && pokers.length == 1;

    private static final Function<Poker[], Boolean> PAIRS_JUDGE = pokers -> {
        if (pokers == null || pokers.length != 2) {
            return false;
        }
        if (PokerFace.JOKER.getFace().equals(pokers[0].face()) || PokerFace.JOKER.getFace().equals(pokers[1].face())) {
            return false;
        }
        return pokers[0].face().equals(pokers[1].face());
    };

    private static final Function<Poker[], Boolean> BOMB_JUDGE = pokers -> {
        if (pokers == null || (pokers.length != 2 && pokers.length != 4)) {
            return false;
        }
        String face = pokers[0].face();
        return Stream.of(pokers).map(Poker::face).allMatch(f -> f.equals(face));
    };
    private static final Function<Poker[], PokerGroup> SINGLE_CREATOR = SinglePokerGroup::new;
    private static final Function<Poker[], PokerGroup> PAIRS_CREATOR = PairsPokerGroup::new;
    private static final Function<Poker[], PokerGroup> BOMB_CREATOR = BombPokerGroup::new;

    static {
        JUDGES.put(SinglePokerGroup.class, SINGLE_JUDGE);
        CREATORS.put(SinglePokerGroup.class, SINGLE_CREATOR);

        JUDGES.put(PairsPokerGroup.class, PAIRS_JUDGE);
        CREATORS.put(PairsPokerGroup.class, PAIRS_CREATOR);

        JUDGES.put(BombPokerGroup.class, BOMB_JUDGE);
        CREATORS.put(BombPokerGroup.class, BOMB_CREATOR);
    }

    public static DDZPokerGroupFactory getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new DDZPokerGroupFactory();
        }
        return SINGLE_INSTANCE;
    }

    @Override
    public PokerGroup create(Poker[] pokers) {
        return CREATORS.get(
                JUDGES.entrySet().stream()
                        .filter(entry -> entry.getValue().apply(pokers))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElseThrow(() -> new NoSuchPokerGroupException(pokers))
        ).apply(pokers);
    }

}
