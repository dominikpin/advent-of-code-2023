package day08;

public class Node {
    
    private String nodeName;
    private Node left;
    private Node right;

    public Node(String nodeName, Node left, Node right) {
        this.nodeName = nodeName;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return String.format("Node: %s, left: %s, right: %s", nodeName, left.getNodeName() != null ? left.getNodeName() : "null", right.getNodeName() != null ? right.getNodeName() : "null");
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
