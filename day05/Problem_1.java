package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem_1 {

    public static final ArrayList<ArrayList<ArrayList<Long>>> seedsToLocation = new ArrayList<>();

    public Problem_1() throws FileNotFoundException {
        File myObj = new File("day05\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<Long> seeds = convertStringToLongArrayList(myReader.nextLine().split(":")[1].trim().split(" "));
        analyzeAlmanac(myReader);
        myReader.close();
        System.out.println(analyzeSeedsToLocation(seeds));
    }

    private static void analyzeAlmanac(Scanner myReader) {
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (line.contains(":")) {
                ArrayList<ArrayList<Long>> data = new ArrayList<>();
                while (myReader.hasNextLine()) {
                    line = myReader.nextLine();
                    if (line.isBlank()) {
                        break;
                    }
                    data.add(convertStringToLongArrayList(line.split(" ")));
                }
                seedsToLocation.add(data);
            }
        }
    }

    private static ArrayList<Long> convertStringToLongArrayList(String[] array) {
        ArrayList<Long> newArray = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            newArray.add(Long.parseLong(array[i]));
        }
        return newArray;
    }
    
    private static long analyzeSeedsToLocation(ArrayList<Long> seeds) {
        long min = -1;
        for (int i = 0; i < seeds.size(); i++) {
            long seed = seeds.get(i);
            for (ArrayList<ArrayList<Long>> data : seedsToLocation) {
                for (ArrayList<Long> line : data) {
                    if (line.get(1) <= seed && line.get(1) + line.get(2) > seed) {
                        seed = line.get(0) - line.get(1) + seed;
                        //System.out.println(seed);
                        break;
                    }
                }
            }
            if (min == -1 || min > seed) {
                min = seed;
            }
        }
        return min;
    }
}