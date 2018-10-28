package com.stock.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/stock")
public class StockController {

	static Logger log = LoggerFactory.getLogger(StockController.class);
	@Autowired
	RestTemplate restTemplate;

	private YahooFinance yahooFinance;

	public StockController() {
		this.yahooFinance = new YahooFinance();

		// TODO Auto-generated constructor stub
	}

	@GetMapping("/welcome")
	public String showWelcome() {
		return "Welcome to my World!";
	}

	@GetMapping("/get/{username}")
	public List<Stock> getStock(@PathVariable("username") final String userName) {
		
		log.info("in getStock method[{}]",userName);
		
		//Look at the url here the url is not hard coded it is being genereated from the erureka server
		ResponseEntity<List<String>> response = restTemplate.exchange("http://db-service/db/get/" + userName,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});
		List<String> quotes = response.getBody();
		List<Stock> collect = quotes.stream().map(quote -> {
			try {
				return yahooFinance.get(quote);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
		return collect;

	}

}
