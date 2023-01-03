package org.jonathan.blogPessoal.controller;

import org.jonathan.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//controller camada controladora das api/endspoints-> endpoint é as funcoes das aplicacoes/ endereços das aplicacoes 
import java.util.List;

import org.jonathan.blogPessoal.model.Postagem;

@RestController // permite o CRUD e status associados
@RequestMapping("/postagens")
@CrossOrigin(origins = "*")
public class PostagemController {

	@Autowired // transfere a responsabilidade do controller pro repository --- tem acesso ao
				// bd
	private PostagemRepository repository;

	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	public PostagemController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id) { // a variavel do caminho da URI
		return repository.findById(id) // retornando a interface autowired o findBy devolve o objeto postagem ou um
										// notfound
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/titulo/{titulo}") // requisição do titulo 
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping // Inserção na base de dados.. 
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem) { // solicita os dados body na requisição 
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}

	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem) { // atualiza a base de dados 
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}