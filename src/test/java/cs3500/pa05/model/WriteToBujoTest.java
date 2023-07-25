package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing write to bujo
 */
public class WriteToBujoTest {
  private WriteToBujo writeToBujo;
  private ReadBujo readBujo;
  private File file;
  private int maxEvent;
  private int maxTasks;
  private String weekTitle;
  private String quote;
  private String category;
  private List<Event> events;
  private List<Task> tasks;

  /**
   * setting up sample bullet journal
   */
  @BeforeEach
  public void setup() {
    file = Paths.get("src/test/resources/testFiles/writeTest.bujo.bujo").toFile();
    maxEvent = 4;
    maxTasks = 3;
    weekTitle = "Week";
    quote = "You can do it!";
    category = "SCHOOL";
    events = new ArrayList<>(Arrays.asList(new Event("meeting", DayWeek.MONDAY,
        LocalTime.NOON, 1), new Event("dinner", DayWeek.FRIDAY,
        LocalTime.of(7, 0, 0), 1)));
    tasks = new ArrayList<>(Arrays.asList(new Task("calc homework", DayWeek.THURSDAY),
        new Task("clean room", DayWeek.TUESDAY)));
    writeToBujo = new WriteToBujo(file.toString());
    readBujo = new ReadBujo(file);
  }

  /**
   * test write to file
   */
  @Test
  public void testWriteToFile() {
    try {
      writeToBujo.writeToFile(maxEvent, maxTasks, weekTitle, quote, category, events, tasks);
      assertTrue(file.exists());

      readBujo.readFile();
      int bujoMaxEvents = readBujo.getMaxEvents();

      assertEquals(maxEvent, bujoMaxEvents);
      assertEquals(weekTitle, readBujo.getWeekTitle());
    } catch (IOException e) {
      System.err.println("invalid file");
    }
  }

  /**
   * test get category
   */
  @Test
  public void testGetCategory() {
    assertNull(events.get(0).getCategory());
    events.get(0).setCategory(Category.SCHOOL);

    assertNotNull(events.get(0).getCategory());

    String category = events.get(0).getCategory().toString();
    assertEquals("SCHOOL", category);
  }
}