package com.techluana.encurtadorapp;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import com.techluana.encurtadorapp.entity.Url;
import com.techluana.encurtadorapp.enums.TipoExpiracao;
import com.techluana.encurtadorapp.service.UrlService;

@ActiveProfiles("test")
class EncurtadorUrlServiceTest extends EncurtadorAppApplicationTests {

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	@Autowired
	private UrlService service;

	@Test
	public void testeSalvarVazio() {
		Url url = new Url();
		Set<ConstraintViolation<Url>> violations = validator.validate(url);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void testeSalvarUrlInvalida_textoQualquer() {
		Url url = new Url();
		url.setUrl("asdasdadasda");
		url.setNovaUrl("TFF5646rFF");
		url.setTempoExpiracao(20l);
		url.setTipoExpiracao(TipoExpiracao.MINUTOS);
		url.setDataInclusao(LocalDateTime.now());
		Set<ConstraintViolation<Url>> violations = validator.validate(url);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void testeSalvarUrlInvalida_semhttp() {
		Url url = new Url();
		url.setUrl("asdasdadasda.com");
		url.setNovaUrl("TFF5646rFF");
		url.setTempoExpiracao(20l);
		url.setTipoExpiracao(TipoExpiracao.MINUTOS);
		url.setDataInclusao(LocalDateTime.now());
		Set<ConstraintViolation<Url>> violations = validator.validate(url);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void testeSalvarUrlInvalida_semNovaURL() {
		Url url = new Url();
		url.setUrl("http://google.com");
		url.setTempoExpiracao(20l);
		url.setTipoExpiracao(TipoExpiracao.MINUTOS);
		url.setDataInclusao(LocalDateTime.now());
		Set<ConstraintViolation<Url>> violations = validator.validate(url);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void testeSalvarUrlInvalida_semTempoExpiracao() {
		Url url = new Url();
		url.setUrl("http://google.com");
		url.setNovaUrl("TFF5646rFF");
		url.setTipoExpiracao(TipoExpiracao.MINUTOS);
		url.setDataInclusao(LocalDateTime.now());
		Set<ConstraintViolation<Url>> violations = validator.validate(url);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void testeSalvarUrlInvalida_semTipoExpiracao() {
		Url url = new Url();
		url.setUrl("http://google.com");
		url.setNovaUrl("TFF5646rFF");
		url.setTempoExpiracao(20l);
		url.setDataInclusao(LocalDateTime.now());
		Set<ConstraintViolation<Url>> violations = validator.validate(url);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void testeSalvarUrlInvalida_semDataInclusao() {
		Url url = new Url();
		url.setUrl("http://google.com");
		url.setNovaUrl("TFF5646rFF");
		url.setTempoExpiracao(20l);
		url.setTipoExpiracao(TipoExpiracao.MINUTOS);
		Set<ConstraintViolation<Url>> violations = validator.validate(url);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void testeSalvarUrlValida() {
		Url url = new Url();
		url.setUrl("http://google.com");
		url.setNovaUrl("TFF5646rFF");
		url.setTempoExpiracao(20l);
		url.setTipoExpiracao(TipoExpiracao.MINUTOS);
		url.setDataInclusao(LocalDateTime.now());
		Set<ConstraintViolation<Url>> violations = validator.validate(url);
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void testeSalvarValido() {
		Url url = new Url();
		url.setUrl("http://google.com");
		url.setTempoExpiracao(30l);
		url.setTipoExpiracao(TipoExpiracao.MINUTOS);
		
		Url urlSalva = service.salvar(url);
		Assert.assertNotNull(urlSalva);
		Assert.assertNotNull(urlSalva.getNovaUrl());
		Assert.assertNotNull(urlSalva.getDataInclusao());
		
		LocalDateTime timeExpiracao = urlSalva.getDataInclusao().plusMinutes(30l);
		Assert.assertEquals(timeExpiracao, urlSalva.getDataExpiracao());
	}
}
