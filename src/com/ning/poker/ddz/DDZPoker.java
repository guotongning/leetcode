package com.ning.poker.ddz;

import com.ning.poker.base.Poker;

public class DDZPoker implements Poker {

    private final String face;
    private final String decor;
    private final int weight;

    public DDZPoker(String face, String decor, int weight) {
        this.face = face;
        this.decor = decor;
        this.weight = weight;
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
