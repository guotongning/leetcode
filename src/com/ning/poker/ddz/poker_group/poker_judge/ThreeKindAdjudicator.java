package com.ning.poker.ddz.poker_group.poker_judge;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroupAdjudicator;

public class ThreeKindAdjudicator implements PokerGroupAdjudicator {

    @Override
    public boolean judge(Poker[] pokers) {
        if (pokers == null || pokers.length < 3 || pokers.length > 5) {
            return false;
        }
        int sameCount = 0;
        Poker start = pokers[0].face().equals(pokers[1].face()) ? pokers[0] : pokers[pokers.length - 1];
        for (Poker poker : pokers) {
            if (poker.face().equals(start.face())) {
                sameCount++;
            }
        }
        return sameCount == 3;
    }
}
