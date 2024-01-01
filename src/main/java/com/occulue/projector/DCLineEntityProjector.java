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
 * Projector for DCLine as outlined for the CQRS pattern.
 * 
 * Commands are handled by DCLineAggregate
 * 
 * @author your_name_here
 *
 */
@Component("dCLine-entity-projector")
public class DCLineEntityProjector {
		
	// core constructor
	public DCLineEntityProjector(DCLineRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a DCLine
	 * 
     * @param	entity DCLine
     */
    public DCLine create( DCLine entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a DCLine
	 * 
     * @param	entity DCLine
     */
    public DCLine update( DCLine entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a DCLine
	 * 
     * @param	id		UUID
     */
    public DCLine delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    DCLine entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Region
     * 
     * @param	parentId	UUID
     * @param	assignment 	SubGeographicalRegion 
     * @return	DCLine
     */
    public DCLine assignRegion( UUID parentId, SubGeographicalRegion assignment ) {
	    LOGGER.info("assigning Region as " + assignment.toString() );

	    DCLine parentEntity = repository.findById( parentId ).get();
	    assignment = SubGeographicalRegionProjector.find(assignment.getSubGeographicalRegionId());
	    
	    // ------------------------------------------
		// assign the Region to the parent entity
		// ------------------------------------------ 
	    parentEntity.setRegion( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Region
	 * 
	 * @param	parentId		UUID
	 * @return	DCLine
	 */
	public DCLine unAssignRegion( UUID parentId ) {
		DCLine parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Region on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Region on the parent entithy
		// ------------------------------------------     
	    parentEntity.setRegion(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the DCLine via an FindDCLineQuery
     * @return 	query	FindDCLineQuery
     */
    @SuppressWarnings("unused")
    public DCLine find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a DCLine - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all DCLines
     *
     * @param	query	FindAllDCLineQuery 
     * @return 	List<DCLine> 
     */
    @SuppressWarnings("unused")
    public List<DCLine> findAll( FindAllDCLineQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all DCLine - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final DCLineRepository repository;
    @Autowired
	@Qualifier(value = "subGeographicalRegion-entity-projector")
	SubGeographicalRegionEntityProjector SubGeographicalRegionProjector;

    private static final Logger LOGGER 	= Logger.getLogger(DCLineEntityProjector.class.getName());

}
