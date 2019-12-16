package com.techluana.encurtadorapp.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import com.techluana.encurtadorapp.enums.TipoExpiracao;

@Service
public class VariaveisAplicacaoUtils {

	@Value("${url.default.tipo-expiracao}")
	private String tipoExpiracaoDefault;
	
	@Value("${url.default.tempo-expiracao}")
	private String tempoExpiracaoDefault;

	public TipoExpiracao getTipoExpiracaoDefault() {
		Integer tipoExpiracao = NumberUtils.parseNumber(tipoExpiracaoDefault, Integer.class);
		return TipoExpiracao.getTipoById(tipoExpiracao);
	}

	public Long getTempoExpiracaoDefault() {
		return NumberUtils.parseNumber(tempoExpiracaoDefault, Long.class);
	}
	
}
