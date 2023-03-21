package com.ning.poker.base;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;

import java.util.Arrays;
import java.util.stream.Stream;

public abstract class BasePokerGroup implements PokerGroup {

    private final Poker[] pokers;
    private final int score;

    public BasePokerGroup(Poker[] pokers) {
        this.pokers = pokers;
        score = Stream.of(pokers).map(Poker::score).reduce(Integer::sum).orElse(-1);
    }

    public abstract int compareTo(PokerGroup other);

    @Override
    public int score() {
        return score;
    }

    @Override
    public Poker[] show() {
        return Arrays.copyOf(pokers, pokers.length);
    }
}
