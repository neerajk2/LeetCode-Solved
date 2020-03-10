import java.util.Stack;

public class GitAss {

    public int evaluateExpression(String expression)
    {
        char[] chars = expression.toCharArray();

        Stack<Integer> operand = new Stack<Integer>();
        Stack<Character> operator = new Stack<Character>();

        for (int i = 0; i < chars.length; i++)
        {
            // Current chars is a whitespace, skip it
            if (chars[i] == ' ')
                continue;

            // Current chars is a number, push it to stack for numbers
            if (chars[i] >= '0' && chars[i] <= '9')
            {
                StringBuffer num = new StringBuffer();
                // There may be more than one digits in number
                while (i < chars.length && chars[i] >= '0' && chars[i] <= '9') {
                    num.append(chars[i++]);
                }
                operand.push(Integer.parseInt(num.toString()));
            }

            else if (chars[i] == '(')
                operator.push(chars[i]);

            // Closing brace encountered, solve entire brace
            else if (chars[i] == ')')
            {
                while (operator.peek() != '(')
                    operand.push(applyOp(operator.pop(), operand.pop(), operand.pop()));
                operator.pop();
            }

            // Current chars is an operator.
            else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/' || chars[i] == '^')
            {
                // While top of 'operator' has same or greater precedence to current
                // chars, which is an operator. Apply operator on top of 'operator'
                // to top two elements in operand stack
                while (!operator.empty() && precedence(chars[i]) <= precedence(operator.peek()))
                    operand.push(applyOp(operator.pop(), operand.pop(), operand.pop()));

                // Push current chars to 'operator'.
                operator.push(chars[i]);
            }
        }

        // Entire expression has been parsed, apply remaining operators
        // on remaining operands in stacks.
        while (!operator.empty())
            operand.push(applyOp(operator.pop(), operand.pop(), operand.pop()));

        // Top of 'operand' contains result, return it
        return operand.pop();
    }

    // Returns the precedence of each operator,
    // relative to another.
    private int precedence(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;

    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    private int applyOp(char op, int b, int a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
            case '^':
                return (int)Math.pow(a,b);
        }
        return 0;
    }

    // Driver method to test above methods
    public static void main(String[] args)
    {
        GitAss gas = new GitAss();
        System.out.println(gas.evaluateExpression("10 + 2 * 6"));
        System.out.println(gas.evaluateExpression("100 * 2 + 12"));
        System.out.println(gas.evaluateExpression("100 * 2 ^ 12"));
        System.out.println(gas.evaluateExpression("100 + 2 ^ 3 - 12"));
        System.out.println(gas.evaluateExpression("100 * ( 2 + 12 )"));
        System.out.println(gas.evaluateExpression("100 * ( 2 + 12 ) / 14"));
    }

}
