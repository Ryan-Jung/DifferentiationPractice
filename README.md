# Differentiation Practice
Differentiation Practice is a project designed to help practice and solve problems requiring simple differentiation. It can be used to find d/dx(f(x)) for simple functions. It simply takes in the expression converts the expression into a more easily evaluable form (postfix notation) and then differentiates that expression following simple rules for differentiation. Does not support any other variable other than 'x'.

---
#How to use
Download https://www.dropbox.com/s/hakihvl6clvnp6w/differentiation_rj_9_23_2016.jar?dl=0
and add jar to class path. Includes Symja (https://bitbucket.org/axelclk/symja_android_library/wiki/Home) a Java Computer Algebra Library.

# Sample Program:

```{Java}
import simpleDifferentiation.rj.Differentiation;
import simpleDifferentiation.rj.InvalidExpression;

public class Test{
	public static void main(String[] args){
		//provide expression
		Differentiation df = new Differentiation("5x^5 + 5");
		String result = "";
		try{
			result = df.getSolution();
		}catch(InvalidExpression){
			System.out.print("I broke");
		}
		
		System.out.println("This is the result " + result);
	}
}
```
```{Java}
 Output : This is the result 25x^4
```

# It can differentiate expressions that involve:
#### -The chain rule
#### -The product rule
#### -The sum rule
#### -The quotient rule
#### -The difference rule


---

# Things it can't do:

### - Differentiate expression with the multiplication or division occurring more than one time in a row. 
##### Examples: "5x * 5x^7 * (5x^-3 + 5x)^5" or "5x*5x^4/3x" or 5x/5x/5x
# 
### - Does not deal with unsimplified terms
#####  Examples: "25/5x^7" should be "5x^7" , "5^2 x^5" should be 25x^5 , and "5x^(11/5)" should be "5x^2.2"
# 
### - Does not differentiate expressions with parenthesis within parenthesis
##### Example: (5x+5*(5x-5))
# 
### - Does not differentiate trigonometric functions (sin ,cos, tan, etc.), exponential functions or logarithmic functions.



