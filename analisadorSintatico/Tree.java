package analisadorSintatico;

import java.io.BufferedWriter;
import java.io.IOException;

public class Tree {

  Node root;
  private BufferedWriter writer;

  public Tree() {
  }

  public Tree(Node root) {
    this.root = root;
  }

  public Tree(Node root, BufferedWriter writer) {
    this.root = root;
    this.writer = writer;
  }

  public void setRoot(Node node) {
    root = node;
  }

  public Node getRoot() {
    return root;
  }

  public void preOrder() {
    preOrder(root);
    System.out.println("");
  }

  public void printCode() {
    printCode(root);
    System.out.println("");
  }

  public void preOrder(Node node) {
    System.out.print(node);
    for (Node n : node.nodes) {
      preOrder(n);
    }
  }

  public void printCode(Node node) {
    System.out.print(node.enter);
    if (node.nodes.isEmpty())
      System.out.print(node);
    for (Node n : node.nodes) {
      printCode(n);
    }
    System.out.print(node.exit);
  }

  public void printTree() {
    try{
      if (writer != null){
        writer.write(root.getTree());
      }
      else{
        System.out.println(root.getTree());
      }
    }catch (IOException e) {
        System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
    }
  }
}
