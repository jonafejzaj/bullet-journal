package cs3500.pa05.controller.event;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.DayWeek;
import cs3500.pa05.model.Event;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * event controller
 */
public class EventController implements Controller {
  private Stage eventStage;
  private JournalAddEvent journalAddEvent;
  private DayWeek day;
  @FXML
  private TextField eventNameTxt;
  @FXML
  private MenuButton dayMenu;
  @FXML
  private MenuItem sundayItem;
  @FXML
  private MenuItem mondayItem;
  @FXML
  private MenuItem tuesdayItem;
  @FXML
  private MenuItem wednesdayItem;
  @FXML
  private MenuItem thursdayItem;
  @FXML
  private MenuItem fridayItem;
  @FXML
  private MenuItem saturdayItem;
  @FXML
  private TextField eventStartTxt;
  @FXML
  private TextField eventDurationTxt;
  @FXML
  private TextField eventCategoryTxT;
  @FXML
  private TextField eventDescriptionTxt;
  @FXML
  private Button buttonOk;
  @FXML
  private Button buttonCancel;

  /**
   * event controller constructor
   *
   * @param stage window
   * @param journalAddEvent interface
   */
  public EventController(Stage stage, JournalAddEvent journalAddEvent) {
    this.eventStage = stage;
    this.journalAddEvent = journalAddEvent;
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
      journalAddEvent.addEvent(createEvent());
      eventStage.close();
    });
  }

  /**
   * handles day menu
   */
  private void handleDayMenu() {
    sundayItem.setOnAction(e2 -> {
      dayMenu.setText("Sunday");
      day = DayWeek.SUNDAY;
    });
    mondayItem.setOnAction(e2 -> {
      dayMenu.setText("Monday");
      day = DayWeek.MONDAY;
    });
    tuesdayItem.setOnAction(e2 -> {
      dayMenu.setText("Tuesday");
      day = DayWeek.TUESDAY;
    });
    wednesdayItem.setOnAction(e2 -> {
      dayMenu.setText("Wednesday");
      day = DayWeek.WEDNESDAY;
    });
    thursdayItem.setOnAction(e2 -> {
      dayMenu.setText("Thursday");
      day = DayWeek.THURSDAY;
    });
    fridayItem.setOnAction(e2 -> {
      dayMenu.setText("Friday");
      day = DayWeek.FRIDAY;
    });
    saturdayItem.setOnAction(e2 -> {
      dayMenu.setText("Saturday");
      day = DayWeek.SATURDAY;
    });
  }

  /**
   * create new event
   *
   * @return event
   */
  private Event createEvent() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
    String name = eventNameTxt.getText();
    LocalTime time = LocalTime.parse(eventStartTxt.getText(), formatter);
    int duration = Integer.parseInt(eventDurationTxt.getText());
    String description = eventDescriptionTxt.getText();
    String cat = eventCategoryTxT.getText();
    Category category = null;

    if (!cat.equals("")) {
      try {
        category = Category.valueOf(cat);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid category: " + cat);
      }
    }
    Event tempEvent = new Event(name, this.day, time, duration);
    if (!description.equals("")) {
      tempEvent.setDescription(description);
    }
    tempEvent.setCategory(category);
    return tempEvent;
  }

  /**
   * call essential methods
   */
  @Override
  public void run() {
    handleButtonClose();
    handleButtonOk();
    handleDayMenu();
  }
}