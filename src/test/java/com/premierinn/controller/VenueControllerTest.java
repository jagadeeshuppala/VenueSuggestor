package com.premierinn.controller;

import com.premierinn.model.*;
import com.premierinn.service.VenueService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(VenueController.class)
public class VenueControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VenueService venueService;


    @Test
    public void testGetVenue() throws Exception{

        Location location = new Location("St.Pauls", "EC2A3AY", "London", "London", "England");
        Response response = new Response(Arrays.asList(new Venue("1234", "st.Pauls",location)));
        FoursquareResponse foursquareResponse = new FoursquareResponse(new Meta(200,"000",null), response);



        Mockito.when(venueService.getPlacesNearBy(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(foursquareResponse);

        mvc.perform(MockMvcRequestBuilders.get("/venue?near=London&limit=1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testClientErrorException() throws Exception{
        Mockito.when(venueService.getPlacesNearBy(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenThrow(HttpClientErrorException.class);

        mvc.perform(MockMvcRequestBuilders.get("/venue?near=L&limit=1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void testParameterMandatory() throws Exception{

        mvc.perform(MockMvcRequestBuilders.get("/venue"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void testParameterValueNotEmtpty() throws Exception{

        mvc.perform(MockMvcRequestBuilders.get("/venue?near"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
