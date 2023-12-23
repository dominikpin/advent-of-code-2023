package day21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Part1 {
    
    public Part1() throws FileNotFoundException {
        File myObj = new File("day21\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> lines = new ArrayList<String>();
        while (myReader.hasNextLine()) {
            lines.add(myReader.nextLine());
        }
        Set<Position> positions = new HashSet<>();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == 'S') {
                    positions.add(new Position(i, j));
                }
            }
        }
        for (int i = 0; i < 64; i++) {
            takeNextStep(lines, positions);
        }
        myReader.close();
        System.out.println(positions.size());
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