package org.formacio.component;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

@Component
public class ServeiAlumnat {
	
	@Autowired
	private RepositoriAlumnes repositori;
	/**
	 * ha de donar d'alta a la base de dades d'alumnes l'alumne indicat amb 
	 * el corresponent codi.
	 * Si el nom de l'alumne es null, no l'ha de donar d'alta
	 * Retorna true si l'alumne s'ha inserit, false si no.
	 */
	
	public boolean matricula (int id, String alumne) {
		if (alumne == null){			
			return false;
		}else{
			repositori.altaAlumne(id, alumne);
			return true;
		}
		
	}
	/*
	@PostConstruct
	public void insertarAlumnes() {
		repositori.altaAlumne(1, "Antonia");
		repositori.altaAlumne(2, "Joan");
	}	
	*/
	
	@PostConstruct
	public void init(){
		/* 
		 * Open/Closed
		 * i Single Responsability 
		 * Principis SOLID 
		 * */
		this.inicialitzarRepositoriAlumnes();
	}
	
	public void inicialitzarRepositoriAlumnes(){
		
		String[] alumnes = {"Antonia", "Joan"};
		final byte POSICIO_INICIAL_REPOSITORI = 1;
		
		short posicio = POSICIO_INICIAL_REPOSITORI;
		for(String alumne: alumnes){			
			repositori.altaAlumne((int) posicio, alumne);
			posicio += 1;
		}
	}
	
	public int nombreAlumnesRepositori(){
		return repositori.llistaAlumnes().size();
	} 
	
}
