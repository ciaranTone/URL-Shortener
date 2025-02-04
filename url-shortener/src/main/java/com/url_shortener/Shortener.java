package com.url_shortener;

public class Shortener {
    protected String shortenURL(int n){
        //Map to store possible characters (62)
        char map[] = "012345689abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        //String buffer can be modified.
        // It contains [particular sequence of chars
        StringBuffer sUrl = new StringBuffer();

        while(n > 0){
            sUrl.append(map[n % 62]);
            n /= 62;
        }
        return sUrl.reverse().toString();
    }

    protected int shortUrlToId(String shortenURL){
        int id = 0;
        //base conversion logic
        for(int i = 0; i < shortenURL.length(); i++){
            if('a' <= shortenURL.charAt(i) && shortenURL.charAt(i) <= 'z'){
                id = id * 62 + shortenURL.charAt(i) - 'a';
            }
            if ('A' <= shortenURL.charAt(i) && shortenURL.charAt(i) <= 'Z'){
                id = id * 62 + shortenURL.charAt(i) - 'A' + 26;
            }
            if('0' <= shortenURL.charAt(i) && shortenURL.charAt(i) <= '9'){
                id = id * 62 + shortenURL.charAt(i) - '0' + 52;
            }
        }
        return id;
    }

    public static void main(String[] args) {
        int n = 12345;
        String shortUrl = new Shortener().shortenURL(n);
        System.out.println(shortUrl);
        System.out.println(new Shortener().shortUrlToId(shortUrl));
    }
}
