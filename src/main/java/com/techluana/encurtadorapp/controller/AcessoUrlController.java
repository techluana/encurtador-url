package com.techluana.encurtadorapp.controller;

import java.net.URI;

import org.apache.log4j.Logger;
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

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("")
public class AcessoUrlController {

	@Autowired
	private UrlService service;
	
	private static Logger log = Logger.getLogger(AcessoUrlController.class);

	@ApiResponse(responseCode = "303", description = "Redireciona para URL salva a partir do parâmetro 'new_url'")
	@ApiResponse(responseCode = "404", description = "URL não encontrada na base de dados")
	@ApiResponse(responseCode = "410", description = "URL expirada")
	@GetMapping(value="/{new_url}")
	@ResponseBody
	public ResponseEntity<Object> getRedirectUrl(@PathVariable(name = "new_url", required = true) String url) throws Exception {
		Url urlValida;
		try {
			urlValida = service.getUrlValida(url);
		}catch (Exception e) {
			log.error(e.getMessage());
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
