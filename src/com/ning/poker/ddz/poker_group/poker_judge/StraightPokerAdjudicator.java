package com.ning.poker.ddz.poker_group.poker_judge;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroupAdjudicator;
import com.ning.poker.base.enums.PokerFace;
import com.ning.poker.base.utils.PokerUtils;

public class StraightPokerAdjudicator implements PokerGroupAdjudicator {
    @Override
    public boolean judge(Poker[] pokers) {
        if (pokers == null || pokers.length < 5) {
            return false;
        }
        PokerUtils.sort(pokers,true);
        int score = pokers[0].score();
        for (Poker poker : pokers) {
            if (isIllegalPoker(poker)) {
                return false;
            }
            if (poker.score() != score--) {
                return false;
            }
        }
        return true;
    }

    protected boolean isIllegalPoker(Poker poker) {
        return PokerFace.BJ.getFace().equals(poker.face()) ||
                PokerFace.SJ.getFace().equals(poker.face()) ||
                PokerFace.TWO.getFace().equals(poker.face());
    }
}
