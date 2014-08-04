package Boulderdash;

import Uniplay.Storage.NGCustomPlayer;
import Uniplay.Storage.NGPlayer;
import Uniwork.Visuals.NGStageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;

public class GameControlStageController extends NGStageController {

    public StageManager Manager;

    @FXML
    private ComboBox cbPlayers;

    @FXML
    protected void handlecbPlayers(ActionEvent Event){
        if (Event.getEventType().equals(ActionEvent.ACTION)) {
            if (cbPlayers.getValue() != null) {
                NGPlayer player = Manager.getPlayerManager().setCurrentPlayer(cbPlayers.getValue().toString());
                cbPlayers.setTooltip(new Tooltip(player.getNickname()));
            }
        }
    }

    @FXML
    protected void handleTest(){
        Manager.Test();
    }

    protected void updatecbPlayers() {
        cbPlayers.getItems().clear();
        for (NGCustomPlayer player : Manager.getPlayerManager().getPlayers()) {
            cbPlayers.getItems().add(player.getName());
        }
        if (Manager.getPlayerManager().getCurrentPlayer() != null) {
            // workaround start
            cbPlayers.valueProperty().set(null);
            // workaround end
            cbPlayers.getSelectionModel().select(Manager.getPlayerManager().getCurrentPlayer().getName());
        }
    }

    public void updateControls() {
        updatecbPlayers();
    }

}
