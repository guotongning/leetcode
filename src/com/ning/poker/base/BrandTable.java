package com.ning.poker.base;

public interface BrandTable {

    String tableId();

    Player[] allPlayers();

    void licensing();

    void start();

    void pause();

    void settlement();

    void destroy();

    GameState state();

    void join(Player player);

}
