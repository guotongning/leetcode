package com.ning.poker.ddz.test;

import com.ning.poker.base.api.PokerGroup;
import com.ning.poker.ddz.poker_group.DDZPokerGroupFactory;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PokerGroup group1 = DDZPokerGroupFactory.getInstance().create("BJ", "SJ");
        PokerGroup group2 = DDZPokerGroupFactory.getInstance().create("2", "2", "2", "2");
        compare(group1, group2);
        compare(group2, group1);

        PokerGroup group3 = DDZPokerGroupFactory.getInstance().create("3", "4", "5", "6", "7");
        PokerGroup group4 = DDZPokerGroupFactory.getInstance().create("4", "5", "6", "7", "8");
        PokerGroup group5 = DDZPokerGroupFactory.getInstance().create("A", "K", "Q", "J", "10");
        compare(group3, group4);
        compare(group4, group2);
        compare(group1, group4);
        compare(group1, group5);
        compare(group5, group4);

        PokerGroup group6 = DDZPokerGroupFactory.getInstance().create("A", "A", "A", "10", "10");
        PokerGroup group7 = DDZPokerGroupFactory.getInstance().create("K", "K", "K", "5", "5");
        compare(group1, group6);
        compare(group7, group6);

        PokerGroup group8 = DDZPokerGroupFactory.getInstance().create("A", "A", "A", "10");
        PokerGroup group9 = DDZPokerGroupFactory.getInstance().create("K", "K", "K", "5");
        compare(group1, group9);
        compare(group8, group9);

        PokerGroup group10 = DDZPokerGroupFactory.getInstance().create("K", "K", "K", "K", "5", "5");
        PokerGroup group11 = DDZPokerGroupFactory.getInstance().create("A", "A", "A", "A", "6", "8");
        compare(group1, group11);
        compare(group10, group11);
    }

    private static void compare(PokerGroup group1, PokerGroup group2) {
        System.out.printf("%s compareTo %s, result=[%s]\r\n", Arrays.toString(group1.show()), Arrays.toString(group2.show()), group1.compareTo(group2));
    }
}