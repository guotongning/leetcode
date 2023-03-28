package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.BasePokerGroup;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.utils.PokerUtils;
import com.ning.poker.base.exception.NoComparabilityException;

public class FourKindPokerGroup extends BasePokerGroup {

    public FourKindPokerGroup(Poker[] pokers) {
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
        if (!(other instanceof FourKindPokerGroup)) {
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
        if (!pokers[0].face().equals(pokers[2].face())) {
            PokerUtils.exchange(pokers, 0, 5);
            PokerUtils.exchange(pokers, 1, 6);
        }
    }
}
