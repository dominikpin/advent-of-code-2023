package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem_2 {

    public Problem_2() throws FileNotFoundException {
        File myObj = new File("day08\\input.txt");
        Scanner myReader = new Scanner(myObj);
        String instructions = myReader.nextLine();
        myReader.nextLine();
        ArrayList<Node> listA = makeDataStructure(myReader);
        int[] rotationLength = calculateRotation(listA, instructions);
        long sum = calculateLCM(rotationLength);
        System.out.println(sum);
        myReader.close();
    }

    private static ArrayList<Node> makeDataStructure(Scanner myReader) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            Node node = checkIfArrayHasNode(line.substring(0, 3), nodes);
            if (node == null) { 
                node = new Node(line.substring(0, 3), null, null);
                nodes.add(node);
            }
            Node left = checkIfArrayHasNode(line.substring(7,10), nodes);
            if (left == null) {
                left = new Node(line.substring(7,10), null, null);
                nodes.add(left);
            }
            node.setLeft(left);
            Node right = checkIfArrayHasNode(line.substring(12,15), nodes);
            if (right == null) {
                right = new Node(line.substring(12,15), null, null);
                nodes.add(right);
            }
            node.setRight(right);
        }
        ArrayList<Node> listA = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node.getNodeName().charAt(2) == 'A') {
                listA.add(node);
            }
        }
        return listA;
    }

    private static Node checkIfArrayHasNode(String nodeName, ArrayList<Node> nodes) {
        for (Node node : nodes) {
            if (node.getNodeName().equals(nodeName)) {
                return node;
            }
        }
        return null;
    }

    private static int[] calculateRotation(ArrayList<Node> listA, String instructions) {
        int[] rotationLength = new int[listA.size()];
        for (int i = 0; i < listA.size(); i++) {
            Node node = listA.get(i);
            int counter = 0;
            while (node.getNodeName().charAt(2) != 'Z') {
                if (instructions.charAt(counter%instructions.length()) == 'L') {
                    node = node.getLeft();
                } else {
                    node = node.getRight();
                }
                counter++;
            }
            rotationLength[i] = counter;
        }
        return rotationLength;
    }

    private static long calculateLCM(int[] rotations) {
        long LCM = rotations[0];
        for (int i = 1; i < rotations.length; i++) {
            LCM = LCM*rotations[i]/calculateGCD(LCM, rotations[i]);
        }
        return LCM;
    }

    private static long calculateGCD(long a, long b) {
        if (b == 0) {
          return a;
        }
        return calculateGCD(b, a % b);
    }
}