package com.techluana.encurtadorapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.techluana.encurtadorapp.entity.Url;

public interface UrlRepository extends CrudRepository<Url, Long>{

	@Query("select u from Url u where u.novaUrl = :url")
	List<Url> getUrls(@Param("url") String url);
}
