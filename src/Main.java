import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Hello! The console calculator welcomes you!\n" +
                "I can to perform addition (a + b), subtraction (a - b), multiplication (a * b) and division (a / b) operations.\n" +
                "BUT ONLY WITH TWO NUMBERS FROM 1 TO 10!\n"+
                "Have a nice count!");
        System.out.println("Enter the data:");
        String text = calc(console.nextLine());
        System.out.println(text);
    }

    public static String calc(String input) throws IOException
    {
        int result;
        input = input.trim();
        if (isArabic(input))
        {
            String operation = readingOperation(input);
            String[] arabicWords = cleanWords(input, operation);
            int[] num = {Integer.parseInt(arabicWords[0]), Integer.parseInt(arabicWords[1])};
            for (int x: num)
            {
                if (isInRange(x))
                    throw new IOException("I only know the numbers from 1 to 10!");
            }

            result = DoOperation(num[0], num[1], operation);
            return String.valueOf(result);
        } else if (isRoman(input))
        {
            String operation = readingOperation(input);
            String[] romanWords = cleanWords(input, operation);
            int[] num = {RomanNumerals.romanToArabic(romanWords[0]), RomanNumerals.romanToArabic(romanWords[1])};
            for (int x: num)
            {
                if (isInRange(x))
                    throw new IOException("I only know the numbers from 1 to 10!");
            }
            result = DoOperation(num[0], num[1], operation);
            if (result > 0)
            {
                return  RomanNumerals.arabicToRoman(result);
            } else
                throw new IOException("Negative Roman numbers don't exist");
        } else {
            throw new IOException("I can't understand what you entered");
        }
    }




    private static int DoOperation(int firstOperand, int secondOperand, String operation)
    {
        switch (operation) {
            case "+" -> firstOperand += secondOperand;
            case "-" -> firstOperand -= secondOperand;
            case "*" -> firstOperand *= secondOperand;
            case "/" -> firstOperand /= secondOperand;
        }
        return  firstOperand;
    }

    private static boolean isInRange(int a)
    {
        return (a > 10 || a < 1);
    }
    private  static  String[] cleanWords(String input, String splitString)
    {
        String[] words = input.split("\\"+splitString);
        for (int i = 0; i < words.length; i++)
            words[i] = words[i].trim();
        return words;
    }
    private  static  String readingOperation (String input)
    {
        String[] operators = {"+", "-", "*", "/"};
        String operation = "";
        for (String str: operators)
            if (input.contains(str))
            {
                operation = str;
            }
        return operation;
    }

    private  static boolean isArabic(String input)
    {
        Pattern patternArabic = Pattern.compile("^\\d+(\\s+)?[+/*-](\\s+)?\\d+$");
        Matcher matcher = patternArabic.matcher(input);
        return matcher.matches();

    }

    private static boolean isRoman(String input)
    {
        Pattern patternRoman = Pattern.compile("^[IiVvXx]+(\\s+)?[+/*-](\\s+)?[IiVvXx]+$");
        Matcher matcher = patternRoman.matcher(input);
        return  matcher.matches();
    }

}