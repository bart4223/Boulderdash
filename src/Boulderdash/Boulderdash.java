package Boulderdash;

import Uniplay.NG2DGameEngine;
import Uniwork.Base.NGObject;

public class Boulderdash extends NGObject {

    protected NG2DGameEngine FGameEngine;

    public Boulderdash() {
        super();
        FGameEngine = new NG2DGameEngine();
        //FGameEngine.setDebugLevel(10);
    }

    public void Run() {
        FGameEngine.Initialize();
        FGameEngine.Run();
    }

    public void Stop() {
        FGameEngine.Stop();
        FGameEngine.Finalize();
    }

}
