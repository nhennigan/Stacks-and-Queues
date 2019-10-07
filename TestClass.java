
import javax.swing.JOptionPane; 

public class TestClass {
public static String userInput;
public static int difference;
static ArrayStack s = new ArrayStack();
public static String postfix="";
static Precedence precedence = new Precedence();
static Evaluation eval = new Evaluation();
	
public TestClass() {
	}
//Takes an input from the user and puts it through the methods to make the String in postfix notation
	public static void main(String[] args) {
		//System.out.println("Please enter a numerical infix expression");
		userInput=JOptionPane.showInputDialog("Please enter a numerical infix expression");
		//inputs("8+(8*2)");
		inputs(userInput);
	
}


public static void inputs (String userInput)
{
	//Passes the user's input into the method checks to see if it contains the right variables
	if (checks(userInput)) {
		
		//Secondly checks the length of the input before continuing
		if (userInput.length()>=3 && userInput.length()<=20) {
		//JOptionPane.showMessageDialog(null, "Valid input accepted: "+userInput);
		//passes the valid input to the method stacking to start changing it to postfix notation
		stacking(userInput);
		}
		//If fails to comply error messages appear
		else {
			JOptionPane.showMessageDialog(null, "Invalid input.Too many characters.Please enter a new expression");
		}
	}
	else {
		JOptionPane.showMessageDialog(null, "Invalid input.Please enter numbers 0-9 or operators +,-,*,^,/,(,).");
	}
}
	

// Method used to check if the inputs are between 0-9 and correct operators
public static boolean checks (String check1) {
	int i=0;
	boolean ok=false;
	char b=0;
	while (i<check1.length()) {
		b=check1.charAt(i);
		if(Character.isDigit(b)|| b=='+'|| b=='-'|| b=='^'||b==','||b=='/'||b=='*'||b=='('||b==')') {
			//JOptionPane.showMessageDialog(null,+b);
			System.out.println(b);
			ok=true;
			i=i+1;
		}
		else return false;
	}
	return ok;
}


//Method used to turn input into postfix
public static void stacking(String input) {
	int u=0; int valueStack; int valueNew; ;
	char k; char topOfStack; boolean go=false;
	double result;
	
	while(u<input.length()) {
		//Sets k equal to a particular character in the String
		k=input.charAt(u);
		
		//if it is a number it is automatically added to the string postfix
		if (Character.isDigit(k))
		{
			postfix+= Character.toString(k);
			//System.out.println statements are used throughout this method to follow what is happening in the stack
			System.out.println("Add number"+k);
		}
		
		else {
			//pushes first operator onto the stack. The stack is empty so we know its the first operator
			if(s.isEmpty()) {
				s.push(k);
				System.out.println("Add first operator to empty "+s.top());
			}
			
			else {
				//gtes the top of the Stack and passes them to the class precedence which returns order of processing in int format
				topOfStack=(char)s.top();
				valueStack=precedence.order(topOfStack);
				valueNew=precedence.order(k);
				//differnce is the indicator to see should an operator be popped,pushed or popped and pushed
				difference=valueStack-valueNew;
			
				//In these cases there is no need to check the top of the stack so operators are pushed
					if ( k=='('|| (char)s.top()=='(')
					{
						s.push(k);
						System.out.println("Open bracket added to stack"+(char)s.top());
					}
			

			//This else if statement is used when the closing bracket is encountered. It pops the operators on the string
					//until the opening bracket is found. Then it pops the bracket.
					else if(k==')') {
						boolean control=true;
						
						while(control) {
							postfix+=s.pop();
							char check=(char)s.top();
						
								if((char)check=='(') {
									control=false;
									s.pop();
								}
					
					
						}
					System.out.println("Brackets have been popped");
					}
			
			
			
			else if(difference>=0) {
				//this difference is when the precedence is lower than the top of the stack. The stack is popped and then the 
				// operator is pushed to the stack
				if (0<=difference && difference<3) {
					postfix+=(char)s.pop();
					s.push(k);
				}
				//This difference occurs when a bracket is on the stack. The difference is much larger than the difference
				//with other operators
				else if(difference>7) {
					s.push(k);
				}
				
				System.out.println("Char popped and added. Added: "+k);
			}
		
			//This difference is when the precedence is higher and the operator is pushed to the stack
			else if (difference<0) {
				s.push(k);
				System.out.println("Char higher prec added to stack:"+k);
			}
		}
		}	
		u=u+1;
	}
	//pops the remainder of the stack after the loop is done iterating through the input string
	while (!s.isEmpty()) {
		postfix+=(char)s.pop();	
	}
	
	System.out.println("Postfix: "+postfix);
	
	//sends the postfix string to the class evaluation to check the numerical value
	result=Evaluation.calc(postfix);
	JOptionPane.showMessageDialog(null, "The result of the expression is: \n Infix: "+input+ "\n Postfix expression: "+postfix+ "\n Result: "+result);
	}
}






