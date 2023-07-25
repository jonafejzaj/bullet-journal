/**
 * required imports
 */
module cs3500.pa05 {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.datatype.jsr310;
  requires java.desktop;

  opens cs3500.pa05 to javafx.fxml;
  exports cs3500.pa05;
  exports cs3500.pa05.controller;
  exports cs3500.pa05.model;
  exports cs3500.pa05.view;
  opens cs3500.pa05.controller to javafx.fxml;
  exports cs3500.pa05.controller.event;
  opens cs3500.pa05.controller.event to javafx.fxml;
  exports cs3500.pa05.controller.set;
  opens cs3500.pa05.controller.set to javafx.fxml;
  exports cs3500.pa05.controller.task;
  opens cs3500.pa05.controller.task to javafx.fxml;
  exports cs3500.pa05.model.json;
  exports cs3500.pa05.controller.bujo;
  opens cs3500.pa05.controller.bujo to javafx.fxml;
  exports cs3500.pa05.controller.password;
  opens cs3500.pa05.controller.password to javafx.fxml;
  exports cs3500.pa05.splash;
  opens cs3500.pa05.splash to javafx.fxml;
}