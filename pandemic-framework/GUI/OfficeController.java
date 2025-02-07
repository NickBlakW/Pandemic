package GUI;

import Pandemic.Game;
import Pandemic.Inventory;
import Pandemic.Item;
import Pandemic.NPC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OfficeController {

    private NPC npc = new NPC("Karen", "My father is lying sick with a fever in his room.\nWould you go to the pharmacy\nand get some medicine for him?", new Item("pills", "Medicine", "item", ""));
    private Game game;

    Inventory inventory = new Inventory();

    @FXML
    Label questLabel, completeLabel;

    @FXML
    private Button rightButton, inventoryButton;

    @FXML
    void buttonAction(MouseEvent event) throws Exception {
        Stage stage = null;
        Parent root = null;

        if(event.getSource() == rightButton){
            stage = (Stage) rightButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("NorthHallway.fxml"));
            stage.setTitle("North Hallway");
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openInventory(MouseEvent event) throws Exception {
        if (event.getSource() == inventoryButton) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inventory.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Inventory");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void talkToNpc(MouseEvent event)
    {
        questLabel.setText(npc.getQuest());
        questLabel.setVisible(false);

        String css = this.getClass().getResource("Stylesheet.css").toExternalForm();
        questLabel.getStylesheets().add(css);

        completeLabel.setVisible(false);
        completeLabel.getStylesheets().add(css);

        if (event.isPrimaryButtonDown())
        {
            questLabel.setVisible(true);
        }
        else if (event.isSecondaryButtonDown() && inventory.isInInventory(npc.getQuestItem()))
        {
            completeLabel.setText("Thank you!");
            completeLabel.setVisible(true);
        }
        else if (event.isSecondaryButtonDown() && !inventory.isInInventory(npc.getQuestItem()))
        {
            completeLabel.setText("Seems like you haven't\n found the medicine yet.");
            completeLabel.setVisible(true);
        }
    }
}