package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * testing task class
 */
public class TaskTest {
  Task task = new Task("study", DayWeek.MONDAY);

  /**
   * test get name
   */
  @Test
  public void testGetName() {
    assertEquals("study", task.getName());
  }

  /**
   * test setting description
   */
  @Test
  void testSetDescription() {
    task.setDescription("Go over Lectures 1 and 2");

    assertEquals("Go over Lectures 1 and 2", task.getDescription());
  }

  /**
   * test setting the category
   */
  @Test
  void testSetCategory() {
    task.setCategory(Category.SCHOOL);

    assertEquals(Category.SCHOOL, task.getCategory());
  }

  /**
   * test get completed
   */
  @Test
  void testGetCompleted() {
    task.setCompleted(true);

    assertTrue(task.getCompleted());
  }

  /**
   * assign category school
   */
  @Test
  public void testAssignCategorySchool() {
    Task taskSchool = new Task("#SCHOOL hw", DayWeek.FRIDAY);

    taskSchool.assignCategory();

    assertEquals("hw", taskSchool.getName());
    assertEquals(Category.SCHOOL, taskSchool.getCategory());
  }

  /**
   * assign category important
   */
  @Test
  public void testAssignCategoryImportant() {
    Task taskImportant = new Task("#IMPORTANT interview", DayWeek.FRIDAY);

    taskImportant.assignCategory();

    assertEquals("interview", taskImportant.getName());
    assertEquals(Category.IMPORTANT, taskImportant.getCategory());
  }

  /**
   * assign category work
   */
  @Test
  public void testAssignCategoryWork() {
    Task taskWork = new Task("#WORK meeting", DayWeek.FRIDAY);

    taskWork.assignCategory();

    assertEquals("meeting", taskWork.getName());
    assertEquals(Category.WORK, taskWork.getCategory());
  }

  /**
   * assign category social
   */
  @Test
  public void testAssignCategorySocial() {
    Task taskSocial = new Task("#SOCIAL activity", DayWeek.FRIDAY);

    taskSocial.assignCategory();

    assertEquals("activity", taskSocial.getName());
    assertEquals(Category.SOCIAL, taskSocial.getCategory());
  }

  /**
   * assign category personal
   */
  @Test
  public void testAssignCategoryPersonal() {
    Task taskPersonal = new Task("#PERSONAL hobby", DayWeek.FRIDAY);

    taskPersonal.assignCategory();

    assertEquals("hobby", taskPersonal.getName());
    assertEquals(Category.PERSONAL, taskPersonal.getCategory());
  }

  /**
   * test print
   */
  @Test
  void testPrint() {
    task.setDescription("review arrays");
    task.setCompleted(true);
    String expected = """
        Task Name: study
        Description: review arrays
        Task Completed""";
    assertEquals(expected, task.print());
  }

  /**
   * test assign category school
   */
  @Test
  void testPrintAssignCategory() {
    Task task1 = new Task("#SCHOOL study", DayWeek.MONDAY);
    task1.setCompleted(true);

    String expected = """
        Task Name: study
        Category: School
        Task Completed""";
    assertEquals(expected, task1.print());
  }

  /**
   * test print
   */
  @Test
  void testPrintCategory() {
    String noDescription = "Task Name: study\n"
        + "Task Not Completed";
    assertEquals(noDescription, task.print());

    task.setDescription("review arrays");
    task.setCompleted(true);
    task.setCategory(Category.SOCIAL);
    String expected = """
        Task Name: study
        Description: review arrays
        Category: Social
        Task Completed""";
    assertEquals(expected, task.print());

    task.setCompleted(false);
    assertFalse(task.getCompleted());

    String expectedUpdate = """
        Task Name: study
        Description: review arrays
        Category: Social
        Task Not Completed""";
    assertEquals(expectedUpdate, task.print());
  }
}