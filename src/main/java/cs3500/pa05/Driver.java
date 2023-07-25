package cs3500.pa05;

import cs3500.pa05.controller.password.PasswordController;
import cs3500.pa05.splash.SplashScreen;
import cs3500.pa05.view.GuiView;
import cs3500.pa05.view.GuiViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Executes GUI
 */
public class Driver extends Application {
  /**
   * Opens to bujo file chooser then user can enter journal
   *
   * @param stage the primary stage for this application, onto which
   *              the application scene can be set.
   *              Applications may create other stages, if needed, but they will not be
   *              primary stages.
   */
  @Override
  public void start(Stage stage) {
    PasswordController passwordController = new PasswordController();
    GuiView login = new GuiViewImpl("password.fxml", passwordController);
    Scene mainScene = login.load();

    SplashScreen splashScreen = new SplashScreen();
    Scene splashScene = splashScreen.splash(stage, mainScene);

    stage.setScene(splashScene);
    stage.show();

    passwordController.run();
  }

  /**
   * entry point for fx application
   *
   * @param args command line arguments (none for this project)
   */
  public static void main(String[] args) {
    launch();
  }
}