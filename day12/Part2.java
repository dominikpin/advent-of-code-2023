package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File myObj = new File("day12\\test.txt");
        Scanner myReader = new Scanner(myObj);
        int sum = 0;
        while (myReader.hasNextLine()) {
            sum += analyzeString((myReader.nextLine()));
        }
        myReader.close();
        System.out.println(sum);
    }

    private static int analyzeString(String line) {
        String[] split = line.split(" ");
        int[] numbers = stringToIntArray(split[1].split(","));
        int sum = calcualteNumberOfWays1(split[0], numbers, 0, 0, 0);
        System.out.println(sum);
        // int damagedSprings = 0;
        // for (int i = 0; i < numbers.length; i++) {
        //     damagedSprings += numbers[i];
        // }
        // damagedSprings *= 5;
        // int[] springNumbers = new int[numbers.length * 5];
        // String springsLine = "";
        // for (int i = 0; i < 5; i++) {
        //     springsLine += split[0] + "?";
        //     for (int j = 0; j < numbers.length; j++) {
        //         springNumbers[i * numbers.length + j] = numbers[j];
        //     }
        // }
        // springsLine = springsLine.substring(0, springsLine.length()-1);
        // for (int i = 0; i < split[0].length(); i++) {
        //     if (split[0].charAt(i) == '#') {
        //         damagedSprings -= 5;
        //     }
        // }
        // int sum = calcualteNumberOfWays(damagedSprings, springNumbers, springsLine, 0);
        return sum;
    }

    private static int[] stringToIntArray(String[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = Integer.parseInt(array[i]);
        }
        return newArray;
    }

    private static int calcualteNumberOfWays1(String springsLine, int[] numbers, int i, int numbersI, int currentBroken) {
        int sum = 0;
        if (currentBroken > numbers[numbersI]) {
            return 0;
        }
        if (i == springsLine.length()) {
            if (numbersI >= numbers.length - 1) {
                return 1;
            }
            return 0;
        }
        if (springsLine.charAt(i) != '.') {
            sum += calcualteNumberOfWays1(springsLine, numbers, i + 1, numbersI, currentBroken + 1);
        }
        if (springsLine.charAt(i) != '#') {
            if (currentBroken != 0) {
                if (currentBroken == numbers[numbersI]) {
                    sum += calcualteNumberOfWays1(springsLine, numbers, i + 1, numbersI + 1, 0);
                }
                return 0;
            }
            sum += calcualteNumberOfWays1(springsLine, numbers, i + 1, numbersI, 0);
        }
        return sum;
    }

    private static int calcualteNumberOfWays(int damagedSprings, int[] numbers, String springsLine, int charIndexToReplace) {
        if (damagedSprings > 0 && !isDamagedSpringsPossible(numbers, springsLine)) {
            return 0;
        }
        if (damagedSprings == 0) {
            if (isDamagedSpringsCorrect(numbers, springsLine)) {
                return 1;
            }
            return 0;
        }
        if (charIndexToReplace == springsLine.length()) {
            return 0;
        }
        while (springsLine.charAt(charIndexToReplace) != '?') {
            charIndexToReplace++;
            if (charIndexToReplace == springsLine.length()) {
                return 0;
            }
        }
        StringBuilder stringBuilder = new StringBuilder(springsLine);
        stringBuilder.setCharAt(charIndexToReplace, '#');
        String modifiedString1 = stringBuilder.toString();
        stringBuilder.setCharAt(charIndexToReplace, '.');
        String modifiedString2 = stringBuilder.toString();

        int count1 = calcualteNumberOfWays(damagedSprings - 1, numbers, modifiedString1, charIndexToReplace + 1);
        int count2 = calcualteNumberOfWays(damagedSprings, numbers, modifiedString2, charIndexToReplace + 1);
        return count1 + count2;
    }

    private static boolean isDamagedSpringsCorrect(int[] numbers, String springsLine) {
        int counter = 0;
        for (int i = 0; i < springsLine.length(); i++) {
            if (springsLine.charAt(i) == '#') {
                if (counter == numbers.length) {
                    return false;
                }
                int counter1 = 0;
                while (i != springsLine.length() && springsLine.charAt(i) == '#') {
                    counter1++;
                    i++;
                }

                if (counter1 != numbers[counter]) {
                    return false;
                }
                counter++;
            }
        }
        return true;
    }
    
    private static boolean isDamagedSpringsPossible(int[] numbers, String springsLine) {
        int counter = 0;
        for (int i = 0; i < springsLine.length(); i++) {
            if (springsLine.charAt(i) == '?') {
                return true;
            }
            if (springsLine.charAt(i) == '#') {
                if (counter == numbers.length) {
                    return false;
                }
                int counter1 = 0;
                while (i != springsLine.length() && springsLine.charAt(i) == '#') {
                    counter1++;
                    i++;
                }
                if (counter1 != numbers[counter] && (i == springsLine.length() || springsLine.charAt(i) == '.')) {
                    return false;
                }
                if (counter1 > numbers[counter]) {
                    return false;
                }
                if (i == springsLine.length() || springsLine.charAt(i) == '?') {
                    return true;
                }
                counter++;
            }
        }
        return true;
    }
}