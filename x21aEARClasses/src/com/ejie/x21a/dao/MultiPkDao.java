package com.ejie.x21a.dao;

import com.ejie.x38.dto.Pagination;
import java.util.List;

import com.ejie.x21a.model.MultiPk;

/**
 * MultiPkDao generated by UDA, 06-sep-2012 8:37:04.
 * @author UDA
 */

public interface MultiPkDao {
    
    /**
     * Inserts a single row in the MultiPk table.
     *
     * @param multipk MultiPk
     * @return MultiPk
     */
    MultiPk add(MultiPk multipk);

    /**
     * Updates a single row in the MultiPk table.
     *
     * @param multipk MultiPk
     * @return MultiPk
     */
    MultiPk update(MultiPk multipk);

    /**
     * Finds a single row in the MultiPk table.
     *
     * @param multipk MultiPk
     * @return MultiPk
     */
    MultiPk find(MultiPk multipk);

    /**
     * Deletes a single row in the MultiPk table.
     *
     * @param multipk MultiPk
     * @return 
     */
    void remove(MultiPk multipk);

    /**
     * Finds a List of rows in the MultiPk table.
     *
     * @param multipk MultiPk
     * @param pagination Pagination
     * @return List
     */
    List<MultiPk> findAll(MultiPk multipk, Pagination pagination);

    /**
     * Counts rows in the MultiPk table.
     *
     * @param multipk MultiPk
     * @return Long
     */
    Long findAllCount(MultiPk multipk);
	
	/**
     * Finds rows in the MultiPk table using like.
     *
     * @param multipk MultiPk
     * @param pagination Pagination
     * @param startsWith Boolean
     * @return List
     */
	List<MultiPk> findAllLike(MultiPk multipk, Pagination pagination, Boolean startsWith);
	
    /**
     * Counts rows in the MultiPk table using like.
     *
     * @param multipk MultiPk
     * @param startsWith Boolean
     * @return Long
     */
    Long findAllLikeCount(MultiPk multipk, Boolean startsWith);
}

