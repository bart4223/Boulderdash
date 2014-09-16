package Boulderdash.Storage;

import Uniplay.Graphics.NG2DCustomSprite;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniplay.Graphics.NGDisplayControllerLayerProp;
import Boulderdash.BoulderdashConsts;
import Uniwork.Visuals.NGDisplayController;

import java.util.ArrayList;

public class BoulderdashSprite extends NG2DCustomSprite {

    public final static Integer ID = BoulderdashConsts.SPRITE_NONE;

    public BoulderdashSprite() {
        super();
        FID = ID;
    }

    @Override
    public Boolean IsRenderEngineResponsible(NGCustomRenderEngineItem aRenderEngine) {
        return aRenderEngine.getName().equals("DEFAULT");
    }

    @Override
    public String getResponsibleDisplayControllerName(NGCustomRenderEngineItem aRenderEngine) {
        return "DEFAULT";
    }

    @Override
    public ArrayList<NGDisplayControllerLayerProp> getDisplayControllerLayerProps(NGDisplayController aDisplayController) {
        return new ArrayList<NGDisplayControllerLayerProp>();
    }

}
