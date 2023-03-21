package com.ning.poker.base.api;

import com.ning.poker.base.enums.GameState;
import com.ning.poker.base.enums.PlayerState;

public interface Player {
    String name();

    String tableID();

    PlayerState state();

    GameState gameState();

    void ready();

    void cancelReady();

    void pause(PokerTable pokerTable);

    void cancelPause(PokerTable pokerTable);

    void joinGame(String tableID);

    void exitGame(String tableID);
}
