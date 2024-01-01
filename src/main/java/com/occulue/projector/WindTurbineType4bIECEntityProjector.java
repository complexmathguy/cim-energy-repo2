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
 * Projector for WindTurbineType4bIEC as outlined for the CQRS pattern.
 * 
 * Commands are handled by WindTurbineType4bIECAggregate
 * 
 * @author your_name_here
 *
 */
@Component("windTurbineType4bIEC-entity-projector")
public class WindTurbineType4bIECEntityProjector {
		
	// core constructor
	public WindTurbineType4bIECEntityProjector(WindTurbineType4bIECRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a WindTurbineType4bIEC
	 * 
     * @param	entity WindTurbineType4bIEC
     */
    public WindTurbineType4bIEC create( WindTurbineType4bIEC entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a WindTurbineType4bIEC
	 * 
     * @param	entity WindTurbineType4bIEC
     */
    public WindTurbineType4bIEC update( WindTurbineType4bIEC entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a WindTurbineType4bIEC
	 * 
     * @param	id		UUID
     */
    public WindTurbineType4bIEC delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    WindTurbineType4bIEC entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a WindMechIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindMechIEC 
     * @return	WindTurbineType4bIEC
     */
    public WindTurbineType4bIEC assignWindMechIEC( UUID parentId, WindMechIEC assignment ) {
	    LOGGER.info("assigning WindMechIEC as " + assignment.toString() );

	    WindTurbineType4bIEC parentEntity = repository.findById( parentId ).get();
	    assignment = WindMechIECProjector.find(assignment.getWindMechIECId());
	    
	    // ------------------------------------------
		// assign the WindMechIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindMechIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindMechIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindTurbineType4bIEC
	 */
	public WindTurbineType4bIEC unAssignWindMechIEC( UUID parentId ) {
		WindTurbineType4bIEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindMechIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindMechIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindMechIEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindContPType4bIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindContPType4bIEC 
     * @return	WindTurbineType4bIEC
     */
    public WindTurbineType4bIEC assignWindContPType4bIEC( UUID parentId, WindContPType4bIEC assignment ) {
	    LOGGER.info("assigning WindContPType4bIEC as " + assignment.toString() );

	    WindTurbineType4bIEC parentEntity = repository.findById( parentId ).get();
	    assignment = WindContPType4bIECProjector.find(assignment.getWindContPType4bIECId());
	    
	    // ------------------------------------------
		// assign the WindContPType4bIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindContPType4bIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindContPType4bIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindTurbineType4bIEC
	 */
	public WindTurbineType4bIEC unAssignWindContPType4bIEC( UUID parentId ) {
		WindTurbineType4bIEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindContPType4bIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindContPType4bIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindContPType4bIEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the WindTurbineType4bIEC via an FindWindTurbineType4bIECQuery
     * @return 	query	FindWindTurbineType4bIECQuery
     */
    @SuppressWarnings("unused")
    public WindTurbineType4bIEC find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a WindTurbineType4bIEC - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all WindTurbineType4bIECs
     *
     * @param	query	FindAllWindTurbineType4bIECQuery 
     * @return 	List<WindTurbineType4bIEC> 
     */
    @SuppressWarnings("unused")
    public List<WindTurbineType4bIEC> findAll( FindAllWindTurbineType4bIECQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all WindTurbineType4bIEC - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final WindTurbineType4bIECRepository repository;
    @Autowired
	@Qualifier(value = "windMechIEC-entity-projector")
	WindMechIECEntityProjector WindMechIECProjector;
    @Autowired
	@Qualifier(value = "windContPType4bIEC-entity-projector")
	WindContPType4bIECEntityProjector WindContPType4bIECProjector;

    private static final Logger LOGGER 	= Logger.getLogger(WindTurbineType4bIECEntityProjector.class.getName());

}
