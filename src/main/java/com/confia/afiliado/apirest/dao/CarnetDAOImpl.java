package com.confia.afiliado.apirest.dao;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.CallableStatementCreatorFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.confia.afiliado.apirest.model.Carnet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class CarnetDAOImpl implements CarnetDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String saveCarnet(String id) {
		
		//llamada a store procedure (Oracle Function)
		String query = "{? = call web.WEB_CREAR_CARNET_FUNC (?, ?)}";
		
		//parametros de entrada y salida
		List<SqlParameter> params = List.of(
		        new SqlOutParameter("numero_carnet", Types.VARCHAR),
		        new SqlParameter("pCodEmpresa", Types.VARCHAR),
		        new SqlParameter("pNup", Types.VARCHAR)

		);
		
		//seteo de valores para parametros de entrada
		Map<String, Object> parameters = Map.of(
		        "pCodEmpresa", "1",
		        "pNup", id
		);
		
		CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(query, params);
		CallableStatementCreator creator = factory.newCallableStatementCreator(parameters);

		Map<String, Object> numero_carnet = jdbcTemplate.call(creator, params);
		
		//conversion de MAP a JSON
		 ObjectMapper objectMapper = new ObjectMapper();
		 String json;
		 
		 try {
			json = objectMapper.writeValueAsString(numero_carnet);
		} catch (JsonProcessingException e) {
			json = "";
		}
		 
		 return json;
	}

	@Override
	public List<Carnet> getAll() {
		String query = "SELECT cf.nup, "
						+ "      trim(to_char(cf.cod_categoria,'09'))  cod_categoria,  "
						+ "      initcap(lower(ca.des_categoria)) desc_categoria,   "
						+ "      f_di_3(per.cod_empresa, per.nup) num_id, "
						+ "      to_char(per.fecha_incorporacion,'dd/mm/yyyy') fecha_afp, "
						+ "      RE_NOMBRE_AFILIADO_FUNC(per.nup, per.cod_combina) nombre_completo, "
						+ "       to_char(per.fecha_incorp_sap,'dd/mm/yyyy')  fecha_incorp_sap "
						+ "FROM ge_clientes_fidelizacion cf,   "
						+ "     ge_categorias_fidelizacion ca, "
						+ "     bfp_persona per "
						+ "WHERE  cf.cod_empresa = ca.cod_empresa   "
						+ "AND cf.cod_categoria = ca.cod_categoria   "
						+ "AND per.nup = cf.nup  "
						+ "AND cf.cod_etapa = 'ACT' "
						+ "AND rownum <= 10";
		
		return jdbcTemplate.query(query, new BeanPropertyRowMapper<Carnet>(Carnet.class));
		
	}

	@Override
	public Carnet getById(String id) {
		String query = "SELECT cf.nup, "
				+ "      trim(to_char(cf.cod_categoria,'09'))  cod_categoria,  "
				+ "      initcap(lower(ca.des_categoria)) desc_categoria,   "
				+ "      f_di_3(per.cod_empresa, per.nup) num_id, "
				+ "      to_char(per.fecha_incorporacion,'dd/mm/yyyy') fecha_afp, "
				+ "      RE_NOMBRE_AFILIADO_FUNC(per.nup, per.cod_combina) nombre_completo, "
				+ "       to_char(per.fecha_incorp_sap,'dd/mm/yyyy')  fecha_incorp_sap "
				+ "FROM ge_clientes_fidelizacion cf,   "
				+ "     ge_categorias_fidelizacion ca, "
				+ "     bfp_persona per "
				+ "WHERE  cf.cod_empresa = ca.cod_empresa   "
				+ "AND cf.cod_categoria = ca.cod_categoria   "
				+ "AND per.nup = cf.nup  "
				+ "AND cf.cod_etapa = 'ACT'   "
				+ "AND per.nup = ?";
		
		return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<Carnet>(Carnet.class), id);
	}

}
