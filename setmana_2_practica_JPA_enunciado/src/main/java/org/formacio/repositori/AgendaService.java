package org.formacio.repositori;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {

	//private Map<String, Persona> bbdd = new LinkedHashMap<>();
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@PostConstruct
	public void init() {
		//bbdd.put("ant", new Persona("ant","Antoni","971-555123"));
		//bbdd.put("joa", new Persona("joa","Joana","971-555555"));
		//bbdd.put("lin", new Persona("lin","Lina","971-555888"));
		personaRepository.save(new Persona("ant","Antoni","971-555123"));
		personaRepository.save(new Persona("joa","Joana","971-555555"));
		personaRepository.save(new Persona("lin","Lina","971-555888"));
	}
	
	public void inserta (String id, String nom, String telefon) {
		//bbdd.put(id, new Persona(id, nom, telefon));
		personaRepository.save(new Persona(id, nom, telefon));
	}
	
	public Persona recupera (String id) {
		return personaRepository.findOne(id);
	}
	
	public int nombreContactes() {
		return personaRepository.count();
	}
}
