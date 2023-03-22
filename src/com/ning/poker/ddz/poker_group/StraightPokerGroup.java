package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.BasePokerGroup;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.exception.NoComparabilityException;

public class StraightPokerGroup extends BasePokerGroup {

    public StraightPokerGroup(Poker[] pokers) {
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
        if (!(other instanceof StraightPokerGroup)) {
            throw new NoComparabilityException(this, other);
        }
        if (this.show().length != other.show().length) {
            throw new NoComparabilityException(this, other);
        }
        return Integer.compare(score(), other.score());
    }

    @Override
    public int score() {
        return show()[0].score();
    }
}
