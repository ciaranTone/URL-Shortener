package com.url_shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/urlshortener")
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
    record CustomerRequest(String email, String name, String longUrl){}
    //Add a customer with url
    @PostMapping
    public void addCustomer(@RequestBody CustomerRequest customerRequest){
        Customer newCustomer = new Customer();
        newCustomer.setEmail(customerRequest.email);
        newCustomer.setName(customerRequest.name);
        newCustomer.setLongUrl(customerRequest.longUrl);
        customerRepository.save(newCustomer);
    }

    /*
    Function needed to shorten url
     */

    //Delete customer by id
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer Id){
        customerRepository.deleteById(Id);
    }

    //Update customers from the table by id
    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody CustomerRequest customerRequest){
        Customer customer = customerRepository.findById(id).get();
        customer.setName(customerRequest.name);
        customer.setEmail(customerRequest.email);
        customerRepository.save(customer);
    }



}
