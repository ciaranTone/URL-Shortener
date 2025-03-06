package com.url_shortener;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
    private final UrlShortenerRepository urlShortenerRepository;
    private final Shortener shortener;
    //Data transfer object (DTO)
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
        // Sets the original URL using the input from the UrlRequest
        urlShortener.setOriginalUrl(urlRequest.getOriginalUrl());
        //Variable used to save entity as a urlShortener(Original URL saved)
        var entity = urlShortenerRepository.save(urlShortener);
        String shortUrl = shortener.shortenURL(entity.getId());
        //Sets and saves shortened URL
        entity.setShortenedUrl(shortUrl);
        urlShortenerRepository.save(entity);
        return shortUrl;
    }

    /*
    Method: getUrlShortener
    Description:
    Find urls by id.
    Displays original and short url
     */
    public List<UrlShortener> getUrlShortener() {
        return urlShortenerRepository.findAll();
    }
    public Optional<UrlShortener> getUrlById(Integer id) {
        return urlShortenerRepository.findById(id);
    }

    /*
    Method: deleteUrl
    Description:
    Deletes all urls by id(along with the id)
     */
    public UrlShortener deleteUrl(Integer id) {
        UrlShortenerRepository repo = urlShortenerRepository;
        //Stores entity, is found in repo by id
        var entity = repo.findById(id).orElse(null);
        //Check to see if it is null
        if (entity != null) {
            repo.delete(entity);
        }
        return entity;
    }

    /*
    Method: updateUrl
    Description:
    Updates the long url by id.
    NOTE: hoping to get the updating of the short url here
     */
    public void updateShortUrl(Integer id, UrlRequest urlRequest) {
        UrlShortener url = urlShortenerRepository.findById(id).orElse(null);
        url.setShortenedUrl(urlRequest.getShortenedUrl());
        var entity = urlShortenerRepository.save(url);
        urlShortenerRepository.save(entity);
    }

    public void updateOriginalUrl(Integer id, UrlRequest urlRequest) {
        UrlShortener url = urlShortenerRepository.findById(id).orElse(null);
        url.setOriginalUrl(urlRequest.getOriginalUrl());
        var entity = urlShortenerRepository.save(url);
        urlShortenerRepository.save(entity);
    }

    public String alias(UrlRequest urlRequest){
        //Creates and instance of class
        UrlShortener urlShortener = new UrlShortener();
        // Sets the original URL using the input from the UrlRequest
        urlShortener.setOriginalUrl(urlRequest.getOriginalUrl());
        //Variable used to save entity as a urlShortener(Original URL saved)
        var entity = urlShortenerRepository.save(urlShortener);
        String shortUrl = urlShortener.setShortenedUrl(urlRequest.getShortenedUrl());
        //Sets and saves shortened URL
        entity.setShortenedUrl(shortUrl);
        urlShortenerRepository.save(entity);
        return shortUrl;
    }

}