package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.BasePokerGroup;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.exception.NoComparabilityException;

public class SinglePokerGroup extends BasePokerGroup {

    public SinglePokerGroup(Poker[] pokers) {
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
        if (other instanceof SinglePokerGroup) {
            return Integer.compare(this.score(), other.score());
        }
        throw new NoComparabilityException(this, other);
    }
}
