package simpleDifferentiation.rj;

import org.matheclipse.core.eval.EvalEngine;
import org.matheclipse.core.interfaces.IExpr;
/**
 * Uses Symja - Java Computer Algebra Library (https://bitbucket.org/axelclk/symja_android_library/wiki/Home)
 * for to simplify an expression. 
 * @author Ryan Jung
 *
 */
public class Simplifier {
	//no instances
	private Simplifier(){};
	/**
	 * Reduces/simplifies the given expression. 
	 * @param expr - a valid expression
	 * @return the reduced expression
	 */
	public static String simplify(String expr){
		expr = minusMinusToPlus(expr);
		EvalEngine engine = new EvalEngine(true);
		IExpr result = engine.evaluate(expr);
		return result.toString();
	}
	/**
	 * Changes all occurrences of a "--" in the expression to a "+" 
	 * @param expression
	 * @return 
	 */
	private static String minusMinusToPlus(String expression){
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

}