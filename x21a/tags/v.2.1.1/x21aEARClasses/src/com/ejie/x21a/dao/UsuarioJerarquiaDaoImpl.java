package com.ejie.x21a.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.UsuarioJerarquia;
import com.ejie.x38.dto.Jerarquia;
import com.ejie.x38.dto.Pagination;

/**
 * UsuarioJerarquiaDaoImpl generated by UDA, 03-oct-2012 10:36:40.
 * @author UDA
 */
 
@Repository
@Transactional
public class UsuarioJerarquiaDaoImpl implements UsuarioJerarquiaDao {
    private JdbcTemplate jdbcTemplate;
	private RowMapper<UsuarioJerarquia> rwMap = new RowMapper<UsuarioJerarquia>() {
		public UsuarioJerarquia mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           return new UsuarioJerarquia(
               resultSet.getString("ID"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO1"), resultSet.getString("APELLIDO2"), resultSet.getString("EJIE"), resultSet.getDate("FECHAALTA"), resultSet.getDate("FECHABAJA"), resultSet.getString("IDPADRE"), resultSet.getString("GRUPO")
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
     * Inserts a single row in the UsuarioJerarquia table.
     *
     * @param usuariojerarquia Pagination
     * @return UsuarioJerarquia
     */
	public UsuarioJerarquia add(UsuarioJerarquia usuariojerarquia) {
    	String query = "INSERT INTO USUARIO_JERARQUIA (ID, NOMBRE, APELLIDO1, APELLIDO2, EJIE, FECHA_ALTA, FECHA_BAJA, ID_PADRE, GRUPO) VALUES (?,?,?,?,?,?,?,?,?)";
		this.jdbcTemplate.update(query, usuariojerarquia.getId(), usuariojerarquia.getNombre(), usuariojerarquia.getApellido1(), usuariojerarquia.getApellido2(), usuariojerarquia.getEjie(), usuariojerarquia.getFechaAlta(), usuariojerarquia.getFechaBaja(), usuariojerarquia.getIdPadre(), usuariojerarquia.getGrupo());
		return usuariojerarquia;
	}

    /**
     * Updates a single row in the UsuarioJerarquia table.
     *
     * @param usuariojerarquia Pagination
     * @return UsuarioJerarquia
     */
    public UsuarioJerarquia update(UsuarioJerarquia usuariojerarquia) {
		String query = "UPDATE USUARIO_JERARQUIA SET NOMBRE=?, APELLIDO1=?, APELLIDO2=?, EJIE=?, FECHA_ALTA=?, FECHA_BAJA=?, ID_PADRE=?, GRUPO=? WHERE ID=?";
		this.jdbcTemplate.update(query, usuariojerarquia.getNombre(), usuariojerarquia.getApellido1(), usuariojerarquia.getApellido2(), usuariojerarquia.getEjie(), usuariojerarquia.getFechaAlta(), usuariojerarquia.getFechaBaja(), usuariojerarquia.getIdPadre(), usuariojerarquia.getGrupo(), usuariojerarquia.getId());
		return usuariojerarquia;
	}

    /**
     * Finds a single row in the UsuarioJerarquia table.
     *
     * @param usuariojerarquia Pagination
     * @return UsuarioJerarquia
     */
    @Transactional (readOnly = true)
    public UsuarioJerarquia find(UsuarioJerarquia usuariojerarquia) {
		String query = "SELECT t1.ID ID, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.EJIE EJIE, t1.FECHA_ALTA FECHAALTA, t1.FECHA_BAJA FECHABAJA, t1.ID_PADRE IDPADRE, t1.GRUPO GRUPO FROM USUARIO_JERARQUIA t1  WHERE t1.ID = ?  ";
		
		List<UsuarioJerarquia> usuariojerarquiaList = this.jdbcTemplate.query(query, this.rwMap, usuariojerarquia.getId());
		return (UsuarioJerarquia) DataAccessUtils.uniqueResult(usuariojerarquiaList);
    }

    /**
     * Removes a single row in the UsuarioJerarquia table.
     *
     * @param usuariojerarquia Pagination
     * @return
     */
    public void remove(UsuarioJerarquia usuariojerarquia) {
		String query = "DELETE FROM USUARIO_JERARQUIA WHERE ID=?";
		this.jdbcTemplate.update(query, usuariojerarquia.getId());
    }
    
   /**
    * Finds a List of rows in the UsuarioJerarquia table.
    * 
    * @param usuariojerarquia UsuarioJerarquia
    * @param pagination Pagination
    * @return List 
    */
	@Transactional (readOnly = true)
    public List<UsuarioJerarquia> findAll(UsuarioJerarquia usuariojerarquia, Pagination pagination) {
		StringBuilder query = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHAALTA,t1.FECHA_BAJA FECHABAJA,t1.ID_PADRE IDPADRE,t1.GRUPO GRUPO "); 
		query.append("FROM USUARIO_JERARQUIA t1 ");
		
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(usuariojerarquia); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);
		
		List<?> params = (List<?>) mapaWhere.get("params");

		if (pagination != null) {
			query = pagination.getPaginationQuery(query);
		}
		
		return (List<UsuarioJerarquia>) this.jdbcTemplate.query(query.toString(), this.rwMap, params.toArray());
	}
	
    /**
     * Counts rows in the UsuarioJerarquia table.
     * 
     * @param usuariojerarquia UsuarioJerarquia
     * @return Long
     */
    @Transactional (readOnly = true)
    public Long findAllCount(UsuarioJerarquia usuariojerarquia) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM USUARIO_JERARQUIA t1 ");
		
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(usuariojerarquia); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);		
		
		List<?> params = (List<?>) mapaWhere.get("params");
		
		return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());
	}
	
	/**
	 * Finds rows in the UsuarioJerarquia table using like.
     * 
     * @param usuariojerarquia UsuarioJerarquia
     * @param pagination Pagination
     * @param startsWith Boolean
     * @return List 
     */
	@Transactional (readOnly = true)
    public List<UsuarioJerarquia> findAllLike(UsuarioJerarquia usuariojerarquia, Pagination pagination, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHAALTA,t1.FECHA_BAJA FECHABAJA,t1.ID_PADRE IDPADRE,t1.GRUPO GRUPO "); 
        query.append("FROM USUARIO_JERARQUIA t1 ");
      	
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuariojerarquia,startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		if (pagination != null) {
			query = pagination.getPaginationQuery(query);
		}
		
		return (List<UsuarioJerarquia>) this.jdbcTemplate.query(query.toString(), this.rwMap, params.toArray());
	}
	
	/**
	 * Counts rows in the UsuarioJerarquia table using like.
     * 
     * @param usuariojerarquia UsuarioJerarquia
     * @param startsWith Boolean
     * @return Long 
     */
	@Transactional (readOnly = true)
    public Long findAllLikeCount(UsuarioJerarquia usuariojerarquia, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM USUARIO_JERARQUIA t1 ");

		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuariojerarquia,startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());
	}
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by 
	 * the UsuarioJerarquia entity 
	 * 
	 * @param usuariojerarquia UsuarioJerarquia
	 *            Bean with the criteria values to filter by.
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - Generación de código de UDA
	private Map<String, ?> getWhereMap (UsuarioJerarquia usuariojerarquia){
		
		StringBuffer where = new StringBuffer(UsuarioJerarquiaDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (usuariojerarquia  != null  && usuariojerarquia.getId() != null ) {
			where.append(" AND t1.ID = ?");
			params.add(usuariojerarquia.getId());
		}
		if (usuariojerarquia  != null  && usuariojerarquia.getNombre() != null ) {
			where.append(" AND t1.NOMBRE = ?");
			params.add(usuariojerarquia.getNombre());
		}
		if (usuariojerarquia  != null  && usuariojerarquia.getApellido1() != null ) {
			where.append(" AND t1.APELLIDO1 = ?");
			params.add(usuariojerarquia.getApellido1());
		}
		if (usuariojerarquia  != null  && usuariojerarquia.getApellido2() != null ) {
			where.append(" AND t1.APELLIDO2 = ?");
			params.add(usuariojerarquia.getApellido2());
		}
		if (usuariojerarquia  != null  && usuariojerarquia.getEjie() != null ) {
			where.append(" AND t1.EJIE = ?");
			params.add(usuariojerarquia.getEjie());
		}
		if (usuariojerarquia  != null  && usuariojerarquia.getFechaAlta() != null ) {
			where.append(" AND t1.FECHA_ALTA = ?");
			params.add(usuariojerarquia.getFechaAlta());
		}
		if (usuariojerarquia  != null  && usuariojerarquia.getFechaBaja() != null ) {
			where.append(" AND t1.FECHA_BAJA = ?");
			params.add(usuariojerarquia.getFechaBaja());
		}
		if (usuariojerarquia  != null  && usuariojerarquia.getIdPadre() != null ) {
			where.append(" AND t1.ID_PADRE = ?");
			params.add(usuariojerarquia.getIdPadre());
		}
		if (usuariojerarquia  != null  && usuariojerarquia.getGrupo() != null ) {
			where.append(" AND t1.GRUPO = ?");
			params.add(usuariojerarquia.getGrupo());
		}


		Map<String,Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		
		return mapWhere;		
	}
	// CHECKSTYLE:ON CyclomaticComplexity - Generación de código de UDA
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by  
	 * the UsuarioJerarquia entity 
	 * 
	 * @param usuariojerarquia UsuarioJerarquia
	 *            Bean with the criteria values to filter by.
     * @param startsWith Boolean	 
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - Generación de código de UDA
	private Map<String, ?> getWhereLikeMap (UsuarioJerarquia usuariojerarquia, Boolean startsWith){
		
		StringBuffer where = new StringBuffer(UsuarioJerarquiaDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

//		if (usuariojerarquia  != null  && usuariojerarquia.getId() != null ) {
//			where.append(" AND UPPER(t1.ID) like ? ESCAPE  '\\'");
//			if (startsWith){
//				params.add(usuariojerarquia.getId().toUpperCase() +"%");
//			}else{
//				params.add("%"+usuariojerarquia.getId().toUpperCase() +"%");
//			}
//			where.append(" AND t1.ID IS NOT NULL");
//	     }			
		if (usuariojerarquia  != null  && usuariojerarquia.getNombre() != null ) {
			where.append(" AND UPPER(t1.NOMBRE) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuariojerarquia.getNombre().toUpperCase() +"%");
			}else{
				params.add("%"+usuariojerarquia.getNombre().toUpperCase() +"%");
			}
			where.append(" AND t1.NOMBRE IS NOT NULL");
	     }			
		if (usuariojerarquia  != null  && usuariojerarquia.getApellido1() != null ) {
			where.append(" AND UPPER(t1.APELLIDO1) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuariojerarquia.getApellido1().toUpperCase() +"%");
			}else{
				params.add("%"+usuariojerarquia.getApellido1().toUpperCase() +"%");
			}
			where.append(" AND t1.APELLIDO1 IS NOT NULL");
	     }			
		if (usuariojerarquia  != null  && usuariojerarquia.getApellido2() != null ) {
			where.append(" AND UPPER(t1.APELLIDO2) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuariojerarquia.getApellido2().toUpperCase() +"%");
			}else{
				params.add("%"+usuariojerarquia.getApellido2().toUpperCase() +"%");
			}
			where.append(" AND t1.APELLIDO2 IS NOT NULL");
	     }			
		
		if (usuariojerarquia  != null  && usuariojerarquia.getEjie() != null ) {
			where.append(" AND t1.EJIE = ?");
			params.add(usuariojerarquia.getEjie());
		}
		if (usuariojerarquia  != null  && usuariojerarquia.getFechaAlta() != null ) {
			where.append(" AND t1.FECHA_ALTA = ?");
			params.add(usuariojerarquia.getFechaAlta());
	     }			
		if (usuariojerarquia  != null  && usuariojerarquia.getFechaBaja() != null ) {
			where.append(" AND t1.FECHA_BAJA = ?");
			params.add(usuariojerarquia.getFechaBaja());
	     }			
//		if (usuariojerarquia  != null  && usuariojerarquia.getIdPadre() != null ) {
//			where.append(" AND UPPER(t1.ID_PADRE) like ? ESCAPE  '\\'");
//			if (startsWith){
//				params.add(usuariojerarquia.getIdPadre().toUpperCase() +"%");
//			}else{
//				params.add("%"+usuariojerarquia.getIdPadre().toUpperCase() +"%");
//			}
//			where.append(" AND t1.ID_PADRE IS NOT NULL");
//	     }	
		if (usuariojerarquia  != null  && usuariojerarquia.getGrupo() != null ) {
			where.append(" AND UPPER(t1.GRUPO) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuariojerarquia.getGrupo().toUpperCase() +"%");
			}else{
				params.add("%"+usuariojerarquia.getGrupo().toUpperCase() +"%");
			}
			where.append(" AND t1.GRUPO IS NOT NULL");
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
	 public static final int STRING_BUILDER_INIT = 4096;
	 
	 /*************
	 * JERARQUIA *
	 *************/
	
	 private RowMapper<Jerarquia<UsuarioJerarquia>> rwMapJerarquia = new RowMapper<Jerarquia<UsuarioJerarquia>>() {
			public Jerarquia<UsuarioJerarquia> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				
				UsuarioJerarquia usuarioJerarquia = new UsuarioJerarquia(
						resultSet.getString("ID"), 
						resultSet.getString("NOMBRE"), 
						resultSet.getString("APELLIDO1"), 
						resultSet.getString("APELLIDO2"), 
						resultSet.getString("EJIE"), 
						resultSet.getDate("FECHAALTA"), 
						resultSet.getDate("FECHABAJA"), 
						resultSet.getString("IDPADRE"), 
						resultSet.getString("GRUPO")
				);
	          
				Jerarquia<UsuarioJerarquia> jerarquia = new Jerarquia<UsuarioJerarquia>();
				jerarquia.setModel(usuarioJerarquia);
				jerarquia.setLevel(resultSet.getBigDecimal("LEVEL").intValue());
				jerarquia.setHasChildren(Boolean.parseBoolean(resultSet.getString("HASCHILDREN")));
				jerarquia.setParentNodes(resultSet.getString("PARENTNODES"));
				jerarquia.setTreeNodes(resultSet.getString("TREENODES"));
				jerarquia.setFilter(Boolean.parseBoolean(resultSet.getString("FILTER")));
	  		
	  			return jerarquia;
			} 
	 };
			
	/**
	 * @param usuarioJerarquia UsuarioJerarquia
	 * @param pagination Pagination
	 * @return UsuarioJerarquia
	 * @throws Exception
	 */
	public List<Jerarquia<UsuarioJerarquia>> findAllLikeJerarquia(UsuarioJerarquia usuarioJerarquia, Pagination pagination){

		//SELECT
		StringBuilder sbSQL = new StringBuilder("SELECT t1.ID ID, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.EJIE EJIE, t1.FECHA_ALTA FECHAALTA, t1.FECHA_BAJA FECHABAJA, t1.ID_PADRE IDPADRE, t1.GRUPO GRUPO ");
		
		//TABLAS
		List<String> from = new ArrayList<String>();
		from.add("USUARIO_JERARQUIA");
		
		//TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");
		
		//CONDICIONES (negocio)
		StringBuilder businessFilters = new StringBuilder();
		List<Object> businessParams = new ArrayList<Object>();
		businessFilters.append("   AND t1.EJIE = ? AND t1.EJIE = ? AND t1.EJIE = ?   ");
		businessParams.add("1");
		businessParams.add("1");
		businessParams.add("1");

		//FILTRO
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuarioJerarquia, false);
		
		//JERARQUIA
		sbSQL = pagination.getQueryJerarquia(sbSQL, mapaWhere, "ID", "ID_PADRE", "NOMBRE", from, from_alias);
//		sbSQL = pagination.getQueryJerarquia(sbSQL, mapaWhere, "ID", "ID_PADRE", "NOMBRE", from, from_alias, new StringBuilder("AND 666=666"), businessFilters, businessParams);

		//PAGINACIÓN
		if (pagination != null) {
			sbSQL = pagination.getPaginationQueryJerarquia(sbSQL);
		}

		System.out.println(sbSQL);
		
		List<?> params = (List<?>) mapaWhere.get("params");
		
		System.out.println(params);
		
		return this.jdbcTemplate.query(sbSQL.toString(), this.rwMapJerarquia, params.toArray());
	}
	/**
	 * @param usuarioJerarquia UsuarioJerarquia
	 * @param pagination Pagination
	 * @return Long
	 */
	public Long findAllLikeCountJerarquia(UsuarioJerarquia usuarioJerarquia, Pagination pagination) {

		//SELECT
		StringBuilder sbSQL = new StringBuilder("SELECT COUNT(1) ");

		//TABLAS
		List<String> from = new ArrayList<String>();
		from.add("USUARIO_JERARQUIA");
		
		//TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");
		
		//CONDICIONES (negocio)
		StringBuilder businessFilters = new StringBuilder();
		List<Object> businessParams = new ArrayList<Object>();
		businessFilters.append("AND t1.EJIE = ? AND t1.EJIE = ? AND t1.EJIE = ?");
		businessParams.add("1");
		businessParams.add("1");
		businessParams.add("1");

		//FILTRO
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuarioJerarquia, false);
		
		//JERARQUIA
		sbSQL = pagination.getQueryJerarquiaCount(sbSQL, mapaWhere, "ID", "ID_PADRE", "NOMBRE", from, from_alias);
//		sbSQL = pagination.getQueryJerarquiaCount(sbSQL, mapaWhere, "ID", "ID_PADRE", "NOMBRE", from, from_alias, new StringBuilder("AND 666=666"), businessFilters, businessParams);
	
		List<?> params = (List<?>) mapaWhere.get("params");
		
		return this.jdbcTemplate.queryForLong(sbSQL.toString(), params.toArray());
	}
	
	
	//Retorno:		page			line	pk
	public TreeMap<String, TreeMap<String, String>> findAllLikeSelected (UsuarioJerarquia usuarioJerarquia, Pagination pagination){

		//SELECT
		StringBuilder sbSQL = new StringBuilder("");
		
		//TABLAS
		List<String> from = new ArrayList<String>();
		from.add("USUARIO_JERARQUIA");
		
		//TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");

		//CONDICIONES (negocio)
		StringBuilder businessFilters = new StringBuilder();
		List<Object> businessParams = new ArrayList<Object>();
		List<String> businessNames = new ArrayList<String>();
		businessFilters.append("AND t1.EJIE = ? AND t1.EJIE = ? AND t1.EJIE = ?");
		businessParams.add("1");
		businessParams.add("1");
		businessParams.add("1");
		businessNames.add("EJIE");
		
		//FILTRO
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuarioJerarquia, false);
		
		//SELECTED
		sbSQL = pagination.getQuerySelectedGrid(sbSQL, mapaWhere, usuarioJerarquia, "ID", "ID_PADRE", from, from_alias);
//		sbSQL = pagination.getQuerySelectedGrid(sbSQL, mapaWhere, usuarioJerarquia, "ID", "ID_PADRE", from, from_alias, "t1", businessFilters, businessParams, businessNames);
		
		System.out.println(sbSQL);
		
		List<?> params = (List<?>) mapaWhere.get("params");

		System.out.println(params);
		
		return this.jdbcTemplate.query(sbSQL.toString(), pagination.selectedExtractorGrid, params.toArray());
	}
}