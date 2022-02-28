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
            if (i >= nums1.length) {
                res[k++] = nums2[j++];
            } else if (j >= nums2.length) {
                res[k++] = nums1[i++];
            } else if (nums1[i] < nums2[j]) {
                res[k++] = nums1[i++];
            } else {
                res[k++] = nums2[j++];
            }
        }
        return res;
    }
}
