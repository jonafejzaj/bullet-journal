package cs3500.pa05.controller;

/**
 * Controller interface for running event handlers
 */
public interface Controller {
  /**
   * void method that calls event handlers when needed
   *
   * @throws IllegalStateException if illegal state occurs
   */
  void run() throws IllegalStateException;
}
