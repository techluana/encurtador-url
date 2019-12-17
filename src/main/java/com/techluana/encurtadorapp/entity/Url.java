package com.techluana.encurtadorapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.techluana.encurtadorapp.enums.TipoExpiracao;

@Entity
@Table(name = "url")
public class Url {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "uid")
	private Long id;

	@URL(message="URL inválida")
	@NotBlank(message="Necessário informar a URL")
	@Column(name = "url", nullable = false)
	private String url;

	@NotBlank(message="Necessário informar a nova URL")
	@Column(name = "new_url", nullable = false)
	private String novaUrl;

	@NotNull(message="Necessário informar o tipo de expiração do link")
	@Enumerated(EnumType.STRING)
	@Column(name = "type_expiration", nullable = false)
	private TipoExpiracao tipoExpiracao;

	@NotNull(message="Necessário informar o tempo de expiração do link")
	@Column(name = "time_expiration", nullable = false)
	private Long tempoExpiracao;

	@NotNull(message="necessário informar a data de inclusão do link")
	@Column(name = "date_create", nullable = false)
	private LocalDateTime dataInclusao;

	public Url() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNovaUrl() {
		return novaUrl;
	}

	public void setNovaUrl(String newUrl) {
		this.novaUrl = newUrl;
	}

	public TipoExpiracao getTipoExpiracao() {
		return tipoExpiracao;
	}

	public void setTipoExpiracao(TipoExpiracao tipoExpiracao) {
		this.tipoExpiracao = tipoExpiracao;
	}

	public Long getTempoExpiracao() {
		return tempoExpiracao;
	}

	public void setTempoExpiracao(Long tempoExpiracao) {
		this.tempoExpiracao = tempoExpiracao;
	}

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	
	public boolean isUrlValida() {
		return null != getDataExpiracao() && LocalDateTime.now().isBefore(getDataExpiracao());
	}

	public LocalDateTime getDataExpiracao() {
		LocalDateTime dataExpiracao = null;
		if (null != getTipoExpiracao() && null != getTempoExpiracao() && null != getDataInclusao()) {
			switch (getTipoExpiracao()) {
			case MINUTOS:
				dataExpiracao = getDataInclusao().plusMinutes(getTempoExpiracao());
				break;
			case HORAS:
				dataExpiracao = getDataInclusao().plusHours(getTempoExpiracao());
				break;
			case DIAS:
				dataExpiracao = getDataInclusao().plusDays(getTempoExpiracao());
				break;
			}
		}
		return dataExpiracao;
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		hash = 31 * hash + (id == null ? 0 : id.intValue());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Url url = (Url) obj;
		return id == url.id;
	}
}
