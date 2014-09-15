package Boulderdash.Storage;

public class SpriteBender extends CustomSprite {

    public final static Integer ID = 1;

    protected Bender FBender;

    public SpriteBender(Bender aBender) {
        super();
        FID = ID;
        FBender = aBender;
    }

    public Bender getBender() {
        return FBender;
    }

    public Integer getID() {
        switch (FBender.getMode()) {
            case footdown:
                FID = ID;
                break;
            case footup:
                FID = 3;
                break;
        }
        return FID;
    }

}
