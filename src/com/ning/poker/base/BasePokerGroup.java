package com.ning.poker.base;

import com.ning.poker.base.api.Poker;
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
        desc(pokers);
        this.pokers = pokers;
        sort(this.pokers);
        score = initScore();
    }

    public abstract int compareTo(PokerGroup other);

    public void sort(Poker[] pokers) {

    }

    public void asc(Poker[] pokers) {
        Arrays.sort(pokers, Poker::compareTo);
    }

    public void desc(Poker[] pokers) {
        Arrays.sort(pokers, Comparator.comparing(Poker::score).reversed());
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
