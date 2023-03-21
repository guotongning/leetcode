package com.ning.poker.ddz;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.enums.PokerDecor;
import com.ning.poker.base.enums.PokerFace;

import java.util.ArrayList;
import java.util.List;

public class DDZPoker implements Poker {

    private final String face;
    private final String decor;
    private final int score;

    public DDZPoker(String face, String decor, int score) {
        this.face = face;
        this.decor = decor;
        this.score = score;
    }

    public static Poker[] defaultPokers() {
        List<Poker> pokers = new ArrayList<>();
        for (PokerDecor pokerDecor : PokerDecor.values()) {
            if (PokerDecor.BIG == pokerDecor || PokerDecor.SMALL == pokerDecor)
                continue;
            for (PokerFace pokerFace : PokerFace.values()) {
                if (PokerFace.JOKER == pokerFace)
                    continue;
                int score = pokerFace.getDefaultScore();
                if (PokerFace.TWO == pokerFace)
                    score = 15;
                if (PokerFace.A == pokerFace)
                    score = 14;
                pokers.add(new DDZPoker(pokerFace.getFace(), pokerDecor.getDecor(), score));
            }
        }
        pokers.add(new DDZPoker(PokerFace.JOKER.getFace(), PokerDecor.BIG.getDecor(), 17));
        pokers.add(new DDZPoker(PokerFace.JOKER.getFace(), PokerDecor.SMALL.getDecor(), 16));
        return pokers.toArray(new Poker[0]);
    }

    @Override
    public String face() {
        return this.face;
    }

    @Override
    public String decor() {
        return this.decor;
    }

    @Override
    public int score() {
        return score;
    }

    @Override
    public int compareTo(Poker poker) {
        return Integer.compare(this.score, poker.score());
    }

    @Override
    public String toString() {
        return face + "_" + decor;
    }
}
