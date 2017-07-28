package com.kundertest.app.controller;

import com.kundertest.app.dto.UpperCaseWordDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by José Rodríguez on 28/07/2017.
 */

@Controller
@RequestMapping("/word")
public class UpperCaseWordCtrl {

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> getUpperCaseWord(@RequestBody UpperCaseWordDto jsonInput) {
        //todo log entry

        //String Response
        String response;

        //String to uppercase de word
        String upperCaseWord;

        //Extracting word from JSON
        String word = jsonInput.getData();

        try {
            if (word.matches(".*\\d.*")) {
                //Contains a number
                response = "{\"code\":\"400\",\"data\":\"\",\"description\":\"BAD_REQUEST\"}";
                return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
            } else {
                //Does not contain a number
                if (word.length() == 4) {
                    //Matches format and length
                    upperCaseWord = word.toUpperCase();
                    response = "{\"code\":\"200\",\"data\":\"" + upperCaseWord + "\",\"description\":\"OK\"}";
                } else {
                    //Matches format but not length
                    response = "{\"code\":\"400\",\"data\":\"\",\"description\":\"BAD_REQUEST\"}";
                    return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
                }
            }

        } catch (Exception e) {
            //todo log entry
            response = "{\"code\":\"500\",\"data\":\"\",\"description\":\"INTERNAL_SERVER_ERROR\"}";
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //Everything OK
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}