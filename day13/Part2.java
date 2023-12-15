package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File myObj = new File("day13\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> pattern;
        int sum = 0;
        while (myReader.hasNextLine()) {
            pattern = loadPattern(myReader);
            sum += switchOneValueAndAnalyze(pattern);
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
    
    private static int switchOneValueAndAnalyze(ArrayList<String> pattern) {
        int originalValidResult = analyzePattern(pattern, -1);
        for (int i = 0; i < pattern.size(); i++) {
            for (int j = 0; j < pattern.get(0).length(); j++) {
                String originalLine = pattern.get(i);
                char char1 = originalLine.charAt(j);
                if (char1 == '#') {
                    char1 = '.';
                } else {
                    char1 = '#';
                }
                StringBuilder stringBuilder = new StringBuilder(originalLine);
                stringBuilder.setCharAt(j, char1);
                String modifiedString = stringBuilder.toString();
                pattern.remove(i);
                pattern.add(i, modifiedString);
                int result = analyzePattern(pattern, originalValidResult);
                if (result != -1) {
                    return result;
                }
                pattern.remove(i);
                pattern.add(i, originalLine);
            }
        }
        return -1;
    }

    private static int analyzePattern(ArrayList<String> pattern, int originalValue) {
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
            if (isSymmetrical && originalValue != i + 1) {
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
            if (isSymmetrical && originalValue != 100*(i + 1)) {
                return 100*(i + 1);
            }
        }
        return -1;
    }
}
