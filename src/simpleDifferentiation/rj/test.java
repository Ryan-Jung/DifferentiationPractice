package simpleDifferentiation.rj;
public class test {

	public static void main(String[] args) {
		String expression = "5x-(5+5x)^5";
		Expression ex = new Expression (expression);
		System.out.println(ex.getExpression());
//		expression = "A * B + C / D";
//		ex.setExpression(expression);
//		System.out.println(ex.getExpression());
//		expression = "(5+5x)^5 + 9x";
//		ex.setExpression(expression);
//		System.out.println(ex.getExpression());
		Calculator calc = new Calculator();
		expression = "5x,(5,5x,+)^5,-";
		System.out.print(calc.readParenTerm(expression, 3));

		
	}

}
