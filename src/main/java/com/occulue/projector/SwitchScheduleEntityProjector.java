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
 * Projector for SwitchSchedule as outlined for the CQRS pattern.
 * 
 * Commands are handled by SwitchScheduleAggregate
 * 
 * @author your_name_here
 *
 */
@Component("switchSchedule-entity-projector")
public class SwitchScheduleEntityProjector {
		
	// core constructor
	public SwitchScheduleEntityProjector(SwitchScheduleRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a SwitchSchedule
	 * 
     * @param	entity SwitchSchedule
     */
    public SwitchSchedule create( SwitchSchedule entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a SwitchSchedule
	 * 
     * @param	entity SwitchSchedule
     */
    public SwitchSchedule update( SwitchSchedule entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a SwitchSchedule
	 * 
     * @param	id		UUID
     */
    public SwitchSchedule delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    SwitchSchedule entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a SwitchIt
     * 
     * @param	parentId	UUID
     * @param	assignment 	SwitchProxy 
     * @return	SwitchSchedule
     */
    public SwitchSchedule assignSwitchIt( UUID parentId, SwitchProxy assignment ) {
	    LOGGER.info("assigning SwitchIt as " + assignment.toString() );

	    SwitchSchedule parentEntity = repository.findById( parentId ).get();
	    assignment = SwitchProxyProjector.find(assignment.getSwitchProxyId());
	    
	    // ------------------------------------------
		// assign the SwitchIt to the parent entity
		// ------------------------------------------ 
	    parentEntity.setSwitchIt( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the SwitchIt
	 * 
	 * @param	parentId		UUID
	 * @return	SwitchSchedule
	 */
	public SwitchSchedule unAssignSwitchIt( UUID parentId ) {
		SwitchSchedule parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning SwitchIt on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the SwitchIt on the parent entithy
		// ------------------------------------------     
	    parentEntity.setSwitchIt(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the SwitchSchedule via an FindSwitchScheduleQuery
     * @return 	query	FindSwitchScheduleQuery
     */
    @SuppressWarnings("unused")
    public SwitchSchedule find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a SwitchSchedule - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all SwitchSchedules
     *
     * @param	query	FindAllSwitchScheduleQuery 
     * @return 	List<SwitchSchedule> 
     */
    @SuppressWarnings("unused")
    public List<SwitchSchedule> findAll( FindAllSwitchScheduleQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all SwitchSchedule - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final SwitchScheduleRepository repository;
    @Autowired
	@Qualifier(value = "switchProxy-entity-projector")
	SwitchProxyEntityProjector SwitchProxyProjector;

    private static final Logger LOGGER 	= Logger.getLogger(SwitchScheduleEntityProjector.class.getName());

}
