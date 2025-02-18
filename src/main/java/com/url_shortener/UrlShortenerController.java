package com.url_shortener;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

import static com.url_shortener.UrlService.*;

@SpringBootApplication
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/url-shortener")
public class UrlShortenerController{
    //Access url repository
    //private final UrlShortenerRepository urlShortenerRepository;

    private final UrlService urlService;
    private final Shortener shortener;
    private final UrlShortenerRepository urlShortenerRepository;


    public UrlShortenerController(UrlShortenerRepository urlShortenerRepository, UrlService urlService, Shortener shortener){
        this.urlService = urlService;
        this.shortener = shortener;
        this.urlShortenerRepository = urlShortenerRepository;
    }
    //Api: retrieve urls by id
    @GetMapping
    public List<UrlShortener> getUrlShortenerById() {
        return urlService.getUrlShortener();
    }
    //Api: use original url and convert to short
    @PostMapping("/short-url")
    public String convertToShortUrl(@RequestBody UrlRequest urlRequest) {
        return urlService.shortenUrl(urlRequest);
    }
    @PostMapping("/alias")
    public String alias(@RequestBody UrlRequest urlRequest) {
       return urlService.alias(urlRequest);
    }
    //Api: delete urls by idshort
    @DeleteMapping("/delete/{id}")
    public void deleteUrlById(@PathVariable Integer id) {
        urlService.deleteUrl(id);
    }
    //Api: updates longUrl by id
    @PutMapping("/update/{id}")
    public void updateUrlById(@PathVariable Integer id, @RequestBody UrlRequest urlRequest) {
        urlService.updateUrl(id, urlRequest);
    }

    /*
    Api: redirects to original url
    Method: getRedirection
    Description:
    Uses the short url passed to find corresponding original url,
    localhost:8080/url-shortener/{shortUrl} must be passed to work.
    Example:  localhost:8080/url-shortener/qp
    Will redirect to youtube.
     */
   @GetMapping("/{shortUrl}")
    public RedirectView getRedirection(@PathVariable String shortUrl) {
       Optional<UrlShortener> urlOpt = urlShortenerRepository.findByUrl(shortUrl);
       if (urlOpt.isPresent()) {
           String original = urlOpt.get().getOriginalUrl();
           return new RedirectView(original);
       }
       else{
           return new RedirectView("COMPUTER SAYS NO!!!");
       }

   }
}