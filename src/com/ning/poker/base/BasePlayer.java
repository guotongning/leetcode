package com.ning.poker.base;

public class BasePlayer implements Player {

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
    public void pause(BrandTable brandTable) {
        brandTable.pause(this);
    }

    @Override
    public void cancelPause(BrandTable brandTable) {
        brandTable.cancelPause(this);
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
}
