import java.util.Scanner;

/**
 * @author WIFI连接超时
 * @version 1.0
 * Create Time: 2023/08/04_0:45
 */
public class Main {
    public static void main(String[] args) {

        Tools tools = new Tools();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" +
                "  _____  _             _  ____   _  _                  \n" +
                " |  ___|(_) _ __    __| |/ ___| | |(_) _ __ ___    ___ \n" +
                " | |_   | || '_ \\  / _` |\\___ \\ | || || '_ ` _ \\  / _ \\\n" +
                " |  _|  | || | | || (_| | ___) || || || | | | | ||  __/\n" +
                " |_|    |_||_| |_| \\__,_||____/ |_||_||_| |_| |_| \\___|\n\n" +
                " 找到最适合做史莱姆农场的区块们！\n Powered By WIFI连接超时 & 御河DE天街 & Imccark & Alan\n" +
                " GitHub: https://github.com/wifi504/find-slime-chunk"
        );
        System.out.println("------------------------------------------------------");

        //定义种子
        System.out.print("输入您的世界种子：");
        long worldSeed = scanner.nextLong();

        // 区域左上角坐标
        System.out.println("输入西北角区块坐标：");
        System.out.print("x=");
        int x1 = scanner.nextInt();
        System.out.print("z=");
        int z1 = scanner.nextInt();

        // 区域右下角坐标
        System.out.println("输入东南角区块坐标：");
        System.out.print("x=");
        int x2 = scanner.nextInt();
        System.out.print("z=");
        int z2 = scanner.nextInt();

        System.out.println("-----------------------------");
        System.out.println("请稍等...");

        // 计时器
        long timestampStart = System.currentTimeMillis();

        // 遍历一个区域内哪些是史莱姆区块(slime)，并置1
        int[][] slime = new int[z2 - z1 + 1][x2 - x1 + 1];
        for (int z = 0; z < slime.length; ++z) {
            for (int x = 0; x < slime[z].length; ++x) {
                if (CheckSlimeChunk.isSlimeChunk(worldSeed, x + x1, z + z1)) {
                    slime[z][x] = 1;
                } else {
                    slime[z][x] = 0;
                }

            }
        }

        // 创建直径为17的圆形蒙板
        int[][] mark = {
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        };

        // 创建棋盘遍历数组，其值为蒙板左上角在该点时史莱姆区块数量
        int[][] findCircle = new int[z2 - z1 + 2 - mark.length][x2 - x1 + 2 - mark[0].length];

        // 遍历棋盘
        for (int z = 0; z < findCircle.length; ++z) {
            for (int x = 0; x < findCircle[z].length; ++x) {
                // 遍历蒙板
                findCircle[z][x] = 0;
                for (int z0 = 0; z0 < mark.length; ++z0) {
                    for (int x0 = 0; x0 < mark[0].length; ++x0) {
                        // 判断史莱姆区块可用
                        if (mark[z0][x0] == 1 && slime[z + z0][x + x0] == 1) {
                            // 计数
                            ++findCircle[z][x];
                        }
                    }
                }
            }
        }

        // 从结果中找到前一百项并排序
        int[][] sort = tools.sort(findCircle);

        // 计时器
        long timestampEnd = System.currentTimeMillis();
        double timeConsume = (timestampEnd - timestampStart) / 1000.0;

        // 输出结果
        System.out.println("检索完成！耗时" + timeConsume + "秒！");
        System.out.println("从" + (x2 - x1) * (z2 - z1) + "个区块中\n找到落在直径为17个区块的圆中的史莱姆区块信息如下：");
        System.out.println("ID\t可用区块数\t区块坐标(x,z)");
        for (int i = 0; i < sort.length; ++i) {
            System.out.print(i + 1 + "\t");
            System.out.print(findCircle[sort[i][0]][sort[i][1]] + "\t\t");

            System.out.print("(" + (sort[i][1] + x1) + ", ");
            System.out.print((sort[i][0] + z1) + ")\n");

        }

    }
}
