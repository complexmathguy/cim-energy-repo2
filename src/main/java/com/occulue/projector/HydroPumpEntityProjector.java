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
 * Projector for HydroPump as outlined for the CQRS pattern.
 * 
 * Commands are handled by HydroPumpAggregate
 * 
 * @author your_name_here
 *
 */
@Component("hydroPump-entity-projector")
public class HydroPumpEntityProjector {
		
	// core constructor
	public HydroPumpEntityProjector(HydroPumpRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a HydroPump
	 * 
     * @param	entity HydroPump
     */
    public HydroPump create( HydroPump entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a HydroPump
	 * 
     * @param	entity HydroPump
     */
    public HydroPump update( HydroPump entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a HydroPump
	 * 
     * @param	id		UUID
     */
    public HydroPump delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    HydroPump entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a RotatingMachine
     * 
     * @param	parentId	UUID
     * @param	assignment 	RotatingMachine 
     * @return	HydroPump
     */
    public HydroPump assignRotatingMachine( UUID parentId, RotatingMachine assignment ) {
	    LOGGER.info("assigning RotatingMachine as " + assignment.toString() );

	    HydroPump parentEntity = repository.findById( parentId ).get();
	    assignment = RotatingMachineProjector.find(assignment.getRotatingMachineId());
	    
	    // ------------------------------------------
		// assign the RotatingMachine to the parent entity
		// ------------------------------------------ 
	    parentEntity.setRotatingMachine( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the RotatingMachine
	 * 
	 * @param	parentId		UUID
	 * @return	HydroPump
	 */
	public HydroPump unAssignRotatingMachine( UUID parentId ) {
		HydroPump parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning RotatingMachine on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the RotatingMachine on the parent entithy
		// ------------------------------------------     
	    parentEntity.setRotatingMachine(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a HydroPowerPlant
     * 
     * @param	parentId	UUID
     * @param	assignment 	HydroPowerPlant 
     * @return	HydroPump
     */
    public HydroPump assignHydroPowerPlant( UUID parentId, HydroPowerPlant assignment ) {
	    LOGGER.info("assigning HydroPowerPlant as " + assignment.toString() );

	    HydroPump parentEntity = repository.findById( parentId ).get();
	    assignment = HydroPowerPlantProjector.find(assignment.getHydroPowerPlantId());
	    
	    // ------------------------------------------
		// assign the HydroPowerPlant to the parent entity
		// ------------------------------------------ 
	    parentEntity.setHydroPowerPlant( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the HydroPowerPlant
	 * 
	 * @param	parentId		UUID
	 * @return	HydroPump
	 */
	public HydroPump unAssignHydroPowerPlant( UUID parentId ) {
		HydroPump parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning HydroPowerPlant on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the HydroPowerPlant on the parent entithy
		// ------------------------------------------     
	    parentEntity.setHydroPowerPlant(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the HydroPump via an FindHydroPumpQuery
     * @return 	query	FindHydroPumpQuery
     */
    @SuppressWarnings("unused")
    public HydroPump find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a HydroPump - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all HydroPumps
     *
     * @param	query	FindAllHydroPumpQuery 
     * @return 	List<HydroPump> 
     */
    @SuppressWarnings("unused")
    public List<HydroPump> findAll( FindAllHydroPumpQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all HydroPump - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final HydroPumpRepository repository;
    @Autowired
	@Qualifier(value = "rotatingMachine-entity-projector")
	RotatingMachineEntityProjector RotatingMachineProjector;
    @Autowired
	@Qualifier(value = "hydroPowerPlant-entity-projector")
	HydroPowerPlantEntityProjector HydroPowerPlantProjector;

    private static final Logger LOGGER 	= Logger.getLogger(HydroPumpEntityProjector.class.getName());

}
