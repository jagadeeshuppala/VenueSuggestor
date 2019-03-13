package com.premierinn.service;

import com.premierinn.model.FoursquareResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class VenueService {
    @Autowired
    private RestTemplate restTemplate;

    private final String URL_TO_GET_VENUE_WITH_LIMIT = "https://api.foursquare.com/v2/venues/search?near={location}&client_id={clientId}&client_secret={clientSecret}&v={dateTime}&limit={limit}";
    private final String URL_TO_GET_VENUE_NO_LIMIT = "https://api.foursquare.com/v2/venues/search?near={location}&client_id={clientId}&client_secret={clientSecret}&v={dateTime}";


    public FoursquareResponse getPlacesNearBy(String near, String clientId, String clientSecret, String dateRequested, Integer limit){
        FoursquareResponse response = null;
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("location", near);
        paramsMap.put("clientId", clientId);
        paramsMap.put("clientSecret", clientSecret);
        paramsMap.put("dateTime", dateRequested);
        if(limit!=null) {
            paramsMap.put("limit", String.valueOf(limit));
        }

        if(limit != null){
            response = restTemplate.getForObject(URL_TO_GET_VENUE_WITH_LIMIT, FoursquareResponse.class, near, clientId, clientSecret, dateRequested, limit);


        }else{
            response = restTemplate.getForObject(URL_TO_GET_VENUE_NO_LIMIT, FoursquareResponse.class, near, clientId, clientSecret, dateRequested);
        }
        return response;
    }
}
