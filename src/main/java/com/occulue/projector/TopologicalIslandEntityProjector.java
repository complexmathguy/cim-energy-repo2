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
 * Projector for TopologicalIsland as outlined for the CQRS pattern.
 * 
 * Commands are handled by TopologicalIslandAggregate
 * 
 * @author your_name_here
 *
 */
@Component("topologicalIsland-entity-projector")
public class TopologicalIslandEntityProjector {
		
	// core constructor
	public TopologicalIslandEntityProjector(TopologicalIslandRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a TopologicalIsland
	 * 
     * @param	entity TopologicalIsland
     */
    public TopologicalIsland create( TopologicalIsland entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a TopologicalIsland
	 * 
     * @param	entity TopologicalIsland
     */
    public TopologicalIsland update( TopologicalIsland entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a TopologicalIsland
	 * 
     * @param	id		UUID
     */
    public TopologicalIsland delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    TopologicalIsland entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a TopologicalNodes
     * 
     * @param	parentId	UUID
     * @param	assignment 	TopologicalNode 
     * @return	TopologicalIsland
     */
    public TopologicalIsland assignTopologicalNodes( UUID parentId, TopologicalNode assignment ) {
	    LOGGER.info("assigning TopologicalNodes as " + assignment.toString() );

	    TopologicalIsland parentEntity = repository.findById( parentId ).get();
	    assignment = TopologicalNodeProjector.find(assignment.getTopologicalNodeId());
	    
	    // ------------------------------------------
		// assign the TopologicalNodes to the parent entity
		// ------------------------------------------ 
	    parentEntity.setTopologicalNodes( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the TopologicalNodes
	 * 
	 * @param	parentId		UUID
	 * @return	TopologicalIsland
	 */
	public TopologicalIsland unAssignTopologicalNodes( UUID parentId ) {
		TopologicalIsland parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning TopologicalNodes on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the TopologicalNodes on the parent entithy
		// ------------------------------------------     
	    parentEntity.setTopologicalNodes(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a AngleRefTopologicalNode
     * 
     * @param	parentId	UUID
     * @param	assignment 	TopologicalNode 
     * @return	TopologicalIsland
     */
    public TopologicalIsland assignAngleRefTopologicalNode( UUID parentId, TopologicalNode assignment ) {
	    LOGGER.info("assigning AngleRefTopologicalNode as " + assignment.toString() );

	    TopologicalIsland parentEntity = repository.findById( parentId ).get();
	    assignment = TopologicalNodeProjector.find(assignment.getTopologicalNodeId());
	    
	    // ------------------------------------------
		// assign the AngleRefTopologicalNode to the parent entity
		// ------------------------------------------ 
	    parentEntity.setAngleRefTopologicalNode( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the AngleRefTopologicalNode
	 * 
	 * @param	parentId		UUID
	 * @return	TopologicalIsland
	 */
	public TopologicalIsland unAssignAngleRefTopologicalNode( UUID parentId ) {
		TopologicalIsland parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning AngleRefTopologicalNode on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the AngleRefTopologicalNode on the parent entithy
		// ------------------------------------------     
	    parentEntity.setAngleRefTopologicalNode(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the TopologicalIsland via an FindTopologicalIslandQuery
     * @return 	query	FindTopologicalIslandQuery
     */
    @SuppressWarnings("unused")
    public TopologicalIsland find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a TopologicalIsland - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all TopologicalIslands
     *
     * @param	query	FindAllTopologicalIslandQuery 
     * @return 	List<TopologicalIsland> 
     */
    @SuppressWarnings("unused")
    public List<TopologicalIsland> findAll( FindAllTopologicalIslandQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all TopologicalIsland - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final TopologicalIslandRepository repository;
    @Autowired
	@Qualifier(value = "topologicalNode-entity-projector")
	TopologicalNodeEntityProjector TopologicalNodeProjector;

    private static final Logger LOGGER 	= Logger.getLogger(TopologicalIslandEntityProjector.class.getName());

}
