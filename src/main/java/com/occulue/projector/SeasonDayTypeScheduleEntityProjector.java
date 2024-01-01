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
 * Projector for SeasonDayTypeSchedule as outlined for the CQRS pattern.
 * 
 * Commands are handled by SeasonDayTypeScheduleAggregate
 * 
 * @author your_name_here
 *
 */
@Component("seasonDayTypeSchedule-entity-projector")
public class SeasonDayTypeScheduleEntityProjector {
		
	// core constructor
	public SeasonDayTypeScheduleEntityProjector(SeasonDayTypeScheduleRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a SeasonDayTypeSchedule
	 * 
     * @param	entity SeasonDayTypeSchedule
     */
    public SeasonDayTypeSchedule create( SeasonDayTypeSchedule entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a SeasonDayTypeSchedule
	 * 
     * @param	entity SeasonDayTypeSchedule
     */
    public SeasonDayTypeSchedule update( SeasonDayTypeSchedule entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a SeasonDayTypeSchedule
	 * 
     * @param	id		UUID
     */
    public SeasonDayTypeSchedule delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    SeasonDayTypeSchedule entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Season
     * 
     * @param	parentId	UUID
     * @param	assignment 	Season 
     * @return	SeasonDayTypeSchedule
     */
    public SeasonDayTypeSchedule assignSeason( UUID parentId, Season assignment ) {
	    LOGGER.info("assigning Season as " + assignment.toString() );

	    SeasonDayTypeSchedule parentEntity = repository.findById( parentId ).get();
	    assignment = SeasonProjector.find(assignment.getSeasonId());
	    
	    // ------------------------------------------
		// assign the Season to the parent entity
		// ------------------------------------------ 
	    parentEntity.setSeason( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Season
	 * 
	 * @param	parentId		UUID
	 * @return	SeasonDayTypeSchedule
	 */
	public SeasonDayTypeSchedule unAssignSeason( UUID parentId ) {
		SeasonDayTypeSchedule parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Season on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Season on the parent entithy
		// ------------------------------------------     
	    parentEntity.setSeason(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a DayType
     * 
     * @param	parentId	UUID
     * @param	assignment 	DayType 
     * @return	SeasonDayTypeSchedule
     */
    public SeasonDayTypeSchedule assignDayType( UUID parentId, DayType assignment ) {
	    LOGGER.info("assigning DayType as " + assignment.toString() );

	    SeasonDayTypeSchedule parentEntity = repository.findById( parentId ).get();
	    assignment = DayTypeProjector.find(assignment.getDayTypeId());
	    
	    // ------------------------------------------
		// assign the DayType to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDayType( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the DayType
	 * 
	 * @param	parentId		UUID
	 * @return	SeasonDayTypeSchedule
	 */
	public SeasonDayTypeSchedule unAssignDayType( UUID parentId ) {
		SeasonDayTypeSchedule parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning DayType on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the DayType on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDayType(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the SeasonDayTypeSchedule via an FindSeasonDayTypeScheduleQuery
     * @return 	query	FindSeasonDayTypeScheduleQuery
     */
    @SuppressWarnings("unused")
    public SeasonDayTypeSchedule find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a SeasonDayTypeSchedule - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all SeasonDayTypeSchedules
     *
     * @param	query	FindAllSeasonDayTypeScheduleQuery 
     * @return 	List<SeasonDayTypeSchedule> 
     */
    @SuppressWarnings("unused")
    public List<SeasonDayTypeSchedule> findAll( FindAllSeasonDayTypeScheduleQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all SeasonDayTypeSchedule - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final SeasonDayTypeScheduleRepository repository;
    @Autowired
	@Qualifier(value = "season-entity-projector")
	SeasonEntityProjector SeasonProjector;
    @Autowired
	@Qualifier(value = "dayType-entity-projector")
	DayTypeEntityProjector DayTypeProjector;

    private static final Logger LOGGER 	= Logger.getLogger(SeasonDayTypeScheduleEntityProjector.class.getName());

}
