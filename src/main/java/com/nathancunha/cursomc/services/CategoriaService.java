package com.nathancunha.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathancunha.cursomc.domain.Categoria;
import com.nathancunha.cursomc.repositories.CategoriaRepository;
import java.util.Optional;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Categoria buscar(Integer id ) {
	
		Optional <Categoria> categoriaObj = categoriaRepo.findById(id); 
		return categoriaObj.orElse(null);
	
	}
}