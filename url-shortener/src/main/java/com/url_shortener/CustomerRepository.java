package com.url_shortener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByLongUrl(String longUrl);
    public Customer findByShortUrl(String shortUrl);
}
