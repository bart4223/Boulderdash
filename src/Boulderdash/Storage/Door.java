package Boulderdash.Storage;

import Uniplay.Storage.*;
import javafx.application.Platform;

public class Door extends NG2DGameObject {

    public static void LevelFinished(final NGCustomGame aGame) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                aGame.FinishLevel();
            }
        });
    }

    public enum State {close, none, quarter, half, threequarter, open};

    protected State FState;
    protected Bender FBender;

    public Door(NGCustomGame aGame) {
        super(aGame);
        FState = State.close;
        FPhysics.Mass = 100.0;
    }

    public void Open() {
        switch (FState) {
            case close:
                setState(State.none);
                break;
            case none:
                setState(State.quarter);
                break;
            case quarter:
                setState(State.half);
                break;
            case half:
                setState(State.threequarter);
                break;
            case threequarter:
                setState(State.open);
                break;
        }
    }

    public void Close() {
        switch (FState) {
            case open:
                setState(State.threequarter);
                break;
            case threequarter:
                setState(State.half);
                break;
            case half:
                setState(State.quarter);
                break;
            case quarter:
                setState(State.none);
                break;
            case none:
                setState(State.close);
                if (FBender != null) {
                    LevelFinished(getGame());
                }
                break;
        }
    }

    public State getState() {
        return FState;
    }

    public void setState(State aState) {
        FState = aState;
        FGame.refreshMemoryCell(getMemoryAddress());
    }

    public Boolean IsOpen() {
        return FState == State.open;
    }

    public Boolean IsClose() {
        return FState == State.close;
    }

    public Bender getBender() {
        return FBender;
    }

    public void setBender(Bender aBender) {
        FBender = aBender;
    }

}
