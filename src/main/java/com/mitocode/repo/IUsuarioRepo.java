package com.mitocode.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {

	//from Usuario u where u.nombre = :username
	Usuario findOneByNombre(String username);

	//from Usuario u where u.nombre = :username
	Usuario findOneByIdUsuario(String username);
	
	/*@Query("from Usuario u where u.nombre = :nombre")
	List<Usuario> listarUsuarioId(@Param("nombre") String nombre);*/
		
	@Modifying
	@Query(value = "INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (:idUsuario, :idRol)", nativeQuery = true)
	void registrarRolPorDefecto(@Param("idUsuario") Integer idUsuario, @Param("idRol") Integer idRol);
}