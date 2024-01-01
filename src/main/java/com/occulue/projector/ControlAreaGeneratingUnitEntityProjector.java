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
 * Projector for ControlAreaGeneratingUnit as outlined for the CQRS pattern.
 * 
 * Commands are handled by ControlAreaGeneratingUnitAggregate
 * 
 * @author your_name_here
 *
 */
@Component("controlAreaGeneratingUnit-entity-projector")
public class ControlAreaGeneratingUnitEntityProjector {
		
	// core constructor
	public ControlAreaGeneratingUnitEntityProjector(ControlAreaGeneratingUnitRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a ControlAreaGeneratingUnit
	 * 
     * @param	entity ControlAreaGeneratingUnit
     */
    public ControlAreaGeneratingUnit create( ControlAreaGeneratingUnit entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a ControlAreaGeneratingUnit
	 * 
     * @param	entity ControlAreaGeneratingUnit
     */
    public ControlAreaGeneratingUnit update( ControlAreaGeneratingUnit entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a ControlAreaGeneratingUnit
	 * 
     * @param	id		UUID
     */
    public ControlAreaGeneratingUnit delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    ControlAreaGeneratingUnit entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a ControlArea
     * 
     * @param	parentId	UUID
     * @param	assignment 	ControlArea 
     * @return	ControlAreaGeneratingUnit
     */
    public ControlAreaGeneratingUnit assignControlArea( UUID parentId, ControlArea assignment ) {
	    LOGGER.info("assigning ControlArea as " + assignment.toString() );

	    ControlAreaGeneratingUnit parentEntity = repository.findById( parentId ).get();
	    assignment = ControlAreaProjector.find(assignment.getControlAreaId());
	    
	    // ------------------------------------------
		// assign the ControlArea to the parent entity
		// ------------------------------------------ 
	    parentEntity.setControlArea( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ControlArea
	 * 
	 * @param	parentId		UUID
	 * @return	ControlAreaGeneratingUnit
	 */
	public ControlAreaGeneratingUnit unAssignControlArea( UUID parentId ) {
		ControlAreaGeneratingUnit parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ControlArea on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ControlArea on the parent entithy
		// ------------------------------------------     
	    parentEntity.setControlArea(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a GeneratingUnit
     * 
     * @param	parentId	UUID
     * @param	assignment 	GeneratingUnit 
     * @return	ControlAreaGeneratingUnit
     */
    public ControlAreaGeneratingUnit assignGeneratingUnit( UUID parentId, GeneratingUnit assignment ) {
	    LOGGER.info("assigning GeneratingUnit as " + assignment.toString() );

	    ControlAreaGeneratingUnit parentEntity = repository.findById( parentId ).get();
	    assignment = GeneratingUnitProjector.find(assignment.getGeneratingUnitId());
	    
	    // ------------------------------------------
		// assign the GeneratingUnit to the parent entity
		// ------------------------------------------ 
	    parentEntity.setGeneratingUnit( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the GeneratingUnit
	 * 
	 * @param	parentId		UUID
	 * @return	ControlAreaGeneratingUnit
	 */
	public ControlAreaGeneratingUnit unAssignGeneratingUnit( UUID parentId ) {
		ControlAreaGeneratingUnit parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning GeneratingUnit on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the GeneratingUnit on the parent entithy
		// ------------------------------------------     
	    parentEntity.setGeneratingUnit(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the ControlAreaGeneratingUnit via an FindControlAreaGeneratingUnitQuery
     * @return 	query	FindControlAreaGeneratingUnitQuery
     */
    @SuppressWarnings("unused")
    public ControlAreaGeneratingUnit find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a ControlAreaGeneratingUnit - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all ControlAreaGeneratingUnits
     *
     * @param	query	FindAllControlAreaGeneratingUnitQuery 
     * @return 	List<ControlAreaGeneratingUnit> 
     */
    @SuppressWarnings("unused")
    public List<ControlAreaGeneratingUnit> findAll( FindAllControlAreaGeneratingUnitQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all ControlAreaGeneratingUnit - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final ControlAreaGeneratingUnitRepository repository;
    @Autowired
	@Qualifier(value = "controlArea-entity-projector")
	ControlAreaEntityProjector ControlAreaProjector;
    @Autowired
	@Qualifier(value = "generatingUnit-entity-projector")
	GeneratingUnitEntityProjector GeneratingUnitProjector;

    private static final Logger LOGGER 	= Logger.getLogger(ControlAreaGeneratingUnitEntityProjector.class.getName());

}
