package com.ning.poker.base.exception;

import com.ning.poker.base.api.Poker;

import java.util.Arrays;

public class NoSuchPokerGroupException extends RuntimeException {
    public NoSuchPokerGroupException(Poker[] pokers) {
        super(String.format("不存在这种牌型！%s", Arrays.toString(pokers)));
    }
}
