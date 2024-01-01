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
 * Projector for WindPlantIEC as outlined for the CQRS pattern.
 * 
 * Commands are handled by WindPlantIECAggregate
 * 
 * @author your_name_here
 *
 */
@Component("windPlantIEC-entity-projector")
public class WindPlantIECEntityProjector {
		
	// core constructor
	public WindPlantIECEntityProjector(WindPlantIECRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a WindPlantIEC
	 * 
     * @param	entity WindPlantIEC
     */
    public WindPlantIEC create( WindPlantIEC entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a WindPlantIEC
	 * 
     * @param	entity WindPlantIEC
     */
    public WindPlantIEC update( WindPlantIEC entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a WindPlantIEC
	 * 
     * @param	id		UUID
     */
    public WindPlantIEC delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    WindPlantIEC entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a WindPlantFreqPcontrolIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindPlantFreqPcontrolIEC 
     * @return	WindPlantIEC
     */
    public WindPlantIEC assignWindPlantFreqPcontrolIEC( UUID parentId, WindPlantFreqPcontrolIEC assignment ) {
	    LOGGER.info("assigning WindPlantFreqPcontrolIEC as " + assignment.toString() );

	    WindPlantIEC parentEntity = repository.findById( parentId ).get();
	    assignment = WindPlantFreqPcontrolIECProjector.find(assignment.getWindPlantFreqPcontrolIECId());
	    
	    // ------------------------------------------
		// assign the WindPlantFreqPcontrolIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindPlantFreqPcontrolIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindPlantFreqPcontrolIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindPlantIEC
	 */
	public WindPlantIEC unAssignWindPlantFreqPcontrolIEC( UUID parentId ) {
		WindPlantIEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindPlantFreqPcontrolIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindPlantFreqPcontrolIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindPlantFreqPcontrolIEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindPlantReactiveControlIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindPlantReactiveControlIEC 
     * @return	WindPlantIEC
     */
    public WindPlantIEC assignWindPlantReactiveControlIEC( UUID parentId, WindPlantReactiveControlIEC assignment ) {
	    LOGGER.info("assigning WindPlantReactiveControlIEC as " + assignment.toString() );

	    WindPlantIEC parentEntity = repository.findById( parentId ).get();
	    assignment = WindPlantReactiveControlIECProjector.find(assignment.getWindPlantReactiveControlIECId());
	    
	    // ------------------------------------------
		// assign the WindPlantReactiveControlIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindPlantReactiveControlIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindPlantReactiveControlIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindPlantIEC
	 */
	public WindPlantIEC unAssignWindPlantReactiveControlIEC( UUID parentId ) {
		WindPlantIEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindPlantReactiveControlIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindPlantReactiveControlIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindPlantReactiveControlIEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the WindPlantIEC via an FindWindPlantIECQuery
     * @return 	query	FindWindPlantIECQuery
     */
    @SuppressWarnings("unused")
    public WindPlantIEC find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a WindPlantIEC - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all WindPlantIECs
     *
     * @param	query	FindAllWindPlantIECQuery 
     * @return 	List<WindPlantIEC> 
     */
    @SuppressWarnings("unused")
    public List<WindPlantIEC> findAll( FindAllWindPlantIECQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all WindPlantIEC - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final WindPlantIECRepository repository;
    @Autowired
	@Qualifier(value = "windPlantFreqPcontrolIEC-entity-projector")
	WindPlantFreqPcontrolIECEntityProjector WindPlantFreqPcontrolIECProjector;
    @Autowired
	@Qualifier(value = "windPlantReactiveControlIEC-entity-projector")
	WindPlantReactiveControlIECEntityProjector WindPlantReactiveControlIECProjector;

    private static final Logger LOGGER 	= Logger.getLogger(WindPlantIECEntityProjector.class.getName());

}
