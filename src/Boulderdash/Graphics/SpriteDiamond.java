package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.Diamond;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Visuals.NGDisplayController;

import java.util.concurrent.CopyOnWriteArrayList;

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
            return "ANIMATION";
        }
        else {
            return "";
        }
    }

    @Override
    public CopyOnWriteArrayList<NGPropertyItem> getDisplayControllerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        CopyOnWriteArrayList<NGPropertyItem> res = super.getDisplayControllerProps(aRenderEngine, aDisplayController);
        if (aDisplayController.getName().equals("ANIMATION")) {
            res.add(new NGPropertyItem("AnimationIndex", FDiamond.getFlickerIndex()));
        }
        return res;
    }

    @Override
    public CopyOnWriteArrayList<NGPropertyItem> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        CopyOnWriteArrayList<NGPropertyItem> res = super.getDisplayControllerLayerProps(aRenderEngine, aDisplayController);
        if (aDisplayController.getName().equals("ANIMATION")) {
            if (FDiamond.getInEarth()) {
                res.add(new NGPropertyItem("Background", BoulderdashConsts.SPRITE_EARTH));
            }
            else {
                res.add(new NGPropertyItem("Background", BoulderdashConsts.SPRITE_AIR));
            }
            res.add(new NGPropertyItem("Front", FID));
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
