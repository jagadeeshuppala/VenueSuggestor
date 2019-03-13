package com.premierinn.model;


public class FoursquareResponse {
    private Meta meta;
    private Response response;

    public FoursquareResponse() {
    }

    public FoursquareResponse(Meta meta, Response response) {
        this.meta = meta;
        this.response = response;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
