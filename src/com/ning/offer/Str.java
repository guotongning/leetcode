package com.ning.offer;

/**
 * 字符串相关题目
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since
 */
public class Str {

    public static void main(String[] args) {
//        String trans = trans("12345", 3);
//        System.out.println(trans);
        int[] ints = maxSlidingWindow(new int[]{9, 11, 3, 1, 2}, 2);
        System.out.println(ints);
    }

    // 旋转字符串
    public static String trans(String s, int n) {
        if ("".equals(s) || n == 0) {
            return s;
        }
        char[] newStr = new char[s.length()];
        char[] oldStr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (i < n) {
                newStr[newStr.length - n + i] = oldStr[i];
            } else {
                newStr[i - n] = oldStr[i];
            }
        }
        return new String(newStr);
    }

    //滑动窗口的最大值
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int left = 0;
        int right = k;
        int[] res = new int[nums.length - k + 1];
        int first = nums[left];
        int second = nums[left];
        while (right <= nums.length) {
            if (left == 0) {
                for (int i = left + 1; i < right; i++) {
                    if (nums[i] > first) {
                        second = first;
                        first = nums[i];
                    }
                }
            } else {
                if (nums[left - 1] == first) {
                    if (nums[right - 1] > second) {
                        first = nums[right - 1];
                    }
                }
                if (nums[left - 1] == second) {
                    if (nums[right - 1] > first) {
                        first = nums[right - 1];
                    }
                }
            }
            res[left++] = first;
            right++;
        }
        return res;
    }


}
