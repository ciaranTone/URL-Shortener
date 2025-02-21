package com.url_shortener;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class UrlShortenerTest {

    UrlShortener testObj = new UrlShortener();
    //Test original url
    //Return True
    @Test
    void testValidateOriginalUrl01() {
        assertTrue(testObj.validateOriginalUrl("https://www.google.com"));
        assertTrue(testObj.validateOriginalUrl("https://gh"));
        assertTrue(testObj.validateOriginalUrl("https://github.com/toney200/SoftwareEngineeringGroup/blob/main/Tests/PublicationTest.java"));
    }
    @Test
    //Return False
    void testValidateOriginalUrl02() {
        assertFalse(testObj.validateOriginalUrl(""));
        assertFalse(testObj.validateOriginalUrl("https://g"));
        assertFalse(testObj.validateOriginalUrl("https://github.com/toney200/SoftwareEngineeringGroup/blob/main/Tests/PublicationTest.java" +
                "erhkrthopktrhrht[trh[ptrk[phkt[hmp[pmntomnrotmnotrmhn[kphthmpqthmopetrhmq[hpmpetrhmtpmbhtrh;mberophmqerhpmh[perhmeothm"));
    }

    //Test short url
    //Return True
    @Test
    void testValidateShortUrl01(){
        assertTrue(testObj.validateShortenedUrl("e"));
        assertTrue(testObj.validateShortenedUrl("ciaran"));
    }

    //Return false
    @Test
    void testValidateShortUrl02(){
        assertFalse(testObj.validateShortenedUrl(""));
        assertFalse(testObj.validateShortenedUrl("https://gwfeiweohfwefiwegiegewiogeiwoghgierjerigergregbregbrigbegierg"));
    }

}