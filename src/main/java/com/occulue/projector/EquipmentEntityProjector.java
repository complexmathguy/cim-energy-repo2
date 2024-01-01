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
 * Projector for Equipment as outlined for the CQRS pattern.
 * 
 * Commands are handled by EquipmentAggregate
 * 
 * @author your_name_here
 *
 */
@Component("equipment-entity-projector")
public class EquipmentEntityProjector {
		
	// core constructor
	public EquipmentEntityProjector(EquipmentRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Equipment
	 * 
     * @param	entity Equipment
     */
    public Equipment create( Equipment entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Equipment
	 * 
     * @param	entity Equipment
     */
    public Equipment update( Equipment entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Equipment
	 * 
     * @param	id		UUID
     */
    public Equipment delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Equipment entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a EquipmentContainer
     * 
     * @param	parentId	UUID
     * @param	assignment 	EquipmentContainer 
     * @return	Equipment
     */
    public Equipment assignEquipmentContainer( UUID parentId, EquipmentContainer assignment ) {
	    LOGGER.info("assigning EquipmentContainer as " + assignment.toString() );

	    Equipment parentEntity = repository.findById( parentId ).get();
	    assignment = EquipmentContainerProjector.find(assignment.getEquipmentContainerId());
	    
	    // ------------------------------------------
		// assign the EquipmentContainer to the parent entity
		// ------------------------------------------ 
	    parentEntity.setEquipmentContainer( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the EquipmentContainer
	 * 
	 * @param	parentId		UUID
	 * @return	Equipment
	 */
	public Equipment unAssignEquipmentContainer( UUID parentId ) {
		Equipment parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning EquipmentContainer on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the EquipmentContainer on the parent entithy
		// ------------------------------------------     
	    parentEntity.setEquipmentContainer(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the Equipment via an FindEquipmentQuery
     * @return 	query	FindEquipmentQuery
     */
    @SuppressWarnings("unused")
    public Equipment find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Equipment - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Equipments
     *
     * @param	query	FindAllEquipmentQuery 
     * @return 	List<Equipment> 
     */
    @SuppressWarnings("unused")
    public List<Equipment> findAll( FindAllEquipmentQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Equipment - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final EquipmentRepository repository;
    @Autowired
	@Qualifier(value = "equipmentContainer-entity-projector")
	EquipmentContainerEntityProjector EquipmentContainerProjector;
    @Autowired
	@Qualifier(value = "operationalLimitSet-entity-projector")
	OperationalLimitSetEntityProjector OperationalLimitSetProjector;

    private static final Logger LOGGER 	= Logger.getLogger(EquipmentEntityProjector.class.getName());

}
