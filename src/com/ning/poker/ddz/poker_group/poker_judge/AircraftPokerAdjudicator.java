package com.ning.poker.ddz.poker_group.poker_judge;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroupAdjudicator;
import com.ning.poker.base.utils.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AircraftPokerAdjudicator implements PokerGroupAdjudicator {

    @Override
    public boolean judge(Poker[] pokers) {
        if (pokers == null || pokers.length < 6 || (pokers.length % 5 != 0 && pokers.length % 4 != 0)) {
            return false;
        }
        PokerUtils.sort(pokers, true);
        int threeKindCount = 0;
        int lastThreeKindScore = 0;
        Poker[] copy = Arrays.copyOf(pokers, pokers.length);
        int i = 0, j = 1, k = 2;
        while (k < copy.length) {
            Poker pokerI = copy[i];
            Poker pokerJ = copy[j];
            Poker pokerK = copy[k];
            if (!(pokerI.face().equals(pokerJ.face()) && pokerI.face().equals(pokerK.face()))) {
                i++;
                j++;
                k++;
            } else {
                threeKindCount++;
                i += 3;
                j += 3;
                k += 3;
                if (lastThreeKindScore != 0 && lastThreeKindScore != pokerI.score() + 1) {
                    return false;
                }
                lastThreeKindScore = pokerI.score();
            }
        }
        return (pokers.length - threeKindCount * 3) % threeKindCount == 0;
    }
}
