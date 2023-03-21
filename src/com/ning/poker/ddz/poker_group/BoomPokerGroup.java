package com.ning.poker.ddz.poker_group;

import com.ning.poker.base.BasePokerGroup;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;

import java.util.Arrays;

public class BoomPokerGroup extends BasePokerGroup {

    public BoomPokerGroup(Poker[] pokers) {
        super(pokers);
    }

    @Override
    public int compareTo(PokerGroup other) {
        if (other instanceof BoomPokerGroup) {
            return Integer.compare(score(), other.score());
        }
        throw new RuntimeException(String.format("两组牌无可比性！%s %s", Arrays.toString(this.show()), Arrays.toString(other.show())));
    }
}
