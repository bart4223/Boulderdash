package Boulderdash.Storage;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Storage.*;

public class Door extends NGCustomGameObject {

    public enum State {close, none, quarter, half, threequarter, open};

    protected NG2DObjectPosition FPosition;
    protected Integer FLayer;
    protected State FState;

    public Door(NGCustomGame aGame, Integer aLayer) {
        super(aGame);
        FPosition = new NG2DObjectPosition();
        FLayer = aLayer;
        FState = State.close;
    }

    public void setPosition(double aX, double aY) {
        FPosition.setX(aX);
        FPosition.setY(aY);
    }

    public NG2DObjectPosition getPosition() {
        return FPosition;
    }

    public NGGameEngineMemoryAddress getMemoryAddress() {
        return new NGGameEngineMemoryAddress(FLayer, (int)FPosition.getY(), (int)FPosition.getX());
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
                break;
        }
    }

    public State getState() {
        return FState;
    }

    public void setState(State aState) {
        FState = aState;
        getGame().refreshMemoryCell(getMemoryAddress());
    }

    public Boolean IsOpen() {
        return FState == State.open;
    }

    public Boolean IsClose() {
        return FState == State.close;
    }

    public Boolean IsDoorFromAddress(NGGameEngineMemoryAddress aAddress) {
        return getMemoryAddress().equals(aAddress);
    }

}
