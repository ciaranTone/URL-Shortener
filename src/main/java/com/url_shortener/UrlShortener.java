package com.url_shortener;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class UrlShortener {
    @Id
    @SequenceGenerator(
            name = "url_id_sequence",
            sequenceName = "url_id_sequence",
            initialValue = 1000,
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "url_id_sequence"
    )
    //Variables
    private int id;
    private String originalUrl;
    private String shortenedUrl;

    //Constructors
    public UrlShortener(Integer id, String originalUrl, String shortenedUrl) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortenedUrl = shortenedUrl;
    }

    public UrlShortener(){

    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }


    public void setOriginalUrl(String originalUrl) {
        if(validateOriginalUrl(originalUrl)){
            this.originalUrl = originalUrl;
        }

    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

<<<<<<< Updated upstream
    public void setShortenedUrl(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
=======
    public String setShortenedUrl(String shortenedUrl) {
        if(validateShortenedUrl(shortenedUrl)){
            this.shortenedUrl = shortenedUrl;
            return shortenedUrl;
        }
        return null;
>>>>>>> Stashed changes
    }


    //Validations
    boolean validateOriginalUrl(String originalUrl) {
      if(originalUrl != null && originalUrl.length() >= 10 && originalUrl.length() <= 200){
          return true;
      }
      return false;
    }

    boolean validateShortenedUrl(String shortenedUrl) {
        if(shortenedUrl != null && !shortenedUrl.isEmpty() && shortenedUrl.length() <= 50){
            return true;
        }
        return false;
    }
<<<<<<< Updated upstream:url-shortener/src/main/java/com/url_shortener/UrlShortener.java
}
=======
}
>>>>>>> Stashed changes:src/main/java/com/url_shortener/UrlShortener.java
