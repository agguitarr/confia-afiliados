package com.confia.afiliado.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.confia.afiliado.apirest.dao.CarnetDAO;
import com.confia.afiliado.apirest.model.Carnet;

@RestController
public class CarnetController {
	
	@Autowired
	private CarnetDAO cDao;
	
	@GetMapping("/carnets")
	public List <Carnet> getCarnets(){
		return cDao.getAll();	
	}
	
	@GetMapping("/carnets/{id}")
	public Carnet getCarnetById(@PathVariable String id) {
		return cDao.getById(id);
	}
	
	@GetMapping("/carnets/nuevo/{id}")
	public String getNuevoCarnet(@PathVariable String id) {
		return cDao.saveCarnet(id);
	}
	

}
