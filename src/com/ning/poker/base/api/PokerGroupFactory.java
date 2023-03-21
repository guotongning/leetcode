package com.ning.poker.base.api;

import com.ning.poker.base.exception.NoSuchPokerGroupException;

public interface PokerGroupFactory {
    PokerGroup create(Poker[] pokers) throws NoSuchPokerGroupException;
}
