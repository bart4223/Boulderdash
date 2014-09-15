package Boulderdash.Storage;

import Uniplay.Storage.NG2DGameCharacter;
import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGCustomPlayer;

public class Bender extends NG2DGameCharacter {

    public enum Mode {footdown, footup}

    protected Mode FMode;

    public Bender(NGCustomGame aGame, NGCustomPlayer aPlayer) {
        super(aGame, aPlayer);
        FMode = Mode.footdown;
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
        getGame().refreshMemoryCell(getMemoryAddress());
    }

}
