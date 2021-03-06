/* Created by IntelliJ IDEA.

    Author: Prajjwal Pachauri(cypher)
    Date: 08-04-2021
    Time: 15:20
    File: implementation.MyBinarySearchTree.java
*/
package implementation;

import myinterface.BinarySearchTreeADT;

public class MyBinarySearchTree<E extends Comparable<E>> implements BinarySearchTreeADT<E> {
    private Node<E> root;

    public Node<E> getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean search(E element) {
        Node<E> temp = root;
        boolean response = false;
        while (temp != null) {
            if (temp.getData() == element) {
                response = true;
                break;
            } else {
                if (element.compareTo(temp.getData()) < 0) {
                    temp = temp.getLeft();
                } else {
                    temp = temp.getRight();
                }
            }
        }
        return response;
    }

    @Override
    public void insert(E element) {
        Node<E> node = new Node<>(element);
        if (isEmpty()) {
            root = node;
        } else {
            Node<E> temp = root;
            Node<E> parent = null;
            while (temp != null) {
                parent = temp;
                if (element.compareTo(temp.getData()) <= 0) {
                    temp = temp.getLeft();
                } else {
                    temp = temp.getRight();
                }
            }
            assert parent != null;
            if (element.compareTo(parent.getData()) <= 0) {
                parent.setLeft(node);
            } else if (element.compareTo(parent.getData()) > 0) {
                parent.setRight(node);
            }
        }
    }

    // In Order Traversal
    @Override
    public void inOrder(Node<E> node) {
        if (node != null) {
            // call inOrder to process left SubTree
            inOrder(node.getLeft());
            System.out.print(node.getData() + ", ");
            inOrder(node.getRight());
        }
    }

    // Pre Order Traversal
    @Override
    public void preOrder(Node<E> node) {
        if (node != null) {
            System.out.print(node.getData() + ", ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    // Post Order Traversal
    @Override
    public void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getData() + ", ");
        }
    }

    @Override
    public void delete(E deletingElement) {
        Node<E> temp = root;
        Node<E> parent = null;
        while (temp != null) {
            if (deletingElement.compareTo(temp.getData()) == 0) {
                break;
            }
            else {
                parent = temp;
                if (deletingElement.compareTo(temp.getData()) < 0) {
                    temp = temp.getLeft();
                }
                else {
                    temp = temp.getRight();
                }
            }
        }
        if (temp != null) {
            // case 1:
            if (isLeaf(temp)) {
                if (parent == null) {
                    root = null;
                }
                else {
                    if (deletingElement.compareTo(parent.getData()) < 0) {
                        parent.setLeft(null);
                    }
                    else {
                        parent.setRight(null);
                    }
                }
            }

            // case 2:
            // left child
            else if (hasLeftNode(temp)) {
                if (parent == null) {
                    root = root.getLeft();
                }
                else if (deletingElement.compareTo(parent.getData()) < 0) {
                    parent.setLeft(temp.getLeft());
                }
                else {
                    parent.setRight(temp.getLeft());
                }
            }
            // right child
            else if (hasRightNode(temp)) {
                if (parent == null) {
                    root = root.getRight();
                }
                else if (deletingElement.compareTo(parent.getData()) < 0) {
                    parent.setLeft(temp.getRight());
                }
                else {
                    parent.setRight(temp.getRight());
                }
            }
            // case 3
            // two children
            else {
                Node<E> successor = getSuccessor(temp);
                delete(successor.getData());
                successor.setLeft(temp.getLeft());
                successor.setRight(temp.getRight());
                if (parent == null) {
                    root = successor;
                }
                else if (deletingElement.compareTo(parent.getData()) < 0) {
                    parent.setLeft(successor);
                }
                else {
                    parent.setRight(successor);
                }
            }
        }
        else {
            System.out.println("Not to be deleted... ");
        }
    }

    @Override
    public int height(Node<E> node) {
        if (node == null) {
            return -1;
        }
        else return 1 + maxDep(height(node.getLeft()), height(node.getRight()));
    }

    private int maxDep(int leftHeight, int rightHeight) {
        if (leftHeight > rightHeight) {
            return leftHeight;
        }
        else {
            return rightHeight;
        }
    }

    private Node<E> getSuccessor(Node<E> node) {
        Node<E> response;
        Node<E> temp = node.getRight();
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        response = temp;
        return response;
    }

    private boolean hasRightNode(Node<E> temp) {
        return temp.getRight() != null && temp.getLeft() == null;
    }
    private boolean hasLeftNode(Node<E> temp) {
        return temp.getLeft() != null && temp.getRight() == null;
    }

    private boolean isLeaf(Node<E> temp) {
        return temp.getLeft() == null && temp.getRight() == null;
    }
}