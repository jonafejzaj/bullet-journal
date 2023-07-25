package cs3500.pa05.model;

/**
 * event and task categories
 */
public enum Category {
  /**
   * work
   */
  WORK("Work"),
  /**
   * school
   */
  SCHOOL("School"),
  /**
   * social
   */
  SOCIAL("Social"),
  /**
   * personal
   */
  PERSONAL("Personal"),
  /**
   * important
   */
  IMPORTANT("Important");

  private final String category;

  Category(String category) {
    this.category = category;
  }

  /**
   * gets category
   *
   * @return category
   */
  public String getCategory() {
    return category;
  }


}
