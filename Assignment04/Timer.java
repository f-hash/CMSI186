// //  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// //    *  File name     :  Timer.java
// //    *  Purpose       :  Provides a class defining methods for the Ball class
// //    *  @author       :  Faith A Akosile
// //    *  Date written  :  2019-11-04
// //    *  Description   :  This class provides a bunch of methods which may be useful for the Timer class
// //    *                   for Homework 4,  Includes the following:
// //    *
// //    *  Notes         :  This program creates the timer for SoccerSim
// //    *  Warnings      :  None
// //    *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
// //    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// //    *  Revision History
// //    *  ---------------
// //    *            Rev      Date     Modified by:  Reason for change/modification
// //    *           -----  ----------  ------------  -----------------------------------------------------------
// //    *  @version 1.0.0  2019-11-04  Faith Akosile  Initial writing and release
// //    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Timer {
  private static final double DEFAULT_TIME_SLICE_IN_SECONDS= 1.0;
  private static final double inchesInFeet = 12.0;
  private static double totalSeconds = 0;
  private static double timeSlice = 0;



  /**
  *  Constructor goes here
  */
  public Timer () {
  timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
  } 

  public Timer (double
    timeSlice) {
    this.timeSlice = timeSlice;
  }

   // This creates the tick method 
  public double tick() {
    totalSeconds += timeSlice;
      return totalSeconds;
  }
    // This creates the total seconds method 
  public static double getTotalSeconds () {
    return totalSeconds;
  }
     //This method validates the timeSlice
  public double validateTimeSliceArg( String argValue ) throws NumberFormatException {
      double validateTimeSlice = Double.parseDouble(argValue);
      if (validateTimeSlice < 1800 && validateTimeSlice > 0){
        return validateTimeSlice;
      }
      throw new NumberFormatException();
  }
     //This method calculates for the hours
  public static int getHours() {
  int hours = (int)(totalSeconds / 3600);
  return hours;
  }
      //This method calculates for the minutes
  public static int getMins() {
  int remainder = (int) (totalSeconds % 3600);
    int mins = (int) (remainder / 60);
  return mins;
  }
       //This method calculates for the seconds 
  public static double getSecs() {
    int remainder = (int) (totalSeconds % 3600);
  double secs = (double) (remainder % 60.0);
  return secs;
  }
         //This method calculates for the string
  public String toString() {
        int hours = (int)(totalSeconds / 3600);
        int remainder = (int) (totalSeconds % 3600);
        int mins = (int) (remainder / 60);
        double secs = (double) (remainder % 60.0);


        String hh = String.format("%02d", hours);
        String mm = String.format("%02d", mins);
        String zz = String.format("%.03f", secs);
     
      return (hh + ":" + mm + ":" + zz);
  }
            //This method tests for the timer
  public static void main(String[] args) {

      Timer timer = new Timer();

      System.out.println("Testing with a timeSlice of 1 with 1 ticks.");
      timeSlice = 1;
      double numSecs = 0;
      for(int i = 0; i < 1; i++) {
        System.out.println(timer.toString());
        timer.tick();
      }
      System.out.println(timer.getTotalSeconds());
  }
}
