package com.ning.poker.base;

public interface Poker {
    String face();

    String decor();

    int weight();

    int compareTo(Poker poker);
}
