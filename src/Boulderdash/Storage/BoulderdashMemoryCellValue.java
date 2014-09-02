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
            case 1:
                setObject(new BoulderdashSpriteBender());
                break;
            case 2:
                setObject(new BoulderdashSpriteDiamond());
                break;
            case 4:
                setObject(new BoulderdashSpriteBoulder());
                break;
            case 5:
                setObject(new BoulderdashSpriteEarth());
                break;
            case 7:
                setObject(new BoulderdashSpriteDoor());
                break;
            case 12:
                setObject(new BoulderdashSpriteMonster());
                break;
            case 14 :case 15 :case 16 :case 17 :case 18 :case 19 :case 20 :case 21 :case 22 :case 23 :case 24 :case 25 :case 26:
                BoulderdashSpriteBrick brick = new BoulderdashSpriteBrick();
                brick.setID(aInteger);
                setObject(brick);
                break;
            case 27:
                setObject(new BoulderdashSpriteBomb());
                break;
        }
    }

    @Override
    public void inc() {
        setInteger(getInteger() + 1);
    }

    @Override
    public void clear() {
        setInteger(0);
    }

}
