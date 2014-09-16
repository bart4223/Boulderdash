package Boulderdash.Storage;

import Boulderdash.BoulderdashConsts;

public class SpriteBender extends BoulderdashSprite {

    protected Bender FBender;

    public SpriteBender(Bender aBender) {
        super();
        FID = BoulderdashConsts.SPRITE_BENDER_FOOT_DOWN;
        FBender = aBender;
    }

    public Bender getBender() {
        return FBender;
    }

    public Integer getID() {
        switch (FBender.getMode()) {
            case footdown:
                FID = BoulderdashConsts.SPRITE_BENDER_FOOT_DOWN;
                break;
            case footup:
                FID = BoulderdashConsts.SPRITE_BENDER_FOOT_UP;
                break;
        }
        return FID;
    }

}
