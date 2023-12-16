package day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part1 {

    private static final Map<Character, int[][]> MAP = new HashMap<>();
    private static final int[][] directionMapping = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    static {
        MAP.put('.', new int[][]{{0, 1}, {2, 3}});
        MAP.put('/', new int[][]{{0, 2}, {1, 3}});
        MAP.put('\\', new int[][]{{0, 3}, {1, 2}});
        MAP.put('-', new int[][]{{0, 1}, {0, 1, 2}, {0, 1, 3}});
        MAP.put('|', new int[][]{{2, 3}, {0, 2, 3}, {1, 2, 3}});
    }

    public Part1() throws FileNotFoundException {
        File myObj = new File("day16\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> grid = new ArrayList<>();
        int sum = 0;
        while (myReader.hasNextLine()) {
            grid.add(myReader.nextLine());
        }
        sum = analyzeGrid(grid);
        myReader.close();
        System.out.println(sum);
    }

    private static int analyzeGrid(ArrayList<String> grid) {
        Tile[][] energisedTileGrid = new Tile[grid.size()][grid.get(0).length()];
        for (int i = 0; i < energisedTileGrid.length; i++) {
            for (int j = 0; j < energisedTileGrid[i].length; j++) {
                energisedTileGrid[i][j] = new Tile();
            }
        }
        energiseTile(grid, energisedTileGrid, 0, 0, 0);
        int sum = 0;
        for (int i = 0; i < energisedTileGrid.length; i++) {
            for (int j = 0; j < energisedTileGrid[0].length; j++) {
                for (int k = 0; k < 4; k++) {
                    if (energisedTileGrid[i][j].getIsFromDirection(k)) {
                        sum++;
                        break;
                    }
                }
            }
        }
        return sum;
    }

    private static void energiseTile(ArrayList<String> grid, Tile[][] energisedTileGrid, int x, int y, int direction) {
        if (x < 0 || x >= grid.size() || y < 0 || y >= grid.get(0).length()) {
            return;
        }
        char gridTile = grid.get(x).charAt(y);
        if (!energisedTileGrid[x][y].getIsFromDirection(direction)) {
            energisedTileGrid[x][y].setIsFromDirection(direction, true);
        } else {
            return;
        }
        int[][] directions = MAP.get(gridTile);
        int relevantI = -1;
        for (int i = 0; i < directions.length; i++) {
            for (int j = 0; j < directions[i].length; j++) {
                if (direction == directions[i][j]) {
                    relevantI = i;
                    break;
                }
            }
            if (relevantI != -1) {
                break;
            }
        }
        for (int i = 0; i < directions[relevantI].length; i++) {
            if (directions[relevantI][i] != direction) {
                energiseTile(grid, energisedTileGrid, x + directionMapping[directions[relevantI][i]][0], y + directionMapping[directions[relevantI][i]][1], directions[relevantI][i] % 2 == 0 ? directions[relevantI][i] + 1 : directions[relevantI][i] - 1);
            }
        }
    }
}
