        // //  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      // //    *  File name     :  Ball.java
      // //    *  Purpose       :  Provides a class defining methods for the Ball class
      // //    *  @author       :  Faith A Akosile
      // //    *  Date written  :  2019-11-04
      // //    *  Description   :  This class provides a bunch of methods which may be useful for the Ball class
      // //    *                   for Homework 4,  Includes the following:
      // //    *
      // //    *  Notes         :  None right now.  I'll add some as they occur.
      // //    *  Warnings      :  None
      // //    *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
      // //    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      // //    *  Revision History
      // //    *  ---------------
      // //    *            Rev      Date     Modified by:  Reason for change/modification
      // //    *           -----  ----------  ------------  -----------------------------------------------------------
      // //    *  @version 1.0.0  2019-11-04  Faith Akosile  Initial writing and release
      // //    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
      import java.text.DecimalFormat;

      public class Ball {
      public static final double RADIUS_IN_INCHES = 4.45;
      private static final double WEIGHT_IN_POUNDS = 1;
      private static final double FRICTION = 0.01;
      public static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0; 
      public static final double FINISHED_X_VELO = 1/12;
      public static final double FINISHED_Y_VELO = 1/12;
      public double xball = 0;
      public double yball = 0;
      public double xballVelo = 0;
      public double yballVelo = 0;
      private double timeSlice = 0;
      /**
      * Constructor goes here
      */
      public Ball (double xLocation, double yLocation, double xVelo, double yVelo, double timeSlice){
      xball = xLocation;
      yball = yLocation; 
      xballVelo = xVelo; 
      yballVelo = yVelo; 
      this.timeSlice = timeSlice;
        if(timeSlice <= 0 || timeSlice > 1799){
      System.out.println( " Sorry you must enter a positive non-zero time slice less then 1800\n");
      System.exit(1); 
        }
      }

      public double getXLocation () {
         return xball;
       }

      public double getYLocation() {
         return yball; 
      }

      public double getXVelocity (){
         return xballVelo; 
      }

      public double getYVelocity() {
         return yballVelo; 
      }

      public boolean still() {
          return Math.abs(xballVelo) <= .083 && Math.abs(yballVelo) <= .083;
      }

      public Ball (String args[]) { if (args.length % 4 == 1) {
         timeSlice = Double.parseDouble(args[args.length-1]); 
      }
         else if (args.length % 4 == 0) {
          timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
       }
         else {
            throw new IllegalArgumentException(); 
        }
      }

      public void moveBall() {
      System.out.println("timeSlice =" + timeSlice);
            xball += (xballVelo);
            yball += (yballVelo);
            xballVelo = xballVelo - ((xballVelo * FRICTION) * timeSlice); 
            yballVelo = yballVelo - ((yballVelo * FRICTION) * timeSlice); 
        if ((Math.abs(xballVelo) * 10) <= 1){
             xballVelo = 0; 
          }
         if ((Math.abs(yballVelo) * 10) <= 1) {
            yballVelo = 0; 
       }
      }

      public String toString() {
       DecimalFormat df = new DecimalFormat("#.##");
         return "Position: " + "[" + df.format(xball) + ", " + df.format(yball) + "]" + " Velocity: " + "[" + df.format(xballVelo) + ", " + df.format(yballVelo) +"]"; 
      }


      public static void main(String args[]) {
      System.out.println( "\nBALL CLASS TESTER PROGRAM\n"); 
      System.out.println( " Creating a new ball... " );
      Ball ball = new Ball(10.0,50.0,2.0,6.0,1.0);  
      System.out.println( " New ball created: " + ball.toString()); ball.moveBall();

       System.out.println("Current: " + ball.toString());
      try { System.out.println( (12.0 == ball.xball) ? " move() for X-val working as intended" : " move() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );
       }
      try { System.out.println( (6.0 == ball.yball) ? " move() for Y-val working as intended" : " move() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
      try { System.out.println( (1.98 == ball.xballVelo) ? " move() for X-Velocity working as intended" : " move() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
      try { System.out.println( (5.94 == ball.yballVelo) ? " move() for Y-Velocity working as intended" : " move() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
      try { System.out.println( (1.0 == ball.timeSlice) ? " move() for timeSlice working as intended" : " move() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );
       } 
         try { System.out.println( (3.0 == ball.timeSlice) ? " move() for timeSlice working as intended" : " move() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );
       } 
         
         try { System.out.println( (5.0 == ball.xball) ? " move() for timeSlice working as intended" : " move() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );
       } 
         try { System.out.println( (9.0 == ball.timeSlice) ? " move() for timeSlice working as intended" : " move() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );
       } 
         try { System.out.println( (10.0 == ball.timeSlice) ? " move() for timeSlice working as intended" : " move() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );
       } 
         try { System.out.println( (12.0 == ball.timeSlice) ? " move() for timeSlice working as intended" : " move() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );
       } 

       System.out.println( "\nSTILL TESTER\n"); 
      System.out.println( " Creating a STILL TEST ... " );
      System.out.println( " STILL: " + ball.still());

    System.out.println("Current: " + ball.still());
    try { System.out.println( (14.0 == ball.xballVelo) ? " still() for X-val working as intended" : " still() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (12.0 == ball.xballVelo) ? " still() for X-val working as intended" : " still() is not working" ); 
   }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
   try { System.out.println( (5.94 == ball.yballVelo) ? " still() for Y-Velocity working as intended" : " still() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
   try { System.out.println( (6.0 == ball.yballVelo) ? " still() for Y-Velocity working as intended" : " still() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
   try { System.out.println( (10.0 == ball.xball) ? " still() for X-val working as intended" : " still() is not working" ); 
   }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );
     }
    try { System.out.println( (1.98 == ball.xballVelo) ? " still() for X-val working as intended" : " still() is not working" ); 
   }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );
     }

    try { System.out.println( (50.0 == ball.yball) ? " still() for Y-ball working as intended" : " still() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (2.94 == ball.yballVelo) ? " still() for Y-Velocity working as intended" : " still() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }

   try { System.out.println( (1 == ball.timeSlice) ? " still() for timeSlice working as intended" : " still() not working" ); 
   }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }

   try { System.out.println( (12.0 == ball.xball) ? " still() for X-val working as intended" : " still() is not working" ); 
   }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() );
     }

      System.out.println( "\nGET X VELOCITY R\n"); 
      System.out.println( " Creating a X VELOCITY TEST ... " );
       ball = new Ball(12.0,30.0,12.0,7.0,1.0);

      System.out.println( " VELOCITY: " + ball.getXVelocity());
       
     System.out.println("Current: " + ball.getXVelocity());
    try { System.out.println( (12.0 == ball.xballVelo) ? " getXVelocity() for X-val working as intended" : " getXVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
   try { System.out.println( (11.88 == ball.xballVelo) ? " getXVelocity() for X-val working as intended" : " getXVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (7.0 == ball.yballVelo) ? " getXVelocity() for Y-val working as intended" : " getXVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (12.0 == ball.xball) ? " getXVelocity() for X-ball working as intended" : " getXVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }

    try { System.out.println( (1.0 == ball.xballVelo) ? " getXVelocity() for X-val working as intended" : " getXVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
   try { System.out.println( (30.0 == ball.yball) ? " getXVelocity() for Y-ball working as intended" : " getXVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (3.0 == ball.yball) ? " getXVelocity() for Y-ball working as intended" : " getXVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (8.0 == ball.xballVelo) ? " getXVelocity() for X-val working as intended" : " getXVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (1.0 == ball.timeSlice) ? " getXVelocity() for timeSlice working as intended" : " getXVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (14.0 == ball.yballVelo) ? " getXVelocity() for X-val working as intended" : " getXVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }


    System.out.println( "\nGET Y VELOCITY R\n"); 
      System.out.println( " Creating a Y VELOCITY TEST ... " );
       ball = new Ball(9.0,3.0,15.0,19.0,2.0);

      System.out.println( " VELOCITY: " + ball.getYVelocity());
       
     System.out.println("Current: " + ball.getYVelocity());
    try { System.out.println( (15.0 == ball.xballVelo) ? " getYVelocity() for X-val working as intended" : " getYVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
   try { System.out.println( (11.88 == ball.xballVelo) ? " getYVelocity() for X-val working as intended" : " getYVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (19.0 == ball.yballVelo) ? " getYVelocity() for Y-val working as intended" : " getYVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (9.0 == ball.xball) ? " getYVelocity() for X-ball working as intended" : " getYVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }

    try { System.out.println( (1.0 == ball.xballVelo) ? " getYVelocity() for X-val working as intended" : " getYVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
   try { System.out.println( (3.0 == ball.yball) ? " getYVelocity() for Y-ball working as intended" : " getYVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (3.0 == ball.yball) ? " getYVelocity() for Y-ball working as intended" : " getYVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (8.0 == ball.xballVelo) ? " getYVelocity() for X-val working as intended" : " getYVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (2.0 == ball.timeSlice) ? " getYVelocity() for timeSlice working as intended" : " getYVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (14.0 == ball.yballVelo) ? " getYVelocity() for X-val working as intended" : " getYVelocity() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }

    System.out.println( "\nGET X LOCATION R\n"); 
      System.out.println( " Creating a X LOCATION TEST ... " );
       ball = new Ball(11.0,8.0,13.0,22.0,1.0);

      System.out.println( " LOCATION: " + ball.getXLocation());
       
     System.out.println("Current: " + ball.getXLocation());
    try { System.out.println( (13.0 == ball.xballVelo) ? " getXLocation() for X-val working as intended" : " getXLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
   try { System.out.println( (11.88 == ball.xballVelo) ? " getXLocation() for X-val working as intended" : "getXLocation()) is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (22.0 == ball.yballVelo) ? " getXLocationX() for Y-val working as intended" : " getXLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (11.0 == ball.xball) ? " getXLocation() for X-ball working as intended" : "getXLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }

    try { System.out.println( (1.0 == ball.xballVelo) ? " getXLocation() for X-val working as intended" : " getXLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
   try { System.out.println( (8.0 == ball.yball) ? " getXLocation() for Y-ball working as intended" : " getXLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (3.0 == ball.yball) ? " getXLocation() for Y-ball working as intended" : " getXLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (8.0 == ball.xballVelo) ? " getXLocation() for X-val working as intended" : " getXLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (1.0 == ball.timeSlice) ? " getXLocation() for timeSlice working as intended" : " getXLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (14.0 == ball.yballVelo) ? " getXLocation() for X-val working as intended" : " getXLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }


   System.out.println( "\nGET Y LOCATION R\n"); 
      System.out.println( " Creating a Y LOCATION TEST ... " );
       ball = new Ball(11.0,8.0,13.0,22.0,1.0);

      System.out.println( " LOCATION: " + ball.getYLocation());
       
     System.out.println("Current: " + ball.getYLocation());
    try { System.out.println( (13.0 == ball.xballVelo) ? " getYLocation() for X-val working as intended" : "getYLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
   try { System.out.println( (11.88 == ball.xballVelo) ? " getYLocation() for X-val working as intended" : " getYLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (22.0 == ball.yballVelo) ? " getYLocationX() for Y-val working as intended" : " getYLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (11.0 == ball.xball) ? " getYLocation() for X-ball working as intended" : " getYLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }

    try { System.out.println( (1.0 == ball.xballVelo) ? " getYLocation() for X-val working as intended" : " getYLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
   try { System.out.println( (8.0 == ball.yball) ? " getYLocation() for Y-ball working as intended" : " getYLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (3.0 == ball.yball) ? " getYLocation() for Y-ball working as intended" : "getYLocation() is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (8.0 == ball.xballVelo) ? " getYLocation() for X-val working as intended" : " getYLocation is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
     try { System.out.println( (1.0 == ball.timeSlice) ? " getYLocation() for timeSlice working as intended" : " getYLocation is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }
    try { System.out.println( (14.0 == ball.yballVelo) ? " getYLocation() for X-val working as intended" : " getYLocation is not working" );
     }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); 
   }

  

   }
         


      }

