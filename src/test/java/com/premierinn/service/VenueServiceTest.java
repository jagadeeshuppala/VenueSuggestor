package com.premierinn.service;

import com.premierinn.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@RunWith(SpringRunner.class)
public class VenueServiceTest {

    private final String clientId = "MTUC2ZENFCL43K2RYF3A5CJG0GT0DGDBENYF4YS41LH2NGHM";
    private final String clientSecret = "5FIOYNLMZB1MGMTSHQRRY4H5KZ2MZUDUGCB5HGKCYVY5AJ1H";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formatDateTime = LocalDate.now().format(formatter);

    @Autowired
    private VenueService venueService;

    @Autowired
    private RestTemplate restTemplate;

    @TestConfiguration
    static class VenueServiceTestContextConfiguration {

        @Bean
        public VenueService venueService() {
            return new VenueService();
        }

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

    }


    @Test(expected = HttpClientErrorException.class)
    public void testForBadRequestException(){
        FoursquareResponse nearByLondon = venueService.getPlacesNearBy("L", clientId, clientSecret, formatDateTime, 1);
        Assert.assertEquals("Returned status code should be 400", 400, nearByLondon.getMeta().getCode().intValue());
        Assert.assertNull("Response should be null", nearByLondon.getResponse());
    }

    @Test
    public void testForNearLondonAndLimit(){
        FoursquareResponse nearByLondon = venueService.getPlacesNearBy("London", clientId, clientSecret, formatDateTime, 1);
        Assert.assertEquals("Returned status code should be 200", 200, nearByLondon.getMeta().getCode().intValue());
        Assert.assertEquals("One Result should be returned", nearByLondon.getResponse().getVenues().size(), 1);
    }
}


