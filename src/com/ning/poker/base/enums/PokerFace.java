package com.ning.poker.base.enums;

public enum PokerFace {
    A("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    J("J"),
    Q("Q"),
    K("K"),
    BJ("BJ"),
    SJ("SJ"),
    ;
    private final String face;

    PokerFace(String face) {
        this.face = face;
    }

    public String getFace() {
        return face;
    }

    public static PokerFace fromFace(String face) {
        for (PokerFace pokerFace : values()) {
            if (pokerFace.face.equals(face)) {
                return pokerFace;
            }
        }
        return null;
    }
}
