package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.BasePokerGroup;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.exception.NoComparabilityException;

public class AircraftPokerGroup extends BasePokerGroup {

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

    }
}
