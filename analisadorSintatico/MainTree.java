package analisadorSintatico;

public class MainTree {
    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");

        A.addNode(B);
        A.addNode(C);
        A.addNode(D);

        C.addNode(E);
        C.addNode(F);

        Tree tree = new Tree(A);
        tree.printTree();
    }
}
