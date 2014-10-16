package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.Boulder;
import Boulderdash.Storage.Diamond;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniplay.Graphics.NGDisplayControllerLayerProp;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Visuals.NGDisplayController;

import java.util.ArrayList;

public class SpriteDiamond extends BoulderdashSprite {

    protected Diamond FDiamond;

    public SpriteDiamond(Diamond aDiamond) {
        super();
        FDiamond = aDiamond;
        FID = BoulderdashConsts.SPRITE_DIAMOND;
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
    public ArrayList<NGDisplayControllerLayerProp> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        ArrayList<NGDisplayControllerLayerProp> res = super.getDisplayControllerLayerProps(aRenderEngine, aDisplayController);
        if (aDisplayController.getName().equals("TWOLAYERS")) {
            res.add(new NGDisplayControllerLayerProp("Background", BoulderdashConsts.SPRITE_EARTH));
            res.add(new NGDisplayControllerLayerProp("Front", FID));
        }
        return res;
    }

    @Override
    public NGCustomGameObject getGameObject() {
        return FDiamond;
    }

    public Diamond getDiamond() {
        return FDiamond;
    }

    public Boolean getInEarth() {
        return FDiamond.getInEarth();
    }

    public void setInEarth(Boolean aInEarth) {
        FDiamond.setInEarth(aInEarth);
    }

}
