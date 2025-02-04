package com.url_shortener;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )

    private int id;
    private String email;
    private String name;
    private String shortUrl;
    private String longUrl;

    public Customer(Integer id, String email, String name, String shortUrl, String longUrl) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public Customer() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(validateCustomerName(name)) {
            this.name = name;
        }
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        if (validateCustomerLongUrl(longUrl)){
            this.longUrl = longUrl;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(email, customer.email) && Objects.equals(name, customer.name) && Objects.equals(shortUrl, customer.shortUrl) && Objects.equals(longUrl, customer.longUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, shortUrl, longUrl);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", longUrl='" + longUrl + '\'' +
                '}';
    }
/*
Validations
 */
    public boolean validateCustomerName(String name){
        if(name != null){
            if(name.length() <2 || name.length() > 50){
                return false;
            }
        }
        return true;
    }

    public boolean validateCustomerLongUrl(String longUrl){
        if(longUrl != null){
            if(longUrl.length() <= 10 || longUrl.length() > 200){
                return false;
            }
        }
        return true;
    }

    public boolean validateCustomerEmail(String email){
        if(email != null){
            if(email.length() < 12 || email.length() > 50){
                return false;
            }
        }
        return true;
    }
}
