package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.Bender;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniwork.Visuals.NGDisplayController;

public class SpriteBender extends BoulderdashSprite {

    protected Bender FBender;

    public SpriteBender(Bender aBender) {
        super();
        FBender = aBender;
    }

    public Bender getBender() {
        return FBender;
    }

    public static Integer getID(Bender aBender) {
        Integer res = BoulderdashConsts.SPRITE_BENDER_FOOT_DOWN;
        switch (aBender.getMode()) {
            case footdown:
                res = BoulderdashConsts.SPRITE_BENDER_FOOT_DOWN;
                break;
            case footup:
                res = BoulderdashConsts.SPRITE_BENDER_FOOT_UP;
                break;
        }
        return res;
    }

    @Override
    public Integer getID() {
        return getID(FBender);
    }

    @Override
    public Boolean IsRenderEngineResponsible(NGCustomRenderEngineItem aRenderEngine) {
        return aRenderEngine.getName().equals("BENDER") || aRenderEngine.getName().equals("BACK");
    }

    @Override
    public Integer getValueForDisplayController(String aRenderEngine, NGDisplayController aDisplayController) {
        if (aRenderEngine.equals("BACK")) {
            return BoulderdashConsts.SPRITE_AIR;
        }
        else {
            return getID();
        }
    }


}
