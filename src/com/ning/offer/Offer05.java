package com.ning.offer;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 示例 1：
 * <p>
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * <p>
 * 限制：
 * <p>
 * 0 <= s 的长度 <= 10000
 */
public class Offer05 {
    public static void main(String[] args) {
        System.out.println(replaceBlank("1 2 3 4"));
    }

    public static String replaceBlank(String s) {
        int length = s.length();
        char[] result = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (32 == c) {
                result[size++] = '%';
                result[size++] = '2';
                result[size++] = '0';
            } else {
                result[size++] = c;
            }
        }
        return new String(result, 0, size);
    }
    /*
    storage
     */
}
