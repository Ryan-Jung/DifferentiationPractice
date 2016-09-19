# Differentiation Practice
Differentiation Practice is a project designed to help practice and solve problems requiring simple differentiation. It can be used to find d/dx(f(x)) for simple functions. It simply takes in the expression converts the expression into a more easily evaluable form (postfix notation) and then differentiates that expression following simple rules for differentiation. 

---
# Requires the use of Symja (https://bitbucket.org/axelclk/symja_android_library/wiki/) for simplifying expressions. Download https://bitbucket.org/axelclk/symja_android_library/downloads/symja_java8-2016-07-24.zip and add to the build bath

# Sample Program:
### Enter the expression you would like to evaluate or have one randomly generated for you like so:
```{Java}
import simpleDifferentiation.rj.*;

//Generate random expression
Expression expression1 = new Expression();
//Enter an expression.
Expression expression2 = new Expression("5x^7 + 2131x * (5x + 381)");
```
###### Note: Expressions should only contain the variable 'x'. Expressions with variables in the exponent will not be calculated correctly.

### Then create a Calculator for the given Expression and call differentiate():
```{Java}
Calculator calculator1 = new Calculator(expression1);
Calculator calculator2 = new Calculator(expression2);
String answerToExpression1 = "";
String answerToExpression2 = "";
//Throws an InvalidExpression exception if there are problems differentiating.
try{
    answerToExpression1 = calculator1.differentiate();
    answerToExpression2 = calculator2.differentiate();
}catch(InvalidExpression iex){
    System.out.println(iex.getMessage());
}
```
###### Note: The answer will not be completely simplified.
### Then you can simplify your answer
```{Java}
String simplifiedAnswer1 = Simplifier.simplify(answerToExpression1);
String simplifiedAnswer2 = Simplifier.simplify(answerToExpression2);
```
---


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



