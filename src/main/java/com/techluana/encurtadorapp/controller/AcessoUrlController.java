package com.techluana.encurtadorapp.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techluana.encurtadorapp.entity.Url;
import com.techluana.encurtadorapp.service.UrlService;

@RestController
@RequestMapping("api")
public class AcessoUrlController {

	@Autowired
	private UrlService service;

	@GetMapping(value="/{new}")
	@ResponseBody
	public ResponseEntity<Object> getRedirectUrl(@PathVariable(name = "new", required = true) String url) throws Exception {
		Url urlValida;
		try {
			urlValida = service.getUrlValida(url);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(null == urlValida) {
			return new ResponseEntity<>(HttpStatus.GONE);
		}
		
		URI uriDirecionar = new URI(urlValida.getUrl());
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(uriDirecionar);
	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}

}
