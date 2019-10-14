package com.mitocode.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	/*
	 * @PostMapping public Cliente registrar(@RequestBody Cliente cliente) { return
	 * service.registrar(cliente); }
	 */
	
	 @PostMapping
	public Cliente registrar(@RequestPart("cliente") Cliente cliente, @RequestPart("file") MultipartFile file) 
		throws IOException{		
		Cliente cli = cliente;
		cli.setNombres(cliente.getNombres());
		cli.setApellidos(cliente.getApellidos());
		cli.setFechaNac(cliente.getFechaNac());
		cli.setDni(cliente.getDni());
		cli.setFoto(file.getBytes());
		return service.registrar(cli);
	}
	 
	
	
	/*
	 * @PutMapping public Cliente modificar(@RequestBody Cliente cliente) { return
	 * service.modificar(cliente); }
	 */
	 @PutMapping
		public Cliente modificar(@RequestPart("cliente") Cliente cliente, @RequestPart("file") MultipartFile file)
				throws IOException {
				 Cliente cli = cliente;
				 cli.setNombres(cliente.getNombres());
				 cli.setApellidos(cliente.getApellidos());
				 cli.setFechaNac(cliente.getFechaNac());
				 cli.setDni(cliente.getDni());
				 cli.setFoto(file.getBytes());
			return service.modificar(cli);
		}
	/*
	 * @GetMapping("/{id}") public Cliente listarPorId(@PathVariable("id") Integer
	 * id) { return service.leer(id); }
	 */
	 @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
		public ResponseEntity<byte[]> listarPorId(@PathVariable("id") Integer id) {
			Cliente cli = service.leer(id);
			byte[] data = cli.getFoto();
			return new ResponseEntity<byte[]>(data, HttpStatus.OK);
		}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		service.eliminar(id);
	}
}
