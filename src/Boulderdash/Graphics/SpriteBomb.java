package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.Bomb;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Visuals.NGDisplayController;

import java.util.ArrayList;

public class SpriteBomb extends BoulderdashSprite {

    protected Bomb FBomb;

    public SpriteBomb(Bomb aBomb) {
        super();
        FID = BoulderdashConsts.SPRITE_BOMB;
        FBomb = aBomb;
    }

    @Override
    public NGCustomGameObject getGameObject() {
        return FBomb;
    }

    public Bomb getBomb() {
        return FBomb;
    }

    @Override
    public String getResponsibleDisplayControllerName(NGCustomRenderEngineItem aRenderEngine) {
        if (aRenderEngine.getName().equals("BACK")) {
            return "TWOLAYERS";
        }
        else {
            return "";
        }
    }

    @Override
    public ArrayList<NGPropertyItem> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        ArrayList<NGPropertyItem> res = super.getDisplayControllerLayerProps(aRenderEngine, aDisplayController);
        if (aDisplayController.getName().equals("TWOLAYERS")) {
            if (FBomb.getInEarth()) {
                res.add(new NGPropertyItem("Background", BoulderdashConsts.SPRITE_EARTH));
            }
            else {
                res.add(new NGPropertyItem("Background", BoulderdashConsts.SPRITE_AIR));
            }
            res.add(new NGPropertyItem("Front", FID));
        }
        return res;
    }

    public Boolean getInEarth() {
        return FBomb.getInEarth();
    }

    public void setInEarth(Boolean aInEarth) {
        FBomb.setInEarth(aInEarth);
    }

}
