package com.premierinn.controller;

import com.premierinn.model.FoursquareResponse;
import com.premierinn.model.Meta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.net.UnknownHostException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<FoursquareResponse> handleClientError(HttpClientErrorException excption){
       // Error error = new Error(400, excption.getMessage() , "visit Premierinn help page for troubleshooting");
        Meta meta = new Meta(400, null, excption.getMessage()+ "; visit Premierinn help page for troubleshooting");
        FoursquareResponse response = new FoursquareResponse(meta,null);

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UnknownHostException.class})
    public ResponseEntity<FoursquareResponse> handleUnknownHostException(UnknownHostException excption){
        Meta meta = new Meta(400, null, "Unknown Host exception: "+excption.getMessage()+ "; Please check your internet connection and try again");
        FoursquareResponse response = new FoursquareResponse(meta,null);

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<FoursquareResponse> handleRuntimeException(RuntimeException runtimeException){
        Meta meta = new Meta(400, null, " "+runtimeException.getMessage()+ "; visit Premierinn help page for troubleshooting");
        FoursquareResponse response = new FoursquareResponse(meta,null);

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { MissingServletRequestParameterException.class})
    public ResponseEntity<FoursquareResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException runtimeException){
        Meta meta = new Meta(400, null, " "+runtimeException.getMessage()+ "; visit Premierinn help page for troubleshooting");
        FoursquareResponse response = new FoursquareResponse(meta,null);

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<FoursquareResponse> handleThrowable(Throwable throwable){
        Meta meta = new Meta(500, null, " "+throwable.getMessage()+ "; visit Premierinn help page for troubleshooting");
        FoursquareResponse response = new FoursquareResponse(meta,null);

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}