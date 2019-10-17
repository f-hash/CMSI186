  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  File name     :  Clock.java
   *  Purpose       :  Provides a class defining methods for the ClockSolver class
   *  @author       :  B.J. Johnson
   *  Date written  :  2017-02-28
   *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
   *                   for Homework 4, part 1.  Includes the following:
   *
   *  Notes         :  None right now.  I'll add some as they occur.
   *  Warnings      :  None
   *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Revision Histor
   *  ---------------
   *            Rev      Date     Modified by:  Reason for change/modification
   *           -----  ----------  ------------  -----------------------------------------------------------
   *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

  import java.text.DecimalFormat;

  public class Clock {
    /**
     *  Class field definintions go here
     */
     private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
     private static final double INVALID_ARGUMENT_VALUE = -1.0;
     private static final double MAXIMUM_DEGREE_VALUE = 360.0;
     private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
     private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
     public double targetAngle = 0.0;
     public double totalSeconds = 0;
     private double timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
     private double angle = 0.0;
     // create a new clock 
    /**
     *  Constructor goes here
     */
     public Clock() {
     

     }

    /**
     *  Methods go here
     *
     *  Method to calculate the next tick from the time increment
     *  @return double-precision value of the current clock tick
     */
     public double tick() {
      // a clock is the same and it always starts at twelve
       // how to change the timeSlice if the user put one in
       this.totalSeconds += timeSlice; 
        return this.totalSeconds;
     }

    /**
     *  Method to validate the angle argument
     *  @param   argValue  String from the main programs args[0] input
     *  @return  double-precision value of the argument
     *  @throws  NumberFormatException
     */
     public double validateAngleArg( String argValue ) throws NumberFormatException {
      double targetAngle = 0.0;
       try{ 
        targetAngle = Double.parseDouble(argValue);

     }
       catch(NumberFormatException nfe){
          System.out.println("change the angle");
          System.exit(1);
       }
        if (targetAngle > MAXIMUM_DEGREE_VALUE || targetAngle <= 0.0){
             // throw new NumberFormatException("Please enter an Number less than 360");
            return INVALID_ARGUMENT_VALUE;
             
       }
             return targetAngle;
             
              
      
          
     }

    /**
     *  Method to validate the optional time slice argument
     *  @param  argValue  String from the main programs args[1] input
     *  @return double-precision value of the argument or -1.0 if invalid
     *  note: if the main program determines there IS no optional argument supplied,
     *         I have elected to have it substitute the string "60.0" and call this
     *         method anyhow.  That makes the main program code more uniform, but
     *         this is a DESIGN DECISION, not a requirement!
     *  note: remember that the time slice, if it is small will cause the simulation
     *         to take a VERY LONG TIME to complete!
     */
     public double validateTimeSliceArg( String argValue ) {
      // if ( targetAngle < Math.abs(1800)){
      //   this.timeSlice = targetAngle;
      //   return this.timeSlice; 
      // }
      try{ 
        System.out.println("Time slice changed");
        this.timeSlice = Double.parseDouble(argValue);
        System.out.println("Time slice after " + this.timeSlice);

     }
       catch(NumberFormatException nfe){
          System.out.println("change the timeSlice");
          System.exit(1);
       }
         
         if (timeSlice < 1800.0 && timeSlice > 0.0){
          
          return timeSlice;
         } else {
         return INVALID_ARGUMENT_VALUE;
         }
       }


    /**
     *  Method to calculate and return the current position of the hour hand
     *  @return double-precision value of the hour hand location
     */
     public double getHourHandAngle() {
      return((HOUR_HAND_DEGREES_PER_SECOND * this.totalSeconds) % 360);
      
     }

    /**
     *  Method to calculate and return the current position of the minute hand
     *  @return double-precision value of the minute hand location
     */
     public double getMinuteHandAngle() {
       return((MINUTE_HAND_DEGREES_PER_SECOND * this.totalSeconds) % 360);
       
        }

    /**
     *  Method to calculate and return the angle between the hands
     *  @return double-precision value of the angle between the two hands
     */
     public double getHandAngle() {
         double hAng  = this.getHourHandAngle();
         double mAng = this.getMinuteHandAngle();
         DecimalFormat df = new DecimalFormat("000.000");
         String df1 = df.format(Math.abs(hAng-mAng));;
         double dble = Double.parseDouble(df1);
         return Math.round(dble) % MAXIMUM_DEGREE_VALUE;
     }

    /**
     *  Method to fetch the total number of seconds
     *   we can use this to tell when 12 hours have elapsed
     *  @return double-precision value the total seconds private variable
     */
     public double getTotalSeconds() {
        return this.totalSeconds;
     }

    /**
     *  Method to return a String representation of this clock
     *  @return String value of the current clock
     */
     public String toString() {
        int hour = (int) Math.floor(this.getHandAngle() / 360);
        double secLeft = this.getHandAngle() % 3600;
        int min = (int) Math.floor(secLeft / 60);
        double sec = secLeft % 60;
        DecimalFormat df = new DecimalFormat("00.000");
        StringBuffer time = new StringBuffer();
        time.append(hour).append (":").append(min).append(":").append(df.format(sec));
        return time.toString();

     }

    

    /**
     *  The main program starts here
     *  remember the constraints from the project desSlicecription
     *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
     *  be sure to make LOTS of tests!!
     *  remember you are trying to BREAK your code, not just prove it works!
     */
     public static void main( String args[] ) {

        System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                            "--------------------------\n" );
        System.out.println( "  Creating a new clock: " );
        Clock clock = new Clock();
        System.out.println( "    New clock created: " + clock.toString() );
        System.out.println( "    Testing validateAngleArg()....");
        System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
        try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
        catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

         System.out.print( "      sending '  180 degrees', expecting double value   180.0" );
        try { System.out.println( (180.0 == clock.validateAngleArg( "180.0" )) ? " - got 180.0" : " - no joy" ); }
        catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
         System.out.print( "      sending '  560 degrees', expecting double value  INVALID_ARGUMENT_VALUE" );
        try { System.out.println( ( INVALID_ARGUMENT_VALUE == clock.validateAngleArg( "560.0" )) ? " - got -1" : " - no joy" ); }
        catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
         System.out.print( "      sending '  90 degrees', expecting double value   90" );
        try { System.out.println( ( 90.0 == clock.validateAngleArg( "90.0" )) ? " - got 90" : " - no joy" ); }
        catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }


           System.out.println( "    Testing validateTimeSliceArg()....");
              System.out.print( "      sending '  20 timeSlice', expecting double value   20.0" );
        try { System.out.println( (20.0 == clock.validateTimeSliceArg( "20.0" )) ? " - got 20.0" : " - no joy" ); }
        catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
            System.out.print( "      sending '  80 timeSlice', expecting double value   80.0" );
         try { System.out.println( (80.0 == clock.validateTimeSliceArg( "80.0" )) ? " - got 80.0" : " - no joy" ); }
        catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
             System.out.print( "      sending '  160 timeSlice', expecting double value   160.0" );
        try { System.out.println( (160.0 == clock.validateTimeSliceArg( "160.0" )) ? " - got 160.0" : " - no joy" ); }
        catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
            System.out.print( "      sending '  3600 timeSlice', expecting double value  INVALID_ARGUMENT_VALUE" );
        try { System.out.println( ( INVALID_ARGUMENT_VALUE == clock.validateTimeSliceArg( "3600.0" )) ? " - got -1" : " - no joy" ); }
        catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

     
     }
  }
