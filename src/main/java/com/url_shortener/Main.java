package com.url_shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/urlshortener")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    //Access customer repository
    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Get all customer in the table
    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    //New customer record
    record CustomerRequest(Integer id, String email, String name, String longUrl, String shortUrl){}

    //Add a customer with url
    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody CustomerRequest customerRequest){
        Customer newCustomer = new Customer();
        newCustomer.setEmail(customerRequest.email);
        newCustomer.setName(customerRequest.name);
        newCustomer.setLongUrl(customerRequest.longUrl);
        customerRepository.save(newCustomer);
    }

    //Delete customer by id
    @DeleteMapping("/deleteCustomer/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    //Update customers from the table by id
    @PutMapping("/updateCustomer/{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody CustomerRequest customerRequest){
        Shortener shortener = new Shortener();
        Customer customer = customerRepository.findById(id).get();
        customer.setName(customerRequest.name);
        customer.setEmail(customerRequest.email);
        customer.setShortUrl(shortener.shortenURL(customer.getId()));
        customerRepository.save(customer);
    }
}
