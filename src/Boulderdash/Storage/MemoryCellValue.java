package Boulderdash.Storage;

import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;

public class MemoryCellValue extends NGGameEngineMemoryObjectCellValue {

    protected BoulderdashSprite getSprite() {
        return (BoulderdashSprite)getObject();
    }

    public MemoryCellValue() {
        super();
    }

    @Override
    public Integer getInteger() {
        return getSprite().getID();
    }

}
