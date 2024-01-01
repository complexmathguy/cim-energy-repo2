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
 * Projector for VoltageCompensatorDynamics as outlined for the CQRS pattern.
 * 
 * Commands are handled by VoltageCompensatorDynamicsAggregate
 * 
 * @author your_name_here
 *
 */
@Component("voltageCompensatorDynamics-entity-projector")
public class VoltageCompensatorDynamicsEntityProjector {
		
	// core constructor
	public VoltageCompensatorDynamicsEntityProjector(VoltageCompensatorDynamicsRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a VoltageCompensatorDynamics
	 * 
     * @param	entity VoltageCompensatorDynamics
     */
    public VoltageCompensatorDynamics create( VoltageCompensatorDynamics entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a VoltageCompensatorDynamics
	 * 
     * @param	entity VoltageCompensatorDynamics
     */
    public VoltageCompensatorDynamics update( VoltageCompensatorDynamics entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a VoltageCompensatorDynamics
	 * 
     * @param	id		UUID
     */
    public VoltageCompensatorDynamics delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    VoltageCompensatorDynamics entity = repository.findById(id).get();
	    
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
     * @return	VoltageCompensatorDynamics
     */
    public VoltageCompensatorDynamics assignExcitationSystemDynamics( UUID parentId, ExcitationSystemDynamics assignment ) {
	    LOGGER.info("assigning ExcitationSystemDynamics as " + assignment.toString() );

	    VoltageCompensatorDynamics parentEntity = repository.findById( parentId ).get();
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
	 * @return	VoltageCompensatorDynamics
	 */
	public VoltageCompensatorDynamics unAssignExcitationSystemDynamics( UUID parentId ) {
		VoltageCompensatorDynamics parentEntity = repository.findById(parentId).get();

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
     * Method to retrieve the VoltageCompensatorDynamics via an FindVoltageCompensatorDynamicsQuery
     * @return 	query	FindVoltageCompensatorDynamicsQuery
     */
    @SuppressWarnings("unused")
    public VoltageCompensatorDynamics find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a VoltageCompensatorDynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all VoltageCompensatorDynamicss
     *
     * @param	query	FindAllVoltageCompensatorDynamicsQuery 
     * @return 	List<VoltageCompensatorDynamics> 
     */
    @SuppressWarnings("unused")
    public List<VoltageCompensatorDynamics> findAll( FindAllVoltageCompensatorDynamicsQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all VoltageCompensatorDynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final VoltageCompensatorDynamicsRepository repository;
    @Autowired
	@Qualifier(value = "remoteInputSignal-entity-projector")
	RemoteInputSignalEntityProjector RemoteInputSignalProjector;
    @Autowired
	@Qualifier(value = "excitationSystemDynamics-entity-projector")
	ExcitationSystemDynamicsEntityProjector ExcitationSystemDynamicsProjector;

    private static final Logger LOGGER 	= Logger.getLogger(VoltageCompensatorDynamicsEntityProjector.class.getName());

}
