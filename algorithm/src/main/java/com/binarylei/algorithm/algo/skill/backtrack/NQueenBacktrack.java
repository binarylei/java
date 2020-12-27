package com.binarylei.algorithm.algo.skill.backtrack;

import org.junit.Test;

/**
 * 回溯法解决 N 皇后问题
 *
 * @author binarylei
 * @version 2020-03-10
 */
public class NQueenBacktrack {

    int n;
    int[] col;
    int total;

    public NQueenBacktrack() {
    }

    private NQueenBacktrack(int n) {
        this.n = n;
        this.col = new int[n];
    }

    public void queen(int row) {
        if (row == n) {
            total++;
            print();
            return;
        }

        for (int column = 0; column < n; column++) {
            if (check(row, column)) { // 判断 (row, column) 能不能放皇后
                this.col[row] = column;
                queen(row + 1);
            }
        }
    }

    // (row, column) 需要和前 row-1 行的皇后比较，判断能否放皇后
    private boolean check(int row, int column) {
        for (int i = 0; i < row; i++) {
            if (col[i] == column || Math.abs(row - i) == Math.abs(column - col[i]))
                return false;
        }
        return true;
    }

    private void print() {
        // 输出结果
        for (int j = 0; j < n; j++) {     // 行
            for (int k = 0; k < n; k++) { // 列
                System.out.printf(k == col[j] ? "Q " : "* ");
            }
            System.out.println();
        }
        System.out.println();
    }


    @Test
    public void test() {
        NQueenBacktrack backtrackAlgorithm = new NQueenBacktrack(5);
        backtrackAlgorithm.queen(0);
        System.out.println(backtrackAlgorithm.total + "种可能");
    }
}
