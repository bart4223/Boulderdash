package Boulderdash;

import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngine;

public class Boulderdash extends NGUniplayObject {

    protected NGGameEngine FGameEngine;

    public Boulderdash() {
        super();
        FGameEngine = new NGGameEngine(this);
        FGameEngine.setConfigurationFilename("resources/config.ucf");
    }

    public void Run() {
        FGameEngine.Startup();
    }

    public void Stop() {
        FGameEngine.Shutdown();
    }

}
