package com.ning.poker.ddz.poker_group.poker_judge;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroupAdjudicator;
import com.ning.poker.base.enums.PokerFace;

import java.util.stream.Stream;

public class BombPokerAdjudicator implements PokerGroupAdjudicator {

    @Override
    public boolean judge(Poker[] pokers) {
        if (pokers == null) {
            return false;
        }
        if (pokers.length == 2) {
            return Stream.of(pokers).allMatch(poker -> PokerFace.BJ.getFace().equals(poker.face()) || PokerFace.SJ.getFace().equals(poker.face()));
        }
        if (pokers.length == 4) {
            String face = pokers[0].face();
            return Stream.of(pokers).map(Poker::face).allMatch(f -> f.equals(face));
        }
        return false;
    }
}
