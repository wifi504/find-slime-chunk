import java.util.Arrays;

/**
 * @author WIFI连接超时
 * @version 1.0
 * Create Time: 2023/12/14_21:38
 * 生成圆形蒙版
 */

// TODO 能用，但是只能用一点点，寒假来优化

public class GenMask {
    public static void main(String[] args) {
        for (int i = 1; i < 100; i+=2) {
            print(getMask(i));
            System.out.println("------------------------------");
        }
    }

    // TODO 通过直径获取圆形蒙版
    public static char[][] getMask(int diameter) {
        char[][] mask = new char[diameter][diameter];

        double radius = diameter / 2.0;
        int center = diameter / 2;

        for (int i = 0; i < diameter; ++i) {
            for (int j = 0; j < diameter; ++j) {
                if (Math.pow(i - center, 2) + Math.pow(j - center, 2) < Math.pow(radius, 2)) {
                    mask[i][j] = '8';
                } else {
                    mask[i][j] = ' ';
                }
            }
        }

        return mask;
    }

    // print
    public static void print(char[][] arr) {
        for (char[] ints : arr) {
            for (char anInt : ints) {
                System.out.print(anInt + "  ");
            }
            System.out.println();
        }
    }
}
