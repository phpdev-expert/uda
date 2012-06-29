/*
* Copyright 2011 E.J.I.E., S.A.
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Departamento;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.util.PaginationManager;
/**   * DepartamentoDaoImpl generated by UDA 1.0, 26-may-2011 13:45:00.
*  @author UDA
*/
@Repository
@Transactional
public class DepartamentoDaoImpl implements DepartamentoDao {
    private JdbcTemplate jdbcTemplate;
	private RowMapper<Departamento> rwMap = new RowMapper<Departamento>() {
          public Departamento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           return new Departamento(
               resultSet.getBigDecimal("CODE"), resultSet.getString("DESCES"), resultSet.getString("DESCEU"), resultSet.getString("CSS")
           ); } } ;

     /**
     * Method use to set the datasource.
     *
     * @param dataSource DataSource
     *
     */
    @Resource
     public void setDataSource(DataSource dataSource) {
      this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Inserts a single row in the Departamento table.
     *
     * @param departamento Pagination
     * @return Departamento
     */
     public Departamento add(Departamento departamento) {

      String query = "INSERT INTO DEPARTAMENTO( CODE,DESC_ES,DESC_EU,CSS)"
        + "VALUES (?,?,?,?)";

      this.jdbcTemplate.update(query, departamento.getCode(), departamento.getDescEs(), departamento.getDescEu(), departamento.getCss());
      return departamento;
    }

    /**
     * Updates a single row in the Departamento table.
     *
     * @param departamento Pagination
     * @return Departamento
     */
    public Departamento update(Departamento departamento) {
      String query = "UPDATE DEPARTAMENTO SET DESC_ES=?,DESC_EU=?,CSS=? WHERE CODE=?";
      this.jdbcTemplate.update(query, departamento.getDescEs(), departamento.getDescEu(), departamento.getCss(), departamento.getCode());
      return departamento;

    }

    /**
     * Finds a single row in the Departamento table.
     *
     * @param departamento Pagination
     * @return Departamento
     */
    @Transactional (readOnly = true)
    public Departamento find(Departamento departamento) {
      String query = "SELECT t1.CODE CODE, t1.DESC_ES DESCES, t1.DESC_EU DESCEU, t1.CSS CSS " 
         + "FROM DEPARTAMENTO t1  " 
         + "WHERE t1.CODE = ?    ";
		  return (Departamento) this.jdbcTemplate.queryForObject(query, 
        rwMap , departamento.getCode());	 
    }

    /**
     * Removes a single row in the Departamento table.
     *
     * @param departamento Pagination
     *
     */
    public void remove(Departamento departamento) {
      String query = "DELETE  FROM DEPARTAMENTO WHERE CODE=?";
      this.jdbcTemplate.update(query, departamento.getCode());

    }
   /**
    * Finds a List of rows in the Departamento table.
    * 
    * @param departamento Departamento
    * @param pagination Pagination
    * @return List 
    */
@Transactional (readOnly = true)
    public List<Departamento> findAll(Departamento departamento, Pagination pagination) {
      StringBuffer where = new StringBuffer(3000);
      List<Object> params = new ArrayList<Object>();
      where.append(" WHERE 1=1 	");
      StringBuffer query = new StringBuffer("SELECT  t1.CODE CODE,t1.DESC_ES DESCES,t1.DESC_EU DESCEU,t1.CSS CSS " 
        + "FROM DEPARTAMENTO t1 ");
        if (departamento  != null  && departamento.getCode() != null ) {
           where.append(" AND t1.CODE = ?");
           params.add(departamento.getCode());
        }
        if (departamento  != null  && departamento.getDescEs() != null ) {
           where.append(" AND t1.DESC_ES = ?");
           params.add(departamento.getDescEs());
        }
        if (departamento  != null  && departamento.getDescEu() != null ) {
           where.append(" AND t1.DESC_EU = ?");
           params.add(departamento.getDescEu());
        }
        if (departamento  != null  && departamento.getCss() != null ) {
           where.append(" AND t1.CSS = ?");
           params.add(departamento.getCss());
        }
	     query.append(where);
        

        StringBuffer order = new StringBuffer(3000);
        if (pagination != null) {
          if (pagination.getSort() != null) {
             order.append(" ORDER BY " + pagination.getSort() + " " + pagination.getAscDsc());
             query.append(order);
          }

          query = new StringBuffer(PaginationManager.getQueryLimits(pagination,query.toString()));
        }
		
		return (List<Departamento>) this.jdbcTemplate.query(query.toString(),rwMap, params.toArray());
    }
    /**
     * Counts rows in the Departamento table.
     * 
     * @param departamento Departamento
     * @return Long
     */
    @Transactional (readOnly = true)
     public Long findAllCount(Departamento departamento) {

       StringBuffer where = new StringBuffer(3000);
       List<Object> params = new ArrayList<Object>();
       where.append(" WHERE 1=1  ");

      StringBuffer query = new StringBuffer("SELECT COUNT(1) FROM  DEPARTAMENTO t1  ");
          if (departamento  != null  && departamento.getCode() != null ) {
            where.append(" AND t1.CODE = ?");
            params.add(departamento.getCode());
          }
          if (departamento  != null  && departamento.getDescEs() != null ) {
            where.append(" AND t1.DESC_ES = ?");
            params.add(departamento.getDescEs());
          }
          if (departamento  != null  && departamento.getDescEu() != null ) {
            where.append(" AND t1.DESC_EU = ?");
            params.add(departamento.getDescEu());
          }
          if (departamento  != null  && departamento.getCss() != null ) {
            where.append(" AND t1.CSS = ?");
            params.add(departamento.getCss());
          }

        query.append(where);

         return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());


    }
  /**
    * Finds rows in the Departamento table using like.
    * 
    * @param departamento Departamento
    * @param pagination Pagination
    * @return List 
    */
