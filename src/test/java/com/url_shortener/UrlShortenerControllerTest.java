package com.url_shortener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UrlShortenerControllerTest {

    @Autowired
    private UrlShortenerController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}