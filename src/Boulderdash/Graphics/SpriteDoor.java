package Boulderdash.Graphics;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Storage.Bender;
import Boulderdash.Storage.Door;
import Uniwork.Visuals.NGDisplayController;

public class SpriteDoor extends BoulderdashSprite {

    protected Door FDoor;
    protected Bender FBender;

    public SpriteDoor(Door aDoor) {
        super();
        FID = BoulderdashConsts.SPRITE_DOOR_CLOSE;
        FDoor = aDoor;
        FBender = null;
    }

    public Door getDoor() {
        return FDoor;
    }

    public Bender getBender() {
        return FBender;
    }

    public void setBender(Bender aBender) {
        FBender = aBender;
    }

    @Override
    public Integer getID() {
        switch (FDoor.getState()) {
            case close:
                FID = BoulderdashConsts.SPRITE_DOOR_CLOSE;
                break;
            case none:
                FID = BoulderdashConsts.SPRITE_DOOR_OPEN_NONE;
                break;
            case quarter:
                FID = BoulderdashConsts.SPRITE_DOOR_OPEN_QUARTER;
                break;
            case half:
                FID = BoulderdashConsts.SPRITE_DOOR_OPEN_HALF;
                break;
            case threequarter:
                FID = BoulderdashConsts.SPRITE_DOOR_OPEN_THREEQUARTER;
                break;
            case open:
                FID = BoulderdashConsts.SPRITE_DOOR_OPEN;
                break;
        }
        return FID;
    }

    public Boolean IsOpen() {
        return FDoor.IsOpen();
    }

    public Boolean IsClose() {
        return FDoor.IsClose();
    }

    @Override
    public Integer getValueForDisplayController(String aRenderEngine, NGDisplayController aDisplayController) {
        if (aRenderEngine.equals("BENDER") && (FBender != null)) {
            return SpriteBender.getID(FBender);
        }
        else {
            return super.getValueForDisplayController(aRenderEngine, aDisplayController);
        }
    }

}
