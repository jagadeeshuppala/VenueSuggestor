package com.premierinn.model;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private List<Venue> venues = new ArrayList<>();

    public Response() {
    }

    public Response(List<Venue> venues) {
        this.venues = venues;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}
