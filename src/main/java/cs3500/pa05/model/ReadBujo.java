package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.BujoJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * class for reading bujo files
 */
public class ReadBujo {
  private File bujoFile;
  private Scanner fileScan;
  private int maxEvents;
  private int maxTasks;
  private String weekTitle;
  private String quote;
  private String category;
  private List<Event> obtainedEvents;
  private List<Task> obtainedTasks;

  /**
   * initialize empty array list for events and tasks
   * read file using scanner
   *
   * @param file bujo file
   */
  public ReadBujo(File file) {
    this.bujoFile = file;
    obtainedEvents = new ArrayList<>();
    obtainedTasks = new ArrayList<>();
    try {
      this.fileScan = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.err.println("file cannot be read");
    }
  }

  /**
   * reads bujo file
   *
   * @throws IOException if file cannot be read
   */
  public void readFile() throws IOException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
    JsonNode fileContent = JsonUtils.importBujo(bujoFile);
    BujoJson bujo = JsonUtils.getBujoRecord(fileContent);
    maxEvents = bujo.maxEvents();
    maxTasks = bujo.maxTasks();
    weekTitle = bujo.weekTitle();
    quote = bujo.savedQuote();
    category = bujo.category();

    EventJson[] events = bujo.events();
    for (EventJson e : events) {
      String getName = e.name();
      String getDesc = e.description();
      DayWeek getDay = DayWeek.valueOf(e.day());
      LocalTime getStart = LocalTime.parse(e.time(), formatter);
      int getDuration = e.duration();
      String categoryString = e.category();
      Event tempEvent = new Event(getName, getDay, getStart, getDuration);
      if (!getDesc.equals("")) {
        tempEvent.setDescription(getDesc);
      }
      if (!categoryString.equals("")) {
        Category getCategory = Category.valueOf(categoryString);
        tempEvent.setCategory(getCategory);
      }
      obtainedEvents.add(tempEvent);
    }

    TaskJson[] tasks = bujo.tasks();
    for (TaskJson t : tasks) {
      String getName = t.name();
      String getDesc = t.description();
      DayWeek getDay = DayWeek.valueOf(t.day());
      boolean getComplete = t.complete();
      String categoryString = t.category();
      Task tempTask = new Task(getName, getDay);
      if (!getDesc.equals("")) {
        tempTask.setDescription(getDesc);
      }
      if (!categoryString.equals("")) {
        Category getCategory = Category.valueOf(categoryString);
        tempTask.setCategory(getCategory);
      }
      tempTask.setCompleted(getComplete);
      obtainedTasks.add(tempTask);
    }
  }

  /**
   * get max events
   *
   * @return max events
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * get max tasks
   *
   * @return max tasks
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * get week title
   *
   * @return week title
   */
  public String getWeekTitle() {
    return this.weekTitle;
  }

  /**
   * gets quote
   *
   * @return quote
   */
  public String getQuote() {
    if (quote != null) {
      return quote;
    } else {
      return "";
    }
  }

  /**
   * get events
   *
   * @return list of events
   */
  public List<Event> getObtainedEvents() {
    return this.obtainedEvents;
  }

  /**
   * get tasks
   *
   * @return list of tasks
   */
  public List<Task> getObtainedTasks() {
    return this.obtainedTasks;
  }

  /**
   * gets category
   *
   * @return string representation of a category
   */
  public String getCategory() {
    return Objects.requireNonNullElse(category, "");
  }
}