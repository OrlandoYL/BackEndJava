package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comida")
public class Comida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idComida;

	@Column(name = "nombre", nullable = false, length = 80)
	private String nombre;

	@Column(name = "precio", length = 2, columnDefinition = "decimal(5,2)")
	private Double precio;

	@JsonIgnore
	@Column(name = "foto", updatable = false)
	private byte[] foto;

	public Integer getIdComida() {
		return idComida;
	}

	public void setIdComida(Integer idComida) {
		this.idComida = idComida;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idComida == null) ? 0 : idComida.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comida other = (Comida) obj;
		if (idComida == null) {
			if (other.idComida != null)
				return false;
		} else if (!idComida.equals(other.idComida))
			return false;
		return true;
	}
	
	

}
