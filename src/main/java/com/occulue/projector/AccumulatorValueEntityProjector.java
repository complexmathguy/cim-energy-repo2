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
 * Projector for AccumulatorValue as outlined for the CQRS pattern.
 * 
 * Commands are handled by AccumulatorValueAggregate
 * 
 * @author your_name_here
 *
 */
@Component("accumulatorValue-entity-projector")
public class AccumulatorValueEntityProjector {
		
	// core constructor
	public AccumulatorValueEntityProjector(AccumulatorValueRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a AccumulatorValue
	 * 
     * @param	entity AccumulatorValue
     */
    public AccumulatorValue create( AccumulatorValue entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a AccumulatorValue
	 * 
     * @param	entity AccumulatorValue
     */
    public AccumulatorValue update( AccumulatorValue entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a AccumulatorValue
	 * 
     * @param	id		UUID
     */
    public AccumulatorValue delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    AccumulatorValue entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Value
     * 
     * @param	parentId	UUID
     * @param	assignment 	IntegerProxy 
     * @return	AccumulatorValue
     */
    public AccumulatorValue assignValue( UUID parentId, IntegerProxy assignment ) {
	    LOGGER.info("assigning Value as " + assignment.toString() );

	    AccumulatorValue parentEntity = repository.findById( parentId ).get();
	    assignment = IntegerProxyProjector.find(assignment.getIntegerProxyId());
	    
	    // ------------------------------------------
		// assign the Value to the parent entity
		// ------------------------------------------ 
	    parentEntity.setValue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Value
	 * 
	 * @param	parentId		UUID
	 * @return	AccumulatorValue
	 */
	public AccumulatorValue unAssignValue( UUID parentId ) {
		AccumulatorValue parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Value on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Value on the parent entithy
		// ------------------------------------------     
	    parentEntity.setValue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Accumulator
     * 
     * @param	parentId	UUID
     * @param	assignment 	Accumulator 
     * @return	AccumulatorValue
     */
    public AccumulatorValue assignAccumulator( UUID parentId, Accumulator assignment ) {
	    LOGGER.info("assigning Accumulator as " + assignment.toString() );

	    AccumulatorValue parentEntity = repository.findById( parentId ).get();
	    assignment = AccumulatorProjector.find(assignment.getAccumulatorId());
	    
	    // ------------------------------------------
		// assign the Accumulator to the parent entity
		// ------------------------------------------ 
	    parentEntity.setAccumulator( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Accumulator
	 * 
	 * @param	parentId		UUID
	 * @return	AccumulatorValue
	 */
	public AccumulatorValue unAssignAccumulator( UUID parentId ) {
		AccumulatorValue parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Accumulator on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Accumulator on the parent entithy
		// ------------------------------------------     
	    parentEntity.setAccumulator(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the AccumulatorValue via an FindAccumulatorValueQuery
     * @return 	query	FindAccumulatorValueQuery
     */
    @SuppressWarnings("unused")
    public AccumulatorValue find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a AccumulatorValue - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all AccumulatorValues
     *
     * @param	query	FindAllAccumulatorValueQuery 
     * @return 	List<AccumulatorValue> 
     */
    @SuppressWarnings("unused")
    public List<AccumulatorValue> findAll( FindAllAccumulatorValueQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all AccumulatorValue - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final AccumulatorValueRepository repository;
    @Autowired
	@Qualifier(value = "accumulatorReset-entity-projector")
	AccumulatorResetEntityProjector AccumulatorResetProjector;
    @Autowired
	@Qualifier(value = "integerProxy-entity-projector")
	IntegerProxyEntityProjector IntegerProxyProjector;
    @Autowired
	@Qualifier(value = "accumulator-entity-projector")
	AccumulatorEntityProjector AccumulatorProjector;

    private static final Logger LOGGER 	= Logger.getLogger(AccumulatorValueEntityProjector.class.getName());

}
