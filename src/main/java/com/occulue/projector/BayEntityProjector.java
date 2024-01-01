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
 * Projector for Bay as outlined for the CQRS pattern.
 * 
 * Commands are handled by BayAggregate
 * 
 * @author your_name_here
 *
 */
@Component("bay-entity-projector")
public class BayEntityProjector {
		
	// core constructor
	public BayEntityProjector(BayRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Bay
	 * 
     * @param	entity Bay
     */
    public Bay create( Bay entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Bay
	 * 
     * @param	entity Bay
     */
    public Bay update( Bay entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Bay
	 * 
     * @param	id		UUID
     */
    public Bay delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Bay entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a VoltageLevel
     * 
     * @param	parentId	UUID
     * @param	assignment 	VoltageLevel 
     * @return	Bay
     */
    public Bay assignVoltageLevel( UUID parentId, VoltageLevel assignment ) {
	    LOGGER.info("assigning VoltageLevel as " + assignment.toString() );

	    Bay parentEntity = repository.findById( parentId ).get();
	    assignment = VoltageLevelProjector.find(assignment.getVoltageLevelId());
	    
	    // ------------------------------------------
		// assign the VoltageLevel to the parent entity
		// ------------------------------------------ 
	    parentEntity.setVoltageLevel( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the VoltageLevel
	 * 
	 * @param	parentId		UUID
	 * @return	Bay
	 */
	public Bay unAssignVoltageLevel( UUID parentId ) {
		Bay parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning VoltageLevel on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the VoltageLevel on the parent entithy
		// ------------------------------------------     
	    parentEntity.setVoltageLevel(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the Bay via an FindBayQuery
     * @return 	query	FindBayQuery
     */
    @SuppressWarnings("unused")
    public Bay find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Bay - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Bays
     *
     * @param	query	FindAllBayQuery 
     * @return 	List<Bay> 
     */
    @SuppressWarnings("unused")
    public List<Bay> findAll( FindAllBayQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Bay - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final BayRepository repository;
    @Autowired
	@Qualifier(value = "voltageLevel-entity-projector")
	VoltageLevelEntityProjector VoltageLevelProjector;

    private static final Logger LOGGER 	= Logger.getLogger(BayEntityProjector.class.getName());

}
