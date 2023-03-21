package com.ning.poker.base.enums;

public enum PokerFace {
    A("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    J("J", 11),
    Q("Q", 12),
    K("K", 13),
    JOKER("JOKER", 1000),
    ;
    private final String face;
    private final int defaultScore;

    PokerFace(String face, int score) {
        this.face = face;
        this.defaultScore = score;
    }

    public String getFace() {
        return face;
    }

    public int getDefaultScore() {
        return defaultScore;
    }
}
