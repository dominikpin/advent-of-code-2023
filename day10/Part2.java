package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {

    private static final char[] LEFT = {'-', 'F', 'L'};
    private static final char[] RIGHT = {'-', '7', 'J'};
    private static final char[] UP = {'|', 'F', '7'};
    private static final char[] DOWN = {'|', 'J', 'L'};
    private static final ArrayList<char[]> directions = new ArrayList<>();
    static {
        directions.add(LEFT);
        directions.add(RIGHT);
        directions.add(UP);
        directions.add(DOWN);
    }

    public Part2() throws FileNotFoundException {
        File myObj = new File("day10\\input.txt");
        Scanner myReader = new Scanner(myObj);
        int sum = 0;
        ArrayList<String> pipes = new ArrayList<>();
        while (myReader.hasNextLine()) {
            pipes.add(myReader.nextLine());
        }
        ArrayList<String> newPipes = analyzePipes(pipes);
        sum = countSpacesBetweenPipes(newPipes);
        myReader.close();
        System.out.println(sum);
    }

    private static ArrayList<String> analyzePipes(ArrayList<String> pipes) {
        ArrayList<String> newPipes = new ArrayList<>();
        for (int i = 0; i < pipes.size(); i++) {
            newPipes.add(".".repeat(pipes.get(0).length()));
        }
        int[] start = new int[2];
        for (int i = 0; i < pipes.size(); i++) {
            for (int j = 0; j < pipes.get(0).length(); j++) {
                if (pipes.get(i).charAt(j) == 'S') {
                    start[0] = i;
                    start[1] = j;
                    break;
                }
            }
        }
        int[] next = new int[2];
        if (checkDirection(UP, start, pipes, next, -1, 0) && 
            checkDirection(DOWN, start, pipes, next, 1, 0) && 
            checkDirection(LEFT, start, pipes, next, 0, -1) && 
            checkDirection(RIGHT, start, pipes, next, 0, 1));
        int[] before = {start[0], start[1]};
        while (pipes.get(next[0]).charAt(next[1]) != 'S') {
            String originalString = newPipes.get(next[0]);
            char newChar = pipes.get(next[0]).charAt(next[1]);
            String modifiedString = originalString.substring(0, next[1]) + newChar + originalString.substring(next[1] + 1);
            newPipes.remove(next[0]);
            newPipes.add(next[0], modifiedString);
            int[] directions = findDirections(pipes.get(next[0]).charAt(next[1]));
            if (before[0] == directions[0] + next[0] && before[1] == directions[1] + next[1]) {
                before[0] = next[0];
                before[1] = next[1];
                next[0] += directions[2];
                next[1] += directions[3];
            } else {
                before[0] = next[0];
                before[1] = next[1];
                next[0] += directions[0];
                next[1] += directions[1];
            }
        }
        ArrayList<char[]> rightDirections = new ArrayList<>();
        if (checkDirection(LEFT, start, pipes, next, 0, -1)) {
            rightDirections.add(LEFT);
        }
        if (checkDirection(RIGHT, start, pipes, next, 0, 1)) {
            rightDirections.add(RIGHT);
        }
        if (checkDirection(UP, start, pipes, next, -1, 0)) {
            rightDirections.add(UP);
        }
        if (checkDirection(DOWN, start, pipes, next, 1, 0)) {
            rightDirections.add(DOWN);
        }
        for (int i = 0; i < rightDirections.get(0).length; i++) {
            for (int j = 0; j < rightDirections.get(1).length; j++) {
                if (rightDirections.get(0)[i] == rightDirections.get(1)[j]) {
                    char newChar = rightDirections.get(1)[j];
                    String originalString = newPipes.get(start[0]);
                    String modifiedString = originalString.substring(0, start[1]) + newChar + originalString.substring(start[1] + 1);
                    newPipes.remove(start[0]);
                    newPipes.add(start[0], modifiedString);
                }
            }
        }
        return newPipes;
    }

    private static boolean checkDirection(char[] direction, int[] start, List<String> pipes, int[] next, int rowChange, int colChange) {
        for (int i = 0; i < direction.length; i++) {
            int newRow = start[0] + rowChange;
            int newCol = start[1] + colChange;
            
            if (newRow > -1 && newRow < pipes.size() && newCol > -1 && newCol < pipes.get(0).length() && pipes.get(newRow).charAt(newCol) == direction[i]) {
                next[0] = newRow;
                next[1] = newCol;
                return false;
            }
        }
        return true;
    }

    private static int[] findDirections(char sign) {
        switch(sign) {
            case '-':
                return new int[]{0, -1, 0, 1};
            case '|':
                return new int[]{-1, 0, 1, 0};
            case 'F':
                return new int[]{1, 0, 0, 1};
            case 'L':
                return new int[]{-1, 0, 0, 1};
            case '7':
                return new int[]{1, 0, 0, -1};
            case 'J':
                return new int[]{-1, 0, 0, -1};
        }
        return null;
    }

    private static int countSpacesBetweenPipes(ArrayList<String> pipes) {
        int counter = 0;
        for (String line : pipes) {
            boolean count = false;
            char prevChar = ' ';
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '|') {
                    count = !count;
                } else if (line.charAt(i) == 'F' || line.charAt(i) == 'L') {
                    prevChar = line.charAt(i);
                } else if (line.charAt(i) == '7' && prevChar == 'L') {
                    count = !count;
                } else if (line.charAt(i) == 'J' && prevChar == 'F') {
                    count = !count;
                } else if (line.charAt(i) == '.' && count) {
                    counter++;
                }
            }
        }
        return counter;
    }
}