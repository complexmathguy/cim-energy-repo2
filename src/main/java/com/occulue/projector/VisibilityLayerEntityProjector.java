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
 * Projector for VisibilityLayer as outlined for the CQRS pattern.
 * 
 * Commands are handled by VisibilityLayerAggregate
 * 
 * @author your_name_here
 *
 */
@Component("visibilityLayer-entity-projector")
public class VisibilityLayerEntityProjector {
		
	// core constructor
	public VisibilityLayerEntityProjector(VisibilityLayerRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a VisibilityLayer
	 * 
     * @param	entity VisibilityLayer
     */
    public VisibilityLayer create( VisibilityLayer entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a VisibilityLayer
	 * 
     * @param	entity VisibilityLayer
     */
    public VisibilityLayer update( VisibilityLayer entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a VisibilityLayer
	 * 
     * @param	id		UUID
     */
    public VisibilityLayer delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    VisibilityLayer entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a DrawingOrder
     * 
     * @param	parentId	UUID
     * @param	assignment 	IntegerProxy 
     * @return	VisibilityLayer
     */
    public VisibilityLayer assignDrawingOrder( UUID parentId, IntegerProxy assignment ) {
	    LOGGER.info("assigning DrawingOrder as " + assignment.toString() );

	    VisibilityLayer parentEntity = repository.findById( parentId ).get();
	    assignment = IntegerProxyProjector.find(assignment.getIntegerProxyId());
	    
	    // ------------------------------------------
		// assign the DrawingOrder to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDrawingOrder( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the DrawingOrder
	 * 
	 * @param	parentId		UUID
	 * @return	VisibilityLayer
	 */
	public VisibilityLayer unAssignDrawingOrder( UUID parentId ) {
		VisibilityLayer parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning DrawingOrder on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the DrawingOrder on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDrawingOrder(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a VisibleObjects
     * 
     * @param	parentId	UUID
     * @param	assignment 	DiagramObject 
     * @return	VisibilityLayer
     */
    public VisibilityLayer assignVisibleObjects( UUID parentId, DiagramObject assignment ) {
	    LOGGER.info("assigning VisibleObjects as " + assignment.toString() );

	    VisibilityLayer parentEntity = repository.findById( parentId ).get();
	    assignment = DiagramObjectProjector.find(assignment.getDiagramObjectId());
	    
	    // ------------------------------------------
		// assign the VisibleObjects to the parent entity
		// ------------------------------------------ 
	    parentEntity.setVisibleObjects( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the VisibleObjects
	 * 
	 * @param	parentId		UUID
	 * @return	VisibilityLayer
	 */
	public VisibilityLayer unAssignVisibleObjects( UUID parentId ) {
		VisibilityLayer parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning VisibleObjects on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the VisibleObjects on the parent entithy
		// ------------------------------------------     
	    parentEntity.setVisibleObjects(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the VisibilityLayer via an FindVisibilityLayerQuery
     * @return 	query	FindVisibilityLayerQuery
     */
    @SuppressWarnings("unused")
    public VisibilityLayer find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a VisibilityLayer - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all VisibilityLayers
     *
     * @param	query	FindAllVisibilityLayerQuery 
     * @return 	List<VisibilityLayer> 
     */
    @SuppressWarnings("unused")
    public List<VisibilityLayer> findAll( FindAllVisibilityLayerQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all VisibilityLayer - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final VisibilityLayerRepository repository;
    @Autowired
	@Qualifier(value = "integerProxy-entity-projector")
	IntegerProxyEntityProjector IntegerProxyProjector;
    @Autowired
	@Qualifier(value = "diagramObject-entity-projector")
	DiagramObjectEntityProjector DiagramObjectProjector;

    private static final Logger LOGGER 	= Logger.getLogger(VisibilityLayerEntityProjector.class.getName());

}
