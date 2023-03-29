package com.ning.poker.base;

import com.ning.poker.base.api.Player;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerPack;
import com.ning.poker.base.api.PokerTable;
import com.ning.poker.base.enums.GameState;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BasePokerTable implements PokerTable {

    private final String tableID;
    private final Player[] players;
    private final PokerPack pokerPack;
    private GameState gameState = GameState.NOT_READY;
    private String lastPausePlayer;
    private final AtomicInteger curPlayerCount = new AtomicInteger(0);

    public BasePokerTable(PokerPack pokerPack, int numberOfPlayers) {
        this.players = new Player[numberOfPlayers];
        this.tableID = UUID.randomUUID().toString();
        this.pokerPack = pokerPack;
        PokerTableManager.register(this);
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
        pokerPack.disorganize();
    }

    @Override
    public void start() {
        if (curPlayerCount.get() < players.length) {
            return;
        }
        for (Player player : players) {
            if (GameState.READY != player.gameState()) {
                this.gameState = GameState.READY_START;
            }
        }
        this.gameState = GameState.start(this.gameState);
        if (GameState.READY_START == this.gameState) {
            //TODO do start
        } else {
            //TODO can't start,do something
        }
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
        //TODO do something
    }

    @Override
    public void cancelPause(Player player) {
        if (player.name().equals(this.lastPausePlayer)) {
            this.gameState = GameState.cancelPause(this.gameState);
            this.lastPausePlayer = null;
        }
        //TODO do something
    }

    @Override
    public void settlement() {
        //TODO do something
    }

    @Override
    public void destroy() {
        this.gameState = GameState.destroy(this.gameState, players, this.tableID);
        settlement();
        //TODO do something
    }

    @Override
    public GameState state() {
        return gameState;
    }

    @Override
    public void joinPlayer(Player player) {
        if (player == null) {
            return;
        }
        if (curPlayerCount.get() == players.length) {
            return;
        }
        player.joinGame(this.tableID);
        players[curPlayerCount.getAndIncrement()] = player;
    }

    public Poker deal() {
        return pokerPack.deal();
    }

    public Poker[] deal(int pokerNum) {
        return pokerPack.deal(pokerNum);
    }
}
