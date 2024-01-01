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
 * Projector for RegularIntervalSchedule as outlined for the CQRS pattern.
 * 
 * Commands are handled by RegularIntervalScheduleAggregate
 * 
 * @author your_name_here
 *
 */
@Component("regularIntervalSchedule-entity-projector")
public class RegularIntervalScheduleEntityProjector {
		
	// core constructor
	public RegularIntervalScheduleEntityProjector(RegularIntervalScheduleRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a RegularIntervalSchedule
	 * 
     * @param	entity RegularIntervalSchedule
     */
    public RegularIntervalSchedule create( RegularIntervalSchedule entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a RegularIntervalSchedule
	 * 
     * @param	entity RegularIntervalSchedule
     */
    public RegularIntervalSchedule update( RegularIntervalSchedule entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a RegularIntervalSchedule
	 * 
     * @param	id		UUID
     */
    public RegularIntervalSchedule delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    RegularIntervalSchedule entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a EndTime
     * 
     * @param	parentId	UUID
     * @param	assignment 	DateTime 
     * @return	RegularIntervalSchedule
     */
    public RegularIntervalSchedule assignEndTime( UUID parentId, DateTime assignment ) {
	    LOGGER.info("assigning EndTime as " + assignment.toString() );

	    RegularIntervalSchedule parentEntity = repository.findById( parentId ).get();
	    assignment = DateTimeProjector.find(assignment.getDateTimeId());
	    
	    // ------------------------------------------
		// assign the EndTime to the parent entity
		// ------------------------------------------ 
	    parentEntity.setEndTime( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the EndTime
	 * 
	 * @param	parentId		UUID
	 * @return	RegularIntervalSchedule
	 */
	public RegularIntervalSchedule unAssignEndTime( UUID parentId ) {
		RegularIntervalSchedule parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning EndTime on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the EndTime on the parent entithy
		// ------------------------------------------     
	    parentEntity.setEndTime(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a TimeStep
     * 
     * @param	parentId	UUID
     * @param	assignment 	Seconds 
     * @return	RegularIntervalSchedule
     */
    public RegularIntervalSchedule assignTimeStep( UUID parentId, Seconds assignment ) {
	    LOGGER.info("assigning TimeStep as " + assignment.toString() );

	    RegularIntervalSchedule parentEntity = repository.findById( parentId ).get();
	    assignment = SecondsProjector.find(assignment.getSecondsId());
	    
	    // ------------------------------------------
		// assign the TimeStep to the parent entity
		// ------------------------------------------ 
	    parentEntity.setTimeStep( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the TimeStep
	 * 
	 * @param	parentId		UUID
	 * @return	RegularIntervalSchedule
	 */
	public RegularIntervalSchedule unAssignTimeStep( UUID parentId ) {
		RegularIntervalSchedule parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning TimeStep on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the TimeStep on the parent entithy
		// ------------------------------------------     
	    parentEntity.setTimeStep(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the RegularIntervalSchedule via an FindRegularIntervalScheduleQuery
     * @return 	query	FindRegularIntervalScheduleQuery
     */
    @SuppressWarnings("unused")
    public RegularIntervalSchedule find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a RegularIntervalSchedule - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all RegularIntervalSchedules
     *
     * @param	query	FindAllRegularIntervalScheduleQuery 
     * @return 	List<RegularIntervalSchedule> 
     */
    @SuppressWarnings("unused")
    public List<RegularIntervalSchedule> findAll( FindAllRegularIntervalScheduleQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all RegularIntervalSchedule - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final RegularIntervalScheduleRepository repository;
    @Autowired
	@Qualifier(value = "dateTime-entity-projector")
	DateTimeEntityProjector DateTimeProjector;
    @Autowired
	@Qualifier(value = "seconds-entity-projector")
	SecondsEntityProjector SecondsProjector;
    @Autowired
	@Qualifier(value = "regularTimePoint-entity-projector")
	RegularTimePointEntityProjector RegularTimePointProjector;

    private static final Logger LOGGER 	= Logger.getLogger(RegularIntervalScheduleEntityProjector.class.getName());

}
