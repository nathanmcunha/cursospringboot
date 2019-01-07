package com.nathancunha.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nathancunha.cursomc.domain.Pedido;
import com.nathancunha.cursomc.repositories.PedidoRepository;
import com.nathancunha.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	public Pedido buscar(Integer id) {

		Optional<Pedido> pedidoObj = pedidoRepository.findById(id);

		return pedidoObj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
