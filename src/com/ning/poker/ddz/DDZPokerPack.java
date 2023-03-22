package com.ning.poker.ddz;

import com.ning.poker.base.BasePokerPack;

import java.util.Arrays;

public class DDZPokerPack extends BasePokerPack {

    public DDZPokerPack() {
        super(DDZPoker.defaultPokers());
    }

    public static void main(String[] args) {
        DDZPokerPack pokerPack = new DDZPokerPack();
        pokerPack.sort();
        System.out.println(Arrays.toString(pokerPack.showAll()));
    }

}
