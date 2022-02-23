package com.changing.party.merge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;

public class CalcPoint {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Ziv\\Desktop\\活頁簿1.csv"));
        LinkedHashMap<Integer, Integer> groupByUser = new LinkedHashMap<>();
        for (String line : lines) {
            String[] split = line.split(",");
            int userId = Integer.parseInt(split[1]);
            int point = Integer.parseInt(split[2]);
            Integer userValue;
            if (groupByUser.containsKey(userId)) {
                userValue = groupByUser.get(userId);
            } else {
                userValue = 0;
            }
            userValue += point;
            groupByUser.put(userId, userValue);
        }
        for (Integer integer : groupByUser.keySet()) {
            System.out.println(String.format("%s", groupByUser.get(integer)));
        }
    }
}
