package com.ning.poker.base.api;

public interface PokerPack {
    Poker[] showAll();

    void sort();

    void disorganize();

    Poker deal();
}
