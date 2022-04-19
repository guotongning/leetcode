package com.ning.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 搜索
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class Search {
    private static final int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final String str = "dvdf";


    public static void main(String[] args) {
        System.out.println(midSearch(arr, 5));
        System.out.println(lengthOfLongestSubstring(str));
    }

    //寻找最大不重复子串。
    public static int lengthOfLongestSubstring(String s) {
        int r = s.length(), l = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < r; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            l = Math.max(l, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return l;
    }

    public static int midSearch(int[] arr, int target) {
        int l = 0;
        int h = arr.length - 1;
        if (arr.length == 0 || target > arr[h] || target < arr[l]) {
            return -1;
        }
        int mid;
        while (l <= h) {
            //每次循环重新计算 mid下标 很关键。
            mid = (h - l) / 2 + l;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                l = mid + 1;
            } else if (arr[mid] > target) {
                h = mid - 1;
            }
        }
        return -1;
    }
}
