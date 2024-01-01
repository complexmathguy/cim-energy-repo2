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
 * Projector for DCTopologicalNode as outlined for the CQRS pattern.
 * 
 * Commands are handled by DCTopologicalNodeAggregate
 * 
 * @author your_name_here
 *
 */
@Component("dCTopologicalNode-entity-projector")
public class DCTopologicalNodeEntityProjector {
		
	// core constructor
	public DCTopologicalNodeEntityProjector(DCTopologicalNodeRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a DCTopologicalNode
	 * 
     * @param	entity DCTopologicalNode
     */
    public DCTopologicalNode create( DCTopologicalNode entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a DCTopologicalNode
	 * 
     * @param	entity DCTopologicalNode
     */
    public DCTopologicalNode update( DCTopologicalNode entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a DCTopologicalNode
	 * 
     * @param	id		UUID
     */
    public DCTopologicalNode delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    DCTopologicalNode entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a DCEquipmentContainer
     * 
     * @param	parentId	UUID
     * @param	assignment 	DCEquipmentContainer 
     * @return	DCTopologicalNode
     */
    public DCTopologicalNode assignDCEquipmentContainer( UUID parentId, DCEquipmentContainer assignment ) {
	    LOGGER.info("assigning DCEquipmentContainer as " + assignment.toString() );

	    DCTopologicalNode parentEntity = repository.findById( parentId ).get();
	    assignment = DCEquipmentContainerProjector.find(assignment.getDCEquipmentContainerId());
	    
	    // ------------------------------------------
		// assign the DCEquipmentContainer to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDCEquipmentContainer( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the DCEquipmentContainer
	 * 
	 * @param	parentId		UUID
	 * @return	DCTopologicalNode
	 */
	public DCTopologicalNode unAssignDCEquipmentContainer( UUID parentId ) {
		DCTopologicalNode parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning DCEquipmentContainer on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the DCEquipmentContainer on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDCEquipmentContainer(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the DCTopologicalNode via an FindDCTopologicalNodeQuery
     * @return 	query	FindDCTopologicalNodeQuery
     */
    @SuppressWarnings("unused")
    public DCTopologicalNode find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a DCTopologicalNode - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all DCTopologicalNodes
     *
     * @param	query	FindAllDCTopologicalNodeQuery 
     * @return 	List<DCTopologicalNode> 
     */
    @SuppressWarnings("unused")
    public List<DCTopologicalNode> findAll( FindAllDCTopologicalNodeQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all DCTopologicalNode - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final DCTopologicalNodeRepository repository;
    @Autowired
	@Qualifier(value = "dCEquipmentContainer-entity-projector")
	DCEquipmentContainerEntityProjector DCEquipmentContainerProjector;
    @Autowired
	@Qualifier(value = "dCTopologicalIsland-entity-projector")
	DCTopologicalIslandEntityProjector DCTopologicalIslandProjector;

    private static final Logger LOGGER 	= Logger.getLogger(DCTopologicalNodeEntityProjector.class.getName());

}
