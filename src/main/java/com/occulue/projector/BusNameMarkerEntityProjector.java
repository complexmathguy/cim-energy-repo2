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
 * Projector for BusNameMarker as outlined for the CQRS pattern.
 * 
 * Commands are handled by BusNameMarkerAggregate
 * 
 * @author your_name_here
 *
 */
@Component("busNameMarker-entity-projector")
public class BusNameMarkerEntityProjector {
		
	// core constructor
	public BusNameMarkerEntityProjector(BusNameMarkerRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a BusNameMarker
	 * 
     * @param	entity BusNameMarker
     */
    public BusNameMarker create( BusNameMarker entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a BusNameMarker
	 * 
     * @param	entity BusNameMarker
     */
    public BusNameMarker update( BusNameMarker entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a BusNameMarker
	 * 
     * @param	id		UUID
     */
    public BusNameMarker delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    BusNameMarker entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Priority
     * 
     * @param	parentId	UUID
     * @param	assignment 	IntegerProxy 
     * @return	BusNameMarker
     */
    public BusNameMarker assignPriority( UUID parentId, IntegerProxy assignment ) {
	    LOGGER.info("assigning Priority as " + assignment.toString() );

	    BusNameMarker parentEntity = repository.findById( parentId ).get();
	    assignment = IntegerProxyProjector.find(assignment.getIntegerProxyId());
	    
	    // ------------------------------------------
		// assign the Priority to the parent entity
		// ------------------------------------------ 
	    parentEntity.setPriority( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Priority
	 * 
	 * @param	parentId		UUID
	 * @return	BusNameMarker
	 */
	public BusNameMarker unAssignPriority( UUID parentId ) {
		BusNameMarker parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Priority on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Priority on the parent entithy
		// ------------------------------------------     
	    parentEntity.setPriority(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a ReportingGroup
     * 
     * @param	parentId	UUID
     * @param	assignment 	ReportingGroup 
     * @return	BusNameMarker
     */
    public BusNameMarker assignReportingGroup( UUID parentId, ReportingGroup assignment ) {
	    LOGGER.info("assigning ReportingGroup as " + assignment.toString() );

	    BusNameMarker parentEntity = repository.findById( parentId ).get();
	    assignment = ReportingGroupProjector.find(assignment.getReportingGroupId());
	    
	    // ------------------------------------------
		// assign the ReportingGroup to the parent entity
		// ------------------------------------------ 
	    parentEntity.setReportingGroup( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ReportingGroup
	 * 
	 * @param	parentId		UUID
	 * @return	BusNameMarker
	 */
	public BusNameMarker unAssignReportingGroup( UUID parentId ) {
		BusNameMarker parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ReportingGroup on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ReportingGroup on the parent entithy
		// ------------------------------------------     
	    parentEntity.setReportingGroup(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the BusNameMarker via an FindBusNameMarkerQuery
     * @return 	query	FindBusNameMarkerQuery
     */
    @SuppressWarnings("unused")
    public BusNameMarker find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a BusNameMarker - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all BusNameMarkers
     *
     * @param	query	FindAllBusNameMarkerQuery 
     * @return 	List<BusNameMarker> 
     */
    @SuppressWarnings("unused")
    public List<BusNameMarker> findAll( FindAllBusNameMarkerQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all BusNameMarker - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final BusNameMarkerRepository repository;
    @Autowired
	@Qualifier(value = "integerProxy-entity-projector")
	IntegerProxyEntityProjector IntegerProxyProjector;
    @Autowired
	@Qualifier(value = "reportingGroup-entity-projector")
	ReportingGroupEntityProjector ReportingGroupProjector;
    @Autowired
	@Qualifier(value = "aCDCTerminal-entity-projector")
	ACDCTerminalEntityProjector ACDCTerminalProjector;

    private static final Logger LOGGER 	= Logger.getLogger(BusNameMarkerEntityProjector.class.getName());

}
