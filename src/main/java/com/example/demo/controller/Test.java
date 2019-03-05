package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@RestController
public class Test {
	
	@Autowired
    private OkHttpClient client;

	
	 @RequestMapping(value = "/trace1",method=RequestMethod.GET)
	    public String dc() throws Exception{
	      
		 Thread.sleep(100);
		 Request request = new Request.Builder().url("http://localhost:8080/trace2").build();
		 Response response = client.newCall(request).execute();
		 return response.body().string();

	    }
}
