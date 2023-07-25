package cs3500.pa05.model;

/**
 * Represents a Day of the week
 */
public enum DayWeek {
  /**
   * sunday
   */
  SUNDAY("Sunday"),
  /**
   * monday
   */
  MONDAY("Monday"),
  /**
   * tuesday
   */
  TUESDAY("Tuesday"),
  /**
   * wednesday
   */
  WEDNESDAY("Wednesday"),
  /**
   * thursday
   */
  THURSDAY("Thursday"),
  /**
   * friday
   */
  FRIDAY("Friday"),
  /**
   * saturday
   */
  SATURDAY("Saturday");

  private final String day;

  DayWeek(String day) {
    this.day = day;
  }


  /**
   * Getter for day
   *
   * @return the String representation of the day of the week
   */
  public String getDay() {
    return day;
  }
}
