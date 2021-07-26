package com.ning.offer;

import com.sun.xml.internal.fastinfoset.stax.factory.StAXOutputFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 * <p>
 * 限制：
 * 2 <= n <= 100000
 * 不允许使用额外空间
 */
public class Offer03 {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 4, 3, 6, 0, 1};
        System.out.println(findRepeatNumber(arr));
    }

    public static int findRepeatNumber(int[] nums) {
        int i = 0;
        while (true) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) {
                return nums[i];
            }
            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
    }
}
