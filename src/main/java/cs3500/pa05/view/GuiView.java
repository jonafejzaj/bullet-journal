package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * GuiView interface loads scene
 */
public interface GuiView {
  /**
   * loads GUI
   *
   * @return scene
   * @throws IllegalStateException if scene cannot be shown
   */
  Scene load() throws IllegalStateException;
}
