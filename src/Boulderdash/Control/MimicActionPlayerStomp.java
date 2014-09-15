package Boulderdash.Control;

import Boulderdash.Storage.Bender;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicPeriodicAction;
import Uniplay.Storage.*;

public class MimicActionPlayerStomp extends NGControlMimicPeriodicAction {

    @Override
    protected void DoHandleTick() {
        super.DoHandleTick();
        NG2DGame game = getGame();
        for (NGCustomGameCharacter pc : game.getPCs()) {
            Bender Bender = (Bender)pc;
            Bender.ToggleMode();
        }
    }

    @Override
    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionPlayerStomp(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
    }

}
