package com.ning.poker.ddz;

import com.ning.poker.base.BasePlayer;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.ddz.poker_group.DDZPokerGroupFactory;

public class DDZPlayer extends BasePlayer {

    private Poker[] pokers;

    public DDZPlayer(String name) {
        super(name);
    }

    public void receiveHands(Poker[] pokers) {

    }

    public PokerGroup showHands(Poker[] selectedPokers) {
        return DDZPokerGroupFactory.getInstance().create(selectedPokers);
    }
}
