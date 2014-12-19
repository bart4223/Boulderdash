package Boulderdash.Storage;

import Uniplay.Storage.NG2DGameObject;
import Uniplay.Storage.NGCustomGame;

public class Bomb extends NG2DGameObject {

    public enum Mode {inactive, active}

    protected Boolean FInEarth;

    protected Mode FMode;

    public Bomb(NGCustomGame aGame) {
        super(aGame);
        FPhysics.Mass = 2.0;
        FInEarth = true;
        FMode = Mode.inactive;
    }

    public Boolean getInEarth() {
        return FInEarth;
    }

    public void setInEarth(Boolean aInEarth) {
        FInEarth = aInEarth;
    }

    public Mode getMode() {
        return FMode;
    }

    public void setMode(Mode aMode) {
        FMode = aMode;
        getGame().refreshMemoryCell(getMemoryAddress());
    }

    public void ToggleMode() {
        if (FMode == Mode.active) {
            setMode(Mode.inactive);
        } else {
            setMode(Mode.active);
        }
    }

    @Override
    public void setPosition(double aX, double aY) {
        super.setPosition(aX, aY);
        FInEarth = false;
    }

}
