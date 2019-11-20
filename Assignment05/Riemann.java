
public class Riemann {

	/** Runs the main method by declaring a string array.*/
	public static void main(String[] args) {
	   	 	
	    boolean lastArgPercent = (args.length > 0 && args[args.length-1].contains("%"));
	    if (args.length == 0 || args[0].equals("runtests")) {
	        runMyTests();
	        return;
	    }
	    if ((args.length < 3 || (args.length < 4 && lastArgPercent))) {
	    	System.out.println("Not enough arguments");
	    	return;
	    }

	    double lowerBound = getlowerBound(args);
	    double upperBound = getupperBound(args);
	    double percent = getPercent(args);
	    double result = 0.0;

		switch (args[0]) {
	        case "poly":
	            if (args.length < 4 || (args.length < 5 && lastArgPercent)) {
	    	        System.out.println("Not enough arguments");
			  	    return;
			    }
			    double [] coeffs = getCoeffs(args);
			    result = intergratePoly(coeffs, lowerBound, upperBound, percent);
			    break;
		 	case "sin":
			    result = integrateSin(lowerBound, upperBound, percent);
			    break;
		   
		}
	}

   	/**Calculates the percentage difference between two values.*/
	public static double calcDiffPercent(double value1, double value2){
		return Math.abs(value1 - value2) / value2;
	}



    /**Calculates the rectWidth for the parameters.*/
	public static double rectWidth(double lowerBound, double upperBound, double numOfRects) {
		
		return (upperBound - lowerBound) / numOfRects;
		
    }

   /**Calculates the calcpoly.*/
	public static double calcPoly(double x, double [] coeffs){
		System.out.println("In CALCPOLY, x is " + x + ", coeffs is " + coeffs);
		double sum = 0;
		for(int i = 0; i < coeffs.length; i++){
			sum += (coeffs[i] * Math.pow(x, i));
		}
		System.out.println("CALCPOLY height result is " + sum);
		return sum;
		
	}

    /**Calculates the intergratePoly() method by declaring the double [] coeffs, double lowerBound, double upperBound, double percentage parameters.*/
	public static double intergratePoly(double [] coeffs, double lowerBound, double upperBound, double percentage){

		double currentX;
		double lastResultSum;
		double currentRectWidth;
		double currentRectHeight;
		double diffPercentage = 1.0;
		double currentResultSum = 0.0;
		double lowerbound;
		int numOfRects = 1;
	    System.out.println(currentResultSum); 
        System.out.println("BREAK");



		while(diffPercentage > percentage){
			 System.out.println("WHILE LOOP: currentResultSum is " + currentResultSum); 
			lastResultSum = currentResultSum;
			currentResultSum = 0.0;
			currentRectWidth = rectWidth(lowerBound, upperBound, numOfRects);
			currentX = lowerBound + currentRectWidth/2;
			 // System.out.println(currentResultSum); 
				
			for(int i = 0; i < numOfRects; i ++){
				currentRectHeight = calcPoly(currentX, coeffs);
				currentResultSum += (currentRectHeight * currentRectWidth);
				currentX += currentRectWidth;
			}
			   	  System.out.println("After forloop: " + currentResultSum); 
			diffPercentage = calcDiffPercent(currentResultSum, lastResultSum);
			numOfRects++;
		

		}
			  
		return currentResultSum;
	}

    /**Calculates the integrateSin() method by declaring the double lowerBound, double upperBound, double percentage.*/
    public static double integrateSin(double lowerBound, double upperBound, double percentage){
     	double currentX;
		double lastResultSum;
		double currentRectWidth;
		double currentRectHeight;
		int numOfRects = 0;
		double diffPercentage = 1.0;
		double currentResultSum = 0.0;
		while (diffPercentage > percentage){
			numOfRects++;
			lastResultSum = currentResultSum;
			currentResultSum = 0.0;
			currentRectWidth = rectWidth( lowerBound, upperBound, numOfRects);
			currentX = lowerBound;
			for(int i = 0; i <= numOfRects; i++){
				currentRectHeight = Math.sin(currentX);
				currentResultSum += (currentRectHeight * currentRectWidth);
				currentX += currentRectWidth;
			}
			diffPercentage = calcDiffPercent(currentResultSum, lastResultSum);
		}

		 	return currentResultSum;

     }


