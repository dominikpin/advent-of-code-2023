package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File myObj = new File("day09\\input.txt");
        Scanner myReader = new Scanner(myObj);
        int sum = 0;
        while (myReader.hasNextLine()) {
            sum += extrapolateValue(myReader.nextLine());
        }
        myReader.close();
        System.out.println(sum);
    }

    private static int extrapolateValue(String line) {
        ArrayList<Integer> numbers = returnIntArray(line.split(" "));
        ArrayList<ArrayList<Integer>> extrapolated = new ArrayList<>();
        extrapolated.add(numbers);
        while (checkIfExtrapolatedTableNotSame(extrapolated.get(extrapolated.size() - 1))) {
            ArrayList<Integer> newArray = new ArrayList<>();
            ArrayList<Integer> oldArray = extrapolated.get(extrapolated.size() - 1);
            for (int i = 0; i < extrapolated.get(extrapolated.size() - 1).size() - 1; i++) {
                newArray.add(oldArray.get(i+1)-oldArray.get(i));
            }
            extrapolated.add(newArray);
        }
        int difference = 0;
        for (int i = extrapolated.size() - 1; i >= 0; i--) {
            difference += extrapolated.get(i).getLast();
        }
        return difference;
    }
    
    private static boolean checkIfExtrapolatedTableNotSame(ArrayList<Integer> table) {
        int number = table.get(0);
        for (int i = 1; i < table.size(); i++) {
            if (number != table.get(i)) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Integer> returnIntArray(String[] array) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            numbers.add(Integer.parseInt(array[i]));
        }
        return numbers;
    }
}