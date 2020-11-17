package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node temp = root;
        while (temp != null && !temp.key.equals(key)) {
            temp = temp.key.compareTo(key) > 0 ? temp.left : temp.right;
        }
        return temp == null ? null : temp.value;

    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value temp = get(key);
        if (temp != null) {
            root = remove(root, key);
            --size;
        }
        return temp;
    }

    @Nullable
    @Override
    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        if (isEmpty()) {
            return null;
        }
        return min(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (isEmpty()) {
            return null;
        }
        return max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node node = floor(root, key);
        return node == null ? null : node.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node node = ceil(root, key);
        return node == null ? null : node.key;
    }

    @Override
    public int size() {
        return size;
    }

    private Node min(Node root) {
        return root.left == null ? root : min(root.left);
    }

    private Node max(Node root) {
        return root.right == null ? root : max(root.right);
    }

    private Node ceil(Node root, Key key) {
        if (root == null) {
            return null;
        }

        int compareResult = root.key.compareTo(key);
        if (compareResult == 0) {
            return root;
        } else if (compareResult < 0) {
            return ceil(root.right, key);
        }

        Node temp = ceil(root.left, key);
        if (temp == null || temp.key.compareTo(root.key) > 0) {
            return root;
        }
        return temp;
    }

    private Node floor(Node root, Key key) {
        if (root == null) {
            return null;
        }

        int compareResult = root.key.compareTo(key);
        if (compareResult == 0) {
            return root;
        } else if (compareResult > 0) {
            return floor(root.left, key);
        }

        Node temp = floor(root.right, key);
        if (temp == null || temp.key.compareTo(root.key) < 0) {
            return root;
        }
        return temp;
    }

    private Node rotateLeft(Node root) {
        Node child = root.right;
        root.right = child.left;
        child.left = root;
        child.color = root.color;
        root.color = RED;
        return child;
    }

    private Node rotateRight(Node root) {
        Node child = root.left;
        root.left = child.right;
        child.right = root;
        child.color = root.color;
        root.color = RED;
        return child;
    }

    private void flipColors(Node root) {
        root.color = !root.color;
        root.left.color = !root.left.color;
        root.right.color = !root.right.color;
    }

    private boolean isRed(Node root) {
        return root != null && root.color == RED;
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

        return fixUp(root);
    }

    private Node remove(Node root, Key key) {
        if (root == null) {
            return null;
        }

        int compareResult = root.key.compareTo(key);
        if (compareResult > 0) {
            if (root.left != null) {
                if (!isRed(root.left) && !isRed(root.left.left)) {
                    root = moveRedLeft(root);
                }
                root.left = remove(root.left, key);
            }
        } else {
            if (isRed(root.left)) {
                root = rotateRight(root);
                root.right = remove(root.right, key);
            } else if (compareResult == 0 && root.right == null) return null;
            else {
                if (root.right != null && !isRed(root.right) && !isRed(root.right.left)) {
                    root = moveRedRight(root);
                }
                if (root.key.equals(key)) {
                    Node min = min(root.right);
                    root.key = min.key;
                    root.value = min.value;
                    root.right = removeMin(root.right);
                } else {
                    root.right = remove(root.right, key);
                }
            }
        }

        return root;
    }

    private Node removeMin(Node root) {
        if (root.left == null) return null;
        if (!isRed(root.left) && !isRed(root.left.left))
            root = moveRedLeft(root);
        root.left = removeMin(root.left);
        return fixUp(root);
    }

    private Node moveRedRight(Node root) {
        flipColors(root);
        if (isRed(root.left.left)) {
            root = rotateRight(root);
            flipColors(root);
        }
        return root;
    }

    private Node moveRedLeft(Node root) {
        flipColors(root);
        if (isRed(root.right.left)) {
            root.right = rotateRight(root.right);
            root = rotateLeft(root);
            flipColors(root);
        }
        return root;
    }

    private Node fixUp(Node root) {
        if (isRed(root.right) && !isRed(root.left)) {
            root = rotateLeft(root);
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
        }
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }
        return root;
    }
}
