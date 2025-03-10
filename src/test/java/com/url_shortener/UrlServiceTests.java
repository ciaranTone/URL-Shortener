package com.url_shortener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UrlServiceTests {
    @Mock
    private UrlShortenerRepository repo;

    @Mock
    private Shortener shortener;

    @InjectMocks
    private UrlService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void shortenUrlTest() {
        UrlService.UrlRequest request = new UrlService.UrlRequest(
                null,
                "http://www.shorten-this-url",
                "");

        UrlShortener urlShortener = new UrlShortener();
        urlShortener.setOriginalUrl(request.getOriginalUrl());

        UrlShortener entity = new UrlShortener();
        entity.setId(1008);
        entity.setOriginalUrl(request.getOriginalUrl());

        when(repo.save(any(UrlShortener.class))).thenReturn(entity);

        String fakeShort = "hfhf";
        when(shortener.shortenURL(entity.getId())).thenReturn(fakeShort);

        String shortUrl = service.shortenUrl(request);

        assertEquals(fakeShort, shortUrl);
        assertNotNull(shortUrl);

    }

    @Test
    public void aliasTest(){
        UrlService.UrlRequest request = new UrlService.UrlRequest(
                null,
                "http://www.shorten-this-url",
                "");

        UrlShortener urlShortener = new UrlShortener();
        urlShortener.setOriginalUrl(request.getOriginalUrl());

        UrlShortener entity = new UrlShortener();
        entity.setId(1008);
        entity.setOriginalUrl(request.getOriginalUrl());

        when(repo.save(any(UrlShortener.class))).thenReturn(entity);

        String alias = urlShortener.setShortenedUrl(request.getShortenedUrl());

        String shortUrl = service.alias(request);
        assertEquals(alias, shortUrl);
        assertNotNull(shortUrl);
    }

    @Test
    public void getAllUrlsTest(){
        //Mock Data
        List<UrlShortener> urls = List.of(
                new UrlShortener(1009, "https://newurl.com", "fh"),
                new UrlShortener(1008, "https://newestUrl.com", "ft"),
                new UrlShortener(1007, "https://newerUrl.com", "fb")
        );
        //Ensure urls are returned when findAll() is used
        when(repo.findAll()).thenReturn(urls);//Mock Repository
        //Method from service
        List<UrlShortener> result = service.getUrlShortener();
        //Verify method was called on repository
        verify(repo, times(1)).findAll();
        //Check results are not null
        assertNotNull(result);
        //Check to see if urls matches the result
        assertEquals(urls, result);
        //Check to see if the sizes match
        assertEquals(urls.size(), result.size());

    }

    @Test
    public void getUrlByIdTest(){
        UrlShortener expectedUrl = new UrlShortener(1009, "http://newurl.com", "ff");
        //Ensures url returned when id entered
        when(repo.findById(1009)).thenReturn(Optional.of(expectedUrl));
        //Method from service
        Optional<UrlShortener> result = service.getUrlById(1009);
        //Verify method was called on repo
        verify(repo, times(1)).findById(1009);
        //Ensure result is present
        assertTrue(result.isPresent());
        //Check to see if url id matches the result
        assertEquals(expectedUrl, result.get());
    }

    @Test
    public void deleteUrlByIdTest(){
        UrlShortener entity = new UrlShortener(
                1009,
                "https://newurl.com",
                "fh");

        //Ensures url returned when id entered
        when(repo.findById(1009)).thenReturn(Optional.of(entity));
        //Method from service
        UrlShortener deleteEntity = service.deleteUrl(1009);
        //Verify method was called on repo
        verify(repo, times(1)).findById(1009);

        assertNotNull(deleteEntity);//Get deleted entry back
        assertEquals(entity, deleteEntity);//Ensure entities match
        //Ensure url searched for is not available
        when(repo.findById(1009)).thenReturn(Optional.empty());
        //Verify Url is no longer available after deletion
        assertNull(service.deleteUrl(1009));
    }

    @Test
    public void updateShortenedUrlTest(){
        //Original short url
        UrlShortener original = new UrlShortener(
                1009,
                "http://www.shorten-this-url",
                "fa"
        );
        //Updated short url
        UrlService.UrlRequest request = new UrlService.UrlRequest(
                1009,
                "http://www.shorten-this-url",
                "ff"
        );
        //Ensures url returned
        when(repo.findById(1009)).thenReturn(Optional.of(original));
        //Method being used
        service.updateShortUrl(1009, request);
        //Verify finById called
        verify(repo, times(1)).findById(1009);
        //Verify save called
        verify(repo, times(1)).save(original);
        //Check that it is not nul
        assertNotNull(original);
        //Check expected with actual
        assertEquals("ff", original.getShortenedUrl());
    }

    @Test
    public void updateOriginalUrlTest(){
        //Original url
        UrlShortener original = new UrlShortener(
                1009,
                "http://www.original-url-to-be-shortened.com",
                "ff"
        );
        //Updated url
        UrlService.UrlRequest request = new UrlService.UrlRequest(1009,
                "http://www.original-url-updated.ie",
                "ff"
                );
        //Ensure url is found
        when(repo.findById(1009)).thenReturn(Optional.of(original));
        //Method used to update
        service.updateOriginalUrl(1009, request);
        //Verify findById is called
        verify(repo, times(1)).findById(1009);
        //Check not null
        assertNotNull(original);
        //Check expected against actual
        assertEquals("http://www.original-url-updated.ie", original.getOriginalUrl());
        //Ensure updated version is saved
        verify(repo, times(1)).save(original);

    }
}
