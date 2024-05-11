package br.com.descomplica.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.entity.Turma;

public interface TurmaRepository extends JpaRepository<Turma,Integer> {

}