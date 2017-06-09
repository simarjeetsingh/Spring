package org.formacio.repositori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	public long size(){
		return grupoRepository.count();
	}
	public void create(String nom){
		grupoRepository.save(new Grupo(nom));
	}
	public Grupo getGrupo(String nom){
		return grupoRepository.findByNom(nom);
	}
	public Iterable<Grupo> getGrupos(){
		return grupoRepository.findAll();
	}
	public boolean existe(String nom){
		if(grupoRepository.findByNom(nom) == null){
			return false;
		}
		else
			return true;
	}
	
	public void deleteAll(){
		grupoRepository.deleteAll();
	}
}
