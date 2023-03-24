package com.ning.poker.ddz.util;

import com.ning.poker.base.api.Poker;

public class PokerUtils {

    public static void exchange(Poker[] pokers, int source, int target) {
        Poker temp = pokers[source];
        pokers[source] = pokers[target];
        pokers[target] = temp;
    }
}
