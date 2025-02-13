package com.url_shortener;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Service
public class UrlService {
    private final UrlShortenerRepository urlShortenerRepository;
    private final Shortener shortener;

    record UrlRequest(Integer id, String originalUrl, String shortenedUrl) {
        public String getOriginalUrl() {
            return originalUrl;
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

    /*
    Method: getUrlShortener
    Description:
    Find urls by id.
    Displays original and short url
     */
    public UrlShortener getUrlShortener(Integer id) {
        return urlShortenerRepository.findById(id).orElse(null);
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
    public void updateUrl(Integer id, UrlRequest urlRequest) {
        UrlShortener url = urlShortenerRepository.findById(id).orElse(null);
        url.setOriginalUrl(urlRequest.getOriginalUrl());
        //url.setShortenedUrl(url.getShortenedUrl());
        var entity = urlShortenerRepository.save(url);
        urlShortenerRepository.save(entity);
    }

    /* ToDO: Redirect short url */
}