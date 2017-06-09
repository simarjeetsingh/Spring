package org.formacio.repositori;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {
	
	// inyectar la dependencia de la BBDD
	@Autowired
	private PersonaRepository personaRepo;
	
	@Autowired
	private GrupoService grupoService;
	
	/*
	 * Incluir @PostConstruct para ejecutar
	 * la aplicacion en el navegador.
	 */
	public void init() {
		// el metodo save() de la BBDD escribe la persona
		// si no existe. Si existe, pasa.
		grupoService.create("uno");
		personaRepo.save(new Persona("ant","Antoni","971-555123", grupoService.getGrupo("uno")));
		personaRepo.save(new Persona("joa","Joana","971-555555", grupoService.getGrupo("uno")));
		personaRepo.save(new Persona("lin","Lina","971-555888", grupoService.getGrupo("uno")));
	}
	
	public void inserta(String id, String nom, String telefon, String grupo) {
		if(!grupoService.existe(grupo)){
			grupoService.create(grupo);
		}
		personaRepo.save(new Persona(id, nom, telefon, grupoService.getGrupo(grupo)));
	}
	
	public Persona recupera(String id) {
		// bbdd.get(id);
		return personaRepo.findOne(id);
	}
	
	public Long nombreContactes() {
		// bbdd.size();
		return personaRepo.count();
	}
	
	public void update(String id, String nom, String telefon, String grupo){
		
		Persona persona = personaRepo.findByClau(id);
		
		if(persona == null){
			this.inserta(id, nom, telefon, grupo);
		}
		else {
			//persona.setClau(id);
			persona.setNom(nom);
			persona.setTelefon(telefon);
			/*if(!grupoService.existe(grupo)){
				grupoService.create(grupo);
				persona.setGrupo(grupoService.getGrupo(grupo));
			}		
			*/
			compruebaGrupo(grupo, persona);
			personaRepo.save(persona);
		}	
		personaRepo.save(new Persona(id, nom, telefon, grupoService.getGrupo(grupo)));
	}
	
	
	private void compruebaGrupo(String grupo, Persona persona ){
		if(!grupoService.existe(grupo)){
			grupoService.create(grupo);
			persona.setGrupo(grupoService.getGrupo(grupo));
		}
	}
	
	
	public String listadoPersonas(){
		List<String> listaPersonas = new ArrayList<String>();
		for(Persona persona : personaRepo.findAll()){
			listaPersonas.add(persona.getNom());
		}
		return String.join(" ", listaPersonas);
	}
	
	public String listadoGrupos(){
		List<String> listaGrupos = new ArrayList<String>();
		for(Grupo grupo: grupoService.getGrupos()){
			listaGrupos.add(grupo.getNombre());
		}
		return String.join(" ", listaGrupos);
	}
	
	public String listadoPersonasGrupo(String grupo){
		List<String> listaPersonas = new ArrayList<String>();
		for(Persona persona :  personaRepo.findByGrupoNombre(grupo)){
			listaPersonas.add(persona.getNom());
		}		
		return String.join(" ", listaPersonas);
	}
	
	public void deleteAll(){
		personaRepo.deleteAll();
	}
}
