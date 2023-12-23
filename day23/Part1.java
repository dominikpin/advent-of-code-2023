package day23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final char[] CHARS = {'>', '<', 'v', '^'};
    
    public Part1() throws FileNotFoundException {
        File myObj = new File("day23\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> map = new ArrayList<>();
        while (myReader.hasNextLine()) {
            map.add(myReader.nextLine());
        }
        map.remove(0);
        map.add(0, "#".repeat(map.get(0).length()));
        int max = findLongestPath(map, 0, 1, 1, 1);
        myReader.close();
        System.out.println(max);
    }

    public static int findLongestPath(ArrayList<String> map, int max, int counter, int x, int y) {
        if (x == map.size() - 1 && y == map.get(0).length() - 2) {
            return Math.max(max, counter);
        }
        String originalLine = map.get(x);
        StringBuilder stringBuilder = new StringBuilder(originalLine);
        stringBuilder.setCharAt(y, 'O');
        String modifedLine = stringBuilder.toString();
        map.remove(x);
        map.add(x, modifedLine);
        for (int i = 0; i < DIRECTIONS.length; i++) {
            int[] direction = DIRECTIONS[i];
            if (map.get(x + direction[0]).charAt(y + direction[1]) == '.') {
                max = Math.max(max, findLongestPath(map, max, counter + 1, x + direction[0], y + direction[1]));
            } else if (map.get(x + direction[0]).charAt(y + direction[1]) == CHARS[i]) {
                max = Math.max(max, findLongestPath(map, max, counter + 2, x + 2 * direction[0], y + 2 * direction[1]));
            }
        }
        map.remove(x);
        map.add(x, originalLine);
        return max;
    }
}