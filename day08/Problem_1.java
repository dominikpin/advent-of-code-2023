package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem_1 {

    public Problem_1() throws FileNotFoundException {
        File myObj = new File("day08\\input.txt");
        Scanner myReader = new Scanner(myObj);
        String instructions = myReader.nextLine();
        myReader.nextLine();
        Node AAA = makeDataStructure(myReader);
        int sum = getToEnd(AAA, instructions);
        System.out.println(sum);
        myReader.close();
    }

    private static Node makeDataStructure(Scanner myReader) {
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
        return checkIfArrayHasNode("AAA", nodes);
    }

    private static Node checkIfArrayHasNode(String nodeName, ArrayList<Node> nodes) {
        for (Node node : nodes) {
            if (node.getNodeName().equals(nodeName)) {
                return node;
            }
        }
        return null;
    }

    private static int getToEnd(Node AAA, String instructions) {
        Node node = AAA;
        int counter = 0;
        while (!node.getNodeName().equals("ZZZ")) {
            if (instructions.charAt(counter%instructions.length()) == 'L') {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
            counter++;
        }
        return counter;
    }
}