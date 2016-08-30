package simpleDifferentiation.rj;

import java.util.EmptyStackException;
import java.util.Stack;
/**
 * Used for differentiating postfix expressions. 
 * @author Ryan Jung
 *
 */
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
		expr = expr.toLowerCase();
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
				String term1;
				String term2;
				try{
					term1 = termStack.pop();
					term2 = termStack.pop();	
				}catch(EmptyStackException esex){
					throw new InvalidExpression("Cannot evaluate. Missing terms");
				}
				switch(opToDo){
					case 3: multiply(term1, term2);
				}
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
	 * @param expr - valid expression
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
	/**
	 * @param charToCheck
	 * @return True if char is not operator false otherwise
	 */
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
	public String multiply(String a, String b){
		return "";
	}
	/**
	 * Differentiates a term and returns the derivative. Applies
	 * the power rule(https://en.wikipedia.org/wiki/Power_rule) to 
	 * differentiate a term and get its derivative. The variable used must be
	 * 'x'. Will not calculate derivative properly if there is an 'x' in 
	 * the exponent.
	 * @param term 
	 * @return the derivative
	 * @throws InvalidExpression - if the term is invalid
	 */
	public String getDerivative(String term) throws InvalidExpression{
		//Derivative of constant is 0;
		boolean isConstant = !term.contains("x");
		if(isConstant){
			return "";
		}
		
		String coefficient = readCoefficient(term);
		String power = readPower(term);
		
		double coef = 0, pow = 0;
		
		try{
			coef = Double.parseDouble(coefficient);
			if(!power.equals("")){
				pow = Double.parseDouble(power);
			}
		}catch(NullPointerException npex){
			throw new InvalidExpression("Invalid term. Term has no numbers.");
		}catch(NumberFormatException nfex){
			throw new InvalidExpression("Invalid term. Cannot read the coefficient of power.");
		}
		//Differentiate by applying power rule.
		if(pow != 0){
			coef = coef * pow;
			pow = pow - 1;
			return coef + "x" + "^" + pow;
		}else{
			return String.valueOf(coef);
		}
	}
	/**
	 * Only works with the variable 'x'. If term is a constant will simply 
	 * return the constant. 
	 * @param term 
	 * @return the coefficient of a term
	 */
	private String readCoefficient(String term){
		String coefficient = "";
		int i = 0;
		while(i < term.length() && term.charAt(i) != 'x'){
			coefficient += term.charAt(i);
			i++;
		}
		return coefficient;
	}
	/**
	 * Exponents should be denoted with a '^' in order for it to be read
	 * correctly. 
	 * @param term
	 * @return returns the exponent value
	 */
	private String readPower(String term){
		String power = "";
		int i = 0;
		//read power
		if(term.contains("^")){
			i = term.indexOf("^") + 1;
			while(i < term.length()){
				power += term.charAt(i);
				i++;
			}
		}
		return power;
	}
	
}
