package com.mitocode.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.dto.ResumenVentaDTO;
import com.mitocode.dto.VentaDTO;
import com.mitocode.model.Venta;
import com.mitocode.model.VentaComida;
import com.mitocode.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private IVentaService service;
	
	@GetMapping
	public List<Venta> listar(){
		return service.listar();
	}
	
	@PostMapping
	public Integer registrar(@RequestBody VentaDTO ventaDTO) {		
		return service.registrarTransaccional(ventaDTO);
	}
	
	@PutMapping
	public Venta modificar(@RequestBody Venta venta) {
		return service.modificar(venta);
	}
	
	@GetMapping("/{id}")
	public Venta listarPorId(@PathVariable("id") Integer id) {
		return service.leer(id);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		service.eliminar(id);
	}
	
	
	@PostMapping(value = "/generarReporte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, consumes = "application/json")
	public ResponseEntity<byte[]> generarReporte(@RequestBody VentaDTO venta) {
		byte[] data = null;
		data = service.generarReporte(venta);
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@PostMapping(value = "/buscar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Venta>> buscar(@RequestBody FiltroConsultaDTO filtro) {
		List<Venta> ventas = new ArrayList<>();

		if (filtro != null) {
			if (filtro.getFechaConsulta() != null) {
				ventas = service.buscarfecha(filtro);
			} else {
				ventas = service.buscar(filtro);
			}
		}
		return new ResponseEntity<List<Venta>>(ventas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscar/comidas/{idVenta}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VentaComida>> listar(@PathVariable("idVenta") Integer idVenta) {
		List<VentaComida> ventaComidas = new ArrayList<>();
		ventaComidas = service.listarComidasPorVenta(idVenta);
		return new ResponseEntity<List<VentaComida>>(ventaComidas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarResumen", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResumenVentaDTO>> listarResumen() {
		List<ResumenVentaDTO> ventas = new ArrayList<>();
		ventas = service.listarResumen();
		return new ResponseEntity<List<ResumenVentaDTO>>(ventas, HttpStatus.OK);
	}

	
}
