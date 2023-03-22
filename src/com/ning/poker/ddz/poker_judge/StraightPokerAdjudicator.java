package com.ning.poker.ddz.poker_judge;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroupAdjudicator;
import com.ning.poker.base.enums.PokerFace;

import java.util.Arrays;
import java.util.Comparator;

public class StraightPokerAdjudicator implements PokerGroupAdjudicator {
    @Override
    public boolean judge(Poker[] pokers) {
        if (pokers == null || pokers.length < 5) {
            return false;
        }
        Arrays.sort(pokers, Comparator.comparing(Poker::score).reversed());
        int score = pokers[0].score();
        for (Poker poker : pokers) {
            if (PokerFace.BIG_JOKER.getFace().equals(poker.face()) ||
                    PokerFace.SMALL_JOKER.getFace().equals(poker.face()) ||
                    PokerFace.A.getFace().equals(poker.face()) ||
                    PokerFace.TEN.getFace().equals(poker.face())
            ) {
                return false;
            }
            if (poker.score() != score--) {
                return false;
            }
        }
        return true;
    }
}
