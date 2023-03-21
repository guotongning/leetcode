package com.ning.poker.base.exception;

import com.ning.poker.base.api.PokerGroup;

import java.util.Arrays;

public class NoComparabilityException extends RuntimeException {
    public NoComparabilityException(PokerGroup group1, PokerGroup group2) {
        super(String.format("两组牌无可比性！%s %s", Arrays.toString(group1.show()), Arrays.toString(group2.show())));
    }
}
