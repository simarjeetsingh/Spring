package org.formacio.repositori;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity // indico que esta es la entidad de mi BBDD
@XmlRootElement // per a que acepti peticions XML
public class Grupo {

	@Id // indico que esta propiedad es la primary key
	@GeneratedValue
	private int clau;	
	private String nom;
	
	
	public Grupo() {
	}

	public Grupo( String nom) {
		
		this.nom = nom;
		
	}

	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
}
