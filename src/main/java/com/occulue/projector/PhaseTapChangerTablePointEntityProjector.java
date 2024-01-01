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
 * Projector for PhaseTapChangerTablePoint as outlined for the CQRS pattern.
 * 
 * Commands are handled by PhaseTapChangerTablePointAggregate
 * 
 * @author your_name_here
 *
 */
@Component("phaseTapChangerTablePoint-entity-projector")
public class PhaseTapChangerTablePointEntityProjector {
		
	// core constructor
	public PhaseTapChangerTablePointEntityProjector(PhaseTapChangerTablePointRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a PhaseTapChangerTablePoint
	 * 
     * @param	entity PhaseTapChangerTablePoint
     */
    public PhaseTapChangerTablePoint create( PhaseTapChangerTablePoint entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a PhaseTapChangerTablePoint
	 * 
     * @param	entity PhaseTapChangerTablePoint
     */
    public PhaseTapChangerTablePoint update( PhaseTapChangerTablePoint entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a PhaseTapChangerTablePoint
	 * 
     * @param	id		UUID
     */
    public PhaseTapChangerTablePoint delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    PhaseTapChangerTablePoint entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Angle
     * 
     * @param	parentId	UUID
     * @param	assignment 	AngleDegrees 
     * @return	PhaseTapChangerTablePoint
     */
    public PhaseTapChangerTablePoint assignAngle( UUID parentId, AngleDegrees assignment ) {
	    LOGGER.info("assigning Angle as " + assignment.toString() );

	    PhaseTapChangerTablePoint parentEntity = repository.findById( parentId ).get();
	    assignment = AngleDegreesProjector.find(assignment.getAngleDegreesId());
	    
	    // ------------------------------------------
		// assign the Angle to the parent entity
		// ------------------------------------------ 
	    parentEntity.setAngle( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Angle
	 * 
	 * @param	parentId		UUID
	 * @return	PhaseTapChangerTablePoint
	 */
	public PhaseTapChangerTablePoint unAssignAngle( UUID parentId ) {
		PhaseTapChangerTablePoint parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Angle on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Angle on the parent entithy
		// ------------------------------------------     
	    parentEntity.setAngle(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a PhaseTapChangerTable
     * 
     * @param	parentId	UUID
     * @param	assignment 	PhaseTapChangerTable 
     * @return	PhaseTapChangerTablePoint
     */
    public PhaseTapChangerTablePoint assignPhaseTapChangerTable( UUID parentId, PhaseTapChangerTable assignment ) {
	    LOGGER.info("assigning PhaseTapChangerTable as " + assignment.toString() );

	    PhaseTapChangerTablePoint parentEntity = repository.findById( parentId ).get();
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
	 * @return	PhaseTapChangerTablePoint
	 */
	public PhaseTapChangerTablePoint unAssignPhaseTapChangerTable( UUID parentId ) {
		PhaseTapChangerTablePoint parentEntity = repository.findById(parentId).get();

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
     * Method to retrieve the PhaseTapChangerTablePoint via an FindPhaseTapChangerTablePointQuery
     * @return 	query	FindPhaseTapChangerTablePointQuery
     */
    @SuppressWarnings("unused")
    public PhaseTapChangerTablePoint find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a PhaseTapChangerTablePoint - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all PhaseTapChangerTablePoints
     *
     * @param	query	FindAllPhaseTapChangerTablePointQuery 
     * @return 	List<PhaseTapChangerTablePoint> 
     */
    @SuppressWarnings("unused")
    public List<PhaseTapChangerTablePoint> findAll( FindAllPhaseTapChangerTablePointQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all PhaseTapChangerTablePoint - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final PhaseTapChangerTablePointRepository repository;
    @Autowired
	@Qualifier(value = "angleDegrees-entity-projector")
	AngleDegreesEntityProjector AngleDegreesProjector;
    @Autowired
	@Qualifier(value = "phaseTapChangerTable-entity-projector")
	PhaseTapChangerTableEntityProjector PhaseTapChangerTableProjector;

    private static final Logger LOGGER 	= Logger.getLogger(PhaseTapChangerTablePointEntityProjector.class.getName());

}
