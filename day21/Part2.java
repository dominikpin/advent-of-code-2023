package day21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File myObj = new File("day21\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> lines = new ArrayList<String>();
        while (myReader.hasNextLine()) {
            lines.add(myReader.nextLine());
        }

        long evenSpaces = calculateNumberOfSteps(lines, lines.size() / 2, lines.size() / 2, 2 * lines.size());
        long oddSpaces = calculateNumberOfSteps(lines, lines.size() / 2, lines.size() / 2, 2 * lines.size() + 1);

        int steps = 26501365;
        long evenPlots = 0;
        long oddPlots = 0;
        int numberOfPlotsInOneWay = (steps - lines.size() / 2) / lines.size();
        for (int i = 1; i < numberOfPlotsInOneWay; i++) {
            if (i % 2 == 1) {
                evenPlots += i;
            } else {
                oddPlots += i;
            }
        }
        evenPlots *= 4;
        oddPlots *= 4;
        oddPlots += 1;
        myReader.close();
        long sum = evenPlots * evenSpaces + oddPlots * oddSpaces;

        sum += calculateNumberOfSteps(lines, lines.size() - 1, lines.size() / 2, lines.size() - 1);
        sum += calculateNumberOfSteps(lines, 0, lines.size() / 2, lines.size() - 1);
        sum += calculateNumberOfSteps(lines, lines.size() / 2, lines.size() - 1, lines.size() - 1);
        sum += calculateNumberOfSteps(lines, lines.size() / 2, 0, lines.size() - 1);
        sum += numberOfPlotsInOneWay * calculateNumberOfSteps(lines, 0, 0, lines.size() / 2 - 1);
        sum += numberOfPlotsInOneWay * calculateNumberOfSteps(lines, 0, lines.size() - 1, lines.size() / 2 - 1);
        sum += numberOfPlotsInOneWay * calculateNumberOfSteps(lines, lines.size() - 1, 0, lines.size() / 2 - 1);
        sum += numberOfPlotsInOneWay * calculateNumberOfSteps(lines, lines.size() -1, lines.size() -1, lines.size() / 2 - 1);
        sum += (numberOfPlotsInOneWay - 1) * calculateNumberOfSteps(lines, 0, 0, lines.size() * 3 / 2 - 1);
        sum += (numberOfPlotsInOneWay - 1) * calculateNumberOfSteps(lines, 0, lines.size() - 1, lines.size() * 3 / 2 - 1);
        sum += (numberOfPlotsInOneWay - 1) * calculateNumberOfSteps(lines, lines.size() - 1, 0, lines.size() * 3/ 2 - 1);
        sum += (numberOfPlotsInOneWay - 1) * calculateNumberOfSteps(lines, lines.size() -1, lines.size() -1, lines.size() * 3 / 2 - 1);
        System.out.println(sum);
    }

    private static int calculateNumberOfSteps(ArrayList<String> lines, int start, int end, int length) {
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(start, end));
        for (int i = 0; i < length; i++) {
            takeNextStep(lines, positions);
        }
        return positions.size();
    }

    private static void takeNextStep(ArrayList<String> lines, Set<Position> positions) {
        Set<Position> newPositions = new HashSet<>();
        Iterator<Position> iterator = positions.iterator();
        while (iterator.hasNext()) {
            Position position = iterator.next();
            iterator.remove();
            if (position.getX() > 0 && lines.get(position.getX() - 1).charAt(position.getY()) != '#') {
                newPositions.add(new Position(position.getX() - 1, position.getY()));
            }
            if (position.getX() < lines.size() - 1 && lines.get(position.getX() + 1).charAt(position.getY()) != '#') {
                newPositions.add(new Position(position.getX() + 1, position.getY()));
            }
            if (position.getY() > 0 && lines.get(position.getX()).charAt(position.getY() - 1) != '#') {
                newPositions.add(new Position(position.getX(), position.getY() - 1));
            }
            if (position.getY() < lines.get(0).length() - 1 && lines.get(position.getX()).charAt(position.getY() + 1) != '#') {
                newPositions.add(new Position(position.getX(), position.getY() + 1));
            }
        }
        positions.addAll(newPositions);
    }
}
