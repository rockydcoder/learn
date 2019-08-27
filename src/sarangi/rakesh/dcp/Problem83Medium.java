package sarangi.rakesh.dcp;

public class Problem83Medium {


    public static void main(String[] args) {
        Node root = prepareTestData();
        printPreOrder(root);
        printPreOrder(solution(root));
    }

    private static void printPreOrder(Node root) {
        if (root != null) {
            System.out.println(root.value);
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }

    private static Node prepareTestData() {
        Node root = new Node(1);
        Node leaf2 = new Node(2);
        Node leaf3 = new Node(3);
        Node leaf4 = new Node(4);
        Node leaf5 = new Node(5);
        Node leaf6 = new Node(6);

        root.left = leaf2;
        root.right = leaf3;
        leaf2.left = leaf4;
        leaf2.right = leaf5;
        leaf3.left = leaf6;

        return root;
    }

    private static Node solution(Node root) {
        invert(root);
        return root;
    }

    private static void invert(Node root) {
        if (root == null){
            return;
        }
        if (root.left != null || root.right != null) {
            Node temp = root.left;
            root.left = root.right;
            root.right = temp;
            invert(root.left);
            invert(root.right);
        }
    }
}

class Node {
    Node left;
    Node right;
    int value;

    Node(int value) {
        this.value = value;
    }
}

