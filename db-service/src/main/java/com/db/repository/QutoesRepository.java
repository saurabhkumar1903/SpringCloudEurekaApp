package com.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.model.Quotes;

@Repository
public interface QutoesRepository extends JpaRepository<Quotes, Integer> {

	public List<Quotes> findByUserName(String username);


	public void deleteQuoteByUserName(String username);

}
