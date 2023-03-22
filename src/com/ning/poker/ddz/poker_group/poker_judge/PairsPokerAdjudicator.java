package com.ning.poker.ddz.poker_group.poker_judge;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroupAdjudicator;
import com.ning.poker.base.enums.PokerFace;

public class PairsPokerAdjudicator implements PokerGroupAdjudicator {

    @Override
    public boolean judge(Poker[] pokers) {
        if (pokers == null || pokers.length != 2) {
            return false;
        }
        if (PokerFace.BJ.getFace().equals(pokers[0].face()) || PokerFace.SJ.getFace().equals(pokers[1].face())) {
            return false;
        }
        return pokers[0].face().equals(pokers[1].face());
    }
}
