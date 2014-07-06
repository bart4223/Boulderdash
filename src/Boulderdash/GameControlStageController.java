package Boulderdash;

import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;

public class GameControlStageController extends NGStageController {

    public StageManager Manager;

    @FXML
    protected void handleTest(){
        Manager.Test();
    }

}
