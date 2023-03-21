package com.ning.poker.base;

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
