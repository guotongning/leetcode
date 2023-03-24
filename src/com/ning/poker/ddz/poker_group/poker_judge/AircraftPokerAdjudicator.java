package com.ning.poker.ddz.poker_group.poker_judge;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroupAdjudicator;
import com.ning.poker.ddz.DDZPokerGroupFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class AircraftPokerAdjudicator implements PokerGroupAdjudicator {

    @Override
    public boolean judge(Poker[] pokers) {
        if (pokers == null || pokers.length < 6 || pokers.length % 2 != 0) {
            return false;
        }
        Arrays.sort(pokers, Comparator.comparing(Poker::score).reversed());
        int threeKinkCount = 0;
        Poker[] copy = Arrays.copyOf(pokers, pokers.length);
        for (int i = 0, j = 1, k = 2; k < copy.length; i++, j++, k++) {
            Poker pokerI = copy[i];
            Poker pokerJ = copy[j];
            Poker pokerK = copy[k];
            if (pokerI.face().equals(pokerJ.face()) && pokerI.face().equals(pokerK.face())) {
                threeKinkCount++;
                copy[i] = null;
                copy[j] = null;
                copy[k] = null;
            }
        }
        Set<Poker> pokerSet = new HashSet<>();
        for (Poker poker : copy) {
            if (poker != null) {
                pokerSet.add(poker);
            }
        }
        return pokerSet.size() % (pokers.length - threeKinkCount * 3) == 0;
    }
}
