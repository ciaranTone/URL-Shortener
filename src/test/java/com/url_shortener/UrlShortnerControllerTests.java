package com.url_shortener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
        .andExpect(status().isOk());
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
    public void convertToShortUrlTest() throws Exception {
        UrlService.UrlRequest request = new UrlService.UrlRequest(1001, "http://www.google.com", "tt");

        url1.setOriginalUrl("http://www.google.com");
        url1.setShortenedUrl("tt");

        when(urlService.shortenUrl(request)).thenReturn(request.getShortenedUrl());

        ResultActions resultActions = mockMvc.perform(post("/url-shortener/short-url")
        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isCreated());

    }
}
