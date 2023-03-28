package com.ning.poker.base;

import com.ning.poker.base.api.Player;
import com.ning.poker.base.api.Poker;
import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.base.api.PokerTable;
import com.ning.poker.base.enums.GameState;
import com.ning.poker.base.enums.PlayerState;
import com.ning.poker.base.utils.PokerUtils;

public abstract class BasePlayer implements Player {

    private final String name;
    private PlayerState playerState;
    private GameState gameState;
    private String curTableID;

    public BasePlayer(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String tableID() {
        return curTableID;
    }

    @Override
    public PlayerState state() {
        return playerState;
    }

    @Override
    public GameState gameState() {
        return gameState;
    }

    @Override
    public void ready() {
        this.gameState = GameState.ready();
    }

    @Override
    public void cancelReady() {
        this.gameState = GameState.cancelReady(this.gameState);
    }

    @Override
    public void pause(PokerTable pokerTable) {
        pokerTable.pause(this);
    }

    @Override
    public void cancelPause(PokerTable pokerTable) {
        pokerTable.cancelPause(this);
    }

    @Override
    public void joinGame(String tableID) {
        if (this.curTableID != null) {
            return;
        }
        this.curTableID = tableID;
        this.playerState = PlayerState.joinGame(this.playerState);
    }

    @Override
    public void exitGame(String tableID) {
        if (!tableID.equals(curTableID)) {
            return;
        }
        this.playerState = PlayerState.exitGame(this.playerState);
        this.curTableID = null;
    }

    @Override
    public void organizeHands(Poker[] hands) {
        PokerUtils.sort(hands, true);
    }
}
