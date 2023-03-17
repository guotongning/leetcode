package com.ning.txt;

public enum TestEnum {
    TEST {
        public int test(int num) {
            return num;
        }

        @Override
        public long test1(long num) {
            return num + 1;
        }
    },

    DUDUDU{
        @Override
        public long test1(long num) {
            return 0;
        }
    }
    ;

    public abstract long test1(long num);

    public int test(int num) {
        throw new AbstractMethodError();
    }
}
