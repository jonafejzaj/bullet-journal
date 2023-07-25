package cs3500.pa05.controller.task;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.DayWeek;
import cs3500.pa05.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * task controller class for handling add task pop up
 */
public class TaskController implements Controller {
  JournalAddTask journalAddTask;
  Stage stage;
  private DayWeek day;
  @FXML
  private TextField taskNameTxt;
  @FXML
  private TextField taskDescriptionTxt;
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
  private TextField taskCategoryTxT;
  @FXML
  private CheckBox completedCheckBox;
  @FXML
  private Button buttonCancel;
  @FXML
  private Button buttonOk;

  /**
   * constructor for TaskController
   *
   * @param stage window of GUI
   * @param journalAddTask interface
   */
  public TaskController(Stage stage, JournalAddTask journalAddTask) {
    this.stage = stage;
    this.journalAddTask = journalAddTask;
  }

  /**
   * closes window
   */
  private void handleButtonClose() {
    buttonCancel.setOnAction(e -> stage.close());
  }

  /**
   * adds task when user clicks ok
   */
  private void handleButtonOk() {
    buttonOk.setOnAction(e -> {
      journalAddTask.addTask(createTask());
      stage.close();
    });
  }

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
   * creates new instance of a task
   *
   * @return Task
   */
  private Task createTask() {
    String name = taskNameTxt.getText();
    String description = taskDescriptionTxt.getText();
    String cat = taskCategoryTxT.getText();
    Category category = null;

    if (!cat.equals("")) {
      try {
        category = Category.valueOf(cat);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid category: " + cat);
      }
    }

    Task tempTask = new Task(name, this.day);

    if (!description.equals("")) {
      tempTask.setDescription(description);
    }
    tempTask.setCategory(category);

    return tempTask;
  }

  /**
   * calls essential methods
   */
  @Override
  public void run() {
    try {
      handleDayMenu();
      handleButtonClose();
      handleButtonOk();
    } catch (IllegalStateException e) {
      System.err.println("illegal state");
    }
  }
}