package com.backwardscollection.driver;

import java.io.Serializable;

public class Pair<K, V> implements Serializable {
    
    // Attributes
    private static final long serialVersionUID = 4489935579804642083L;
    private final K first;
    private final V second;
    
    private Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
    
    // Start Methods
    public static <Y, W> Pair<Y, W> of(Y first, W second){
        return new Pair<>(first, second);
    }
    // End Methods
}
