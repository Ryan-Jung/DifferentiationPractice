package simpleDifferentiation.rj;
/**
 * For reading various properties of a term, such as a coefficient or power, or the term itself.
 * Should be used for postfix expressions with commas separating each term, and with terms 
 * using only the variable 'x'. 
 * @author Ryan Jung
 *
 */
public final class Reader {
	//no instances
	private Reader(){
	};
	/**
	 * Reads to the end of term which is indicated by a comma. If a parenthesis
	 * is encountered the parenthetical expression and its exponent will be considered
	 * a single term.
	 * @param expr - a valid postfix expression with commas separating each term
	 * @return the term.
	 */
	public static String readTerm(String expr, int i){
		String term = "";
		while(i < expr.length() && (isNotOperator(expr.charAt(i)) || isNegExponent(expr, i))){
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
		return "";
	}
	/**
	 * If a subtraction sign is encountered determines if the exponent is a 
	 * negative number
	 * @param expr the expression to read
	 * @param i an index for the String
	 * @return True if the index where are '-' is read is a negative exponent and false
	 * otherwise.
	 */
	private static boolean isNegExponent(String expr, int i){
		if(expr.charAt(i) == '-')
			return expr.charAt(i-1) == '^';
		else 
			return false;
	}
	/**
	 * @param charToCheck
	 * @return True if char is not operator false otherwise
	 */
	private static boolean isNotOperator(char charToCheck) {
		return !(charToCheck == '+' || charToCheck == '-' || charToCheck == '*' 
				|| charToCheck == '/');

	}
	private static String readParenTerm(String expr, int i){
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
	 * Reads the numbers until an 'x' is encountered. Only works with the 
	 * variable 'x'. If term is a constant will simply return the constant. 
	 * @param term 
	 * @return The coefficient of a term.
	 */
	public static String readCoefficient(String term){
		String coefficient = "";
		int i = 0;
		while(i < term.length() && term.charAt(i) != 'x'){
			coefficient += term.charAt(i);
			i++;
		}
		return coefficient;
	}
	/**
	 * Finds the exponent of a variable. Reads the numbers after the char 
	 * '^' as the exponent. If no numbers come after '^' returns an empty String. If a term
	 * contains the variable 'x' but no exponent is explicitly stated this method
	 * will return "1" because "x^1 = x". The term must be in its simplest form in order to work properly.
	 * ie. "5^2x^5" should be written as "25x^5".
	 * @param term
	 * @return the exponent
	 */
	public static String readPower(String term){
		String power = "";
		int i = 0;
		if(term.contains("^")){
			i = term.indexOf("^") + 1;
			
			while(i < term.length()){
				power += term.charAt(i);
				i++;
			}
		 //if term is "x" the power is '1'	
		}else if(term.contains("x") && term.indexOf('x') == term.length()-1){
			return "1";
		}
		return power;
	}
	/**
	 * Turns a String containing only numbers into a double. An empty String
	 * is considered to be 0. The number must be in its simplest form in order 
	 * to be converted properly ie. "5^2" should be written as "25".
	 * @param number 
	 * @return The double value of a number in a String.
	 */
	public static double stringToDouble(String number){
		if(!number.equals("")){
			return Double.parseDouble(number);
		}else{
			return 0;
		}
	}
	
	
}
