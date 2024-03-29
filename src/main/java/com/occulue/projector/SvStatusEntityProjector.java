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
 * Projector for SvStatus as outlined for the CQRS pattern.
 * 
 * Commands are handled by SvStatusAggregate
 * 
 * @author your_name_here
 *
 */
@Component("svStatus-entity-projector")
public class SvStatusEntityProjector {
		
	// core constructor
	public SvStatusEntityProjector(SvStatusRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a SvStatus
	 * 
     * @param	entity SvStatus
     */
    public SvStatus create( SvStatus entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a SvStatus
	 * 
     * @param	entity SvStatus
     */
    public SvStatus update( SvStatus entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a SvStatus
	 * 
     * @param	id		UUID
     */
    public SvStatus delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    SvStatus entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a InService
     * 
     * @param	parentId	UUID
     * @param	assignment 	BooleanProxy 
     * @return	SvStatus
     */
    public SvStatus assignInService( UUID parentId, BooleanProxy assignment ) {
	    LOGGER.info("assigning InService as " + assignment.toString() );

	    SvStatus parentEntity = repository.findById( parentId ).get();
	    assignment = BooleanProxyProjector.find(assignment.getBooleanProxyId());
	    
	    // ------------------------------------------
		// assign the InService to the parent entity
		// ------------------------------------------ 
	    parentEntity.setInService( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the InService
	 * 
	 * @param	parentId		UUID
	 * @return	SvStatus
	 */
	public SvStatus unAssignInService( UUID parentId ) {
		SvStatus parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning InService on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the InService on the parent entithy
		// ------------------------------------------     
	    parentEntity.setInService(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a ConductingEquipment
     * 
     * @param	parentId	UUID
     * @param	assignment 	ConductingEquipment 
     * @return	SvStatus
     */
    public SvStatus assignConductingEquipment( UUID parentId, ConductingEquipment assignment ) {
	    LOGGER.info("assigning ConductingEquipment as " + assignment.toString() );

	    SvStatus parentEntity = repository.findById( parentId ).get();
	    assignment = ConductingEquipmentProjector.find(assignment.getConductingEquipmentId());
	    
	    // ------------------------------------------
		// assign the ConductingEquipment to the parent entity
		// ------------------------------------------ 
	    parentEntity.setConductingEquipment( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ConductingEquipment
	 * 
	 * @param	parentId		UUID
	 * @return	SvStatus
	 */
	public SvStatus unAssignConductingEquipment( UUID parentId ) {
		SvStatus parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ConductingEquipment on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ConductingEquipment on the parent entithy
		// ------------------------------------------     
	    parentEntity.setConductingEquipment(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the SvStatus via an FindSvStatusQuery
     * @return 	query	FindSvStatusQuery
     */
    @SuppressWarnings("unused")
    public SvStatus find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a SvStatus - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all SvStatuss
     *
     * @param	query	FindAllSvStatusQuery 
     * @return 	List<SvStatus> 
     */
    @SuppressWarnings("unused")
    public List<SvStatus> findAll( FindAllSvStatusQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all SvStatus - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final SvStatusRepository repository;
    @Autowired
	@Qualifier(value = "booleanProxy-entity-projector")
	BooleanProxyEntityProjector BooleanProxyProjector;
    @Autowired
	@Qualifier(value = "conductingEquipment-entity-projector")
	ConductingEquipmentEntityProjector ConductingEquipmentProjector;

    private static final Logger LOGGER 	= Logger.getLogger(SvStatusEntityProjector.class.getName());

}
