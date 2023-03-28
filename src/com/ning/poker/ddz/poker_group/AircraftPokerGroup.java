package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.BasePokerGroup;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.utils.PokerUtils;
import com.ning.poker.base.exception.NoComparabilityException;

public class AircraftPokerGroup extends BasePokerGroup{

    public AircraftPokerGroup(Poker[] pokers) {
        super(pokers);
    }

    @Override
    public int compareTo(PokerGroup other) {
        if (other == null) {
            return 1;
        }
        if (other instanceof BombPokerGroup) {
            return -1;
        }
        if (!(other instanceof AircraftPokerGroup) || show().length != other.show().length) {
            throw new NoComparabilityException(this, other);
        }
        return Integer.compare(this.score(), other.score());
    }

    @Override
    public int score() {
        return show()[0].score();
    }

    @Override
    public void sort(Poker[] pokers) {
        super.sort(pokers);
        int x = 0, y = 1, z = 2, preDiffLength = 0, threeKindLength;
        if (pokers[x].face().equals(pokers[y].face()) && !pokers[x].face().equals(pokers[z].face())) {
            threeKindLength = pokers.length / 5 * 3;
        } else if (!pokers[x].face().equals(pokers[y].face())) {
            threeKindLength = pokers.length / 4 * 3;
        } else {
            return;
        }
        while (z < pokers.length) {
            Poker pokerI = pokers[x++];
            Poker pokerJ = pokers[y++];
            Poker pokerK = pokers[z++];
            if (pokerI.face().equals(pokerJ.face()) && pokerI.face().equals(pokerK.face())) {
                preDiffLength = x - 1;
                break;
            }
        }
        Poker[] copy = PokerUtils.copy(pokers);
        System.arraycopy(copy, preDiffLength, pokers, 0, threeKindLength);
        System.arraycopy(copy, 0, pokers, threeKindLength, preDiffLength);
        for (int i = 0; i < copy.length - preDiffLength - threeKindLength; i++) {
            pokers[threeKindLength + preDiffLength + i] = copy[threeKindLength + preDiffLength + i];
        }
        System.out.println();
    }
}
