package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

    private static final String[] NUMBERS = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public Part2() throws FileNotFoundException {
        File myObj = new File("day01\\input.txt");
        Scanner myReader = new Scanner(myObj);
        int sum = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            sum += analyzeOneLine(data);
        }
        myReader.close();
        System.out.println(sum);
    }

    private static int analyzeOneLine(String line) {
        String firstNum = ""; 
        String lastNum = "";
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                if (firstNum == "") {
                    firstNum = line.substring(i, i+1);
                    lastNum = line.substring(i, i+1);
                } else {
                    lastNum = line.substring(i, i+1);
                }
            } else {
                String textNum = checkIfTextNumber(line, i);
                if (textNum != "") {
                    if (firstNum == "") {
                        firstNum = textNum;
                        lastNum = textNum;
                    } else {
                        lastNum = textNum;
                    }
                }
            }
        }
        int number = Integer.parseInt(firstNum + lastNum);
        return number;
    }

    private static String checkIfTextNumber(String line, int charNum) {
        String number = "";
        int maxCharNumLength = line.length() - charNum;
        for (int i = 0; i < NUMBERS.length; i++) {
            if (maxCharNumLength < NUMBERS[i].length()) {
                continue;
            }
            boolean isNumber = true;
            for (int j = 0; j < NUMBERS[i].length(); j++) {
                if (line.charAt(charNum+j) != NUMBERS[i].charAt(j)) {
                    isNumber = false;
                    break;
                }
            }
            if (isNumber == true) {
                number = Integer.toString(i + 1);
            }
        }
        return number;
    }
}