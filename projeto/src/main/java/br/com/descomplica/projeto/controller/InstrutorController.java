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

import br.com.descomplica.projeto.entity.Instrutor;
import br.com.descomplica.projeto.service.InstrutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
	@Autowired
	InstrutorService instrutorService;
	
	@GetMapping
	@Operation(summary="Listar todos os instrutores", description  = "Listagem de Instrutores")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso."),
			  @ApiResponse(responseCode = "400", description = "Requisição Inválida"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")})
	public ResponseEntity<List<Instrutor>> getAll(){
		List<Instrutor> instrutors = instrutorService.getAll();
		if(!instrutors.isEmpty())
			return new ResponseEntity<>(instrutors, HttpStatus.OK);
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	@Operation(summary="Seleciona um instrutor pelo seu Id", description  = "Instrutor por Id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso."),
			  @ApiResponse(responseCode = "400", description = "Requisição Inválida"),  
			  @ApiResponse(responseCode = "404", description = "Id não encontrado.")})
	public ResponseEntity<Instrutor> getById(@PathVariable Integer id) {
		Instrutor instrutor = instrutorService.getById(id);
		if(instrutor != null)
			return new ResponseEntity<>(instrutor, HttpStatus.OK); 
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);		
	}
	
	@PostMapping
	@Operation(summary="Registra um novo instrutor na base de dados.", description  = "Registrar nome do instrutor")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "201", description = "Um novo registro foi criado com sucesso."),
			  @ApiResponse(responseCode = "400", description = "Requisição Inválida")})
	public ResponseEntity<Instrutor> saveInstrutor(@RequestBody Instrutor instrutor) {
		return new ResponseEntity<>(instrutorService.saveInstrutor(instrutor), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Operation(summary="Altera o nome de um instrutor na base de dados, a partir de seu Id.", description  = "Atualizar nome do instrutor")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "O registro foi atualizado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "Requisição Inválida"),  
			  @ApiResponse(responseCode = "404", description = "Id não encontrado.")})
	public ResponseEntity<Instrutor> updateInstrutor(@PathVariable Integer id, @RequestBody Instrutor instrutor) {
		Instrutor instrutorAtualizado = instrutorService.updateInstrutor(id, instrutor);
		if(instrutorAtualizado != null)
			return new ResponseEntity<>(instrutorAtualizado, HttpStatus.OK); 
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	@Operation(summary="Deleta um instrutor na base de dados, a partir de seu Id.", description  = "Deletar instrutor")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "O registro foi deletado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "Requisição Inválida"),  
			  @ApiResponse(responseCode = "404", description = "Id não encontrado.")})
	public ResponseEntity<Boolean> deleteInstrutor(@PathVariable Integer id) {
		if(instrutorService.deleteInstrutor(id))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else 
			return new ResponseEntity<>(false, HttpStatus.OK);
	}
}