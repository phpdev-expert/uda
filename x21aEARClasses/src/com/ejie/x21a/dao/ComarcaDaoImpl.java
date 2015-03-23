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

import com.ejie.x21a.model.Provincia;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.util.PaginationManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Comarca;
/**   * ComarcaDaoImpl generated by UDA 1.0, 29-jul-2011 9:08:11.
*  @author UDA
*/
@Repository
@Transactional
public class ComarcaDaoImpl implements ComarcaDao {
    private SimpleJdbcTemplate jdbcTemplate;
	private RowMapper<Comarca> rwMap = new RowMapper<Comarca>() {
          public Comarca mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           return new Comarca(
               resultSet.getBigDecimal("CODE"), resultSet.getString("DESCES"), resultSet.getString("DESCEU"), resultSet.getString("CSS"), new Provincia(resultSet.getBigDecimal("ProvinciaCODE"), resultSet.getString("ProvinciaDESCES"), resultSet.getString("ProvinciaDESCEU"), resultSet.getString("ProvinciaCSS"))
           ); } } ;

     /**
     * Method use to set the datasource.
     *
     * @param dataSource DataSource
     *
     */
    @Resource
     public void setDataSource(DataSource dataSource) {
      this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    /**
     * Inserts a single row in the Comarca table.
     *
     * @param comarca Pagination
     * @return Comarca
     */
     public Comarca add(Comarca comarca) {

      String query = "INSERT INTO COMARCA( CODE,CODE_PROVINCIA,DESC_ES,DESC_EU,CSS)"
        + "VALUES (?,?,?,?,?)";

				   Object getProvinciaCodeAux=null;
		     if (comarca.getProvincia()!= null  && comarca.getProvincia().getCode()!=null ){
			     getProvinciaCodeAux=comarca.getProvincia().getCode();
		   	  }
      this.jdbcTemplate.update(query, comarca.getCode(), getProvinciaCodeAux, comarca.getDescEs(), comarca.getDescEu(), comarca.getCss());
      return comarca;
    }

    /**
     * Updates a single row in the Comarca table.
     *
     * @param comarca Pagination
     * @return Comarca
     */
    public Comarca update(Comarca comarca) {
      String query = "UPDATE COMARCA SET CODE_PROVINCIA=?,DESC_ES=?,DESC_EU=?,CSS=? WHERE CODE=?";
				   Object getProvinciaCodeAux=null;
		     if (comarca.getProvincia()!= null   && comarca.getProvincia().getCode()!=null ){
			     getProvinciaCodeAux=comarca.getProvincia().getCode();
		   	  }
      this.jdbcTemplate.update(query, getProvinciaCodeAux, comarca.getDescEs(), comarca.getDescEu(), comarca.getCss(), comarca.getCode());
      return comarca;

    }

    /**
     * Finds a single row in the Comarca table.
     *
     * @param comarca Pagination
     * @return Comarca
     */
    @Transactional (readOnly = true)
    public Comarca find(Comarca comarca) {
      String query = "SELECT t1.CODE CODE, t1.DESC_ES DESCES, t1.DESC_EU DESCEU, t1.CSS CSS, t2.CODE PROVINCIACODE, t2.DESC_ES PROVINCIADESCES, t2.DESC_EU PROVINCIADESCEU, t2.CSS PROVINCIACSS " 
         + "FROM COMARCA t1 , PROVINCIA t2  " 
         + "WHERE t1.CODE = ?   AND t1.CODE_PROVINCIA= t2.CODE(+)  ";
		  return (Comarca) this.jdbcTemplate.queryForObject(query, 
        rwMap , comarca.getCode());	 
    }

    /**
     * Removes a single row in the Comarca table.
     *
     * @param comarca Pagination
     *
     */
    public void remove(Comarca comarca) {
      String query = "DELETE  FROM COMARCA WHERE CODE=?";
      this.jdbcTemplate.update(query, comarca.getCode());

    }
   /**
    * Finds a List of rows in the Comarca table.
    * 
    * @param comarca Comarca
    * @param pagination Pagination
    * @return List 
    */
@Transactional (readOnly = true)
    public List<Comarca> findAll(Comarca comarca, Pagination pagination) {
      StringBuffer where = new StringBuffer(3000);
      List<Object> params = new ArrayList<Object>();
      where.append(" WHERE 1=1 AND t1.CODE_PROVINCIA= t2.CODE(+) 	");
      StringBuffer query = new StringBuffer("SELECT  t1.CODE CODE,t1.DESC_ES DESCES,t1.DESC_EU DESCEU,t1.CSS CSS,t2.CODE PROVINCIACODE,t2.DESC_ES PROVINCIADESCES,t2.DESC_EU PROVINCIADESCEU,t2.CSS PROVINCIACSS " 
        + "FROM COMARCA t1 ,PROVINCIA t2 ");
        if (comarca  != null  && comarca.getCode() != null ) {
           where.append(" AND t1.CODE = ?");
           params.add(comarca.getCode());
        }
        if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getCode() != null ) {
           where.append(" AND t2.CODE = ?");
           params.add(comarca.getProvincia().getCode());
        }
        if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getDescEs() != null ) {
           where.append(" AND t2.DESC_ES = ?");
           params.add(comarca.getProvincia().getDescEs());
        }
        if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getDescEu() != null ) {
           where.append(" AND t2.DESC_EU = ?");
           params.add(comarca.getProvincia().getDescEu());
        }
        if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getCss() != null ) {
           where.append(" AND t2.CSS = ?");
           params.add(comarca.getProvincia().getCss());
        }
        if (comarca  != null  && comarca.getDescEs() != null ) {
           where.append(" AND t1.DESC_ES = ?");
           params.add(comarca.getDescEs());
        }
        if (comarca  != null  && comarca.getDescEu() != null ) {
           where.append(" AND t1.DESC_EU = ?");
           params.add(comarca.getDescEu());
        }
        if (comarca  != null  && comarca.getCss() != null ) {
           where.append(" AND t1.CSS = ?");
           params.add(comarca.getCss());
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
		
		return (List<Comarca>) this.jdbcTemplate.query(query.toString(),rwMap, params.toArray());
    }
    /**
     * Counts rows in the Comarca table.
     * 
     * @param comarca Comarca
     * @return Long
     */
    @Transactional (readOnly = true)
     public Long findAllCount(Comarca comarca) {

       StringBuffer where = new StringBuffer(3000);
       List<Object> params = new ArrayList<Object>();
       where.append(" WHERE 1=1  and t1.CODE_PROVINCIA= t2.CODE(+) ");

      StringBuffer query = new StringBuffer("SELECT COUNT(1) FROM  COMARCA t1   ,  PROVINCIA t2  ");
          if (comarca  != null  && comarca.getCode() != null ) {
            where.append(" AND t1.CODE = ?");
            params.add(comarca.getCode());
          }
          if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getCode() != null ) {
            where.append(" AND t2.CODE = ?");
            params.add(comarca.getProvincia().getCode());
          }
          if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getDescEs() != null ) {
            where.append(" AND t2.DESC_ES = ?");
            params.add(comarca.getProvincia().getDescEs());
          }
          if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getDescEu() != null ) {
            where.append(" AND t2.DESC_EU = ?");
            params.add(comarca.getProvincia().getDescEu());
          }
          if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getCss() != null ) {
            where.append(" AND t2.CSS = ?");
            params.add(comarca.getProvincia().getCss());
          }
          if (comarca  != null  && comarca.getDescEs() != null ) {
            where.append(" AND t1.DESC_ES = ?");
            params.add(comarca.getDescEs());
          }
          if (comarca  != null  && comarca.getDescEu() != null ) {
            where.append(" AND t1.DESC_EU = ?");
            params.add(comarca.getDescEu());
          }
          if (comarca  != null  && comarca.getCss() != null ) {
            where.append(" AND t1.CSS = ?");
            params.add(comarca.getCss());
          }

        query.append(where);

         return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());


    }
  /**
    * Finds rows in the Comarca table using like.
    * 
    * @param comarca Comarca
    * @param pagination Pagination
    * @return List 
    */
