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
 * Projector for GenICompensationForGenJ as outlined for the CQRS pattern.
 * 
 * Commands are handled by GenICompensationForGenJAggregate
 * 
 * @author your_name_here
 *
 */
@Component("genICompensationForGenJ-entity-projector")
public class GenICompensationForGenJEntityProjector {
		
	// core constructor
	public GenICompensationForGenJEntityProjector(GenICompensationForGenJRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a GenICompensationForGenJ
	 * 
     * @param	entity GenICompensationForGenJ
     */
    public GenICompensationForGenJ create( GenICompensationForGenJ entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a GenICompensationForGenJ
	 * 
     * @param	entity GenICompensationForGenJ
     */
    public GenICompensationForGenJ update( GenICompensationForGenJ entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a GenICompensationForGenJ
	 * 
     * @param	id		UUID
     */
    public GenICompensationForGenJ delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    GenICompensationForGenJ entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Rcij
     * 
     * @param	parentId	UUID
     * @param	assignment 	PU 
     * @return	GenICompensationForGenJ
     */
    public GenICompensationForGenJ assignRcij( UUID parentId, PU assignment ) {
	    LOGGER.info("assigning Rcij as " + assignment.toString() );

	    GenICompensationForGenJ parentEntity = repository.findById( parentId ).get();
	    assignment = PUProjector.find(assignment.getPUId());
	    
	    // ------------------------------------------
		// assign the Rcij to the parent entity
		// ------------------------------------------ 
	    parentEntity.setRcij( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Rcij
	 * 
	 * @param	parentId		UUID
	 * @return	GenICompensationForGenJ
	 */
	public GenICompensationForGenJ unAssignRcij( UUID parentId ) {
		GenICompensationForGenJ parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Rcij on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Rcij on the parent entithy
		// ------------------------------------------     
	    parentEntity.setRcij(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Xcij
     * 
     * @param	parentId	UUID
     * @param	assignment 	PU 
     * @return	GenICompensationForGenJ
     */
    public GenICompensationForGenJ assignXcij( UUID parentId, PU assignment ) {
	    LOGGER.info("assigning Xcij as " + assignment.toString() );

	    GenICompensationForGenJ parentEntity = repository.findById( parentId ).get();
	    assignment = PUProjector.find(assignment.getPUId());
	    
	    // ------------------------------------------
		// assign the Xcij to the parent entity
		// ------------------------------------------ 
	    parentEntity.setXcij( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Xcij
	 * 
	 * @param	parentId		UUID
	 * @return	GenICompensationForGenJ
	 */
	public GenICompensationForGenJ unAssignXcij( UUID parentId ) {
		GenICompensationForGenJ parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Xcij on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Xcij on the parent entithy
		// ------------------------------------------     
	    parentEntity.setXcij(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a SynchronousMachineDynamics
     * 
     * @param	parentId	UUID
     * @param	assignment 	SynchronousMachineDynamics 
     * @return	GenICompensationForGenJ
     */
    public GenICompensationForGenJ assignSynchronousMachineDynamics( UUID parentId, SynchronousMachineDynamics assignment ) {
	    LOGGER.info("assigning SynchronousMachineDynamics as " + assignment.toString() );

	    GenICompensationForGenJ parentEntity = repository.findById( parentId ).get();
	    assignment = SynchronousMachineDynamicsProjector.find(assignment.getSynchronousMachineDynamicsId());
	    
	    // ------------------------------------------
		// assign the SynchronousMachineDynamics to the parent entity
		// ------------------------------------------ 
	    parentEntity.setSynchronousMachineDynamics( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the SynchronousMachineDynamics
	 * 
	 * @param	parentId		UUID
	 * @return	GenICompensationForGenJ
	 */
	public GenICompensationForGenJ unAssignSynchronousMachineDynamics( UUID parentId ) {
		GenICompensationForGenJ parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning SynchronousMachineDynamics on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the SynchronousMachineDynamics on the parent entithy
		// ------------------------------------------     
	    parentEntity.setSynchronousMachineDynamics(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a VcompIEEEType2
     * 
     * @param	parentId	UUID
     * @param	assignment 	VCompIEEEType2 
     * @return	GenICompensationForGenJ
     */
    public GenICompensationForGenJ assignVcompIEEEType2( UUID parentId, VCompIEEEType2 assignment ) {
	    LOGGER.info("assigning VcompIEEEType2 as " + assignment.toString() );

	    GenICompensationForGenJ parentEntity = repository.findById( parentId ).get();
	    assignment = VCompIEEEType2Projector.find(assignment.getVCompIEEEType2Id());
	    
	    // ------------------------------------------
		// assign the VcompIEEEType2 to the parent entity
		// ------------------------------------------ 
	    parentEntity.setVcompIEEEType2( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the VcompIEEEType2
	 * 
	 * @param	parentId		UUID
	 * @return	GenICompensationForGenJ
	 */
	public GenICompensationForGenJ unAssignVcompIEEEType2( UUID parentId ) {
		GenICompensationForGenJ parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning VcompIEEEType2 on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the VcompIEEEType2 on the parent entithy
		// ------------------------------------------     
	    parentEntity.setVcompIEEEType2(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the GenICompensationForGenJ via an FindGenICompensationForGenJQuery
     * @return 	query	FindGenICompensationForGenJQuery
     */
    @SuppressWarnings("unused")
    public GenICompensationForGenJ find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a GenICompensationForGenJ - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all GenICompensationForGenJs
     *
     * @param	query	FindAllGenICompensationForGenJQuery 
     * @return 	List<GenICompensationForGenJ> 
     */
    @SuppressWarnings("unused")
    public List<GenICompensationForGenJ> findAll( FindAllGenICompensationForGenJQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all GenICompensationForGenJ - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final GenICompensationForGenJRepository repository;
    @Autowired
	@Qualifier(value = "pU-entity-projector")
	PUEntityProjector PUProjector;
    @Autowired
	@Qualifier(value = "synchronousMachineDynamics-entity-projector")
	SynchronousMachineDynamicsEntityProjector SynchronousMachineDynamicsProjector;
    @Autowired
	@Qualifier(value = "vCompIEEEType2-entity-projector")
	VCompIEEEType2EntityProjector VCompIEEEType2Projector;

    private static final Logger LOGGER 	= Logger.getLogger(GenICompensationForGenJEntityProjector.class.getName());

}
