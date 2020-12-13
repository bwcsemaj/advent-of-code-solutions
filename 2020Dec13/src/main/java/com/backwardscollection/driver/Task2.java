package com.backwardscollection.driver;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task2 {
    
    public static void main(String[] args) {
        // Obtain Input
        var input = Task1.obtainInput();
        
        // Convert input into Model
        // Organize ids by highest to lowest
        var inputParts = input.split("\n");
        var timestamp = Long.valueOf(inputParts[Task1.TIME_STAMP]);
        var busIdsAsStringWithEmpties = inputParts[Task1.BUS_IDS].split(",");
        var busIdsToIndex = new TreeMap<Integer, Integer>(Collections.reverseOrder(Integer::compareTo));
        busIdsToIndex.putAll(IntStream.range(0, busIdsAsStringWithEmpties.length)
                .filter(value -> Task1.isInteger(busIdsAsStringWithEmpties[value], Task1.DEFAULT_RADIX))
                .boxed()
                .collect(Collectors.toMap(value -> Integer.valueOf(busIdsAsStringWithEmpties[value]),
                        UnaryOperator.identity())));
        System.out.println(String.format("TIME STAMP: %s", timestamp));
        System.out.println(String.format("BUS IDS: %s", busIdsToIndex.toString()));
        
        
        // Calculate earliest time when all bus ids
        // correlate to the index they are in regarding timestamp
        calculateEarliestTime(busIdsToIndex);
        
        // Display result
        
        
    }
    
    private static void calculateEarliestTime(TreeMap<Integer, Integer> busIdsToIndex) {
        var busIdsToIndexs = new ArrayList<>(busIdsToIndex.entrySet());
        var highestBusIdToIndex =
                busIdsToIndexs.get(0);
        
        //Set time
        long time = highestBusIdToIndex.getKey() - highestBusIdToIndex.getValue();
        long amountIncrease = highestBusIdToIndex.getKey();
        
        //Cycle through all while increasing amount by bus id
        for (int index = 1; busIdsToIndex.size() > index; index++) {
            var currentBusIdToIndex = busIdsToIndexs.get(index);
            var currentBusId = currentBusIdToIndex.getKey();
            var currentIndex = currentBusIdToIndex.getValue();
            System.out.println(index);
            while ((time + currentIndex) % currentBusId != 0) {
                System.out.println(time % currentBusId);
                System.out.println(currentIndex);
                time += amountIncrease;
                System.out.println(time);
            }
            amountIncrease *= currentBusId;
        }
        
        System.out.println(time);
    }
}
