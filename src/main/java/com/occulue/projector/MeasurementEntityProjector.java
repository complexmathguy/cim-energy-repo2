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
 * Projector for Measurement as outlined for the CQRS pattern.
 * 
 * Commands are handled by MeasurementAggregate
 * 
 * @author your_name_here
 *
 */
@Component("measurement-entity-projector")
public class MeasurementEntityProjector {
		
	// core constructor
	public MeasurementEntityProjector(MeasurementRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Measurement
	 * 
     * @param	entity Measurement
     */
    public Measurement create( Measurement entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Measurement
	 * 
     * @param	entity Measurement
     */
    public Measurement update( Measurement entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Measurement
	 * 
     * @param	id		UUID
     */
    public Measurement delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Measurement entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a MeasurementType
     * 
     * @param	parentId	UUID
     * @param	assignment 	StringProxy 
     * @return	Measurement
     */
    public Measurement assignMeasurementType( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning MeasurementType as " + assignment.toString() );

	    Measurement parentEntity = repository.findById( parentId ).get();
	    assignment = StringProxyProjector.find(assignment.getStringProxyId());
	    
	    // ------------------------------------------
		// assign the MeasurementType to the parent entity
		// ------------------------------------------ 
	    parentEntity.setMeasurementType( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the MeasurementType
	 * 
	 * @param	parentId		UUID
	 * @return	Measurement
	 */
	public Measurement unAssignMeasurementType( UUID parentId ) {
		Measurement parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning MeasurementType on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the MeasurementType on the parent entithy
		// ------------------------------------------     
	    parentEntity.setMeasurementType(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a PowerSystemResource
     * 
     * @param	parentId	UUID
     * @param	assignment 	PowerSystemResource 
     * @return	Measurement
     */
    public Measurement assignPowerSystemResource( UUID parentId, PowerSystemResource assignment ) {
	    LOGGER.info("assigning PowerSystemResource as " + assignment.toString() );

	    Measurement parentEntity = repository.findById( parentId ).get();
	    assignment = PowerSystemResourceProjector.find(assignment.getPowerSystemResourceId());
	    
	    // ------------------------------------------
		// assign the PowerSystemResource to the parent entity
		// ------------------------------------------ 
	    parentEntity.setPowerSystemResource( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the PowerSystemResource
	 * 
	 * @param	parentId		UUID
	 * @return	Measurement
	 */
	public Measurement unAssignPowerSystemResource( UUID parentId ) {
		Measurement parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning PowerSystemResource on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the PowerSystemResource on the parent entithy
		// ------------------------------------------     
	    parentEntity.setPowerSystemResource(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Terminal
     * 
     * @param	parentId	UUID
     * @param	assignment 	ACDCTerminal 
     * @return	Measurement
     */
    public Measurement assignTerminal( UUID parentId, ACDCTerminal assignment ) {
	    LOGGER.info("assigning Terminal as " + assignment.toString() );

	    Measurement parentEntity = repository.findById( parentId ).get();
	    assignment = ACDCTerminalProjector.find(assignment.getACDCTerminalId());
	    
	    // ------------------------------------------
		// assign the Terminal to the parent entity
		// ------------------------------------------ 
	    parentEntity.setTerminal( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Terminal
	 * 
	 * @param	parentId		UUID
	 * @return	Measurement
	 */
	public Measurement unAssignTerminal( UUID parentId ) {
		Measurement parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Terminal on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Terminal on the parent entithy
		// ------------------------------------------     
	    parentEntity.setTerminal(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the Measurement via an FindMeasurementQuery
     * @return 	query	FindMeasurementQuery
     */
    @SuppressWarnings("unused")
    public Measurement find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Measurement - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Measurements
     *
     * @param	query	FindAllMeasurementQuery 
     * @return 	List<Measurement> 
     */
    @SuppressWarnings("unused")
    public List<Measurement> findAll( FindAllMeasurementQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Measurement - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final MeasurementRepository repository;
    @Autowired
	@Qualifier(value = "stringProxy-entity-projector")
	StringProxyEntityProjector StringProxyProjector;
    @Autowired
	@Qualifier(value = "powerSystemResource-entity-projector")
	PowerSystemResourceEntityProjector PowerSystemResourceProjector;
    @Autowired
	@Qualifier(value = "aCDCTerminal-entity-projector")
	ACDCTerminalEntityProjector ACDCTerminalProjector;

    private static final Logger LOGGER 	= Logger.getLogger(MeasurementEntityProjector.class.getName());

}
