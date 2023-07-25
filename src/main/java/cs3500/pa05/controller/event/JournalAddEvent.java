package cs3500.pa05.controller.event;

import cs3500.pa05.model.Event;

/**
 * add event interface
 */
public interface JournalAddEvent {
  /**
   * add new event
   *
   * @param e event
   */
  void addEvent(Event e);
}
