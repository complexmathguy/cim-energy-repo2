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
 * Projector for StringMeasurementValue as outlined for the CQRS pattern.
 * 
 * Commands are handled by StringMeasurementValueAggregate
 * 
 * @author your_name_here
 *
 */
@Component("stringMeasurementValue-entity-projector")
public class StringMeasurementValueEntityProjector {
		
	// core constructor
	public StringMeasurementValueEntityProjector(StringMeasurementValueRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a StringMeasurementValue
	 * 
     * @param	entity StringMeasurementValue
     */
    public StringMeasurementValue create( StringMeasurementValue entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a StringMeasurementValue
	 * 
     * @param	entity StringMeasurementValue
     */
    public StringMeasurementValue update( StringMeasurementValue entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a StringMeasurementValue
	 * 
     * @param	id		UUID
     */
    public StringMeasurementValue delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    StringMeasurementValue entity = repository.findById(id).get();
	    
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
     * @param	assignment 	StringProxy 
     * @return	StringMeasurementValue
     */
    public StringMeasurementValue assignValue( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning Value as " + assignment.toString() );

	    StringMeasurementValue parentEntity = repository.findById( parentId ).get();
	    assignment = StringProxyProjector.find(assignment.getStringProxyId());
	    
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
	 * @return	StringMeasurementValue
	 */
	public StringMeasurementValue unAssignValue( UUID parentId ) {
		StringMeasurementValue parentEntity = repository.findById(parentId).get();

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
     * Assign a StringMeasurement
     * 
     * @param	parentId	UUID
     * @param	assignment 	StringMeasurement 
     * @return	StringMeasurementValue
     */
    public StringMeasurementValue assignStringMeasurement( UUID parentId, StringMeasurement assignment ) {
	    LOGGER.info("assigning StringMeasurement as " + assignment.toString() );

	    StringMeasurementValue parentEntity = repository.findById( parentId ).get();
	    assignment = StringMeasurementProjector.find(assignment.getStringMeasurementId());
	    
	    // ------------------------------------------
		// assign the StringMeasurement to the parent entity
		// ------------------------------------------ 
	    parentEntity.setStringMeasurement( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the StringMeasurement
	 * 
	 * @param	parentId		UUID
	 * @return	StringMeasurementValue
	 */
	public StringMeasurementValue unAssignStringMeasurement( UUID parentId ) {
		StringMeasurementValue parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning StringMeasurement on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the StringMeasurement on the parent entithy
		// ------------------------------------------     
	    parentEntity.setStringMeasurement(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the StringMeasurementValue via an FindStringMeasurementValueQuery
     * @return 	query	FindStringMeasurementValueQuery
     */
    @SuppressWarnings("unused")
    public StringMeasurementValue find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a StringMeasurementValue - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all StringMeasurementValues
     *
     * @param	query	FindAllStringMeasurementValueQuery 
     * @return 	List<StringMeasurementValue> 
     */
    @SuppressWarnings("unused")
    public List<StringMeasurementValue> findAll( FindAllStringMeasurementValueQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all StringMeasurementValue - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final StringMeasurementValueRepository repository;
    @Autowired
	@Qualifier(value = "stringProxy-entity-projector")
	StringProxyEntityProjector StringProxyProjector;
    @Autowired
	@Qualifier(value = "stringMeasurement-entity-projector")
	StringMeasurementEntityProjector StringMeasurementProjector;

    private static final Logger LOGGER 	= Logger.getLogger(StringMeasurementValueEntityProjector.class.getName());

}
