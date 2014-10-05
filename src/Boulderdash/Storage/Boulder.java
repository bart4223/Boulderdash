package Boulderdash.Storage;

import Uniplay.Storage.NG2DGameObject;
import Uniplay.Storage.NGCustomGame;

public class Boulder extends NG2DGameObject {

    protected Boolean FInEarth;

    public Boulder(NGCustomGame aGame) {
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

}
