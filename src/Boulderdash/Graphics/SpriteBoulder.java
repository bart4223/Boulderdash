package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniplay.Graphics.NGDisplayControllerLayerProp;
import Uniwork.Visuals.NGDisplayController;

import java.util.ArrayList;

public class SpriteBoulder extends BoulderdashSprite {

    protected Boolean FInEarth;

    public SpriteBoulder() {
        super();
        FID = BoulderdashConsts.SPRITE_BOULDER;
        FInEarth = true;
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
        return FInEarth;
    }

    public void setInEarth(Boolean aInEarth) {
        FInEarth = aInEarth;
    }

}
