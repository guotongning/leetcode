package com.ning.poker.base;

public enum GameState {
    //player
    READY,
    UN_READY,
    //brand table
    NOT_READY,
    READY_START,
    IN_PROGRESS,
    PAUSED,
    OVER,
    ;

    public static GameState ready() {
        return READY;
    }

    public static GameState cancelReady(GameState gameState) {
        if (READY == gameState) {
            return UN_READY;
        }
        return gameState;
    }

    public static GameState pause(GameState gameState) {
        if (IN_PROGRESS == gameState) {
            return PAUSED;
        }
        return gameState;
    }

    public static GameState cancelPause(GameState gameState) {
        if (PAUSED == gameState) {
            return IN_PROGRESS;
        }
        return gameState;
    }

    public static GameState start(GameState gameState, Player[] players) {
        for (Player player : players) {
            if (READY != player.gameState()) {
                return NOT_READY;
            }
        }
        if (GameState.NOT_READY == gameState) {
            return READY_START;
        }
        return NOT_READY;
    }
}
