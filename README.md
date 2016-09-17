# Differentiation Practice
Differentiate Practice is a project designed to help practice and solve problems requiring simple differentiation. It simply takes in the expression
converts the expression into a more easily evaluable form (postfix) and then
differentiates that expression.

# How to use:
### Enter the expression you would like to evaluate or have one randomly generated for you like so:
```{Java}
//Generate random expression
Expression expression = new Expression();
//Enter an expression.
Expression expression = new Expression("5x^7 + 2131x * (5x + 381)");
```
###### Note: Expressions should only contain the variable 'x'. Expressions with variables in the exponent will not be calculated correctly.

### Then calcuate the derivative by providing the postfix expression generated by Expression:
```{Java}
String answer = Calculator.differentiate(expression.getPostFixExpression);
```
###### Note: The answer will not be completely simplified.

#### Sample Output
```{Java}

```

# It can differentiate expressions that involve:
#### -The chain rule
#### -The product rule
#### -The sum rule
#### -The quotient rule
#### -The difference rule