package com.backwardscollection.driver;

import java.util.Arrays;
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
    
    private int determineBusToTake(int timeStamp){
    
    }
}
