package com.ning.poker.ddz;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.enums.PokerDecor;
import com.ning.poker.ddz.enums.DDZPokerScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        for (DDZPokerScore ddzPokerScore : DDZPokerScore.values()) {
            if (DDZPokerScore.DZZ_BIG_JOKER.equals(ddzPokerScore) || DDZPokerScore.DZZ_SMALL_JOKER.equals(ddzPokerScore)) {
                pokers.add(new DDZPoker(ddzPokerScore.getPokerFace().getFace(), null, ddzPokerScore.getScore()));
                continue;
            }
            for (PokerDecor decor : PokerDecor.values()) {
                pokers.add(new DDZPoker(ddzPokerScore.getPokerFace().getFace(), decor.getDecor(), ddzPokerScore.getScore()));
            }
        }
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
    public String toString() {
        return Optional.ofNullable(decor).orElse("") + face;
    }
}
