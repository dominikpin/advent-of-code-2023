package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File myObj = new File("day11\\input.txt");
        Scanner myReader = new Scanner(myObj);
        long sum = 0;
        ArrayList<String> space = new ArrayList<>();
        while (myReader.hasNextLine()) {
            space.add(myReader.nextLine());
        }
        sum = calculateAllDistancewithoutSpaceExpand(space);
        myReader.close();
        System.out.println(sum);
    }

    private static long calculateAllDistancewithoutSpaceExpand(ArrayList<String> space) {
        ArrayList<Integer> emptySpaceX = new ArrayList<>();
        for (int i = 0; i < space.size(); i++) {
            boolean isSpace = true;
            for (int j = 0; j < space.get(0).length(); j++) {
                if (space.get(i).charAt(j) != '.') {
                    isSpace = false;
                    break;
                }
            }
            if (isSpace) {
                emptySpaceX.add(i);
            }
        }
        ArrayList<Integer> emptySpaceY = new ArrayList<>();
        for (int i = 0; i < space.get(0).length(); i++) {
            boolean isSpace = true;
            for (int j = 0; j < space.size(); j++) {
                if (space.get(j).charAt(i) != '.') {
                    isSpace = false;
                    break;
                }
            }
            if (isSpace) {
                emptySpaceY.add(i);
            }
        }
        ArrayList<int[]> galaxiesCoords = new ArrayList<>();
        for (int i = 0; i < space.size(); i++) {
            for (int j = 0; j < space.get(0).length(); j++) {
                if (space.get(i).charAt(j) == '#') {
                    galaxiesCoords.add(new int[]{i, j});
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < galaxiesCoords.size(); i++) {
            for (int j = i + 1; j < galaxiesCoords.size(); j++) {
                if (galaxiesCoords.get(i)[1] > galaxiesCoords.get(j)[1]) {
                    sum += galaxiesCoords.get(i)[1] - galaxiesCoords.get(j)[1];
                    sum += checkEmptySpace(galaxiesCoords.get(j)[1], galaxiesCoords.get(i)[1] , emptySpaceY);
                } else {
                    sum += galaxiesCoords.get(j)[1] - galaxiesCoords.get(i)[1];
                    sum += checkEmptySpace(galaxiesCoords.get(i)[1], galaxiesCoords.get(j)[1] , emptySpaceY);
                    
                }
                sum += galaxiesCoords.get(j)[0] - galaxiesCoords.get(i)[0];
                sum += checkEmptySpace(galaxiesCoords.get(i)[0], galaxiesCoords.get(j)[0] , emptySpaceX);
            }
        }
        return sum;
    }

    private static int checkEmptySpace(int cord1, int cord2, ArrayList<Integer> emptySpace) {
        int counter = 0;
        for (int empty : emptySpace) {
            if (cord1 < empty && cord2 > empty) {
                counter++;
            }
        }
        return counter * 999999;
    }
}