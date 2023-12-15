package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public static final ArrayList<ArrayList<ArrayList<Long>>> seedsToLocation = new ArrayList<>();

    public Part2() throws FileNotFoundException {
        File myObj = new File("day05\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<Long> seeds = convertStringToLongArrayList(myReader.nextLine().split(":")[1].trim().split(" "));
        ArrayList<Long[]> seedsRange = new ArrayList<>();
        for (int i = 0; i < seeds.size(); i = i + 2) {
            seedsRange.add(new Long[]{seeds.get(i), seeds.get(i) + seeds.get(i + 1)});
        }
        analyzeAlmanac(myReader);
        myReader.close();
        System.out.println(analyzeSeedsToLocation(seedsRange));
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
    
    private static long analyzeSeedsToLocation(ArrayList<Long[]> seedsRanges) {
        for (ArrayList<ArrayList<Long>> data : seedsToLocation) {
            ArrayList<Long[]> newArray = new ArrayList<>();
            while (!seedsRanges.isEmpty()) {
                long start = seedsRanges.get(0)[0];
                long end = seedsRanges.get(0)[1];
                seedsRanges.remove(0);
                boolean notMapped = true;
                for (ArrayList<Long> line : data) {
                    long os = Math.max(start, line.get(1));
                    long oe = Math.min(end, line.get(1) + line.get(2));
                    if (os < oe) {
                        notMapped = false;
                        newArray.add(new Long[]{os - line.get(1) + line.get(0), oe - line.get(1) + line.get(0)});
                        if (os > start) {
                            seedsRanges.add(new Long[]{start, os});
                        }
                        if (end > oe) {
                            seedsRanges.add(new Long[]{oe, end});
                        }
                        break;
                    }
                }
                if (notMapped) {
                    newArray.add(new Long[]{start, end});
                }
            }
            seedsRanges = newArray;
        }
        long min = -1;
        for (Long[] trans : seedsRanges) {
            if (min == -1 || trans[0] < min) {
                min = trans[0];
            }
        }
        return min;
    }
}