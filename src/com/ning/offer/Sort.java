package com.ning.offer;

import java.util.Arrays;

/**
 * 排序
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class Sort {
    private static final int[] arr = new int[]{5, 8, 3, 9, 2, 0, 7, 4, 1, 6};

    public static void main(String[] args) {
        Arrays.stream(arr).forEach(System.out::print);
        quickSort(arr, 0, arr.length - 1);
        System.out.println();
        Arrays.stream(arr).forEach(System.out::print);
    }

    //快排：On*logN -> On^2
    public static void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int base = nums[l];
        int i = l;
        int j = r;
        while (i != j) {
            //从右往左 定位到第一个大于等于base的数
            while (nums[j] >= base && i < j) {
                j--;
            }
            //从左往右 定位到第一个小于等于base的数
            while (nums[i] <= base && i < j) {
                i++;
            }
            //交换这两个数
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        //上面的循环跳出条件：i==j，代表基准值应该在的下标。
        //基准值归位
        nums[l] = nums[i];
        nums[i] = base;
        //递归对子数组进行排序
        quickSort(nums, l, r - 1);
        quickSort(nums, l + 1, r);
    }
}
