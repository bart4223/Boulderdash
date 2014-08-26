package Boulderdash.Storage;

import Uniplay.Kernel.NGGameEngineMemoryCustomCellValue;

public class BoulderdashMemoryCellValue extends NGGameEngineMemoryCustomCellValue {

    protected BoulderdashCustomSprite getSprite() {
        return (BoulderdashCustomSprite)getObject();
    }

    public BoulderdashMemoryCellValue() {
        this(new BoulderdashSpriteAir());
    }

    public BoulderdashMemoryCellValue(BoulderdashCustomSprite aSprite) {
        super(aSprite);
    }

    @Override
    public Integer getInteger() {
        return getSprite().getID();
    }

    @Override
    public void setInteger(Integer aInteger) {
        switch (aInteger) {
            case 0:
                setObject(new BoulderdashSpriteAir());
                break;
        }
    }

    @Override
    public void inc() {
        setInteger(getInteger() + 1);
    }

    @Override
    public void clear() {
        setObject(new BoulderdashSpriteAir());
    }

}
