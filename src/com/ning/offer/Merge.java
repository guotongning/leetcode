package com.ning.offer;

/**
 * 合并有序数组
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class Merge {
    private static final int[] nums1 = new int[]{1, 2, 3, 4, 5};
    private static final int[] nums2 = new int[]{2, 2, 5, 6};

    public static void main(String[] args) {
        for (int i : merge(nums1, nums2)) {
            System.out.println(i);
        }
    }

    public static int[] merge(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        while (k < res.length) {
            res[k++] = i >= nums1.length ? nums2[j++] : j >= nums2.length ? nums1[i++] : nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        return res;
    }
}
