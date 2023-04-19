package com.team3gdx.game.util;

public class TutorialMode implements GameMode {
    @Override
    public int getNumberOfWaves() {
        return 1;
    }

    @Override
    public int getNumberOfChefs() {
        return 2;
    }

    @Override
    public boolean showTutorial() {
        return true;
    }
}