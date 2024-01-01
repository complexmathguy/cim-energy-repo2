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
 * Projector for VoltageLevel as outlined for the CQRS pattern.  All event handling and query handling related to VoltageLevel are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * Commands are handled by VoltageLevelAggregate
 * 
 * @author your_name_here
 *
 */
//@ProcessingGroup("voltageLevel")
@Component("voltageLevel-projector")
public class VoltageLevelProjector extends VoltageLevelEntityProjector {
		
	// core constructor
	public VoltageLevelProjector(VoltageLevelRepository repository, QueryUpdateEmitter queryUpdateEmitter ) {
        super(repository);
        this.queryUpdateEmitter = queryUpdateEmitter;
    }	

	/*
     * @param	event CreateVoltageLevelEvent
     */
    @EventHandler( payloadType=CreateVoltageLevelEvent.class )
    public void handle( CreateVoltageLevelEvent event) {
	    LOGGER.info("handling CreateVoltageLevelEvent - " + event );
	    VoltageLevel entity = new VoltageLevel();
        entity.setVoltageLevelId( event.getVoltageLevelId() );
	    
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    create(entity);
        
        // ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllVoltageLevel( entity );
    }

    /*
     * @param	event UpdateVoltageLevelEvent
     */
    @EventHandler( payloadType=UpdateVoltageLevelEvent.class )
    public void handle( UpdateVoltageLevelEvent event) {
    	LOGGER.info("handling UpdateVoltageLevelEvent - " + event );
    	
	    VoltageLevel entity = new VoltageLevel();
        entity.setVoltageLevelId( event.getVoltageLevelId() );
        entity.setHighVoltageLimit( event.getHighVoltageLimit() );
        entity.setLowVoltageLimit( event.getLowVoltageLimit() );
        entity.setBaseVoltage( event.getBaseVoltage() );
        entity.setSubstation( event.getSubstation() );
 
    	// ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		update(entity);

    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindVoltageLevel( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllVoltageLevel( entity );        
    }
    
    /*
     * @param	event DeleteVoltageLevelEvent
     */
    @EventHandler( payloadType=DeleteVoltageLevelEvent.class )
    public void handle( DeleteVoltageLevelEvent event) {
    	LOGGER.info("handling DeleteVoltageLevelEvent - " + event );
    	
    	// ------------------------------------------
    	// delete delegation
    	// ------------------------------------------
    	VoltageLevel entity = delete( event.getVoltageLevelId() );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllVoltageLevel( entity );

    }    

    /*
     * @param	event AssignHighVoltageLimitToVoltageLevelEvent
     */
    @EventHandler( payloadType=AssignHighVoltageLimitToVoltageLevelEvent.class)
    public void handle( AssignHighVoltageLimitToVoltageLevelEvent event) {
	    LOGGER.info("handling AssignHighVoltageLimitToVoltageLevelEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    VoltageLevel entity = assignHighVoltageLimit( event.getVoltageLevelId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindVoltageLevel( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllVoltageLevel( entity );
    }
    

	/*
	 * @param	event UnAssignHighVoltageLimitFromVoltageLevelEvent
	 */
	@EventHandler( payloadType=UnAssignHighVoltageLimitFromVoltageLevelEvent.class)
	public void handle( UnAssignHighVoltageLimitFromVoltageLevelEvent event) {
	    LOGGER.info("handling UnAssignHighVoltageLimitFromVoltageLevelEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    VoltageLevel entity = unAssignHighVoltageLimit( event.getVoltageLevelId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindVoltageLevel( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllVoltageLevel( entity );
	}

    /*
     * @param	event AssignLowVoltageLimitToVoltageLevelEvent
     */
    @EventHandler( payloadType=AssignLowVoltageLimitToVoltageLevelEvent.class)
    public void handle( AssignLowVoltageLimitToVoltageLevelEvent event) {
	    LOGGER.info("handling AssignLowVoltageLimitToVoltageLevelEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    VoltageLevel entity = assignLowVoltageLimit( event.getVoltageLevelId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindVoltageLevel( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllVoltageLevel( entity );
    }
    

	/*
	 * @param	event UnAssignLowVoltageLimitFromVoltageLevelEvent
	 */
	@EventHandler( payloadType=UnAssignLowVoltageLimitFromVoltageLevelEvent.class)
	public void handle( UnAssignLowVoltageLimitFromVoltageLevelEvent event) {
	    LOGGER.info("handling UnAssignLowVoltageLimitFromVoltageLevelEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    VoltageLevel entity = unAssignLowVoltageLimit( event.getVoltageLevelId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindVoltageLevel( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllVoltageLevel( entity );
	}

    /*
     * @param	event AssignBaseVoltageToVoltageLevelEvent
     */
    @EventHandler( payloadType=AssignBaseVoltageToVoltageLevelEvent.class)
    public void handle( AssignBaseVoltageToVoltageLevelEvent event) {
	    LOGGER.info("handling AssignBaseVoltageToVoltageLevelEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    VoltageLevel entity = assignBaseVoltage( event.getVoltageLevelId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindVoltageLevel( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllVoltageLevel( entity );
    }
    

	/*
	 * @param	event UnAssignBaseVoltageFromVoltageLevelEvent
	 */
	@EventHandler( payloadType=UnAssignBaseVoltageFromVoltageLevelEvent.class)
	public void handle( UnAssignBaseVoltageFromVoltageLevelEvent event) {
	    LOGGER.info("handling UnAssignBaseVoltageFromVoltageLevelEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    VoltageLevel entity = unAssignBaseVoltage( event.getVoltageLevelId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindVoltageLevel( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllVoltageLevel( entity );
	}

    /*
     * @param	event AssignSubstationToVoltageLevelEvent
     */
    @EventHandler( payloadType=AssignSubstationToVoltageLevelEvent.class)
    public void handle( AssignSubstationToVoltageLevelEvent event) {
	    LOGGER.info("handling AssignSubstationToVoltageLevelEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    VoltageLevel entity = assignSubstation( event.getVoltageLevelId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindVoltageLevel( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllVoltageLevel( entity );
    }
    

	/*
	 * @param	event UnAssignSubstationFromVoltageLevelEvent
	 */
	@EventHandler( payloadType=UnAssignSubstationFromVoltageLevelEvent.class)
	public void handle( UnAssignSubstationFromVoltageLevelEvent event) {
	    LOGGER.info("handling UnAssignSubstationFromVoltageLevelEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    VoltageLevel entity = unAssignSubstation( event.getVoltageLevelId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindVoltageLevel( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllVoltageLevel( entity );
	}




    /**
     * Method to retrieve the VoltageLevel via an VoltageLevelPrimaryKey.
     * @param 	id Long
     * @return 	VoltageLevel
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public VoltageLevel handle( FindVoltageLevelQuery query ) 
    throws ProcessingException, IllegalArgumentException {
    	return find( query.getFilter().getVoltageLevelId() );
    }
    
    /**
     * Method to retrieve a collection of all VoltageLevels
     *
     * @param	query	FindAllVoltageLevelQuery 
     * @return 	List<VoltageLevel> 
     * @exception ProcessingException Thrown if any problems
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public List<VoltageLevel> handle( FindAllVoltageLevelQuery query ) throws ProcessingException {
    	return findAll( query );
    }


	/**
	 * emit to subscription queries of type FindVoltageLevel, 
	 * but only if the id matches
	 * 
	 * @param		entity	VoltageLevel
	 */
	protected void emitFindVoltageLevel( VoltageLevel entity ) {
		LOGGER.info("handling emitFindVoltageLevel" );
		
	    queryUpdateEmitter.emit(FindVoltageLevelQuery.class,
	                            query -> query.getFilter().getVoltageLevelId().equals(entity.getVoltageLevelId()),
	                            entity);
	}
	
	/**
	 * unconditionally emit to subscription queries of type FindAllVoltageLevel
	 * 
	 * @param		entity	VoltageLevel
	 */
	protected void emitFindAllVoltageLevel( VoltageLevel entity ) {
		LOGGER.info("handling emitFindAllVoltageLevel" );
		
	    queryUpdateEmitter.emit(FindAllVoltageLevelQuery.class,
	                            query -> true,
	                            entity);
	}


	//--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
	private final QueryUpdateEmitter queryUpdateEmitter;
    private static final Logger LOGGER 	= Logger.getLogger(VoltageLevelProjector.class.getName());

}
