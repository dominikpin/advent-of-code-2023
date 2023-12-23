package day23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public Part2() throws FileNotFoundException {
        File myObj = new File("day23\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> map = new ArrayList<>();
        while (myReader.hasNextLine()) {
            map.add(myReader.nextLine());
        }
        char[][] charMap = new char[map.size()][map.get(0).length()];
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {
                charMap[i][j] = map.get(i).charAt(j);
            }
        }
        charMap[0][1] = '#';
        int max = findLongestPath(charMap, 0, 1, 1, 1);
        myReader.close();
        System.out.println(max);
    }

    public static int findLongestPath(char[][] map, int max, int counter, int x, int y) {
        if (x == map.length - 1 && y == map[0].length - 2) {
            return Math.max(max, counter);
        }
        char originalChar = map[x][y];
        map[x][y] = '#';
        for (int[] direction : DIRECTIONS) {
            if (map[x + direction[0]][y + direction[1]] != '#') {
                max = Math.max(max, findLongestPath(map, max, counter + 1, x + direction[0], y + direction[1]));
            }
        }
        map[x][y] = originalChar;
        return max;
    }
}
