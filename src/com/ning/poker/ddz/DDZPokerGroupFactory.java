package com.ning.poker.ddz;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.api.PokerGroupAdjudicator;
import com.ning.poker.base.api.PokerGroupFactory;
import com.ning.poker.base.enums.PokerDecor;
import com.ning.poker.base.enums.PokerFace;
import com.ning.poker.base.exception.NoSuchPokerGroupException;
import com.ning.poker.ddz.enums.DDZPokerScore;
import com.ning.poker.ddz.poker_group.*;
import com.ning.poker.ddz.poker_group.poker_judge.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class DDZPokerGroupFactory implements PokerGroupFactory {

    private static DDZPokerGroupFactory SINGLE_INSTANCE;

    private static final List<Pair<PokerGroupAdjudicator, Function<Poker[], PokerGroup>>> JUDGES = new ArrayList<>();

    static {
        JUDGES.add(new Pair<>(new SinglePokerAdjudicator(), SinglePokerGroup::new));
        JUDGES.add(new Pair<>(new PairsPokerAdjudicator(), PairsPokerGroup::new));
        JUDGES.add(new Pair<>(new ThreeKindAdjudicator(), ThreeKindPokerGroup::new));
        JUDGES.add(new Pair<>(new BombPokerAdjudicator(), BombPokerGroup::new));
        JUDGES.add(new Pair<>(new FourKindPokerAdjudicator(), FourKindPokerGroup::new));
        JUDGES.add(new Pair<>(new StraightPokerAdjudicator(), StraightPokerGroup::new));
        JUDGES.add(new Pair<>(new DoubleStraightPokerAdjudicator(), DoubleStraightPokerGroup::new));
        JUDGES.add(new Pair<>(new AircraftPokerAdjudicator(), AircraftPokerGroup::new));
    }

    public static DDZPokerGroupFactory getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new DDZPokerGroupFactory();
        }
        return SINGLE_INSTANCE;
    }

    @Override
    public PokerGroup create(Poker[] pokers) {
        return JUDGES.stream().filter(adjudicator -> adjudicator.getKey().judge(pokers)).findFirst().orElseThrow(() -> new NoSuchPokerGroupException(pokers)).getValue().apply(pokers);
    }

    public PokerGroup create(String... faces) {
        if (faces == null || faces.length == 0) {
            return null;
        }
        DDZPoker[] pokers = new DDZPoker[faces.length];
        for (int i = 0; i < faces.length; i++) {
            String face = faces[i];
            PokerFace pokerFace = PokerFace.fromFace(face);
            int rand = new Random().nextInt(4);
            DDZPoker ddzPoker = new DDZPoker(pokerFace.getFace(), PokerDecor.values()[rand].getDecor(), DDZPokerScore.fromPokerFace(pokerFace).getScore());
            pokers[i] = ddzPoker;
        }
        return create(pokers);
    }

}
