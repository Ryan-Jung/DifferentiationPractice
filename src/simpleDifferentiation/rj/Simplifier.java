package simpleDifferentiation.rj;

/**
 * Does basic sign simplification
 * @author Ryan Jung
 *
 */
public final class Simplifier {
	//no instances
	private Simplifier(){};
	/**
	 * Reduces/simplifies the given expression. 
	 * @param expr - a valid expression
	 * @return the reduced expression
	 */
	public static String simplify(String expr){
		expr = minusMinusToPlus(expr);
		expr = plusMinusToMinus(expr);
		return expr;
	}
	/**
	 * Changes all occurrences of a "--" in the expression to a "+" 
	 * @param expression
	 * @return 
	 */
	public static String minusMinusToPlus(String expression){
		String result = "";
		for(int i = 0; i < expression.length(); i++){
			int indexToCheck = expression.indexOf("-", i);
			if(i < indexToCheck && indexToCheck != -1){
				result += expression.charAt(i);
			}
			else if(indexToCheck != -1 && expression.charAt(indexToCheck + 1) == '-'){
				result += "+";
				i = indexToCheck + 1;
			}else{
				result += expression.charAt(i);
			}
		}
		return result;
	}
	private static String plusMinusToMinus(String expression){
		String result = "";
		for(int i = 0; i < expression.length(); i++){
			int indexToCheck = expression.indexOf("+", i);
			if(i < indexToCheck && indexToCheck != -1){
				result += expression.charAt(i);
			}
			else if( indexToCheck != -1 && expression.charAt(indexToCheck+1) == '-'){
				result += '-';
				i = indexToCheck +1;		
			}else{
				result += expression.charAt(i);
			}
		}
		return result;
	}

}