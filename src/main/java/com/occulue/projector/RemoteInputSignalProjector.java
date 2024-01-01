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
 * Projector for RemoteInputSignal as outlined for the CQRS pattern.  All event handling and query handling related to RemoteInputSignal are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * Commands are handled by RemoteInputSignalAggregate
 * 
 * @author your_name_here
 *
 */
//@ProcessingGroup("remoteInputSignal")
@Component("remoteInputSignal-projector")
public class RemoteInputSignalProjector extends RemoteInputSignalEntityProjector {
		
	// core constructor
	public RemoteInputSignalProjector(RemoteInputSignalRepository repository, QueryUpdateEmitter queryUpdateEmitter ) {
        super(repository);
        this.queryUpdateEmitter = queryUpdateEmitter;
    }	

	/*
     * @param	event CreateRemoteInputSignalEvent
     */
    @EventHandler( payloadType=CreateRemoteInputSignalEvent.class )
    public void handle( CreateRemoteInputSignalEvent event) {
	    LOGGER.info("handling CreateRemoteInputSignalEvent - " + event );
	    RemoteInputSignal entity = new RemoteInputSignal();
        entity.setRemoteInputSignalId( event.getRemoteInputSignalId() );
        entity.setRemoteSignalType( event.getRemoteSignalType() );
	    
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    create(entity);
        
        // ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllRemoteInputSignal( entity );
    }

