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
 * Projector for GrossToNetActivePowerCurve as outlined for the CQRS pattern.
 * 
 * Commands are handled by GrossToNetActivePowerCurveAggregate
 * 
 * @author your_name_here
 *
 */
@Component("grossToNetActivePowerCurve-entity-projector")
public class GrossToNetActivePowerCurveEntityProjector {
		
	// core constructor
	public GrossToNetActivePowerCurveEntityProjector(GrossToNetActivePowerCurveRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a GrossToNetActivePowerCurve
	 * 
     * @param	entity GrossToNetActivePowerCurve
     */
    public GrossToNetActivePowerCurve create( GrossToNetActivePowerCurve entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a GrossToNetActivePowerCurve
	 * 
     * @param	entity GrossToNetActivePowerCurve
     */
    public GrossToNetActivePowerCurve update( GrossToNetActivePowerCurve entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a GrossToNetActivePowerCurve
	 * 
     * @param	id		UUID
     */
    public GrossToNetActivePowerCurve delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    GrossToNetActivePowerCurve entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a GeneratingUnit
     * 
     * @param	parentId	UUID
     * @param	assignment 	GeneratingUnit 
     * @return	GrossToNetActivePowerCurve
     */
    public GrossToNetActivePowerCurve assignGeneratingUnit( UUID parentId, GeneratingUnit assignment ) {
	    LOGGER.info("assigning GeneratingUnit as " + assignment.toString() );

	    GrossToNetActivePowerCurve parentEntity = repository.findById( parentId ).get();
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
	 * @return	GrossToNetActivePowerCurve
	 */
	public GrossToNetActivePowerCurve unAssignGeneratingUnit( UUID parentId ) {
		GrossToNetActivePowerCurve parentEntity = repository.findById(parentId).get();

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
     * Method to retrieve the GrossToNetActivePowerCurve via an FindGrossToNetActivePowerCurveQuery
     * @return 	query	FindGrossToNetActivePowerCurveQuery
     */
    @SuppressWarnings("unused")
    public GrossToNetActivePowerCurve find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a GrossToNetActivePowerCurve - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all GrossToNetActivePowerCurves
     *
     * @param	query	FindAllGrossToNetActivePowerCurveQuery 
     * @return 	List<GrossToNetActivePowerCurve> 
     */
    @SuppressWarnings("unused")
    public List<GrossToNetActivePowerCurve> findAll( FindAllGrossToNetActivePowerCurveQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all GrossToNetActivePowerCurve - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final GrossToNetActivePowerCurveRepository repository;
    @Autowired
	@Qualifier(value = "generatingUnit-entity-projector")
	GeneratingUnitEntityProjector GeneratingUnitProjector;

    private static final Logger LOGGER 	= Logger.getLogger(GrossToNetActivePowerCurveEntityProjector.class.getName());

}
