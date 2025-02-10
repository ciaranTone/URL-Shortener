package com.url_shortener;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlService {
    private final UrlShortenerRepository urlShortenerRepository;
    private final Shortener shortener;

    record UrlRequest(Integer id, String originalUrl, String shortenedUrl) {
        public String getOriginalUrl() {
            return originalUrl;
        }

        public String getShortenedUrl() {
            return shortenedUrl;
        }
    }

    public UrlService(UrlShortenerRepository urlShortenerRepository, Shortener shortener) {
        this.urlShortenerRepository = urlShortenerRepository;
        this.shortener = shortener;
    }

    /*
    Method: shortenUrl
    Description:
    Allows the original url to be entered.
    Url gets an id associated with it.
    ID is then used to convert the originalUrl to a shortUrl.
    All urls are saved to the database.
     */
    public String shortenUrl(UrlRequest urlRequest) {
        //Creates and instance of class
        UrlShortener urlShortener = new UrlShortener();
        //Sets the original url with input given
        urlShortener.setOriginalUrl(urlRequest.getOriginalUrl());
        //Variable used to save entity as a urlShortener(Original URL saved)
        var entity = urlShortenerRepository.save(urlShortener);
        String shortUrl = shortener.shortenURL(entity.getId());
        //Sets and saves shortened URL
        entity.setShortenedUrl(shortUrl);
        urlShortenerRepository.save(entity);
        return shortUrl;
    }

    public List<UrlShortener> getUrl() {
        return this.urlShortenerRepository.findAll();
    }
}