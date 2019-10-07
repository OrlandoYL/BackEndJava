package com.mitocode.service;

import java.util.List;

public interface ICRUD<T> {

	T registrar(T obj);
	T modificar(T obj);
	T leer(Integer id);
	List<T> listar();
	void eliminar(Integer id);
}
