package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Visuals.NGDisplayController;

import java.util.ArrayList;

public class SpriteBomb extends BoulderdashSprite {

   public SpriteBomb() {
        super();
        FID = BoulderdashConsts.SPRITE_BOMB;
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
            res.add(new NGPropertyItem("Background", BoulderdashConsts.SPRITE_EARTH));
            res.add(new NGPropertyItem("Front", FID));
        }
        return res;
    }

}
