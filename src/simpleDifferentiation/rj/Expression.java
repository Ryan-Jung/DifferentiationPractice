package simpleDifferentiation.rj;

import java.util.Stack;

/**
 * Holds an expression in postfix notation with each term separated by a comma.
 * (Example : 5x,(5,5x,+)^5,- )Parenthetical expressions are considered as a single term.
 * Can randomly generate an expression by calling the default constructor or can 
 * take in an infix expression and convert it to  postfix. To see or use the expression
 * call getExpression.
 * @author Ryan Jung
 *
 */
public class Expression {

	private String expression;

	/**
	 * Creates an expression with random integers and operators containing at
	 * most 4 terms and at least 3 terms
	 */
	Expression() {
		int numberOfTerms = (int) (Math.random() * 2 + 2);
		String expr = "";
		for (int i = 0; i < numberOfTerms; i++) {
			expr += termGenerator();
			expr += signGenerator();
		}
		expr += termGenerator();
		setExpression(expr);
	}
	/**
	 * 
	 * @param expression a valid infix expression
	 */
	Expression(String expression) {
		setExpression(expression);
	}

	/**
	 * Generates random integers from 1 to 10 for the coefficient and power of x of
	 * a term
	 * 
	 * @return a term
	 */
	private String termGenerator() {

		int randomcoefficient = (int) (Math.random() * 10 + 1);
		int randomPower = (int) (Math.random() * 10 + 1);

		Term<Integer> newTerm = new Term<Integer>(randomcoefficient, randomPower);
		return newTerm.toString();
	}

	/**
	 * Generates an mathematical operation for the expression
	 * 
	 * @return a string representing the operation to conduct
	 */
	private String signGenerator() {
		double randomizer = Math.random();
		if (randomizer < .25) {
			return "+";
		} else if (randomizer < .50) {
			return "-";
		} else if (randomizer < .75) {
			return "*";
		} else {
			return "/";
		}
	}

	/**
	 * Converts an infix expression to postfix before setting it
	 * 
	 * @param exp a valid infix expression
	 */
	public void setExpression(String expr) {
		expr = infixToPostfix(expr);
		// remove all whitespace
		expression = expr.replaceAll("\\s+", "");
	}

	/**
	 * Returns a postfix expression with commas separating terms
	 * @return an expression in postfix notation 
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * Uses the shunting-yard alrgorithm to convert an infix expression to
	 * postfix (Note: Parenthetical expressions are considered as a single term).
	 * 
	 * @param expr A valid infix expression
	 * @return A string representing an expression's postfix notation with each
	 *         term separated by commas
	 */
	private String infixToPostfix(String expr) {
		Stack<Character> operatorStack = new Stack<Character>();
		String postfix = "";

		for (int i = 0; i < expr.length(); i++) {
			String term = "";
			// since operations may be inside parenthesis read everything in 
			// parenthesis as a whole term
			if (expr.charAt(i) == '(') {
				while (expr.charAt(i) != ')') {
					term += expr.charAt(i);
					i++;
				}
				// make sure expression in parenthesis is in postfix notation
				term = term.replaceAll("[(]", "");
				term = "(" + infixToPostfix(term);

				while (i < expr.length() && isNotOperator(expr.charAt(i))) {
					term += expr.charAt(i);
					i++;
				}

				postfix += term + ",";
			} else {
				// read term
				while (i < expr.length() && isNotOperator(expr.charAt(i))) {
					term += expr.charAt(i);
					i++;
				}
				term += ",";

				postfix += term;

			}
			// Operator encountered. Decides which operation needs to be
			// added to postfix string
			if (i < expr.length() && !isNotOperator(expr.charAt(i))) {
				if (operatorStack.isEmpty()) {
					operatorStack.push(expr.charAt(i));
				} else {
					char operatorToCheck = expr.charAt(i);
					char operatorOnStack = operatorStack.peek();

					while (operationPrecedence(operatorToCheck) >= operationPrecedence(operatorOnStack)
							&& !operatorStack.isEmpty()) {
						postfix += operatorStack.pop();
					}
					operatorStack.push(operatorToCheck);

				}
			}

		}
		while (!operatorStack.isEmpty()) {
			postfix += operatorStack.pop();
		}

		return postfix;
	}

	/**
	 * Returns an integer representing operation precedence with a lower number
	 * indicating higher precedence. Operations of the same precedence have the
	 * same value.
	 * 
	 * @param opToCheck
	 * @return an integer representing an operations precedence
	 * 
	 * 
	 */
	private int operationPrecedence(char opToCheck) {
		if (opToCheck == '+')
			return 2;
		else if (opToCheck == '-')
			return 2;
		else if (opToCheck == '*')
			return 1;
		else if (opToCheck == '/')
			return 1;
		else
			return -1;

	}

	/**
	 * @param charToCheck
	 * @return True if character to check is not operator and false if it is
	 */
	private boolean isNotOperator(char charToCheck) {
		return !(charToCheck == '+' || charToCheck == '-' || charToCheck == '*' || charToCheck == '/');

	}


}