package com.ning.poker.base.api;

public interface Poker {
    String face();

    String decor();

    int weight();

    int compareTo(Poker poker);
}
