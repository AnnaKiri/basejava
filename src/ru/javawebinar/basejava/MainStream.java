package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] values = {4, 2, 4, 4, 1, 6, 4, 4, 4};
        List<Integer> integers = List.of(4, 2, 3, 5, 1, 6, 4, 5, 4);

        System.out.println(minValue(values));
        System.out.println(oddOrEven(integers));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, el) -> acc * 10 + el);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers.stream()
                .collect(Collectors.partitioningBy(el -> el % 2 != 0));

        boolean takeOddNumbers = map.get(true).size() % 2 == 0;

        return takeOddNumbers ? map.get(true) : map.get(false);
    }
}