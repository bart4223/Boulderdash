package Boulderdash.Storage;

public class SpriteDoor extends CustomSprite {

    public final static Integer ID = 7;

    protected Door FDoor;

    public SpriteDoor(Door aDoor) {
        super();
        FID = ID;
        FDoor = aDoor;
    }

    public Door getDoor() {
        return FDoor;
    }

    public Integer getID() {
        switch (FDoor.getState()) {
            case close:
                FID = ID;
                break;
            case quarter:
                FID = 8;
                break;
            case half:
                FID = 9;
                break;
            case threequarter:
                FID = 10;
                break;
            case open:
                FID = 11;
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

}
