package com.ning.poker.base;

public interface PokerTable {

    String tableId();

    Player[] allPlayers();

    void licensing();

    void start();

    void pause(Player player);

    void cancelPause(Player player);

    void settlement();

    void destroy();

    GameState state();

    void register(Player player);

}
