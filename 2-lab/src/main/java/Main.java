import java.util.*;

public class Main {
    public static List<String> digits = new ArrayList<String>(
            Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",")
    );
    public static List<String> simpleFunctions = new ArrayList<String>(
            Arrays.asList("^", "/", "*", "+", "-")
    );
    public static List<String> Functions = new ArrayList<String>(
            Arrays.asList("sqrt", "sin", "cos", "log", "abs")
    );
    public static List<String> brackets = new ArrayList<String>(
            Arrays.asList("(", ")")
    );
    public static List<String> constants = new ArrayList<String>(
            Arrays.asList("pi")
    );

    public static String formatExpression(String expression) {
        return expression.replaceAll(" ", "");
    }

    public static String getStringSymbol(char c) {
        return Character.toString(c);
    }

    public static ArrayList<String> toStringArray(String expression) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            arrayList.add(getStringSymbol(expression.charAt(i)));
        }
        return arrayList;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(CalculateExpression("sqrt(sqrt(81))"));
    }

    public static double CalculateExpression(String expr) throws Exception {
        ArrayList<String> expression = toStringArray("(" + expr + ")");

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
                        // получить параметры
                        // вычислить значение функции
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
