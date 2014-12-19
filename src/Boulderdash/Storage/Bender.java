package Boulderdash.Storage;

import Uniplay.Storage.NG2DGameCharacter;
import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGCustomPlayer;

public class Bender extends NG2DGameCharacter {

    public enum Mode {footdown, footup}

    protected Mode FMode;
    protected Boolean FInDoor;

    protected void DoReset() {
        super.DoReset();
        FInDoor = false;
    }

    public Bender(NGCustomGame aGame, NGCustomPlayer aPlayer) {
        super(aGame, aPlayer);
        FMode = Mode.footdown;
        FInDoor = false;
        FPhysics.Mass = 1.0;
    }

    public Mode getMode() {
        return FMode;
    }

    public void setMode(Mode aMode) {
        FMode = aMode;
        getGame().refreshMemoryCell(getMemoryAddress());
    }

    public void ToggleMode() {
        if (FMode == Mode.footdown) {
            setMode(Mode.footup);
        } else {
            setMode(Mode.footdown);
        }
    }

    public Boolean getInDoor() {
        return FInDoor;
    }

    public void setInDoor(Boolean aInDoor) {
        FInDoor = aInDoor;
    }

}
