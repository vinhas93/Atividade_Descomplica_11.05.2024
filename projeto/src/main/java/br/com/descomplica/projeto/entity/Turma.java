package br.com.descomplica.projeto.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "turma")
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turma")
	private Integer idTurma;

	@Column(name = "materia")
	private String materia;

	@Column(name = "serie")
	private Integer serieTurma;

	@ManyToOne
	@JoinColumn(name = "id_instrutor", referencedColumnName = "id_instrutor")
	@JsonBackReference
	private Instrutor instrutor;

	
	public Integer getTurmaId() {
		return idTurma;
	}

	public void setTurmaId(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Integer getSerieTurma() {
		return serieTurma;
	}

	public void setSerieTurma(Integer serieTurma) {
		this.serieTurma = serieTurma;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

}