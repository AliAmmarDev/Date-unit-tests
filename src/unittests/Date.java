package unittests;
import java.util.GregorianCalendar;
import java.util.Calendar;


/**
 * Simple representation of a date.
 */
public class Date {

  private int year;
  private int month;
  private int day;
  private int[] yearArray = {31,28,31,30,31,30,31,31,30,31,30,31};

  /**
   * Creating today's date
   */
  public Date(){
      Calendar today = Calendar.getInstance();
      year = today.get(Calendar.YEAR);
      month = today.get(Calendar.MONTH) + 1;
      day = today.get(Calendar.DAY_OF_MONTH);
  }
  /**
   * Creates a date using the given values for year, month and day.
   * @param y Year
   * @param m Month
   * @param d Day
   *
   * @exception IllegalArgumentException if a year is invald
   * @exception IllegalArgumentException if a month is invalid.
   * @exception IllegalArgumentException if a day is invalid.
   *
   */
  public Date(int y, int m, int d) {
    year = y;
    month = m;
    day = d;

    if(this.isLeapYear()) {
      yearArray[1] += 1;
    }

    if(y < 1){
      throw new IllegalArgumentException("Invalid year");
    }
    if(m < 1 || m > 12){
      throw new IllegalArgumentException("Invalid month");
    }
    if(d < 1 || d > yearArray[m-1]){
      throw new IllegalArgumentException("Invalid day");
    }

  }

  /**
   * Returns the year component of this date.
   *
   * @return Year
   */
  public int getYear() {
    return year;
  }

  /**
   * Returns the month component of this date.
   *
   * @return Month
   */
  public int getMonth() {
    return month;
  }

  /**
   * Returns the day component of this date.
   *
   * @return Day
   */
  public int getDay() {
    return day;
  }

  /**
   * Provides a string representation of this date.
   *
   * ISO 8601 format is used (YYYY-MM-DD).
   *
   * @return Date as a string
   */
  @Override
  public String toString() {
    return String.format("%04d-%02d-%02d", year, month, day);
  }

  /**
   * Tests whether this date is equal to another.
   * @param Object other
   *
   * <p>The two objects are considered equal if both are instances of
   * the Date class <em>and</em> both represent exactly the same
   * date.</p>
   *
   * @return true if this Date object is equal to the other, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      // 'other' is same object as this one!
      return true;
    }
    else if (! (other instanceof Date)) {
      // 'other' is not a Date object
      return false;
    }
    else {
      // Compare fields
      Date otherDate = (Date) other;
      return getYear() == otherDate.getYear()
              && getMonth() == otherDate.getMonth()
              && getDay() == otherDate.getDay();
    }
  }
  /**
   * Returns the value of the day in the year
   *
   * <p>There are 365 days in a year and 366 days in a leap year</p>
   *
   * @return value of the day in the year as an integer
   */
  public int getDayOfYear(){
    int dateNumber = 0;

    dateNumber += this.getDay();
    //if month comes after january
    if(this.getMonth() > 1) {
      for (int i = 1; i < this.getMonth(); i++) {
        dateNumber += yearArray[i-1];
      }
    }
    return dateNumber;
  }

  /**
   * Checks whether the year is a leap year
   *
   * <p>A year is considered a leap year if it can be evenly divided by 400 or
   *    if it can be evenly divided by 4 <em>and</em> not 100</p>
   *
   * @return true if the year is a leap year, false otherwise
   */
  public Boolean isLeapYear(){
    if(this.getYear() % 400 == 0) {
      return true;
    }
    if(this.getYear() % 4 == 0 && this.getYear() % 100 != 0){
      return true;
    }
    return false;
  }

}