       public static double [] getCoeffs(String[]args){
       	double[] coeffs;
       	boolean lastArgPercent = (args.length > 0 && args[args.length - 1].contains("%"));
       	int lastCoeff;
       	if(lastArgPercent){
       		lastCoeff = args.length - 4;
       		coeffs = new double[args.length - 3];
       	}else{
       		lastCoeff = args.length -3;
       		coeffs = new double[args.length - 2];
       	}

       	for (int i = 1; i <= lastCoeff; i++){
       		coeffs[i-1] = Double.valueOf(args[i]);

       	}

            return coeffs;
       }

/**Calculates the getlowerBound() method by declaring a string array.*/
   public static double getlowerBound(String args[]){
   	     boolean lastArgPercent = (args.length > 0 && args[ args.length - 1].contains("%"));
   	     if (lastArgPercent){
   	     	return Double.valueOf(args[args.length - 3]);
   	     }else{
   	     	return Double.valueOf(args[args.length - 2]);
   	     }

   	 }

/**Calculates the getupperBound() method by declaring a string array.*/
	public static double getupperBound(String args[]){
   	     boolean lastArgPercent = (args.length > 0 && args[ args.length - 1].contains("%"));
   	     if (lastArgPercent){
   	     	return Double.valueOf(args[args.length - 2]);
   	     }else{
   	     	return Double.valueOf(args[args.length - 1]);
   	     }

   	 }

/**Calculates the getPercent() method by declaring a string array.*/
	public static double getPercent(String args[]){
   	     boolean lastArgPercent = (args.length > 0 && args[ args.length - 1].contains("%"));
   	     if (lastArgPercent){
   	     	System.out.println("PERCENT: " + Double.valueOf(args[args.length - 1].replace("%", "")) / 100);
   	     	return Double.valueOf(args[args.length - 1].replace("%", "")) / 100;
   	     }else{

	 		System.out.println("PERCENT: " + 0.01);

   	     	return 0.01;
   	     }

   	 }

    private static boolean isCloseTo(double x, double y) {
    	return Math.abs(x - y) < 0.001;
    }

 	private static void runMyTests() {
 		runArgumentTests();
 		runDiffPercentTests();
 		runRectWidthTests();
 		runPolyTests();
 		runSinTests();
 		
 	}

