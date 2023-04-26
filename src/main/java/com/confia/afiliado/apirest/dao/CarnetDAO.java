package com.confia.afiliado.apirest.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.confia.afiliado.apirest.model.Carnet;

import net.sf.jasperreports.engine.JRException;

public interface CarnetDAO {
	
	public String saveCarnet(String id);
	
	public List <Carnet> getAll();
	
	public Carnet getById(String id);
	
	public String exportReport(String id) throws FileNotFoundException, JRException, SQLException, IOException;

}
