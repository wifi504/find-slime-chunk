import java.util.Random;

/**
 * @author WIFI连接超时
 * @version 1.0
 * Create Time: 2023/08/03_23:30
 */
public class CheckSlimeChunk {

    // 从MCWiki上查得史莱姆区块判断源码
    // https://minecraft.fandom.com/zh/wiki/%E5%8F%B2%E8%8E%B1%E5%A7%86

    public static boolean isSlimeChunk(long worldSeed,    //世界种子，一个64位整数，可以通过/seed获取
                                       int chunkX,        // 区块X轴坐标，32位整数
                                       int chunkZ) {      // 区块Z轴坐标，32位整数
        Random rng = new Random(
                worldSeed +
                        (chunkX * chunkX) * 0x4C1906 +
                        (chunkX * 0x5AC0DB) +
                        (chunkZ * chunkZ) * 0x4307A7L +
                        (chunkZ * 0x5F24F) ^ 0x3AD8025F
        );
        return rng.nextInt(10) == 0;
    }
}
