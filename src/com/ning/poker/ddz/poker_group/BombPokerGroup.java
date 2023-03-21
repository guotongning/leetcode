package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.BasePokerGroup;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.exception.NoComparability;

public class BombPokerGroup extends BasePokerGroup {

    public BombPokerGroup(Poker[] pokers) {
        super(pokers);
    }

    @Override
    public int compareTo(PokerGroup other) throws NoComparability {
        if (other instanceof BombPokerGroup) {
            return Integer.compare(score(), other.score());
        }
        throw new NoComparability(this, other);
    }
}
