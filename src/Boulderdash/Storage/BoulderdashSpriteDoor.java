package Boulderdash.Storage;

public class BoulderdashSpriteDoor extends BoulderdashCustomSprite {

    public final static Integer ID  = 7;
    public final static Integer MAX_ID  = 11;

    public BoulderdashSpriteDoor() {
        super();
        FID = ID;
    }

    public void Open() {
        if (FID < MAX_ID) {
            FID++;
        }
    }

    public Boolean IsOpen() {
        return FID >= MAX_ID;
    }

}
