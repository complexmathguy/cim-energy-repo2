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
 * Projector for Line as outlined for the CQRS pattern.
 * 
 * Commands are handled by LineAggregate
 * 
 * @author your_name_here
 *
 */
@Component("line-entity-projector")
public class LineEntityProjector {
		
	// core constructor
	public LineEntityProjector(LineRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Line
	 * 
     * @param	entity Line
     */
    public Line create( Line entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Line
	 * 
     * @param	entity Line
     */
    public Line update( Line entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Line
	 * 
     * @param	id		UUID
     */
    public Line delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Line entity = repository.findById(id).get();
	    
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
     * @return	Line
     */
    public Line assignRegion( UUID parentId, SubGeographicalRegion assignment ) {
	    LOGGER.info("assigning Region as " + assignment.toString() );

	    Line parentEntity = repository.findById( parentId ).get();
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
	 * @return	Line
	 */
	public Line unAssignRegion( UUID parentId ) {
		Line parentEntity = repository.findById(parentId).get();

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
     * Method to retrieve the Line via an FindLineQuery
     * @return 	query	FindLineQuery
     */
    @SuppressWarnings("unused")
    public Line find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Line - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Lines
     *
     * @param	query	FindAllLineQuery 
     * @return 	List<Line> 
     */
    @SuppressWarnings("unused")
    public List<Line> findAll( FindAllLineQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Line - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final LineRepository repository;
    @Autowired
	@Qualifier(value = "subGeographicalRegion-entity-projector")
	SubGeographicalRegionEntityProjector SubGeographicalRegionProjector;

    private static final Logger LOGGER 	= Logger.getLogger(LineEntityProjector.class.getName());

}
