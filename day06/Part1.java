package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File myObj = new File("day06\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<ArrayList<Integer>> timeAndDistance = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            timeAndDistance.add(stringToIntArray(myReader.nextLine().split(":")[1].trim().split(" ")));
        }
        int sum = calculateNumberOfWays(timeAndDistance);
        myReader.close();
        System.out.println(sum);
    }

    private static ArrayList<Integer> stringToIntArray(String[] stringArray) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 0; i < stringArray.length; i++) {
            if (!stringArray[i].isEmpty()) {
                intArray.add(Integer.parseInt(stringArray[i]));
            }
        }
        return intArray;
    }

    private static int calculateNumberOfWays(ArrayList<ArrayList<Integer>> timeAndDistance) {
        int sum = 0;
        for (int i = 0; i < timeAndDistance.get(0).size(); i++) {
            int time = timeAndDistance.get(0).get(i);
            int distance = timeAndDistance.get(1).get(i);
            for (int j = time/2; j >= 0; j--) {
                if (j*(time-j) <= distance) {
                    int optimalTime = time - (2*j) - 1;
                    if (sum == 0) {
                        sum = optimalTime;
                    } else {
                        sum *= optimalTime;
                    }
                    break;
                }
            }
            
        }
        return sum;
    }
}
