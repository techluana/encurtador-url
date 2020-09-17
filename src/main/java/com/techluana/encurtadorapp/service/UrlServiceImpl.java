package com.techluana.encurtadorapp.service;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.techluana.encurtadorapp.entity.Url;
import com.techluana.encurtadorapp.repository.UrlRepository;
import com.techluana.encurtadorapp.utils.VariaveisAplicacaoUtils;

@Service
public class UrlServiceImpl implements UrlService {

	@Autowired
	private UrlRepository repository;

	@Autowired
	private VariaveisAplicacaoUtils variaveisApp;
	
	private static Logger log = Logger.getLogger(VariaveisAplicacaoUtils.class);

	@Override
	public Url getUrlValida(String url) throws Exception {
		List<Url> urlsExistentes = repository.getUrls(url);
		if (null == urlsExistentes || CollectionUtils.isEmpty(urlsExistentes)) {
			throw new Exception("URL não existente");
		}
		return urlsExistentes.stream().filter(umaUrl -> umaUrl.isUrlValida()).findFirst().orElse(null);
	}

	@Override
	public Url salvar(Url urlSalvar) {
		String newURL;
		Url urlValidaExistente;
		do {
			newURL = getRandomURL();
			try {
				urlValidaExistente = getUrlValida(newURL);
			} catch (Exception e) {
				urlValidaExistente = null;
				log.warn(e.getMessage());
			}
		} while (urlValidaExistente != null);
		urlSalvar.setNovaUrl(newURL);

		if (null == urlSalvar.getTipoExpiracao()) {
			urlSalvar.setTipoExpiracao(variaveisApp.getTipoExpiracaoDefault());
		}
		if (null == urlSalvar.getTempoExpiracao()) {
			urlSalvar.setTempoExpiracao(variaveisApp.getTempoExpiracaoDefault());
		}
		return repository.save(urlSalvar);
	}

	private String getRandomURL() {
		Random r = new Random();
		int size = 0;
		do {
			size = r.nextInt(36);
		} while (size < 5);

		StringBuilder newUrl = new StringBuilder();
		for (int i = 0; i < size; i++) {
			newUrl.append(getRandomChar(r));
		}
		return newUrl.toString();
	}

	private String getRandomChar(Random r) {
		String regex = "^[a-zA-Z0-9]+$";
		String randomChar = "";
		int randorInt;
		do {
			randorInt = r.nextInt(150);
			if (randorInt < 0) {
				continue;
			}
			randomChar = new String(Character.toChars(randorInt));
		} while (!randomChar.matches(regex));
		return randomChar;
	}
}
