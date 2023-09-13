package com.ning.eat;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    //随机次数
    private final static int RANDOM_FACTOR = 100;
    //食物列表
    public static List<String> foods() {
        return Arrays.asList(
                "魏家凉皮",
                "海盗虾饭",
                "和合谷",
                "小恒水饺",
                "南城香",
                "阿香米线",
                "田老师",
                "胡辣汤",
                "驴肉火烧",
                "赛百味"
        );
    }

    public static void main(String[] args) {
        //待选食物列表
        List<String> foods = foods();
        //随机结果收集
        List<String> res = new ArrayList<>();
        //随机
        for (int i = 0; i < RANDOM_FACTOR; i++) {
            int foodIndex = new Random().nextInt(foods.size() - 1);
            res.add(foods.get(foodIndex));
        }
        //分组
        Map<String, List<String>> resMap = res.stream().collect(Collectors.groupingBy(k -> k, Collectors.toList()));
        //统计
        Map<String, Integer> countMap = resMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
        //排序
        String food = countMap.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).orElseThrow(() -> new RuntimeException("啊啊啊，我死了。")).getKey();
        //结果
        System.out.println("今天吃：" + food);
    }

}
