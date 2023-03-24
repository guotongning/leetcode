package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.api.PokerGroupAdjudicator;
import com.ning.poker.base.api.PokerGroupFactory;
import com.ning.poker.base.enums.PokerDecor;
import com.ning.poker.base.enums.PokerFace;
import com.ning.poker.base.exception.NoSuchPokerGroupException;
import com.ning.poker.ddz.DDZPoker;
import com.ning.poker.ddz.enums.DDZPokerScore;
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
        JUDGES.add(new Pair<>(new BombPokerAdjudicator(), BombPokerGroup::new));
        JUDGES.add(new Pair<>(new StraightPokerAdjudicator(), StraightPokerGroup::new));
        JUDGES.add(new Pair<>(new ThreeKindAdjudicator(), ThreeKindPokerGroup::new));
        JUDGES.add(new Pair<>(new FourKindPokerAdjudicator(), FourKindPokerGroup::new));
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

    public static void main(String[] args) {
        PokerGroup group1 = DDZPokerGroupFactory.getInstance().create("BJ", "SJ");
        PokerGroup group2 = DDZPokerGroupFactory.getInstance().create("2", "2", "2", "2");
        System.out.println(group1.compareTo(group2));
        System.out.println(group2.compareTo(group1));

        PokerGroup group3 = DDZPokerGroupFactory.getInstance().create("3", "4", "5", "6", "7");
        PokerGroup group4 = DDZPokerGroupFactory.getInstance().create("4", "5", "6", "7", "8");
        System.out.println(group3.compareTo(group4));
        System.out.println(group4.compareTo(group2));
        System.out.println(group1.compareTo(group4));

        PokerGroup group5 = DDZPokerGroupFactory.getInstance().create("A", "K", "Q", "J", "10");
        System.out.println(group1.compareTo(group5));
        System.out.println(group5.compareTo(group4));

        PokerGroup group6 = DDZPokerGroupFactory.getInstance().create("A", "A", "A", "10", "10");
        PokerGroup group7 = DDZPokerGroupFactory.getInstance().create("K", "K", "K", "5", "5");
        System.out.println(group7.compareTo(group6));

        PokerGroup group8 = DDZPokerGroupFactory.getInstance().create("A", "A", "A", "10");
        PokerGroup group9 = DDZPokerGroupFactory.getInstance().create("K", "K", "K", "5");
        System.out.println(group8.compareTo(group9));

        PokerGroup group10 = DDZPokerGroupFactory.getInstance().create("K", "K", "K", "K", "5", "5");
        PokerGroup group11 = DDZPokerGroupFactory.getInstance().create("A", "A", "A", "A", "6", "8");
        System.out.println(group10.compareTo(group11));
    }

}
