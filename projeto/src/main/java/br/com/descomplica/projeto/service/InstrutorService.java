package br.com.descomplica.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.descomplica.projeto.entity.Instrutor;
import br.com.descomplica.projeto.repository.InstrutorRepository;

@Service
public class InstrutorService {
	@Autowired
	InstrutorRepository instrutorRepository;
	
	public List<Instrutor> getAll(){
		return instrutorRepository.findAll();
	}
	
	public Instrutor getById(Integer id) {
		return instrutorRepository.findById(id).orElse(null) ;
	}
	
	public Instrutor saveInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}
	
	public Instrutor updateInstrutor(Integer id, Instrutor instrutor) {
		Instrutor instrutorAtualizado = instrutorRepository.findById(id).orElse(null);
		if(instrutorAtualizado != null) {
			instrutorAtualizado.setNomeInstrutor(instrutor.getNomeInstrutor());
			return instrutorRepository.save(instrutorAtualizado);
		}else {
			return null;
		}
	}

	public Boolean deleteInstrutor(Integer id) {
		Instrutor instrutor = instrutorRepository.findById(id).orElse(null);
		if(instrutor != null) {
			instrutorRepository.delete(instrutor);
			return true;
		}else {
			return false;
		}
	}
}