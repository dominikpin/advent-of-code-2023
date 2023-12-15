package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File myObj = new File("day06\\input.txt");
        Scanner myReader = new Scanner(myObj);
        long[] timeAndDistance = {Long.parseLong(myReader.nextLine().split(":")[1].replaceAll("\\s+","")), Long.parseLong(myReader.nextLine().split(":")[1].replaceAll("\\s+",""))};
        long sum = calculateNumberOfWays(timeAndDistance);
        myReader.close();
        System.out.println(sum);
    }

    private static long calculateNumberOfWays(long[] timeAndDistance) {
        long time = timeAndDistance[0];
        long distance = timeAndDistance[1];
        for (long j = time/2; j >= 0; j--) {
            if (j*(time-j) <= distance) {
                long optimalTime = time - (2*j) - 1;
                return optimalTime;
            }
        }
        return 0;
    }
}