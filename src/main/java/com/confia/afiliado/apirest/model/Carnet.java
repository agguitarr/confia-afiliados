package com.confia.afiliado.apirest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carnet {
	
	private String nup;
	
	private String cod_categoria;
	
	private String desc_categoria;
	
	private String num_id;
	
	private String fecha_afp;
	
	private String nombre_completo;
	
	private String fecha_incorp_sap;
	
	public String getNup() {
		return nup;
	}
	public void setNup(String nup) {
		this.nup = nup;
	}
	public String getCod_categoria() {
		return cod_categoria;
	}
	public void setCod_categoria(String cod_categoria) {
		this.cod_categoria = cod_categoria;
	}
	public String getDesc_categoria() {
		return desc_categoria;
	}
	public void setDesc_categoria(String desc_categoria) {
		this.desc_categoria = desc_categoria;
	}
	public String getNum_id() {
		return num_id;
	}
	public void setNum_id(String num_id) {
		this.num_id = num_id;
	}
	public String getFecha_afp() {
		return fecha_afp;
	}
	public void setFecha_afp(String fecha_afp) {
		this.fecha_afp = fecha_afp;
	}
	public String getNombre_completo() {
		return nombre_completo;
	}
	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}
	public String getFecha_incorp_sap() {
		return fecha_incorp_sap;
	}
	public void setFecha_incorp_sap(String fecha_incorp_sap) {
		this.fecha_incorp_sap = fecha_incorp_sap;
	}

	
	
}