    /*
     * @param	event UpdateRemoteInputSignalEvent
     */
    @EventHandler( payloadType=UpdateRemoteInputSignalEvent.class )
    public void handle( UpdateRemoteInputSignalEvent event) {
    	LOGGER.info("handling UpdateRemoteInputSignalEvent - " + event );
    	
	    RemoteInputSignal entity = new RemoteInputSignal();
        entity.setRemoteInputSignalId( event.getRemoteInputSignalId() );
        entity.setRemoteSignalType( event.getRemoteSignalType() );
        entity.setTerminal( event.getTerminal() );
        entity.setDiscontinuousExcitationControlDynamics( event.getDiscontinuousExcitationControlDynamics() );
        entity.setPowerSystemStabilizerDynamics( event.getPowerSystemStabilizerDynamics() );
        entity.setVoltageCompensatorDynamics( event.getVoltageCompensatorDynamics() );
        entity.setUnderexcitationLimiterDynamics( event.getUnderexcitationLimiterDynamics() );
        entity.setPFVArControllerType1Dynamics( event.getPFVArControllerType1Dynamics() );
 
    	// ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		update(entity);

    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindRemoteInputSignal( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllRemoteInputSignal( entity );        
    }
    
    /*
     * @param	event DeleteRemoteInputSignalEvent
     */
    @EventHandler( payloadType=DeleteRemoteInputSignalEvent.class )
    public void handle( DeleteRemoteInputSignalEvent event) {
    	LOGGER.info("handling DeleteRemoteInputSignalEvent - " + event );
    	
    	// ------------------------------------------
    	// delete delegation
    	// ------------------------------------------
    	RemoteInputSignal entity = delete( event.getRemoteInputSignalId() );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllRemoteInputSignal( entity );

    }    

    /*
     * @param	event AssignTerminalToRemoteInputSignalEvent
     */
    @EventHandler( payloadType=AssignTerminalToRemoteInputSignalEvent.class)
    public void handle( AssignTerminalToRemoteInputSignalEvent event) {
	    LOGGER.info("handling AssignTerminalToRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    RemoteInputSignal entity = assignTerminal( event.getRemoteInputSignalId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindRemoteInputSignal( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllRemoteInputSignal( entity );
    }
    

	/*
	 * @param	event UnAssignTerminalFromRemoteInputSignalEvent
	 */
	@EventHandler( payloadType=UnAssignTerminalFromRemoteInputSignalEvent.class)
	public void handle( UnAssignTerminalFromRemoteInputSignalEvent event) {
	    LOGGER.info("handling UnAssignTerminalFromRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    RemoteInputSignal entity = unAssignTerminal( event.getRemoteInputSignalId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindRemoteInputSignal( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllRemoteInputSignal( entity );
	}

    /*
     * @param	event AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalEvent
     */
    @EventHandler( payloadType=AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalEvent.class)
    public void handle( AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalEvent event) {
	    LOGGER.info("handling AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    RemoteInputSignal entity = assignDiscontinuousExcitationControlDynamics( event.getRemoteInputSignalId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindRemoteInputSignal( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllRemoteInputSignal( entity );
    }
    

	/*
	 * @param	event UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalEvent
	 */
	@EventHandler( payloadType=UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalEvent.class)
	public void handle( UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalEvent event) {
	    LOGGER.info("handling UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    RemoteInputSignal entity = unAssignDiscontinuousExcitationControlDynamics( event.getRemoteInputSignalId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindRemoteInputSignal( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllRemoteInputSignal( entity );
	}

    /*
     * @param	event AssignPowerSystemStabilizerDynamicsToRemoteInputSignalEvent
     */
    @EventHandler( payloadType=AssignPowerSystemStabilizerDynamicsToRemoteInputSignalEvent.class)
    public void handle( AssignPowerSystemStabilizerDynamicsToRemoteInputSignalEvent event) {
	    LOGGER.info("handling AssignPowerSystemStabilizerDynamicsToRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    RemoteInputSignal entity = assignPowerSystemStabilizerDynamics( event.getRemoteInputSignalId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindRemoteInputSignal( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllRemoteInputSignal( entity );
    }
    

	/*
	 * @param	event UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalEvent
	 */
	@EventHandler( payloadType=UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalEvent.class)
	public void handle( UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalEvent event) {
	    LOGGER.info("handling UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    RemoteInputSignal entity = unAssignPowerSystemStabilizerDynamics( event.getRemoteInputSignalId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindRemoteInputSignal( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllRemoteInputSignal( entity );
	}

    /*
     * @param	event AssignVoltageCompensatorDynamicsToRemoteInputSignalEvent
     */
    @EventHandler( payloadType=AssignVoltageCompensatorDynamicsToRemoteInputSignalEvent.class)
    public void handle( AssignVoltageCompensatorDynamicsToRemoteInputSignalEvent event) {
	    LOGGER.info("handling AssignVoltageCompensatorDynamicsToRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    RemoteInputSignal entity = assignVoltageCompensatorDynamics( event.getRemoteInputSignalId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindRemoteInputSignal( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllRemoteInputSignal( entity );
    }
    

	/*
	 * @param	event UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalEvent
	 */
	@EventHandler( payloadType=UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalEvent.class)
	public void handle( UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalEvent event) {
	    LOGGER.info("handling UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    RemoteInputSignal entity = unAssignVoltageCompensatorDynamics( event.getRemoteInputSignalId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindRemoteInputSignal( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllRemoteInputSignal( entity );
	}

    /*
     * @param	event AssignUnderexcitationLimiterDynamicsToRemoteInputSignalEvent
     */
    @EventHandler( payloadType=AssignUnderexcitationLimiterDynamicsToRemoteInputSignalEvent.class)
    public void handle( AssignUnderexcitationLimiterDynamicsToRemoteInputSignalEvent event) {
	    LOGGER.info("handling AssignUnderexcitationLimiterDynamicsToRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    RemoteInputSignal entity = assignUnderexcitationLimiterDynamics( event.getRemoteInputSignalId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindRemoteInputSignal( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllRemoteInputSignal( entity );
    }
    

	/*
	 * @param	event UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalEvent
	 */
	@EventHandler( payloadType=UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalEvent.class)
	public void handle( UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalEvent event) {
	    LOGGER.info("handling UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    RemoteInputSignal entity = unAssignUnderexcitationLimiterDynamics( event.getRemoteInputSignalId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindRemoteInputSignal( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllRemoteInputSignal( entity );
	}

    /*
     * @param	event AssignPFVArControllerType1DynamicsToRemoteInputSignalEvent
     */
    @EventHandler( payloadType=AssignPFVArControllerType1DynamicsToRemoteInputSignalEvent.class)
    public void handle( AssignPFVArControllerType1DynamicsToRemoteInputSignalEvent event) {
	    LOGGER.info("handling AssignPFVArControllerType1DynamicsToRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    RemoteInputSignal entity = assignPFVArControllerType1Dynamics( event.getRemoteInputSignalId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindRemoteInputSignal( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllRemoteInputSignal( entity );
    }
    

	/*
	 * @param	event UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalEvent
	 */
	@EventHandler( payloadType=UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalEvent.class)
	public void handle( UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalEvent event) {
	    LOGGER.info("handling UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    RemoteInputSignal entity = unAssignPFVArControllerType1Dynamics( event.getRemoteInputSignalId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindRemoteInputSignal( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllRemoteInputSignal( entity );
	}




    /**
     * Method to retrieve the RemoteInputSignal via an RemoteInputSignalPrimaryKey.
     * @param 	id Long
     * @return 	RemoteInputSignal
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public RemoteInputSignal handle( FindRemoteInputSignalQuery query ) 
    throws ProcessingException, IllegalArgumentException {
    	return find( query.getFilter().getRemoteInputSignalId() );
    }
    
    /**
     * Method to retrieve a collection of all RemoteInputSignals
     *
     * @param	query	FindAllRemoteInputSignalQuery 
     * @return 	List<RemoteInputSignal> 
     * @exception ProcessingException Thrown if any problems
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public List<RemoteInputSignal> handle( FindAllRemoteInputSignalQuery query ) throws ProcessingException {
    	return findAll( query );
    }


	/**
	 * emit to subscription queries of type FindRemoteInputSignal, 
	 * but only if the id matches
	 * 
	 * @param		entity	RemoteInputSignal
	 */
	protected void emitFindRemoteInputSignal( RemoteInputSignal entity ) {
		LOGGER.info("handling emitFindRemoteInputSignal" );
		
	    queryUpdateEmitter.emit(FindRemoteInputSignalQuery.class,
	                            query -> query.getFilter().getRemoteInputSignalId().equals(entity.getRemoteInputSignalId()),
	                            entity);
	}
	
	/**
	 * unconditionally emit to subscription queries of type FindAllRemoteInputSignal
	 * 
	 * @param		entity	RemoteInputSignal
	 */
	protected void emitFindAllRemoteInputSignal( RemoteInputSignal entity ) {
		LOGGER.info("handling emitFindAllRemoteInputSignal" );
		
	    queryUpdateEmitter.emit(FindAllRemoteInputSignalQuery.class,
	                            query -> true,
	                            entity);
	}


	//--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
	private final QueryUpdateEmitter queryUpdateEmitter;
    private static final Logger LOGGER 	= Logger.getLogger(RemoteInputSignalProjector.class.getName());

}
