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
 * Projector for TurbineGovernorDynamics as outlined for the CQRS pattern.
 * 
 * Commands are handled by TurbineGovernorDynamicsAggregate
 * 
 * @author your_name_here
 *
 */
@Component("turbineGovernorDynamics-entity-projector")
public class TurbineGovernorDynamicsEntityProjector {
		
	// core constructor
	public TurbineGovernorDynamicsEntityProjector(TurbineGovernorDynamicsRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a TurbineGovernorDynamics
	 * 
     * @param	entity TurbineGovernorDynamics
     */
    public TurbineGovernorDynamics create( TurbineGovernorDynamics entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a TurbineGovernorDynamics
	 * 
     * @param	entity TurbineGovernorDynamics
     */
    public TurbineGovernorDynamics update( TurbineGovernorDynamics entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a TurbineGovernorDynamics
	 * 
     * @param	id		UUID
     */
    public TurbineGovernorDynamics delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    TurbineGovernorDynamics entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a AsynchronousMachineDynamics
     * 
     * @param	parentId	UUID
     * @param	assignment 	AsynchronousMachineDynamics 
     * @return	TurbineGovernorDynamics
     */
    public TurbineGovernorDynamics assignAsynchronousMachineDynamics( UUID parentId, AsynchronousMachineDynamics assignment ) {
	    LOGGER.info("assigning AsynchronousMachineDynamics as " + assignment.toString() );

	    TurbineGovernorDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = AsynchronousMachineDynamicsProjector.find(assignment.getAsynchronousMachineDynamicsId());
	    
	    // ------------------------------------------
		// assign the AsynchronousMachineDynamics to the parent entity
		// ------------------------------------------ 
	    parentEntity.setAsynchronousMachineDynamics( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the AsynchronousMachineDynamics
	 * 
	 * @param	parentId		UUID
	 * @return	TurbineGovernorDynamics
	 */
	public TurbineGovernorDynamics unAssignAsynchronousMachineDynamics( UUID parentId ) {
		TurbineGovernorDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning AsynchronousMachineDynamics on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the AsynchronousMachineDynamics on the parent entithy
		// ------------------------------------------     
	    parentEntity.setAsynchronousMachineDynamics(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a SynchronousMachineDynamics
     * 
     * @param	parentId	UUID
     * @param	assignment 	SynchronousMachineDynamics 
     * @return	TurbineGovernorDynamics
     */
    public TurbineGovernorDynamics assignSynchronousMachineDynamics( UUID parentId, SynchronousMachineDynamics assignment ) {
	    LOGGER.info("assigning SynchronousMachineDynamics as " + assignment.toString() );

	    TurbineGovernorDynamics parentEntity = repository.findById( parentId ).get();
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
	 * @return	TurbineGovernorDynamics
	 */
	public TurbineGovernorDynamics unAssignSynchronousMachineDynamics( UUID parentId ) {
		TurbineGovernorDynamics parentEntity = repository.findById(parentId).get();

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
     * Method to retrieve the TurbineGovernorDynamics via an FindTurbineGovernorDynamicsQuery
     * @return 	query	FindTurbineGovernorDynamicsQuery
     */
    @SuppressWarnings("unused")
    public TurbineGovernorDynamics find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a TurbineGovernorDynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all TurbineGovernorDynamicss
     *
     * @param	query	FindAllTurbineGovernorDynamicsQuery 
     * @return 	List<TurbineGovernorDynamics> 
     */
    @SuppressWarnings("unused")
    public List<TurbineGovernorDynamics> findAll( FindAllTurbineGovernorDynamicsQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all TurbineGovernorDynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final TurbineGovernorDynamicsRepository repository;
    @Autowired
	@Qualifier(value = "asynchronousMachineDynamics-entity-projector")
	AsynchronousMachineDynamicsEntityProjector AsynchronousMachineDynamicsProjector;
    @Autowired
	@Qualifier(value = "synchronousMachineDynamics-entity-projector")
	SynchronousMachineDynamicsEntityProjector SynchronousMachineDynamicsProjector;
    @Autowired
	@Qualifier(value = "turbineLoadControllerDynamics-entity-projector")
	TurbineLoadControllerDynamicsEntityProjector TurbineLoadControllerDynamicsProjector;

    private static final Logger LOGGER 	= Logger.getLogger(TurbineGovernorDynamicsEntityProjector.class.getName());

}
