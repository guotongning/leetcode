package com.ning.poker.ddz.poker_group.poker_judge;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroupAdjudicator;

import java.util.stream.Stream;

public class BombPokerAdjudicator implements PokerGroupAdjudicator {

    @Override
    public boolean judge(Poker[] pokers) {
        if (pokers == null || (pokers.length != 2 && pokers.length != 4)) {
            return false;
        }
        String face = pokers[0].face();
        return Stream.of(pokers).map(Poker::face).allMatch(f -> f.equals(face));
    }
}
