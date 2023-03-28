package com.ning.poker.base.utils;

import com.ning.poker.base.api.Poker;

import java.util.Arrays;
import java.util.Comparator;

public class PokerUtils {

    public static void exchange(Poker[] pokers, int source, int target) {
        Poker temp = pokers[source];
        pokers[source] = pokers[target];
        pokers[target] = temp;
    }

    public static Poker[] copy(Poker[] pokers) {
        return Arrays.copyOf(pokers, pokers.length);
    }

    public static void sort(Poker[] pokers, boolean reversed) {
        Comparator<Poker> comparator = Comparator.comparing(Poker::score);
        Arrays.sort(pokers, reversed ? comparator.reversed() : comparator);
    }
}
