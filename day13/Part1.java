package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File myObj = new File("day13\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> pattern;
        int sum = 0;
        while (myReader.hasNextLine()) {
            pattern = loadPattern(myReader);
            sum += analyzePattern(pattern);
        }
        myReader.close();
        System.out.println(sum);
    }

    private static ArrayList<String> loadPattern(Scanner myReader) {
        ArrayList<String> pattern = new ArrayList<String>();
        while (myReader.hasNextLine()) {
            String newLine = myReader.nextLine();
            if (newLine.isEmpty()) {
                break;
            }
            pattern.add(newLine);
        }
        return pattern;
    }

    private static int analyzePattern(ArrayList<String> pattern) {
        ArrayList<Integer> symmetryNumbers = new ArrayList<Integer>();
        for (int i = 0; i < pattern.get(0).length() - 1; i++) {
            boolean isSymmetrical = true;
            for (int j = 0; j <= i && pattern.get(0).length() != i + j + 1 && i - j > -1; j++) {
                if (pattern.get(0).charAt(i - j) != pattern.get(0).charAt(i + j + 1)) {
                    isSymmetrical = false;
                    break;
                }
            }
            if (isSymmetrical) {
                symmetryNumbers.add(i);
            }
        }
        for (int i : symmetryNumbers) {
            boolean isSymmetrical = true;
            for (String line : pattern) {
                for (int j = 0; j < line.length() && line.length() != i + j + 1 && i - j > -1; j++) {
                    if (line.charAt(i - j) != line.charAt(i + j + 1)) {
                        isSymmetrical = false;
                        break;
                    }
                }
                if (!isSymmetrical) {
                    break;
                }
            }
            if (isSymmetrical) {
                return i + 1;
            }
        }
        
        symmetryNumbers.clear();
        for (int i = 0; i < pattern.size() - 1; i++) {
            boolean isSymmetrical = true;
            for (int j = 0; j <= i && pattern.size() != i + j + 1 && i - j > -1; j++) {
                if (pattern.get(i - j).charAt(0) != pattern.get(i + j + 1).charAt(0)) {
                    isSymmetrical = false;
                    break;
                }
            }
            if (isSymmetrical) {
                symmetryNumbers.add(i);
            }
        }
        for (int i : symmetryNumbers) {
            boolean isSymmetrical = true;
            for (int j = 0; j < pattern.get(i).length() ; j++) {
                for (int k = 0; k < pattern.size() && pattern.size() != i + k + 1 && i - k > -1; k++) {
                    if (pattern.get(i - k).charAt(j) != pattern.get(i + k + 1).charAt(j)) {
                        isSymmetrical = false;
                        break;
                    }
                }
                if (!isSymmetrical) {
                    break;
                }
            }
            if (isSymmetrical) {
                return 100*(i + 1);
            }
        }
        return -1;
    }
}