@Transactional (readOnly = true)
    public List<Comarca> findAllLike(Comarca comarca, Pagination pagination, Boolean startsWith) {
      StringBuffer where = new StringBuffer(3000);
      List<Object> params = new ArrayList<Object>();
      where.append(" WHERE 1=1 AND t1.CODE_PROVINCIA= t2.CODE(+) 	");
      StringBuffer query = new StringBuffer("SELECT  t1.CODE CODE,t1.DESC_ES DESCES,t1.DESC_EU DESCEU,t1.CSS CSS,t2.CODE PROVINCIACODE,t2.DESC_ES PROVINCIADESCES,t2.DESC_EU PROVINCIADESCEU,t2.CSS PROVINCIACSS " 
        + "FROM COMARCA t1 ,PROVINCIA t2 ");
        if (comarca  != null  && comarca.getCode() != null ) {
			where.append(" AND t1.CODE like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getCode()  +"%");
			}else{
				params.add("%"+comarca.getCode() +"%");
			}	
				where.append(" AND t1.CODE IS NOT NULL");
		
        }
        if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getCode() != null ) {
			where.append(" AND t2.CODE like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getProvincia().getCode()  +"%");
			}else{
				params.add("%"+comarca.getProvincia().getCode() +"%");
			}	
				where.append(" AND t2.CODE IS NOT NULL");
		
        }
        if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getDescEs() != null ) {
			where.append(" AND UPPER(t2.DESC_ES) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getProvincia().getDescEs().toUpperCase()  +"%");
			}else{
				params.add("%"+comarca.getProvincia().getDescEs().toUpperCase() +"%");
			}	
				where.append(" AND t2.DESC_ES IS NOT NULL");
		
        }
        if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getDescEu() != null ) {
			where.append(" AND UPPER(t2.DESC_EU) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getProvincia().getDescEu().toUpperCase()  +"%");
			}else{
				params.add("%"+comarca.getProvincia().getDescEu().toUpperCase() +"%");
			}	
				where.append(" AND t2.DESC_EU IS NOT NULL");
		
        }
        if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getCss() != null ) {
			where.append(" AND UPPER(t2.CSS) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getProvincia().getCss().toUpperCase()  +"%");
			}else{
				params.add("%"+comarca.getProvincia().getCss().toUpperCase() +"%");
			}	
				where.append(" AND t2.CSS IS NOT NULL");
		
        }
        if (comarca  != null  && comarca.getDescEs() != null ) {
			where.append(" AND UPPER(t1.DESC_ES) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getDescEs().toUpperCase()  +"%");
			}else{
				params.add("%"+comarca.getDescEs().toUpperCase() +"%");
			}	
				where.append(" AND t1.DESC_ES IS NOT NULL");
		
        }
        if (comarca  != null  && comarca.getDescEu() != null ) {
			where.append(" AND UPPER(t1.DESC_EU) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getDescEu().toUpperCase()  +"%");
			}else{
				params.add("%"+comarca.getDescEu().toUpperCase() +"%");
			}	
				where.append(" AND t1.DESC_EU IS NOT NULL");
		
        }
        if (comarca  != null  && comarca.getCss() != null ) {
			where.append(" AND UPPER(t1.CSS) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getCss().toUpperCase()  +"%");
			}else{
				params.add("%"+comarca.getCss().toUpperCase() +"%");
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
		
		return (List<Comarca>) this.jdbcTemplate.query(query.toString(),rwMap, params.toArray());
    }

}

