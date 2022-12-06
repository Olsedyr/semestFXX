package com.example.semestfxx;

public final class GameSingleton {

    private static GameSingleton INSTANCE;
    private Game game;

    private GameSingleton() {
        game = new Game();
    }

    public static GameSingleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GameSingleton();
        }

        return INSTANCE;
    }

    public Game getGame(){
        return game;
    }
}