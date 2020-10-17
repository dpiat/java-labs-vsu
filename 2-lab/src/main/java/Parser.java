import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * The class {@code Math} contains methods for performing basic
 * numeric operations such as the elementary exponential, logarithm,
 * square root, and trigonometric functions.
 *
 * <p>Unlike some of the numeric methods of class
 * {@code StrictMath}, all implementations of the equivalent
 * functions of class {@code Math} are not defined to return the
 * bit-for-bit same results.  This relaxation permits
 * better-performing implementations where strict reproducibility is
 * not required.
 *
 * <p>By default many of the {@code Math} methods simply call
 * the equivalent method in {@code StrictMath} for their
 * implementation.  Code generators are encouraged to use
 * platform-specific native libraries or microprocessor instructions,
 * where available, to provide higher-performance implementations of
 * {@code Math} methods.  Such higher-performance
 * implementations still must conform to the specification for
 * {@code Math}.
 *
 * <p>The quality of implementation specifications concern two
 * properties, accuracy of the returned result and monotonicity of the
 * method.  Accuracy of the floating-point {@code Math} methods is
 * measured in terms of <i>ulps</i>, units in the last place.  For a
 * given floating-point format, an {@linkplain #ulp(double) ulp} of a
 * specific real number value is the distance between the two
 * floating-point values bracketing that numerical value.  When
 * discussing the accuracy of a method as a whole rather than at a
 * specific argument, the number of ulps cited is for the worst-case
 * error at any argument.  If a method always has an error less than
 * 0.5 ulps, the method always returns the floating-point number
 * nearest the exact result; such a method is <i>correctly
 * rounded</i>.  A correctly rounded method is generally the best a
 * floating-point approximation can be; however, it is impractical for
 * many floating-point methods to be correctly rounded.  Instead, for
 * the {@code Math} class, a larger error bound of 1 or 2 ulps is
 * allowed for certain methods.  Informally, with a 1 ulp error bound,
 * when the exact result is a representable number, the exact result
 * should be returned as the computed result; otherwise, either of the
 * two floating-point values which bracket the exact result may be
 * returned.  For exact results large in magnitude, one of the
 * endpoints of the bracket may be infinite.  Besides accuracy at
 * individual arguments, maintaining proper relations between the
 * method at different arguments is also important.  Therefore, most
 * methods with more than 0.5 ulp errors are required to be
 * <i>semi-monotonic</i>: whenever the mathematical function is
 * non-decreasing, so is the floating-point approximation, likewise,
 * whenever the mathematical function is non-increasing, so is the
 * floating-point approximation.  Not all approximations that have 1
 * ulp accuracy will automatically meet the monotonicity requirements.
 *
 * <p>
 * The platform uses signed two's complement integer arithmetic with
 * int and long primitive types.  The developer should choose
 * the primitive type to ensure that arithmetic operations consistently
 * produce correct results, which in some cases means the operations
 * will not overflow the range of values of the computation.
 * The best practice is to choose the primitive type and algorithm to avoid
 * overflow. In cases where the size is {@code int} or {@code long} and
 * overflow errors need to be detected, the methods {@code addExact},
 * {@code subtractExact}, {@code multiplyExact}, {@code toIntExact},
 * {@code incrementExact}, {@code decrementExact} and {@code negateExact}
 * throw an {@code ArithmeticException} when the results overflow.
 * For the arithmetic operations divide and absolute value, overflow
 * occurs only with a specific minimum or maximum value and
 * should be checked against the minimum or maximum as appropriate.
 *
 * @author  Piataikin D.
 */
public final class Parser {

    private Parser() {}

    private static List<String> digits = new ArrayList<String>(
            Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",")
    );
    private static List<String> simpleFunctions = new ArrayList<String>(
            Arrays.asList("^", "/", "*", "+", "-")
    );
    private static List<String> Functions = new ArrayList<String>(
            Arrays.asList("sqrt", "sin", "cos")
    );
    private static List<String> brackets = new ArrayList<String>(
            Arrays.asList("(", ")")
    );

    private static String formatExpression(String expression) {
        return expression.replaceAll(" ", "");
    }

    private static String getStringSymbol(char c) {
        return Character.toString(c);
    }

    private static ArrayList<String> toStringArray(String expression) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            arrayList.add(getStringSymbol(expression.charAt(i)));
        }
        return arrayList;
    }

    public static double CalculateExpression(String expr) throws Exception {

        ArrayList<String> expression = toStringArray("(" + formatExpression(expr) + ")");

        Stack<Double> operands = new Stack<>();
        Stack<String> functions = new Stack<>();


        int pos = 0;
        String token;
        String number = "";
        String function = "";
        String prevToken;
        do {
            token = expression.get(pos);

            if (digits.contains(token)) {
                number += token;
                //operands.push(Double.parseDouble(token));
            }
            else if (simpleFunctions.contains(token) || brackets.contains(token)) {
                if (number != "") {
                    operands.push(Double.parseDouble(number));
                    number = "";
                }

                if (token.equals(")")) {
                    while (functions.size() > 0 && !functions.peek().equals("("))
                        popFunction(operands, functions);
                    functions.pop();
                }
                else {
                    while (canPop(token, functions))
                        popFunction(operands, functions);
                    functions.push(token);
                }
            }
            else {
                function += token;
                if (Functions.contains(function)) {
                    int functionBracketStart = pos + 1;
                    int functionBracketEnd = 0;
                    int bracketsSum = 1;

                    for (int i = functionBracketStart + 1; i < expression.size(); i++) {
                        if (expression.get(i).equals("(")) bracketsSum++;
                        if (expression.get(i).equals(")")) bracketsSum--;
                        if (bracketsSum == 0) {
                            functionBracketEnd = i;
                            pos = functionBracketEnd;
                            break;
                        }
                    }

                    String functionParameter = expr.substring(functionBracketStart, functionBracketEnd-1);

                    if (function.equals("sqrt")) {
                        double res = Math.sqrt(CalculateExpression( functionParameter ));
                        operands.add(res);
                    }

                    if (function.equals("sin")) {
                        double res = Math.sin(CalculateExpression( functionParameter ));
                        operands.add(res);
                    }

                    if (function.equals("cos")) {
                        double res = Math.cos(CalculateExpression( functionParameter ));
                        operands.add(res);
                    }
                }
            }
            prevToken = token;
            pos++;
        }
        while (token != null && pos + 1 <= expression.size());
        if (operands.size() > 1 || functions.size() > 0)
            throw new Exception("Ошибка в разборе выражения");
        return operands.pop();
    }

    private static void popFunction(Stack<Double> operands, Stack<String> functions) {
        double b = operands.pop();
        double a = operands.pop();
        switch (functions.pop()) {
            case "+" :
                operands.push(a + b);
                break;
            case "-" :
                operands.push(a - b);
                break;
            case "*" :
                operands.push(a * b);
                break;
            case "/" :
                operands.push(a / b);
                break;
            case "^":
                operands.push(Math.pow(a, b));
        }
    }

    private static boolean canPop(String op1, Stack<String> functions) throws Exception {
        if (functions.size() == 0)
            return false;
        int p1 = getPriotity(op1);
        int p2 = getPriotity(functions.peek());

        return p1 >= 0 && p2 >= 0 && p1 >= p2;
    }

    private static int getPriotity(String op) throws Exception {
        switch (op) {
            case "(":
                return -1;
            case "^":
                return 0;
            case "*":
                return 1;
            case "/":
                return 1;
            case "+":
                return 2;
            case "-":
                return 2;
            default:
                throw new Exception("Недопустимая операция");
        }
    }
}
