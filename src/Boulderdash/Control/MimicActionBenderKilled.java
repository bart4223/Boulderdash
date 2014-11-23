package Boulderdash.Control;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.Bender;
import Boulderdash.Storage.Boulderdash;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicPeriodicAction;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGCustomGameCharacter;
import Uniwork.Base.NGPropertyList;
import javafx.application.Platform;

public class MimicActionBenderKilled extends NGControlMimicPeriodicAction {

    protected Boolean FStartGame;

    @Override
    protected void DoActivate() {
        super.DoActivate();
        FStartGame = false;
        Boulderdash game = (Boulderdash)getGame();
        for (NGCustomGameCharacter pc : game.getPCs()) {
            Bender Bender = (Bender)pc;
            pc.subCurrentLives();
            game.subPoints(500);
            if (pc.getCurrentLives() == 0) {
                FStartGame = true;
            }
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
                    if (FStartGame) {
                        getGame().Restart();
                    }
                    else {
                        getGame().RestartLevel();
                    }
                }
            });
    }

    @Override
    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionBenderKilled(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName, Kind.temporary);
        FStartGame = false;
    }

}
