package com.ning.poker.base;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseBrandTable implements BrandTable {

    private final String tableID;
    private final Player[] players;
    private final PokerPack pokerPack;
    private GameState gameState;
    private String lastPausePlayer;
    private final AtomicInteger curPlayerCount = new AtomicInteger(0);

    public BaseBrandTable(PokerPack pokerPack, int numberOfPlayers) {
        this.players = new Player[numberOfPlayers];
        this.tableID = UUID.randomUUID().toString();
        this.pokerPack = pokerPack;
        BrandTableManager.register(this);
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
        if (curPlayerCount.get() < players.length) {
            return;
        }
        this.gameState = GameState.start(this.gameState, players);
    }

    @Override
    public void pause(Player player) {
        if (this.lastPausePlayer != null) {
            return;
        }
        this.gameState = GameState.pause(this.gameState);
        if (GameState.PAUSED == gameState) {
            this.lastPausePlayer = player.name();
        }
    }

    @Override
    public void cancelPause(Player player) {
        if (player.name().equals(this.lastPausePlayer)) {
            this.gameState = GameState.cancelPause(this.gameState);
            this.lastPausePlayer = null;
        }
    }

    @Override
    public void settlement() {
        System.out.println("开始结算");
    }

    @Override
    public void destroy() {
        this.gameState = GameState.destroy(this.gameState, players, this.tableID);
        settlement();
    }

    @Override
    public GameState state() {
        return gameState;
    }

    @Override
    public void register(Player player) {
        if (player == null) {
            return;
        }
        if (curPlayerCount.get() == players.length) {
            return;
        }
        player.joinGame(this.tableID);
        players[curPlayerCount.getAndIncrement()] = player;
        //TODO do something
    }
}
