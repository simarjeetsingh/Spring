package org.formacio.component;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Aquest es un altre component de l'aplicacio. 
 * 
 * En aquesta cas, en lloc d'emprar @Component hem emprat @Repository. Per aquest
 * cas es exactament igual. @Repositori es un subtipus de @Component per tant tambe
 * es detectat per Spring.
 * 
 * La idea de emprar @Repository en lloc de @Component es ser mes especifics: 
 * Estem dient que aquest compoment particular fera la tasca d'acces a un repositori
 * de dades. 
 * En un altre curs veurem en mes detall aquesta anotacio pero es important 
 * dir que, per aquest exemple, el comportament amb @Component i @Repository es
 * exactament identic
 */
@Repository
public class RepositoriAlumnesEnMemoria implements RepositoriAlumnes {

	@Override
	public List<String> getAlumnesPercurs(String curs) {
		return Arrays.asList("Antonia","Joan","Aina");
	}

	@Override
	public String toString() {
		return "Repositori d'alumnes que empra dades fitxes en memoria";
	}

	
	
}
