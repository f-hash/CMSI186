import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{

	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static int highScore = 0;


	public static void main(String[] args) {
       int count = 0; 
       int sides = 0;
       String inputLine = "";

        if (2 < args.length || 2 > args.length)  {
        	throw new IllegalArgumentException(" Please enter 2 inputs for number of DIE and number of sides.");

        } 
         try {
         	count = Integer.parseInt(args[0]);
         	sides = Integer.parseInt(args[1]);
         } catch( NumberFormatException nfe) {
         	System.out.println("You must enter two numbers as the argument for number of sides and Die");
               
         }

         DiceSet ds = new DiceSet(count, sides);


         System.out.println("\n Welcome to Highroll! \n");
         System.out.println("This is a dice game, the object is to roll ");
           

           while ( true ){
           	System.out.println("Please Select one of the following: \n" + 
           		"R - Roll all the dice \n" +
           		 "S - ROLL A SINGLE DIE \n" +
           		 "C - CALCULATE THE SCORE FOR THIS SET \n" +
           		 "H - SAVE THIS SCORE AS HIGH SCORE \n " + 
           		 "D - DISPLAY THE HIGH SCORE \n" +
           		  "E - DISPLAY QUIT THE PROGRAM \n");

           	System.out.println("\n Pick one! \n");

           	try {
           		inputLine = input.readLine();
           		if ('E' == inputLine.toUpperCase().charAt(0)){
           			System.out.println( "Thanks for playing")
;           			System.exit(1);
           		} if ('R'== inputLine.toUpperCase().charAt(0)){
                   ds.roll();
           		} if ('S' == inputLine.toUpperCase().charAt(0)){
                   int index = Integer.parseInt(input.readLine());
                   ds.rollIndividual(index - 1);
           		} if ('C' == inputLine.toUpperCase().charAt(0)){
                       int score = ds.sum();
           		} if ( 'H' == inputLine.toUpperCase().charAt(0)){
                       highScore = ds.sum();
                       System.out.println("This is your highScore : " + highScore);
           		} if ( 'D' == inputLine.toUpperCase().charAt(0)){
           			System.out.println("This is your score : " + highScore);
              }
           	} 
           	catch (IOException ioe){
           		System.out.println("Caught IO exception, bad input from user");
           	}


           }

	}
	
}
