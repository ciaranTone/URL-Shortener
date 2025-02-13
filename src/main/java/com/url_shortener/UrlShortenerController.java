package com.url_shortener;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

import static com.url_shortener.UrlService.*;

@SpringBootApplication
@RestController
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
    @GetMapping("{id}")
    public UrlShortener getUrlShortenerById(@PathVariable Integer id) {
        return urlService.getUrlShortener(id);
    }
    //Api: use original url and convert to short
    @PostMapping("/short-url")
    public String convertToShortUrl(@RequestBody UrlRequest urlRequest) {
        return urlService.shortenUrl(urlRequest);
    }
    //Api: delete urls by id
    @DeleteMapping("/delete/{id}")
    public void deleteUrlById(@PathVariable Integer id) {
        urlService.deleteUrl(id);
    }
    //Api: updates longUrl by id
    @PutMapping("/update/{id}")
    public void updateUrlById(@PathVariable Integer id, @RequestBody UrlRequest urlRequest) {
        urlService.updateUrl(id, urlRequest);
    }

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