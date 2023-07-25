package cs3500.pa05.controller.task;

import cs3500.pa05.model.Task;

/**
 * journal edit task interface
 */
public interface JournalEditTask {
  /**
   * updates old taks with new task
   *
   * @param oldTask prior task
   * @param newTask new task
   */
  void updateTask(Task oldTask, Task newTask);
}
