package cs3500.pa05.controller.event;

import cs3500.pa05.model.Event;

/**
 * journal editing interface
 */
public interface JournalEditEvent {
  /**
   * update event
   *
   * @param oldEvent old event
   * @param newEvent new event
   */
  void updateEvent(Event oldEvent, Event newEvent);
}
