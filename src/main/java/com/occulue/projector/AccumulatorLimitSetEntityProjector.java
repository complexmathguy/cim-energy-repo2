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
 * Projector for AccumulatorLimitSet as outlined for the CQRS pattern.
 * 
 * Commands are handled by AccumulatorLimitSetAggregate
 * 
 * @author your_name_here
 *
 */
@Component("accumulatorLimitSet-entity-projector")
public class AccumulatorLimitSetEntityProjector {
		
	// core constructor
	public AccumulatorLimitSetEntityProjector(AccumulatorLimitSetRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a AccumulatorLimitSet
	 * 
     * @param	entity AccumulatorLimitSet
     */
    public AccumulatorLimitSet create( AccumulatorLimitSet entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a AccumulatorLimitSet
	 * 
     * @param	entity AccumulatorLimitSet
     */
    public AccumulatorLimitSet update( AccumulatorLimitSet entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a AccumulatorLimitSet
	 * 
     * @param	id		UUID
     */
    public AccumulatorLimitSet delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    AccumulatorLimitSet entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Measurements
     * 
     * @param	parentId	UUID
     * @param	assignment 	Accumulator 
     * @return	AccumulatorLimitSet
     */
    public AccumulatorLimitSet assignMeasurements( UUID parentId, Accumulator assignment ) {
	    LOGGER.info("assigning Measurements as " + assignment.toString() );

	    AccumulatorLimitSet parentEntity = repository.findById( parentId ).get();
	    assignment = AccumulatorProjector.find(assignment.getAccumulatorId());
	    
	    // ------------------------------------------
		// assign the Measurements to the parent entity
		// ------------------------------------------ 
	    parentEntity.setMeasurements( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Measurements
	 * 
	 * @param	parentId		UUID
	 * @return	AccumulatorLimitSet
	 */
	public AccumulatorLimitSet unAssignMeasurements( UUID parentId ) {
		AccumulatorLimitSet parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Measurements on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Measurements on the parent entithy
		// ------------------------------------------     
	    parentEntity.setMeasurements(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the AccumulatorLimitSet via an FindAccumulatorLimitSetQuery
     * @return 	query	FindAccumulatorLimitSetQuery
     */
    @SuppressWarnings("unused")
    public AccumulatorLimitSet find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a AccumulatorLimitSet - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all AccumulatorLimitSets
     *
     * @param	query	FindAllAccumulatorLimitSetQuery 
     * @return 	List<AccumulatorLimitSet> 
     */
    @SuppressWarnings("unused")
    public List<AccumulatorLimitSet> findAll( FindAllAccumulatorLimitSetQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all AccumulatorLimitSet - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final AccumulatorLimitSetRepository repository;
    @Autowired
	@Qualifier(value = "accumulatorLimit-entity-projector")
	AccumulatorLimitEntityProjector AccumulatorLimitProjector;
    @Autowired
	@Qualifier(value = "accumulator-entity-projector")
	AccumulatorEntityProjector AccumulatorProjector;

    private static final Logger LOGGER 	= Logger.getLogger(AccumulatorLimitSetEntityProjector.class.getName());

}
