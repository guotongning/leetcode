package com.ning.poker.base;

public interface BrandTable {

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
