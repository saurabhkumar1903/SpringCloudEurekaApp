package com.db.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.model.Quotes;
import com.db.repository.QuoteRepositoryCustom;
import com.db.repository.QutoesRepository;
import com.db.service.DbService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
@Transactional
public class DbServiceImpl implements DbService {
	static Logger log = LoggerFactory.getLogger(DbServiceImpl.class);

	@Autowired
	QutoesRepository quotesRepository;

	@Autowired
	QuoteRepositoryCustom quoteRepositoryCustom;

	@Override
	public List<String> findByUserName(String username) throws JsonProcessingException {

		List<Quotes> quotes = quotesRepository.findByUserName(username);

		log.info("In findByUserName :[{}]", quotes);
		return quotes.stream().map(quote -> quote.getQuote()).collect(Collectors.toList());

	}

	@Override
	public List<String> addUser(Quotes quote) {

		quotesRepository.save(quote);
		return new ArrayList(Arrays.asList("Success"));
	}

	@Override
	public int delUser(String username) {


		quotesRepository.deleteQuoteByUserName(username);
		return 0;
	}

}
