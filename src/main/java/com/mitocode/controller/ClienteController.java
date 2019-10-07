package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Cliente;
import com.mitocode.service.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService service;
	
	@GetMapping
	public List<Cliente> listar(){
		return service.listar();
	}
	
	@PostMapping
	public Cliente registrar(@RequestBody Cliente cliente) {		
		return service.registrar(cliente);
	}
	
	@PutMapping
	public Cliente modificar(@RequestBody Cliente cliente) {
		return service.modificar(cliente);
	}
	
	@GetMapping("/{id}")
	public Cliente listarPorId(@PathVariable("id") Integer id) {
		return service.leer(id);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		service.eliminar(id);
	}
}
