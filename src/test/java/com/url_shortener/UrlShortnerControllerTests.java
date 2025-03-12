package com.url_shortener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortnerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    UrlService urlService;

    @MockitoBean
    UrlShortenerRepository repo;

    UrlShortener url1 = new UrlShortener(1001, "http://www.google.com", "go");
    UrlShortener url2 = new UrlShortener(1002, "http://www.facebook.com", "fb");
    UrlShortener url3 = new UrlShortener(1003, "http://www.youtube.com", "yt");

    @Test
    public void getAllUrlsTest() throws Exception {
        List<UrlShortener> urls = List.of(url1, url2, url3);


        when(urlService.getUrlShortener()).thenReturn(urls);

        ResultActions resultActions = mockMvc.perform(get("/url-shortener")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(jsonPath("$.size()").value(3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3));
    }

    @Test
    public void getUrlByIdTest() throws Exception {
        Optional<UrlShortener> url = Optional.of(new UrlShortener
                (
                        1001,
                        "http://www.test.com",
                        "gi"
                ));

        when(urlService.getUrlById(1001)).thenReturn(url);

        ResultActions resultActions = mockMvc.perform(get("/url-shortener/get/{id}", 1001));
        resultActions.andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1001))
                .andExpect(jsonPath("$.originalUrl").value("http://www.test.com"))
                .andExpect(jsonPath("$.shortenedUrl").value("gi"))
                .andDo(print());
    }

    @Test
    public void deleteUrlByIdTest() throws Exception {
        UrlShortener url = new UrlShortener(1001, "http://www.test.com", "gi");

        when(urlService.deleteUrl(url.getId())).thenReturn(url);

        ResultActions resultActions = mockMvc.perform(delete("/url-shortener/delete/{id}", 1001));

        resultActions.andExpect(status().isOk());

        verify(urlService).deleteUrl(url.getId());
    }

    @Test
    public void getRedirectionUrlFoundTest() throws Exception {
        UrlShortener url = new UrlShortener(1001, "http://www.google.com", "gi");
        String shortUrl = url.getShortenedUrl();

        when(repo.findByUrl(shortUrl)).thenReturn(Optional.of(url));

        ResultActions resultActions = mockMvc.perform(get("/url-shortener/{shortUrl}", shortUrl)
        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isFound())
                .andExpect(header().string("Location", url.getOriginalUrl()));

    }

    @Test
    public void getRedirectionUrlNotFoundTest() throws Exception {
        String nonExistentShortUrl = "i-am-not-really-a-short-url";

        when(repo.findByUrl(nonExistentShortUrl)).thenReturn(Optional.empty());

        ResultActions resultActions = mockMvc.perform(get("/url-shortener/{shortUrl}", nonExistentShortUrl)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isNotFound());

    }


}
