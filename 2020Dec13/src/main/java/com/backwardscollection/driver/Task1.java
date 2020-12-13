package com.backwardscollection.driver;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Task1 {
    
    private static final int TIME_STAMP = 0;
    private static final int BUS_IDS = 1;
    private static final int DEFAULT_RADIX = 10;
    
    public static void main(String[] args) {
        
        // Obtain Input
        var input = obtainInput();
        
        // Convert Input into usable model objects
        var inputParts = input.split("\n");
        var timestamp = Integer.valueOf(inputParts[TIME_STAMP]);
        var busIds = Arrays.stream(inputParts[BUS_IDS].split(","))
                .filter(value -> isInteger(value, DEFAULT_RADIX))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        System.out.println(String.format("TIME STAMP: %s", timestamp));
        System.out.println(String.format("BUS IDS: %s", busIds.toString()));
        
        // Calculate the bus to hop on next
        var busToMinutesFromStation = busIds.stream()
                .collect(Collectors.toMap(UnaryOperator.identity(), busId ->
                        calculateMinutesBusToStation(busId, timestamp)));
        //Print Times
        busToMinutesFromStation.forEach((busId, seconds) -> {
            System.out.printf("BUS ID: %s MINUTES: %s\n", busId, seconds);
        });
        var nextBusIdToSeconds = busToMinutesFromStation.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).orElseThrow();
        var nextBusId = nextBusIdToSeconds.getKey();
        var minutesToBus = nextBusIdToSeconds.getValue();
        System.out.printf("NEXT BUS ID: %s MINUTES: %s BUS ID * MINUTES = %s", nextBusId, minutesToBus, nextBusId * minutesToBus);
    }
    
    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty())
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1)
                    return false;
                else
                    continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0)
                return false;
        }
        return true;
    }
    
    /**
     * Advent of code input is different for each user so instead of doing a HTTP Get I just copied
     *
     * @return input from the advent of code (for my user)
     */
    private static String obtainInput() {
//        var client = HttpClient.newHttpClient();
//        var request = HttpRequest.newBuilder()
//                .uri(URI.create("https://adventofcode.com/2020/day/13/input"))
//                .build();
//        HttpResponse<String> response =
//                client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.statusCode());
//        System.out.println(response.body());
//        return response.body();
        
        return """
                1006605
                19,x,x,x,x,x,x,x,x,x,x,x,x,37,x,x,x,x,x,883,x,x,x,x,x,x,x,23,x,x,x,x,13,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,x,x,797,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,29
                """;
    }
    
    /**
     * Buses come and go at certain intervals
     * To determine when the next bus will come we take the timestamp % busId
     * which will determine when that bus will be at the station again in minutes
     * lower the amount
     *
     * @param busId     : id of the Bus (which is used for when bus will be located at station)
     * @param timestamp : current time
     * @return minutes at which the bus will be at the station again
     */
    private static int calculateMinutesBusToStation(int busId, int timestamp) {
        return busId - (timestamp % busId);
    }
}
