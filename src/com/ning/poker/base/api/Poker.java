package com.ning.poker.base.api;

public interface Poker {
    String face();

    String decor();

    int score();

    int compareTo(Poker poker);
}
