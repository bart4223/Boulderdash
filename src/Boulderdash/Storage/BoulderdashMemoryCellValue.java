package Boulderdash.Storage;

import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;

public class BoulderdashMemoryCellValue extends NGGameEngineMemoryObjectCellValue {

    protected CustomSprite getSprite() {
        return (CustomSprite)getObject();
    }

    public BoulderdashMemoryCellValue() {
        super();
    }

    @Override
    public Integer getInteger() {
        return getSprite().getID();
    }

}
