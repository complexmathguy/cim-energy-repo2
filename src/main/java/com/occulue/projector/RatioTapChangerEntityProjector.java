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
 * Projector for RatioTapChanger as outlined for the CQRS pattern.
 * 
 * Commands are handled by RatioTapChangerAggregate
 * 
 * @author your_name_here
 *
 */
@Component("ratioTapChanger-entity-projector")
public class RatioTapChangerEntityProjector {
		
	// core constructor
	public RatioTapChangerEntityProjector(RatioTapChangerRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a RatioTapChanger
	 * 
     * @param	entity RatioTapChanger
     */
    public RatioTapChanger create( RatioTapChanger entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a RatioTapChanger
	 * 
     * @param	entity RatioTapChanger
     */
    public RatioTapChanger update( RatioTapChanger entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a RatioTapChanger
	 * 
     * @param	id		UUID
     */
    public RatioTapChanger delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    RatioTapChanger entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a StepVoltageIncrement
     * 
     * @param	parentId	UUID
     * @param	assignment 	PerCent 
     * @return	RatioTapChanger
     */
    public RatioTapChanger assignStepVoltageIncrement( UUID parentId, PerCent assignment ) {
	    LOGGER.info("assigning StepVoltageIncrement as " + assignment.toString() );

	    RatioTapChanger parentEntity = repository.findById( parentId ).get();
	    assignment = PerCentProjector.find(assignment.getPerCentId());
	    
	    // ------------------------------------------
		// assign the StepVoltageIncrement to the parent entity
		// ------------------------------------------ 
	    parentEntity.setStepVoltageIncrement( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the StepVoltageIncrement
	 * 
	 * @param	parentId		UUID
	 * @return	RatioTapChanger
	 */
	public RatioTapChanger unAssignStepVoltageIncrement( UUID parentId ) {
		RatioTapChanger parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning StepVoltageIncrement on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the StepVoltageIncrement on the parent entithy
		// ------------------------------------------     
	    parentEntity.setStepVoltageIncrement(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a TransformerEnd
     * 
     * @param	parentId	UUID
     * @param	assignment 	TransformerEnd 
     * @return	RatioTapChanger
     */
    public RatioTapChanger assignTransformerEnd( UUID parentId, TransformerEnd assignment ) {
	    LOGGER.info("assigning TransformerEnd as " + assignment.toString() );

	    RatioTapChanger parentEntity = repository.findById( parentId ).get();
	    assignment = TransformerEndProjector.find(assignment.getTransformerEndId());
	    
	    // ------------------------------------------
		// assign the TransformerEnd to the parent entity
		// ------------------------------------------ 
	    parentEntity.setTransformerEnd( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the TransformerEnd
	 * 
	 * @param	parentId		UUID
	 * @return	RatioTapChanger
	 */
	public RatioTapChanger unAssignTransformerEnd( UUID parentId ) {
		RatioTapChanger parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning TransformerEnd on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the TransformerEnd on the parent entithy
		// ------------------------------------------     
	    parentEntity.setTransformerEnd(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a RatioTapChangerTable
     * 
     * @param	parentId	UUID
     * @param	assignment 	RatioTapChangerTable 
     * @return	RatioTapChanger
     */
    public RatioTapChanger assignRatioTapChangerTable( UUID parentId, RatioTapChangerTable assignment ) {
	    LOGGER.info("assigning RatioTapChangerTable as " + assignment.toString() );

	    RatioTapChanger parentEntity = repository.findById( parentId ).get();
	    assignment = RatioTapChangerTableProjector.find(assignment.getRatioTapChangerTableId());
	    
	    // ------------------------------------------
		// assign the RatioTapChangerTable to the parent entity
		// ------------------------------------------ 
	    parentEntity.setRatioTapChangerTable( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the RatioTapChangerTable
	 * 
	 * @param	parentId		UUID
	 * @return	RatioTapChanger
	 */
	public RatioTapChanger unAssignRatioTapChangerTable( UUID parentId ) {
		RatioTapChanger parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning RatioTapChangerTable on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the RatioTapChangerTable on the parent entithy
		// ------------------------------------------     
	    parentEntity.setRatioTapChangerTable(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the RatioTapChanger via an FindRatioTapChangerQuery
     * @return 	query	FindRatioTapChangerQuery
     */
    @SuppressWarnings("unused")
    public RatioTapChanger find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a RatioTapChanger - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all RatioTapChangers
     *
     * @param	query	FindAllRatioTapChangerQuery 
     * @return 	List<RatioTapChanger> 
     */
    @SuppressWarnings("unused")
    public List<RatioTapChanger> findAll( FindAllRatioTapChangerQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all RatioTapChanger - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final RatioTapChangerRepository repository;
    @Autowired
	@Qualifier(value = "perCent-entity-projector")
	PerCentEntityProjector PerCentProjector;
    @Autowired
	@Qualifier(value = "transformerEnd-entity-projector")
	TransformerEndEntityProjector TransformerEndProjector;
    @Autowired
	@Qualifier(value = "ratioTapChangerTable-entity-projector")
	RatioTapChangerTableEntityProjector RatioTapChangerTableProjector;

    private static final Logger LOGGER 	= Logger.getLogger(RatioTapChangerEntityProjector.class.getName());

}
