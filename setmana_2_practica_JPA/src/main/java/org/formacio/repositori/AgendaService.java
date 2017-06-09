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

	// private Map<String, Persona> bbdd = new LinkedHashMap<>();
	
	@PostConstruct
	public void init() {
		// bbdd.put("ant", new Persona("ant","Antoni","971-555123"));
		// bbdd.put("joa", new Persona("joa","Joana","971-555555"));
		// bbdd.put("lin", new Persona("lin","Lina","971-555888"));
		// el metodo save() de la BBDD escribe la persona
		// si no existe. Si existe, pasa.
		grupoService.create("NekoTeam");
		personaRepo.save(new Persona("ant","Antoni","971-555123", grupoService.getGrupo("NekoTeam")));
		personaRepo.save(new Persona("joa","Joana","971-555555", grupoService.getGrupo("NekoTeam")));
		personaRepo.save(new Persona("lin","Lina","971-555888", grupoService.getGrupo("NekoTeam")));
	}
	
	public void inserta(String id, String nom, String telefon, String grupo ) {
		// bbdd.put(id, new Persona(id, nom, telefon));
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
		
		personaRepo.save(new Persona(id, nom, telefon, grupoService.getGrupo(grupo)));
		
		
		
		
		
		
	}
	
	public String listadoPersonas(){
		List<String> listaPersonas = new ArrayList<String>();
		for(Persona persona : personaRepo.findAll()){
			listaPersonas.add(persona.getNom());
		}
		return String.join(" ", listaPersonas);
	}
	
	
	
	
	private void compruebaGrupo(String grupo, Persona persona ){
		if(!grupoService.existe(grupo)){
			grupoService.create(grupo);
			persona.setGrupo(grupoService.getGrupo(grupo));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
