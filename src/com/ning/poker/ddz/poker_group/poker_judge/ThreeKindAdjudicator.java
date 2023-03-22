package com.ning.poker.ddz.poker_group.poker_judge;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroupAdjudicator;

public class ThreeKindAdjudicator implements PokerGroupAdjudicator {

    @Override
    public boolean judge(Poker[] pokers) {
        if (pokers == null || pokers.length < 3) {
            return false;
        }
        int sameCount = 0;
        Poker start = pokers[0];
        for (Poker poker : pokers) {
            if (poker.face().equals(start.face())) {
                sameCount++;
            }
        }
        return sameCount == 3;
    }
}
