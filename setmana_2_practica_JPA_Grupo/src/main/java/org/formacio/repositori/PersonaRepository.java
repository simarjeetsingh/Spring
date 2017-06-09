package org.formacio.repositori;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/*
 * Spring provee de una interfaz con los metodos CRUD
 * de las propiedades de la entidad /clase Persona.
 * Hay que indicarle cual es (el tipo de) la entidad y cual 
 * el tipo de la primary key.
 */
public interface PersonaRepository extends CrudRepository<Persona, String> {
		public Persona findByClau(String clau);
		public List<Persona> findByGrupoNombre(String nombre);
}
