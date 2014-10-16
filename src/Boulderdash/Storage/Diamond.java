package Boulderdash.Storage;

import Uniplay.Storage.NG2DGameObject;
import Uniplay.Storage.NGCustomGame;

public class Diamond extends NG2DGameObject {

    protected Boolean FInEarth;

    public Diamond(NGCustomGame aGame) {
        super(aGame);
        FPhysics.Mass = 2.0;
        FInEarth = true;
    }

    public Boolean getInEarth() {
        return FInEarth;
    }

    public void setInEarth(Boolean aInEarth) {
        FInEarth = aInEarth;
    }

    @Override
    public void setPosition(double aX, double aY) {
        super.setPosition(aX, aY);
        FInEarth = false;
    }

}
