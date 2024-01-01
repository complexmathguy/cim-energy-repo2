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
 * Projector for Voltage as outlined for the CQRS pattern.
 * 
 * Commands are handled by VoltageAggregate
 * 
 * @author your_name_here
 *
 */
@Component("voltage-entity-projector")
public class VoltageEntityProjector {
		
	// core constructor
	public VoltageEntityProjector(VoltageRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Voltage
	 * 
     * @param	entity Voltage
     */
    public Voltage create( Voltage entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Voltage
	 * 
     * @param	entity Voltage
     */
    public Voltage update( Voltage entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Voltage
	 * 
     * @param	id		UUID
     */
    public Voltage delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Voltage entity = repository.findById(id).get();
	    
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
     * @return	Voltage
     */
    public Voltage assignValue( UUID parentId, FloatProxy assignment ) {
	    LOGGER.info("assigning Value as " + assignment.toString() );

	    Voltage parentEntity = repository.findById( parentId ).get();
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
	 * @return	Voltage
	 */
	public Voltage unAssignValue( UUID parentId ) {
		Voltage parentEntity = repository.findById(parentId).get();

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
     * Method to retrieve the Voltage via an FindVoltageQuery
     * @return 	query	FindVoltageQuery
     */
    @SuppressWarnings("unused")
    public Voltage find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Voltage - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Voltages
     *
     * @param	query	FindAllVoltageQuery 
     * @return 	List<Voltage> 
     */
    @SuppressWarnings("unused")
    public List<Voltage> findAll( FindAllVoltageQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Voltage - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final VoltageRepository repository;
    @Autowired
	@Qualifier(value = "floatProxy-entity-projector")
	FloatProxyEntityProjector FloatProxyProjector;
    @Autowired
	@Qualifier(value = "baseVoltage-entity-projector")
	BaseVoltageEntityProjector BaseVoltageProjector;
    @Autowired
	@Qualifier(value = "aCDCConverter-entity-projector")
	ACDCConverterEntityProjector ACDCConverterProjector;
    @Autowired
	@Qualifier(value = "voltageLevel-entity-projector")
	VoltageLevelEntityProjector VoltageLevelProjector;
    @Autowired
	@Qualifier(value = "petersenCoil-entity-projector")
	PetersenCoilEntityProjector PetersenCoilProjector;
    @Autowired
	@Qualifier(value = "powerTransformer-entity-projector")
	PowerTransformerEntityProjector PowerTransformerProjector;
    @Autowired
	@Qualifier(value = "powerTransformerEnd-entity-projector")
	PowerTransformerEndEntityProjector PowerTransformerEndProjector;
    @Autowired
	@Qualifier(value = "seriesCompensator-entity-projector")
	SeriesCompensatorEntityProjector SeriesCompensatorProjector;
    @Autowired
	@Qualifier(value = "shuntCompensator-entity-projector")
	ShuntCompensatorEntityProjector ShuntCompensatorProjector;
    @Autowired
	@Qualifier(value = "staticVarCompensator-entity-projector")
	StaticVarCompensatorEntityProjector StaticVarCompensatorProjector;
    @Autowired
	@Qualifier(value = "tapChanger-entity-projector")
	TapChangerEntityProjector TapChangerProjector;
    @Autowired
	@Qualifier(value = "svVoltage-entity-projector")
	SvVoltageEntityProjector SvVoltageProjector;

    private static final Logger LOGGER 	= Logger.getLogger(VoltageEntityProjector.class.getName());

}
