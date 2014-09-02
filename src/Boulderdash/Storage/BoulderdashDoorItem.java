package Boulderdash.Storage;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Storage.*;

public class BoulderdashDoorItem extends NGCustomGameObjectItem {

    protected NG2DGameObjectPosition FPosition;
    protected Integer FLayer;

    public BoulderdashDoorItem(NGCustomGame aGame, Integer aLayer) {
        super(aGame);
        FPosition = new NG2DGameObjectPosition();
        FLayer = aLayer;
    }

    public void setPosition(double aX, double aY) {
        FPosition.setX(aX);
        FPosition.setY(aY);
    }

    public NG2DGameObjectPosition getPosition() {
        return FPosition;
    }

    public NGGameEngineMemoryAddress getMemoryAddress() {
        return new NGGameEngineMemoryAddress(FLayer, (int)getPosition().getY(), (int)getPosition().getX());
    }

}
