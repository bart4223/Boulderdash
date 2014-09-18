package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniplay.Graphics.NGDisplayControllerLayerProp;
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
    public ArrayList<NGDisplayControllerLayerProp> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        ArrayList<NGDisplayControllerLayerProp> res = super.getDisplayControllerLayerProps(aRenderEngine, aDisplayController);
        if (aDisplayController.getName().equals("TWOLAYERS")) {
            res.add(new NGDisplayControllerLayerProp("Background", BoulderdashConsts.SPRITE_EARTH));
            res.add(new NGDisplayControllerLayerProp("Front", FID));
        }
        return res;
    }

}
