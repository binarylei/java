package com.github.binarylei.algorithm.backtrack;

/**
 * @author: leigang
 * @version: 2018-12-16
 */
public class BacktrackAlgorithm {

    int n;
    int[] col;
    int total;

    public BacktrackAlgorithm(int n) {
        this.n = n;
        this.col = new int[n];
    }

    // 第 i 行能不能放皇后要和前 0 ~ i-1 行比较
    private boolean check(int i) {
        for (int j = 0; j < i; j++) {
            if (col[i] == col[j] || Math.abs(i - j) == Math.abs(col[i] - col[j]))
                return false;
        }
        return true;
    }

    public void queen(int i) {
        if (i == n) {
            total++;
            // 输出结果
            for (int j = 0; j < n; j++) {     // 行
                for (int k = 0; k < n; k++) { // 列
                    System.out.printf(k == col[j] ? "* " : "= ");
                }
                System.out.println();
            }
            System.out.println();
        } else {
            for (int j = 0; j < n; j++) {
                col[i] = j;
                if (check(i)) { // 判断 i 行 j 列能不能放皇后
                    queen(i + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        BacktrackAlgorithm backtrackAlgorithm = new BacktrackAlgorithm(4);
        backtrackAlgorithm.queen(0);
        System.out.println(backtrackAlgorithm.total);
    }
}
