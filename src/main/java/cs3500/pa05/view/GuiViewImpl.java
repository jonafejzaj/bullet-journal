package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * gui view class for loading a scene
 */
public class GuiViewImpl implements GuiView {
  private final FXMLLoader loader;

  /**
   * gui view impl constructor instantiates new fxmlLoader and sets its location to given fxml file
   *
   * @param fxmlFile scene builder file
   * @param controller for different scenes and user interactions
   */
  public GuiViewImpl(String fxmlFile, Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(fxmlFile));
    this.loader.setController(controller);
  }

  /**
   * load scene
   *
   * @return scene
   */
  @Override
  public Scene load() {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.", exc);
    }
  }
}
