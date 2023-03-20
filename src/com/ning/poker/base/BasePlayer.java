package com.ning.poker.base;

public class BasePlayer implements Player {

    private final String name;
    private PlayerState playerState;
    private GameState gameState;

    public BasePlayer(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
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
    public void pause() {
        this.gameState = GameState.pause(this.gameState);
    }

    @Override
    public void cancelPause() {
        this.gameState = GameState.cancelPause(this.gameState);
    }

    @Override
    public void joinGame() {
        this.playerState = PlayerState.joinGame(this.playerState);
    }

    @Override
    public void exitGame() {
        this.playerState = PlayerState.exitGame(this.playerState);
    }
}
