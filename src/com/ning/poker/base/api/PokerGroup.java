package com.ning.poker.base.api;

public interface PokerGroup extends PokerComparator {

    int score();

    int initScore();
    Poker[] show();
}
