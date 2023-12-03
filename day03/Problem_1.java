package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem_1 { 

    public Problem_1() throws FileNotFoundException {
        File myObj = new File("day03\\input.txt");
        Scanner myReader = new Scanner(myObj);
        int sum = 0;
        String previousLine = "";
        String line = myReader.nextLine();;
        String nextLine = "";
        while (myReader.hasNextLine()) {
            nextLine = myReader.nextLine();
            sum += analyzeOneLine(previousLine, line, nextLine);
            previousLine = line;
            line = nextLine;
            nextLine = "";
        }
        sum += analyzeOneLine(previousLine, line, nextLine);
        myReader.close();
        System.out.println(sum);
    }

    private static int analyzeOneLine(String previousLine, String line, String nextLine) {
        int sum = 0;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                int numberStart = i;
                do {
                    i++;
                    if (line.length() == i) {
                        break;
                    }
                } while (Character.isDigit(line.charAt(i)));
                int numberEnd = i - 1;
                if (checkIfCharacterClose(previousLine, line, nextLine, numberStart, numberEnd)) {
                    sum += Integer.parseInt(line.substring(numberStart, numberEnd + 1));
                }
            }
        }
        return sum;
    }

    private static boolean checkIfCharacterClose(String prevLine, String line, String nextLine, int start, int end) {
        if (start == 0) {
            start++;
        }
        if (end == line.length() - 1) {
            end--;
        }
        if (isRandomSymbol(line.charAt(start-1)) || isRandomSymbol(line.charAt(end + 1))) {
            return true;
        }
        for (int i = start - 1; i <= end + 1; i++) {
            if (!prevLine.equals("")) {
                if (isRandomSymbol(prevLine.charAt(i))) {
                    return true;
                }
            }
            if (!nextLine.equals("")) {
                if (isRandomSymbol(nextLine.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isRandomSymbol(char char1) {
        return !(Character.isDigit(char1) || Character.compare(char1, '.') == 0);
    }
}