package com.backwardscollection.driver;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class Task1 {
    
    public static void main(String[] args) {
        
        
        // Obtain Input
        
        // Convert Input into usable model objects
        
        // Calculate the bus to hop on next
        
    }
    
    
    private void String obtainInput(){
        var client = HttpClient.newBuilder()
                .version(Version.HTTP_2)
                .followRedirects(Redirect.SAME_PROTOCOL)
                .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
                .authenticator(Authenticator.getDefault())
                .build();
        
        var response =
                client.send(request, BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
