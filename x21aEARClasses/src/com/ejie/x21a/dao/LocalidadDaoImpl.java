/*
* Copyright 2012 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
* Solo podrá usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
* SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
* Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.dao;

import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Provincia;
import com.ejie.x38.dto.Pagination;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Localidad;

/**
 * LocalidadDaoImpl generated by UDA, 14-ago-2012 12:59:38.
 * @author UDA
 */
 
@Repository
@Transactional
public class LocalidadDaoImpl implements LocalidadDao {
    private JdbcTemplate jdbcTemplate;
	private RowMapper<Localidad> rwMap = new RowMapper<Localidad>() {
		public Localidad mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           return new Localidad(
               resultSet.getBigDecimal("CODE"), resultSet.getString("DESCES"), resultSet.getString("DESCEU"), resultSet.getString("CSS"), new Comarca(resultSet.getBigDecimal("ComarcaCODE"), resultSet.getString("ComarcaDESCES"), resultSet.getString("ComarcaDESCEU"), resultSet.getString("ComarcaCSS"), new Provincia(resultSet.getBigDecimal("ComarcaCODEPROVINCIA"), null, null, null))
           ); } } ;

	/**
     * Method use to set the datasource.
     *
     * @param dataSource DataSource
     * @return
     */
    @Resource
    public void setDataSource(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Inserts a single row in the Localidad table.
     *
     * @param localidad Pagination
     * @return Localidad
     */
	public Localidad add(Localidad localidad) {
    	String query = "INSERT INTO LOCALIDAD (CODE, CODE_COMARCA, DESC_ES, DESC_EU, CSS) VALUES (?,?,?,?,?)";
				   Object getComarcaCodeAux=null;
		     if (localidad.getComarca()!= null  && localidad.getComarca().getCode()!=null ){
			     getComarcaCodeAux=localidad.getComarca().getCode();
		   	  }
		this.jdbcTemplate.update(query, localidad.getCode(), getComarcaCodeAux, localidad.getDescEs(), localidad.getDescEu(), localidad.getCss());
		return localidad;
	}

    /**
     * Updates a single row in the Localidad table.
     *
     * @param localidad Pagination
     * @return Localidad
     */
    public Localidad update(Localidad localidad) {
		String query = "UPDATE LOCALIDAD SET CODE_COMARCA=?, DESC_ES=?, DESC_EU=?, CSS=? WHERE CODE=?";
				Object getComarcaCodeAux=null;
				if (localidad.getComarca()!= null   && localidad.getComarca().getCode()!=null ){
					getComarcaCodeAux=localidad.getComarca().getCode();
				}
		this.jdbcTemplate.update(query, getComarcaCodeAux, localidad.getDescEs(), localidad.getDescEu(), localidad.getCss(), localidad.getCode());
		return localidad;
	}

    /**
     * Finds a single row in the Localidad table.
     *
     * @param localidad Pagination
     * @return Localidad
     */
    @Transactional (readOnly = true)
    public Localidad find(Localidad localidad) {
		String query = "SELECT t1.CODE CODE, t1.DESC_ES DESCES, t1.DESC_EU DESCEU, t1.CSS CSS, t2.CODE COMARCACODE, t2.DESC_ES COMARCADESCES, t2.DESC_EU COMARCADESCEU, t2.CSS COMARCACSS, t2.CODE_PROVINCIA COMARCACODEPROVINCIA FROM LOCALIDAD t1 , COMARCA t2  WHERE t1.CODE = ?   AND t1.CODE_COMARCA= t2.CODE(+)";
		
		List<Localidad> localidadList = this.jdbcTemplate.query(query, this.rwMap, localidad.getCode());
		return (Localidad) DataAccessUtils.uniqueResult(localidadList);
    }

    /**
     * Removes a single row in the Localidad table.
     *
     * @param localidad Pagination
     * @return
     */
    public void remove(Localidad localidad) {
		String query = "DELETE FROM LOCALIDAD WHERE CODE=?";
		this.jdbcTemplate.update(query, localidad.getCode());
    }
    
   /**
    * Finds a List of rows in the Localidad table.
    * 
    * @param localidad Localidad
    * @param pagination Pagination
    * @return List 
    */
	@Transactional (readOnly = true)
    public List<Localidad> findAll(Localidad localidad, Pagination pagination) {
		StringBuilder query = new StringBuilder("SELECT  t1.CODE CODE,t1.DESC_ES DESCES,t1.DESC_EU DESCEU,t1.CSS CSS,t2.CODE COMARCACODE,t2.DESC_ES COMARCADESCES,t2.DESC_EU COMARCADESCEU,t2.CSS COMARCACSS,t2.CODE_PROVINCIA COMARCACODEPROVINCIA "); 
		query.append("FROM LOCALIDAD t1 ,COMARCA t2 ");
		
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(localidad); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 AND t1.CODE_COMARCA= t2.CODE(+) ");
		where.append(mapaWhere.get("query"));
		query.append(where);
		
		List<?> params = (List<?>) mapaWhere.get("params");

		if (pagination != null) {
			query = pagination.getPaginationQuery(query);
		}
		
		return (List<Localidad>) this.jdbcTemplate.query(query.toString(), this.rwMap, params.toArray());
	}
	
    /**
     * Counts rows in the Localidad table.
     * 
     * @param localidad Localidad
     * @return Long
     */
    @Transactional (readOnly = true)
    public Long findAllCount(Localidad localidad) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM LOCALIDAD t1 , COMARCA t2 ");
		
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(localidad); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 AND t1.CODE_COMARCA= t2.CODE(+) ");
		where.append(mapaWhere.get("query"));
		query.append(where);		
		
		List<?> params = (List<?>) mapaWhere.get("params");
		
		return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());
	}
	
	/**
	 * Finds rows in the Localidad table using like.
     * 
     * @param localidad Localidad
     * @param pagination Pagination
     * @param startsWith Boolean
     * @return List 
     */
	@Transactional (readOnly = true)
    public List<Localidad> findAllLike(Localidad localidad, Pagination pagination, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT  t1.CODE CODE,t1.DESC_ES DESCES,t1.DESC_EU DESCEU,t1.CSS CSS,t2.CODE COMARCACODE,t2.DESC_ES COMARCADESCES,t2.DESC_EU COMARCADESCEU,t2.CSS COMARCACSS,t2.CODE_PROVINCIA COMARCACODEPROVINCIA "); 
        query.append("FROM LOCALIDAD t1 ,COMARCA t2 ");
      	
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(localidad,startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 AND t1.CODE_COMARCA= t2.CODE(+) ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		if (pagination != null) {
			query = pagination.getPaginationQuery(query);
		}
		
		return (List<Localidad>) this.jdbcTemplate.query(query.toString(), this.rwMap, params.toArray());
	}
	
	/**
	 * Counts rows in the Localidad table using like.
     * 
     * @param localidad Localidad
     * @param startsWith Boolean
     * @return Long 
     */
	@Transactional (readOnly = true)
    public Long findAllLikeCount(Localidad localidad, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM LOCALIDAD t1 ,COMARCA t2 ");

		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(localidad,startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 AND t1.CODE_COMARCA= t2.CODE(+) ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());
	}
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by 
	 * the Localidad entity 
	 * 
	 * @param localidad Localidad
	 *            Bean with the criteria values to filter by.
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - Generación de código de UDA
	private Map<String, ?> getWhereMap (Localidad localidad){
		
		StringBuffer where = new StringBuffer(LocalidadDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (localidad  != null  && localidad.getCode() != null ) {
			where.append(" AND t1.CODE = ?");
			params.add(localidad.getCode());
		}
		if (localidad!=null && localidad.getComarca() != null  && localidad.getComarca().getCode() != null ) {
			where.append(" AND t2.CODE = ?");
			params.add(localidad.getComarca().getCode());
		}
		if (localidad!=null && localidad.getComarca() != null && localidad.getComarca().getProvincia() != null && localidad.getComarca().getProvincia().getCode() != null ) {
			where.append(" AND t2.CODE_PROVINCIA = ?");
			params.add(localidad.getComarca().getProvincia().getCode());
		}
		if (localidad!=null && localidad.getComarca() != null  && localidad.getComarca().getDescEs() != null ) {
			where.append(" AND t2.DESC_ES = ?");
			params.add(localidad.getComarca().getDescEs());
		}
		if (localidad!=null && localidad.getComarca() != null  && localidad.getComarca().getDescEu() != null ) {
			where.append(" AND t2.DESC_EU = ?");
			params.add(localidad.getComarca().getDescEu());
		}
		if (localidad!=null && localidad.getComarca() != null  && localidad.getComarca().getCss() != null ) {
			where.append(" AND t2.CSS = ?");
			params.add(localidad.getComarca().getCss());
		}
		if (localidad  != null  && localidad.getDescEs() != null ) {
			where.append(" AND t1.DESC_ES = ?");
			params.add(localidad.getDescEs());
		}
		if (localidad  != null  && localidad.getDescEu() != null ) {
			where.append(" AND t1.DESC_EU = ?");
			params.add(localidad.getDescEu());
		}
		if (localidad  != null  && localidad.getCss() != null ) {
			where.append(" AND t1.CSS = ?");
			params.add(localidad.getCss());
		}

		Map<String,Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		
		return mapWhere;		
	}
	// CHECKSTYLE:ON CyclomaticComplexity - Generación de código de UDA
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by  
	 * the Localidad entity 
	 * 
	 * @param localidad Localidad
	 *            Bean with the criteria values to filter by.
     * @param startsWith Boolean	 
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - Generación de código de UDA
	private Map<String, ?> getWhereLikeMap (Localidad localidad, Boolean startsWith){
		
		StringBuffer where = new StringBuffer(LocalidadDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (localidad  != null  && localidad.getCode() != null ) {
			where.append(" AND t1.CODE = ?");
			params.add(localidad.getCode());
	     }			
		if (localidad!=null && localidad.getComarca() != null  && localidad.getComarca().getCode() != null ) {
			where.append(" AND t2.CODE = ?");
			params.add(localidad.getComarca().getCode());
	     }			
		if (localidad!=null && localidad.getComarca() != null && localidad.getComarca().getProvincia() != null && localidad.getComarca().getProvincia().getCode() != null ) {
			where.append(" AND t2.CODE_PROVINCIA = ?");
			params.add(localidad.getComarca().getProvincia().getCode());
	     }			
		if (localidad!=null && localidad.getComarca() != null  && localidad.getComarca().getDescEs() != null ) {
			where.append(" AND UPPER(t2.DESC_ES) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(localidad.getComarca().getDescEs().toUpperCase() +"%");
			}else{
				params.add("%"+localidad.getComarca().getDescEs().toUpperCase() +"%");
			}
			where.append(" AND t2.DESC_ES IS NOT NULL");
	     }			
		if (localidad!=null && localidad.getComarca() != null  && localidad.getComarca().getDescEu() != null ) {
			where.append(" AND UPPER(t2.DESC_EU) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(localidad.getComarca().getDescEu().toUpperCase() +"%");
			}else{
				params.add("%"+localidad.getComarca().getDescEu().toUpperCase() +"%");
			}
			where.append(" AND t2.DESC_EU IS NOT NULL");
	     }			
		if (localidad!=null && localidad.getComarca() != null  && localidad.getComarca().getCss() != null ) {
			where.append(" AND UPPER(t2.CSS) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(localidad.getComarca().getCss().toUpperCase() +"%");
			}else{
				params.add("%"+localidad.getComarca().getCss().toUpperCase() +"%");
			}
			where.append(" AND t2.CSS IS NOT NULL");
	     }			
		if (localidad  != null  && localidad.getDescEs() != null ) {
			where.append(" AND UPPER(t1.DESC_ES) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(localidad.getDescEs().toUpperCase() +"%");
			}else{
				params.add("%"+localidad.getDescEs().toUpperCase() +"%");
			}
			where.append(" AND t1.DESC_ES IS NOT NULL");
	     }			
		if (localidad  != null  && localidad.getDescEu() != null ) {
			where.append(" AND UPPER(t1.DESC_EU) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(localidad.getDescEu().toUpperCase() +"%");
			}else{
				params.add("%"+localidad.getDescEu().toUpperCase() +"%");
			}
			where.append(" AND t1.DESC_EU IS NOT NULL");
	     }			
		if (localidad  != null  && localidad.getCss() != null ) {
			where.append(" AND UPPER(t1.CSS) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(localidad.getCss().toUpperCase() +"%");
			}else{
				params.add("%"+localidad.getCss().toUpperCase() +"%");
			}
			where.append(" AND t1.CSS IS NOT NULL");
	     }			

		Map<String,Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		
		return mapWhere;		
	}
	// CHECKSTYLE:ON CyclomaticComplexity - Generación de código de UDA
	
	/**
	 * StringBuilder initilization value
	 */
	 public static final int STRING_BUILDER_INIT = 4096;}

