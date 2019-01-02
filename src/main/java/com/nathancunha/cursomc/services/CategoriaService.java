package com.nathancunha.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathancunha.cursomc.domain.Categoria;
import com.nathancunha.cursomc.repositories.CategoriaRepository;
import com.nathancunha.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Categoria buscar(Integer id) {
	
		Optional <Categoria> categoriaObj = categoriaRepo.findById(id); 
		return categoriaObj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
}