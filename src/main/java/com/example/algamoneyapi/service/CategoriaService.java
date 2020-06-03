package com.example.algamoneyapi.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Categoria;
import com.example.algamoneyapi.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria findById(Long id) {
		Categoria categoria = this.categoriaRepository.findById(id).orElse(null);
		if (categoria != null) {
			return categoria;
		}
		 throw new ValidationException("Categoria não encontrada");
		
	}
	
		
	public Categoria atualizar(Long id,Categoria categoria) {
		Categoria categoriaSalva = categoriaRepository.findById(id).get();
		if(categoriaSalva == null) {
			throw new NoSuchElementException();
		}
		BeanUtils.copyProperties(categoria, categoriaSalva , "id");
		return categoriaRepository.save(categoriaSalva);
	}
	
	
	
	

	public List<Categoria> findAll() {
		return this.categoriaRepository.findAll();
	}
	
	
}
