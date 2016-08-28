package simpleDifferentiation.rj;

import java.util.Stack;

public class Calculator {
	private Expression expression;
	
	Calculator(){		
		expression = new Expression();
	}
	
	Calculator(Expression ex){
		expression = ex;
	}
	/**
	 * Evaluates a postfix expression and returns the answer. Each term must be separated
	 * with commas. 
	 * 		Example : 5x,(5,5x,+)^5,- 
	 * @param expr - a valid postfix expression
	 * @return a String representing the answer
	 */
	public Stack<String> evaluate(String expr) throws InvalidExpression{
		String answer = "";
		Stack<String> termStack = new Stack<String>();
		
		int i = 0;
		while(i < expr.length()){
			int opToDo = checkOperation(expr.charAt(i));
			// -1 equals no operation
			if(opToDo < 0){
				String term = readTerm(expr, i);
				termStack.push(term);
				i += term.length();
			}else{
				
			}
			
		}
			
		return termStack;
	}
	/**
	 * Reads to the end of term which is indicated by a comma
	 * @param expr - a valid postfix expression with commas separating each term
	 * @return the term
	 */
	private String readTerm(String expr, int i){
		String term = "";
		while(i < expr.length() && isNotOperator(expr.charAt(i))){
			if(expr.charAt(i) == ','){
				return term;
			}
			if(expr.charAt(i) == '('){
				return readParenTerm(expr, i);
			}else{
				term += expr.charAt(i);
			}
			i++;
		}
		return " ";
	}
	/**
	 * 
	 * @param expr - valid expresion
	 * @param i - index where char '(' was read
	 * @return 
	 */
	private String readParenTerm(String expr, int i){
		String parenTerm= "";
		while(i < expr.length() && expr.charAt(i) != ')'){
			parenTerm += expr.charAt(i);
			i++;
		}
		while(i < expr.length() && expr.charAt(i) != ','){
			parenTerm += expr.charAt(i);
			i++;
		}
		return  parenTerm;
	}

	private boolean isNotOperator(char charToCheck) {
		return !(charToCheck == '+' || charToCheck == '-' || charToCheck == '*' || charToCheck == '/');

	}
	
	/**
	 * Returns 1 for addition, 2 for subtraction, 3 for multiplication, 4 for
	 * division, and -1 if not an operation
	 * @param charToRead
	 * @return an integer representing the operation
	 */
	private int checkOperation(char charToRead){
		if (charToRead == '+')
			return 1;
		else if (charToRead == '-')
			return 2;
		else if (charToRead == '*')
			return 3;
		else if (charToRead == '/')
			return 4;
		else
			return -1;

	}
	
}
