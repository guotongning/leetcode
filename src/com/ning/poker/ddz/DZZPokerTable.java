package com.ning.poker.ddz;

import com.ning.poker.base.BasePokerTable;
import com.ning.poker.base.api.PokerPack;

public class DZZPokerTable extends BasePokerTable {

    private static final Integer PLAYER_NUMBERS = 3;

    public DZZPokerTable(PokerPack pokerPack) {
        super(pokerPack, PLAYER_NUMBERS);
    }

    @Override
    public void licensing() {
        super.licensing();
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
