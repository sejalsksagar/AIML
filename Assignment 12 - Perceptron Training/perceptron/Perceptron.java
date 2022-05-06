package perceptron;

import java.util.*;

class Gate{
	String name;
	int A[];
	int B[];
	int Yexp[];
	
	 Gate(String name){
		 this.name = name;
		 A = new int[]{0, 0, 1, 1};
		 B = new int[]{0, 1, 0, 1};
		 Yexp = new int[4];
		 for(int i=0; i<4; i++) {
			 if(name.equals("AND"))
				 Yexp[i] = A[i] & B[i];
			 if(name.equals("OR"))
				 Yexp[i] = A[i] | B[i];
		 }
	 }
}

public class Perceptron {

	static Gate AND = new Gate("AND");
	static Gate OR = new Gate("OR");;
	
	public static void main(String[] args) {
		
		int ch;
		Gate g = null;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("\n---------------------------------------------------------------------");
			System.out.println("\n***** Perceptron Training ***** ");
			System.out.println("0. Exit");
			System.out.println("1. AND");
			System.out.println("2. OR");
			System.out.println("Enter your choice: ");
			ch = sc.nextInt();
			System.out.println("---------------------------------------------------------------------");
			
			switch(ch) {
				case 0: System.out.println("***** Program End *****"); break;
				case 1: g = AND; break;
				case 2: g = OR; break;
				default: System.out.println("Invalid choice");
			}
			
			if(ch == 0) break;
			System.out.println("Enter w1 & w2: ");
			Double w1 = sc.nextDouble();
			Double w2 = sc.nextDouble();
			System.out.println("Enter learning rate: ");
			Double lr = sc.nextDouble();
			System.out.println("Enter threshold: ");
			Double th = sc.nextDouble();
			
			System.out.println("\n---------------------------------------------------------------------");
			System.out.println("Given: w1 = "+w1+", w2 = "+w2+", learning_rate = "+lr+", threshold = "+th);
			System.out.println("\n---------------------------------------------------------------------");
			
			boolean loop = true;
			 while(loop) {
				 System.out.format("\n%5s %5s %5s %5s %5s %5s %5s %5s %5s","", "A", "B", "Yexp", "wixi", "Yact", "Error", "w1", "w2");
				 for(int i=0; i<4; i++) {
					 double wixi = w1*g.A[i] + w2*g.B[i];
					 int Yact = 0;
					 if(wixi > th) 
						 Yact = 1;
					 
					 int error = g.Yexp[i] - Yact;
					 if(error != 0) {
						 w1 += lr*error*g.A[i];
						 w2 += lr*error*g.B[i];
					 }
					 System.out.format("\n%5s %5s %5s %5s %5.2f %5s %5s %5.2f %5.2f",(i+1), g.A[i], g.B[i], g.Yexp[i], wixi, Yact, error, w1, w2);
					 if(error != 0) {
						 System.out.println("\n---------------------------------------------------------------------");
						 break;
					 }
					 if(i == 3)
						 loop = false;
				 }
			 }
		}while(ch!=0);
		sc.close();
	}
}

/*

---------------------------------------------------------------------

***** Perceptron Training ***** 
0. Exit
1. AND
2. OR
Enter your choice: 
1
---------------------------------------------------------------------
Enter w1 & w2: 
1.2	0.6
Enter learning rate: 
0.5
Enter threshold: 
1

---------------------------------------------------------------------
Given: w1 = 1.2, w2 = 0.6, learning_rate = 0.5, threshold = 1.0

---------------------------------------------------------------------

          A     B  Yexp  wixi  Yact Error    w1    w2
    1     0     0     0  0.00     0     0  1.20  0.60
    2     0     1     0  0.60     0     0  1.20  0.60
    3     1     0     0  1.20     1    -1  0.70  0.60
---------------------------------------------------------------------

          A     B  Yexp  wixi  Yact Error    w1    w2
    1     0     0     0  0.00     0     0  0.70  0.60
    2     0     1     0  0.60     0     0  0.70  0.60
    3     1     0     0  0.70     0     0  0.70  0.60
    4     1     1     1  1.30     1     0  0.70  0.60
---------------------------------------------------------------------

***** Perceptron Training ***** 
0. Exit
1. AND
2. OR
Enter your choice: 
2
---------------------------------------------------------------------
Enter w1 & w2: 
0.6	0.6
Enter learning rate: 
0.5
Enter threshold: 
1

---------------------------------------------------------------------
Given: w1 = 0.6, w2 = 0.6, learning_rate = 0.5, threshold = 1.0

---------------------------------------------------------------------

          A     B  Yexp  wixi  Yact Error    w1    w2
    1     0     0     0  0.00     0     0  0.60  0.60
    2     0     1     1  0.60     0     1  0.60  1.10
---------------------------------------------------------------------

          A     B  Yexp  wixi  Yact Error    w1    w2
    1     0     0     0  0.00     0     0  0.60  1.10
    2     0     1     1  1.10     1     0  0.60  1.10
    3     1     0     1  0.60     0     1  1.10  1.10
---------------------------------------------------------------------

          A     B  Yexp  wixi  Yact Error    w1    w2
    1     0     0     0  0.00     0     0  1.10  1.10
    2     0     1     1  1.10     1     0  1.10  1.10
    3     1     0     1  1.10     1     0  1.10  1.10
    4     1     1     1  2.20     1     0  1.10  1.10
---------------------------------------------------------------------

***** Perceptron Training ***** 
0. Exit
1. AND
2. OR
Enter your choice: 
0
---------------------------------------------------------------------
***** Program End *****

*/