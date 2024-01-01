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
 * Projector for WindGenTurbineType2IEC as outlined for the CQRS pattern.
 * 
 * Commands are handled by WindGenTurbineType2IECAggregate
 * 
 * @author your_name_here
 *
 */
@Component("windGenTurbineType2IEC-entity-projector")
public class WindGenTurbineType2IECEntityProjector {
		
	// core constructor
	public WindGenTurbineType2IECEntityProjector(WindGenTurbineType2IECRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a WindGenTurbineType2IEC
	 * 
     * @param	entity WindGenTurbineType2IEC
     */
    public WindGenTurbineType2IEC create( WindGenTurbineType2IEC entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a WindGenTurbineType2IEC
	 * 
     * @param	entity WindGenTurbineType2IEC
     */
    public WindGenTurbineType2IEC update( WindGenTurbineType2IEC entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a WindGenTurbineType2IEC
	 * 
     * @param	id		UUID
     */
    public WindGenTurbineType2IEC delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    WindGenTurbineType2IEC entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a WindContRotorRIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindContRotorRIEC 
     * @return	WindGenTurbineType2IEC
     */
    public WindGenTurbineType2IEC assignWindContRotorRIEC( UUID parentId, WindContRotorRIEC assignment ) {
	    LOGGER.info("assigning WindContRotorRIEC as " + assignment.toString() );

	    WindGenTurbineType2IEC parentEntity = repository.findById( parentId ).get();
	    assignment = WindContRotorRIECProjector.find(assignment.getWindContRotorRIECId());
	    
	    // ------------------------------------------
		// assign the WindContRotorRIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindContRotorRIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindContRotorRIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindGenTurbineType2IEC
	 */
	public WindGenTurbineType2IEC unAssignWindContRotorRIEC( UUID parentId ) {
		WindGenTurbineType2IEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindContRotorRIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindContRotorRIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindContRotorRIEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindPitchContEmulIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindPitchContEmulIEC 
     * @return	WindGenTurbineType2IEC
     */
    public WindGenTurbineType2IEC assignWindPitchContEmulIEC( UUID parentId, WindPitchContEmulIEC assignment ) {
	    LOGGER.info("assigning WindPitchContEmulIEC as " + assignment.toString() );

	    WindGenTurbineType2IEC parentEntity = repository.findById( parentId ).get();
	    assignment = WindPitchContEmulIECProjector.find(assignment.getWindPitchContEmulIECId());
	    
	    // ------------------------------------------
		// assign the WindPitchContEmulIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindPitchContEmulIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindPitchContEmulIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindGenTurbineType2IEC
	 */
	public WindGenTurbineType2IEC unAssignWindPitchContEmulIEC( UUID parentId ) {
		WindGenTurbineType2IEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindPitchContEmulIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindPitchContEmulIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindPitchContEmulIEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the WindGenTurbineType2IEC via an FindWindGenTurbineType2IECQuery
     * @return 	query	FindWindGenTurbineType2IECQuery
     */
    @SuppressWarnings("unused")
    public WindGenTurbineType2IEC find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a WindGenTurbineType2IEC - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all WindGenTurbineType2IECs
     *
     * @param	query	FindAllWindGenTurbineType2IECQuery 
     * @return 	List<WindGenTurbineType2IEC> 
     */
    @SuppressWarnings("unused")
    public List<WindGenTurbineType2IEC> findAll( FindAllWindGenTurbineType2IECQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all WindGenTurbineType2IEC - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final WindGenTurbineType2IECRepository repository;
    @Autowired
	@Qualifier(value = "windContRotorRIEC-entity-projector")
	WindContRotorRIECEntityProjector WindContRotorRIECProjector;
    @Autowired
	@Qualifier(value = "windPitchContEmulIEC-entity-projector")
	WindPitchContEmulIECEntityProjector WindPitchContEmulIECProjector;

    private static final Logger LOGGER 	= Logger.getLogger(WindGenTurbineType2IECEntityProjector.class.getName());

}
