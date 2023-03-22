package com.ning.poker.ddz.poker_judge;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroupAdjudicator;

public class SinglePokerAdjudicator implements PokerGroupAdjudicator {

    @Override
    public boolean judge(Poker[] pokers) {
        return pokers != null && pokers.length == 1;
    }
}
