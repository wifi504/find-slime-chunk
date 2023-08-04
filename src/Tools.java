import java.util.PriorityQueue;

/**
 * @author WIFI连接超时
 * @version 1.0
 * Create Time: 2023/08/04_0:51
 */
public class Tools {

    // 打印二维数组
    public void print(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + ", ");
            }
            System.out.println();
        }
    }

    // 数组排序
    public int[][] sort(int[][] arr) {
        // 创建一个缓存前一百个区块数据的数组
        int[][] sorted = new int[100][2];

        // 用优先队列来存储这些 Element 对象，并在队列大小超过100时移除队列中的最小元素
        PriorityQueue<Element> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[i].length; ++j) {
                pq.offer(new Element(arr[i][j], i, j));
                if (pq.size() > 100) {
                    pq.poll();
                }
            }
        }

        // 从大到小将排序结果置于返回数组
        for (int i = 99; i >= 0; --i) {
            Element e = pq.poll();
            assert e != null;
            sorted[i][0] = e.row;
            sorted[i][1] = e.col;
        }

        return sorted;
    }
}
