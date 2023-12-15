package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part2 {

    private static Map<int[], Long> memoTable = new HashMap<>();

    public Part2() throws FileNotFoundException {
        File myObj = new File("day12\\input.txt");
        Scanner myReader = new Scanner(myObj);
        long sum = 0;
        while (myReader.hasNextLine()) {
            sum += analyzeString((myReader.nextLine()));
        }
        myReader.close();
        System.out.println(sum);
    }

    private static long analyzeString(String line) {
        String[] split = line.split(" ");
        int[] numbers = stringToIntArray(split[1].split(","));
        int[] springNumbers = new int[numbers.length * 5];
        String springsLine = "";
        for (int i = 0; i < 5; i++) {
            springsLine += split[0] + "?";
            for (int j = 0; j < numbers.length; j++) {
                springNumbers[i * numbers.length + j] = numbers[j];
            }
        }
        springsLine = springsLine.substring(0, springsLine.length()-1);
        long sum = calcualteNumberOfWays(springsLine, springNumbers, 0, 0, 0);
        memoTable.clear();
        return sum;
    }

    private static int[] stringToIntArray(String[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = Integer.parseInt(array[i]);
        }
        return newArray;
    }

    private static long calcualteNumberOfWays(String springsLine, int[] numbers, int i, int numbersI, int currentBroken) {
        for (Map.Entry<int[], Long> entry : memoTable.entrySet()) {
            int[] key = entry.getKey();
            if (Arrays.equals(key, new int[]{i, numbersI, currentBroken})) {
                return entry.getValue();
            }
        }
        long sum = 0;
        if (i == springsLine.length()) {
            if (numbersI == numbers.length && currentBroken == 0 || numbersI == numbers.length - 1 && currentBroken != 0 && numbers[numbersI] == currentBroken) {
                memoTable.put(new int[]{i, numbersI, currentBroken}, 1l);
                return 1;
            }
            memoTable.put(new int[]{i, numbersI, currentBroken}, 0l);
            return 0;
        }
        if (numbersI > numbers.length) {
            memoTable.put(new int[]{i, numbersI, currentBroken}, 0l);
            return 0;
        }
        if (springsLine.charAt(i) != '.') {
            sum += calcualteNumberOfWays(springsLine, numbers, i + 1, numbersI, currentBroken + 1);
        }
        if (springsLine.charAt(i) != '#') {
            if (currentBroken != 0) {
                if (numbersI < numbers.length && currentBroken == numbers[numbersI]) {
                    sum += calcualteNumberOfWays(springsLine, numbers, i + 1, numbersI + 1, 0);
                }
            } else {
                sum += calcualteNumberOfWays(springsLine, numbers, i + 1, numbersI, 0);
            }
        }
        memoTable.put(new int[]{i, numbersI, currentBroken}, sum);
        return sum;
    }
}