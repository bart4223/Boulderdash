package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.ExplosionCenter;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Visuals.NGDisplayController;

import java.util.concurrent.CopyOnWriteArrayList;

public class SpriteExplosion extends BoulderdashSprite {

    protected ExplosionCenter FExplosionCenter;

    public SpriteExplosion(ExplosionCenter aExplosionCenter) {
        super();
        FExplosionCenter = aExplosionCenter;
        FID = BoulderdashConsts.SPRITE_EXPLOSION;
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
            res.add(new NGPropertyItem("AnimationIndex", FExplosionCenter.getExplosionIndex()));
        }
        return res;
    }

    @Override
    public CopyOnWriteArrayList<NGPropertyItem> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        CopyOnWriteArrayList<NGPropertyItem> res = super.getDisplayControllerLayerProps(aRenderEngine, aDisplayController);
        if (aDisplayController.getName().equals("ANIMATION")) {
            res.add(new NGPropertyItem("Background", BoulderdashConsts.SPRITE_AIR));
            res.add(new NGPropertyItem("Front", FID));
        }
        return res;
    }

    public ExplosionCenter getExplosionCenter() {
        return FExplosionCenter;
    }

    @Override
    public NGCustomGameObject getGameObject() {
        return FExplosionCenter;
    }

}
