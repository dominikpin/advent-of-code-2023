package day22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Part2 {
    
    public Part2() throws FileNotFoundException {
        File myObj = new File("day22\\input.txt");
        ArrayList<String> lines = new ArrayList<String>();
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            lines.add(myReader.nextLine());
        }
        
        ArrayList<Block> blocks = placeBlocksInGrid(lines);
        int sum = analyseBlock(blocks);
        myReader.close();
        System.out.println(sum);
    }

    private static ArrayList<Block> placeBlocksInGrid(ArrayList<String> lines) {
        ArrayList<Block> blocks = new ArrayList<>();
        int maxX = -1;
        int maxY = -1;
        for (String line : lines) {
            String[] split = line.split("~");
            int x = Integer.parseInt(split[1].split(",")[0]);
            int y = Integer.parseInt(split[1].split(",")[1]);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }
        sortLines(lines);
        Block[][] grid = new Block[maxX + 1][maxY + 1];
        for (String line : lines) {
            String[] split = line.split("~");
            int startX = Integer.parseInt(split[0].split(",")[0]);
            int startY = Integer.parseInt(split[0].split(",")[1]);
            int startZ = Integer.parseInt(split[0].split(",")[2]);
            int endX = Integer.parseInt(split[1].split(",")[0]);
            int endY = Integer.parseInt(split[1].split(",")[1]);
            int endZ = Integer.parseInt(split[1].split(",")[2]);
            int diffZ = endZ - startZ + 1;
            Set<Block> supportedBy = new HashSet<>();
            int maxZ = 0;
            for (int i = startX; i <= endX; i++) {
                for (int j = startY; j <= endY; j++) {
                    if (grid[i][j] == null) {
                        continue;
                    }
                    Block block = grid[i][j];
                    if (maxZ > block.getZ()) {
                        continue;
                    }
                    if (maxZ == block.getZ()) {
                        supportedBy.add(block);
                    }
                    if (maxZ < block.getZ()) {
                        maxZ = block.getZ();
                        supportedBy.clear();
                        supportedBy.add(block);
                    }
                }
            }
            Block newBlock = new Block(maxZ + diffZ);
            for (Block block : supportedBy) {
                newBlock.addSupportedBy(block);
                block.addSupportFrom(newBlock);
            }
            blocks.add(newBlock);
            for (int i = startX; i <= endX; i++) {
                for (int j = startY; j <= endY; j++) {
                    grid[i][j] = newBlock;
                }
            }
        }
        return blocks;
    }

    private static void sortLines(ArrayList<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.size() - i - 1; j++) {
                String line1 = lines.get(j);
                String line2 = lines.get(j + 1);
                int z1 = Integer.parseInt(line1.split("~")[0].split(",")[2]);
                int z2 = Integer.parseInt(line2.split("~")[0].split(",")[2]);
                if (z1 > z2) {
                    lines.remove(j);
                    lines.add(j + 1, line1);
                }
            }
        }
    }

    private static int analyseBlock(ArrayList<Block> blocks) {
        int sum = 0;
        for (Block block : blocks) {
            Set<Block> set = new HashSet<>();
            set.add(block);
            Set<Block> potentialFall = new HashSet<>(block.getSupportFor());
            while (potentialFall.size() > 0) { 
                Block potential = potentialFall.iterator().next();
                potentialFall.remove(potential);
                boolean fall = true;
                for (Block support : potential.getSupportedBy()) {
                    if (!set.contains(support)) {
                        fall = false;
                    }
                }
                if (fall) {
                    set.add(potential);
                    potentialFall.addAll(potential.getSupportFor());
                }
            }
            sum += set.size() - 1;
        }
        return sum;
    }
}