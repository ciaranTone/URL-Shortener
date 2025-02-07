package com.url_shortener.url_shortener;

import com.url_shortener.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTests {
    /*
    Verify valid customer name(2 - 50 characters inclusive)
    Inputs: Ciaran
    Expected outputs: true
     */
    Customer testObj = new Customer();
    @Test
    void testValidateCustomerName01(){
        assertTrue(testObj.validateCustomerName("Ciaran"));//5 chars;
        assertTrue(testObj.validateCustomerName("Jo"));
    }

    /*
    Verify valid customer name less than min rejected
    Inputs: t
    Expected outputs: false
    */
    @Test
    void testValidateCustomerName02(){
        assertFalse(testObj.validateCustomerName("t"));
    }

    /*
    Verify valid customer name greater than max rejected
    Inputs: check if this is greater than fifty characters for testing purposes
    Expected outputs: false
    */
    @Test
    void testValidateCustomerName03(){
        assertFalse(testObj.validateCustomerName("check if this is greater than fifty characters for testing purposes"));
    }

    /*
    Verify valid customer long url (12 - 200 characters)
    Inputs: http://www.google.com
    Expected outputs: true
     */
    @Test
    void validateCustomerLongUrl(){
        assertTrue(testObj.validateCustomerLongUrl("http://www.google.com"));
        assertTrue(testObj.validateCustomerLongUrl("www.google.com"));
    }

    /*
    Verify valid customer long url less than min rejected
    Inputs: www.g.com
    Expected outputs: false
     */
    @Test
    void validateCustomerLongUrl02(){
        assertFalse(testObj.validateCustomerLongUrl("www.g.com"));
    }

    /*
    Verify valid customer long url less than min rejected
    Inputs: www.g.com
    Expected outputs: false
     */
/*    @Test
    void validateCustomerLongUrl03(){

    }*/

    /*
    Verify valid customer email (12 - 50 characters)
    Inputs: ciaran@gmail.com
    Expected outputs: true
     */
    @Test
    void validateCustomerEmail01(){
        assertTrue(testObj.validateCustomerEmail("ciaran@gmail.com"));
        assertTrue(testObj.validateCustomerEmail("ct@gmail.com"));
    }

    /*
    Verify valid customer email less than min rejected
    Inputs: t@gmail.com
    Expected outputs: false
     */
    @Test
    void validateCustomerEmail02(){
        assertFalse(testObj.validateCustomerEmail("t@gmail.com"));
    }

    /*
    Verify valid customer email greater than max rejected
    Inputs: thisshouldbea51charemailormore-ghdhsnvbsoewutjn@gmail.com
    Expected outputs: false
     */
    @Test
    void validateCustomerEmail03(){
        assertFalse(testObj.validateCustomerEmail("thisshouldbea51charemailormore-ghdhsnvbsoewutjn@gmail.com"));
    }



}
