package bst;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    private class Node {
        T value;
        Node left;
        Node right;
        Node parent;

        Node(T value) {
            this.value = value;
            left = null;
            right = null;
            parent = null;
        }
    }

    public void insert(T value) {
        if (value == null) {
            return;
        }

        Node node = null;
        Node traverse = root;
        while (traverse != null) {
            node = traverse;

            if (traverse.value.compareTo(value) < 0) {
                traverse = traverse.right;
            } else {
                traverse = traverse.left;
            }
        }

        if (node == null) {
            // This is the first insertion, init root and return
            root = new Node(value);
            return;
        }

        Node insertion = new Node(value);
        insertion.parent = node;
        if (node.value.compareTo(value) < 0) {
            node.right = insertion;
        } else {
            node.left = insertion;
        }
    }

    public boolean contains(T value) {
        Node traverse = root;

        while (traverse != null) {
            if (traverse.value.compareTo(value) == 0) {
                return true;
            } else if (traverse.value.compareTo(value) < 0) {
                traverse = traverse.right;
            } else {
                traverse = traverse.left;
            }
        }

        return false;
    }

    public void remove(T value) {
        Node deleteNode = get(value);
        if (deleteNode == null) {
            return;
        }

        if (deleteNode.left == null) {
            swapParent(deleteNode, deleteNode.right);
        } else if (deleteNode.right == null) {
            swapParent(deleteNode, deleteNode.left);
        } else {
            Node minimum = getMinimum(deleteNode.right);
            if (minimum.parent != deleteNode) {
                swapParent(minimum, minimum.right);
                minimum.right = deleteNode.right;
                minimum.right.parent = minimum;
            }

            swapParent(deleteNode, minimum);
            minimum.left = deleteNode.left;
            minimum.left.parent = minimum;
        }
    }

    public T minimum() {
        Node traverse = root;
        Node minimum = traverse;
        while (traverse != null) {
            minimum = traverse;
            traverse = traverse.left;
        }

        return minimum.value;
    }

    public T maximum() {
        Node traverse = root;
        Node maximum = traverse;
        while (traverse != null) {
            maximum = traverse;
            traverse = traverse.right;
        }

        return maximum.value;
    }

    public T successor(T value) {
        Node node = get(value);
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return getMinimum(node).value;
        }

        Node parent = node.parent;
        while (parent != null && node == parent.right) {
            node = parent;
            parent = parent.parent;
        }

        return parent == null ? null : parent.value;
    }

    public T predecessor(T value) {
        Node node = get(value);
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            return getMaximum(node).value;
        }

        Node parent = node.parent;
        while (parent != null && node == parent.left) {
            node = parent;
            parent = parent.parent;
        }

        return parent == null ? null : parent.value;
    }

    public void printInOrder() {
        Node traverse = root;
        inOrder(traverse);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node);
        inOrder(node.right);
    }

    private Node getMinimum(Node node) {
        Node traverse = node;
        Node minimum = traverse;
        while (traverse != null) {
            minimum = traverse;
            traverse = traverse.left;
        }

        return minimum;
    }

    private Node getMaximum(Node node) {
        Node traverse = node;
        Node maximum = traverse;
        while (traverse != null) {
            maximum = traverse;
            traverse = traverse.right;
        }

        return maximum;
    }

    private void swapParent(Node node, Node swapParent) {
        if (node.parent == null) {
            root = swapParent;
        } else if (node.parent.left == node) {
            node.parent.left = swapParent;
        } else {
            node.parent.right = swapParent;
        }

        if (swapParent != null) {
            swapParent.parent = node.parent;
        }
    }

    private Node get(T value) {
        Node traverse = root;

        while (traverse != null) {
            if (traverse.value.compareTo(value) == 0) {
                return traverse;
            } else if (traverse.value.compareTo(value) < 0) {
                traverse = traverse.right;
            } else {
                traverse = traverse.left;
            }
        }

        return null;
    }
}
