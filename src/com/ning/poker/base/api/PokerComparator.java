package com.ning.poker.base.api;

import com.ning.poker.base.exception.NoComparability;

public interface PokerComparator {
    int compareTo(PokerGroup other) throws NoComparability;
}
