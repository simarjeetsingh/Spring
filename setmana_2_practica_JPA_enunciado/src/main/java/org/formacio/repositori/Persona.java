package org.formacio.repositori;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@XmlRootElement // per a que acepti peticions XML
public class Persona {
	
	@Id
	private String clau;
	@JsonProperty("contacte")
	private String nom;
	private String telefon;
	
	public Persona() {
	}

	public Persona(String clau, String nom, String telefon) {
		this.clau = clau;
		this.nom = nom;
		this.telefon = telefon;
	}

	public String getClau() {
		return clau;
	}

	public void setClau(String clau) {
		this.clau = clau;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

}
