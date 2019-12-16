package com.techluana.encurtadorapp.enums;

public enum TipoExpiracao {

	MINUTOS(0),
	HORAS(1),
	DIAS(2);
	
	private int idTipo;

	private TipoExpiracao(int idTipo) {
		this.idTipo = idTipo;
	}

	public int getIdTipo() {
		return idTipo;
	}
	
	public static TipoExpiracao getTipoById(int id) {
		for(TipoExpiracao tipo : TipoExpiracao.values()) {
			if(id == tipo.getIdTipo()) {
				return tipo;
			}
		}
		return null;
	}
}
