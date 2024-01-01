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
 * Projector for WindTurbineType3or4Dynamics as outlined for the CQRS pattern.
 * 
 * Commands are handled by WindTurbineType3or4DynamicsAggregate
 * 
 * @author your_name_here
 *
 */
@Component("windTurbineType3or4Dynamics-entity-projector")
public class WindTurbineType3or4DynamicsEntityProjector {
		
	// core constructor
	public WindTurbineType3or4DynamicsEntityProjector(WindTurbineType3or4DynamicsRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a WindTurbineType3or4Dynamics
	 * 
     * @param	entity WindTurbineType3or4Dynamics
     */
    public WindTurbineType3or4Dynamics create( WindTurbineType3or4Dynamics entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a WindTurbineType3or4Dynamics
	 * 
     * @param	entity WindTurbineType3or4Dynamics
     */
    public WindTurbineType3or4Dynamics update( WindTurbineType3or4Dynamics entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a WindTurbineType3or4Dynamics
	 * 
     * @param	id		UUID
     */
    public WindTurbineType3or4Dynamics delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    WindTurbineType3or4Dynamics entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a EnergySource
     * 
     * @param	parentId	UUID
     * @param	assignment 	EnergySource 
     * @return	WindTurbineType3or4Dynamics
     */
    public WindTurbineType3or4Dynamics assignEnergySource( UUID parentId, EnergySource assignment ) {
	    LOGGER.info("assigning EnergySource as " + assignment.toString() );

	    WindTurbineType3or4Dynamics parentEntity = repository.findById( parentId ).get();
	    assignment = EnergySourceProjector.find(assignment.getEnergySourceId());
	    
	    // ------------------------------------------
		// assign the EnergySource to the parent entity
		// ------------------------------------------ 
	    parentEntity.setEnergySource( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the EnergySource
	 * 
	 * @param	parentId		UUID
	 * @return	WindTurbineType3or4Dynamics
	 */
	public WindTurbineType3or4Dynamics unAssignEnergySource( UUID parentId ) {
		WindTurbineType3or4Dynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning EnergySource on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the EnergySource on the parent entithy
		// ------------------------------------------     
	    parentEntity.setEnergySource(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindPlantDynamics
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindPlantDynamics 
     * @return	WindTurbineType3or4Dynamics
     */
    public WindTurbineType3or4Dynamics assignWindPlantDynamics( UUID parentId, WindPlantDynamics assignment ) {
	    LOGGER.info("assigning WindPlantDynamics as " + assignment.toString() );

	    WindTurbineType3or4Dynamics parentEntity = repository.findById( parentId ).get();
	    assignment = WindPlantDynamicsProjector.find(assignment.getWindPlantDynamicsId());
	    
	    // ------------------------------------------
		// assign the WindPlantDynamics to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindPlantDynamics( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindPlantDynamics
	 * 
	 * @param	parentId		UUID
	 * @return	WindTurbineType3or4Dynamics
	 */
	public WindTurbineType3or4Dynamics unAssignWindPlantDynamics( UUID parentId ) {
		WindTurbineType3or4Dynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindPlantDynamics on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindPlantDynamics on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindPlantDynamics(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a RemoteInputSignal
     * 
     * @param	parentId	UUID
     * @param	assignment 	RemoteInputSignal 
     * @return	WindTurbineType3or4Dynamics
     */
    public WindTurbineType3or4Dynamics assignRemoteInputSignal( UUID parentId, RemoteInputSignal assignment ) {
	    LOGGER.info("assigning RemoteInputSignal as " + assignment.toString() );

	    WindTurbineType3or4Dynamics parentEntity = repository.findById( parentId ).get();
	    assignment = RemoteInputSignalProjector.find(assignment.getRemoteInputSignalId());
	    
	    // ------------------------------------------
		// assign the RemoteInputSignal to the parent entity
		// ------------------------------------------ 
	    parentEntity.setRemoteInputSignal( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the RemoteInputSignal
	 * 
	 * @param	parentId		UUID
	 * @return	WindTurbineType3or4Dynamics
	 */
	public WindTurbineType3or4Dynamics unAssignRemoteInputSignal( UUID parentId ) {
		WindTurbineType3or4Dynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning RemoteInputSignal on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the RemoteInputSignal on the parent entithy
		// ------------------------------------------     
	    parentEntity.setRemoteInputSignal(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the WindTurbineType3or4Dynamics via an FindWindTurbineType3or4DynamicsQuery
     * @return 	query	FindWindTurbineType3or4DynamicsQuery
     */
    @SuppressWarnings("unused")
    public WindTurbineType3or4Dynamics find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a WindTurbineType3or4Dynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all WindTurbineType3or4Dynamicss
     *
     * @param	query	FindAllWindTurbineType3or4DynamicsQuery 
     * @return 	List<WindTurbineType3or4Dynamics> 
     */
    @SuppressWarnings("unused")
    public List<WindTurbineType3or4Dynamics> findAll( FindAllWindTurbineType3or4DynamicsQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all WindTurbineType3or4Dynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final WindTurbineType3or4DynamicsRepository repository;
    @Autowired
	@Qualifier(value = "energySource-entity-projector")
	EnergySourceEntityProjector EnergySourceProjector;
    @Autowired
	@Qualifier(value = "windPlantDynamics-entity-projector")
	WindPlantDynamicsEntityProjector WindPlantDynamicsProjector;
    @Autowired
	@Qualifier(value = "remoteInputSignal-entity-projector")
	RemoteInputSignalEntityProjector RemoteInputSignalProjector;

    private static final Logger LOGGER 	= Logger.getLogger(WindTurbineType3or4DynamicsEntityProjector.class.getName());

}
