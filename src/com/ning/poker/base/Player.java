package com.ning.poker.base;

public interface Player {
    String name();

    PlayerState state();

    GameState gameState();

    void ready();

    void cancelReady();

    void pause();

    void cancelPause();

    void joinGame();

    void exitGame();
}
