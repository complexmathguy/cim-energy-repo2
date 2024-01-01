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
 * Projector for TransformerEnd as outlined for the CQRS pattern.  All event handling and query handling related to TransformerEnd are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * Commands are handled by TransformerEndAggregate
 * 
 * @author your_name_here
 *
 */
//@ProcessingGroup("transformerEnd")
@Component("transformerEnd-projector")
public class TransformerEndProjector extends TransformerEndEntityProjector {
		
	// core constructor
	public TransformerEndProjector(TransformerEndRepository repository, QueryUpdateEmitter queryUpdateEmitter ) {
        super(repository);
        this.queryUpdateEmitter = queryUpdateEmitter;
    }	

	/*
     * @param	event CreateTransformerEndEvent
     */
    @EventHandler( payloadType=CreateTransformerEndEvent.class )
    public void handle( CreateTransformerEndEvent event) {
	    LOGGER.info("handling CreateTransformerEndEvent - " + event );
	    TransformerEnd entity = new TransformerEnd();
        entity.setTransformerEndId( event.getTransformerEndId() );
	    
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    create(entity);
        
        // ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllTransformerEnd( entity );
    }

    /*
     * @param	event UpdateTransformerEndEvent
     */
    @EventHandler( payloadType=UpdateTransformerEndEvent.class )
    public void handle( UpdateTransformerEndEvent event) {
    	LOGGER.info("handling UpdateTransformerEndEvent - " + event );
    	
	    TransformerEnd entity = new TransformerEnd();
        entity.setTransformerEndId( event.getTransformerEndId() );
        entity.setEndNumber( event.getEndNumber() );
        entity.setGrounded( event.getGrounded() );
        entity.setRground( event.getRground() );
        entity.setXground( event.getXground() );
        entity.setBaseVoltage( event.getBaseVoltage() );
        entity.setTerminal( event.getTerminal() );
 
    	// ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		update(entity);

    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindTransformerEnd( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllTransformerEnd( entity );        
    }
    
    /*
     * @param	event DeleteTransformerEndEvent
     */
    @EventHandler( payloadType=DeleteTransformerEndEvent.class )
    public void handle( DeleteTransformerEndEvent event) {
    	LOGGER.info("handling DeleteTransformerEndEvent - " + event );
    	
    	// ------------------------------------------
    	// delete delegation
    	// ------------------------------------------
    	TransformerEnd entity = delete( event.getTransformerEndId() );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllTransformerEnd( entity );

    }    

    /*
     * @param	event AssignEndNumberToTransformerEndEvent
     */
    @EventHandler( payloadType=AssignEndNumberToTransformerEndEvent.class)
    public void handle( AssignEndNumberToTransformerEndEvent event) {
	    LOGGER.info("handling AssignEndNumberToTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    TransformerEnd entity = assignEndNumber( event.getTransformerEndId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindTransformerEnd( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllTransformerEnd( entity );
    }
    

	/*
	 * @param	event UnAssignEndNumberFromTransformerEndEvent
	 */
	@EventHandler( payloadType=UnAssignEndNumberFromTransformerEndEvent.class)
	public void handle( UnAssignEndNumberFromTransformerEndEvent event) {
	    LOGGER.info("handling UnAssignEndNumberFromTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    TransformerEnd entity = unAssignEndNumber( event.getTransformerEndId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindTransformerEnd( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllTransformerEnd( entity );
	}

    /*
     * @param	event AssignGroundedToTransformerEndEvent
     */
    @EventHandler( payloadType=AssignGroundedToTransformerEndEvent.class)
    public void handle( AssignGroundedToTransformerEndEvent event) {
	    LOGGER.info("handling AssignGroundedToTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    TransformerEnd entity = assignGrounded( event.getTransformerEndId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindTransformerEnd( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllTransformerEnd( entity );
    }
    

	/*
	 * @param	event UnAssignGroundedFromTransformerEndEvent
	 */
	@EventHandler( payloadType=UnAssignGroundedFromTransformerEndEvent.class)
	public void handle( UnAssignGroundedFromTransformerEndEvent event) {
	    LOGGER.info("handling UnAssignGroundedFromTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    TransformerEnd entity = unAssignGrounded( event.getTransformerEndId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindTransformerEnd( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllTransformerEnd( entity );
	}

    /*
     * @param	event AssignRgroundToTransformerEndEvent
     */
    @EventHandler( payloadType=AssignRgroundToTransformerEndEvent.class)
    public void handle( AssignRgroundToTransformerEndEvent event) {
	    LOGGER.info("handling AssignRgroundToTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    TransformerEnd entity = assignRground( event.getTransformerEndId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindTransformerEnd( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllTransformerEnd( entity );
    }
    

	/*
	 * @param	event UnAssignRgroundFromTransformerEndEvent
	 */
	@EventHandler( payloadType=UnAssignRgroundFromTransformerEndEvent.class)
	public void handle( UnAssignRgroundFromTransformerEndEvent event) {
	    LOGGER.info("handling UnAssignRgroundFromTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    TransformerEnd entity = unAssignRground( event.getTransformerEndId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindTransformerEnd( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllTransformerEnd( entity );
	}

    /*
     * @param	event AssignXgroundToTransformerEndEvent
     */
    @EventHandler( payloadType=AssignXgroundToTransformerEndEvent.class)
    public void handle( AssignXgroundToTransformerEndEvent event) {
	    LOGGER.info("handling AssignXgroundToTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    TransformerEnd entity = assignXground( event.getTransformerEndId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindTransformerEnd( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllTransformerEnd( entity );
    }
    

	/*
	 * @param	event UnAssignXgroundFromTransformerEndEvent
	 */
	@EventHandler( payloadType=UnAssignXgroundFromTransformerEndEvent.class)
	public void handle( UnAssignXgroundFromTransformerEndEvent event) {
	    LOGGER.info("handling UnAssignXgroundFromTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    TransformerEnd entity = unAssignXground( event.getTransformerEndId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindTransformerEnd( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllTransformerEnd( entity );
	}

    /*
     * @param	event AssignBaseVoltageToTransformerEndEvent
     */
    @EventHandler( payloadType=AssignBaseVoltageToTransformerEndEvent.class)
    public void handle( AssignBaseVoltageToTransformerEndEvent event) {
	    LOGGER.info("handling AssignBaseVoltageToTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    TransformerEnd entity = assignBaseVoltage( event.getTransformerEndId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindTransformerEnd( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllTransformerEnd( entity );
    }
    

	/*
	 * @param	event UnAssignBaseVoltageFromTransformerEndEvent
	 */
	@EventHandler( payloadType=UnAssignBaseVoltageFromTransformerEndEvent.class)
	public void handle( UnAssignBaseVoltageFromTransformerEndEvent event) {
	    LOGGER.info("handling UnAssignBaseVoltageFromTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    TransformerEnd entity = unAssignBaseVoltage( event.getTransformerEndId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindTransformerEnd( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllTransformerEnd( entity );
	}

    /*
     * @param	event AssignTerminalToTransformerEndEvent
     */
    @EventHandler( payloadType=AssignTerminalToTransformerEndEvent.class)
    public void handle( AssignTerminalToTransformerEndEvent event) {
	    LOGGER.info("handling AssignTerminalToTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    TransformerEnd entity = assignTerminal( event.getTransformerEndId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindTransformerEnd( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllTransformerEnd( entity );
    }
    

	/*
	 * @param	event UnAssignTerminalFromTransformerEndEvent
	 */
	@EventHandler( payloadType=UnAssignTerminalFromTransformerEndEvent.class)
	public void handle( UnAssignTerminalFromTransformerEndEvent event) {
	    LOGGER.info("handling UnAssignTerminalFromTransformerEndEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    TransformerEnd entity = unAssignTerminal( event.getTransformerEndId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindTransformerEnd( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllTransformerEnd( entity );
	}




    /**
     * Method to retrieve the TransformerEnd via an TransformerEndPrimaryKey.
     * @param 	id Long
     * @return 	TransformerEnd
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public TransformerEnd handle( FindTransformerEndQuery query ) 
    throws ProcessingException, IllegalArgumentException {
    	return find( query.getFilter().getTransformerEndId() );
    }
    
    /**
     * Method to retrieve a collection of all TransformerEnds
     *
     * @param	query	FindAllTransformerEndQuery 
     * @return 	List<TransformerEnd> 
     * @exception ProcessingException Thrown if any problems
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public List<TransformerEnd> handle( FindAllTransformerEndQuery query ) throws ProcessingException {
    	return findAll( query );
    }


	/**
	 * emit to subscription queries of type FindTransformerEnd, 
	 * but only if the id matches
	 * 
	 * @param		entity	TransformerEnd
	 */
	protected void emitFindTransformerEnd( TransformerEnd entity ) {
		LOGGER.info("handling emitFindTransformerEnd" );
		
	    queryUpdateEmitter.emit(FindTransformerEndQuery.class,
	                            query -> query.getFilter().getTransformerEndId().equals(entity.getTransformerEndId()),
	                            entity);
	}
	
	/**
	 * unconditionally emit to subscription queries of type FindAllTransformerEnd
	 * 
	 * @param		entity	TransformerEnd
	 */
	protected void emitFindAllTransformerEnd( TransformerEnd entity ) {
		LOGGER.info("handling emitFindAllTransformerEnd" );
		
	    queryUpdateEmitter.emit(FindAllTransformerEndQuery.class,
	                            query -> true,
	                            entity);
	}


	//--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
	private final QueryUpdateEmitter queryUpdateEmitter;
    private static final Logger LOGGER 	= Logger.getLogger(TransformerEndProjector.class.getName());

}
