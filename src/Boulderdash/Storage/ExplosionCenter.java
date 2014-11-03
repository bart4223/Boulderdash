package Boulderdash.Storage;

import Uniplay.Storage.NG2DGameObject;
import Uniplay.Storage.NGCustomGame;

public class ExplosionCenter extends NG2DGameObject {

    protected Integer FExplosionIndex;

    public ExplosionCenter(NGCustomGame aGame) {
        super(aGame);
        FExplosionIndex = 0;
    }

    public void Explosion() {
        FExplosionIndex = FExplosionIndex + 1;
        if (FExplosionIndex > 6) {
            FExplosionIndex = 0;
        }
    }

    public Integer getExplosionIndex() {
        return FExplosionIndex;
    }

}
