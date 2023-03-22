package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.api.PokerGroupAdjudicator;
import com.ning.poker.base.api.PokerGroupFactory;
import com.ning.poker.base.exception.NoSuchPokerGroupException;
import com.ning.poker.ddz.poker_group.poker_judge.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DDZPokerGroupFactory implements PokerGroupFactory {

    private static DDZPokerGroupFactory SINGLE_INSTANCE;

    private static final List<Pair<PokerGroupAdjudicator, Function<Poker[], PokerGroup>>> JUDGES = new ArrayList<>();

    static {
        JUDGES.add(new Pair<>(new SinglePokerAdjudicator(), SinglePokerGroup::new));
        JUDGES.add(new Pair<>(new PairsPokerAdjudicator(), PairsPokerGroup::new));
        JUDGES.add(new Pair<>(new BombPokerAdjudicator(), BombPokerGroup::new));
        JUDGES.add(new Pair<>(new StraightPokerAdjudicator(), StraightPokerGroup::new));
        JUDGES.add(new Pair<>(new ThreeKindAdjudicator(), ThreeKindPokerGroup::new));
    }

    public static DDZPokerGroupFactory getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new DDZPokerGroupFactory();
        }
        return SINGLE_INSTANCE;
    }

    @Override
    public PokerGroup create(Poker[] pokers) {
        return JUDGES.stream()
                .filter(adjudicator -> adjudicator.getKey().judge(pokers))
                .findFirst().orElseThrow(() -> new NoSuchPokerGroupException(pokers))
                .getValue().apply(pokers);
    }

}
