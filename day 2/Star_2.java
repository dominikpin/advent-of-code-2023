import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Star_2 {

    private static String[] cubeColors = {"red", "green", "blue"};

    public static void main(String args[]) throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        int sum = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            sum += analyzeOneLine(data);
        }
        myReader.close();
        System.err.println(sum);
    }

    public static int analyzeOneLine(String line) {
        int[] numOfCubes = {0, 0, 0};
        String[] sets = line.split(":")[1].split(";");
        for (int i = 0; i < sets.length; i++) {
            String[] set = sets[i].split(",");
            for (int j = 0; j < set.length; j++) {
                String[] cube = set[j].trim().split(" ");
                for (int k = 0; k < cubeColors.length; k++) {
                    if (cubeColors[k].equals(cube[1]) && numOfCubes[k] < Integer.parseInt(cube[0])) {
                        numOfCubes[k] = Integer.parseInt(cube[0]);
                    }
                }
            }
        }
        return numOfCubes[0] * numOfCubes[1] * numOfCubes[2];
    }
}