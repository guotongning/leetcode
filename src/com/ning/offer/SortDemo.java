package com.ning.offer;

import java.util.Arrays;

/**
 * 1. [1~10000] 区间内随机生成100个整数，放到list里。然后排序，奇数再左边。偶数再有边。
 * 例如：1 2 3 4 5 6 7
 * 结果：7 5 3 1 6 4 2
 * 2. 遍历第一步得到的list，删除能被3整除得数。然后打印。
 *
 * @author <a href="guotongning@126.com">Nicholas</a>
 * @since 1.0.0
 * Created on 2021/5/11 22:32
 */
public class SortDemo {
    private static final int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};

    public static void main(String[] args) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            if (arr[i] % 2 == 0) {
                if (arr[j] % 2 != 0) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                } else {
                    j--;
                }
            } else {
                i++;
            }
        }
        Arrays.stream(arr).forEach(System.out::println);
    }
}
