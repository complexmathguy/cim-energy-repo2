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
 * Projector for VoltageLevel as outlined for the CQRS pattern.
 * 
 * Commands are handled by VoltageLevelAggregate
 * 
 * @author your_name_here
 *
 */
@Component("voltageLevel-entity-projector")
public class VoltageLevelEntityProjector {
		
	// core constructor
	public VoltageLevelEntityProjector(VoltageLevelRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a VoltageLevel
	 * 
     * @param	entity VoltageLevel
     */
    public VoltageLevel create( VoltageLevel entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a VoltageLevel
	 * 
     * @param	entity VoltageLevel
     */
    public VoltageLevel update( VoltageLevel entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a VoltageLevel
	 * 
     * @param	id		UUID
     */
    public VoltageLevel delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    VoltageLevel entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a HighVoltageLimit
     * 
     * @param	parentId	UUID
     * @param	assignment 	Voltage 
     * @return	VoltageLevel
     */
    public VoltageLevel assignHighVoltageLimit( UUID parentId, Voltage assignment ) {
	    LOGGER.info("assigning HighVoltageLimit as " + assignment.toString() );

	    VoltageLevel parentEntity = repository.findById( parentId ).get();
	    assignment = VoltageProjector.find(assignment.getVoltageId());
	    
	    // ------------------------------------------
		// assign the HighVoltageLimit to the parent entity
		// ------------------------------------------ 
	    parentEntity.setHighVoltageLimit( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the HighVoltageLimit
	 * 
	 * @param	parentId		UUID
	 * @return	VoltageLevel
	 */
	public VoltageLevel unAssignHighVoltageLimit( UUID parentId ) {
		VoltageLevel parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning HighVoltageLimit on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the HighVoltageLimit on the parent entithy
		// ------------------------------------------     
	    parentEntity.setHighVoltageLimit(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a LowVoltageLimit
     * 
     * @param	parentId	UUID
     * @param	assignment 	Voltage 
     * @return	VoltageLevel
     */
    public VoltageLevel assignLowVoltageLimit( UUID parentId, Voltage assignment ) {
	    LOGGER.info("assigning LowVoltageLimit as " + assignment.toString() );

	    VoltageLevel parentEntity = repository.findById( parentId ).get();
	    assignment = VoltageProjector.find(assignment.getVoltageId());
	    
	    // ------------------------------------------
		// assign the LowVoltageLimit to the parent entity
		// ------------------------------------------ 
	    parentEntity.setLowVoltageLimit( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the LowVoltageLimit
	 * 
	 * @param	parentId		UUID
	 * @return	VoltageLevel
	 */
	public VoltageLevel unAssignLowVoltageLimit( UUID parentId ) {
		VoltageLevel parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning LowVoltageLimit on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the LowVoltageLimit on the parent entithy
		// ------------------------------------------     
	    parentEntity.setLowVoltageLimit(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a BaseVoltage
     * 
     * @param	parentId	UUID
     * @param	assignment 	BaseVoltage 
     * @return	VoltageLevel
     */
    public VoltageLevel assignBaseVoltage( UUID parentId, BaseVoltage assignment ) {
	    LOGGER.info("assigning BaseVoltage as " + assignment.toString() );

	    VoltageLevel parentEntity = repository.findById( parentId ).get();
	    assignment = BaseVoltageProjector.find(assignment.getBaseVoltageId());
	    
	    // ------------------------------------------
		// assign the BaseVoltage to the parent entity
		// ------------------------------------------ 
	    parentEntity.setBaseVoltage( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the BaseVoltage
	 * 
	 * @param	parentId		UUID
	 * @return	VoltageLevel
	 */
	public VoltageLevel unAssignBaseVoltage( UUID parentId ) {
		VoltageLevel parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning BaseVoltage on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the BaseVoltage on the parent entithy
		// ------------------------------------------     
	    parentEntity.setBaseVoltage(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Substation
     * 
     * @param	parentId	UUID
     * @param	assignment 	Substation 
     * @return	VoltageLevel
     */
    public VoltageLevel assignSubstation( UUID parentId, Substation assignment ) {
	    LOGGER.info("assigning Substation as " + assignment.toString() );

	    VoltageLevel parentEntity = repository.findById( parentId ).get();
	    assignment = SubstationProjector.find(assignment.getSubstationId());
	    
	    // ------------------------------------------
		// assign the Substation to the parent entity
		// ------------------------------------------ 
	    parentEntity.setSubstation( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Substation
	 * 
	 * @param	parentId		UUID
	 * @return	VoltageLevel
	 */
	public VoltageLevel unAssignSubstation( UUID parentId ) {
		VoltageLevel parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Substation on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Substation on the parent entithy
		// ------------------------------------------     
	    parentEntity.setSubstation(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the VoltageLevel via an FindVoltageLevelQuery
     * @return 	query	FindVoltageLevelQuery
     */
    @SuppressWarnings("unused")
    public VoltageLevel find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a VoltageLevel - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all VoltageLevels
     *
     * @param	query	FindAllVoltageLevelQuery 
     * @return 	List<VoltageLevel> 
     */
    @SuppressWarnings("unused")
    public List<VoltageLevel> findAll( FindAllVoltageLevelQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all VoltageLevel - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final VoltageLevelRepository repository;
    @Autowired
	@Qualifier(value = "bay-entity-projector")
	BayEntityProjector BayProjector;
    @Autowired
	@Qualifier(value = "voltage-entity-projector")
	VoltageEntityProjector VoltageProjector;
    @Autowired
	@Qualifier(value = "baseVoltage-entity-projector")
	BaseVoltageEntityProjector BaseVoltageProjector;
    @Autowired
	@Qualifier(value = "substation-entity-projector")
	SubstationEntityProjector SubstationProjector;

    private static final Logger LOGGER 	= Logger.getLogger(VoltageLevelEntityProjector.class.getName());

}
