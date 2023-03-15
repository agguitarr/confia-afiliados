package com.confia.afiliado.apirest.dao;

import java.util.List;

import com.confia.afiliado.apirest.model.Carnet;

public interface CarnetDAO {
	
	public String saveCarnet(String id);
	
	public List <Carnet> getAll();
	
	public Carnet getById(String id);

}
