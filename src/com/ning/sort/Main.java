package com.ning.sort;

import java.util.Arrays;

public class Main {

    private static final int[] arr = new int[]{1, 0, 7, 4, 3, 8, 9, 2, 6, 5};

    public static void main(String[] args) {
        Arrays.stream(arr).asLongStream().forEach(num -> System.out.printf("%s ", num));
        System.out.println();
        quickSort(arr, 0, arr.length - 1);
        Arrays.stream(arr).asLongStream().forEach(num -> System.out.printf("%s ", num));
    }

    // 快速排序函数
    public static void quickSort(int[] arr, int low, int high) {
        // 检查数组是否为空
        if (arr == null || arr.length == 0) {
            return;
        }
        // 检查低位是否小于高位
        if (low >= high) {
            return;
        }
        // 选择中间元素作为基准
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];
        // 将小于基准的元素放在左边，大于基准的元素放在右边
        int i = low, j = high;
        while (i <= j) {
            // 找到左边第一个大于等于基准的元素
            while (arr[i] < pivot) {
                i++;
            }
            // 找到右边第一个小于等于基准的元素
            while (arr[j] > pivot) {
                j--;
            }
            // 如果找到了两个元素，交换它们的位置
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // 对左右两个子数组递归调用快速排序函数
        if (low < j) {
            quickSort(arr, low, j);
        }

        if (high > i) {
            quickSort(arr, i, high);
        }
    }
}
