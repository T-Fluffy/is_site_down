package com.tarek.is_site_down.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String IS_SITE_UP="Site is UP !";
    private final String IS_SITE_DOWN="Site is DOWN !";
    private final String INCORRECT_URL="URL is incorrect !";

    @GetMapping("/check")
    public String getUrlStatusMessag(@RequestParam String url){
        String ReturnMessage="";
        
        try {
            URL urlObject =new URL(url);
            HttpURLConnection conn =(HttpURLConnection) urlObject.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            
            int responseCodeCategorie=conn.getResponseCode()/100;
            if(responseCodeCategorie == 2 ||responseCodeCategorie == 3){
                ReturnMessage=IS_SITE_UP;
            }else ReturnMessage=IS_SITE_DOWN;


        } catch (MalformedURLException e) {
            ReturnMessage=INCORRECT_URL;
        } catch (IOException e) {
            ReturnMessage=IS_SITE_DOWN;
            e.printStackTrace();
        }

        return ReturnMessage;
    } 
}
