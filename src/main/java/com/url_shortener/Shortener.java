
package com.url_shortener;

import org.springframework.stereotype.Service;

@Service
public class Shortener {
    protected String shortenURL(int n){
        //Map to store possible characters (62)
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        //String buffer can be modified.
        // It contains particular sequence of chars
        StringBuffer sUrl = new StringBuffer();
        // sUrl.append("urlShortener");
        //continues until n / 62 returns 0
        while(n > 0) {
            sUrl.append(map[n % 62]);
            n = n / 62;
        }
        //Reverse needed to complete base conversion
        sUrl.reverse();
        sUrl.insert(0, "http://urlshortener/");
        return sUrl.toString();
    }
    /*
    Function to trace back to original id given
     */
    protected int shortUrlToId(String shortURL){
        int id = 0;
        //base conversion logic
        for(int i = 0; i < shortURL.length(); i++){
            if('a' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'z'){
                id = id * 62 + shortURL.charAt(i) - 'a';
            }
            if ('A' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'Z'){
                id = id * 62 + shortURL.charAt(i) - 'A' + 26;
            }
            if('0' <= shortURL.charAt(i) && shortURL.charAt(i) <= '9'){
                id = id * 62 + shortURL.charAt(i) - '0' + 52;
            }
        }
        return id;
    }

    public static void main(String[] args) {
        int n = 54903;
        String shortUrl = new Shortener().shortenURL(n);
        System.out.println("Generated short url is: " + shortUrl);
        System.out.println("ID from url is: " + new Shortener().shortUrlToId(shortUrl));
    }
}
