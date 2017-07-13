package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.Boulder;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Visuals.NGDisplayController;

import java.util.concurrent.CopyOnWriteArrayList;

public class SpriteBoulder extends BoulderdashSprite {

    protected Boulder FBoulder;

    public SpriteBoulder(Boulder aBoulder) {
        super();
        FID = BoulderdashConsts.SPRITE_BOULDER;
        FBoulder = aBoulder;
    }

    @Override
    public String getResponsibleDisplayControllerName(NGCustomRenderEngineItem aRenderEngine) {
        if (aRenderEngine.getName().equals("BACK")) {
            if (getInEarth()) {
                return "TWOLAYERS";
            }
            else {
                return "DEFAULT";
            }
        }
        else {
            return "";
        }
    }

    @Override
    public CopyOnWriteArrayList<NGPropertyItem> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        CopyOnWriteArrayList<NGPropertyItem> res = super.getDisplayControllerLayerProps(aRenderEngine, aDisplayController);
        if (aDisplayController.getName().equals("TWOLAYERS")) {
            res.add(new NGPropertyItem("Background", BoulderdashConsts.SPRITE_EARTH));
            res.add(new NGPropertyItem("Front", FID));
        }
        return res;
    }

    @Override
    public NGCustomGameObject getGameObject() {
        return FBoulder;
    }

    public Boulder getBoulder() {
        return FBoulder;
    }

    public Boolean getInEarth() {
        return FBoulder.getInEarth();
    }

    public void setInEarth(Boolean aInEarth) {
        FBoulder.setInEarth(aInEarth);
    }

}
