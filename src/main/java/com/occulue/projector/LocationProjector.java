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

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.repository.*;

/**
 * Projector for Location as outlined for the CQRS pattern.  All event handling and query handling related to Location are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * Commands are handled by LocationAggregate
 * 
 * @author your_name_here
 *
 */
//@ProcessingGroup("location")
@Component("location-projector")
public class LocationProjector extends LocationEntityProjector {
		
	// core constructor
	public LocationProjector(LocationRepository repository, QueryUpdateEmitter queryUpdateEmitter ) {
        super(repository);
        this.queryUpdateEmitter = queryUpdateEmitter;
    }	

	/*
     * @param	event CreateLocationEvent
     */
    @EventHandler( payloadType=CreateLocationEvent.class )
    public void handle( CreateLocationEvent event) {
	    LOGGER.info("handling CreateLocationEvent - " + event );
	    Location entity = new Location();
        entity.setLocationId( event.getLocationId() );
	    
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    create(entity);
        
        // ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllLocation( entity );
    }

    /*
     * @param	event UpdateLocationEvent
     */
    @EventHandler( payloadType=UpdateLocationEvent.class )
    public void handle( UpdateLocationEvent event) {
    	LOGGER.info("handling UpdateLocationEvent - " + event );
    	
	    Location entity = new Location();
        entity.setLocationId( event.getLocationId() );
        entity.setPowerSystemResources( event.getPowerSystemResources() );
        entity.setCoordinateSystem( event.getCoordinateSystem() );
 
    	// ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		update(entity);

    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindLocation( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllLocation( entity );        
    }
    
    /*
     * @param	event DeleteLocationEvent
     */
    @EventHandler( payloadType=DeleteLocationEvent.class )
    public void handle( DeleteLocationEvent event) {
    	LOGGER.info("handling DeleteLocationEvent - " + event );
    	
    	// ------------------------------------------
    	// delete delegation
    	// ------------------------------------------
    	Location entity = delete( event.getLocationId() );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllLocation( entity );

    }    

    /*
     * @param	event AssignPowerSystemResourcesToLocationEvent
     */
    @EventHandler( payloadType=AssignPowerSystemResourcesToLocationEvent.class)
    public void handle( AssignPowerSystemResourcesToLocationEvent event) {
	    LOGGER.info("handling AssignPowerSystemResourcesToLocationEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    Location entity = assignPowerSystemResources( event.getLocationId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindLocation( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllLocation( entity );
    }
    

	/*
	 * @param	event UnAssignPowerSystemResourcesFromLocationEvent
	 */
	@EventHandler( payloadType=UnAssignPowerSystemResourcesFromLocationEvent.class)
	public void handle( UnAssignPowerSystemResourcesFromLocationEvent event) {
	    LOGGER.info("handling UnAssignPowerSystemResourcesFromLocationEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    Location entity = unAssignPowerSystemResources( event.getLocationId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindLocation( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllLocation( entity );
	}

    /*
     * @param	event AssignCoordinateSystemToLocationEvent
     */
    @EventHandler( payloadType=AssignCoordinateSystemToLocationEvent.class)
    public void handle( AssignCoordinateSystemToLocationEvent event) {
	    LOGGER.info("handling AssignCoordinateSystemToLocationEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    Location entity = assignCoordinateSystem( event.getLocationId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindLocation( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllLocation( entity );
    }
    

	/*
	 * @param	event UnAssignCoordinateSystemFromLocationEvent
	 */
	@EventHandler( payloadType=UnAssignCoordinateSystemFromLocationEvent.class)
	public void handle( UnAssignCoordinateSystemFromLocationEvent event) {
	    LOGGER.info("handling UnAssignCoordinateSystemFromLocationEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    Location entity = unAssignCoordinateSystem( event.getLocationId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindLocation( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllLocation( entity );
	}




    /**
     * Method to retrieve the Location via an LocationPrimaryKey.
     * @param 	id Long
     * @return 	Location
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public Location handle( FindLocationQuery query ) 
    throws ProcessingException, IllegalArgumentException {
    	return find( query.getFilter().getLocationId() );
    }
    
    /**
     * Method to retrieve a collection of all Locations
     *
     * @param	query	FindAllLocationQuery 
     * @return 	List<Location> 
     * @exception ProcessingException Thrown if any problems
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public List<Location> handle( FindAllLocationQuery query ) throws ProcessingException {
    	return findAll( query );
    }


	/**
	 * emit to subscription queries of type FindLocation, 
	 * but only if the id matches
	 * 
	 * @param		entity	Location
	 */
	protected void emitFindLocation( Location entity ) {
		LOGGER.info("handling emitFindLocation" );
		
	    queryUpdateEmitter.emit(FindLocationQuery.class,
	                            query -> query.getFilter().getLocationId().equals(entity.getLocationId()),
	                            entity);
	}
	
	/**
	 * unconditionally emit to subscription queries of type FindAllLocation
	 * 
	 * @param		entity	Location
	 */
	protected void emitFindAllLocation( Location entity ) {
		LOGGER.info("handling emitFindAllLocation" );
		
	    queryUpdateEmitter.emit(FindAllLocationQuery.class,
	                            query -> true,
	                            entity);
	}


	//--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
	private final QueryUpdateEmitter queryUpdateEmitter;
    private static final Logger LOGGER 	= Logger.getLogger(LocationProjector.class.getName());

}
