package com.ning.poker.base.enums;

public enum PlayerState {
    IN_GAME,
    FREE,
    ;

    public static PlayerState joinGame(PlayerState playerState) {
        if (FREE == playerState) {
            return IN_GAME;
        }
        return playerState;
    }

    public static PlayerState exitGame(PlayerState playerState) {
        if (IN_GAME == playerState) {
            return FREE;
        }
        return playerState;
    }
}
