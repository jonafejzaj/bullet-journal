package cs3500.pa05.controller.bujo;

import cs3500.pa05.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for bujo files
 */
public class BujoSaveController implements Controller {
  private final JournalSave journalSave;
  private final Stage stage;
  @FXML
  private TextField saveFileTxt;
  @FXML
  private Button saveOk;
  @FXML
  private Button saveCancel;

  /**
   * constructor takes in a stage and interface
   *
   * @param stage of scene
   * @param journalSave interface containing essential saving methods
   */
  public BujoSaveController(Stage stage, JournalSave journalSave) {
    this.stage = stage;
    this.journalSave = journalSave;
  }

  /**
   * handle user pressing close button
   */
  private void handleButtonClose() {
    saveCancel.setOnAction(e -> stage.close());
  }

  /**
   * handle user pressing ok button
   */
  private void handleButtonOk() {
    saveOk.setOnAction(e -> {
      String file = saveFileTxt.getText();
      if (!file.isEmpty()) {
        journalSave.saveToFile(file);
        stage.close();
      }
    });
  }

  /**
   * calls essential methods
   */
  @Override
  public void run() {
    try {
      handleButtonClose();
      handleButtonOk();
    } catch (IllegalStateException e) {
      System.err.println("illegal state");
    }
  }
}
