package cs3500.pa05.controller.event;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.DayWeek;
import cs3500.pa05.model.Event;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * class for editing events
 */
public class EditEventController implements Controller {
  Stage eventStage;
  JournalEditEvent journalEditEvent;
  Event changeEvent;
  @FXML
  private TextField editName;
  @FXML
  private TextField editDescription;
  @FXML
  private TextField editStartTime;
  @FXML
  private TextField editDuration;
  @FXML
  private TextField editCategory;
  @FXML
  private Button buttonOk;
  @FXML
  private Button buttonCancel;

  /**
   * @param s   stage
   * @param jee journal edit event that updates old event with new event
   * @param e   current event
   */
  public EditEventController(Stage s, JournalEditEvent jee, Event e) {
    this.eventStage = s;
    this.journalEditEvent = jee;
    this.changeEvent = e;
  }

  /**
   * closes window when close button is clicked
   */
  private void handleButtonClose() {

    buttonCancel.setOnAction(e -> eventStage.close());
  }

  /**
   * creates new event even ok is clicked
   */
  private void handleButtonOk() {
    buttonOk.setOnAction(e -> {
      journalEditEvent.updateEvent(this.changeEvent, editEvent());
      eventStage.close();
    });
  }

  /**
   * creates new event
   *
   * @return event
   */
  private Event editEvent() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
    String name = editName.getText();
    String desc = editDescription.getText();
    DayWeek day = changeEvent.getDay();
    LocalTime time = LocalTime.parse(editStartTime.getText(), formatter);
    int duration = Integer.parseInt(editDuration.getText());
    String cat = editCategory.getText();
    Category category = null;
    if (!cat.equals("")) {
      try {
        category = Category.valueOf(cat);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid category: " + cat);
      }
    }
    Event tempEvent = new Event(name, day, time, duration);
    tempEvent.setDescription(desc);
    tempEvent.setCategory(category);
    return tempEvent;
  }


  /**
   * call necessary methods
   */
  @Override
  public void run() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
    editName.setText(changeEvent.getName());
    editDescription.setText(changeEvent.getDescription());
    editStartTime.setText(changeEvent.getStartTime().format(formatter));
    editDuration.setText(Integer.toString(changeEvent.getDuration()));
    if (changeEvent.getCategory() != null) {
      editCategory.setText(changeEvent.getCategory().toString());
    }
    handleButtonOk();
    handleButtonClose();
  }
}