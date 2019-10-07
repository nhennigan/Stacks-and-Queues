
public class Precedence {
public static int rank;
public static int difference;
	
public Precedence() {
	}

	public static void main(String[] args) {
	}
	//Assigns a numeric value to each operator
	public int order(char operator) {
		if (operator=='^') {
			rank=2;
		}
		if (operator=='*'||operator=='/') {
			rank=1;
		}
		if (operator=='+'|| operator=='-') {
			rank=0;
		}
		if (operator=='('||operator==')') {
			rank=10;
		}
		return rank;
	}
		
}
