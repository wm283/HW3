package a3_1;

public class AlgebraicComputation {

	public static void main(String[] args) throws WrongFormatException {
		// TODO Auto-generated method stub
		System.out.println(postfixCalc("1 2 3 - 4 5 + 10 2 / - * +"));
		System.out.println(postToInfix("1 2 3 - 4 5 + 10 2 / - * +"));
		System.out.println(isPostfix("1 2 3 - 4 5 + 10 2 / - * +"));
		System.out.println(isPostfix("1+((2-3)*((4+5)-(10/2)))"));

	}
	
	public static boolean isPostfix(String equation) {
		String [] e = equation.replaceAll("^[\\s]+", "").split("[\\s]+");
		boolean isPostfix = false;
		try {
			Double.parseDouble(e[0]);
			Double.parseDouble(e[1]);
			isPostfix = true;
		} catch (NumberFormatException nfe) {
			isPostfix = false;
		}
		return isPostfix;
	}
	
	public static String postToInfix(String equation) throws WrongFormatException {
		String [] e = equation.replaceAll("^[\\s]+", "").split("[\\s]+");
		String operation  = "+-*/";
		Stack s = new Stack();
		
		for (int i = 0; i < e.length; i++) {
			if (operation.indexOf(e[i]) == -1) {
				System.out.println(e[i]);
				s.push(e[i]);
			}
			else {
				String latter = s.pop();
				String former = s.pop();
				String result = "(" + former + e[i] + latter + ")";
				System.out.println(result);
				s.push(result);
			}
		}
		String infix = s.pop();
		return infix.substring(1, infix.length()-1);
	}
	
	public static String inToPostfix(String equation) throws WrongFormatException {
		String operation  = "+-*/";
		String digits = "0123456789";
		
		return null;
	}
	
	public static double postfixCalc(String equation) throws WrongFormatException { // assume equation is well-formatted
		String [] e = equation.replaceAll("^[\\s]+", "").split("[\\s]+");
		String operation  = "+-*/";
		Stack s = new Stack();
		
		for (int i = 0; i < e.length; i++) {
			if (operation.indexOf(e[i]) == -1) {
				s.push(e[i]);
			}
			else {
				String latter = s.pop();
				String former = s.pop();
				double l = Double.parseDouble(latter);
				double f = Double.parseDouble(former);
				double r = operation(e[i], f, l);
				String result = "" + r;
				s.push(result);
			}
		}
		return Double.parseDouble(s.pop());
	}
	
	public static double operation(String operation, double former, double latter) {
		if (operation.equals("+")) { return former + latter; }
		if (operation.equals("-")) { return former - latter; }
		if (operation.equals("*")) { return former * latter; }
		if (operation.equals("/")) { return former / latter; }
		else return 0;
	}

}
