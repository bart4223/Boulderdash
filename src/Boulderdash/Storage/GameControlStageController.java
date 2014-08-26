package Boulderdash.Storage;

import Boulderdash.Storage.Boulderdash;
import Uniplay.Storage.NGPlayer;
import Uniplay.Storage.NGPlayerItem;
import Uniwork.Visuals.NGStageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;

public class GameControlStageController extends NGStageController {

    public Boulderdash Game;

    @FXML
    private ComboBox cbPlayers;

    @FXML
    protected void handlecbPlayers(ActionEvent Event){
        if (Event.getEventType().equals(ActionEvent.ACTION)) {
            if (cbPlayers.getValue() != null) {
                NGPlayer player = Game.getPlayerManager().setCurrentPlayer(cbPlayers.getValue().toString());
                cbPlayers.setTooltip(new Tooltip(player.getNickname()));
            }
        }
    }

    @FXML
    protected void handleTest(){
        Game.Test();
    }

    @FXML
    protected void handleNew(){
        Game.Finish();
        Game.Start();
    }

    protected void updatecbPlayers() {
        cbPlayers.getItems().clear();
        for (NGPlayerItem item : Game.getPlayerManager().getPlayers()) {
            NGPlayer player = item.getPlayer();
            cbPlayers.getItems().add(player.getName());
        }
        if (Game.getPlayerManager().getCurrentPlayer() != null) {
            // workaround start
            cbPlayers.valueProperty().set(null);
            // workaround end
            cbPlayers.getSelectionModel().select(Game.getPlayerManager().getCurrentPlayer().getName());
        }
    }

    public void updateControls() {
        updatecbPlayers();
    }

}
