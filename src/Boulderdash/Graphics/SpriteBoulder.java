package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.Boulder;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniplay.Graphics.NGDisplayControllerLayerProp;
import Uniwork.Visuals.NGDisplayController;

import java.util.ArrayList;

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
                return "";
            }
        }
        else {
            return "";
        }
    }

    @Override
    public ArrayList<NGDisplayControllerLayerProp> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        ArrayList<NGDisplayControllerLayerProp> res = super.getDisplayControllerLayerProps(aRenderEngine, aDisplayController);
        if (aDisplayController.getName().equals("TWOLAYERS")) {
            res.add(new NGDisplayControllerLayerProp("Background", BoulderdashConsts.SPRITE_EARTH));
            res.add(new NGDisplayControllerLayerProp("Front", FID));
        }
        return res;
    }

    public Boolean getInEarth() {
        return FBoulder.getInEarth();
    }

    public void setInEarth(Boolean aInEarth) {
        FBoulder.setInEarth(aInEarth);
    }

}
