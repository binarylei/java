package com.binarylei.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author binarylei
 * @version 2020-03-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Node<E> {
    public E item;
    public Node<E> left;
    public Node<E> right;

    public Node(E item) {
        this.item = item;
    }

    private static final String SEPARATOR = ", ";


    @Override
    public String toString() {
        return midToString(this);
    }

    public String midToString() {
        return midToString(this);
    }

    public String leftToString() {
        return leftToString(this);
    }

    public String rightToString() {
        return rightToString(this);
    }

    public String levelToString() {
        return levelToString(this);
    }

    public int maxLevel() {
        return maxLevel(this);
    }

    public int minLevel() {
        return minLevel(this);
    }

    // 中序遍历
    public static String midToString(Node node) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(node.item).append(SEPARATOR);
        if (node.left != null) {
            sb.append(midToString(node.left)).append(SEPARATOR);
        }
        if (node.right != null) {
            sb.append(midToString(node.right)).append(SEPARATOR);
        }
        String result = sb.toString();
        return result.endsWith(SEPARATOR) ? result.substring(0, sb.length() - SEPARATOR.length()) : result;
    }

    // 前序遍历
    public static String leftToString(Node node) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        if (node.left != null) {
            sb.append(leftToString(node.left)).append(SEPARATOR);
        }
        sb.append(node.item).append(SEPARATOR);
        if (node.right != null) {
            sb.append(leftToString(node.right)).append(SEPARATOR);
        }
        String result = sb.toString();
        return result.endsWith(SEPARATOR) ? result.substring(0, sb.length() - SEPARATOR.length()) : result;
    }

    // 后序遍历
    public static String rightToString(Node node) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        if (node.right != null) {
            sb.append(rightToString(node.right)).append(SEPARATOR);
        }
        sb.append(node.item).append(SEPARATOR);
        if (node.left != null) {
            sb.append(rightToString(node.left)).append(SEPARATOR);
        }
        String result = sb.toString();
        return result.endsWith(SEPARATOR) ? result.substring(0, sb.length() - SEPARATOR.length()) : result;
    }

    // 层次遍历
    public static String levelToString(Node node) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);

        while (queue.size() > 0) {
            Node tmp = queue.poll();
            sb.append(tmp.item).append(SEPARATOR);
            if (tmp.left != null) {
                queue.add(tmp.left);
            }
            if (tmp.right != null) {
                queue.add(tmp.right);
            }
        }
        String result = sb.toString();
        return result.endsWith(SEPARATOR) ? result.substring(0, sb.length() - 2) : result;
    }

    // 最大高度计算
    public static int maxLevel(Node node) {
        if (node == null) return 0;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);

        int level = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                Node tmp = queue.poll();
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
        }
        return level;
    }

    // 最大高度计算
    public static int minLevel(Node node) {
        if (node == null) return 0;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);

        int level = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                Node tmp = queue.poll();
                if (tmp.left == null && tmp.right == null) {
                    return level;
                }
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
        }
        return level;
    }
}
