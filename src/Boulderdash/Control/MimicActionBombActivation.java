package Boulderdash.Control;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.Bomb;
import Uniplay.Control.NGControlMimicGameObjectActivation;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Storage.NGCustomGame;
import Uniwork.Base.NGPropertyList;

public class MimicActionBombActivation extends NGControlMimicGameObjectActivation {

    @Override
    protected void itemMatureStart(GameObjectActivationItem aItem) {
        super.addMatureItem(aItem);
        NGPropertyList props = new NGPropertyList();
        props.set("StartObject", aItem.getGameObject());
        FManager.ActivateMimic(BoulderdashConsts.MIMIC_ACTION_EXPLOSION, props);
    }

    @Override
    protected void itemMatureEnd(GameObjectActivationItem aItem) {
        FManager.DeactivateMimic(BoulderdashConsts.MIMIC_ACTION_EXPLOSION);
    }

    @Override
    protected void itemActivationCountDown(GameObjectActivationItem aItem) {
        super.itemActivationCountDown(aItem);
        Bomb bomb = (Bomb)aItem.getGameObject();
        bomb.ToggleMode();
    }

    public MimicActionBombActivation(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
    }

}
