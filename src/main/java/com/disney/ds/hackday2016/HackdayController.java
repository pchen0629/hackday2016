package com.disney.ds.hackday2016;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by peichen on 3/2/16.
 */

@Controller
@RequestMapping("/")
public class HackdayController {
    private static Logger log = Logger.getLogger(HackdayController.class);


    @Value("${external.host.name}")
    private String externalServerName;

    @Resource
    private HackdayService hackdayService;

    @RequestMapping(value = "getImageQueue", method = RequestMethod.GET)
    public ResponseEntity<String> getImageQueue() {
        String methodName = "getImageQueue()";
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        log.info(methodName + "...........begin");
        try {
            String imageResponse = hackdayService.getImageQueue();
            return new ResponseEntity<>(imageResponse, responseHeaders, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(methodName + " Exception: " + ex + " stacktrace: " + ex.getStackTrace().toString(), responseHeaders, HttpStatus.CREATED);
        }
    }


}
