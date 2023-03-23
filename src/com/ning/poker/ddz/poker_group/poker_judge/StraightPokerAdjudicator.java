package com.ning.poker.ddz.poker_group.poker_judge;

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
            if (PokerFace.BJ.getFace().equals(poker.face()) ||
                    PokerFace.SJ.getFace().equals(poker.face()) ||
                    PokerFace.TWO.getFace().equals(poker.face())
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
