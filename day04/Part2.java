package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {

    private static ArrayList<Line> lines = new ArrayList<>();

    public Part2() throws FileNotFoundException {
        File myObj = new File("day04\\input.txt");
        Scanner myReader = new Scanner(myObj);
        int sum = 0;
        int counter = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            lines.add(new Line(data, 0, 1));
            analyzeOneLine(counter);
            counter++;
        }
        sum = getNumberOfTickets();
        myReader.close();
        System.out.println(sum);
    }

    private static void analyzeOneLine(int index) {
        int scoreCounter = 0;
        String[] winningAndOurNumbers = lines.get(index).getLine().split(":")[1].split(" ", -1);
        int indexSplit = Arrays.asList(winningAndOurNumbers).indexOf("|");
        for (int i = 0; i < indexSplit; i++) {
            if (winningAndOurNumbers[i].equals("")) {
                continue;
            }
            if (winningAndOurNumbers[i].equals("|")) {
                break;
            }
            for (int j = indexSplit + 1; j < winningAndOurNumbers.length; j++) {
                if (winningAndOurNumbers[i].equals(winningAndOurNumbers[j])) {
                    scoreCounter++;
                }
            }
        }
        lines.get(index).putScore(scoreCounter);
    }

    private static int getNumberOfTickets() {
        int numOfTickets = 0;
        for (Line line : lines) {
            int counter = line.getCounter();
            int score = line.getScore();
            int index = lines.indexOf(line);
            numOfTickets += counter;
            for (int i = index + 1; i <= index + score; i++) {
                lines.get(i).putCounter(lines.get(i).getCounter() + counter);
            }
        }
        return numOfTickets;
    }
}