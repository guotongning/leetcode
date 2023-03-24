package com.ning.poker.ddz.poker_group.poker_judge;

import com.ning.poker.base.api.Poker;

import java.util.Arrays;
import java.util.Comparator;

public class DoubleStraightPokerAdjudicator extends StraightPokerAdjudicator {

    @Override
    public boolean judge(Poker[] pokers) {
        if (pokers == null || pokers.length < 6) {
            return false;
        }
        Arrays.sort(pokers, Comparator.comparing(Poker::score).reversed());
        int score1 = pokers[0].score();
        int score2 = pokers[1].score();
        for (int i = 0, j = 1; i < pokers.length - 1 && j < pokers.length; i += 2, j += 2) {
            Poker poker1 = pokers[i];
            Poker poker2 = pokers[j];
            if (isIllegalPoker(poker1) || isIllegalPoker(poker2)) {
                return false;
            }
            if (poker1.score() != score1-- || poker2.score() != score2--) {
                return false;
            }
        }
        return true;
    }


}
