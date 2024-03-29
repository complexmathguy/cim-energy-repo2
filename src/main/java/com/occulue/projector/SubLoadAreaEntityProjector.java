/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.projector;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.repository.*;

/**
 * Projector for SubLoadArea as outlined for the CQRS pattern.
 * 
 * Commands are handled by SubLoadAreaAggregate
 * 
 * @author your_name_here
 *
 */
@Component("subLoadArea-entity-projector")
public class SubLoadAreaEntityProjector {
		
	// core constructor
	public SubLoadAreaEntityProjector(SubLoadAreaRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a SubLoadArea
	 * 
     * @param	entity SubLoadArea
     */
    public SubLoadArea create( SubLoadArea entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a SubLoadArea
	 * 
     * @param	entity SubLoadArea
     */
    public SubLoadArea update( SubLoadArea entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a SubLoadArea
	 * 
     * @param	id		UUID
     */
    public SubLoadArea delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    SubLoadArea entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a LoadArea
     * 
     * @param	parentId	UUID
     * @param	assignment 	LoadArea 
     * @return	SubLoadArea
     */
    public SubLoadArea assignLoadArea( UUID parentId, LoadArea assignment ) {
	    LOGGER.info("assigning LoadArea as " + assignment.toString() );

	    SubLoadArea parentEntity = repository.findById( parentId ).get();
	    assignment = LoadAreaProjector.find(assignment.getLoadAreaId());
	    
	    // ------------------------------------------
		// assign the LoadArea to the parent entity
		// ------------------------------------------ 
	    parentEntity.setLoadArea( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the LoadArea
	 * 
	 * @param	parentId		UUID
	 * @return	SubLoadArea
	 */
	public SubLoadArea unAssignLoadArea( UUID parentId ) {
		SubLoadArea parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning LoadArea on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the LoadArea on the parent entithy
		// ------------------------------------------     
	    parentEntity.setLoadArea(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the SubLoadArea via an FindSubLoadAreaQuery
     * @return 	query	FindSubLoadAreaQuery
     */
    @SuppressWarnings("unused")
    public SubLoadArea find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a SubLoadArea - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all SubLoadAreas
     *
     * @param	query	FindAllSubLoadAreaQuery 
     * @return 	List<SubLoadArea> 
     */
    @SuppressWarnings("unused")
    public List<SubLoadArea> findAll( FindAllSubLoadAreaQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all SubLoadArea - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final SubLoadAreaRepository repository;
    @Autowired
	@Qualifier(value = "loadGroup-entity-projector")
	LoadGroupEntityProjector LoadGroupProjector;
    @Autowired
	@Qualifier(value = "loadArea-entity-projector")
	LoadAreaEntityProjector LoadAreaProjector;

    private static final Logger LOGGER 	= Logger.getLogger(SubLoadAreaEntityProjector.class.getName());

}
