package com.ning.poker.ddz;

import com.ning.poker.base.api.Poker;
import com.ning.poker.base.enums.PokerDecor;
import com.ning.poker.base.enums.PokerFace;

import java.util.ArrayList;
import java.util.List;

public class DDZPoker implements Poker {

    private final String face;
    private final String decor;
    private final int weight;

    public DDZPoker(String face, String decor, int weight) {
        this.face = face;
        this.decor = decor;
        this.weight = weight;
    }

    public static Poker[] defaultPokers() {
        List<Poker> pokers = new ArrayList<>();
        int pokerWeight = 1;
        for (PokerDecor pokerDecor : PokerDecor.values()) {
            if (PokerDecor.BIG == pokerDecor || PokerDecor.SMALL == pokerDecor)
                continue;
            for (PokerFace pokerFace : PokerFace.values()) {
                if (PokerFace.JOKER == pokerFace)
                    continue;
                pokers.add(new DDZPoker(pokerFace.getFace(), pokerDecor.getDecor(), pokerWeight++));
            }
        }
        pokers.add(new DDZPoker(PokerFace.JOKER.getFace(), PokerDecor.BIG.getDecor(), pokerWeight++));
        pokers.add(new DDZPoker(PokerFace.JOKER.getFace(), PokerDecor.SMALL.getDecor(), pokerWeight));
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
    public int weight() {
        return weight;
    }

    @Override
    public int compareTo(Poker poker) {
        return Integer.compare(this.weight, poker.weight());
    }

    @Override
    public String toString() {
        return face + "_" + decor;
    }

    public static void main(String[] args) {
        DDZPoker poker1 = new DDZPoker("A", "♠", 1);
        DDZPoker poker2 = new DDZPoker("A", "♥", 2);
        DDZPoker poker3 = new DDZPoker("A", "♣", 3);
        DDZPoker poker4 = new DDZPoker("A", "♦", 4);
        System.out.println(poker1);
        System.out.println(poker2);
        System.out.println(poker3);
        System.out.println(poker4);
    }
}
