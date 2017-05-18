package org.formacio.component;

import java.util.List;

/**
 * Es una bona practica, encara que no sigui obligatori, publicar la funcionalitat
 * via una interficie. 
 * 
 * Fitxau-vos que no anotem aquesta interficie amb @Component ja que no es
 * cap component.
 */
public interface RepositoriAlumnes {

	List<String> getAlumnesPercurs(String curs);
}
