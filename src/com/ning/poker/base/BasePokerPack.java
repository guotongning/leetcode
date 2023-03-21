package com.ning.poker.base;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerPack;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BasePokerPack implements PokerPack {
    private final Poker[] pokers;
    private final AtomicInteger surplus;

    public BasePokerPack(Poker[] pokers) {
        this.pokers = pokers;
        surplus = new AtomicInteger(pokers.length - 1);
    }

    @Override
    public Poker[] showAll() {
        return Arrays.copyOf(pokers, pokers.length);
    }

    @Override
    public void sort() {
        Arrays.sort(pokers, Poker::compareTo);
    }

    @Override
    public void disorganize() {
        Random random = new Random();
        for (int i = pokers.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Poker temp = pokers[i];
            pokers[i] = pokers[j];
            pokers[j] = temp;
        }
    }

    @Override
    public Poker deal() {
        if (surplus.get() < 0) {
            return null;
        }
        return pokers[surplus.getAndDecrement()];
    }

}
