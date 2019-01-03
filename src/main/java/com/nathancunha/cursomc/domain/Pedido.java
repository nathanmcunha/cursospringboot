package com.nathancunha.cursomc.domain;

import java.io.Serializable;
import java.util.Date;

public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date instante;
	private Pagamento pagamento;
	private Cliente umCliente;
	private Endereco enderecoDeEntrega;
	
	public Pedido() {
		super();
	}

	public Pedido(Integer id, Date instante, Pagamento pagamento, Cliente umCliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.pagamento = pagamento;
		this.umCliente = umCliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getUmCliente() {
		return umCliente;
	}

	public void setUmCliente(Cliente umCliente) {
		this.umCliente = umCliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
