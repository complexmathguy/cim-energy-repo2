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
 * Projector for ConformLoadSchedule as outlined for the CQRS pattern.
 * 
 * Commands are handled by ConformLoadScheduleAggregate
 * 
 * @author your_name_here
 *
 */
@Component("conformLoadSchedule-entity-projector")
public class ConformLoadScheduleEntityProjector {
		
	// core constructor
	public ConformLoadScheduleEntityProjector(ConformLoadScheduleRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a ConformLoadSchedule
	 * 
     * @param	entity ConformLoadSchedule
     */
    public ConformLoadSchedule create( ConformLoadSchedule entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a ConformLoadSchedule
	 * 
     * @param	entity ConformLoadSchedule
     */
    public ConformLoadSchedule update( ConformLoadSchedule entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a ConformLoadSchedule
	 * 
     * @param	id		UUID
     */
    public ConformLoadSchedule delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    ConformLoadSchedule entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a ConformLoadGroup
     * 
     * @param	parentId	UUID
     * @param	assignment 	ConformLoadGroup 
     * @return	ConformLoadSchedule
     */
    public ConformLoadSchedule assignConformLoadGroup( UUID parentId, ConformLoadGroup assignment ) {
	    LOGGER.info("assigning ConformLoadGroup as " + assignment.toString() );

	    ConformLoadSchedule parentEntity = repository.findById( parentId ).get();
	    assignment = ConformLoadGroupProjector.find(assignment.getConformLoadGroupId());
	    
	    // ------------------------------------------
		// assign the ConformLoadGroup to the parent entity
		// ------------------------------------------ 
	    parentEntity.setConformLoadGroup( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ConformLoadGroup
	 * 
	 * @param	parentId		UUID
	 * @return	ConformLoadSchedule
	 */
	public ConformLoadSchedule unAssignConformLoadGroup( UUID parentId ) {
		ConformLoadSchedule parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ConformLoadGroup on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ConformLoadGroup on the parent entithy
		// ------------------------------------------     
	    parentEntity.setConformLoadGroup(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the ConformLoadSchedule via an FindConformLoadScheduleQuery
     * @return 	query	FindConformLoadScheduleQuery
     */
    @SuppressWarnings("unused")
    public ConformLoadSchedule find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a ConformLoadSchedule - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all ConformLoadSchedules
     *
     * @param	query	FindAllConformLoadScheduleQuery 
     * @return 	List<ConformLoadSchedule> 
     */
    @SuppressWarnings("unused")
    public List<ConformLoadSchedule> findAll( FindAllConformLoadScheduleQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all ConformLoadSchedule - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final ConformLoadScheduleRepository repository;
    @Autowired
	@Qualifier(value = "conformLoadGroup-entity-projector")
	ConformLoadGroupEntityProjector ConformLoadGroupProjector;

    private static final Logger LOGGER 	= Logger.getLogger(ConformLoadScheduleEntityProjector.class.getName());

}
