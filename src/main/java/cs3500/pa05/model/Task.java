package cs3500.pa05.model;

import static cs3500.pa05.model.Category.IMPORTANT;
import static cs3500.pa05.model.Category.PERSONAL;
import static cs3500.pa05.model.Category.SCHOOL;
import static cs3500.pa05.model.Category.SOCIAL;
import static cs3500.pa05.model.Category.WORK;

/**
 * Represents a Task
 */
public class Task {
  private String name;
  private String description;
  private final DayWeek day;
  private boolean completed;
  private Category category;


  /**
   * Constructor for Task
   *
   * @param name is a String that represents the name of the Task
   * @param day is a Day enum that represents the Day of the Task
   */
  public Task(String name, DayWeek day) {
    this.name = name;
    this.day = day;
    this.description = "";
    this.completed = false;
  }

  /**
   * Getter for name
   *
   * @return the name of the Task
   */
  public String getName() {
    return this.name;
  }

  /**
   * gets description
   *
   * @return description
   */
  public String getDescription() {
    return description;
  }


  /**
   * Getter for day
   *
   * @return the Day enum that represents the day of the week
   */
  public DayWeek getDay() {
    return this.day;
  }

  /**
   * gets completed task
   *
   * @return true if task is completed or false if not
   */
  public boolean getCompleted() {
    return this.completed;
  }

  /**
   * Sets the description of the Task or Event
   *
   * @param desc is a String that represents the description
   */
  public void setDescription(String desc) {
    this.description = desc;
  }

  /**
   * updates category
   *
   * @param category enum
   */
  public void setCategory(Category category) {
    this.category = category;
  }

  /**
   * updates category
   *
   * @return category
   */
  public Category getCategory() {
    return this.category;
  }

  /**
   * updates completed task
   *
   * @param b given boolean to set task to
   */
  public void setCompleted(boolean b) {
    this.completed = b;
  }

  /**
   * assigns category
   *
   * @return string representation of a category
   */
  public String assignCategory() {
    int startIndex = name.indexOf("#") + 1;
    int endIndex = name.indexOf(" ");
    String category = name.substring(startIndex, endIndex).trim();

    if (category.length() > 0 && exists(category)) {
      setCategory(Category.valueOf(category.toUpperCase()));
      this.name = name.replace("#" + category, "").trim();
    }
    return name;
  }

  /**
   * determine if category exists
   *
   * @param category event category
   *
   * @return true if category exists, false otherwise
   */
  private boolean exists(String category) {
    return category.equalsIgnoreCase(WORK.name())
        || category.equalsIgnoreCase(SCHOOL.name())
        || category.equalsIgnoreCase(SOCIAL.name())
        || category.equalsIgnoreCase(PERSONAL.name())
        || category.equalsIgnoreCase(IMPORTANT.name());
  }

  /**
   * string representation of a task
   *
   * @return a string
   */
  public String print() {
    StringBuilder printString = new StringBuilder();
    if (name.startsWith("#")) {
      assignCategory();
    }
    printString.append("Task Name: ").append(name);
    printString.append(System.lineSeparator());
    if (!this.description.equals("")) {
      printString.append("Description: ").append(description);
      printString.append(System.lineSeparator());
    }
    if (this.category !=  null) {
      printString.append("Category: ").append((this.category).getCategory());
      printString.append(System.lineSeparator());
    }
    if (completed) {
      printString.append("Task Completed");
    } else {
      printString.append("Task Not Completed");
    }

    return printString.toString();
  }
}
