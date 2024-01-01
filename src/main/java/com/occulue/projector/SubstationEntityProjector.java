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
 * Projector for Substation as outlined for the CQRS pattern.
 * 
 * Commands are handled by SubstationAggregate
 * 
 * @author your_name_here
 *
 */
@Component("substation-entity-projector")
public class SubstationEntityProjector {
		
	// core constructor
	public SubstationEntityProjector(SubstationRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Substation
	 * 
     * @param	entity Substation
     */
    public Substation create( Substation entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Substation
	 * 
     * @param	entity Substation
     */
    public Substation update( Substation entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Substation
	 * 
     * @param	id		UUID
     */
    public Substation delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Substation entity = repository.findById(id).get();
	    
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
     * @return	Substation
     */
    public Substation assignRegion( UUID parentId, SubGeographicalRegion assignment ) {
	    LOGGER.info("assigning Region as " + assignment.toString() );

	    Substation parentEntity = repository.findById( parentId ).get();
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
	 * @return	Substation
	 */
	public Substation unAssignRegion( UUID parentId ) {
		Substation parentEntity = repository.findById(parentId).get();

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
     * Method to retrieve the Substation via an FindSubstationQuery
     * @return 	query	FindSubstationQuery
     */
    @SuppressWarnings("unused")
    public Substation find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Substation - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Substations
     *
     * @param	query	FindAllSubstationQuery 
     * @return 	List<Substation> 
     */
    @SuppressWarnings("unused")
    public List<Substation> findAll( FindAllSubstationQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Substation - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final SubstationRepository repository;
    @Autowired
	@Qualifier(value = "dCConverterUnit-entity-projector")
	DCConverterUnitEntityProjector DCConverterUnitProjector;
    @Autowired
	@Qualifier(value = "subGeographicalRegion-entity-projector")
	SubGeographicalRegionEntityProjector SubGeographicalRegionProjector;

    private static final Logger LOGGER 	= Logger.getLogger(SubstationEntityProjector.class.getName());

}
