package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing if bujo file can be read properly
 */
class ReadBujoTest {
  private ReadBujo readBujo;
  private File validFile;
  private File borkenFile;

  @BeforeEach
  void setUp() {

    validFile = Paths.get("src/test/resources/testFiles/testFiles.bujo").toFile();
    borkenFile = new File("bork.json");

    readBujo = new ReadBujo(validFile);
  }

  /**
   * test from file
   */
  @Test
  void testReadFile() {

    try {
      readBujo.readFile();

      assertEquals(2, readBujo.getMaxEvents());
      assertEquals(3, readBujo.getMaxTasks());
      assertEquals("Hell Week", readBujo.getWeekTitle());

      List<Event> obtainedEvents = readBujo.getObtainedEvents();
      Assertions.assertEquals(2, obtainedEvents.size());

      List<Task> obtainedTasks = readBujo.getObtainedTasks();
      Assertions.assertEquals(2, obtainedTasks.size());

    } catch (IOException e) {
      Assertions.fail("Exception thrown while reading the valid bujo file");
    }

  }

  /**
   * test get max events
   */
  @Test
  void getMaxEvents() {
    try {
      readBujo.readFile();

      assertEquals(2, readBujo.getMaxEvents());

    } catch (IOException e) {
      Assertions.fail("Exception thrown while reading the valid bujo file");
    }
  }

  /**
   * test get max tasks
   */
  @Test
  void getMaxTasks() {
    try {
      readBujo.readFile();

      assertEquals(3, readBujo.getMaxTasks());

    } catch (IOException e) {
      Assertions.fail("Exception thrown while reading the valid bujo file");
    }
  }

  /**
   * test get week title
   */
  @Test
  void getWeekTitle() {
    try {
      readBujo.readFile();

      assertEquals("Hell Week", readBujo.getWeekTitle());

    } catch (IOException e) {
      Assertions.fail("Exception thrown while reading the valid bujo file");
    }
  }

  /**
   * gets quote
   */
  @Test
  void getQuote() {
    try {
      readBujo.readFile();
      assertEquals(" Important", readBujo.getQuote());
    } catch (IOException e) {
      fail("cannot read file");
    }
  }

  /**
   * test get events
   */
  @Test
  void getObtainedEvents() {
    try {
      readBujo.readFile();

      List<Event> obtainedEvents = readBujo.getObtainedEvents();
      Assertions.assertEquals(2, obtainedEvents.size());

    } catch (IOException e) {
      Assertions.fail("Exception thrown while reading the valid bujo file");
    }
  }

  /**
   * test get obtained task
   */
  @Test
  void getObtainedTasks() {
    try {
      readBujo.readFile();

      List<Task> obtainedTasks = readBujo.getObtainedTasks();
      Assertions.assertEquals(2, obtainedTasks.size());

    } catch (IOException e) {
      Assertions.fail("Exception thrown while reading the valid bujo file");
    }
  }

  /**
   * gets category
   */
  @Test
  void getCategory() {
    try {
      readBujo.readFile();
      assertEquals("", readBujo.getCategory());
    } catch (IOException e) {
      fail("cannot read file");
    }
  }

  /**
   * testing invalid file being read
   */
  @Test
  void readInvalidFile() {
    ReadBujo invalid = new ReadBujo(borkenFile);
    assertThrows(FileNotFoundException.class, invalid::readFile, "file cannot be read");
  }
}