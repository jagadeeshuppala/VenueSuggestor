package com.premierinn.controller;

import com.premierinn.model.*;
import com.premierinn.service.VenueService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/venue")
@Api(value="venue - API", description="get suggestions on the near by places from the provided location")
public class VenueController {

    @Autowired
    private VenueService service;

    /**
     * Retrieving the suggestions on the near by places from the provided location
     * @param near
     * @param limit
     * @return ResponseEntity with HttpStatus in the header
     */

    @GetMapping()
    public ResponseEntity<FoursquareResponse> getVenuSuggestions(@RequestParam(required = true) String near, @RequestParam(required = false) Integer limit,
                                                                 @RequestAttribute(value="clientId") String clientId,
                                                                 @RequestAttribute(value="clientSecret") String clientSecret,
                                                                 @RequestAttribute(value="dateRequested") String dateRequested
                                                                 ){


        if(Optional.ofNullable(near).orElse("").trim().isEmpty()){
            throw new RuntimeException("near param can not be empty or null");
        }

        FoursquareResponse placesNearByResponse = service.getPlacesNearBy(near, clientId, clientSecret, dateRequested, limit);
        if(placesNearByResponse.getMeta().getCode().equals(200)){
            return new ResponseEntity(placesNearByResponse, HttpStatus.OK);
        }else{
            Meta  meta = new Meta(500,null, "Internal Server Error");
            FoursquareResponse response = new FoursquareResponse(meta, null);
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        }



    }
}
