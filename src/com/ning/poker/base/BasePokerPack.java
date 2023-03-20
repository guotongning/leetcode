package com.ning.poker.base;

import java.util.Arrays;
import java.util.Random;

public class BasePokerPack implements PokerPack {
    private final Poker[] pokers;

    public BasePokerPack(Poker[] pokers) {
        this.pokers = pokers;
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

}
