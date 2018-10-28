package com.db.service;

import java.util.List;

import com.db.model.Quotes;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DbService {

	List<String> findByUserName(String username) throws JsonProcessingException;

	List<String> addUser(Quotes quotes);

	int delUser(String username);

}
