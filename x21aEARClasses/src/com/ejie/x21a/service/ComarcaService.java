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
package com.ejie.x21a.service;


import com.ejie.x38.dto.Pagination;
import java.util.List;

import com.ejie.x21a.model.Comarca;

/**
 * ComarcaService generated by UDA, 14-ago-2012 12:59:39.
 * @author UDA
 */

public interface ComarcaService {

	/**
	 * Inserts a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
    Comarca add(Comarca comarca);

	/**
	 * Updates a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	Comarca update(Comarca comarca);

	/**
	 * Finds a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	Comarca find(Comarca comarca);

	/**
	 * Finds a List of rows in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @param pagination Pagination
	 * @return List
	 */
	List<Comarca> findAll(Comarca comarca, Pagination pagination);

	/**
	 * Counts rows in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Long
	 */
	Long findAllCount(Comarca comarca);
	
	/**
	 * Finds rows in the Comarca table using like.
	 *
	 * @param comarca Comarca
	 * @param pagination Pagination
     * @param startsWith Boolean	 
	 * @return List
	 */
	List<Comarca> findAllLike(Comarca comarca, Pagination pagination, Boolean startsWith) ;

	/**
	 * Counts rows in the Comarca table using like.
	 *
	 * @param comarca Comarca
     * @param startsWith Boolean	 
	 * @return Long
	 */
	Long findAllLikeCount(Comarca comarca, Boolean startsWith) ;
  
	/**
	 * Deletes a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return 
	 */
	void remove(Comarca comarca);
	
	/**
	 * Deletes multiple rows in the Comarca table.
	 *
	 * @param comarcaList List
	 * @return 
	 */	
	void removeMultiple(List<Comarca> comarcaList);
    
}


