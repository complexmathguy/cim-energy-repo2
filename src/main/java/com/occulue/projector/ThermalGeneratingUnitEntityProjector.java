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
 * Projector for ThermalGeneratingUnit as outlined for the CQRS pattern.
 * 
 * Commands are handled by ThermalGeneratingUnitAggregate
 * 
 * @author your_name_here
 *
 */
@Component("thermalGeneratingUnit-entity-projector")
public class ThermalGeneratingUnitEntityProjector {
		
	// core constructor
	public ThermalGeneratingUnitEntityProjector(ThermalGeneratingUnitRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a ThermalGeneratingUnit
	 * 
     * @param	entity ThermalGeneratingUnit
     */
    public ThermalGeneratingUnit create( ThermalGeneratingUnit entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a ThermalGeneratingUnit
	 * 
     * @param	entity ThermalGeneratingUnit
     */
    public ThermalGeneratingUnit update( ThermalGeneratingUnit entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a ThermalGeneratingUnit
	 * 
     * @param	id		UUID
     */
    public ThermalGeneratingUnit delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    ThermalGeneratingUnit entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    




    /**
     * Method to retrieve the ThermalGeneratingUnit via an FindThermalGeneratingUnitQuery
     * @return 	query	FindThermalGeneratingUnitQuery
     */
    @SuppressWarnings("unused")
    public ThermalGeneratingUnit find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a ThermalGeneratingUnit - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all ThermalGeneratingUnits
     *
     * @param	query	FindAllThermalGeneratingUnitQuery 
     * @return 	List<ThermalGeneratingUnit> 
     */
    @SuppressWarnings("unused")
    public List<ThermalGeneratingUnit> findAll( FindAllThermalGeneratingUnitQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all ThermalGeneratingUnit - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final ThermalGeneratingUnitRepository repository;
    @Autowired
	@Qualifier(value = "fossilFuel-entity-projector")
	FossilFuelEntityProjector FossilFuelProjector;

    private static final Logger LOGGER 	= Logger.getLogger(ThermalGeneratingUnitEntityProjector.class.getName());

}
