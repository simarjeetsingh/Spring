package org.formacio.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/personal")
public class PersonalController {

	// no toqueu la declaracio de baseDeDades ni el metode getBaseDeDades
	// 
	// als metodes que anau afegint, tracteu aquest array com si fos la base de dades
	//
	// per exemple, obtenir la persona amb id 1 sera fer baseDeDades.get(1), etc etc 
	
	private List<String> baseDeDades = new ArrayList<>(Arrays.asList("Joana","Antonia","Pere"));
	
	public List<String> getBaseDeDades() {
		return baseDeDades;
	}

	// Poseu a partir d'aqui els vostre metodes
	
	@RequestMapping(path="/info")
	@ResponseBody
	public String numeroPersones(){
		return "Hi ha " + this.getBaseDeDades().size() + " persones";
	}
	
	@RequestMapping(path="/consulta")
	@ResponseBody
	public String personaEnPosicio(@RequestParam(name="id", required=true, defaultValue="0") int id){
		try {
			return this.getBaseDeDades().get(id);
		}
		catch(IndexOutOfBoundsException exception) {
			return "no existe persona en esta posicion";
		}
	}
	
	@RequestMapping(path="/persona/{id}")
	@ResponseBody
	public String personaEnPosicioRest(@PathVariable int id){
		try {
			return this.getBaseDeDades().get(id);
		}
		catch(IndexOutOfBoundsException exception) {
			return "no existe persona en esta posicion";
		}
	}
	
	@RequestMapping(path="/afegir", method=RequestMethod.POST)
	@ResponseBody
	public String afegirPersona(@RequestParam String nom){
		// @RequestParam tipo parametro indica que el parametro es obligatorio
		// ya que por defecto required=true
		this.getBaseDeDades().add(nom);
		return "ok";
	}
	
	
}
