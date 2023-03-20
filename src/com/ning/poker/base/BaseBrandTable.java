package com.ning.poker.base;

import java.util.Arrays;
import java.util.UUID;

public class BaseBrandTable implements BrandTable {

    private final String tableID;
    private final Player[] players;
    private final PokerPack pokerPack;
    private GameState gameState;
    private int curPlayerCount;

    public BaseBrandTable(PokerPack pokerPack, int numberOfPlayers) {
        this.players = new Player[numberOfPlayers];
        this.tableID = UUID.randomUUID().toString();
        this.pokerPack = pokerPack;
    }

    @Override
    public String tableId() {
        return tableID;
    }

    @Override
    public Player[] allPlayers() {
        return Arrays.copyOf(players, players.length);
    }

    @Override
    public void licensing() {
        System.out.println(pokerPack);
    }

    @Override
    public void start() {
        this.gameState = GameState.start(this.gameState, players);
    }

    @Override
    public void pause() {

    }

    @Override
    public void settlement() {
        //TODO do something
    }

    @Override
    public void destroy() {

    }

    @Override
    public GameState state() {
        return null;
    }

    @Override
    public void join(Player player) {
        if (player == null) {
            return;
        }
        if (curPlayerCount == players.length) {
            return;
        }
        players[curPlayerCount++] = player;
        //TODO do something
    }
}
