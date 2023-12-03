package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem_2 { 

    public Problem_2() throws FileNotFoundException {
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
            if (Character.compare(line.charAt(i), '*') == 0) {
                sum += checkIfNumbersClose(previousLine, line, nextLine, i);
            }
        }
        return sum;
    }

    private static int checkIfNumbersClose(String prevLine, String line, String nextLine, int location) {
        int sum = 0;
        int counter = 0;
        if (location > 0 && Character.isDigit(line.charAt(location - 1))) {
            counter++;
            sum = addOrMultiply(sum, findNumberLeft(location, line));
        }
        if (location < line.length() - 1 && Character.isDigit(line.charAt(location + 1))) {
            counter++;
            sum = addOrMultiply(sum, findNumberRight(location, line));
        }
        if (!prevLine.equals("")) {
            if (Character.isDigit(prevLine.charAt(location))) {
                counter++;
                sum = addOrMultiply(sum, findNumberMiddle(location, prevLine));
            } else {
                if (location > 0 && Character.isDigit(prevLine.charAt(location - 1))) {
                    counter++;
                    sum = addOrMultiply(sum, findNumberLeft(location, prevLine));
                }
                if (location < line.length() - 1 && Character.isDigit(prevLine.charAt(location + 1))) {
                    counter++;
                    sum = addOrMultiply(sum, findNumberRight(location, prevLine));
                }
            }
            
        }
        if (!nextLine.equals("")) {
            if (Character.isDigit(nextLine.charAt(location))) {
                counter++;
                sum = addOrMultiply(sum, findNumberMiddle(location, nextLine));
            } else {
                if (location > 0 && Character.isDigit(nextLine.charAt(location - 1))) {
                    counter++;
                    sum = addOrMultiply(sum, findNumberLeft(location, nextLine));
                }
                if (location < line.length() - 1 && Character.isDigit(nextLine.charAt(location + 1))) {
                    counter++;
                    sum = addOrMultiply(sum, findNumberRight(location, nextLine));
                }
            }
        }
        if (counter == 2) {
            return sum;
        }
        return 0;
    }

    private static int findNumberLeft(int location, String line) {
        int i = location - 2;
        do {
            if (i < 0) {
                i--;
                break;
            }
        } while (Character.isDigit(line.charAt(i--)));
        return Integer.parseInt(line.substring(i + 2, location));
    }

    private static int findNumberRight(int location, String line) {
        int i = location + 2;
            do {
                if (i > line.length() - 1) {
                    i++;
                    break;
                }
            } while (Character.isDigit(line.charAt(i++)));
        return Integer.parseInt(line.substring(location + 1, i - 1));
    }

    private static int findNumberMiddle(int location, String line) {
        int i = location - 1;
        do {
            if (i < 0) {
                i--;
                break;
            }
        } while (Character.isDigit(line.charAt(i--)));
        int start = i + 2;
        i = location + 1;
        do {
            if (i > line.length() - 1) {
                i++;
                break;
            }
        } while (Character.isDigit(line.charAt(i++)));
        int end = i - 1;
        return Integer.parseInt(line.substring(start, end));
    }

    private static int addOrMultiply(int sum, int a) {
        if (sum == 0) {
            return a;
        }
        return sum*a;
    }
}