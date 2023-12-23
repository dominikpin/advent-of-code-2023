package day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public Part2() throws FileNotFoundException {
        File myObj = new File("day17\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> map = new ArrayList<>();
        while (myReader.hasNextLine()) {
            map.add(myReader.nextLine());
        }
        int[][] intMap = convertMapStringtoMapInt(map);
        int sum = dykstrasAlgorithm(intMap);
        myReader.close();
        System.out.println(sum);
    }

    private static int[][] convertMapStringtoMapInt(ArrayList<String> map) {
        int[][] newMap = new int[map.size()][map.get(0).length()];
        for (int i = 0; i < newMap.length; i++) {
            for (int j = 0; j < newMap[i].length; j++) {
                newMap[i][j] = Character.getNumericValue(map.get(i).charAt(j));
            }
        }
        return newMap;
    }

    private static int dykstrasAlgorithm(int[][] map) {
        ArrayList<int[]> seen = new ArrayList<>();
        ArrayList<int[]> queue = new ArrayList<>();
        queue.add(new int[]{0, 0, 0, 0, 0, 0});
        while (queue.size() > 0) {
            int[] element = queue.remove(0);
            int[] pq = new int[] {element[1], element[2], element[3], element[4], element[5]};

            if (element[1] == map.length - 1 && element[2] == map[0].length - 1 && element[5] > 3) {
                return element[0];
            }
            boolean found = false;
            for (int[] elment1 : seen.reversed()) {
                boolean isSame = true;
                for (int i = 0; i < elment1.length; i++) {
                    if (elment1[i] != pq[i]) {
                        isSame = false;
                        break;
                    }
                }
                if (isSame) {
                    found = true;
                    break;
                }
            }
            if (found) {
                continue;
            }
            seen.add(pq);
            if (element[5] < 10 && !(element[3] == 0 && element[4] == 0)) {
                int nextRow = element[1] + element[3];
                int nextColumn = element[2] + element[4];
                if (nextRow >= 0 && nextRow < map.length && nextColumn >= 0 && nextColumn < map[0].length ) {
                    addToQueue(queue, element[0] + map[nextRow][nextColumn], nextRow, nextColumn, element[3], element[4], element[5] + 1);
                }
            }

            if (element[5] > 3 || (element[3] == 0 && element[4] == 0)) {
                for (int i = 0; i < DIRECTIONS.length; i++) {
                    if ((DIRECTIONS[i][0] == element[3] && DIRECTIONS[i][1] == element[4]) || (DIRECTIONS[i][0] == -element[3] && DIRECTIONS[i][1] == -element[4]) ) {
                        continue;
                    }
                    int nextRow = element[1] + DIRECTIONS[i][0];
                    int nextColumn = element[2] + DIRECTIONS[i][1];
                    if (nextRow >= 0 && nextRow < map.length && nextColumn >= 0 && nextColumn < map[0].length) {
                        addToQueue(queue, element[0] + map[nextRow][nextColumn], nextRow, nextColumn, DIRECTIONS[i][0], DIRECTIONS[i][1], 1);
                    }
                }
            }
        }
        return -1;
    }

    private static void addToQueue(ArrayList<int[]> queue, int heatLoss, int row, int col, int drow, int dcol, int n) {
        if (queue.isEmpty()) {
            queue.add(new int[]{heatLoss, row, col, drow, dcol, n});
            return;
        }
        for (int i = 0; i < queue.size(); i++) {
            int[] element = queue.get(i);
            if (element[0] > heatLoss) {
                queue.add(i, new int[]{heatLoss, row, col, drow, dcol, n});
                return;
            }
        }
        queue.add(new int[]{heatLoss, row, col, drow, dcol, n});
    }
}
