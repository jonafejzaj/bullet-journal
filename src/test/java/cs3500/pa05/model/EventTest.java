package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;

/**
 * testing event class
 */
public class EventTest {

  Event event = new Event("meeting", DayWeek.FRIDAY, LocalTime.NOON, 1);

  /**
   * test get name
   */
  @Test
  public void testGetName() {
    assertEquals("meeting", event.getName());
  }

  /**
   * test get day
   */
  @Test
  public void testGetDay() {
    assertEquals(DayWeek.FRIDAY, event.getDay());
  }

  /**
   * test getDescription
   */
  @Test
  public void testGetDescription() {
    event.setDescription("Zoom meeting with advisor.");

    String expected = "Zoom meeting with advisor.";

    assertEquals(expected, event.getDescription());
  }

  /**
   * test getDuration
   */
  @Test
  public void testGetDuration() {

    assertEquals(1, event.getDuration());
  }

  /**
   * test getStartTime
   */
  @Test
  public void testGetStartTime() {

    assertEquals(LocalTime.NOON, event.getStartTime());
  }

  /**
   * test setDescription
   */
  @Test
  public void testSetDesc() {
    event.setDescription("Zoom meeting with advisor.");

    assertEquals("Zoom meeting with advisor.", event.getDescription());
  }

  /**
   * test setDescription
   */
  @Test
  public void testSetCategory() {
    event.setCategory(Category.IMPORTANT);

    assertEquals(Category.IMPORTANT, event.getCategory());
  }

  /**
   * assign category work
   */
  @Test
  public void testAssignCategory() {
    Event event2 = new Event("#WORK meeting", DayWeek.FRIDAY, LocalTime.NOON, 1);

    event2.assignCategory();

    assertEquals("meeting", event2.getName());
    assertEquals(Category.WORK, event2.getCategory());
  }

  /**
   * assign category school
   */
  @Test
  public void testAssignCategorySchool() {
    Event eventSchool = new Event("#SCHOOL study",
        DayWeek.FRIDAY, LocalTime.NOON, 1);

    eventSchool.assignCategory();

    assertEquals("study", eventSchool.getName());
    assertEquals(Category.SCHOOL, eventSchool.getCategory());
  }

  /**
   * assign category social
   */
  @Test
  public void testAssignCategorySocial() {
    Event eventStudy = new Event("#SOCIAL lunch",
        DayWeek.FRIDAY, LocalTime.NOON, 1);

    eventStudy.assignCategory();

    assertEquals("lunch", eventStudy.getName());
    assertEquals(Category.SOCIAL, eventStudy.getCategory());
  }

  /**
   * assign category personal
   */
  @Test
  public void testAssignCategoryPersonal() {
    Event eventPersonal = new Event("#PERSONAL doctor appointment",
        DayWeek.FRIDAY, LocalTime.NOON, 1);

    eventPersonal.assignCategory();

    assertEquals("doctor appointment", eventPersonal.getName());
    assertEquals(Category.PERSONAL, eventPersonal.getCategory());
  }

  /**
   * assign category important
   */
  @Test
  public void testAssignCategoryImportant() {
    Event eventImportant = new Event("#IMPORTANT interview",
        DayWeek.FRIDAY, LocalTime.NOON, 1);

    eventImportant.assignCategory();

    assertEquals("interview", eventImportant.getName());
    assertEquals(Category.IMPORTANT, eventImportant.getCategory());
  }


  /**
   * test print
   */
  @Test
  public void testPrint() {
    String expected = """
        Event Name: meeting
        Time: 12:00
        Duration: 1""";
    assertEquals(expected, event.print());
  }

  /**
   * test print
   */
  @Test
  public void testPrintDescCategory() {
    event.setCategory(Category.SCHOOL);
    event.setDescription("Zoom meeting with advisor.");

    String expected = """
        Event Name: meeting
        Description: Zoom meeting with advisor.
        Category: School
        Time: 12:00
        Duration: 1""";
    assertEquals(expected, event.print());
  }

  /**
   * test print
   */
  @Test
  public void testPrintAssignCategory() {
    Event event3 = new Event("#IMPORTANT meeting", DayWeek.FRIDAY, LocalTime.NOON, 1);

    String expected = """
        Event Name: meeting
        Category: Important
        Time: 12:00
        Duration: 1""";
    assertEquals(expected, event3.print());
  }
}