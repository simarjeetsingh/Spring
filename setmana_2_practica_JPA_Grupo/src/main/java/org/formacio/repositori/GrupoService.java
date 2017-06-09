package org.formacio.repositori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//Nuestro


@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepo;
	
	public long size(){
		return grupoRepo.count();
	}
	
	public void create(String nombre){
		grupoRepo.save(new Grupo(nombre));
	}
	
	public Grupo getGrupo(String nombre){
		return grupoRepo.findByNombre(nombre);
	}
	
	public Iterable<Grupo> getGrupos(){
		return grupoRepo.findAll();
	}
	
	public boolean existe(String nombre){
		if(grupoRepo.findByNombre(nombre) == null){
			return false;
		}
		else
			return true;
	}
	
	public void deleteAll(){
		grupoRepo.deleteAll();
	}

}
