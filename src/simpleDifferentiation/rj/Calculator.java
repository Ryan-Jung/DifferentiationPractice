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
	 * @param exp Must be a valid postfix expression
	 * @return a String representing the answer
	 */
	public Stack<String> evaluate(String expr) throws InvalidExpression{
		String answer = "";
		Stack<String> termStack = new Stack<String>();
		
		int i = 0;
		while(i < expr.length()){
			
			//push parenthetical expression as a single term since chain rule
			//needs to be applied
			if(expr.charAt(i) == '('){
				String parenTerm = readParenTerm(expr, i);
				termStack.push(parenTerm);
				
			}else{
				int locToReadTo = findLocOfNextOp(expr);
				
			}
			}
			
		return termStack;
	}
	/**
	 * 
	 * @param expr expression to be looked at
	 * @param i index where char '(' was read
	 * @return 
	 */
	public String readParenTerm(String expr, int i){
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
	private int findLengthOfTerm(String expr){
		int i = 0;
		while(i < expr.length() && expr.charAt(i) != ','){
			i++;
		}
		return i;
	}
	
	/**
	 * @param expr expression to examine
	 * @return location of first operation to appear in an expression
	 * @throws InvalidExpression 
	 */
	private int findLocOfNextOp(String expr) throws InvalidExpression{
		int location = Integer.MAX_VALUE ;
		char [] opsToFind = {'+', '-', '/', '*'};
		for(int i = 0 ; i < opsToFind.length; i++){
			if(expr.indexOf(opsToFind[i]) < location){
				location = expr.indexOf(opsToFind[i]);
			}
		}
		if(location == Integer.MAX_VALUE){
			throw new InvalidExpression("Missing operation");
		} 
		return location;
		
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
