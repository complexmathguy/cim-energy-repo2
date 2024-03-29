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
 * Projector for ExcitationSystemDynamics as outlined for the CQRS pattern.
 * 
 * Commands are handled by ExcitationSystemDynamicsAggregate
 * 
 * @author your_name_here
 *
 */
@Component("excitationSystemDynamics-entity-projector")
public class ExcitationSystemDynamicsEntityProjector {
		
	// core constructor
	public ExcitationSystemDynamicsEntityProjector(ExcitationSystemDynamicsRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a ExcitationSystemDynamics
	 * 
     * @param	entity ExcitationSystemDynamics
     */
    public ExcitationSystemDynamics create( ExcitationSystemDynamics entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a ExcitationSystemDynamics
	 * 
     * @param	entity ExcitationSystemDynamics
     */
    public ExcitationSystemDynamics update( ExcitationSystemDynamics entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a ExcitationSystemDynamics
	 * 
     * @param	id		UUID
     */
    public ExcitationSystemDynamics delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    ExcitationSystemDynamics entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a SynchronousMachineDynamics
     * 
     * @param	parentId	UUID
     * @param	assignment 	SynchronousMachineDynamics 
     * @return	ExcitationSystemDynamics
     */
    public ExcitationSystemDynamics assignSynchronousMachineDynamics( UUID parentId, SynchronousMachineDynamics assignment ) {
	    LOGGER.info("assigning SynchronousMachineDynamics as " + assignment.toString() );

	    ExcitationSystemDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = SynchronousMachineDynamicsProjector.find(assignment.getSynchronousMachineDynamicsId());
	    
	    // ------------------------------------------
		// assign the SynchronousMachineDynamics to the parent entity
		// ------------------------------------------ 
	    parentEntity.setSynchronousMachineDynamics( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the SynchronousMachineDynamics
	 * 
	 * @param	parentId		UUID
	 * @return	ExcitationSystemDynamics
	 */
	public ExcitationSystemDynamics unAssignSynchronousMachineDynamics( UUID parentId ) {
		ExcitationSystemDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning SynchronousMachineDynamics on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the SynchronousMachineDynamics on the parent entithy
		// ------------------------------------------     
	    parentEntity.setSynchronousMachineDynamics(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the ExcitationSystemDynamics via an FindExcitationSystemDynamicsQuery
     * @return 	query	FindExcitationSystemDynamicsQuery
     */
    @SuppressWarnings("unused")
    public ExcitationSystemDynamics find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a ExcitationSystemDynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all ExcitationSystemDynamicss
     *
     * @param	query	FindAllExcitationSystemDynamicsQuery 
     * @return 	List<ExcitationSystemDynamics> 
     */
    @SuppressWarnings("unused")
    public List<ExcitationSystemDynamics> findAll( FindAllExcitationSystemDynamicsQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all ExcitationSystemDynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final ExcitationSystemDynamicsRepository repository;
    @Autowired
	@Qualifier(value = "synchronousMachineDynamics-entity-projector")
	SynchronousMachineDynamicsEntityProjector SynchronousMachineDynamicsProjector;
    @Autowired
	@Qualifier(value = "overexcitationLimiterDynamics-entity-projector")
	OverexcitationLimiterDynamicsEntityProjector OverexcitationLimiterDynamicsProjector;

    private static final Logger LOGGER 	= Logger.getLogger(ExcitationSystemDynamicsEntityProjector.class.getName());

}
