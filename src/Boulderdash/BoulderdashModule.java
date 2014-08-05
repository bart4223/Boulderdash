package Boulderdash;

import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public class BoulderdashModule extends NGGameEngineModule {

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        Boulderdash game = new Boulderdash(this, "Boulderdash");
        addSubComponent(game);
        registerEventHandler(new BoulderdashEventHandlerKernelStarted(game));
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        registerObject("Boulderdash.Gamefield.Layer1", getGame().getGameFieldCanvas());
    }

    protected Boulderdash getGame() {
        return (Boulderdash)getSubComponent("Boulderdash");
    }

    public BoulderdashModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
