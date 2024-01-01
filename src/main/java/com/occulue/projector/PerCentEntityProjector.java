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
 * Projector for PerCent as outlined for the CQRS pattern.
 * 
 * Commands are handled by PerCentAggregate
 * 
 * @author your_name_here
 *
 */
@Component("perCent-entity-projector")
public class PerCentEntityProjector {
		
	// core constructor
	public PerCentEntityProjector(PerCentRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a PerCent
	 * 
     * @param	entity PerCent
     */
    public PerCent create( PerCent entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a PerCent
	 * 
     * @param	entity PerCent
     */
    public PerCent update( PerCent entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a PerCent
	 * 
     * @param	id		UUID
     */
    public PerCent delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    PerCent entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Value
     * 
     * @param	parentId	UUID
     * @param	assignment 	FloatProxy 
     * @return	PerCent
     */
    public PerCent assignValue( UUID parentId, FloatProxy assignment ) {
	    LOGGER.info("assigning Value as " + assignment.toString() );

	    PerCent parentEntity = repository.findById( parentId ).get();
	    assignment = FloatProxyProjector.find(assignment.getFloatProxyId());
	    
	    // ------------------------------------------
		// assign the Value to the parent entity
		// ------------------------------------------ 
	    parentEntity.setValue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Value
	 * 
	 * @param	parentId		UUID
	 * @return	PerCent
	 */
	public PerCent unAssignValue( UUID parentId ) {
		PerCent parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Value on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Value on the parent entithy
		// ------------------------------------------     
	    parentEntity.setValue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the PerCent via an FindPerCentQuery
     * @return 	query	FindPerCentQuery
     */
    @SuppressWarnings("unused")
    public PerCent find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a PerCent - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all PerCents
     *
     * @param	query	FindAllPerCentQuery 
     * @return 	List<PerCent> 
     */
    @SuppressWarnings("unused")
    public List<PerCent> findAll( FindAllPerCentQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all PerCent - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final PerCentRepository repository;
    @Autowired
	@Qualifier(value = "floatProxy-entity-projector")
	FloatProxyEntityProjector FloatProxyProjector;
    @Autowired
	@Qualifier(value = "measurementValue-entity-projector")
	MeasurementValueEntityProjector MeasurementValueProjector;
    @Autowired
	@Qualifier(value = "generatingUnit-entity-projector")
	GeneratingUnitEntityProjector GeneratingUnitProjector;
    @Autowired
	@Qualifier(value = "asynchronousMachine-entity-projector")
	AsynchronousMachineEntityProjector AsynchronousMachineProjector;
    @Autowired
	@Qualifier(value = "energyConsumer-entity-projector")
	EnergyConsumerEntityProjector EnergyConsumerProjector;
    @Autowired
	@Qualifier(value = "phaseTapChangerNonLinear-entity-projector")
	PhaseTapChangerNonLinearEntityProjector PhaseTapChangerNonLinearProjector;
    @Autowired
	@Qualifier(value = "ratioTapChanger-entity-projector")
	RatioTapChangerEntityProjector RatioTapChangerProjector;
    @Autowired
	@Qualifier(value = "synchronousMachine-entity-projector")
	SynchronousMachineEntityProjector SynchronousMachineProjector;
    @Autowired
	@Qualifier(value = "tapChangerTablePoint-entity-projector")
	TapChangerTablePointEntityProjector TapChangerTablePointProjector;

    private static final Logger LOGGER 	= Logger.getLogger(PerCentEntityProjector.class.getName());

}
