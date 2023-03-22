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
import java.util.Arrays;
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
        return JUDGES.stream().filter(adjudicator -> adjudicator.getKey().judge(pokers)).findFirst().orElseThrow(() -> new NoSuchPokerGroupException(pokers)).getValue().apply(pokers);
    }

    public static void main(String[] args) {
        DDZPoker bj = new DDZPoker(PokerFace.BJ.getFace(), null, DDZPokerScore.DZZ_BIG_JOKER.getScore());
        DDZPoker sj = new DDZPoker(PokerFace.SJ.getFace(), null, DDZPokerScore.DZZ_SMALL_JOKER.getScore());

        DDZPoker two1 = new DDZPoker(PokerFace.TWO.getFace(), PokerDecor.values()[0].getDecor(), DDZPokerScore.DDZ_TWO.getScore());
        DDZPoker two2 = new DDZPoker(PokerFace.TWO.getFace(), PokerDecor.values()[1].getDecor(), DDZPokerScore.DDZ_TWO.getScore());
        DDZPoker two3 = new DDZPoker(PokerFace.TWO.getFace(), PokerDecor.values()[2].getDecor(), DDZPokerScore.DDZ_TWO.getScore());
        DDZPoker two4 = new DDZPoker(PokerFace.TWO.getFace(), PokerDecor.values()[3].getDecor(), DDZPokerScore.DDZ_TWO.getScore());

        Poker[] pokers1 = {bj, sj};
        Poker[] pokers2 = {two1, two2, two3, two4};
        PokerGroup group1 = DDZPokerGroupFactory.getInstance().create(pokers1);
        PokerGroup group2 = DDZPokerGroupFactory.getInstance().create(pokers2);
        System.out.println(group1.compareTo(group2));

        DDZPoker straight1 = new DDZPoker(PokerFace.THREE.getFace(), PokerDecor.values()[3].getDecor(), DDZPokerScore.DDZ_THREE.getScore());
        DDZPoker straight2 = new DDZPoker(PokerFace.FOUR.getFace(), PokerDecor.values()[3].getDecor(), DDZPokerScore.DDZ_FOUR.getScore());
        DDZPoker straight3 = new DDZPoker(PokerFace.FIVE.getFace(), PokerDecor.values()[3].getDecor(), DDZPokerScore.DDZ_FIVE.getScore());
        DDZPoker straight4 = new DDZPoker(PokerFace.SIX.getFace(), PokerDecor.values()[3].getDecor(), DDZPokerScore.DDZ_SIX.getScore());
        DDZPoker straight5 = new DDZPoker(PokerFace.SEVEN.getFace(), PokerDecor.values()[3].getDecor(), DDZPokerScore.DDZ_SEVEN.getScore());

        DDZPoker straight7 = new DDZPoker(PokerFace.FOUR.getFace(), PokerDecor.values()[3].getDecor(), DDZPokerScore.DDZ_FOUR.getScore());
        DDZPoker straight8 = new DDZPoker(PokerFace.FIVE.getFace(), PokerDecor.values()[3].getDecor(), DDZPokerScore.DDZ_FIVE.getScore());
        DDZPoker straight9 = new DDZPoker(PokerFace.SIX.getFace(), PokerDecor.values()[3].getDecor(), DDZPokerScore.DDZ_SIX.getScore());
        DDZPoker straight10 = new DDZPoker(PokerFace.SEVEN.getFace(), PokerDecor.values()[3].getDecor(), DDZPokerScore.DDZ_SEVEN.getScore());
        DDZPoker straight11 = new DDZPoker(PokerFace.EIGHT.getFace(), PokerDecor.values()[3].getDecor(), DDZPokerScore.DDZ_EIGHT.getScore());

        Poker[] pokers3 = {straight1, straight2, straight3, straight4, straight5};
        Poker[] pokers4 = {straight7, straight8, straight9, straight10, straight11};
        PokerGroup group3 = DDZPokerGroupFactory.getInstance().create(pokers3);
        PokerGroup group4 = DDZPokerGroupFactory.getInstance().create(pokers4);
        System.out.println(group3.compareTo(group4));
        System.out.println(group4.compareTo(group2));
        System.out.println(group1.compareTo(group4));
    }

}
