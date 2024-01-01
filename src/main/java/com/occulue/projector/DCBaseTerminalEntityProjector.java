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
 * Projector for DCBaseTerminal as outlined for the CQRS pattern.
 * 
 * Commands are handled by DCBaseTerminalAggregate
 * 
 * @author your_name_here
 *
 */
@Component("dCBaseTerminal-entity-projector")
public class DCBaseTerminalEntityProjector {
		
	// core constructor
	public DCBaseTerminalEntityProjector(DCBaseTerminalRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a DCBaseTerminal
	 * 
     * @param	entity DCBaseTerminal
     */
    public DCBaseTerminal create( DCBaseTerminal entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a DCBaseTerminal
	 * 
     * @param	entity DCBaseTerminal
     */
    public DCBaseTerminal update( DCBaseTerminal entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a DCBaseTerminal
	 * 
     * @param	id		UUID
     */
    public DCBaseTerminal delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    DCBaseTerminal entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a DCNode
     * 
     * @param	parentId	UUID
     * @param	assignment 	DCNode 
     * @return	DCBaseTerminal
     */
    public DCBaseTerminal assignDCNode( UUID parentId, DCNode assignment ) {
	    LOGGER.info("assigning DCNode as " + assignment.toString() );

	    DCBaseTerminal parentEntity = repository.findById( parentId ).get();
	    assignment = DCNodeProjector.find(assignment.getDCNodeId());
	    
	    // ------------------------------------------
		// assign the DCNode to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDCNode( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the DCNode
	 * 
	 * @param	parentId		UUID
	 * @return	DCBaseTerminal
	 */
	public DCBaseTerminal unAssignDCNode( UUID parentId ) {
		DCBaseTerminal parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning DCNode on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the DCNode on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDCNode(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the DCBaseTerminal via an FindDCBaseTerminalQuery
     * @return 	query	FindDCBaseTerminalQuery
     */
    @SuppressWarnings("unused")
    public DCBaseTerminal find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a DCBaseTerminal - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all DCBaseTerminals
     *
     * @param	query	FindAllDCBaseTerminalQuery 
     * @return 	List<DCBaseTerminal> 
     */
    @SuppressWarnings("unused")
    public List<DCBaseTerminal> findAll( FindAllDCBaseTerminalQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all DCBaseTerminal - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final DCBaseTerminalRepository repository;
    @Autowired
	@Qualifier(value = "dCNode-entity-projector")
	DCNodeEntityProjector DCNodeProjector;

    private static final Logger LOGGER 	= Logger.getLogger(DCBaseTerminalEntityProjector.class.getName());

}
