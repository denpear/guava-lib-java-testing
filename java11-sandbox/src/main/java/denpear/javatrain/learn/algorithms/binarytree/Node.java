package denpear.javatrain.learn.algorithms.binarytree;

/**
 *
 * Сбалансированное дерево — дерево, в котором каждое поддерево сбалансировано и
 * высота двух поддеревьев отличается не более чем на единицу.
 * Двоичное дерево поиска — сбалансированное дерево. Его глубина всегда равна O(log n).
 * Пример: Поиск места для добавления элемента в двоичное дерево поиска (не сбалансированное):
 * Этот Java-код имеет структуру и функциональность, аналогичные коду на языке Python.
 * <p>
 * Он включает два класса, Node и Tree, где Node представляет каждый узел дерева,
 * а Tree - само бинарное дерево. Метод add используется для добавления нового узла в дерево.
 * Обратите внимание, что в Java в именах переменных принято использовать верблюжий регистр,
 * начиная со строчной буквы. В переводе я соответствующим образом скорректировал имена переменных.
 *
 */

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

    // Method to add a node to the tree
    public void add2(Node v, int x) {
        if (v == null) {
            return;
        }

        if (x < v.x) {
            if (v.l == null) {
                v.l = new Node(x, v); // Create a new left child node
                return;
            }

            add2(v.l, x); // Recursively add the node to the left subtree
        } else {
            if (v.r == null) {
                v.r = new Node(x, v); // Create a new right child node
                return;
            }

            add2(v.r, x); // Recursively add the node to the right subtree
        }
    }

    // Method to add a node to the tree (starting from the root)
    public void add(int x) {
        if (root == null) {
            root = new Node(x, null); // Create a new root node
            return;
        }

        add2(root, x); // Add the node to the tree starting from the root
    }
}
