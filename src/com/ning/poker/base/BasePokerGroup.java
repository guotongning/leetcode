package com.ning.poker.base;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.utils.*;
import com.ning.poker.base.api.PokerGroup;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public abstract class BasePokerGroup implements PokerGroup {

    private final Poker[] pokers;
    private final int score;

    public BasePokerGroup(Poker[] pokers) {
        if (pokers == null) {
            throw new IllegalArgumentException("非法的牌");
        }
        sort(pokers);
        this.pokers = pokers;
        score = initScore();
    }

    public abstract int compareTo(PokerGroup other);

    public void sort(Poker[] pokers) {
        PokerUtils.sort(pokers, true);
    }

    @Override
    public int score() {
        return score;
    }

    @Override
    public int initScore() {
        return Stream.of(pokers).map(Poker::score).reduce(Integer::sum).orElse(-1);
    }

    @Override
    public Poker[] show() {
        return Arrays.copyOf(pokers, pokers.length);
    }
}
