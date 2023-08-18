package denpear.javatrain.learn.algorithms.binarytree.remove;

/**
 * Удаление элементов из дерева поиска
 * Самое главное
 * ● Первый вариант удаления элемента из дерева поиска — отметить его
 * «удалённым», но оставить в дереве. Это может вызвать проблемы при хранении
 * одинаковых элементов или при добавлении элементов с таким же значением,
 * как «удалённые».
 * ● Второй вариант удаления элемента из двоичного дерева поиска — реальное
 * удаление элемента.
 * Пример:
 * Удаление элемента:
 * 1. Если удаляется лист, то удаляем элемент, удаляем ребро, ведущее в него.
 * 2. Если удаляется элемент с одним «ребёнком», то ребро, ведущее в удаляемый
 * элемент, заменяем на ребро, ведущее в его «ребёнка».
 * 3. Если у удаляемого элемента два «ребёнка», то необходимо поменять его местами с
 * элементом, следующим по значению — самым левым в правом поддереве:
 *
 * Этот Java-код имеет структуру и функциональность, аналогичные коду на языке Python.
 * Он включает в себя два класса - Node и Tree.
 * Метод find2 используется для поиска узла с определенным значением в дереве. Метод delete2 используется для удаления узла из дерева.
 * Методы find и delete используются в качестве точек входа для поиска и удаления узлов соответственно.
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

    // Method to search for a node with a specific value in the tree
    public Node find2(Node v, int x) {
        if (v == null) {
            return null; // Base case: node not found
        }

        if (v.x == x) {
            return v; // Base case: node found
        }

        if (v.x > x) {
            return find2(v.l, x); // Recursively search in the left subtree
        } else {
            return find2(v.r, x); // Recursively search in the right subtree
        }
    }

    // Method to search for a node with a specific value in the tree
    public Node find(int x) {
        return find2(root, x);
    }

    // Method to delete a node from the tree
    public void delete2(Node t) {
        if (t == null) {
            return; // Base case: node not found
        }

        if (t.l == null || t.r == null) {
            // Case 1: Node has at most one child
            Node child = null;
            if (t.l != null) {
                child = t.l;
            } else {
                child = t.r;
            }

            if (t == root) {
                root = child; // Update the root if the node to be deleted is the root
            }

            if (child != null) {
                child.parent = null; // Update the parent of the child node
            }

            if (t.parent.l == t) {
                t.parent.l = child; // Update the left child of the parent
            } else {
                t.parent.r = child; // Update the right child of the parent
            }

            if (child != null) {
                child.parent = t.parent; // Update the parent of the child node
            }
        } else {
            // Case 2: Node has two children
            Node nxt = t.r;
            while (nxt.l != null) {
                nxt = nxt.l; // Find the leftmost node in the right subtree
            }
            t.x = nxt.x; // Replace the value of the node to be deleted with the value of the leftmost node
            delete2(nxt); // Recursively delete the leftmost node
        }
    }

    /**
     * Получение следующего элемента
     * Самое главное
     * • Если у вершины есть правый «сын», то переходим в правое поддерево и
     * берём самый левый элемент. Если правого «сына» нет, то поднимаемся
     * вверх, пока элемент, в котором находимся, не окажется левым «сыном»
     * своего предка. Для поиска предыдущего элемента действовать аналогично.
     * • Время работы алгоритма в несбалансированном дереве — O(n).
     * • В сбалансированном — O(log n).
     * @param v as a Node
     * @return next Node
     */
    public Node nextNode(Node v) {
        if (v == null) {
            return null;
        }
        if (v.r != null) {
            Node nxt = v.r;
            while (nxt.l != null) {
                nxt = nxt.l;
            }
            return nxt;
        }
        Node nxt = v;
        while (nxt.parent != null && nxt.parent.r == nxt) {
            nxt = nxt.parent;
        }
        return nxt.parent;
    }

    /**
     * Обход дерева поиска
     * Пример:
     * Функция, выводящая всё дерево в порядке возрастания ключей:
     *
     * два метода: printTree2 и printTree. Метод printTree2 рекурсивно печатает значения узлов
     * дерева в порядке их обхода
     * (левое поддерево, текущий узел, правое поддерево).
     * Метод printTree служит методом-оберткой, который вызывает printTree2 с корнем дерева.
     *
      * @param v as a Node
     */
    public void printTree2(Node v) {
        if (v == null) {
            return;
        }
        printTree2(v.l);
        System.out.println(v.x);
        printTree2(v.r);
    }

    /**
     * Обход дерева поиска:
     * Имеет два метода: check2 и check.
     * Метод check2 рекурсивно проверяет, удовлетворяют ли значения узлов дерева
     * свойству бинарного дерева поиска.
     * Он принимает три параметра: текущий узел v, нижнюю границу l и верхнюю границу r.
     * Если значение текущего узла меньше l или больше r, то он возвращает false.
     * В противном случае выполняется рекурсивная проверка левого и правого поддеревьев
     * с обновленными границами. Метод check служит методом-оберткой,
     * вызывающим check2 с корнем дерева и нулевыми границами.
     * <p>
     * Функция, проверяющая, является ли дерево двоичным деревом поиска:
     * @param v Node
     * @param l left
     * @param r right
     * @return
     */

    // Recursive method to check if the values in the tree satisfy the binary search tree property
    public boolean check2(Node v, Integer l, Integer r) {
        if (v == null) {
            return true; // Base case: an empty subtree is considered a valid binary search tree
        }
        if ((l != null) && (v.x < l)) {
            return false; // If the current node's value is less than the lower bound, it violates the binary search tree property
        }
        if ((r != null) && (v.x > r)) {
            return false; // If the current node's value is greater than the upper bound, it violates the binary search tree property
        }
        // Recursively check the left and right subtrees with updated bounds
        return check2(v.l, l, v.x) && check2(v.r, v.x, r);
    }

    // Wrapper method to call check2 with the root of the tree and null bounds
    public boolean check() {
        return check2(root, null, null);
    }


    public void printTree() {
        printTree2(root);
    }

    // Method to delete a node from the tree
    public void delete(int x) {
        if (root == null) {
            return; // Base case: tree is empty
        }

        Node t = find(x);
        if (t == null) {
            return; // Base case: node not found
        }

        delete2(t);
    }
}
