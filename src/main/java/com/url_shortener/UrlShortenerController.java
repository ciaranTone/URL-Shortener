package com.url_shortener;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.url_shortener.UrlService.*;

@SpringBootApplication
@RestController
@RequestMapping("/url-shortener")
public class UrlShortenerController {
    //Access url repository
    //private final UrlShortenerRepository urlShortenerRepository;

    private final UrlService urlService;


    public UrlShortenerController(UrlShortenerRepository urlShortenerRepository, UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/short-url")
    public String convertToShortUrl(@RequestBody UrlRequest urlRequest) {
        return urlService.shortenUrl(urlRequest);
    }
    //Get all urls in table

   /* record UrlRequest(Integer id, String originalUrl, String shortenedUrl) {}

    @PostMapping("/addUrl")
    public void addUrl(@RequestBody UrlRequest urlRequest, Integer id) {
        UrlShortener urlShortener = new UrlShortener();
        urlShortener.setOriginalUrl(urlRequest.originalUrl);
        urlShortenerRepository.save(urlShortener);
    }

    @PutMapping("/updateUrl/{urlId}")
    public void updateUrl(@PathVariable("urlId") Integer id,@RequestBody UrlRequest urlRequest) {
        Shortener shortener = new Shortener();
        UrlShortener urlShortener = urlShortenerRepository.findById(id).get();
        urlShortener.setShortenedUrl(shortener.shortenURL(urlShortener.getId()));
        urlShortenerRepository.save(urlShortener);
    }

    @DeleteMapping("/deleteUrl/{urlId}")
    public void deleteUrl(@PathVariable("urlId") Integer id) {
        urlShortenerRepository.deleteById(id);
    }*/
}