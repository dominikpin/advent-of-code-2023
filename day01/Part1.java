package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File myObj = new File("day01\\input.txt");
        Scanner myReader = new Scanner(myObj);
        int sum = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            sum += analyzeOneLine(data);
        }
        myReader.close();
        System.out.println(sum);
    }

    private static int analyzeOneLine(String line) {
        String firstNum = ""; 
        String lastNum = "";
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                if (firstNum == "") {
                    firstNum = line.substring(i, i+1);
                    lastNum = line.substring(i, i+1);
                } else {
                    lastNum = line.substring(i, i+1);
                }
            }
        }
        int number = Integer.parseInt(firstNum + lastNum);
        return number;
    }
}