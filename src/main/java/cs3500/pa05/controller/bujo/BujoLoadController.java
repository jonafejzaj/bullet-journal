package cs3500.pa05.controller.bujo;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.JournalControllerImpl;
import cs3500.pa05.model.ReadBujo;
import cs3500.pa05.view.GuiView;
import cs3500.pa05.view.GuiViewImpl;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * load bujo controller
 */
public class BujoLoadController implements Controller {
  @FXML
  private Button chooseFile;

  @FXML
  private Button newFile;

  Stage bujoStage;

  /**
   * initalizes new stage
   */
  public BujoLoadController() {
    bujoStage = new Stage();
  }

  /**
   * opens a bujo file
   */
  @FXML
  public void handleOpenFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select File");

    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Bujo Files", "*.bujo"),
        new FileChooser.ExtensionFilter("All Files", "*.*")
    );

    File selectedFile = fileChooser.showOpenDialog(bujoStage);

    if (selectedFile != null) {
      ReadBujo importBujo = new ReadBujo(selectedFile);
      try {
        importBujo.readFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      JournalControllerImpl jci =
          new JournalControllerImpl(importBujo.getMaxEvents(), importBujo.getMaxTasks(),
              importBujo.getWeekTitle(), importBujo.getQuote(), importBujo.getCategory(),
              importBujo.getObtainedEvents(), importBujo.getObtainedTasks());
      GuiView jgi = new GuiViewImpl("bulletJournal.fxml", jci);

      try {
        // load and place the view's scene onto the stage
        bujoStage.setScene(jgi.load());
        bujoStage.setTitle("Bullet Journal");
        jci.run();

        bujoStage.show();
        Stage currentStage = (Stage) newFile.getScene().getWindow();
        currentStage.close();
      } catch (IllegalStateException exc) {
        System.err.println("Unable to load GUI.");
      }
    }
  }

  /**
   * handles user creating new bujo file
   */
  public void handleNewFile() {
    JournalControllerImpl jci = new JournalControllerImpl();
    GuiView jgi = new GuiViewImpl("bulletJournal.fxml", jci);

    try {
      // load and place the view's scene onto the stage
      bujoStage.setScene(jgi.load());
      bujoStage.setTitle("Bullet Journal");
      jci.run();

      bujoStage.show();
      Stage currentStage = (Stage) newFile.getScene().getWindow();
      currentStage.close();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }

  /**
   * runs essential methods
   */
  @Override
  public void run() {
    chooseFile.setOnAction(e -> handleOpenFile());
    newFile.setOnAction(e -> handleNewFile());
  }
}
