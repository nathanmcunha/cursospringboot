package com.nathancunha.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nathancunha.cursomc.domain.Categoria;
import com.nathancunha.cursomc.repositories.CategoriaRepository;
import com.nathancunha.cursomc.services.exceptions.DataIntegrityException;
import com.nathancunha.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepo;

	public Categoria find(Integer id) {

		Optional<Categoria> categoriaObj = categoriaRepo.findById(id);
		return categoriaObj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return categoriaRepo.save(obj);
	}

	public void delete(Integer id) {
		find(id);	
		try {
			categoriaRepo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new  DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}

	public List<Categoria> findAll() {
		return categoriaRepo.findAll();
	}
	
	public Page <Categoria> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); 
		 return categoriaRepo.findAll(pageRequest);
	}
	
	
	
}