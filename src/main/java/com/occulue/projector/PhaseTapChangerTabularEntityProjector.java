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
 * Projector for PhaseTapChangerTabular as outlined for the CQRS pattern.
 * 
 * Commands are handled by PhaseTapChangerTabularAggregate
 * 
 * @author your_name_here
 *
 */
@Component("phaseTapChangerTabular-entity-projector")
public class PhaseTapChangerTabularEntityProjector {
		
	// core constructor
	public PhaseTapChangerTabularEntityProjector(PhaseTapChangerTabularRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a PhaseTapChangerTabular
	 * 
     * @param	entity PhaseTapChangerTabular
     */
    public PhaseTapChangerTabular create( PhaseTapChangerTabular entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a PhaseTapChangerTabular
	 * 
     * @param	entity PhaseTapChangerTabular
     */
    public PhaseTapChangerTabular update( PhaseTapChangerTabular entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a PhaseTapChangerTabular
	 * 
     * @param	id		UUID
     */
    public PhaseTapChangerTabular delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    PhaseTapChangerTabular entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a PhaseTapChangerTable
     * 
     * @param	parentId	UUID
     * @param	assignment 	PhaseTapChangerTable 
     * @return	PhaseTapChangerTabular
     */
    public PhaseTapChangerTabular assignPhaseTapChangerTable( UUID parentId, PhaseTapChangerTable assignment ) {
	    LOGGER.info("assigning PhaseTapChangerTable as " + assignment.toString() );

	    PhaseTapChangerTabular parentEntity = repository.findById( parentId ).get();
	    assignment = PhaseTapChangerTableProjector.find(assignment.getPhaseTapChangerTableId());
	    
	    // ------------------------------------------
		// assign the PhaseTapChangerTable to the parent entity
		// ------------------------------------------ 
	    parentEntity.setPhaseTapChangerTable( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the PhaseTapChangerTable
	 * 
	 * @param	parentId		UUID
	 * @return	PhaseTapChangerTabular
	 */
	public PhaseTapChangerTabular unAssignPhaseTapChangerTable( UUID parentId ) {
		PhaseTapChangerTabular parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning PhaseTapChangerTable on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the PhaseTapChangerTable on the parent entithy
		// ------------------------------------------     
	    parentEntity.setPhaseTapChangerTable(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the PhaseTapChangerTabular via an FindPhaseTapChangerTabularQuery
     * @return 	query	FindPhaseTapChangerTabularQuery
     */
    @SuppressWarnings("unused")
    public PhaseTapChangerTabular find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a PhaseTapChangerTabular - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all PhaseTapChangerTabulars
     *
     * @param	query	FindAllPhaseTapChangerTabularQuery 
     * @return 	List<PhaseTapChangerTabular> 
     */
    @SuppressWarnings("unused")
    public List<PhaseTapChangerTabular> findAll( FindAllPhaseTapChangerTabularQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all PhaseTapChangerTabular - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final PhaseTapChangerTabularRepository repository;
    @Autowired
	@Qualifier(value = "phaseTapChangerTable-entity-projector")
	PhaseTapChangerTableEntityProjector PhaseTapChangerTableProjector;

    private static final Logger LOGGER 	= Logger.getLogger(PhaseTapChangerTabularEntityProjector.class.getName());

}
