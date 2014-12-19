package Boulderdash.Control;

import Boulderdash.Storage.Bomb;
import Uniplay.Control.NGControlMimicGameObjectActivation;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Storage.NGCustomGame;

public class MimicActionBombActivation extends NGControlMimicGameObjectActivation {

    @Override
    protected void itemCountDown(GameObjectActivationItem aItem) {
        super.itemCountDown(aItem);
        Bomb bomb = (Bomb)aItem.getGameObject();
        bomb.ToggleMode();
    }

    public MimicActionBombActivation(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
    }

}
