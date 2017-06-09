package org.formacio.repositori;

import org.springframework.data.repository.CrudRepository;

public interface GrupoRepository extends CrudRepository<Grupo, Integer>{
	
		public Grupo findByNombre(String nombre);
		
}
