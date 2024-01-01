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
 * Projector for SynchronousMachineDynamics as outlined for the CQRS pattern.
 * 
 * Commands are handled by SynchronousMachineDynamicsAggregate
 * 
 * @author your_name_here
 *
 */
@Component("synchronousMachineDynamics-entity-projector")
public class SynchronousMachineDynamicsEntityProjector {
		
	// core constructor
	public SynchronousMachineDynamicsEntityProjector(SynchronousMachineDynamicsRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a SynchronousMachineDynamics
	 * 
     * @param	entity SynchronousMachineDynamics
     */
    public SynchronousMachineDynamics create( SynchronousMachineDynamics entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a SynchronousMachineDynamics
	 * 
     * @param	entity SynchronousMachineDynamics
     */
    public SynchronousMachineDynamics update( SynchronousMachineDynamics entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a SynchronousMachineDynamics
	 * 
     * @param	id		UUID
     */
    public SynchronousMachineDynamics delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    SynchronousMachineDynamics entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a SynchronousMachine
     * 
     * @param	parentId	UUID
     * @param	assignment 	SynchronousMachine 
     * @return	SynchronousMachineDynamics
     */
    public SynchronousMachineDynamics assignSynchronousMachine( UUID parentId, SynchronousMachine assignment ) {
	    LOGGER.info("assigning SynchronousMachine as " + assignment.toString() );

	    SynchronousMachineDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = SynchronousMachineProjector.find(assignment.getSynchronousMachineId());
	    
	    // ------------------------------------------
		// assign the SynchronousMachine to the parent entity
		// ------------------------------------------ 
	    parentEntity.setSynchronousMachine( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the SynchronousMachine
	 * 
	 * @param	parentId		UUID
	 * @return	SynchronousMachineDynamics
	 */
	public SynchronousMachineDynamics unAssignSynchronousMachine( UUID parentId ) {
		SynchronousMachineDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning SynchronousMachine on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the SynchronousMachine on the parent entithy
		// ------------------------------------------     
	    parentEntity.setSynchronousMachine(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the SynchronousMachineDynamics via an FindSynchronousMachineDynamicsQuery
     * @return 	query	FindSynchronousMachineDynamicsQuery
     */
    @SuppressWarnings("unused")
    public SynchronousMachineDynamics find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a SynchronousMachineDynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all SynchronousMachineDynamicss
     *
     * @param	query	FindAllSynchronousMachineDynamicsQuery 
     * @return 	List<SynchronousMachineDynamics> 
     */
    @SuppressWarnings("unused")
    public List<SynchronousMachineDynamics> findAll( FindAllSynchronousMachineDynamicsQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all SynchronousMachineDynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final SynchronousMachineDynamicsRepository repository;
    @Autowired
	@Qualifier(value = "synchronousMachine-entity-projector")
	SynchronousMachineEntityProjector SynchronousMachineProjector;
    @Autowired
	@Qualifier(value = "turbineGovernorDynamics-entity-projector")
	TurbineGovernorDynamicsEntityProjector TurbineGovernorDynamicsProjector;

    private static final Logger LOGGER 	= Logger.getLogger(SynchronousMachineDynamicsEntityProjector.class.getName());

}
