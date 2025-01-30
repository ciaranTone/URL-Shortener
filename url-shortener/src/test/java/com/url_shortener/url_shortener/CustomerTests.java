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
    Verify valid customer nameless than min and null rejected
    Inputs: t
    Expected outputs: false
    */
    void testValidateCustomerName02(){
        assertFalse(testObj.validateCustomerName(""));
        assertFalse(testObj.validateCustomerName("t"));
    }


}
