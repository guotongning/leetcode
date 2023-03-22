package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.BasePokerGroup;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
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
        if (!(other instanceof ThreeKindPokerGroup)) {
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
            exchange(pokers, 0, 3);
        }
        if (pokers.length == 5 && !pokers[0].face().equals(pokers[2].face())) {
            exchange(pokers, 0, 3);
            exchange(pokers, 1, 4);
        }
    }

    private void exchange(Poker[] pokers, int source, int target) {
        Poker temp = pokers[source];
        pokers[source] = pokers[target];
        pokers[target] = temp;
    }
}
