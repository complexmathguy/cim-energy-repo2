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
 * Projector for ACDCConverterDCTerminal as outlined for the CQRS pattern.
 * 
 * Commands are handled by ACDCConverterDCTerminalAggregate
 * 
 * @author your_name_here
 *
 */
@Component("aCDCConverterDCTerminal-entity-projector")
public class ACDCConverterDCTerminalEntityProjector {
		
	// core constructor
	public ACDCConverterDCTerminalEntityProjector(ACDCConverterDCTerminalRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a ACDCConverterDCTerminal
	 * 
     * @param	entity ACDCConverterDCTerminal
     */
    public ACDCConverterDCTerminal create( ACDCConverterDCTerminal entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a ACDCConverterDCTerminal
	 * 
     * @param	entity ACDCConverterDCTerminal
     */
    public ACDCConverterDCTerminal update( ACDCConverterDCTerminal entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a ACDCConverterDCTerminal
	 * 
     * @param	id		UUID
     */
    public ACDCConverterDCTerminal delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    ACDCConverterDCTerminal entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a DCConductingEquipment
     * 
     * @param	parentId	UUID
     * @param	assignment 	ACDCConverter 
     * @return	ACDCConverterDCTerminal
     */
    public ACDCConverterDCTerminal assignDCConductingEquipment( UUID parentId, ACDCConverter assignment ) {
	    LOGGER.info("assigning DCConductingEquipment as " + assignment.toString() );

	    ACDCConverterDCTerminal parentEntity = repository.findById( parentId ).get();
	    assignment = ACDCConverterProjector.find(assignment.getACDCConverterId());
	    
	    // ------------------------------------------
		// assign the DCConductingEquipment to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDCConductingEquipment( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the DCConductingEquipment
	 * 
	 * @param	parentId		UUID
	 * @return	ACDCConverterDCTerminal
	 */
	public ACDCConverterDCTerminal unAssignDCConductingEquipment( UUID parentId ) {
		ACDCConverterDCTerminal parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning DCConductingEquipment on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the DCConductingEquipment on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDCConductingEquipment(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the ACDCConverterDCTerminal via an FindACDCConverterDCTerminalQuery
     * @return 	query	FindACDCConverterDCTerminalQuery
     */
    @SuppressWarnings("unused")
    public ACDCConverterDCTerminal find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a ACDCConverterDCTerminal - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all ACDCConverterDCTerminals
     *
     * @param	query	FindAllACDCConverterDCTerminalQuery 
     * @return 	List<ACDCConverterDCTerminal> 
     */
    @SuppressWarnings("unused")
    public List<ACDCConverterDCTerminal> findAll( FindAllACDCConverterDCTerminalQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all ACDCConverterDCTerminal - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final ACDCConverterDCTerminalRepository repository;
    @Autowired
	@Qualifier(value = "aCDCConverter-entity-projector")
	ACDCConverterEntityProjector ACDCConverterProjector;

    private static final Logger LOGGER 	= Logger.getLogger(ACDCConverterDCTerminalEntityProjector.class.getName());

}
