package com.db.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.model.Quotes;
import com.db.service.DbService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value="/db")
public class DbController {
	
	static Logger log = LoggerFactory.getLogger(DbController.class);
	@Autowired
	DbService dbService;
	
	
	@GetMapping("/welcome")
	public String showWelcome() {
		System.out.println("method hit");
		log.info("method hit welcome");
		return "Welcome to my World!";
	}


	@GetMapping(value="/get/{username}")
	public List<String> getQuote(@PathVariable("username") String username) throws JsonProcessingException
	{
		log.info("in getQuote[{}]",username);
	return dbService.findByUserName(username);	
	
	}
	
	@PostMapping(value="/add",consumes="application/JSON" )
	public List<String> addQuote(@RequestBody Quotes quote)
	{
		log.info("in add[{}]",quote);
		return dbService.addUser(quote);
	}
	
	@DeleteMapping("/del/{username}")
	public  int delQuote(@PathVariable("username") final String username)
	{
		log.info("in delete[{}]",username);
		return dbService.delUser(username);
	}
}
