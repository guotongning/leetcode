package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.BasePokerGroup;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.exception.NoComparability;

public class SinglePokerGroup extends BasePokerGroup {

    public SinglePokerGroup(Poker poker) {
        super(new Poker[]{poker});
    }

    @Override
    public int compareTo(PokerGroup other) throws NoComparability {
        if (other == null) {
            return 1;
        }
        if (other instanceof BombPokerGroup) {
            return -1;
        }
        if (other instanceof SinglePokerGroup) {
            return Integer.compare(this.score(), other.score());
        }
        throw new NoComparability(this, other);
    }
}
