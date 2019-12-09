import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

   /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt(Integer.valueOf(Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf(Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt(Long.valueOf(Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf(Long.MIN_VALUE ).toString() );


  
  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this BrobInt
   private int   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string 
   
   //private byte[] b   = null;
   private int[] digits = null;
   int length = 0;


  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) { 
      internalValue = value;
       

      //handles the sign character
      if( value.charAt(0) == '-' ) {
      
         sign = 1;
         value = value.substring(1);
      } else if( value.charAt(0) == '+' || value.charAt(0) == ' ' ) {
        sign = 0;
        value = value.substring(1);
      }
    
      this.validateDigits(value);

     
      length = value.length();
      digits = new int[length];

      try {
         for( int i = 0; i < length; i++ ) {
            digits[i] = Character.getNumericValue(value.charAt(( length - 1 ) - i ));
        }
      } catch (NumberFormatException nfe) {
         throw new NumberFormatException("invalid number");
      }

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits(String value) {
        for( int i = 0; i < value.length(); i++ ) {
           char c = value.charAt(i);
           if( c < 48 || c > 57 ) {
              throw new IllegalArgumentException("invalid");
           }
        }
        return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static int[] reverser( int[] b2 ) {
      for (int i = 0; i < b2.length / 2; i++) {
         int temp = b2[i];
         b2[i] = b2[b2.length - i - 1];
         b2[b2.length - i - 1] = temp;
      }
      return b2; 
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return sign
   *  @param none
   *  @return int
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int sign() {
       return sign;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return the correct representation of BrobInt 
   *  @param  b and sign         make the BrobInt 
   *  @return BrobInt that turns a string into a BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   private BrobInt makeBrobIntStr(String b, int sign) {
      StringBuffer buffer = new StringBuffer(b);
      if (sign == 1){
         buffer.insert(0, '-');
      }
      String buffStr = buffer.toString();
      BrobInt brob = new BrobInt(buffStr);
      return brob;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return the correct representation of BrobInt 
   *  @param  b and sign         make the BrobInt 
   *  @return BrobInt that joins the array, reverses the number and returns a BrobInt 
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   private BrobInt makeBrobInt(String[] b, int sign) {
      StringBuffer buffer = new StringBuffer(String.join("", b)); //makes the result array into a string
      if (b.length == 0) {
        throw new IllegalArgumentException("illegal arg");  
      }
      if (sign == 1){
         buffer.append("-");
      }
      buffer = buffer.reverse();

      //getting rid of the zeros in the front
      String buffStr = buffer.toString();
      for (int i = 0, length = buffStr.length() - 1; i < length; i++) {
         if (buffStr.charAt(i) != '0') {
            buffStr = buffStr.substring(i);
            break;
         } 
      }
      BrobInt brob = new BrobInt(buffStr);
      brob = GetRidOfLeadingZeros(brob);
      return brob;
   }

 
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return the correct representation of BrobInt 
   *  @param  none         make the BrobInt 
   *  @return BrobInt that returns a positive BrobInt  
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt makePositiveBrobInt() {
      if (internalValue.indexOf("-") == 0) {
         BrobInt temp = new BrobInt(internalValue.substring(1));
         return temp;
      } else {
        return this;
      }
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return the correct representation of BrobInt 
   *  @param  b and sigb         make the BrobInt 
   *  @return BrobInt that joins the array, and returns a BrobInt 
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   private BrobInt makeBrobIntDivide(String[] b, int sign) {
      StringBuffer buffer = new StringBuffer(String.join("", b)); //makes the result array into a string
      if (b.length == 0) {
        throw new IllegalArgumentException("illegal arg");  
      }
      if (sign == 1){
         buffer.insert(0, '-');
      }

      //getting rid of the zeros in the front
      String buffStr = buffer.toString().trim();
      for (int i = 0, length = buffStr.length() - 1; i < length; i++) {
         if (buffStr.charAt(i) != '0') {
            buffStr = buffStr.substring(i);
            break;
         } 
      }
      BrobInt brob = new BrobInt(buffStr);
      return brob;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return the correct representation of BrobInt 
   *  @param  none         make the BrobInt 
   *  @return BrobInt that returns a negative BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt makeNegativeBrobInt() {
      if (internalValue.indexOf("-") == 0) {
         return this;
      } else {
        BrobInt temp = new BrobInt("-" + internalValue);
        return temp;
      }

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return the correct representation of BrobInt 
   *  @param  b         make the BrobInt 
   *  @return BrobInt with the leading number of zeros gone
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   private BrobInt GetRidOfLeadingZeros(BrobInt b) {
      StringBuffer buffer = new StringBuffer(b.toString()); 
      String buffStr = buffer.toString();
      int toInt = Integer.parseInt(buffStr);
      buffStr = Integer.toString(toInt);
      BrobInt brob = new BrobInt(buffStr);
      return brob;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  b2         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt b2 ) {
      int[] digits2 = b2.toArray();
      int carryOver = 0;
      int sum = 0, d1 = 0, d2 = 0;
      int len1 = digits.length; 
      int len2 = digits2.length;
      int length = Math.max(len2, len1) + 1;
      String[] result = new String[length];
      int sign2 = b2.sign();

      if (sign == sign2) {
         for (int i = 0; i < length; i++) {
             //System.out.println("What is the Sign " + sign);
            // System.out.println("What is Sign 2 " + sign2);
            d1 = i < len1 ? digits[i] : 0;
            d2 = i < len2 ? digits2[i] : 0;
            sum = d1 + d2 + carryOver;
            if (sum > 9) {
               carryOver = 1;
               sum -= 10;
            } else {
                 carryOver = 0;
            }
             //System.out.println("What is the Sign " + sign2);
            result[i] = Integer.toString(sum);
         }
         // System.out.println("What is the Sign " + sign2);
         return makeBrobInt(result, sign);
      } else if (sign == 0 && sign2 == 1) {
           b2 = b2.makePositiveBrobInt();
           return this.subtract(b2);
      } else if (sign == 1 && sign2 == 0) {
           b2 = b2.makeNegativeBrobInt();
           return this.subtract(b2);
      }
           return this;
      
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare BrobInts of abs value
   *  @param  b         BrobInt to compare
   *  @return BrobInt that is compared to another -> positve
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   private int compareAbsValue( BrobInt b ) {
      BrobInt b1 = this.makePositiveBrobInt();
      BrobInt b2 = b.makePositiveBrobInt();
      return b1.compareTo(b2); 
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  b2         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt b2 ) {
      int[] digitsBig;
      int[] digitsSmall;
      int borrow = 0, diff = 0, d1 = 0, d2 = 0;
      int len1, len2;
      String[] result = null;
      int sign2 = b2.sign();
      int resultSign;
      
      if (sign == sign2) {
        if (sign == 0) {
           if (this.compareTo(b2) < 0) {
              digitsBig = b2.toArray();
              digitsSmall = digits;
              resultSign = 1;
           } else {
              digitsBig = digits;
              digitsSmall = b2.toArray();
              resultSign = 0;
           }
        } else {
          if (this.compareAbsValue(b2) < 0) {
              digitsBig = b2.toArray();
              digitsSmall = digits;
              resultSign = 0;
           } else {
              digitsBig = digits;
              digitsSmall = b2.toArray();
              resultSign = 1;
           }
        }
        len1 = digitsBig.length;
        len2 = digitsSmall.length;
        result = new String[len1];

        for (int i = 0; i < len1; i++) {
           d1 = digitsBig[i];
           d2 = i < len2 ? digitsSmall[i] : 0; 
           diff = d1 - d2;
           if ((d1 - borrow) - d2 < 0) {
              diff = (10 + d1 - borrow) - d2;
              borrow = 1;
           } else {
              diff = (d1 - borrow) - d2;
              borrow = 0;    
           }
           result[i] = Integer.toString(diff);
        }
        return makeBrobInt(result, resultSign);
      } else if (sign == 0 && sign2 == 1) {
           b2 = b2.makePositiveBrobInt();
           return this.add(b2);
      } else if (sign == 1 && sign2 == 0) {
           b2 = b2.makeNegativeBrobInt();
           return this.add(b2);
      }
      // System.out.println("What is the Sign " + sign2);
      return this;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  b2 BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt b2 ) {
      int[] digits2 = b2.toArray();
      int[] longer = null;
      int[] shorter = null;
      int carry = 0;
      int k = 0;
      int len1 = digits.length; 
      int len2 = digits2.length;
      int length = Math.max(len2, len1);
      int length1 = Math.min(len2, len1);
      int[] product = new int[len1 + len2 + 1];
      int sign2 = b2.sign();

      if (len1 > len2) {
        longer = digits;
        shorter = digits2; 
      } else if (len1 == len2) {
         if (this.compareTo(b2) < 0) {
            longer = digits2;
            shorter = digits; 
         } else {
            longer = digits;
            shorter = digits2; 
         }
      } else {
         longer = digits2;
         shorter = digits; 
      }

      for (int i = 0; i < length1; i++) {                    
         k = i;
         carry = 0;
         for (int j = 0; j < length; j++) {                  
            product[k] += (longer[j] * shorter[i]) + carry;
            if (product[k] > 9) {
               carry = product[k] / 10;
               if (carry == 0) {
                  carry = 1;
               }
               product[k] = product[k] % 10;
            } else {
               carry = 0;
            }
            k++;
         }
         product[k] = carry;
      }
      if (carry >= 0) {
        product[k] = carry;
      }
      String[] result = new String[k + 1];
      for (int a = 0; a <= k; a++) {
         result[a] = String.valueOf(product[a]);
      }
      if (sign != sign2) {
         sign = 1;
      } else {
        sign = 0;
      }
      return makeBrobInt(result, sign);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  b2         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt dvs ) { 
      BrobInt divisor = dvs.makePositiveBrobInt();           //arg passed in  (d1) 
      BrobInt dividend = this.makePositiveBrobInt();         //this           (d2)
      BrobInt quotient = BrobInt.ZERO; 
      String d3 = ""; 
      String d4 = "";   
      String[] result = new String[this.length];
      int subVal;
      int n;
      int idx = 0;
      int resultSign = 0;
      int[] temp;

      if (sign != dvs.sign()) {
         resultSign = 1;
      }
      if (divisor.toString() == "0" ) {
         throw new IllegalArgumentException("Are you trying to break the code??");
      } else if (dividend.compareTo(divisor) == 0) {
         return BrobInt.ONE;
      } else if (dividend.compareTo(divisor) < 0) {   //d1>d2
         return BrobInt.ZERO;
      } else {
         n = divisor.length;
         d3 = dividend.toString().substring(0, n); //extract that many characters from the front of d2 and put into d3
         d4 = dividend.toString().substring(0, n); 
         if (divisor.compareTo(makeBrobIntStr(d3, 0)) > 0) { //d1>d3
            n += 1;
            d3 = dividend.toString().substring(0, n);
            d4 = dividend.toString().substring(0, n); 
          }
          while (n <= dividend.toString().length()) {
             quotient = BrobInt.ZERO; 
             d3 = d4;
             while (makeBrobIntStr(d3, 0).compareTo(divisor) >= 0) {  //d3>d1  
                String pc = makeBrobIntStr(d3, 0).subtract(divisor).toString();
                int placehold = Integer.valueOf(pc);
                d3 = Integer.toString(placehold);
                quotient = quotient.add(BrobInt.ONE);
             }
             quotient = GetRidOfLeadingZeros(quotient);
             result[idx] = quotient.toString();
             idx++;
             if (n++ == dividend.toString().length()) {
                result = Arrays.copyOfRange(result, 0, idx);
                break;
             }
             d3 = (makeBrobIntStr(d4, 0)).subtract(GetRidOfLeadingZeros(divisor.multiply(quotient))).toString(); 
             quotient.multiply(BrobInt.TEN);   
             d4 = d3.concat(dividend.toString().substring(n - 1, n));//add current value of d3 to extracted digit [e.g., get "7" from d2, concat to d3 to make "197"]
             int toInt = Integer.parseInt(d4);
             d4 = Integer.toString(toInt);
          } 
      }
      return makeBrobIntDivide(result, resultSign);
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  b2         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt b2 ) {
      //remainder = dividend - multiply(dividend.divide(divisor),divisor)
      return GetRidOfLeadingZeros(this.subtract(b2.multiply(this.divide(b2))));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
   *        It takes into account the length of the two numbers, and if that isn't enough it does a
   *        character by character comparison to determine
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {

     // handle the signs here
      if( 1 == sign && 0 == gint.sign ) {
         return -1;
      } else if( 0 == sign && 1 == gint.sign ) {
         return 1;
      }

     // the signs are the same at this point
     // check the length and return the appropriate value
     //   1 means this is longer than gint, hence larger
     //  -1 means gint is longer than this, hence larger
      if( internalValue.length() > gint.internalValue.length() ) {
         return 1;
      } else if( internalValue.length() < gint.internalValue.length() ) {
         return (-1);

     // otherwise, they are the same length, so compare absolute values
      } else {
         for( int i = 0; i < internalValue.length(); i++ ) {
            Character a = Character.valueOf( internalValue.charAt(i) );
            Character b = Character.valueOf( gint.internalValue.charAt(i) );
            if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
               return 1;
            } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
               return (-1);
            }
         }
      }
      return 0;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt(Long.valueOf(value).toString());
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      return internalValue;

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int[] toArray( ) {
      return digits;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
  
     System.out.println( "\n First Tests: testing all the methods, the constructor and the toString() methods ...\n" );
      BrobInt a = new BrobInt("-7");
      BrobInt b = new BrobInt("2");
      System.out.println("\n Test 1 \n");
      System.out.println("Adding " + a.toString() + " + " + b.toString() + " = " + (a.add(b)).toString());
      System.out.println("Subtraction " + a.toString() + " - " + b.toString() + " = " + (a.subtract(b)).toString());
      System.out.println("Dividing " + a.toString() + " / " + b.toString() + " = " + (a.divide(b)).toString());
      System.out.println("Multiply  " + a.toString() + " * " + b.toString() + " = " + (a.multiply(b)).toString());
      System.out.println("The Remainder is " + a.toString() + " / " + b.toString() + " = " + (a.remainder(b)).toString());
    
      a = new BrobInt("7");
      b = new BrobInt("-2");
      System.out.println("\n  Test 2 \n");
      System.out.println("Adding " + a.toString() + " + " + b.toString() + " = " + (a.add(b)).toString());
     System.out.println("Subtraction " + a.toString() + " - " + b.toString() + " = " + (a.subtract(b)).toString());
      System.out.println("Dividing " + a.toString() + " / " + b.toString() + " = " + (a.divide(b)).toString());
      System.out.println("Multiply  " + a.toString() + " * " + b.toString() + " = " + (a.multiply(b)).toString());
      System.out.println("The Remainder is " + a.toString() + " / " + b.toString() + " = " + (a.remainder(b)).toString());

      a = new BrobInt("-7");
      b = new BrobInt("-2");
      System.out.println("\n Test 3 \n");
      System.out.println("Adding " + a.toString() + " + " + b.toString() + " = " + (a.add(b)).toString());
      System.out.println("Subtraction " + a.toString() + " - " + b.toString() + " = " + (a.subtract(b)).toString());
      System.out.println("Dividing " + a.toString() + " / " + b.toString() + " = " + (a.divide(b)).toString());
      System.out.println("Multiply  " + a.toString() + " * " + b.toString() + " = " + (a.multiply(b)).toString());
      System.out.println("The Remainder is " + a.toString() + " / " + b.toString() + " = " + (a.remainder(b)).toString());

      a = new BrobInt("10");
      b = new BrobInt("5");
      System.out.println(" \n  Test 4 \n");
      System.out.println("Adding " + a.toString() + " + " + b.toString() + " = " + (a.add(b)).toString());
      System.out.println("Subtraction " + a.toString() + " - " + b.toString() + " = " + (a.subtract(b)).toString());
      System.out.println("Dividing " + a.toString() + " / " + b.toString() + " = " + (a.divide(b)).toString());
      System.out.println("Multiply  " + a.toString() + " * " + b.toString() + " = " + (a.multiply(b)).toString());
      System.out.println("The Remainder is " + a.toString() + " / " + b.toString() + " = " + (a.remainder(b)).toString());

      a = new BrobInt("100");
      b = new BrobInt("-2");
      System.out.println("\n Test 5 \n");
      System.out.println("Adding " + a.toString() + " + " + b.toString() + " = " + (a.add(b)).toString());
      System.out.println("Subtraction " + a.toString() + " - " + b.toString() + " = " + (a.subtract(b)).toString());
      System.out.println("Dividing " + a.toString() + " / " + b.toString() + " = " + (a.divide(b)).toString());
      System.out.println("Multiply  " + a.toString() + " * " + b.toString() + " = " + (a.multiply(b)).toString());
      System.out.println("The Remainder is " + a.toString() + " / " + b.toString() + " = " + (a.remainder(b)).toString());

      a = new BrobInt("-100");
      b = new BrobInt("-2");
      System.out.println("\n Test 6 \n");
      System.out.println("Adding " + a.toString() + " + " + b.toString() + " = " + (a.add(b)).toString());
      System.out.println("Subtraction " + a.toString() + " - " + b.toString() + " = " + (a.subtract(b)).toString());
      System.out.println("Dividing " + a.toString() + " / " + b.toString() + " = " + (a.divide(b)).toString());
      System.out.println("Multiply  " + a.toString() + " * " + b.toString() + " = " + (a.multiply(b)).toString());
      System.out.println("The Remainder is " + a.toString() + " / " + b.toString() + " = " + (a.remainder(b)).toString());

      a = new BrobInt("20");
      b = new BrobInt("-4");
      System.out.println("\n Test 7 \n");
      System.out.println("Adding " + a.toString() + " + " + b.toString() + " = " + (a.add(b)).toString());
      System.out.println("Subtraction " + a.toString() + " - " + b.toString() + " = " + (a.subtract(b)).toString());
      System.out.println("Dividing " + a.toString() + " / " + b.toString() + " = " + (a.divide(b)).toString());
      System.out.println("Multiply  " + a.toString() + " * " + b.toString() + " = " + (a.multiply(b)).toString());
      System.out.println("The Remainder is " + a.toString() + " / " + b.toString() + " = " + (a.remainder(b)).toString());
    
      a = new BrobInt("-20");
      b = new BrobInt("-4");
      System.out.println("\n Test 8 \n");
      System.out.println("Adding " + a.toString() + " + " + b.toString() + " = " + (a.add(b)).toString());
      System.out.println("Subtraction " + a.toString() + " - " + b.toString() + " = " + (a.subtract(b)).toString());
      System.out.println("Dividing " + a.toString() + " / " + b.toString() + " = " + (a.divide(b)).toString());
      System.out.println("Multiply  " + a.toString() + " * " + b.toString() + " = " + (a.multiply(b)).toString());
      System.out.println("The Remainder is " + a.toString() + " / " + b.toString() + " = " + (a.remainder(b)).toString());

      a = new BrobInt("2");
      b = new BrobInt("5");
      System.out.println("\n Test 9 \n");
      System.out.println("Adding " + a.toString() + " + " + b.toString() + " = " + (a.add(b)).toString());
      System.out.println("Subtraction " + a.toString() + " - " + b.toString() + " = " + (a.subtract(b)).toString());
      System.out.println("Dividing " + a.toString() + " / " + b.toString() + " = " + (a.divide(b)).toString());
      System.out.println("Multiply  " + a.toString() + " * " + b.toString() + " = " + (a.multiply(b)).toString());
      System.out.println("The Remainder is " + a.toString() + " / " + b.toString() + " = " + (a.remainder(b)).toString());

      a = new BrobInt("1000");
      b = new BrobInt("200");
      System.out.println("\n Test 10 \n");
      System.out.println("Adding " + a.toString() + " + " + b.toString() + " = " + (a.add(b)).toString());
      System.out.println("Subtraction " + a.toString() + " - " + b.toString() + " = " + (a.subtract(b)).toString());
      System.out.println("Dividing " + a.toString() + " / " + b.toString() + " = " + (a.divide(b)).toString());
      System.out.println("Multiply  " + a.toString() + " * " + b.toString() + " = " + (a.multiply(b)).toString());
      System.out.println("The Remainder is " + a.toString() + " / " + b.toString() + " = " + (a.remainder(b)).toString());

      a = new BrobInt("-1000");
      b = new BrobInt("200");
      System.out.println("\n Test 11 \n");
      System.out.println("Adding " + a.toString() + " + " + b.toString() + " = " + (a.add(b)).toString());
      System.out.println("Subtraction " + a.toString() + " - " + b.toString() + " = " + (a.subtract(b)).toString());
      System.out.println("Dividing " + a.toString() + " / " + b.toString() + " = " + (a.divide(b)).toString());
      System.out.println("Multiply  " + a.toString() + " * " + b.toString() + " = " + (a.multiply(b)).toString());
      System.out.println("The Remainder is " + a.toString() + " / " + b.toString() + " = " + (a.remainder(b)).toString());

    System.out.println("\n This is the add test \n");
      a = new BrobInt("1");
      b = new BrobInt("2");
      System.out.println(" \n 1 + 2 " + "= " + (a.add(b)));
    
      a = new BrobInt("2");
      b = new BrobInt("3");
      System.out.println(" \n 2 + 3 " + "= " + (a.add(b)));
    
      a = new BrobInt("-3");
      b = new BrobInt("-2");
      System.out.println(" \n -3 + -2 " + "= " + (a.add(b)));

      a = new BrobInt("-2");
      b = new BrobInt("-1");
      System.out.println(" \n -2 + -1 " + "= " + (a.add(b)));
   
      a = new BrobInt("10");
      b = new BrobInt("2");
      System.out.println(" \n 10 + 2 " + "= " + (a.add(b)));

      a = new BrobInt("2000");
      b = new BrobInt("100");
      System.out.println(" \n 2000 + 100 " + "= " + (a.add(b)));
  
     
      a = new BrobInt("1000");
      b = new BrobInt("200");
      System.out.println(" \n 1000 + 200 " + "= " + (a.add(b)));

     
      a = new BrobInt("-1");
      b = new BrobInt("200");
      System.out.println(" \n -1 + 200 " + "= " + (a.add(b)));

    
      a = new BrobInt("-20");
      b = new BrobInt("2");
      System.out.println(" \n -20 + 2 " + "= " + (a.add(b)));

      a = new BrobInt("-46");
      b = new BrobInt("2");
      System.out.println(" \n -46 + 2 " + "= " + (a.add(b)));

    System.out.println("\n This is the subtract test \n");

      a = new BrobInt("3");
      b = new BrobInt("2");
      System.out.println(" \n 3 - 2 " + " =" + (a.subtract(b)));

      a = new BrobInt("2");
      b = new BrobInt("-3");
      System.out.println(" \n 2 - (-3) " + "= " + (a.subtract(b)));
    
      a = new BrobInt("-1");
      b = new BrobInt("-2");
      System.out.println(" \n -1 - (-2) " + "= " + (a.subtract(b)));

      a = new BrobInt("10");
      b = new BrobInt("-2");
      System.out.println(" \n 10 - (-2) " + "= " + (a.subtract(b)));

      a = new BrobInt("2000");
      b = new BrobInt("100");
      System.out.println(" \n 2000 - 100" + "= " + (a.subtract(b)));
  
      a = new BrobInt("200");
      b = new BrobInt("1000");
      System.out.println(" \n 200 - 1000" + "= " + (a.subtract(b)));

      a = new BrobInt("200");
      b = new BrobInt("-1");
      System.out.println(" \n 200 - (-1) " + "= " + (a.subtract(b)));
  
      a = new BrobInt("-20");
      b = new BrobInt("2");
      System.out.println(" \n -20 - 2 " + "= " + (a.subtract(b)));

      a = new BrobInt("-46");
      b = new BrobInt("2");
      System.out.println(" \n -46 - 2 " + "= " + (a.subtract(b)));
 
      a = new BrobInt("-16");
      b = new BrobInt("-2");
      System.out.println(" \n -16 - (-2) " + "= " + (a.subtract(b)));

      System.out.println("\n This is the multiply test \n");
    
      a = new BrobInt("2");
      b = new BrobInt("3");
      System.out.println("\n 2 * 3 " + "= " + (a.multiply(b)));

      a = new BrobInt("-2");
      b = new BrobInt("3");     
      System.out.println("\n -2 * 3 " + "= " + (a.multiply(b)));
    
      a = new BrobInt("-1");
      b = new BrobInt("-2");
      System.out.println("\n -1 * -2 " + "= " + (a.multiply(b)));

      a = new BrobInt("2");
      b = new BrobInt("10");
      System.out.println("\n 2 * 10 " + "= " + (a.multiply(b)));

      a = new BrobInt("100");
      b = new BrobInt("2000");
      System.out.println("\n 100 * 2000 " + "= " + (a.multiply(b)));

      a = new BrobInt("1000");
      b = new BrobInt("200");
      System.out.println("\n 1000 * 200 " + "= " + (a.multiply(b)));

      a = new BrobInt("-1");
      b = new BrobInt("200");
      System.out.println("\n -1 * 200" + "= " + (a.multiply(b)));

      a = new BrobInt("-20");
      b = new BrobInt("2");
      System.out.println("\n -20 * 2" + "= " + (a.multiply(b)));
  
      a = new BrobInt("-46");
      b = new BrobInt("2");
      System.out.println("\n -46 * 2 " + "= " + (a.multiply(b)));
   
      a = new BrobInt("16");
      b = new BrobInt("2");
      System.out.println("\n 16 * 2 " + "= " + (a.multiply(b)));
   
  System.out.println("\n This is the Divide test \n");
      
      a = new BrobInt("3");
      b = new BrobInt("5");
      System.out.println("\n 3 / 5 " + "= " + (a.divide(b)));

      a = new BrobInt("-3");
      b = new BrobInt("8");
      System.out.println("\n -3 / 8 " + "= " + (a.divide(b)));

      a = new BrobInt("-10");
      b = new BrobInt("-2");
      System.out.println("\n -10 / -2 " + "= " + (a.divide(b)));
  
      a = new BrobInt("20");
      b = new BrobInt("10");
     System.out.println("\n 20 / 10 " + "= " + (a.divide(b)));
   
      a = new BrobInt("10000");
      b = new BrobInt("2000");
     System.out.println("\n 10000 / 2000 " + "= " + (a.divide(b)));
  
      a = new BrobInt("1000");
      b = new BrobInt("200");
      System.out.println("\n 1000 / 200 " + "= " + (a.divide(b)));

      a = new BrobInt("-6000");
      b = new BrobInt("200");
      System.out.println("\n -6000 / 200 " + "= " + (a.divide(b)));
     
      a = new BrobInt("-20");
      b = new BrobInt("2");
       System.out.println("\n -20 / 2 " + "= " + (a.divide(b)));

      a = new BrobInt("-46");
      b = new BrobInt("-2");
      System.out.println("\n -46 / -2 " + "= " + (a.divide(b)));

      a = new BrobInt("16");
      b = new BrobInt("2");
      System.out.println("\n 16 / 2 " + "= " + (a.divide(b)));

  System.out.println("\n This is the a Remainder test \n");
      
      a = new BrobInt("2");
      b = new BrobInt("3");
     System.out.println("\n 2 % 3 " + "= " + (a.remainder(b)));

      a = new BrobInt("-2");
      b = new BrobInt("3");
      System.out.println("\n -2 % 3 " + "= " + (a.remainder(b)));
 
      a = new BrobInt("1");
      b = new BrobInt("-2");
      System.out.println("\n 1 % -2 " + "= " + (a.remainder(b)));

      a = new BrobInt("10");
      b = new BrobInt("2");
      System.out.println("\n 10 % 2 " + "= " + (a.remainder(b)));

      a = new BrobInt("2000");
      b = new BrobInt("100");
      System.out.println("\n 2000 % 100 " + "= " + (a.remainder(b)));
     
      a = new BrobInt("1000");
      b = new BrobInt("200");
      System.out.println("\n 1000 % 200 " + "= " + (a.remainder(b)));
    
      a = new BrobInt("-1");
      b = new BrobInt("200");
      System.out.println("\n -1 % 200 " + "= " + (a.remainder(b)));

      a = new BrobInt("-20");
      b = new BrobInt("2");
      System.out.println("\n -20 % 2 " + "= " + (a.remainder(b)));
     
      a = new BrobInt("-46");
      b = new BrobInt("2");
      System.out.println("\n -46 % 2 " + "= " + (a.remainder(b)));

      a = new BrobInt("16");
      b = new BrobInt("2");
      System.out.println("\n 16 % 2 " + "= " + (a.remainder(b)));


  System.out.println("\n This is the a compareTo test \n");
   
      a = new BrobInt("2");
      b = new BrobInt("3");
      System.out.println("\n 2 compared to 3  " + "= " + (a.compareTo(b)));

      a = new BrobInt("-1");
      b = new BrobInt("-1");
      System.out.println("\n -1 compares to -1 " + "= " + (a.compareTo(b)));
  
      a = new BrobInt("-1");
      b = new BrobInt("-2");
      System.out.println("\n -1 compared to 2  " + "= " + (a.compareTo(b)));
      
      a = new BrobInt("2");
      b = new BrobInt("2");
      System.out.println("\n 0 compared to 1  " + "= " + (a.compareTo(b)));
    
      a = new BrobInt("100");
      b = new BrobInt("2000");
      System.out.println("\n 100 compared to 2000 " + "= " + (a.compareTo(b)));
  
      a = new BrobInt("-1000");
      b = new BrobInt("200");
     System.out.println("\n -1000 compared to 200 " + "= " + (a.compareTo(b)));
    
      a = new BrobInt("1");
      b = new BrobInt("200");
      System.out.println("\n 1 compared to 200 " + "= " + (a.compareTo(b)));
      
      a = new BrobInt("1");
      b = new BrobInt("0");
      System.out.println("\n 1 compared to 0 " + "= " + (a.compareTo(b)));

      a = new BrobInt("1");
      b = new BrobInt("1");
      System.out.println("\n 1 compared to 1 " + "= " + (a.compareTo(b)));
    
      a = new BrobInt("0");
      b = new BrobInt("1");
      System.out.println("\n 0 compared to 1 " + "= " + (a.compareTo(b)));

      System.out.println("\n This is the a equals test \n");
 
      a = new BrobInt("1000000");
      b = new BrobInt("1000000");
      System.out.println("\n 1000000 equals to 1000000 " + "= " + (a.equals(b)));

      a = new BrobInt("3");
      b = new BrobInt("3");
      System.out.println("\n 3 equals 3 " + "= " + (a.equals(b)));

      a = new BrobInt("2");
      b = new BrobInt("2");
      System.out.println("\n 2 equals 2 " + "= " + (a.equals(b)));
      
      a = new BrobInt("10");
      b = new BrobInt("10");
      System.out.println("\n 10 equals 10 " + "= " + (a.equals(b)));

      a = new BrobInt("2000");
      b = new BrobInt("2000");
      System.out.println("\n 2000 equals 2000 " + "= " + (a.equals(b)));
  
      a = new BrobInt("200");
      b = new BrobInt("200");
      System.out.println("\n 200 equals 200 " + "= " + (a.equals(b)));
      
      a = new BrobInt("500");
      b = new BrobInt("500");
      System.out.println("\n 500 equals 500 " + "= " + (a.equals(b)));
     
      a = new BrobInt("-20");
      b = new BrobInt("-20");
      System.out.println("\n -20 equals -20" + "= " + (a.equals(b)));

      a = new BrobInt("-6");
      b = new BrobInt("-6");
      System.out.println("\n -6 equals -6 " + "= " + (a.equals(b)));

      a = new BrobInt("2");
      b = new BrobInt("2");    
     System.out.println("\n 2 equals 2 " + "= " + (a.equals(b)));

         
 System.out.println( "\n\n    TESTING VALUEOF( LONG ) METHOD:\n" +
                          "    ===============================" );
      System.out.println( "\n   Creating several long type values to check the 'valueOf()' method: " );
      long long01 = Long.MAX_VALUE;
      long long02 = Long.MIN_VALUE;
      long long03 = 1234567890;
   try {
         System.out.println( "      expecting: " + Long.MAX_VALUE + "\n" +
                             "        and got: " + long01 );
      }
      catch( Exception e ) { System.out.println( " long01 Exception thrown:  " ); }

   try {
         System.out.println( "      expecting: " + Long.MIN_VALUE + "\n" +
                             "        and got: " + long02 );
      }
      catch( Exception e ) { System.out.println( " long02 Exception thrown:  " ); }
long03 = 1234567890;
  try {
         System.out.println( "      expecting: 1234567890\n" +
                             "        and got: " + long03 );
      }
      catch( Exception e ) { System.out.println( " 1234567890       Exception thrown:  " ); }
long03 = 14665890;
  try {
         System.out.println( "      expecting: 14665890\n" +
                             "        and got: " + long03 );
      }
      catch( Exception e ) { System.out.println( " 14665890   Exception thrown:  " ); }
  long03 = 18877890;

  try {
         System.out.println( "      expecting: 18877890\n" +
                             "        and got: " + long03 );
      }
      catch( Exception e ) { System.out.println( "   18877890  Exception thrown:  " ); }
  long03 = 1890;
  try {
         System.out.println( "      expecting: 1890\n" +
                             "        and got: " + long03 );
      }
      catch( Exception e ) { System.out.println( "1890  Exception thrown:  " ); }
  long03 = 7890;
  try {
         System.out.println( "      expecting: 7890\n" +
                             "        and got: " + long03 );
      }
      catch( Exception e ) { System.out.println( "7890 Exception thrown:  " ); }
long03 = 12340;

  try {
         System.out.println( "      expecting: 12340\n" +
                             "        and got: " + long03 );
      }
      catch( Exception e ) { System.out.println( " 12340 Exception thrown:  " ); }
  long03 = 567890;
  try {
         System.out.println( "      expecting: 567890\n" +
                             "        and got: " + long03 );
      }
      catch( Exception e ) { System.out.println( "  567890 Exception thrown:  " ); }
      long03 = 34567890;
  try {
         System.out.println( "      expecting: 34567890\n" +
                             "        and got: " + long03 );
      }
      catch( Exception e ) { System.out.println( " 34567890 Exception thrown:  " ); }

    long03 = 123450;     
 try {
         System.out.println( "      expecting: 123450\n" +
                             "        and got: " + long03 );
      }
      catch( Exception e ) { System.out.println( " 123450 Exception thrown:  " ); }



    System.out.println("This  is for the BrobInt ZERO");
     
      System.out.println( "\n  Test  Brobint constant ZERO is correct: " );
      try { System.out.println( "  63225336 expecting: true\n         and got: " + new BrobInt("63225336").ZERO.equals( new BrobInt("0") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
      System.out.println( "\n  Test  Brobint constant ZERO is correct: " );
      try { System.out.println( "  63225377    expecting: true\n         and got: " + new BrobInt("63225377").ZERO.equals( new BrobInt("0") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
      System.out.println( "\n  Test  Brobint constant ZERO is correct: " );
      try { System.out.println( "  632    expecting: true\n         and got: " + new BrobInt("632").ZERO.equals( new BrobInt("0") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
      System.out.println( "\n  Test  Brobint constant ZERO is correct: " );
      try { System.out.println( "  78762567496535   expecting: true\n         and got: " + new BrobInt("78762567496535").ZERO.equals( new BrobInt("0") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
      System.out.println( "\n  Test  Brobint constant ZERO is correct: " );
      try { System.out.println( "  7876256749233   expecting: true\n         and got: " + new BrobInt("78762567492335").ZERO.equals( new BrobInt("0") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
      System.out.println( "\n  Test  Brobint constant ZERO is correct: " );
      try { System.out.println( "  632258362883748    expecting: true\n         and got: " + new BrobInt("632258362883748").ZERO.equals( new BrobInt("0") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }           
      System.out.println( "\n  Test  Brobint constant ZERO is correct: " );
      try { System.out.println( "  63225836288348    expecting: true\n         and got: " + new BrobInt("63225836288348").ZERO.equals( new BrobInt("0") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
      System.out.println( "\n  Test  Brobint constant ZERO is correct: " );
      try { System.out.println( "  63225883748    expecting: true\n         and got: " + new BrobInt("63225883748").ZERO.equals( new BrobInt("0") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
      System.out.println( "\n  Test  Brobint constant ZERO is correct: " );
      try { System.out.println( "  732258362883748    expecting: true\n         and got: " + new BrobInt("732258362883748").ZERO.equals( new BrobInt("0") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
      System.out.println( "\n  Test  Brobint constant ZERO is correct: " );
      try { System.out.println( "  832258362883748    expecting: true\n         and got: " + new BrobInt("832258362883748").ZERO.equals( new BrobInt("0") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
           
    System.out.println("This is for the BrobInt ONE");
      System.out.println( "\n  Test Brobint constant ONE is correct: " );
      try { System.out.println( "  63225336 expecting: true\n         and got: " + new BrobInt("63225336").ONE.equals( new BrobInt("1") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
      System.out.println( "\n  Test  Brobint constant ONE is correct: " );
      try { System.out.println( "  63225377    expecting: true\n         and got: " + new BrobInt("63225377").ONE.equals( new BrobInt("1") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
      System.out.println( "\n  Test  Brobint constant ONE is correct: " );
      try { System.out.println( "  632    expecting: true\n         and got: " + new BrobInt("632").ONE.equals( new BrobInt("1") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
      System.out.println( "\n  Test  Brobint constant ONE is correct: " );
      try { System.out.println( "  78762567496535   expecting: true\n         and got: " + new BrobInt("78762567496535").ONE.equals( new BrobInt("1") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
      System.out.println( "\n  Test  Brobint constant ONE is correct: " );
      try { System.out.println( "  7876256749233   expecting: true\n         and got: " + new BrobInt("78762567492335").ONE.equals( new BrobInt("1") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
      System.out.println( "\n  Test  Brobint constant ONE is correct: " );
      try { System.out.println( "  632258362883748    expecting: true\n         and got: " + new BrobInt("632258362883748").ONE.equals( new BrobInt("1") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }           
      System.out.println( "\n  Test  Brobint constant ONE is correct: " );
      try { System.out.println( "  63225836288348    expecting: true\n         and got: " + new BrobInt("63225836288348").ONE.equals( new BrobInt("1") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
      System.out.println( "\n  Test  Brobint constant ONE is correct: " );
       try { System.out.println( "  63225883748    expecting: true\n         and got: " + new BrobInt("63225883748").ONE.equals( new BrobInt("1") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
      System.out.println( "\n  Test  Brobint constant ONE is correct: " );
     try { System.out.println( "  732258362883748    expecting: true\n         and got: " + new BrobInt("732258362883748").ONE.equals( new BrobInt("1") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
      System.out.println( "\n  Test  Brobint constant ONE is correct: " );
     try { System.out.println( "  832258362883748    expecting: true\n         and got: " + new BrobInt("832258362883748").ONE.equals( new BrobInt("1") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
      
         System.out.println("This is for the BrobInt TEN");

    System.out.println( "\n   Test Brobint constant TEN is correct: " );
      try { System.out.println( "  63225336 expecting: true\n         and got: " + new BrobInt("63225336").TEN.equals( new BrobInt("10") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
       System.out.println( "\n  Test  Brobint constant TEN is correct: " );
      try { System.out.println( "  63225377    expecting: true\n         and got: " + new BrobInt("63225377").TEN.equals( new BrobInt("10") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
       System.out.println( "\n  Test  Brobint constant TEN is correct: " );
      try { System.out.println( "  632    expecting: true\n         and got: " + new BrobInt("632").TEN.equals( new BrobInt("10") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
       System.out.println( "\n  Test  Brobint constant TEN is correct: " );
      try { System.out.println( "  78762567496535   expecting: true\n         and got: " + new BrobInt("78762567496535").TEN.equals( new BrobInt("10") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
       System.out.println( "\n  Test  Brobint constant TEN is correct: " );
      try { System.out.println( "  7876256749233   expecting: true\n         and got: " + new BrobInt("78762567492335").TEN.equals( new BrobInt("10") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }
       System.out.println( "\n  Test  Brobint constant TEN is correct: " );
      try { System.out.println( "  632258362883748    expecting: true\n         and got: " + new BrobInt("632258362883748").TEN.equals( new BrobInt("10") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }           
       System.out.println( "\n  Test  Brobint constant TEN is correct: " );
      try { System.out.println( "  63225836288348    expecting: true\n         and got: " + new BrobInt("63225836288348").TEN.equals( new BrobInt("10") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
       System.out.println( "\n  Test  Brobint constant TEN is correct: " );
       try { System.out.println( "  63225883748    expecting: true\n         and got: " + new BrobInt("63225883748").TEN.equals( new BrobInt("10") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
      System.out.println( "\n  Test  Brobint constant TEN is correct: " );
     try { System.out.println( "  732258362883748    expecting: true\n         and got: " + new BrobInt("732258362883748").TEN.equals( new BrobInt("10") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
       System.out.println( "\n  Test  Brobint constant TEN is correct: " );
     try { System.out.println( "  832258362883748    expecting: true\n         and got: " + new BrobInt("832258362883748").TEN.equals( new BrobInt("10") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }      
        

   }
}



