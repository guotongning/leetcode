package com.ning.poker.ddz;

import com.ning.poker.base.BasePokerTable;
import com.ning.poker.base.api.Player;
import com.ning.poker.base.api.Poker;

public class DZZPokerTable extends BasePokerTable {

    private static final Integer PLAYER_NUMBERS = 3;
    private final Poker[] pokerPool = new Poker[DDZPokerPack.MAX_POKER_NUM];

    public DZZPokerTable() {
        super(new DDZPokerPack(), PLAYER_NUMBERS);
    }

    @Override
    public void licensing() {
        super.licensing();
        for (Player player : allPlayers()) {
            player.addHand(deal(17));
        }
    }

    @Override
    public void settlement() {
        super.settlement();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
