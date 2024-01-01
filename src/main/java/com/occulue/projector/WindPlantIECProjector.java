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
 * Projector for WindPlantIEC as outlined for the CQRS pattern.  All event handling and query handling related to WindPlantIEC are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * Commands are handled by WindPlantIECAggregate
 * 
 * @author your_name_here
 *
 */
//@ProcessingGroup("windPlantIEC")
@Component("windPlantIEC-projector")
public class WindPlantIECProjector extends WindPlantIECEntityProjector {
		
	// core constructor
	public WindPlantIECProjector(WindPlantIECRepository repository, QueryUpdateEmitter queryUpdateEmitter ) {
        super(repository);
        this.queryUpdateEmitter = queryUpdateEmitter;
    }	

	/*
     * @param	event CreateWindPlantIECEvent
     */
    @EventHandler( payloadType=CreateWindPlantIECEvent.class )
    public void handle( CreateWindPlantIECEvent event) {
	    LOGGER.info("handling CreateWindPlantIECEvent - " + event );
	    WindPlantIEC entity = new WindPlantIEC();
        entity.setWindPlantIECId( event.getWindPlantIECId() );
	    
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    create(entity);
        
        // ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindPlantIEC( entity );
    }

    /*
     * @param	event UpdateWindPlantIECEvent
     */
    @EventHandler( payloadType=UpdateWindPlantIECEvent.class )
    public void handle( UpdateWindPlantIECEvent event) {
    	LOGGER.info("handling UpdateWindPlantIECEvent - " + event );
    	
	    WindPlantIEC entity = new WindPlantIEC();
        entity.setWindPlantIECId( event.getWindPlantIECId() );
        entity.setWindPlantFreqPcontrolIEC( event.getWindPlantFreqPcontrolIEC() );
        entity.setWindPlantReactiveControlIEC( event.getWindPlantReactiveControlIEC() );
 
    	// ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		update(entity);

    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindWindPlantIEC( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindPlantIEC( entity );        
    }
    
    /*
     * @param	event DeleteWindPlantIECEvent
     */
    @EventHandler( payloadType=DeleteWindPlantIECEvent.class )
    public void handle( DeleteWindPlantIECEvent event) {
    	LOGGER.info("handling DeleteWindPlantIECEvent - " + event );
    	
    	// ------------------------------------------
    	// delete delegation
    	// ------------------------------------------
    	WindPlantIEC entity = delete( event.getWindPlantIECId() );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindPlantIEC( entity );

    }    

    /*
     * @param	event AssignWindPlantFreqPcontrolIECToWindPlantIECEvent
     */
    @EventHandler( payloadType=AssignWindPlantFreqPcontrolIECToWindPlantIECEvent.class)
    public void handle( AssignWindPlantFreqPcontrolIECToWindPlantIECEvent event) {
	    LOGGER.info("handling AssignWindPlantFreqPcontrolIECToWindPlantIECEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    WindPlantIEC entity = assignWindPlantFreqPcontrolIEC( event.getWindPlantIECId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindWindPlantIEC( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindPlantIEC( entity );
    }
    

	/*
	 * @param	event UnAssignWindPlantFreqPcontrolIECFromWindPlantIECEvent
	 */
	@EventHandler( payloadType=UnAssignWindPlantFreqPcontrolIECFromWindPlantIECEvent.class)
	public void handle( UnAssignWindPlantFreqPcontrolIECFromWindPlantIECEvent event) {
	    LOGGER.info("handling UnAssignWindPlantFreqPcontrolIECFromWindPlantIECEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    WindPlantIEC entity = unAssignWindPlantFreqPcontrolIEC( event.getWindPlantIECId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindWindPlantIEC( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllWindPlantIEC( entity );
	}

    /*
     * @param	event AssignWindPlantReactiveControlIECToWindPlantIECEvent
     */
    @EventHandler( payloadType=AssignWindPlantReactiveControlIECToWindPlantIECEvent.class)
    public void handle( AssignWindPlantReactiveControlIECToWindPlantIECEvent event) {
	    LOGGER.info("handling AssignWindPlantReactiveControlIECToWindPlantIECEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    WindPlantIEC entity = assignWindPlantReactiveControlIEC( event.getWindPlantIECId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindWindPlantIEC( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindPlantIEC( entity );
    }
    

	/*
	 * @param	event UnAssignWindPlantReactiveControlIECFromWindPlantIECEvent
	 */
	@EventHandler( payloadType=UnAssignWindPlantReactiveControlIECFromWindPlantIECEvent.class)
	public void handle( UnAssignWindPlantReactiveControlIECFromWindPlantIECEvent event) {
	    LOGGER.info("handling UnAssignWindPlantReactiveControlIECFromWindPlantIECEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    WindPlantIEC entity = unAssignWindPlantReactiveControlIEC( event.getWindPlantIECId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindWindPlantIEC( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllWindPlantIEC( entity );
	}




    /**
     * Method to retrieve the WindPlantIEC via an WindPlantIECPrimaryKey.
     * @param 	id Long
     * @return 	WindPlantIEC
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public WindPlantIEC handle( FindWindPlantIECQuery query ) 
    throws ProcessingException, IllegalArgumentException {
    	return find( query.getFilter().getWindPlantIECId() );
    }
    
    /**
     * Method to retrieve a collection of all WindPlantIECs
     *
     * @param	query	FindAllWindPlantIECQuery 
     * @return 	List<WindPlantIEC> 
     * @exception ProcessingException Thrown if any problems
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public List<WindPlantIEC> handle( FindAllWindPlantIECQuery query ) throws ProcessingException {
    	return findAll( query );
    }


	/**
	 * emit to subscription queries of type FindWindPlantIEC, 
	 * but only if the id matches
	 * 
	 * @param		entity	WindPlantIEC
	 */
	protected void emitFindWindPlantIEC( WindPlantIEC entity ) {
		LOGGER.info("handling emitFindWindPlantIEC" );
		
	    queryUpdateEmitter.emit(FindWindPlantIECQuery.class,
	                            query -> query.getFilter().getWindPlantIECId().equals(entity.getWindPlantIECId()),
	                            entity);
	}
	
	/**
	 * unconditionally emit to subscription queries of type FindAllWindPlantIEC
	 * 
	 * @param		entity	WindPlantIEC
	 */
	protected void emitFindAllWindPlantIEC( WindPlantIEC entity ) {
		LOGGER.info("handling emitFindAllWindPlantIEC" );
		
	    queryUpdateEmitter.emit(FindAllWindPlantIECQuery.class,
	                            query -> true,
	                            entity);
	}


	//--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
	private final QueryUpdateEmitter queryUpdateEmitter;
    private static final Logger LOGGER 	= Logger.getLogger(WindPlantIECProjector.class.getName());

}
