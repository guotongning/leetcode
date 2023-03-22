package com.ning.poker.ddz.enums;

import com.ning.poker.base.enums.PokerFace;

import static com.ning.poker.base.enums.PokerFace.*;

public enum DDZPokerScore {
    DZZ_BIG_JOKER(BIG_JOKER, 15),
    DZZ_SMALL_JOKER(SMALL_JOKER, 14),
    DDZ_TWO(TWO, 13),
    DDZ_A(A, 12),
    DDZ_K(K, 11),
    DDZ_Q(Q, 10),
    DDZ_J(J, 9),
    DDZ_TEN(TEN, 8),
    DDZ_NINE(NINE, 7),
    DDZ_EIGHT(EIGHT, 6),
    DDZ_SEVEN(SEVEN, 5),
    DDZ_SIX(SIX, 4),
    DDZ_FIVE(FIVE, 3),
    DDZ_FOUR(FOUR, 2),
    DDZ_THREE(THREE, 1),
    ;
    private final PokerFace pokerFace;
    private final int score;


    DDZPokerScore(PokerFace face, int score) {
        this.pokerFace = face;
        this.score = score;
    }

    public PokerFace getPokerFace() {
        return pokerFace;
    }

    public int getScore() {
        return score;
    }
}
