package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] values = {4, 2, 4, 4, 1, 6, 4, 4, 4};
        List<Integer> integers = List.of(4, 2, 3, 5, 1, 6, 5, 5, 4);

        System.out.println(minValue(values));
        System.out.println(oddOrEven(integers));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((acc, el) -> acc * 10 + el)
                .getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        boolean takeOddNumbers = integers.stream()
                .mapToInt(Integer::intValue)
                .sum() % 2 == 0;
        return integers.stream()
                .collect(Collectors.partitioningBy(el -> el % 2 != 0))
                .get(takeOddNumbers);
    }
}
