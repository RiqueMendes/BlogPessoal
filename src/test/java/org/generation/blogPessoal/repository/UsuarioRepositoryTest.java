package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start () {
		usuarioRepository.deleteAll();
		usuarioRepository.save(new Usuario(1,"Joao ","joao@email.com.br", "123456"));
		usuarioRepository.save(new Usuario(2,"Maria", "maria@email.com.br", "632452"));
		usuarioRepository.save(new Usuario(3,"Pedro", "pedro@email.com.br", "145235"));
		
	}
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario () {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	}
	@Test
	@DisplayName("Retorna 3 usuario")
		public void deveRetornarTresUsuario() {
			List<Usuario> listadeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
			assertEquals(3, listadeUsuarios.size());
			assertTrue(listadeUsuarios.get(0).getNome().equals("Joao da Silva"));
			assertTrue(listadeUsuarios.get(1).getNome().equals("Manuela da Silva"));
			assertTrue(listadeUsuarios.get(2).getNome().equals("Adriana da Silva"));
		}
	}

