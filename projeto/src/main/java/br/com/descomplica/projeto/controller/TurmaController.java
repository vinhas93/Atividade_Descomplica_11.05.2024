package br.com.descomplica.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.descomplica.projeto.entity.Turma;
import br.com.descomplica.projeto.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/turma")
public class TurmaController {
	@Autowired
	TurmaService turmaService;
	
	@GetMapping
	@Operation(summary="Listar todas as Turmas", description  = "Listagem de Turmas")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso."),
			  @ApiResponse(responseCode = "400", description = "Requisição Inválida"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")})
	public ResponseEntity<List<Turma>> getAll(){
		List<Turma> turmas = turmaService.getAll();
		if(!turmas.isEmpty())
			return new ResponseEntity<>(turmas, HttpStatus.OK);
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	@Operation(summary="Seleciona uma turma pelo seu Id", description  = "Turma por Id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso."),
			  @ApiResponse(responseCode = "400", description = "Requisição Inválida"),  
			  @ApiResponse(responseCode = "404", description = "Id não encontrado.")})
	public ResponseEntity<Turma> getById(@PathVariable Integer id) {
		Turma turma = turmaService.getById(id);
		if(turma != null)
			return new ResponseEntity<>(turma, HttpStatus.OK); 
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);		
	}
	
	@PostMapping
	@Operation(summary="Registra uma nova turma na base de dados.", description  = "Registrar turma")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "201", description = "Um novo registro foi criado com sucesso."),
			  @ApiResponse(responseCode = "400", description = "Requisição Inválida")})
	public ResponseEntity<Turma> saveTurma(@RequestBody Turma turma) {
		return new ResponseEntity<>(turmaService.saveTurma(turma), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Operation(summary="Altera o nome de uma turma na base de dados, a partir de seu Id.", description  = "Atualizar turma")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "O registro foi atualizado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "Requisição Inválida"),  
			  @ApiResponse(responseCode = "404", description = "Id não encontrado.")})
	public ResponseEntity<Turma> updateTurma(@PathVariable Integer id, @RequestBody Turma turma) {
		Turma turmaAtualizada = turmaService.updateTurma(id, turma);
		if(turmaAtualizada != null)
			return new ResponseEntity<>(turmaAtualizada, HttpStatus.OK); 
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	@Operation(summary="Deleta uma turma na base de dados, a partir de seu Id.", description  = "Deletar turma")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "O registro foi deletado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "Requisição Inválida"),  
			  @ApiResponse(responseCode = "404", description = "Id não encontrado.")})
	public ResponseEntity<Boolean> deleteTurma(@PathVariable Integer id) {
		if(turmaService.deleteTurma(id))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else 
			return new ResponseEntity<>(false, HttpStatus.OK);
	}
}