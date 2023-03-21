package com.ning.poker.base;

public interface Player {
    String name();

    String tableID();

    PlayerState state();

    GameState gameState();

    void ready();

    void cancelReady();

    void pause(BrandTable brandTable);

    void cancelPause(BrandTable brandTable);

    void joinGame(String tableID);

    void exitGame(String tableID);
}
