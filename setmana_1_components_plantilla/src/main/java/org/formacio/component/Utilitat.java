package org.formacio.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Component que proporciona funcionalitat per mostrar informacio d'un curs.
 * 
 * L'anotacio @Component fera que Spring Boot el crei i inicialitzi correctament
 */
@Component
public class Utilitat {
	
	/**
	 * Per tal de fer la seva tasca, Utilitat necessita accedir a les dades
	 * dels alumnes. El component que fa aixo es del tipus RepositoriAlumnes.
	 * 
	 * @Autowired fera que Spring Boot cerqui un component del tipus 
	 * RepositoriAlumnes i l'assigni a aquesta variable. 
	 * Fitxau-vos que el component que assignara es RepositoriAlumnesEnMemoria,
	 * encara que aqui emprem la interficie (es la practica recomanada)
	 */
	@Autowired
	private RepositoriAlumnes repositori;
	
	/**
	 * Si definim un metode anotat amb @PostConstruct Spring l'invocara una
	 * vegada hagui creat el component i inicialitzat totes les seves 
	 * dependencies. 
	 * Aquesta metode se invoca nomes una vegada i sempre abans de que el
	 * component rebi cap invocacio de un client
	 */
	@PostConstruct
	public void init() {
		System.out.println("inialitzat amb repositori " + repositori);
	}

	/**
	 * Metode de servei (d'utilitat per els clients).
	 * Aquest metode ja pot emprar la dependencia (repositori) tranquilament.
	 */
	public String infoCurs( String curs ) {
		return curs +  ": Hi ha  " + repositori.getAlumnesPercurs(curs).size() + " alumnes";
	}
}
