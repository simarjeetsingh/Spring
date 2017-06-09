package org.formacio.mvc;


import org.formacio.repositori.AgendaService;
import org.formacio.repositori.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AgendaServiceController {
	
	@Autowired
	private AgendaService agenda;
	
	@RequestMapping(path="/nombre")
	@ResponseBody
	public String nombreContactes(){
		// metodo count de la BBDD devuelve Long
		Long nombreContactes = agenda.nombreContactes();
		return nombreContactes.toString();		
	}
	
	@RequestMapping(path="/telefon")
	@ResponseBody
	public String recuperaTelefon(@RequestParam String id){
		return agenda.recupera(id).getTelefon();
	}
	
	
	@RequestMapping(path="/contacte/{id}")
	@ResponseBody
	public Persona recuperaContacte(@PathVariable String id){
		if(agenda.recupera(id) == null){
			throw new OperationException();
		} 	
		else
			return agenda.recupera(id);
	}
	
	@RequestMapping(path="/afegir", method=RequestMethod.POST)
	@ResponseBody
	public String afegirContacte(@RequestParam String id, @RequestParam String nom, @RequestParam String telefon, @RequestParam String grupo){
		agenda.update(id, nom, telefon, grupo);
		return "ok";
	}
	
	/* 
	 * Metodos mios para utilizar metodo update de la BBDD 
	 * 
	 * */	
	
	@RequestMapping(path="/actualizar", method=RequestMethod.POST)
	@ResponseBody
	public String actualizarContacto(@RequestParam String id, @RequestParam String nom, @RequestParam String telefon, @RequestParam String grupo){
		agenda.update(id, nom, telefon, grupo);
		return "ok";
	}
	
	
	// localhost:8080/name?id=jos
	@RequestMapping(path="/name")
	@ResponseBody
	public String recuperaNombre(@RequestParam String id){
		return agenda.recupera(id).getNom();
	}
	
	@RequestMapping(path="/listado")
	@ResponseBody
	public String listadoPersonas(){
		return agenda.listadoPersonas();
	}
	
	@RequestMapping(path="/grupos")
	@ResponseBody
	public String listadoGrupos(){
		return agenda.listadoGrupos();
	}
	
	@RequestMapping(path="/grupo")
	@ResponseBody
	public String listadoPersonasGrupo(@RequestParam String grupo){
		return agenda.listadoPersonasGrupo(grupo);
	}


}
