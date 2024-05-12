package com.lhl.mc.ver01;

/**
 * @author WIFI连接超时
 * @version 2.0
 * Create Time: 2023/12/14_21:38
 * 生成圆形蒙版
 */

public class GenMask {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            print(getMask(i));
            System.out.println("------------------------------------------------");
        }
    }

    /**
     * 获取圆形蒙版
     *
     * @param diameter 直径
     */

    public static int[][] getMask(int diameter) {
        // 默认筛选比例 0.8 精度 1000
        return getMask(diameter, 0.8, 1000);
    }


    /**
     * 获取圆形蒙版
     *
     * @param diameter 直径
     * @param percent  筛选百分比
     * @param acc      精度
     */
    public static int[][] getMask(int diameter, double percent, int acc) {
        int[][] mask = new int[diameter][diameter];
        double[][] area = getMaskArea(diameter, acc);

        // 筛选大于指定比例的区域
        for (int i = 0; i < diameter; i++) {
            for (int j = 0; j < diameter; j++) {
                mask[i][j] = area[i][j] >= percent ? 1 : 0;
            }
        }
        return mask;
    }

    /**
     * 获取圆形蒙版覆盖面积矩阵
     *
     * @param diameter 直径
     * @param acc      分割精度
     */
    public static double[][] getMaskArea(int diameter, int acc) {
        if (acc % 2 != 0) acc++; // acc得是个偶数

        // 定义结果的矩阵
        double[][] mask = new double[diameter][diameter];
        // 定义面积计数矩阵
        int[][] computeArea = new int[diameter * acc][diameter * acc];

        int d = diameter * acc; // 直径
        int r = diameter * acc / 2; // 半径

        // 判断面积计数矩阵中的点是否在圆内
        for (double y = -r + 0.5; y < r; y++) {
            for (double x = -r + 0.5; x < r; x++) {
                computeArea[(int) (y + r - 0.5)][(int) (x + r - 0.5)] =
                        isInside(x, y, r) ? 1 : 0;
            }
        }

        // 把面积计数加到结果矩阵中
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                mask[i / acc][j / acc] += computeArea[i][j];
            }
        }

        // 把结果矩阵中的计数转化为几何面积
        for (int i = 0; i < diameter; i++) {
            for (int j = 0; j < diameter; j++) {
                mask[i][j] = Math.round(mask[i][j] / Math.pow(acc, 2) * 1000) / 1000.0;
            }
        }

        // print(computeArea);
        return mask;
    }


    /**
     * 判断传入的点 (x,y) 是否落在半径为 r 的圆内
     *
     * @param x x坐标
     * @param y y坐标
     * @param r 半径
     */
    private static boolean isInside(double x, double y, int r) {
        return (Math.pow(x, 2) + Math.pow(y, 2)) < Math.pow(r, 2);
    }


    // print
    private static void print(char[][] arr) {
        for (char[] ints : arr) {
            for (char anInt : ints) {
                System.out.print(anInt + "  ");
            }
            System.out.println();
        }
    }

    private static void print(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + "  ");
            }
            System.out.println();
        }
    }

    private static void print(double[][] arr) {
        for (double[] ints : arr) {
            for (double anInt : ints) {
                System.out.print(anInt + " \t");
            }
            System.out.println();
        }
    }
}
