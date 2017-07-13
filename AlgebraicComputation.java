package a3_1;

public class AlgebraicComputation {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(postfixCalc("1 2 3 - 4 5 + 10 2 / - * +"));
		//System.out.println(postToInfix("1 2 3 - 4 5 + 10 2 / - * +"));
		//System.out.println(isPostfix("1 2 3 - 4 5 + 10 2 / - * +"));
		//System.out.println(isPostfix("1+((2-3)*((4+5)-(10/2)))"));
		System.out.println(inToPostfix("1+((2-3)*((4+5)-10/2))"));
		System.out.println(inToPostfix("1+(2-3)*(4+5-10/2)"));
		System.out.println(postfixCalc("1 2 3 - 4 5 10 2 / - + * + "));
		System.out.println(postToInfix("1 2 3 - 4 5 10 2 / - + * + "));

	}
	
	public static String postToInfix(String equation) throws WrongFormatException {
		String [] e = equation.replaceAll("^[\\s]+", "").split("[\\s]+");
		String operation  = "+-*/%";
		Stack s = new Stack();
		
		for (int i = 0; i < e.length; i++) {
			if (operation.indexOf(e[i]) == -1) {
				s.push(e[i]);
			}
			else {
				String latter = s.pop();
				String former = s.pop();
				String result = "(" + former + e[i] + latter + ")";
				s.push(result);
			}
		}
		String infix = s.pop();
		return infix.substring(1, infix.length()-1);
	}
	
	public static String inToPostfix(String equation) throws Exception {
		String operation  = "+-*/%";
		String digits = "0123456789";
		String parenthesis = "()";
		
		String infix = "";
		for (int i = 0; i < equation.length(); i++) {
			String temp = Character.toString(equation.charAt(i));
			if (operation.indexOf(temp) == -1 && digits.indexOf(temp) == -1 && parenthesis.indexOf(temp) == -1) {
				throw new IllegalCharException();
			}
			else {
				if (digits.indexOf(temp) == -1) infix = infix + " " + temp + " ";
				else infix = infix + temp;
			}
		}
		
		String [] e = infix.replaceAll("^[\\s]+", "").split("[\\s]+");
		String postfix = "";
		Stack s = new Stack();
		
		for (int i = 0; i < e.length; i++) {
			if (operation.indexOf(e[i]) == -1 && parenthesis.indexOf(e[i]) == -1) {
				postfix += e[i] + " ";
			}
			else {
				if (e[i].equals("+") || e[i].equals("-")) {
					String prev = "";
					try { prev = s.pop(); } catch (WrongFormatException wfe) { prev = ""; }
					if (prev.equals("(") || prev.equals("+") || prev.equals("-")) {
						s.push(prev);
					}
					if (prev.equals("*") || prev.equals("/") || prev.equals("%")) {
						postfix += prev + " ";
					}
					s.push(e[i]);
				}
				if (e[i].equals("%") || e[i].equals("*") || e[i].equals("/") || e[i].equals("(")) {
					s.push(e[i]);
				}
				if (e[i].equals(")")) {
					String temp = s.pop();
					while (!temp.equals("(")) {
						postfix += temp + " ";
						temp = s.pop();
					}
				}
			}
		}
		boolean f = true;
		
		while (f) {
			String temp = "";
			try {
				temp = s.pop();
			} catch (WrongFormatException wfe) {
				f = false;
			}
			postfix += temp + " ";
		}
		
		return postfix.substring(0, postfix.length()-1);
	}
	
	public static double postfixCalc(String equation) throws WrongFormatException { // assume equation is well-formatted
		String [] e = equation.replaceAll("^[\\s]+", "").split("[\\s]+");
		String operation  = "+-*/%";
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
		if (operation.equals("%")) { return former % latter; }
		else return 0;
	}

}
