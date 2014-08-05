package Boulderdash;

import Uniplay.Kernel.NGGameEngineEventHandlerKernelStarted;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGGameManager;

public class BoulderdashModuleEventHandlerKernelStarted extends NGGameEngineEventHandlerKernelStarted {

    protected BoulderdashModule FModule;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        getGameManager().showGames();
    }

    protected NGGameManager getGameManager() {
        return (NGGameManager)FModule.ResolveObject(NGGameEngineConstants.CMP_GAME_MANAGER, NGGameManager.class);
    }

    public BoulderdashModuleEventHandlerKernelStarted(BoulderdashModule aModule) {
        super();
        FModule = aModule;
    }

}
