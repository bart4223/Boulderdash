package Boulderdash;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class GameControlController implements Initializable {

    public Boulderdash Game;

    @FXML
    protected void handleTest(){
        Game.Test();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
