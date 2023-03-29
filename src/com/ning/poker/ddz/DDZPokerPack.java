package com.ning.poker.ddz;

import com.ning.poker.base.BasePokerPack;

public class DDZPokerPack extends BasePokerPack {

    public static final int MAX_POKER_NUM = 52;

    public DDZPokerPack() {
        super(DDZPoker.defaultPokers());
    }

}
