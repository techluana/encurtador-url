package com.techluana.encurtadorapp.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techluana.encurtadorapp.entity.Url;
import com.techluana.encurtadorapp.service.UrlService;

@RestController
@RequestMapping("api/v1/encurtador-url")
public class EncurtadorUrlController {

	@Autowired
	private UrlService service;

	@PostMapping
	@ResponseBody
	public ResponseEntity<Object> salvar(@RequestBody Url urlIncluir) throws Exception {
		Url urlSalva = service.salvar(urlIncluir);
		return new ResponseEntity<>(urlSalva, HttpStatus.CREATED);
	}

}
