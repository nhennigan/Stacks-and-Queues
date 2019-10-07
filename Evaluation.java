
public class Evaluation {
static ArrayStack numbers= new ArrayStack();
static double c;
	public Evaluation() {
	}
//This method calculates the value of the postfix expression

public static double calc (String old) {
	int t=0;
	char r; 
	double res;

	// while loop is used to iterate through the string input
	while (t<old.length()) {
		r=old.charAt(t);
		
		//If it is a number, it is pushed to the stack
		if (Character.isDigit(old.charAt(t))){
			numbers.push((double)old.charAt(t)-'0');
		}
		
		//Otherwise it must be an operator
		else  {
			double x=0; double y=0; 
			
			//Two numbers are popped from the stack and cast as doubles.
			y=(double)numbers.pop();
			System.out.println("First popped"+y);
			x=(double)numbers.pop();
			System.out.println("Second popped"+x);

			//These two numbers along with the current character (the operator) are sent to the method operation
			res=operation(x,y,old.charAt(t));
			//The resulting number is pushed to the stack
			numbers.push(res);
		}
		t=t+1;
		System.out.println("Running result:"+numbers.top());
		}
	System.out.println("Result:"+numbers.top());
	return (double)numbers.top();
		}

//This method checks what operator has been passed to it and carries out the relevant mathematical equation and 
//returns the answer to the calc method.
public static double operation(double a, double b,char r) {
	if (r=='+') {
		c=a+b;
		return c;
	}
	if (r=='-') {
		c=a-b;
		return c;
	}
	if(r=='/') {
		c=a/b;
		return c;
	}
	if (r=='*') {
		c=a*b;
		return c;
	}
	if (r=='^') {
		c=(double)Math.pow(a, b);
		return c;
	}
	return c;
	}
}







