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
 * Projector for StringProxy as outlined for the CQRS pattern.
 * 
 * Commands are handled by StringProxyAggregate
 * 
 * @author your_name_here
 *
 */
@Component("stringProxy-entity-projector")
public class StringProxyEntityProjector {
		
	// core constructor
	public StringProxyEntityProjector(StringProxyRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a StringProxy
	 * 
     * @param	entity StringProxy
     */
    public StringProxy create( StringProxy entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a StringProxy
	 * 
     * @param	entity StringProxy
     */
    public StringProxy update( StringProxy entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a StringProxy
	 * 
     * @param	id		UUID
     */
    public StringProxy delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    StringProxy entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    




    /**
     * Method to retrieve the StringProxy via an FindStringProxyQuery
     * @return 	query	FindStringProxyQuery
     */
    @SuppressWarnings("unused")
    public StringProxy find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a StringProxy - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all StringProxys
     *
     * @param	query	FindAllStringProxyQuery 
     * @return 	List<StringProxy> 
     */
    @SuppressWarnings("unused")
    public List<StringProxy> findAll( FindAllStringProxyQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all StringProxy - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final StringProxyRepository repository;
    @Autowired
	@Qualifier(value = "boundaryExtensions-entity-projector")
	BoundaryExtensionsEntityProjector BoundaryExtensionsProjector;
    @Autowired
	@Qualifier(value = "eNTSOEIdentifiedObject-entity-projector")
	ENTSOEIdentifiedObjectEntityProjector ENTSOEIdentifiedObjectProjector;
    @Autowired
	@Qualifier(value = "extensionVersion-entity-projector")
	ExtensionVersionEntityProjector ExtensionVersionProjector;
    @Autowired
	@Qualifier(value = "domainVersion-entity-projector")
	DomainVersionEntityProjector DomainVersionProjector;
    @Autowired
	@Qualifier(value = "equipmentBoundaryVersion-entity-projector")
	EquipmentBoundaryVersionEntityProjector EquipmentBoundaryVersionProjector;
    @Autowired
	@Qualifier(value = "identifiedObject-entity-projector")
	IdentifiedObjectEntityProjector IdentifiedObjectProjector;
    @Autowired
	@Qualifier(value = "topologyBoundaryVersion-entity-projector")
	TopologyBoundaryVersionEntityProjector TopologyBoundaryVersionProjector;
    @Autowired
	@Qualifier(value = "equipmentVersion-entity-projector")
	EquipmentVersionEntityProjector EquipmentVersionProjector;
    @Autowired
	@Qualifier(value = "control-entity-projector")
	ControlEntityProjector ControlProjector;
    @Autowired
	@Qualifier(value = "measurement-entity-projector")
	MeasurementEntityProjector MeasurementProjector;
    @Autowired
	@Qualifier(value = "stringMeasurementValue-entity-projector")
	StringMeasurementValueEntityProjector StringMeasurementValueProjector;
    @Autowired
	@Qualifier(value = "coordinateSystem-entity-projector")
	CoordinateSystemEntityProjector CoordinateSystemProjector;
    @Autowired
	@Qualifier(value = "positionPoint-entity-projector")
	PositionPointEntityProjector PositionPointProjector;
    @Autowired
	@Qualifier(value = "textDiagramObject-entity-projector")
	TextDiagramObjectEntityProjector TextDiagramObjectProjector;

    private static final Logger LOGGER 	= Logger.getLogger(StringProxyEntityProjector.class.getName());

}
