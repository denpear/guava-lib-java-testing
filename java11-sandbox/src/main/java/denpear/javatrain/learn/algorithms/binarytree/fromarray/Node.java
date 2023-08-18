package denpear.javatrain.learn.algorithms.binarytree.fromarray;

import java.util.List;

/**
 * Двоичные деревья. Построение из отсортированного
 * массива
 * Пример:
 * Построение сбалансированного двоичного дерева поиска из отсортированного
 * массива:
 * <p>
 * Этот Java-код имеет структуру и функциональность, аналогичные коду на языке Python.
 * Он включает в себя два класса - Node и Tree.
 * Метод fromArray2 используется для построения бинарного дерева из заданного списка целых чисел,
 * а метод fromArray - для создания нового объекта Tree из этого списка.
 *
 */


import java.util.List;

public class Node {
    public int x; // Value of the node
    public Node parent; // Parent node
    public Node l; // Left child node
    public Node r; // Right child node

    public Node(int x, Node parent) {
        this.x = x;
        this.parent = parent;
        this.l = null;
        this.r = null;
    }
}

class Tree {
    public Node root; // Root node of the tree

    // Method to construct a binary tree from a given list of integers
    public Node fromArray2(List<Integer> a, int l, int r) {
        if (l + 1 > r) {
            return null; // Base case: no elements in the list
        }

        if (l + 1 == r) {
            return new Node(a.get(l), null); // Base case: single element in the list
        }

        int m = (l + r) / 2; // Calculate the middle index
        Node t = new Node(a.get(m), null); // Create a new node with the middle element
        t.l = fromArray2(a, l, m); // Recursively construct the left subtree
        t.r = fromArray2(a, m + 1, r); // Recursively construct the right subtree

        if (t.l != null) {
            t.l.parent = t; // Set the parent of the left child node
        }

        if (t.r != null) {
            t.r.parent = t; // Set the parent of the right child node
        }

        return t; // Return the constructed node
    }

    // Method to create a new Tree object from a list of integers
    public Tree fromArray(List<Integer> a) {
        Tree res = new Tree();
        res.root = fromArray2(a, 0, a.size()); // Construct the tree starting from index 0 to the size of the list
        return res;
    }
}


