package com.example.SalesReportApi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SalesReportController {
	private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/getSalesReport")
	public String report() {
		String uri = "http://localhost:9090/getOrders";
		String responseMsg = restTemplate.getForObject(uri, String.class);
		return responseMsg;

	}


}
