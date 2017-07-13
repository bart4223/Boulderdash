package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.Bomb;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Visuals.NGDisplayController;

import java.util.concurrent.CopyOnWriteArrayList;

public class SpriteBomb extends BoulderdashSprite {

    protected Bomb FBomb;

    public SpriteBomb(Bomb aBomb) {
        super();
        FID = BoulderdashConsts.SPRITE_BOMB;
        FBomb = aBomb;
    }

    @Override
    public NGCustomGameObject getGameObject() {
        return FBomb;
    }

    public Bomb getBomb() {
        return FBomb;
    }

    public static Integer getID(Bomb aBomb) {
        Integer res = BoulderdashConsts.SPRITE_BOMB;
        switch (aBomb.getMode()) {
            case inactive:
                res = BoulderdashConsts.SPRITE_BOMB;
                break;
            case active:
                res = BoulderdashConsts.SPRITE_BOMB_ACTIVATED;
                break;
        }
        return res;
    }

    @Override
    public Integer getID() {
        return getID(FBomb);
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
    public CopyOnWriteArrayList<NGPropertyItem> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        CopyOnWriteArrayList<NGPropertyItem> res = super.getDisplayControllerLayerProps(aRenderEngine, aDisplayController);
        if (aDisplayController.getName().equals("TWOLAYERS")) {
            if (FBomb.getInEarth()) {
                res.add(new NGPropertyItem("Background", BoulderdashConsts.SPRITE_EARTH));
            }
            else {
                res.add(new NGPropertyItem("Background", BoulderdashConsts.SPRITE_AIR));
            }
            res.add(new NGPropertyItem("Front", getID()));
        }
        return res;
    }

    public Boolean getInEarth() {
        return FBomb.getInEarth();
    }

    public void setInEarth(Boolean aInEarth) {
        FBomb.setInEarth(aInEarth);
    }

}
