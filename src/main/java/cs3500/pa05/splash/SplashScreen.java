package cs3500.pa05.splash;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Represents the splash screen
 */
public class SplashScreen {


  /**
   * Creates and returns a Scene object for the splash screen.
   *
   * @param stage primary stage of the application.
   * @param mainScene main scene of the application.
   * @return Scene object for the splash screen.
   */

  public Scene splash(Stage stage, Scene mainScene) {
    try {
      AnchorPane anchorPane = new AnchorPane();
      anchorPane.setPrefHeight(339.0);
      anchorPane.setPrefWidth(616.0);

      ImageView imageView = new ImageView();
      imageView.setFitHeight(345.0);
      imageView.setFitWidth(616.0);
      imageView.setLayoutY(1.0);

      InputStream gif = new FileInputStream("src/main/resources/cat-yawn.gif");
      Image image = new Image(gif);
      imageView.setImage(image);

      Label label1 = new Label("hello!");
      label1.setAlignment(javafx.geometry.Pos.CENTER);
      label1.setGraphicTextGap(10.0);
      label1.setLayoutX(14.0);
      label1.setLayoutY(14.0);
      label1.setPrefHeight(65.0);
      label1.setPrefWidth(134.0);
      label1.setFont(Font.font("Andale Mono", 26.0));

      Label label2 = new Label("click anywhere to begin. . .");
      label2.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
      label2.setLayoutX(33.0);
      label2.setLayoutY(64.0);
      label2.setPrefHeight(30.0);
      label2.setPrefWidth(170.0);
      label2.setFont(Font.font("Andale Mono", 10.0));

      Button button = new Button();
      button.setPrefHeight(346.0);
      button.setPrefWidth(615.0);
      button.setStyle("-fx-background-color: clear;");
      button.setOnAction(e -> stage.setScene(mainScene));

      VBox vbox = new VBox();
      anchorPane.getChildren().addAll(vbox, imageView, label1, label2, button);
      return new Scene(anchorPane, 616, 339);
    } catch (IOException e) {
      System.err.println("Error");
    }
    return mainScene;
  }
}