package com.ning.poker.base.enums;

public enum PokerDecor {
    SPADE("♠"),
    RED_HEART("♥"),
    PLUM_BLOSSOM("♣"),
    BLOCK("♦"),
    BIG("BIG"),
    SMALL("SMALL"),
    ;

    private final String decor;

    PokerDecor(String code) {
        this.decor = code;
    }

    public String getDecor() {
        return decor;
    }
}
