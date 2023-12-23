package day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {
    
    public Part2() throws FileNotFoundException {
        File myObj = new File("day18\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<long[]> points = new ArrayList<>();
        points.add(new long[]{0, 0});
        long sum = 0;
        long edges = getAllPoints(myReader, points);
        sum += calculateGridSize(points, edges);
        myReader.close();
        System.out.println(sum);
    }

    private static long getAllPoints(Scanner myReader, ArrayList<long[]> points) {
        long counter = 0;
        while (myReader.hasNextLine()) {
            long newPoint[] = points.getLast().clone();
            String line = myReader.nextLine();
            String stringNumber = line.split(" ")[2];
            int i = Integer.parseInt(stringNumber.substring(2, stringNumber.length() - 2), 16);
            counter += i;
            switch (stringNumber.charAt(stringNumber.length() - 2)) {
                case '0':
                    newPoint[1] += i;
                    break;
                case '1':
                    newPoint[0] += i;
                    break;
                case '2':
                    newPoint[1] -= i;
                    break;
                case '3':
                    newPoint[0] -= i;
                    break;
            }
            points.add(newPoint);
        }
        return counter;
    }

    private static long calculateGridSize(ArrayList<long[]> points, long edges) {
        long sum = 0;
        for (int i = 0; i < points.size(); i++) {
            if (i == 0) {
                sum += points.get(i)[0] * (points.getLast()[1] - points.get(i + 1)[1]);
            } else if (i == points.size() - 1) {
                sum += points.get(i)[0] * (points.get(i - 1)[1] - points.getFirst()[1]);
            } else {
                sum += points.get(i)[0] * (points.get(i - 1)[1] - points.get(i + 1)[1]);
            }
        }
        if (sum < 0) {
            sum *= -1;
        }
        sum = (sum - edges) / 2 + 1;
        sum += edges;
        return sum;
    }
}
