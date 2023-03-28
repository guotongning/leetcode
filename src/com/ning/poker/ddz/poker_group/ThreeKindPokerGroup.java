package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.BasePokerGroup;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.utils.PokerUtils;
import com.ning.poker.base.exception.NoComparabilityException;

public class ThreeKindPokerGroup extends BasePokerGroup {

    public ThreeKindPokerGroup(Poker[] pokers) {
        super(pokers);
    }

    @Override
    public int compareTo(PokerGroup other) throws NoComparabilityException {
        if (other == null) {
            return 1;
        }
        if (other instanceof BombPokerGroup) {
            return -1;
        }
        if (!(other instanceof ThreeKindPokerGroup) || show().length != other.show().length) {
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
        if (pokers.length == 4 && !pokers[0].face().equals(pokers[1].face())) {
            PokerUtils.exchange(pokers, 0, 3);
        }
        if (pokers.length == 5 && !pokers[0].face().equals(pokers[2].face())) {
            PokerUtils.exchange(pokers, 0, 3);
            PokerUtils.exchange(pokers, 1, 4);
        }
    }
}
