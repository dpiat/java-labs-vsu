import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<String> digits = new ArrayList<String>(
            Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",")
    );
    public static List<String> operands = new ArrayList<String>(
            Arrays.asList("^", "/", "*", "+", "-")
    );
    public static List<String> functions = new ArrayList<String>(
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String expression = scanner.nextLine();
            System.out.println(expression);
        }
    }

    public static double CalculateExpression(String expr)
    {
        expr = formatExpression(expr);
        ArrayList<String> expression = toStringArray(expr);

        List<String> symbols = new ArrayList<>();

        StringBuilder lastSymbol = new StringBuilder();
        String lastFunction = "";

        // считываем по символу
        for (int i = 0; i < expression.size(); i++) {
            if (brackets.contains(expression.get(i))) {
                if (!lastSymbol.toString().equals("")){
                    symbols.add(lastSymbol.toString());
                    lastSymbol = new StringBuilder();
                }
                symbols.add(expression.get(i));
            }
            else if (digits.contains(expression.get(i)) || (expression.get(i).equals(",") && !lastSymbol.toString().contains(","))){
                lastSymbol.append(expression.get(i));
            }
            else if(operands.contains(expression.get(i))) {
                if (lastSymbol.toString() != ""){
                    symbols.add(lastSymbol.toString());
                    lastSymbol = new StringBuilder();
                }

                if (symbols.size() >= 1 && operands.contains(symbols.get(symbols.size() - 1)) || symbols.size() == 0) {
                    String number = "";

                    switch (expression.get(i)) {
                        case "-": number += "-"; break;
                        case "+": number += "+"; break;
                    }

                    i++;
                    while (i < expression.size() && digits.contains(expression.get(i))) {
                        number += expression.get(i);
                        i++;
                    }

                    symbols.add(number);
                    i--;
                }
                else symbols.add(expression.get(i));
            } else {
                lastFunction += expression.get(i).toLowerCase();

                if (constants.contains(lastFunction)) {
                    symbols.add(constantsValues[lastFunction]);
                    lastFunction = "";
                }
                else if (functions.contains(lastFunction))
                {
                    int functionStart = i + 1;
                    int functionEnd = 0;
                    int bracketsSum = 1;

                    for (int j = functionStart + 1; j < expression.size(); j++)
                    {
                        if (expression.get(j) == "(") bracketsSum++;
                        if (expression.get(j) == ")") bracketsSum--;
                        if (bracketsSum == 0)
                        {
                            functionEnd = j;
                            i = functionEnd;
                            break;
                        }
                    }

                    char[] buffer = new char[functionEnd - functionStart - 1];
                    expression.CopyTo(functionStart + 1, buffer, 0, functionEnd - functionStart - 1);
                    String functionParametrs = new String(buffer);

                    if (lastFunction == "sqrt"){
                        var parametrs = GetParametrs(functionParametrs);
                        symbols.add(Math.Pow(CalculateExpression(parametrs[0]), 1 / CalculateExpression(parametrs[1])).ToString());
                    }

                    if (lastFunction == "log"){
                        var parametrs = GetParametrs(functionParametrs);
                        symbols.add(Math.Log(CalculateExpression(parametrs[0]), CalculateExpression(parametrs[1])).ToString());
                    }

                    if (lastFunction == "sin") symbols.add(Math.Sin(CalculateExpression(functionParametrs)).ToString());
                    if (lastFunction == "cos") symbols.add(Math.Cos(CalculateExpression(functionParametrs)).ToString());
                    if (lastFunction == "abs") symbols.add(Math.Abs(CalculateExpression(functionParametrs)).ToString());

                    lastFunction = "";
                }
            }
        }

        if (lastSymbol.toString() != ""){
            symbols.add(lastSymbol.toString());
            lastSymbol = new StringBuilder();
        }

        while (symbols.contains("(")) {
            int bracketsStart = 0;
            int bracketsEnd = 0;
            int bracketsSum = 0;

            for (int i = 0; i < symbols.size(); i++) {
                if (symbols.get(i) == "(") {
                    bracketsStart = i;
                    bracketsSum = 1;
                    break;
                }
            }

            for (int i = bracketsStart + 1; i < symbols.Count; i++) {
                if (symbols.get(i) == "(") bracketsSum++;
                if (symbols.get(i) == ")") bracketsSum--;
                if (bracketsSum == 0) {
                    bracketsEnd = i;
                    break;
                }
            }

            String bracketsExpression = "";
            for (int i = bracketsStart + 1; i < bracketsEnd; i++) bracketsExpression += symbols[i];

            symbols[bracketsStart] = CalculateExpression(bracketsExpression).ToString();
            symbols.RemoveRange(bracketsStart + 1, bracketsEnd - bracketsStart);
        }

        foreach(var j in operands) {
        var flagO = true;
        while (flagO){
            flagO = false;
            for (int i = 0; i < symbols.Count; i++){
                if (symbols[i] == j){
                    symbols[i - 1] = symbols[i - 1] + " " + symbols[i + 1] + " " + j;
                    symbols.RemoveRange(i, 2);

                    flagO = true;
                    break;
                }
            }
        }
    }

        List<String> result = new List<String>();

        String[] temp = symbols.get(0).Split(' ');

        for (int i = 0; i < temp.size(); i++) {
            if (operands.contains(temp[i])) {
                if (temp[i] == "^") {
                    result[result.size() - 2] = Math.pow(double.Parse(result[result.size() - 2]), double.Parse(result[result.size() - 1])).ToString();
                    result.RemoveRange(result.size() - 1, 1);
                }
                if (temp[i] == "+") {
                    result[result.size() - 2] = (double.Parse(result[result.size() - 2]) + double.Parse(result[result.size() - 1])).ToString();
                    result.RemoveRange(result.size() - 1, 1);
                }
                if (temp[i] == "-") {
                    result[result.size() - 2] = (double.Parse(result[result.size() - 2]) - double.Parse(result[result.size() - 1])).ToString();
                    result.RemoveRange(result.size() - 1, 1);
                }
                if (temp[i] == "*") {
                    result[result.size() - 2] = (double.Parse(result[result.size() - 2]) * double.Parse(result[result.size() - 1])).ToString();
                    result.RemoveRange(result.size() - 1, 1);
                }
                if (temp[i] == "/") {
                    result[result.size() - 2] = (double.Parse(result[result.size() - 2]) / double.Parse(result[result.size() - 1])).ToString();
                    result.RemoveRange(result.size() - 1, 1);
                }
            }
            else result.add(temp[i]);
        }

        return double.Parse(result[0]);
    }
}
