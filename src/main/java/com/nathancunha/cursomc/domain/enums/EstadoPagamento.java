package com.nathancunha.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1,"Pendente"), 
	QUITADO(2,"Quitado"),
	CANELADO(3,"Cancelado");
	
	private int cod; 
	private String descricao;
	
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
			if (cod.equals(estadoPagamento.getCod())) {
				return estadoPagamento;
			}
		}
		throw new IllegalArgumentException("Id Invalido" + cod);
	}
		
		
		
	
}
