import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Parser - это парсер математических выражений. Выражение может содержать числа,
 * знаки операций, скобки. В случае, если выражение записано корректно, вычисляет
 * значение, в противном случае — выводит сообщение об ошибке.
 * @author  Piataikin D.
 */
public final class Parser {

    private Parser() {}

    /**
     * Список допустимых чисел
     */
    private static List<String> digits = new ArrayList<String>(
            Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",")
    );
    /**
     * Список операций
     */
    private static List<String> simpleFunctions = new ArrayList<String>(
            Arrays.asList("^", "/", "*", "+", "-")
    );
    /**
     * Список функий. Если необходимы другие, то нужно дописать название функции в этот массив и
     * добавить условие в {@link #calculateExpression(String) calculateExpression}
     */
    private static List<String> Functions = new ArrayList<String>(
            Arrays.asList("sqrt", "sin", "cos")
    );
    /**
     * Список скобок
     */
    private static List<String> brackets = new ArrayList<String>(
            Arrays.asList("(", ")")
    );

    /**
     * Убирает лишние пробелы.
     */
    private static String formatExpression(String expression) {
        return expression.replaceAll(" ", "");
    }

    private static String getStringSymbol(char c) {
        return Character.toString(c);
    }

    /**
     * Делает из выражение массив символов
     */
    private static ArrayList<String> toStringArray(String expression) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            arrayList.add(getStringSymbol(expression.charAt(i)));
        }
        return arrayList;
    }

    /**
     * Основная функция, которая вычисляет значение выражения
     */
    public static double calculateExpression(String expr) throws Exception {

        ArrayList<String> expression = toStringArray("(" + formatExpression(expr) + ")");

        // стек для операндов (чисел)
        Stack<Double> operands = new Stack<>();
        // стек для операций
        Stack<String> functions = new Stack<>();

        // Мы будем идти слева на право, добавляя операнды в один стек,
        // а операции в другой. При каждом добавлении новой операции мы
        // будем пытаться вытолкнуть из стека старые, руководствуясь
        // приоритетами операций.
        int pos = 0;
        String token;
        String prevToken = "";
        String number = "";
        String function = "";
        do {
            token = expression.get(pos);

            // для чтения чисел, состоящих из более одной цифры
            if (prevToken.equals("(") && token.equals("-")) {
                operands.push(0.0);
            }
            if (digits.contains(token)) {
                number += token;
            }
            else if (simpleFunctions.contains(token) || brackets.contains(token)) {
                if (!number.equals("")) {
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

                    // можно добавить сплитер, который вернет аргументы функции
                    // Func(x1, x2,..., xn), split("x1, x2,..., xn") -> ["x1", "x2",..., "xn"]
                    String functionParameter = expr.substring(functionBracketStart, functionBracketEnd-1);

                    // работает только для функций с 1 аргументом
                    if (function.equals("sqrt")) {
                        double res = Math.sqrt(calculateExpression( functionParameter ));
                        operands.add(res);
                    }

                    if (function.equals("sin")) {
                        double res = Math.sin(calculateExpression( functionParameter ));
                        operands.add(res);
                    }

                    if (function.equals("cos")) {
                        double res = Math.cos(calculateExpression( functionParameter ));
                        operands.add(res);
                    }
                }
            }
            pos++;
            prevToken = token;
        }
        while (token != null && pos + 1 <= expression.size());
        if (operands.size() > 1 || functions.size() > 0)
            throw new Exception("Ошибка в разборе выражения");
        return operands.pop();
    }

    /**
     * Функция, которая выталкивает из стека два операнда, выполняет операцию над ними,
     * кладет результат в стек с операндами
     */
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

    /**
     * Получаем приоритет операции
     */
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
