package com.lhl.mc.ver01;

/**
 * @author WIFI连接超时
 * @version 1.0
 * Create Time: 2023/08/04_2:27
 */

// 用于缓存待排序数组的值与索引
class Element implements Comparable<Element> {
    int value;
    int row;
    int col;

    public Element(int value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Element o) {
        return Integer.compare(this.value, o.value);
    }
}
