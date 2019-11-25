import java.math.BigInteger;

public class BrobBigInt {
   
    public static void main (String[] args) {
    	BigInteger a = new BigInteger("203984");
    	BigInteger b = new BigInteger("293847");
    	System.out.println("Adding "+ b.toString() + " + " + a.toString() + " = " + (a.add(b)).toString() );
    	System.out.println("Subtraction"+ b.toString() + " - " + a.toString() + " = " + (a.subract(b)).toString() );
        System.out.println("Dividing "+ b.toString() + " / " + a.toString() + " = " + (b.divide(a)).toString() );
        System.out.println("Multiply "+ b.toString() + " * " + a.toString() + " = " + (b.multiply(a)).toString() );
        System.out.println("Remainder "+ b.toString() + " / " + a.toString() + " = " + (b.remainder(a)).toString() );


	    System.out.println( "\n  Hello, from the BrobInt program!!\n" );
	    System.out.println( "\n  Run your tests from the BrobIntTester...\n" );
	    BrobInt b = new BrobInt("12");
	    System.out.println(b.toString());
   
        System.out.println(" \n This is the Addition test");
        System.out.println(addBrob(10, 5) == 15.0);
        System.out.println(addBrob(0, 5) == 5.0);
      
        System.out.println(" \n This is the Subtraction test");
        System.out.println(subtractBrob(5, 2) == 3.0);
        System.out.println(subtractBrob(8, 1) == 7.0);

       System.out.println(" \n This is the Divide test");  
        System.out.println(divideBrob(6, 2) == 3.0);
        System.out.println(divideBrob(8, 1) == 8.0);

      System.out.println(" \n This is the Multiply test");  
       System.out.println(multiplyBrob(8, 1) == 8.0);
       System.out.println(multiplyBrob(2, 4) == 8.0); 
      
      System.out.println(" \n This is the negative sign test");  
       System.out.println(checkNegativeBrob(10, -5) == 5.0);
       System.out.println(checkNegativeBrob(-5, 1) == -4.0);
    
      System.out.println(" \n This is the compareTo test");  
       System.out.println(compareToBrob(0, 1) == -1);
       System.out.println(compareToBrob(1, 0) == 1);
       System.out.println(compareToBrob(1, 1) == 0);

     System.out.println(" \n This is the equals test");
        System.out.println(equals(1, 1) == true);
        System.out.println(equals(2, 2) == true);
   
    System.out.println( "\n  Test  Brobint constant ZERO is okay: " );
      try { System.out.println( "      expecting: true\n         and got: " + new BrobInt("63225336").ZERO.equals( new BrobInt("0") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }

      System.out.println( "\n  Test Brobint constant ONE is okay: " );
      try { System.out.println( "      expecting: true\n        and got: " + new BrobInt("63225336").ONE.equals( new BrobInt("1") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }

      System.out.println( "\n   Test Brobint constant TEN is okay: " );
      try { System.out.println( "      expecting: true\n        and got: " + new BrobInt("63225336").TEN.equals( new BrobInt("10") ) ); }
      catch( UnsupportedOperationException uoe ) { System.out.println( "        Exception thrown:  "  ); }


        
  }


}
