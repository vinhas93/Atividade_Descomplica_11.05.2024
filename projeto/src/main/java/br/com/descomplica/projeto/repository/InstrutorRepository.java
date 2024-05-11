package br.com.descomplica.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.entity.Instrutor;

public interface InstrutorRepository extends JpaRepository<Instrutor,Integer> {

}