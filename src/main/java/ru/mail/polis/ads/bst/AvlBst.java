package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            height = 1;
        }
    }

    private Node root;
    private int size = 0;

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value value = get(key);
        if (value != null) {
            root = remove(root, key);
            --size;
        }
        return value;
    }

    @Override
    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    @Override
    public Value minValue() {
        if (isEmpty()) {
            return null;
        }
        return min(root).value;
    }

    @Override
    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return max(root).key;
    }

    @Override
    public Value maxValue() {
        if (isEmpty()) {
            return null;
        }
        return max(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node node = floor(root, key);
        return node == null ? null : node.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node node = ceil(root, key);
        return node == null ? null : node.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node root) {
        return  root == null ? 0 : root.height;
    }

    private void fixHeight(Node root) {
        root.height = Math.max(height(root.left), height(root.right)) + 1;
    }

    private Node rotateLeft(Node root) {
        Node child = root.right;
        root.right = child.left;
        child.left = root;
        fixHeight(root);
        fixHeight(child);
        return child;
    }

    private Node rotateRight(Node root) {
        Node child = root.left;
        root.left = child.right;
        child.right = root;
        fixHeight(root);
        fixHeight(child);
        return child;
    }

    private int heightDifference(Node root) {
        return height(root.left) - height(root.right);
    }

    private Node balance(Node root) {
        int diff = heightDifference(root);
        if (diff == 2) {
            if (heightDifference(root.left) < 0) {
                root.left = rotateLeft(root.left);
            }
            return rotateRight(root);
        } else if (diff == -2) {
            if (heightDifference(root.right) > 0) {
                root.right = rotateRight(root.right);
            }
            return rotateLeft(root);
        }
        return root;
    }

    private Value get(Node root, Key key) {
        if (root == null) {
            return null;
        }

        int compareResult = root.key.compareTo(key);
        if (compareResult == 0) {
            return root.value;
        } else {
            return compareResult > 0 ? get(root.left, key) : get(root.right, key);
        }
    }

    private Node put(Node root, Key key, Value value) {
        if (root == null) {
            ++size;
            return new Node(key, value);
        }

        int compareResult = root.key.compareTo(key);
        if (compareResult > 0) {
            root.left = put(root.left, key, value);
        } else if (compareResult < 0) {
            root.right = put(root.right, key, value);
        } else {
            root.value = value;
        }

        fixHeight(root);
        root = balance(root);
        return root;
    }

    private Node removeMin(Node root) {
        if (root.left == null) {
            return null;
        }
        root.left = removeMin(root.left);
        fixHeight(root);
        root = balance(root);
        return root;
    }

    private Node remove(Node root, Key key) {
        if (root == null) {
            return null;
        }

        int compareResult = root.key.compareTo(key);
        if (compareResult > 0) {
            root.left = remove(root.left, key);
        } else if (compareResult < 0) {
            root.right = remove(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            Node temp = root;
            root = min(temp.right);
            root.right = removeMin(temp.right);
            root.left = temp.left;
        }
        fixHeight(root);
        root = balance(root);
        return root;
    }

    private Node min(Node root) {
        return root.left == null ? root : min(root.left);
    }

    private Node max(Node root) {
        return root.right == null ? root : max(root.right);
    }

    private Node ceil(Node root, Key key) {
        if (root == null || root.key.equals(key)) {
            return root;
        }

        if (root.key.compareTo(key) < 0) {
            return ceil(root.right, key);
        }

        Node temp = ceil(root.left, key);
        if (temp == null || temp.key.compareTo(root.key) > 0) {
            return root;
        }
        return temp;
    }

    private Node floor(Node root, Key key) {
        if (root == null || root.key.equals(key)) {
            return root;
        }

        if (root.key.compareTo(key) > 0) {
            return floor(root.left, key);
        }

        Node temp = floor(root.right, key);
        if (temp == null || temp.key.compareTo(root.key) < 0) {
            return root;
        }
        return temp;
    }
}
