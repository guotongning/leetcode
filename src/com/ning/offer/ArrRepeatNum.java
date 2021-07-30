package com.ning.offer;

/**
 * 找到数组里重复的一个数
 */
public class ArrRepeatNum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 0};
        System.out.println(solution(nums));
    }

    public static int solution(int[] nums) {
        int index = 0;
        while (index < nums.length) {
            if (nums[index] == index) {
                index++;
                continue;
            }
            if (nums[nums[index]] == nums[index]) {
                return nums[index];
            }
            int temp = nums[index];
            nums[index] = nums[temp];
            nums[temp] = temp;
        }
        return -1;
    }
}
