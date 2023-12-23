package day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Part1 {
    
    public Part1() throws FileNotFoundException {
        File myObj = new File("day18\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> lines = new ArrayList<String>();
        int[] minMaxXY = calculateGridSize(myReader, lines);
        boolean[][] grid = new boolean[minMaxXY[3] - minMaxXY[2] + 1][minMaxXY[1] - minMaxXY[0] + 1];
        int sum = 0;
        analyzeLines(grid, lines, minMaxXY);
        sum = countGrid(grid);
        myReader.close();
        System.out.println(sum);
    }

    private static int[] calculateGridSize(Scanner myReader, ArrayList<String> lines) {
        int x = 0;
        int y = 0;
        int maxX = 0;
        int maxY = 0;
        int minX = 0;
        int minY = 0;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            lines.add(line);
            String[] split = line.split(" ");
            int i = Integer.parseInt(split[1]);
            switch (line.charAt(0)) {
                case 'R':
                    x += i;
                    break;
                case 'D':
                    y += i;
                    break;
                case 'L':
                    x -= i;
                    break;
                case 'U':
                    y -= i;
                    break;
            }
            maxX = Math.max(maxX, x);
            minX = Math.min(minX, x);
            maxY = Math.max(maxY, y);
            minY = Math.min(minY, y);
        }
        return new int[]{minX, maxX, minY, maxY};
    }

    private static void analyzeLines(boolean[][] grid, ArrayList<String> lines, int[] minMaxXY) {
        int x = -minMaxXY[0];
        int y = -minMaxXY[2];
        for (String line : lines) {
            String[] split = line.split(" ");
            int i = Integer.parseInt(split[1]);
            switch (line.charAt(0)) {
                case 'R':
                    for (int j = x + 1; j <= x + i; j++) {
                        grid[y][j] = true;
                    }
                    x += i;
                    break;
                case 'D':
                    for (int j = y + 1; j <= y + i; j++) {
                        grid[j][x] = true;
                    }
                    y += i;
                    break;
                case 'L':
                    for (int j = x - i; j < x; j++) {
                        grid[y][j] = true;
                    }
                    x -= i;
                    break;
                case 'U':
                    for (int j = y - i; j < y; j++) {
                        grid[j][x] = true;
                    }
                    y -= i;
                    break;
            }
        }
    }

    private static int countGrid(boolean[][] grid) {
        int sum = 0;
        boolean found = false;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length - 1; j++) {
                if (grid[i][j] && !grid[i][j + 1] && (j == 0 || !grid[i][j - 1])) {
                    findAllInnerTiles(grid, visited, i, j + 1);
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] || visited[i][j]) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private static void findAllInnerTiles(boolean[][] grid, boolean[][] visited, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            x = current[0];
            y = current[1];

            if (grid[x][y] || visited[x][y]) {
                continue;
            }

            visited[x][y] = true;

            queue.offer(new int[]{x - 1, y});
            queue.offer(new int[]{x + 1, y});
            queue.offer(new int[]{x, y - 1});
            queue.offer(new int[]{x, y + 1});
        }
    }
}
