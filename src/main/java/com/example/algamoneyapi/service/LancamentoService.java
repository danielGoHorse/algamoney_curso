package com.example.algamoneyapi.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	@Autowired
	private PessoaService pessoaService;
	
	
	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaService.findById(lancamento.getPessoa().getId());
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}
	
	
	
	
	
	
	
	
	public List<Lancamento> findAll() {
		return this.lancamentoRepository.findAll();
	}

	public Lancamento findById(Long id) {
		Lancamento lancamento = this.lancamentoRepository.findById(id).orElse(null);
		if (lancamento != null) {
			return lancamento;
		}
		 throw new ValidationException("Lançamento não encontrada");
	}
	
	public void remover(Long id) {
		lancamentoRepository.deleteById(id);
	}

}
