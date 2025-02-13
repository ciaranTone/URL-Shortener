package com.url_shortener;

import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlShortenerRepository extends JpaRepository<UrlShortener, Integer> {
    //Query to find
    @Query (value = "SELECT * FROM url_shortener WHERE shortened_url = :shortUrl", nativeQuery = true)
    Optional<UrlShortener> findByUrl(String shortUrl);
}