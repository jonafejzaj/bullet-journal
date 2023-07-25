package cs3500.pa05.controller;

import cs3500.pa05.controller.bujo.BujoSaveController;
import cs3500.pa05.controller.bujo.JournalSave;
import cs3500.pa05.controller.event.EditEventController;
import cs3500.pa05.controller.event.EventController;
import cs3500.pa05.controller.event.JournalAddEvent;
import cs3500.pa05.controller.event.JournalEditEvent;
import cs3500.pa05.controller.set.JournalSetUpdate;
import cs3500.pa05.controller.set.SetController;
import cs3500.pa05.controller.task.EditTaskController;
import cs3500.pa05.controller.task.JournalAddTask;
import cs3500.pa05.controller.task.JournalEditTask;
import cs3500.pa05.controller.task.TaskController;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.DayWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.WriteToBujo;
import cs3500.pa05.view.GuiView;
import cs3500.pa05.view.GuiViewImpl;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * journal controller class that handles essential functions for proper journal usage
 */
public class JournalControllerImpl
    implements Controller, JournalSetUpdate, JournalAddEvent, JournalAddTask, JournalSave,
    JournalEditEvent, JournalEditTask {
  private String weekTitle;
  private String currentQuote;
  private String categoryWeek;
  private Pair<ObservableList<String>, String> selectedItem;
  private int currentType;
  private int eventMax;
  private int taskMax;
  private int countTasks = 0;
  private int countEvents = 0;
  private List<Event> eventList;
  private List<Task> taskList;
  private Stage tempStage;
  private ObservableList<String>[] weekEventList;
  private ObservableList<String>[] weekTaskList;

  @FXML
  private Button setAttrs;

  @FXML
  private Button addEvent;

  @FXML
  private Button addTask;

  @FXML
  private Button save;

  @FXML
  private Button delete;

  @FXML
  private Button markDone;
  @FXML
  private Button edit;

  @FXML
  private DialogPane addEventDialog;

  @FXML
  private TextField eventNameTxT;

  @FXML
  private TextField eventDayTxT;

  @FXML
  private TextField eventStartTxT;

  @FXML
  private TextField eventDurationTxT;

  @FXML
  private TextField eventDescriptionTxT;

  @FXML
  private ListView<String> sundayEvent;

  @FXML
  private ListView<String> mondayEvent;

  @FXML
  private ListView<String> tuesdayEvent;

  @FXML
  private ListView<String> wednesdayEvent;

  @FXML
  private ListView<String> thursdayEvent;

  @FXML
  private ListView<String> fridayEvent;

  @FXML
  private ListView<String> saturdayEvent;

  @FXML
  private ListView<String> sundayTask;

  @FXML
  private ListView<String> mondayTask;

  @FXML
  private ListView<String> tuesdayTask;

  @FXML
  private ListView<String> wednesdayTask;

  @FXML
  private ListView<String> thursdayTask;

  @FXML
  private ListView<String> fridayTask;

  @FXML
  private ListView<String> saturdayTask;

  @FXML
  private ListView<String> weekTasks;

  @FXML
  private ListView<String> completeTasks;

  @FXML
  private Label weekLabel;

  @FXML
  private Label errorLabel;

  @FXML
  private Label totalEvents;

  @FXML
  private Label totalTasks;

  @FXML
  private Label percentLabel;

  @FXML
  private Label weekCategory;

  @FXML
  private TextField enterQuote;

  @FXML
  private Button saveQuote;

  @FXML
  private Button clear;

  @FXML
  private Label savedQuote;


  /**
   * initialize components of journal
   */
  public JournalControllerImpl() {
    weekTitle = "Week Title";
    currentQuote = "";
    eventMax = 5;
    taskMax = 5;
    weekEventList = new ObservableList[7];
    weekTaskList = new ObservableList[9];
    eventList = new ArrayList<>();
    taskList = new ArrayList<>();
    tempStage = new Stage();
  }

  /**
   * constructor essential for saving to bujo file
   *
   * @param evntMax  max events
   * @param tskMax   max tasks
   * @param title    of journal
   * @param quote    user has entered
   * @param category category of an event
   * @param events   list of events
   * @param tasks    list of tasks
   */
  public JournalControllerImpl(int evntMax, int tskMax, String title, String quote, String category,
                               List<Event> events, List<Task> tasks) {
    weekTitle = title;
    eventMax = evntMax;
    taskMax = tskMax;
    currentQuote = quote;
    categoryWeek = category;
    weekEventList = new ObservableList[7];
    weekTaskList = new ObservableList[9];
    eventList = new ArrayList<>(events);
    taskList = new ArrayList<>(tasks);
    tempStage = new Stage();
  }

  /**
   * handler for when user clicks the set button: set week name, max events and tasks
   */
  public void handleSetButton() {
    SetController setController = new SetController(tempStage, this);
    GuiView setLoader = new GuiViewImpl("setMenu.fxml", setController);
    tempStage.setScene(setLoader.load());
    tempStage.setTitle("Set Values");
    setController.run();
    tempStage.show();
  }

  /**
   * handler for add event button
   */
  public void handleAddEventButton() {
    EventController eventController = new EventController(tempStage, this);
    GuiView eventLoader = new GuiViewImpl("eventMenu.fxml", eventController);
    tempStage.setScene(eventLoader.load());
    tempStage.setTitle("Add Event");
    eventController.run();
    tempStage.show();
  }

  /**
   * handler for add task button
   */
  public void handleAddTaskButton() {
    TaskController taskController = new TaskController(tempStage, this);
    GuiView taskLoader = new GuiViewImpl("taskMenu.fxml", taskController);
    tempStage.setScene(taskLoader.load());
    tempStage.setTitle("Add Task");
    taskController.run();
    tempStage.show();
  }

  /**
   * handler for the save button for saving journal contents to a bujo file
   */
  public void handleSaveButton() {
    BujoSaveController bujoController = new BujoSaveController(tempStage, this);
    GuiView bujoLoader = new GuiViewImpl("saveBujo.fxml", bujoController);
    tempStage.setScene(bujoLoader.load());
    tempStage.setTitle("Save to Bujo");
    bujoController.run();
    tempStage.show();
  }

  /**
   * handles creating new event when edit event button is clicked
   */
  public void handleEditEventButton() {
    Event tempEvent = null;
    for (Event eve : eventList) {
      if (eve.print().equals(selectedItem.getValue())) {
        tempEvent = eve;
        break;
      }
    }
    EditEventController editEventController =
        new EditEventController(tempStage, this, tempEvent);
    GuiView editLoader = new GuiViewImpl("eventEditMenu.fxml", editEventController);
    tempStage.setScene(editLoader.load());
    tempStage.setTitle("Edit Event");
    editEventController.run();
    tempStage.show();
  }

  /**
   * handler for edit task button, runs method in edit task controller
   */
  public void handleEditTaskButton() {
    Task tempTask = null;
    for (Task t : taskList) {
      if (t.print().equals(selectedItem.getValue())) {
        tempTask = t;
        break;
      }
    }
    EditTaskController editTaskController =
        new EditTaskController(tempStage, this, tempTask);
    GuiView editLoader = new GuiViewImpl("taskEditMenu.fxml", editTaskController);
    tempStage.setScene(editLoader.load());
    tempStage.setTitle("Edit Task");
    editTaskController.run();
    tempStage.show();
  }

  /**
   * initializes days and sets font and style
   */
  public void initializeDays() {
    sundayEvent.setStyle("-fx-font-size: 8px;");
    weekEventList[0] = sundayEvent.getItems();

    mondayEvent.setStyle("-fx-font-size: 8px;");
    weekEventList[1] = mondayEvent.getItems();

    tuesdayEvent.setStyle("-fx-font-size: 8px;");
    weekEventList[2] = tuesdayEvent.getItems();

    wednesdayEvent.setStyle("-fx-font-size: 8px;");
    weekEventList[3] = wednesdayEvent.getItems();

    thursdayEvent.setStyle("-fx-font-size: 8px;");
    weekEventList[4] = thursdayEvent.getItems();

    fridayEvent.setStyle("-fx-font-size: 8px;");
    weekEventList[5] = fridayEvent.getItems();

    saturdayEvent.setStyle("-fx-font-size: 8px;");
    weekEventList[6] = saturdayEvent.getItems();

    sundayTask.setStyle("-fx-font-size: 8px;");
    weekTaskList[0] = sundayTask.getItems();

    mondayTask.setStyle("-fx-font-size: 8px;");
    weekTaskList[1] = mondayTask.getItems();

    tuesdayTask.setStyle("-fx-font-size: 8px;");
    weekTaskList[2] = tuesdayTask.getItems();

    wednesdayTask.setStyle("-fx-font-size: 8px;");
    weekTaskList[3] = wednesdayTask.getItems();

    thursdayTask.setStyle("-fx-font-size: 8px;");
    weekTaskList[4] = thursdayTask.getItems();

    fridayTask.setStyle("-fx-font-size: 8px;");
    weekTaskList[5] = fridayTask.getItems();

    saturdayTask.setStyle("-fx-font-size: 8px;");
    weekTaskList[6] = saturdayTask.getItems();

    weekTasks.setStyle("-fx-font-size: 8px;");
    weekTaskList[7] = weekTasks.getItems();

    completeTasks.setStyle("-fx-font-size: 8px;");
    weekTaskList[8] = completeTasks.getItems();
  }

  /**
   * prints events and adds contents to list
   */
  public void initializeEvents() {
    for (Event e : eventList) {
      if (e.getDay() == DayWeek.SUNDAY && weekEventList[0].size() < eventMax) {
        weekEventList[0].add(e.print());
      } else if (e.getDay() == DayWeek.MONDAY && weekEventList[1].size() < eventMax) {
        weekEventList[1].add(e.print());
      } else if (e.getDay() == DayWeek.TUESDAY && weekEventList[2].size() < eventMax) {
        weekEventList[2].add(e.print());
      } else if (e.getDay() == DayWeek.WEDNESDAY && weekEventList[3].size() < eventMax) {
        weekEventList[3].add(e.print());
      } else if (e.getDay() == DayWeek.THURSDAY && weekEventList[4].size() < eventMax) {
        weekEventList[4].add(e.print());
      } else if (e.getDay() == DayWeek.FRIDAY && weekEventList[5].size() < eventMax) {
        weekEventList[5].add(e.print());
      } else if (e.getDay() == DayWeek.SATURDAY && weekEventList[6].size() < eventMax) {
        weekEventList[6].add(e.print());
      } else {
        errorLabel.setText("Error: Events full for " + e.getDay().getDay());
      }
      countEvents++;
    }
  }

  /**
   * prints tasks and adds contents to list
   */
  public void initializeTasks() {
    for (Task t : taskList) {
      if (t.getDay() == DayWeek.SUNDAY && weekTaskList[0].size() < taskMax) {
        weekTaskList[0].add(t.print());
      } else if (t.getDay() == DayWeek.MONDAY && weekTaskList[1].size() < taskMax) {
        weekTaskList[1].add(t.print());
      } else if (t.getDay() == DayWeek.TUESDAY && weekTaskList[2].size() < taskMax) {
        weekTaskList[2].add(t.print());
      } else if (t.getDay() == DayWeek.WEDNESDAY && weekTaskList[3].size() < taskMax) {
        weekTaskList[3].add(t.print());
      } else if (t.getDay() == DayWeek.THURSDAY && weekTaskList[4].size() < taskMax) {
        weekTaskList[4].add(t.print());
      } else if (t.getDay() == DayWeek.FRIDAY && weekTaskList[5].size() < taskMax) {
        weekTaskList[5].add(t.print());
      } else if (t.getDay() == DayWeek.SATURDAY && weekTaskList[6].size() < taskMax) {
        weekTaskList[6].add(t.print());
      } else {
        errorLabel.setText("Error: Tasks full for " + t.getDay().getDay());
      }
      countTasks++;
      if (t.getCompleted()) {
        weekTaskList[8].add(t.print());
        completedTasks++;
      } else {
        weekTaskList[7].add(t.print());
      }
    }
  }


  /**
   * changes week label name
   *
   * @param text new week label name
   */
  @Override
  public void updateWeekLabel(String text) {
    weekLabel.setText(text);
  }

  /**
   * changes max event value
   *
   * @param max events
   */
  @Override
  public void updateEventSize(String max) {
    this.eventMax = Integer.parseInt(max);
  }

  /**
   * changes task size value
   *
   * @param max task size
   */
  @Override
  public void updateTaskSize(String max) {
    this.taskMax = Integer.parseInt(max);
  }

  /**
   * updates category
   *
   * @param category type
   */
  @Override
  public void updateWeekCategory(String category) {
    weekCategory.setText(" " + (Category.valueOf(category)).getCategory());
  }

  /**
   * adds event to list or throws error if reaches max account
   *
   * @param e event
   */
  @Override
  public void addEvent(Event e) {
    errorLabel.setText("");
    if (e.getDay() == DayWeek.SUNDAY && weekEventList[0].size() < eventMax) {
      eventList.add(e);
      weekEventList[0].add(e.print());
      countEvents++;
    } else if (e.getDay() == DayWeek.MONDAY && weekEventList[1].size() < eventMax) {
      eventList.add(e);
      weekEventList[1].add(e.print());
      countEvents++;
    } else if (e.getDay() == DayWeek.TUESDAY && weekEventList[2].size() < eventMax) {
      eventList.add(e);
      weekEventList[2].add(e.print());
      countEvents++;
    } else if (e.getDay() == DayWeek.WEDNESDAY && weekEventList[3].size() < eventMax) {
      eventList.add(e);
      weekEventList[3].add(e.print());
      countEvents++;
    } else if (e.getDay() == DayWeek.THURSDAY && weekEventList[4].size() < eventMax) {
      eventList.add(e);
      weekEventList[4].add(e.print());
      countEvents++;
    } else if (e.getDay() == DayWeek.FRIDAY && weekEventList[5].size() < eventMax) {
      eventList.add(e);
      weekEventList[5].add(e.print());
      countEvents++;
    } else if (e.getDay() == DayWeek.SATURDAY && weekEventList[6].size() < eventMax) {
      eventList.add(e);
      weekEventList[6].add(e.print());
      countEvents++;
    } else {
      errorLabel.setText("Error: Events full for " + e.getDay().getDay());
    }

    totalEvents.setText(Integer.toString(countEvents));
  }

  /**
   * adds task or throws error if reached max amount
   *
   * @param t task
   */
  @Override
  public void addTask(Task t) {
    errorLabel.setText("");
    if (t.getDay() == DayWeek.SUNDAY && weekTaskList[0].size() < taskMax) {
      taskList.add(t);
      weekTaskList[0].add(t.print());
      weekTaskList[7].add(t.print());
      countTasks++;
    } else if (t.getDay() == DayWeek.MONDAY && weekTaskList[1].size() < taskMax) {
      taskList.add(t);
      weekTaskList[1].add(t.print());
      weekTaskList[7].add(t.print());
      countTasks++;
    } else if (t.getDay() == DayWeek.TUESDAY && weekTaskList[2].size() < taskMax) {
      taskList.add(t);
      weekTaskList[2].add(t.print());
      weekTaskList[7].add(t.print());
      countTasks++;
    } else if (t.getDay() == DayWeek.WEDNESDAY && weekTaskList[3].size() < taskMax) {
      taskList.add(t);
      weekTaskList[3].add(t.print());
      weekTaskList[7].add(t.print());
      countTasks++;
    } else if (t.getDay() == DayWeek.THURSDAY && weekTaskList[4].size() < taskMax) {
      taskList.add(t);
      weekTaskList[4].add(t.print());
      weekTaskList[7].add(t.print());
      countTasks++;
    } else if (t.getDay() == DayWeek.FRIDAY && weekTaskList[5].size() < taskMax) {
      taskList.add(t);
      weekTaskList[5].add(t.print());
      weekTaskList[7].add(t.print());
      countTasks++;
    } else if (t.getDay() == DayWeek.SATURDAY && weekTaskList[6].size() < taskMax) {
      taskList.add(t);
      weekTaskList[6].add(t.print());
      weekTaskList[7].add(t.print());
      countTasks++;
    } else {
      errorLabel.setText("Error: Tasks full for " + t.getDay().getDay());
    }
    totalTasks.setText(Integer.toString(countTasks));
    updatePercentCompleted();
  }

  /**
   * @param lv   list view
   * @param list list
   */
  private void setEventListViewClick(ListView<String> lv, ObservableList<String> list) {
    lv.setOnMouseClicked(e -> {
      selectedItem = new Pair<>(list, lv.getSelectionModel().getSelectedItem());
      currentType = 0;
    });
  }

  private void setTaskListViewClick(ListView<String> lv, ObservableList<String> list) {
    lv.setOnMouseClicked(e -> {
      selectedItem = new Pair<>(list, lv.getSelectionModel().getSelectedItem());
      currentType = 1;
    });
  }

  /**
   * handler for when user clicks delete for deleting a task or event
   */
  private void handleDeleteButton() {
    delete.setOnAction(e -> {
      if (selectedItem != null) {
        if (selectedItem.getValue() != null) {
          selectedItem.getKey().remove(selectedItem.getValue());
          if (currentType == 0) {
            Event tempEvent = null;
            for (Event eve : eventList) {
              if (eve.print().equals(selectedItem.getValue())) {
                tempEvent = eve;
                break;
              }
            }
            eventList.remove(tempEvent);
            countEvents--;
            totalEvents.setText(Integer.toString(countEvents));
          } else if (currentType == 1) {
            Task tempTask = null;
            for (Task t : taskList) {
              if (t.print().equals(selectedItem.getValue())) {
                tempTask = t;
                break;
              }
            }

            taskList.remove(tempTask);
            if (tempTask.getCompleted()) {
              weekTaskList[8].remove(selectedItem.getValue());
              completedTasks--;
            } else {
              weekTaskList[7].remove(selectedItem.getValue());
            }
            countTasks--;
            updatePercentCompleted();
            totalTasks.setText(Integer.toString(countTasks));
          }
        }
      }
    });
  }

  private int completedTasks = 0;

  /**
   * handler for when user marks task as completed
   */
  private void handleMarkDoneButton() {
    markDone.setOnAction(e -> {
      if (selectedItem != null && currentType == 1) {
        for (Task t : taskList) {
          if (t.print().equals(selectedItem.getValue()) && !t.getCompleted()) {
            t.setCompleted(true);
            completedTasks++;
            int index = selectedItem.getKey().indexOf(selectedItem.getValue());
            selectedItem.getKey().set(index, t.print());
            weekTaskList[7].remove(selectedItem.getValue());
            weekTaskList[8].add(t.print());
            updatePercentCompleted();
            break;
          }
        }
      }
    });
  }

  /**
   * sets events to corresponding days based on week
   */
  public void handleListViewClick() {
    setEventListViewClick(sundayEvent, weekEventList[0]);
    setEventListViewClick(mondayEvent, weekEventList[1]);
    setEventListViewClick(tuesdayEvent, weekEventList[2]);
    setEventListViewClick(wednesdayEvent, weekEventList[3]);
    setEventListViewClick(thursdayEvent, weekEventList[4]);
    setEventListViewClick(fridayEvent, weekEventList[5]);
    setEventListViewClick(saturdayEvent, weekEventList[6]);
    setTaskListViewClick(sundayTask, weekTaskList[0]);
    setTaskListViewClick(mondayTask, weekTaskList[1]);
    setTaskListViewClick(tuesdayTask, weekTaskList[2]);
    setTaskListViewClick(wednesdayTask, weekTaskList[3]);
    setTaskListViewClick(thursdayTask, weekTaskList[4]);
    setTaskListViewClick(fridayTask, weekTaskList[5]);
    setTaskListViewClick(saturdayTask, weekTaskList[6]);
  }

  /**
   * updates percent of tasks completed in the weekly stats
   */
  public void updatePercentCompleted() {
    double percent;
    if (countTasks == 0) {
      percentLabel.setText("");
    } else {
      percent = Math.round((completedTasks / (double) taskList.size()) * 100);
      String completed = percent + "%";
      percentLabel.setText(completed);
    }
  }

  /**
   * writes saved bujo contents to file
   *
   * @param file path of file
   */
  @Override
  public void saveToFile(String file) {
    WriteToBujo writeBujo = new WriteToBujo(file);
    writeBujo.writeToFile(eventMax, taskMax, weekLabel.getText(), weekCategory.getText(),
        savedQuote.getText(), eventList, taskList);
  }

  /**
   * sets label as saved user quote or note
   */
  public void setSaveQuote() {
    saveQuote.setOnAction(e -> {
      String quote = enterQuote.getText();
      savedQuote.setText(quote);
    });
  }

  /**
   * clears text box
   */
  public void setClear() {
    clear.setOnAction(e -> savedQuote.setText(""));
  }

  @Override
  public void updateEvent(Event oldEvent, Event newEvent) {
    int indexEvent = eventList.indexOf(oldEvent);
    eventList.set(indexEvent, newEvent);
    for (ObservableList<String> ol : weekEventList) {
      if (ol.contains(oldEvent.print())) {
        int printIndex = ol.indexOf(oldEvent.print());
        ol.set(printIndex, newEvent.print());
        break;
      }
    }
  }

  @Override
  public void updateTask(Task oldTask, Task newTask) {
    int indexTask = taskList.indexOf(oldTask);
    taskList.set(indexTask, newTask);
    if (oldTask.getCompleted() && newTask.getCompleted()) {
      int indexComplete = weekTaskList[8].indexOf(oldTask.print());
      weekTaskList[8].set(indexComplete, newTask.print());
    } else if (!oldTask.getCompleted() && newTask.getCompleted()) {
      weekTaskList[7].remove(oldTask.print());
      weekTaskList[8].add(newTask.print());
      completedTasks++;
      updatePercentCompleted();
    } else if (oldTask.getCompleted() && !newTask.getCompleted()) {
      weekTaskList[8].remove(oldTask.print());
      weekTaskList[7].add(newTask.print());
      completedTasks--;
      updatePercentCompleted();
    } else if (!oldTask.getCompleted() && !newTask.getCompleted()) {
      int indexIncomplete = weekTaskList[7].indexOf(oldTask.print());
      weekTaskList[7].set(indexIncomplete, newTask.print());
    }

    for (ObservableList<String> ol : weekTaskList) {
      if (ol.contains(oldTask.print())) {
        int printIndex = ol.indexOf(oldTask.print());
        ol.set(printIndex, newTask.print());
        break;
      }
    }
  }

  /**
   * calls essential methods
   */
  @Override
  public void run() {
    try {
      initializeDays();
      initializeEvents();
      initializeTasks();
      handleListViewClick();
      handleDeleteButton();
      handleMarkDoneButton();
      updatePercentCompleted();
      totalEvents.setText(Integer.toString(countEvents));
      totalTasks.setText(Integer.toString(countTasks));
      weekLabel.setText(weekTitle);
      savedQuote.setText(currentQuote);
      weekCategory.setText(categoryWeek);
      setAttrs.setOnAction(e -> handleSetButton());
      addEvent.setOnAction(e -> handleAddEventButton());
      addTask.setOnAction(e -> handleAddTaskButton());
      save.setOnAction(e -> handleSaveButton());
      edit.setOnAction(e -> {
        if (selectedItem != null) {
          if (currentType == 0) {
            handleEditEventButton();
          } else if (currentType == 1) {
            handleEditTaskButton();
          }
        }
      });
      setSaveQuote();
      setClear();
    } catch (IllegalStateException e) {
      System.err.println("illegal state exception");
    }
  }
}