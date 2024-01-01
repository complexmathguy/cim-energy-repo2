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
 * Projector for PFVArControllerType2Dynamics as outlined for the CQRS pattern.
 * 
 * Commands are handled by PFVArControllerType2DynamicsAggregate
 * 
 * @author your_name_here
 *
 */
@Component("pFVArControllerType2Dynamics-entity-projector")
public class PFVArControllerType2DynamicsEntityProjector {
		
	// core constructor
	public PFVArControllerType2DynamicsEntityProjector(PFVArControllerType2DynamicsRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a PFVArControllerType2Dynamics
	 * 
     * @param	entity PFVArControllerType2Dynamics
     */
    public PFVArControllerType2Dynamics create( PFVArControllerType2Dynamics entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a PFVArControllerType2Dynamics
	 * 
     * @param	entity PFVArControllerType2Dynamics
     */
    public PFVArControllerType2Dynamics update( PFVArControllerType2Dynamics entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a PFVArControllerType2Dynamics
	 * 
     * @param	id		UUID
     */
    public PFVArControllerType2Dynamics delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    PFVArControllerType2Dynamics entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a ExcitationSystemDynamics
     * 
     * @param	parentId	UUID
     * @param	assignment 	ExcitationSystemDynamics 
     * @return	PFVArControllerType2Dynamics
     */
    public PFVArControllerType2Dynamics assignExcitationSystemDynamics( UUID parentId, ExcitationSystemDynamics assignment ) {
	    LOGGER.info("assigning ExcitationSystemDynamics as " + assignment.toString() );

	    PFVArControllerType2Dynamics parentEntity = repository.findById( parentId ).get();
	    assignment = ExcitationSystemDynamicsProjector.find(assignment.getExcitationSystemDynamicsId());
	    
	    // ------------------------------------------
		// assign the ExcitationSystemDynamics to the parent entity
		// ------------------------------------------ 
	    parentEntity.setExcitationSystemDynamics( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ExcitationSystemDynamics
	 * 
	 * @param	parentId		UUID
	 * @return	PFVArControllerType2Dynamics
	 */
	public PFVArControllerType2Dynamics unAssignExcitationSystemDynamics( UUID parentId ) {
		PFVArControllerType2Dynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ExcitationSystemDynamics on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ExcitationSystemDynamics on the parent entithy
		// ------------------------------------------     
	    parentEntity.setExcitationSystemDynamics(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the PFVArControllerType2Dynamics via an FindPFVArControllerType2DynamicsQuery
     * @return 	query	FindPFVArControllerType2DynamicsQuery
     */
    @SuppressWarnings("unused")
    public PFVArControllerType2Dynamics find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a PFVArControllerType2Dynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all PFVArControllerType2Dynamicss
     *
     * @param	query	FindAllPFVArControllerType2DynamicsQuery 
     * @return 	List<PFVArControllerType2Dynamics> 
     */
    @SuppressWarnings("unused")
    public List<PFVArControllerType2Dynamics> findAll( FindAllPFVArControllerType2DynamicsQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all PFVArControllerType2Dynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final PFVArControllerType2DynamicsRepository repository;
    @Autowired
	@Qualifier(value = "excitationSystemDynamics-entity-projector")
	ExcitationSystemDynamicsEntityProjector ExcitationSystemDynamicsProjector;

    private static final Logger LOGGER 	= Logger.getLogger(PFVArControllerType2DynamicsEntityProjector.class.getName());

}
