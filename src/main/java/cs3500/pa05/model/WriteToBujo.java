package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.BujoJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * class for writing to bujo file
 */
public class WriteToBujo {
  private String path;

  /**
   * add bujo extension to end of path
   *
   * @param path of file
   */
  public WriteToBujo(String path) {
    this.path = path + ".bujo";
  }

  /**
   * writes to bujo file using json records and serialization
   *
   * @param maxEvent number of max events user entered
   * @param maxTasks number of max tasks user entered
   * @param weekTitle user set
   * @param quote user added
   * @param category event category
   * @param events list of events
   * @param tasks list of tasks
   */
  public void writeToFile(int maxEvent, int maxTasks, String weekTitle, String quote,
                          String category, List<Event> events, List<Task> tasks) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

    ArrayList<EventJson> eventJsons = new ArrayList<>();
    for (Event e : events) {
      String eventCategory = "";
      if (e.getCategory() != null) {
        eventCategory = e.getCategory().toString();
      }
      EventJson newEventJson = new EventJson(e.getName(), e.getDescription(),
          e.getDay().toString(), e.getStartTime().format(formatter),
          e.getDuration(), eventCategory);
      eventJsons.add(newEventJson);
    }
    EventJson[] eventJsonArgs = new EventJson[eventJsons.size()];
    eventJsonArgs = eventJsons.toArray(eventJsonArgs);

    ArrayList<TaskJson> taskJsons = new ArrayList<>();
    for (Task t : tasks) {
      String taskCategory = "";
      if (t.getCategory() != null) {
        taskCategory = t.getCategory().toString();
      }
      TaskJson newTaskJson = new TaskJson(t.getName(), t.getDescription(),
          t.getDay().toString(), t.getCompleted(), taskCategory);
      taskJsons.add(newTaskJson);
    }
    TaskJson[] taskJsonArgs = new TaskJson[taskJsons.size()];
    taskJsonArgs = taskJsons.toArray(taskJsonArgs);

    BujoJson bujoJson = new BujoJson(maxEvent, maxTasks, weekTitle, quote, category, eventJsonArgs,
        taskJsonArgs);
    JsonNode outputBujo = JsonUtils.serializeRecord(bujoJson);

    JsonUtils.outputJsonNode(this.path, outputBujo);
  }
}