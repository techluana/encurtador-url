package com.techluana.encurtadorapp.service;

import com.techluana.encurtadorapp.entity.Url;

public interface UrlService {

	Url getUrlValida(String url) throws Exception;
	
	Url salvar(Url urlSalvar);
	
}
