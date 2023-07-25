package cs3500.pa05.controller.set;

/**
 * Update interface
 */
public interface JournalSetUpdate {
  /**
   * updates old week label
   *
   * @param text new week label
   */
  void updateWeekLabel(String text);

  /**
   * updates old max event
   *
   * @param max new number of max events
   */
  void updateEventSize(String max);

  /**
   * updates old task size
   *
   * @param max new number of max tasks
   */
  void updateTaskSize(String max);

  /**
   * updates old category
   *
   * @param category new category
   */
  void updateWeekCategory(String category);
}
