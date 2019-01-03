package com.nathancunha.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathancunha.cursomc.domain.Cliente;
import com.nathancunha.cursomc.repositories.ClienteRepository;
import com.nathancunha.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public Cliente buscar(Integer id) {

		Optional<Cliente> clienteObj = clienteRepository.findById(id);

		return clienteObj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
