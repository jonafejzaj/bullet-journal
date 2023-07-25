package cs3500.pa05.controller.set;

import cs3500.pa05.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Set controller for setting week title, max events and tasks
 */
public class SetController implements Controller {
  private final JournalSetUpdate journalSetUpdate;
  private final Stage stage;
  private int choice;

  @FXML
  private MenuButton setMenu;
  @FXML
  private MenuItem setWeek;
  @FXML
  private MenuItem setEvent;
  @FXML
  private MenuItem setTask;
  @FXML
  private MenuItem setCategory;
  @FXML
  private Label setLabel;
  @FXML
  private TextField textField;
  @FXML
  private Button setButton;

  /**
   * set controller
   *
   * @param stage window of GUI
   * @param controller interface
   */
  public SetController(Stage stage, JournalSetUpdate controller) {
    this.stage = stage;
    this.journalSetUpdate = controller;
  }

  /**
   * handles the updating of labels
   */
  public void handleMenuItems() {
    setWeek.setOnAction(e -> {
      setMenu.setText("Set Week Title");
      setLabel.setText("Set Week Title");
      setButton.setOnAction(buttonEvent -> {
        journalSetUpdate.updateWeekLabel(textField.getText());
        stage.close();
      });
    });

    setEvent.setOnAction(e -> {
      setMenu.setText("Set Max Events");
      setLabel.setText("Set Max Events");
      setButton.setOnAction(buttonEvent -> {
        journalSetUpdate.updateEventSize(textField.getText());
        stage.close();
      });
    });

    setTask.setOnAction(e -> {
      setMenu.setText("Set Max Tasks");
      setLabel.setText("Set Max Tasks");
      setButton.setOnAction(buttonEvent -> {
        journalSetUpdate.updateTaskSize(textField.getText());
        stage.close();
      });
    });

    setCategory.setOnAction(e -> {
      setMenu.setText("Set Week Category");
      setLabel.setText("Set Week Category");
      setButton.setOnAction(buttonEvent -> {
        journalSetUpdate.updateWeekCategory(textField.getText());
        stage.close();
      });
    });
  }

  /**
   * calls essential methods
   */
  @Override
  public void run() {
    handleMenuItems();
  }
}