 	private static void runArgumentTests() {
		String[] args = new String[]{"poly", "5.0", "1.0", "2.0", "3.0", "0.0", "3.0", "0.01%"};
		System.out.println("lower bound works:  " + (getlowerBound(args) == 0.0));
		System.out.println("upper bound works:  " + (getupperBound(args) == 3.0));
		System.out.println("percent works: " + (getPercent(args) == (0.01/100)));
		double[] coeffs = getCoeffs(args);
		System.out.println("Coeffs test pass:" + (coeffs[0] == 5.0 && coeffs[1] == 1.0 && coeffs[2] == 2.0 && coeffs[3] == 3.0));

		args = new String[]{"sin", "1", "2", "0.05%"};
		System.out.println("lower bound works:  " + (getlowerBound(args) == 1.0));
		System.out.println("upper bound works:  " + (getupperBound(args) == 2.0));
		System.out.println("percent works: " + (getPercent(args) == (0.05/100)));



		args = new String[]{"sin", "0", "1", "0.05%"};
		System.out.println("lower bound works:  " + (getlowerBound(args) == 0.0));
		System.out.println("upper bound works:  " + (getupperBound(args) == 1.0));
		System.out.println("percent works: " + (getPercent(args) == (0.05/100)));
    

       args = new String[]{"sin", "1", "2", "0.09%"};
		System.out.println("lower bound works:  " + (getlowerBound(args) == 1.0));
		System.out.println("upper bound works:  " + (getupperBound(args) == 2.0));
		System.out.println("percent works: " + (getPercent(args) == (0.09/100)));

		args = new String[]{"sin", "9", "2", "0.05%"};
		System.out.println("lower bound works:  " + (getlowerBound(args) == 9.0));
		System.out.println("upper bound works:  " + (getupperBound(args) == 2.0));
		System.out.println("percent works: " + (getPercent(args) == (0.05/100)));


		args = new String[]{"sin", "10", "11", "0.08%"};
		System.out.println("lower bound works:  " + (getlowerBound(args) == 10.0));
		System.out.println("upper bound works:  " + (getupperBound(args) == 11.0));
		System.out.println("percent works: " + (getPercent(args) == (0.08/100)));


		args = new String[]{"poly", "6.0", "1.0", "2.0", "3.0", "0.0", "4.0", "0.06%"};
		System.out.println("lower bound works:  " + (getlowerBound(args) == 0.0));
		System.out.println("upper bound works:  " + (getupperBound(args) == 4.0));
		System.out.println("percent works: " + (getPercent(args) == (0.06/100)));
		
		

        args = new String[]{"poly", "10.0", "11.0", "12.0", "13.0", "1.0", "14.0", "0.08%"};
        System.out.println("lower bound works:  " + (getlowerBound(args) == 1.0));
		System.out.println("upper bound works:  " + (getupperBound(args) == 14.0));
		System.out.println("percent works: " + (getPercent(args) == (0.08/100)));
		

	     args = new String[]{"poly", "11.0", "1.0", "4.0", "3.0", "1.0", "14.0", "0.02%"};
		System.out.println("lower bound works:  " + (getlowerBound(args) == 1.0));
		System.out.println("upper bound works:  " + (getupperBound(args) == 14.0));
		System.out.println("percent works: " + (getPercent(args) == (0.02/100)));
		

		 args = new String[]{"poly", "1.0", "2.0", "4.0", "3.0", "2.0", "4.0", "0.02%"};
		System.out.println("lower bound works:  " + (getlowerBound(args) == 2.0));
		System.out.println("upper bound works:  " + (getupperBound(args) == 4.0));
		System.out.println("percent works: " + (getPercent(args) == (0.02/100)));
		

}
    private static void runDiffPercentTests() {
    	System.out.println(" \n This is the runDiffPercentTests");
        System.out.println(calcDiffPercent(10, 5) == 1.0);
        System.out.println(calcDiffPercent(2, 4) == 0.5);
        System.out.println(calcDiffPercent(0, 5) == 1.0);
        System.out.println(calcDiffPercent(6, 2) == 2.0);
        System.out.println(calcDiffPercent(4, 2) == 1.0);
        System.out.println(calcDiffPercent(6, 3) == 1.0);
        System.out.println(calcDiffPercent(10, 2) == 4.0);
        System.out.println(calcDiffPercent(12, 2) == 5.0);
        System.out.println(calcDiffPercent(8, 4) == 1.0);
        System.out.println(calcDiffPercent(16, 8) == 1.0);
    }        
         
    private static void runRectWidthTests() {
    	System.out.println(" \n This is the runRectWidthTests");
        System.out.println(rectWidth(1, 5, 1) == 4.0);
        System.out.println(rectWidth(2, 6, 2) == 2.0);
        System.out.println(rectWidth(2, 5, 1) == 3.0);
        System.out.println(rectWidth(1, 5, 2) == 2.0);
        System.out.println(rectWidth(1, 4, 1) == 3.0);
        System.out.println(rectWidth(1, 3, 1) == 2.0);
        System.out.println(rectWidth(1, 2, 2) == 0.5);
        System.out.println(rectWidth(0, 2, 2) == 1.0);
        System.out.println(rectWidth(1, 4, 1) == 3.0);
        System.out.println(rectWidth(2, 4, 1) == 2.0);

      
    }

    private static void runPolyTests() {
    // 	 System.out.println("\n This is the poly test");
    // 	  System.out.println(intergratePoly(1, 2, 3, -1, 1, 0.1) == 0.0);
    	
     }

    private static void runSinTests() {
      System.out.println("\n This is the runSinTests");
      System.out.println(integrateSin(-1, 1, 0.1) == 0.0);
      System.out.println(isCloseTo(integrateSin(0, Math.PI, 0.001), 1.990));
      System.out.println(isCloseTo(integrateSin(1, 2, 0.001), 0.9846044024597911));
      System.out.println(isCloseTo(integrateSin(2, 4, 0.001), 0.2434699031125554));
      System.out.println(isCloseTo(integrateSin(6, 8, 0.001), 1.1334782766484381 ));
      System.out.println(isCloseTo(integrateSin(10, 12, 0.001), -2.080584235440508));
      System.out.println(isCloseTo(integrateSin(8, 4, 0.001),0.09371949376694455 ));
      System.out.println(isCloseTo(integrateSin(6, 1, 0.001), -0.528180647298377 ));
      System.out.println(isCloseTo(integrateSin(7, 2, 0.001), -1.4718847696984345));
      System.out.println(isCloseTo(integrateSin(0, 1, 0.001), 0.4732299433087055));
    }   

    

}