@Transactional (readOnly = true)
    public List<Departamento> findAllLike(Departamento departamento, Pagination pagination, Boolean startsWith) {
      StringBuffer where = new StringBuffer(3000);
      List<Object> params = new ArrayList<Object>();
      where.append(" WHERE 1=1 	");
      StringBuffer query = new StringBuffer("SELECT  t1.CODE CODE,t1.DESC_ES DESCES,t1.DESC_EU DESCEU,t1.CSS CSS " 
        + "FROM DEPARTAMENTO t1 ");
        if (departamento  != null  && departamento.getCode() != null ) {
			where.append(" AND t1.CODE like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamento.getCode()  +"%");
			}else{
				params.add("%"+departamento.getCode() +"%");
			}	
				where.append(" AND t1.CODE IS NOT NULL");
		
        }
        if (departamento  != null  && departamento.getDescEs() != null ) {
			where.append(" AND UPPER(t1.DESC_ES) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamento.getDescEs().toUpperCase()  +"%");
			}else{
				params.add("%"+departamento.getDescEs().toUpperCase() +"%");
			}	
				where.append(" AND t1.DESC_ES IS NOT NULL");
		
        }
        if (departamento  != null  && departamento.getDescEu() != null ) {
			where.append(" AND UPPER(t1.DESC_EU) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamento.getDescEu().toUpperCase()  +"%");
			}else{
				params.add("%"+departamento.getDescEu().toUpperCase() +"%");
			}	
				where.append(" AND t1.DESC_EU IS NOT NULL");
		
        }
        if (departamento  != null  && departamento.getCss() != null ) {
			where.append(" AND UPPER(t1.CSS) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamento.getCss().toUpperCase()  +"%");
			}else{
				params.add("%"+departamento.getCss().toUpperCase() +"%");
			}	
				where.append(" AND t1.CSS IS NOT NULL");
		
        }
        
        query.append(where);
        

        StringBuffer order = new StringBuffer(3000);
        if (pagination != null) {
          if (pagination.getSort() != null) {
             order.append(" ORDER BY " + pagination.getSort() + " " + pagination.getAscDsc());
             query.append(order);
          }

          query = new StringBuffer(PaginationManager.getQueryLimits(pagination,query.toString()));
        }
		
		return (List<Departamento>) this.jdbcTemplate.query(query.toString(),rwMap, params.toArray());
    }

}

