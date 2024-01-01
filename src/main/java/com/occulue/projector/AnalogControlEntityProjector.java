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
 * Projector for AnalogControl as outlined for the CQRS pattern.
 * 
 * Commands are handled by AnalogControlAggregate
 * 
 * @author your_name_here
 *
 */
@Component("analogControl-entity-projector")
public class AnalogControlEntityProjector {
		
	// core constructor
	public AnalogControlEntityProjector(AnalogControlRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a AnalogControl
	 * 
     * @param	entity AnalogControl
     */
    public AnalogControl create( AnalogControl entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a AnalogControl
	 * 
     * @param	entity AnalogControl
     */
    public AnalogControl update( AnalogControl entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a AnalogControl
	 * 
     * @param	id		UUID
     */
    public AnalogControl delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    AnalogControl entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a MaxValue
     * 
     * @param	parentId	UUID
     * @param	assignment 	Simple_Float 
     * @return	AnalogControl
     */
    public AnalogControl assignMaxValue( UUID parentId, Simple_Float assignment ) {
	    LOGGER.info("assigning MaxValue as " + assignment.toString() );

	    AnalogControl parentEntity = repository.findById( parentId ).get();
	    assignment = Simple_FloatProjector.find(assignment.getSimple_FloatId());
	    
	    // ------------------------------------------
		// assign the MaxValue to the parent entity
		// ------------------------------------------ 
	    parentEntity.setMaxValue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the MaxValue
	 * 
	 * @param	parentId		UUID
	 * @return	AnalogControl
	 */
	public AnalogControl unAssignMaxValue( UUID parentId ) {
		AnalogControl parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning MaxValue on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the MaxValue on the parent entithy
		// ------------------------------------------     
	    parentEntity.setMaxValue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a MinValue
     * 
     * @param	parentId	UUID
     * @param	assignment 	Simple_Float 
     * @return	AnalogControl
     */
    public AnalogControl assignMinValue( UUID parentId, Simple_Float assignment ) {
	    LOGGER.info("assigning MinValue as " + assignment.toString() );

	    AnalogControl parentEntity = repository.findById( parentId ).get();
	    assignment = Simple_FloatProjector.find(assignment.getSimple_FloatId());
	    
	    // ------------------------------------------
		// assign the MinValue to the parent entity
		// ------------------------------------------ 
	    parentEntity.setMinValue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the MinValue
	 * 
	 * @param	parentId		UUID
	 * @return	AnalogControl
	 */
	public AnalogControl unAssignMinValue( UUID parentId ) {
		AnalogControl parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning MinValue on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the MinValue on the parent entithy
		// ------------------------------------------     
	    parentEntity.setMinValue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a AnalogValue
     * 
     * @param	parentId	UUID
     * @param	assignment 	AnalogValue 
     * @return	AnalogControl
     */
    public AnalogControl assignAnalogValue( UUID parentId, AnalogValue assignment ) {
	    LOGGER.info("assigning AnalogValue as " + assignment.toString() );

	    AnalogControl parentEntity = repository.findById( parentId ).get();
	    assignment = AnalogValueProjector.find(assignment.getAnalogValueId());
	    
	    // ------------------------------------------
		// assign the AnalogValue to the parent entity
		// ------------------------------------------ 
	    parentEntity.setAnalogValue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the AnalogValue
	 * 
	 * @param	parentId		UUID
	 * @return	AnalogControl
	 */
	public AnalogControl unAssignAnalogValue( UUID parentId ) {
		AnalogControl parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning AnalogValue on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the AnalogValue on the parent entithy
		// ------------------------------------------     
	    parentEntity.setAnalogValue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the AnalogControl via an FindAnalogControlQuery
     * @return 	query	FindAnalogControlQuery
     */
    @SuppressWarnings("unused")
    public AnalogControl find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a AnalogControl - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all AnalogControls
     *
     * @param	query	FindAllAnalogControlQuery 
     * @return 	List<AnalogControl> 
     */
    @SuppressWarnings("unused")
    public List<AnalogControl> findAll( FindAllAnalogControlQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all AnalogControl - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final AnalogControlRepository repository;
    @Autowired
	@Qualifier(value = "simple_Float-entity-projector")
	Simple_FloatEntityProjector Simple_FloatProjector;
    @Autowired
	@Qualifier(value = "analogValue-entity-projector")
	AnalogValueEntityProjector AnalogValueProjector;

    private static final Logger LOGGER 	= Logger.getLogger(AnalogControlEntityProjector.class.getName());

}
