package simpleDifferentiation.rj;

public class Differentiation {
	private Expression expression;
	private Calculator calculator;
	
	public Differentiation(String expression){
		this.expression = new Expression(expression);
		this.calculator = new Calculator(this.expression);
	}
	
	/**
	 * Returns the derivative, in its simplest form, of the given expression during the 
	 * instantiation of this class. Is not able to calculate more than '/' or '*' operation in a row
	 * nor is it able to calculate expressions that involve rules other than the chain, power,
	 * difference ,multiplication by constant, sum , product, and quotient rule. 
	 * @return the derivative in its simplest form
	 * @throws InvalidExpression if the expression can't be differentiated
	 */
	public String getSolution() throws InvalidExpression{;
		String result = calculator.differentiate();
		result = Simplifier.simplify(result);
		return result;
	}
	/**
	 * Returns the expression given during instantiation.
	 * @return the given expression
	 */
	public String getExpression(){
		return expression.getOriginalExpression();
	}
	/**
	 * Returns the postfix of the given expression.
	 * @return postfix of the given expression
	 */
	public String getPostFix(){
		return expression.getPostfixExpression();
	}
	
}
