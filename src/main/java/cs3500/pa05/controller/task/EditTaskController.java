package cs3500.pa05.controller.task;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.DayWeek;
import cs3500.pa05.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * class for editing tasks
 */
public class EditTaskController implements Controller {
  Stage eventStage;
  JournalEditTask journalEditTask;
  Task changeTask;
  @FXML
  private TextField editName;
  @FXML
  private TextField editDescription;
  @FXML
  private TextField editCategory;
  @FXML
  private CheckBox completeBox;
  @FXML
  private Button buttonOk;
  @FXML
  private Button buttonCancel;

  /**
   * instantiate parameters in constructor
   *
   * @param s   stage
   * @param jet journal editing task interface
   * @param t   task
   */
  public EditTaskController(Stage s, JournalEditTask jet, Task t) {
    this.eventStage = s;
    this.journalEditTask = jet;
    this.changeTask = t;
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
      journalEditTask.updateTask(this.changeTask, editTask());
      eventStage.close();
    });
  }

  /**
   * edit task
   *
   * @return task
   */
  private Task editTask() {
    String name = editName.getText();
    String desc = editDescription.getText();
    DayWeek day = changeTask.getDay();
    String cat = editCategory.getText();
    Category category = null;
    if (!cat.equals("")) {
      try {
        category = Category.valueOf(cat);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid category: " + cat);
      }
    }
    Task tempTask = new Task(name, day);
    tempTask.setDescription(desc);
    tempTask.setCategory(category);
    boolean com = completeBox.isSelected();
    tempTask.setCompleted(com);
    return tempTask;
  }


  /**
   * call essential methods
   */
  @Override
  public void run() {
    editName.setText(changeTask.getName());
    editDescription.setText(changeTask.getDescription());
    completeBox.setSelected(changeTask.getCompleted());
    if (changeTask.getCategory() != null) {
      editCategory.setText(changeTask.getCategory().toString());
    }
    handleButtonOk();
    handleButtonClose();
  }
}
