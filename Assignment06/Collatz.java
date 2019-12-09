// //  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      // //    *  File name     :  Collatz.java
      // //    *  Purpose       :  Provides a class defining methods for the Collatz class
      // //    *  @author       :  Faith A Akosile
      // //    *  Date written  :  2019-12-08
      // //    *  Description   :  This class provides a bunch of methods which may be useful for the Collatz class
      // //    *                   for Homework 6,  Includes the following:
      // //    *
      // //    *  Notes         :  None right now.  I'll add some as they occur.
      // //    *  Warnings      :  None
      // //    *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
      // //    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      // //    *  Revision History
      // //    *  ---------------
      // //    *            Rev      Date     Modified by:  Reason for change/modification
      // //    *           -----  ----------  ------------  -----------------------------------------------------------
      // //    *  @version 1.0.0  2019-12-08  Faith Akosile  Initial writing and release
      // //    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.*; 

public class Collatz {
     private BrobInt CollatzNumber = null; 
        
        private static String one = "1";
        private static String two = "2";
        private static String three = "3";
/**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
        public Collatz(String value){
            CollatzNumber = new BrobInt(value);
        }

        /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  b2  BrobInt to divide this by
   *  @param  b  BrobInt second parameter
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

        public BrobInt longDivision(BrobInt gint, int b)
        {
            int[] c = new int [10000];
            String dividend = gint.toString();
            
            int la;
            int i,k=0,flag=0;
            int temp=1;
            la = dividend.length();
            
            int [] a = new int[la];

            for(i=0;i<la;i++){
                a[i] = dividend.charAt(i) - 48;
            }

            temp = a[0];
    
           
            for(i=1;i<=la;i++){

             if(b<=temp){
                 c[k++] = temp/b;
                 temp = temp % b;

                 if(i<la)
                     temp =temp*10 + a[i];
                 else
                     temp =temp*10;
                 flag=1;

             }
             else{
                 if(i<la)
                     temp =temp*10 + a[i];
                 else
                     temp =temp*10;
                 
                 if(flag==1)
                     c[k++] = 0;
             }
        }

        String strArray = "";
        
        for(int j=0;j<k;j++){
            strArray += String.valueOf(c[j]);
        }

        String ans = strArray;

        BrobInt d = new BrobInt(ans);

        return d;

        }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to test if the value is an even or odd function 
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public void printCollatz() {
            
            BrobInt one_ = new BrobInt(one); 
            BrobInt two_ = new BrobInt(two); 
            BrobInt three_ = new BrobInt(three);

            BrobInt counter = BrobInt.ZERO;
            
            // We simply follow steps 
            // while we do not reach 1 

            while (CollatzNumber.compareTo(one_) > 0){ 

               // System.out.print("\n step = ");
               // System.out.println(counter.toString());
              //  System.out.println(CollatzNumber.toString());                
                String r = CollatzNumber.toString();
                int lastIndx = r.length();
                
                if(lastIndx == 0){
                    break;
                }
                 char msb = r.charAt(lastIndx-1);
                //System.out.println(lastIndx);

                // If n is odd 
                if (msb == '1' || msb == '3' || msb == '5' || msb == '7' || msb == '9') {
                    
                    CollatzNumber = CollatzNumber.multiply(three_);
                    
                    CollatzNumber = CollatzNumber.add(one_);
                    
                }
          
                // If even 
                else {
                    
                    CollatzNumber = longDivision(CollatzNumber, 2); 
                    
                }
                // Print 1 at the end
                
                counter = counter.add(one_);    
                           
            } 
              System.out.println(counter);

        }
 /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
            public static void main(String [] args){
                if(args.length == 1){
                    Collatz obj = new Collatz(args[0]);
                    obj.printCollatz();
                    obj = null;
                }
        } 
}
