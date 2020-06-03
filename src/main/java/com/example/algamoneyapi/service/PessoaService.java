package com.example.algamoneyapi.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	

	public Pessoa findById(Long id) {
		Pessoa pessoa = this.pessoaRepository.findById(id).get();
		if (pessoa != null) {
			return pessoa;
		}
		 throw new ValidationException("Pessoa não encontrada");
		
	}
	
	
	
	public Pessoa atualizar(Long id,Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPeloId(id);
		BeanUtils.copyProperties(pessoa, pessoaSalva , "id");
		return pessoaRepository.save(pessoaSalva);
	}

	public List<Pessoa> findAll() {
		return this.pessoaRepository.findAll();
	}



	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloId(id);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
		
	}
	
	private Pessoa buscarPessoaPeloId(Long id) {
		Pessoa pessoaSalva = pessoaRepository.findById(id).get();
		if(pessoaSalva == null) {
			throw new NoSuchElementException();
		}
		return pessoaSalva;
	}
	
	
}
