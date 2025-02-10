package com.url_shortener;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("url-shortener")
public class UrlShortenerController {
    //Access url repository
    private final UrlShortenerRepository urlShortenerRepository;

    public UrlShortenerController(UrlShortenerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    //Get all urls in table
    @GetMapping
    public List<UrlShortener> getUrlShortener() {
        return this.urlShortenerRepository.findAll();
    }

    record UrlRequest(Integer id, String originalUrl, String shortenedUrl) {}

    @PostMapping("addUrl")
    public void addUrl(@RequestBody UrlRequest urlRequest) {
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
    }
}
