package Boulderdash.Storage;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Storage.*;

public class BoulderdashDoorItem extends NGCustomGameObject {

    protected NG2DObjectPosition FPosition;
    protected Integer FLayer;

    public BoulderdashDoorItem(NGCustomGame aGame, Integer aLayer) {
        super(aGame);
        FPosition = new NG2DObjectPosition();
        FLayer = aLayer;
    }

    public void setPosition(double aX, double aY) {
        FPosition.setX(aX);
        FPosition.setY(aY);
    }

    public NG2DObjectPosition getPosition() {
        return FPosition;
    }

    public NGGameEngineMemoryAddress getMemoryAddress() {
        return new NGGameEngineMemoryAddress(FLayer, (int)getPosition().getY(), (int)getPosition().getX());
    }

}
