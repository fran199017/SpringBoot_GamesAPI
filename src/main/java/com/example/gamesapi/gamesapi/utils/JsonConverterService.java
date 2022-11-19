package com.example.gamesapi.gamesapi.utils;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("jsonConverterService")
public class JsonConverterService {

    public JSONObject getJSONObjectForJSONString(String jsonString) {
        try{
            JSONObject json = new JSONObject(jsonString);
            return json;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
