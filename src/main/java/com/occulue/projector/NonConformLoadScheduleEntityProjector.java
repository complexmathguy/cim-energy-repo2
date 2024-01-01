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
 * Projector for NonConformLoadSchedule as outlined for the CQRS pattern.
 * 
 * Commands are handled by NonConformLoadScheduleAggregate
 * 
 * @author your_name_here
 *
 */
@Component("nonConformLoadSchedule-entity-projector")
public class NonConformLoadScheduleEntityProjector {
		
	// core constructor
	public NonConformLoadScheduleEntityProjector(NonConformLoadScheduleRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a NonConformLoadSchedule
	 * 
     * @param	entity NonConformLoadSchedule
     */
    public NonConformLoadSchedule create( NonConformLoadSchedule entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a NonConformLoadSchedule
	 * 
     * @param	entity NonConformLoadSchedule
     */
    public NonConformLoadSchedule update( NonConformLoadSchedule entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a NonConformLoadSchedule
	 * 
     * @param	id		UUID
     */
    public NonConformLoadSchedule delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    NonConformLoadSchedule entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a NonConformLoadGroup
     * 
     * @param	parentId	UUID
     * @param	assignment 	NonConformLoadGroup 
     * @return	NonConformLoadSchedule
     */
    public NonConformLoadSchedule assignNonConformLoadGroup( UUID parentId, NonConformLoadGroup assignment ) {
	    LOGGER.info("assigning NonConformLoadGroup as " + assignment.toString() );

	    NonConformLoadSchedule parentEntity = repository.findById( parentId ).get();
	    assignment = NonConformLoadGroupProjector.find(assignment.getNonConformLoadGroupId());
	    
	    // ------------------------------------------
		// assign the NonConformLoadGroup to the parent entity
		// ------------------------------------------ 
	    parentEntity.setNonConformLoadGroup( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the NonConformLoadGroup
	 * 
	 * @param	parentId		UUID
	 * @return	NonConformLoadSchedule
	 */
	public NonConformLoadSchedule unAssignNonConformLoadGroup( UUID parentId ) {
		NonConformLoadSchedule parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning NonConformLoadGroup on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the NonConformLoadGroup on the parent entithy
		// ------------------------------------------     
	    parentEntity.setNonConformLoadGroup(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the NonConformLoadSchedule via an FindNonConformLoadScheduleQuery
     * @return 	query	FindNonConformLoadScheduleQuery
     */
    @SuppressWarnings("unused")
    public NonConformLoadSchedule find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a NonConformLoadSchedule - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all NonConformLoadSchedules
     *
     * @param	query	FindAllNonConformLoadScheduleQuery 
     * @return 	List<NonConformLoadSchedule> 
     */
    @SuppressWarnings("unused")
    public List<NonConformLoadSchedule> findAll( FindAllNonConformLoadScheduleQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all NonConformLoadSchedule - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final NonConformLoadScheduleRepository repository;
    @Autowired
	@Qualifier(value = "nonConformLoadGroup-entity-projector")
	NonConformLoadGroupEntityProjector NonConformLoadGroupProjector;

    private static final Logger LOGGER 	= Logger.getLogger(NonConformLoadScheduleEntityProjector.class.getName());

}
