package com.kundertest.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by José Rodríguez on 28/07/2017.
 */

@Controller
@RequestMapping("/time")
public class UtcIsoTimeCtrl {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getUtcIsoTime(@RequestParam(value = "value", required = true) String value) {
        //todo log entry here

        //String Response
        String response = "";

        //Formats
        SimpleDateFormat hourFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat UtcIsoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sZ");

        //No exception is being thrown by this parse method; instead null is returned
        ParsePosition position = new ParsePosition(0);

        //To make strict date format validation
        hourFormat.setLenient(false);

        //Used to validate entry value
        Date hourParsedDate;

        try {
            hourParsedDate = hourFormat.parse(value, position);
            if (hourParsedDate == null) {
                response = "{\"code\":\"400\",\"data\":\"\",\"description\":\"BAD_REQUEST\"}";
                return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
            } else if (position.getIndex() != value.length()) {
                response = "{\"code\":\"400\",\"data\":\"\",\"description\":\"BAD_REQUEST\"}";
                return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
            } else {
                String UtcIsoDate = UtcIsoDateFormat.format(hourParsedDate);
                response = "{\"code\":\"200\",\"data\":\"" + UtcIsoDate + "\",\"description\":\"OK\"}";
            }
        } catch (Exception e) {
            new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //Everything OK
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}