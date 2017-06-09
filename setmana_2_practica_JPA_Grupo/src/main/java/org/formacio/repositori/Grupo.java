package org.formacio.repositori;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Grupo {
	
	@Id 
	@GeneratedValue
	int id;
	private String nombre;
	
	public Grupo() {
	}
	
	public Grupo(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
