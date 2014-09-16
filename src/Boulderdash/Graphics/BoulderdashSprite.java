package Boulderdash.Graphics;

import Uniplay.Graphics.NG2DCustomSprite;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Boulderdash.BoulderdashConsts;
import Uniplay.Graphics.NGDisplayControllerLayerProp;
import Uniwork.Visuals.NGDisplayController;

import java.util.ArrayList;

public class BoulderdashSprite extends NG2DCustomSprite {

    public BoulderdashSprite() {
        super();
    }

    @Override
    public Boolean IsRenderEngineResponsible(NGCustomRenderEngineItem aRenderEngine) {
        return aRenderEngine.getName().equals("BACK") || aRenderEngine.getName().equals("BENDER");
    }

    @Override
    public String getResponsibleDisplayControllerName(NGCustomRenderEngineItem aRenderEngine) {
        return "DEFAULT";
    }

    @Override
    public ArrayList<NGDisplayControllerLayerProp> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        return new ArrayList<NGDisplayControllerLayerProp>();
    }

    @Override
    public Integer getValueForDisplayController(String aRenderEngine, NGDisplayController aDisplayController) {
        if (aRenderEngine.equals("BENDER")) {
            return BoulderdashConsts.SPRITE_AIR;
        }
        else {
            return getID();
        }
    }

}
