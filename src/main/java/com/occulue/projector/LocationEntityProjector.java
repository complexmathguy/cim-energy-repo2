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
 * Projector for Location as outlined for the CQRS pattern.
 * 
 * Commands are handled by LocationAggregate
 * 
 * @author your_name_here
 *
 */
@Component("location-entity-projector")
public class LocationEntityProjector {
		
	// core constructor
	public LocationEntityProjector(LocationRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Location
	 * 
     * @param	entity Location
     */
    public Location create( Location entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Location
	 * 
     * @param	entity Location
     */
    public Location update( Location entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Location
	 * 
     * @param	id		UUID
     */
    public Location delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Location entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a PowerSystemResources
     * 
     * @param	parentId	UUID
     * @param	assignment 	PowerSystemResource 
     * @return	Location
     */
    public Location assignPowerSystemResources( UUID parentId, PowerSystemResource assignment ) {
	    LOGGER.info("assigning PowerSystemResources as " + assignment.toString() );

	    Location parentEntity = repository.findById( parentId ).get();
	    assignment = PowerSystemResourceProjector.find(assignment.getPowerSystemResourceId());
	    
	    // ------------------------------------------
		// assign the PowerSystemResources to the parent entity
		// ------------------------------------------ 
	    parentEntity.setPowerSystemResources( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the PowerSystemResources
	 * 
	 * @param	parentId		UUID
	 * @return	Location
	 */
	public Location unAssignPowerSystemResources( UUID parentId ) {
		Location parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning PowerSystemResources on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the PowerSystemResources on the parent entithy
		// ------------------------------------------     
	    parentEntity.setPowerSystemResources(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a CoordinateSystem
     * 
     * @param	parentId	UUID
     * @param	assignment 	CoordinateSystem 
     * @return	Location
     */
    public Location assignCoordinateSystem( UUID parentId, CoordinateSystem assignment ) {
	    LOGGER.info("assigning CoordinateSystem as " + assignment.toString() );

	    Location parentEntity = repository.findById( parentId ).get();
	    assignment = CoordinateSystemProjector.find(assignment.getCoordinateSystemId());
	    
	    // ------------------------------------------
		// assign the CoordinateSystem to the parent entity
		// ------------------------------------------ 
	    parentEntity.setCoordinateSystem( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the CoordinateSystem
	 * 
	 * @param	parentId		UUID
	 * @return	Location
	 */
	public Location unAssignCoordinateSystem( UUID parentId ) {
		Location parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning CoordinateSystem on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the CoordinateSystem on the parent entithy
		// ------------------------------------------     
	    parentEntity.setCoordinateSystem(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the Location via an FindLocationQuery
     * @return 	query	FindLocationQuery
     */
    @SuppressWarnings("unused")
    public Location find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Location - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Locations
     *
     * @param	query	FindAllLocationQuery 
     * @return 	List<Location> 
     */
    @SuppressWarnings("unused")
    public List<Location> findAll( FindAllLocationQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Location - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final LocationRepository repository;
    @Autowired
	@Qualifier(value = "powerSystemResource-entity-projector")
	PowerSystemResourceEntityProjector PowerSystemResourceProjector;
    @Autowired
	@Qualifier(value = "coordinateSystem-entity-projector")
	CoordinateSystemEntityProjector CoordinateSystemProjector;
    @Autowired
	@Qualifier(value = "positionPoint-entity-projector")
	PositionPointEntityProjector PositionPointProjector;

    private static final Logger LOGGER 	= Logger.getLogger(LocationEntityProjector.class.getName());

}
