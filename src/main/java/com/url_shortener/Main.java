package com.url_shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
//@RequestMapping("urlshortener/")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
}