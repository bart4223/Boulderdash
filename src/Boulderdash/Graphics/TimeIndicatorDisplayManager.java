package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Uniwork.Visuals.NGAnimatedImageDisplayController;
import Uniwork.Visuals.NGDisplayController;
import Uniwork.Visuals.NGDisplayManager;
import Uniwork.Visuals.NGImageDisplayController;
import javafx.scene.canvas.Canvas;

public class TimeIndicatorDisplayManager extends NGDisplayManager {

    public String CBomb = "Bomb";
    public String CFusible = "Fusible";
    public String CFlame = "Flame";

    protected Integer FMaxFusibleLength;
    protected Integer FCurrentFusibleLength;
    protected Boolean FFlameBurned;

    protected void CreateIndicators() {
        addController(new NGImageDisplayController(FCanvas, CBomb, "resources/sprites/id_%d.png"));
        addController(new NGImageDisplayController(FCanvas, CFusible, "resources/sprites/id_%d.png"));
        addController(new NGAnimatedImageDisplayController(FCanvas, CFlame, "resources/sprites/id_%d_%d.png"));
    }

    protected void DoRenderFusible(NGImageDisplayController aController) {
        Integer fusibleLength = FCurrentFusibleLength;
        double y = 804;
        double x = FWidth - 36;
        if (fusibleLength == 0) {
            NGDisplayController dc = getController(CFlame);
            dc.setPosition(x, y - 32);
            return;
        }
        aController.setProperty(aController,String.format("%s.ImageNumber", CLAYERBACKGROUND), BoulderdashConsts.SPRITE_FUSIBLE_RIGHT);
        for (int i = 0; i < 24; i++) {
            y = y - 32;
            aController.setPosition(x, y);
            aController.Render();
            fusibleLength--;
            if (fusibleLength == 0) {
                NGDisplayController dc = getController(CFlame);
                dc.setPosition(x, y - 32);
                return;
            }
        }
        aController.setProperty(aController,String.format("%s.ImageNumber", CLAYERBACKGROUND), BoulderdashConsts.SPRITE_FUSIBLE_CORNER_RIGHT);
        y = y - 32;
        aController.setPosition(x, y);
        aController.Render();
        fusibleLength--;
        if (fusibleLength == 0) {
            NGDisplayController dc = getController(CFlame);
            dc.setPosition(x - 32, y);
            return;
        }
        aController.setProperty(aController,String.format("%s.ImageNumber", CLAYERBACKGROUND), BoulderdashConsts.SPRITE_FUSIBLE_TOP);
        for (int i = 0; i < 25; i++) {
            x = x - 32;
            aController.setPosition(x, y);
            aController.Render();
            fusibleLength--;
            if (fusibleLength == 0) {
                NGDisplayController dc = getController(CFlame);
                dc.setPosition(x - 32, y);
                return;
            }
        }
        aController.setProperty(aController,String.format("%s.ImageNumber", CLAYERBACKGROUND), BoulderdashConsts.SPRITE_FUSIBLE_CORNER_LEFT);
        x = x - 32;
        aController.setPosition(x, y);
        aController.Render();
        fusibleLength--;
        if (fusibleLength == 0) {
            NGDisplayController dc = getController(CFlame);
            dc.setPosition(x - 8, y + 32);
            return;
        }
        aController.setProperty(aController,String.format("%s.ImageNumber", CLAYERBACKGROUND), BoulderdashConsts.SPRITE_FUSIBLE_LEFT);
        for (int i = 0; i < 24; i++) {
            y = y + 32;
            aController.setPosition(x, y);
            aController.Render();
            fusibleLength--;
            if (fusibleLength == 0) {
                NGDisplayController dc = getController(CFlame);
                dc.setPosition(x - 8, y + 32);
                return;
            }
        }
    }

    @Override
    protected void DoRenderController(NGDisplayController aController) {
        if (aController.getName().equals(CFusible)) {
            DoRenderFusible((NGImageDisplayController)aController);
        } else if (aController.getName().equals(CFlame)) {
            if (FFlameBurned) {
                super.DoRenderController(aController);
            }
        } else {
            super.DoRenderController(aController);
        }
    }

    protected void DoInitialize() {
        super.DoInitialize();
        NGDisplayController dc = getController(CBomb);
        dc.setProperty(dc, String.format("%s.ImageNumber", CLAYERBACKGROUND), BoulderdashConsts.SPRITE_BOMB);
        dc.setPosition(FWidth - 36, 804);
        dc = getController(CFlame);
        dc.setProperty(dc, String.format("%s.ImageNumber", CLAYERBACKGROUND), BoulderdashConsts.SPRITE_FLAME);
        dc.setProperty(dc, "AnimationIndex", 0);
        dc.setPosition(-32, -32);
    }

    public TimeIndicatorDisplayManager(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public TimeIndicatorDisplayManager(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        FMaxFusibleLength = 75;
        FCurrentFusibleLength = FMaxFusibleLength;
        FFlameBurned = false;
        CreateIndicators();
    }

    public void incFlameAnimationIndex() {
        FFlameBurned = true;
        NGAnimatedImageDisplayController dc = (NGAnimatedImageDisplayController)getController(CFlame);
        dc.AnimationIndex = dc.AnimationIndex + 1;
        if (dc.AnimationIndex > 4) {
            dc.AnimationIndex = 0;
        }
    }

    public Integer getMaxFusibleLength() {
        return FMaxFusibleLength;
    }

    public void resetCurrentFusibelLength() {
        FFlameBurned = false;
        FCurrentFusibleLength = getMaxFusibleLength();
    }

    public void subCurrentFusibleLength() {
        if (FCurrentFusibleLength > 0) {
            FCurrentFusibleLength = FCurrentFusibleLength - 1;
            clearRect(0, 0, FWidth, FHeight - 40);
        }
    }

}
