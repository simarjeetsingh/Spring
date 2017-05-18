package org.formacio;

import org.formacio.component.Utilitat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @SpringBootApplication fa moltes coses diferents. En aquest cas, la que ens 
 * interessa es que fa un component scan (inspeccio de totes les classes cercant 
 * classes que siguin componnents) a tots els packages a partir d'aquesta 
 * ubicacio.
 * 
 * En particular, detectara els components que son dins org.formacio.component: 
 * Utilitat i RepositoriAlumnesEnMemoria
 */
@SpringBootApplication
public class ComponentsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ComponentsApplication.class, args);
		
		// Generalment el metode anterior (run) ja executara tota l'aplicacio
		// i al metode main ja no farem res mes.
		// Pero aquest es un projecte peculiar. Volem mostrar com funcionen
		// els components que queden enregistrats a l'AplicationContext que crea
		// el metode run (ctx).
		
		
		// Dins ctx hi ha els beans creats a la inicialitzacio: en particular
		// Utilitat i RepositoriAlumnesEnMemoria
		
		// Obtenim el bean Utilitat
		Utilitat util = ctx.getBean(Utilitat.class);
		
		// Ja podem emprar-ho i esta correctament inicialitzat
		System.out.println(util.infoCurs("Introducció a Spring"));
		
		ctx.close();
	}
}
