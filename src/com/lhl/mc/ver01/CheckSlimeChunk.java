package com.lhl.mc.ver01;

import java.util.Random;

/**
 * @author WIFI连接超时
 * @version 1.0
 * Create Time: 2023/08/03_23:30
 */
public class CheckSlimeChunk {

    /**
     * 从MCWiki上查得史莱姆区块判断源码
     * https://minecraft.fandom.com/zh/wiki/%E5%8F%B2%E8%8E%B1%E5%A7%86
     *
     * @param worldSeed 世界种子，一个64位整数，可以通过/seed获取
     * @param chunkX    区块X轴坐标，32位整数
     * @param chunkZ    区块Z轴坐标，32位整数
     * @return boolean
     */
    public static boolean isSlimeChunk(long worldSeed,
                                       int chunkX,
                                       int chunkZ) {
        Random rng = new Random(
                worldSeed +
                        (chunkX * chunkX) * 0x4C1906 +
                        (chunkX * 0x5AC0DB) +
                        (chunkZ * chunkZ) * 0x4307A7L +
                        (chunkZ * 0x5F24F) ^ 0x3AD8025F
        );
        return rng.nextInt(10) == 0;
    }

    /**
     * 得到一个史莱姆区块地图
     *
     * @param worldSeed 世界种子，一个64位整数，可以通过/seed获取
     * @param posX      西北角 x 坐标
     * @param posZ      西北角 z 坐标
     * @param width     需要的地图宽度
     * @param height    需要的地图高度
     * @return int[][]
     */
    public static int[][] getSlimeMap(long worldSeed,
                                      int posX, int posZ,
                                      int width, int height) {
        int[][] map = new int[height][width];
        for (int z = 0; z < height; z++) {
            for (int x = 0; x < width; x++) {
                if (CheckSlimeChunk.isSlimeChunk(worldSeed,
                        x + posX, z + posZ)) {
                    map[z][x] = 1;
                } else {
                    map[z][x] = 0;
                }
            }
        }
        return map;
    }
}
