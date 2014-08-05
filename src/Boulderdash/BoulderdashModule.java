package Boulderdash;

import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGGameManager;

public class BoulderdashModule extends NGGameEngineModule {

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        newGame("Boulderdash");
    }

    @Override
    protected void registerEventHandlers() {
        super.registerEventHandlers();
        registerEventHandler(new BoulderdashModuleEventHandlerKernelInitialized(this));
        registerEventHandler(new BoulderdashModuleEventHandlerKernelStarted(this));
    }

    protected NGGameManager getGameManager() {
        return (NGGameManager)ResolveObject(NGGameEngineConstants.CMP_GAME_MANAGER, NGGameManager.class);
    }

    protected void newGame(String aName) {
        NGGameManager manager = getGameManager();
        Boulderdash game = new Boulderdash(manager, aName);
        game.setGameFieldGridSize(Integer.parseInt(getConfigurationProperty("GameFieldGridSize")));
        game.setShowGameFieldGrid(Boolean.valueOf(getConfigurationProperty("ShowGameFieldGrid")));
        manager.addGame(game);
        game.Initialize();
        registerObject(String.format("%s.Gamefield.Layer1",aName), game.getGameFieldCanvas());
    }

    public BoulderdashModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
