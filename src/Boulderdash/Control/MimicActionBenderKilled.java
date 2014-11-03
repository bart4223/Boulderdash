package Boulderdash.Control;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.Bender;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicPeriodicAction;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGCustomGameCharacter;
import Uniwork.Base.NGPropertyList;
import javafx.application.Platform;

public class MimicActionBenderKilled extends NGControlMimicPeriodicAction {

    @Override
    protected void DoActivate() {
        super.DoActivate();
        NG2DGame game = getGame();
        for (NGCustomGameCharacter pc : game.getPCs()) {
            Bender Bender = (Bender)pc;
            NGPropertyList props = new NGPropertyList();
            props.set("StartObject", Bender);
            FManager.ActivateMimic(BoulderdashConsts.MIMIC_ACTION_EXPLOSION, props);
        }
    }

    @Override
    protected void DoHandleTick() {
        super.DoHandleTick();
        Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    getGame().RestartLevel();
                }
            });
    }

    @Override
    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionBenderKilled(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName, Kind.temporary);
    }

}
