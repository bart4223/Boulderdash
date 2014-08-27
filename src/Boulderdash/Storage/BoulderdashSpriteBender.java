package Boulderdash.Storage;

public class BoulderdashSpriteBender extends BoulderdashCustomSprite {

    public enum Mode {footdown, footup};

    public final static Integer ID    = 1;
    public final static Integer ID_UP = 3;

    protected Mode FMode;

    public BoulderdashSpriteBender() {
        super();
        setMode(Mode.footdown);
    }

    public Mode getMode() {
        return FMode;
    }

    public void ToggleMode() {
        if (FMode == Mode.footdown) {
            setMode(Mode.footup);
        }
        else {
            setMode(Mode.footdown);
        }
    }

    public void setMode(Mode aMode) {
        FMode = aMode;
        switch (FMode) {
            case footdown:
                FID = ID;
                break;
            case footup:
                FID = ID_UP;
                break;
        }
    }

}
