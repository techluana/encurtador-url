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

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/encurtador-url")
public class EncurtadorUrlController {

	@Autowired
	private UrlService service;

	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			content=@Content(mediaType="application/json", 
			schema=@Schema(accessMode=AccessMode.READ_WRITE, example="{\"url\":\"http://google.com\",\"tipoExpiracao\":0,\"tempoExpiracao\":30}")))
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Gera uma sequência de caracteres que representará uma URL e posteriormente poderá ser direcionado à mesma") } )
	@PostMapping
	@ResponseBody
	public ResponseEntity<Object> salvar(@RequestBody Url urlIncluir) throws Exception {
		Url urlSalva = service.salvar(urlIncluir);
		return new ResponseEntity<>(urlSalva, HttpStatus.CREATED);
	}

}
