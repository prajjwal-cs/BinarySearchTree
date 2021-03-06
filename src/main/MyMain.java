/* Created by IntelliJ IDEA.

    Author: Prajjwal Pachauri(cypher)
    Date: 09-04-2021
    Time: 15:25
    File: MyMain.java 
*/
package main;

import implementation.MyBinarySearchTree;

public class MyMain {
    public static void main(String[] args) {
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
        tree.insert(50);
        tree.insert(40);
        tree.insert(30);
        tree.insert(45);
        tree.insert(60);
        tree.insert(66);
        tree.insert(70);
        tree.insert(55);
        System.out.println("preOrder");
        tree.preOrder(tree.getRoot());
        System.out.println();
        System.out.println(tree.height(tree.getRoot()));
        tree.delete(66);
        System.out.println("InOrder");
        tree.inOrder(tree.getRoot());
        System.out.println();
        System.out.println("PostOrder");
        tree.postOrder(tree.getRoot());
        System.out.println();
    }
}