package com.ning.poker.base.api;

import com.ning.poker.base.exception.NoComparabilityException;

public interface PokerComparator {
    int compareTo(PokerGroup other) throws NoComparabilityException;
}
