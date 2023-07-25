package cs3500.pa05.controller.password;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.bujo.BujoLoadController;
import cs3500.pa05.view.GuiView;
import cs3500.pa05.view.GuiViewImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * Password controller class
 */
public class PasswordController implements Controller {
  @FXML
  PasswordField enterPassword;
  @FXML
  Button login;

  Stage eventStage;

  /**
   * instantiate new stage
   */
  public PasswordController() {
    this.eventStage = new Stage();
  }

  /**
   * handles when log in button is pressed
   */
  public void handleButtonLogin() {


    if (enterPassword.getText().equals("firehazard")) {
      Controller bujoController = new BujoLoadController();
      GuiView bujoGui = new GuiViewImpl("chooseBujo.fxml", bujoController);

      try {
        eventStage.setScene(bujoGui.load());
        bujoController.run();
        eventStage.show();
        Stage currentStage = (Stage) login.getScene().getWindow();
        currentStage.close();
      } catch (IllegalStateException exc) {
        System.err.println("Unable to load GUI.");
      }
    }
  }

  /**
   * void method that calls event handlers when needed
   */
  @Override
  public void run() {
    this.login.setOnAction(e -> handleButtonLogin());
  }
}
