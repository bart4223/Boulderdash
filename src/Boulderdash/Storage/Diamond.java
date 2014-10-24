package Boulderdash.Storage;

import Uniplay.Storage.NG2DGameObject;
import Uniplay.Storage.NGCustomGame;

import java.util.Random;

public class Diamond extends NG2DGameObject {

    protected Boolean FInEarth;
    protected Boolean FCollected;
    protected Integer FFlickerIndex;
    protected Integer FFlickerDirection;
    protected Random FGenerator;

    protected Integer getFlickerDirection() {
        if (FGenerator.nextBoolean()) {
            return 1;
        }
        else {
            return -1;
        }
    }

    public Diamond(NGCustomGame aGame) {
        super(aGame);
        FGenerator = new Random();
        FFlickerDirection = getFlickerDirection();
        FPhysics.Mass = 2.0;
        FInEarth = true;
        FCollected = false;
        FFlickerIndex = 0;
    }

    public Boolean getInEarth() {
        return FInEarth;
    }

    public void setInEarth(Boolean aInEarth) {
        FInEarth = aInEarth;
    }

    @Override
    public void setPosition(double aX, double aY) {
        super.setPosition(aX, aY);
        FInEarth = false;
    }

    public void setCollected(Boolean aCollected) {
        FCollected = aCollected;
    }

    public Boolean getCollected() {
        return FCollected;
    }

    public void Flicker() {
        FFlickerIndex = FFlickerIndex + FFlickerDirection;
        if (FFlickerIndex > 6) {
            FFlickerDirection = getFlickerDirection();
            FFlickerIndex = FFlickerIndex + FFlickerDirection;
            if (FFlickerIndex > 6) {
                FFlickerIndex = 0;
            }
        }
        else if (FFlickerIndex < 0) {
            FFlickerDirection = getFlickerDirection();
            FFlickerIndex = FFlickerIndex + FFlickerDirection;
            if (FFlickerIndex < 0) {
                FFlickerIndex = 6;
            }
        }
    }

    public Integer getFlickerIndex() {
        return FFlickerIndex;
    }

}
