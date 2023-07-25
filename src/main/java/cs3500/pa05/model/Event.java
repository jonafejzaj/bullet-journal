package cs3500.pa05.model;

import static cs3500.pa05.model.Category.IMPORTANT;
import static cs3500.pa05.model.Category.PERSONAL;
import static cs3500.pa05.model.Category.SCHOOL;
import static cs3500.pa05.model.Category.SOCIAL;
import static cs3500.pa05.model.Category.WORK;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * event class containing its name, description, day, time and duration
 */
public class Event {
  private String name;
  private String description;
  private final DayWeek day;
  private final LocalTime startTime;
  private final int duration;
  private Category category;

  /**
   * Constructor for Event
   *
   * @param name      is a String that represents the name of the event
   * @param day       is a Day enum that represents the Day of the event
   * @param startTime is a LocalTime that represents the time of the event
   * @param duration  is an int that represents the duration
   */
  public Event(String name, DayWeek day, LocalTime startTime, int duration) {
    this.name = name;
    this.description = "";
    this.day = day;
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * Getter for name
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * description getter
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
   * get time
   *
   * @return time
   */
  public LocalTime getStartTime() {
    return startTime;
  }

  /**
   * get duration
   *
   * @return duration
   */
  public int getDuration() {
    return duration;
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
   * assigns category
   */
  public void assignCategory() {
    int startIndex = name.indexOf("#") + 1;
    int endIndex = name.indexOf(" ");
    String category = name.substring(startIndex, endIndex).trim();

    if (category.length() > 0 && exists(category)) {
      setCategory(Category.valueOf(category.toUpperCase()));
      this.name = name.replace("#" + category, "").trim();
    }
  }

  /**
   * returns category
   *
   * @return category
   */
  public Category getCategory() {
    return this.category;
  }

  /**
   * determine if category exists
   *
   * @param category event category
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
   * Prints the Event to a String
   *
   * @return a String that represents the event
   */
  public String print() {
    StringBuilder printString = new StringBuilder();

    if (name.startsWith("#")) {
      assignCategory();
    }
    printString.append("Event Name: ").append(name);
    printString.append(System.lineSeparator());
    if (!description.equals("")) {
      printString.append("Description: ").append(description);
      printString.append(System.lineSeparator());
    }
    if (this.category != null) {
      printString.append("Category: ").append((this.category).getCategory());
      printString.append(System.lineSeparator());
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
    String formattedStartTime = startTime.format(formatter);
    printString.append("Time: ").append(formattedStartTime);
    printString.append(System.lineSeparator());
    printString.append("Duration: ").append(duration);
    return printString.toString();
  }
}