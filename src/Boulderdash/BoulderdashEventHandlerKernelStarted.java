package Boulderdash;

import Uniplay.Kernel.NGGameEngineEventHandlerKernelStarted;

public class BoulderdashEventHandlerKernelStarted extends NGGameEngineEventHandlerKernelStarted {

    protected Boulderdash FGame;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        FGame.updateGameControlControls();
        FGame.showStages();
    }

    public BoulderdashEventHandlerKernelStarted(Boulderdash aGame) {
        super();
        FGame = aGame;
    }

}
