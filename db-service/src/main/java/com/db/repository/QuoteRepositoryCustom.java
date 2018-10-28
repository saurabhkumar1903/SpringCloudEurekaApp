package com.db.repository;

import java.util.List;

import com.db.model.Quotes;

public interface QuoteRepositoryCustom {

	List<Quotes> findByUserName(String username);
}
