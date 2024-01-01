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
 * Projector for MutualCoupling as outlined for the CQRS pattern.  All event handling and query handling related to MutualCoupling are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * Commands are handled by MutualCouplingAggregate
 * 
 * @author your_name_here
 *
 */
//@ProcessingGroup("mutualCoupling")
@Component("mutualCoupling-projector")
public class MutualCouplingProjector extends MutualCouplingEntityProjector {
		
	// core constructor
	public MutualCouplingProjector(MutualCouplingRepository repository, QueryUpdateEmitter queryUpdateEmitter ) {
        super(repository);
        this.queryUpdateEmitter = queryUpdateEmitter;
    }	

	/*
     * @param	event CreateMutualCouplingEvent
     */
    @EventHandler( payloadType=CreateMutualCouplingEvent.class )
    public void handle( CreateMutualCouplingEvent event) {
	    LOGGER.info("handling CreateMutualCouplingEvent - " + event );
	    MutualCoupling entity = new MutualCoupling();
        entity.setMutualCouplingId( event.getMutualCouplingId() );
	    
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    create(entity);
        
        // ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );
    }

    /*
     * @param	event UpdateMutualCouplingEvent
     */
    @EventHandler( payloadType=UpdateMutualCouplingEvent.class )
    public void handle( UpdateMutualCouplingEvent event) {
    	LOGGER.info("handling UpdateMutualCouplingEvent - " + event );
    	
	    MutualCoupling entity = new MutualCoupling();
        entity.setMutualCouplingId( event.getMutualCouplingId() );
        entity.setB0ch( event.getB0ch() );
        entity.setDistance11( event.getDistance11() );
        entity.setDistance12( event.getDistance12() );
        entity.setDistance21( event.getDistance21() );
        entity.setDistance22( event.getDistance22() );
        entity.setG0ch( event.getG0ch() );
        entity.setR0( event.getR0() );
        entity.setX0( event.getX0() );
        entity.setSecond_Terminal( event.getSecond_Terminal() );
        entity.setFirst_Terminal( event.getFirst_Terminal() );
 
    	// ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		update(entity);

    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindMutualCoupling( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );        
    }
    
    /*
     * @param	event DeleteMutualCouplingEvent
     */
    @EventHandler( payloadType=DeleteMutualCouplingEvent.class )
    public void handle( DeleteMutualCouplingEvent event) {
    	LOGGER.info("handling DeleteMutualCouplingEvent - " + event );
    	
    	// ------------------------------------------
    	// delete delegation
    	// ------------------------------------------
    	MutualCoupling entity = delete( event.getMutualCouplingId() );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );

    }    

    /*
     * @param	event AssignB0chToMutualCouplingEvent
     */
    @EventHandler( payloadType=AssignB0chToMutualCouplingEvent.class)
    public void handle( AssignB0chToMutualCouplingEvent event) {
	    LOGGER.info("handling AssignB0chToMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    MutualCoupling entity = assignB0ch( event.getMutualCouplingId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindMutualCoupling( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );
    }
    

	/*
	 * @param	event UnAssignB0chFromMutualCouplingEvent
	 */
	@EventHandler( payloadType=UnAssignB0chFromMutualCouplingEvent.class)
	public void handle( UnAssignB0chFromMutualCouplingEvent event) {
	    LOGGER.info("handling UnAssignB0chFromMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    MutualCoupling entity = unAssignB0ch( event.getMutualCouplingId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindMutualCoupling( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllMutualCoupling( entity );
	}

    /*
     * @param	event AssignDistance11ToMutualCouplingEvent
     */
    @EventHandler( payloadType=AssignDistance11ToMutualCouplingEvent.class)
    public void handle( AssignDistance11ToMutualCouplingEvent event) {
	    LOGGER.info("handling AssignDistance11ToMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    MutualCoupling entity = assignDistance11( event.getMutualCouplingId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindMutualCoupling( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );
    }
    

	/*
	 * @param	event UnAssignDistance11FromMutualCouplingEvent
	 */
	@EventHandler( payloadType=UnAssignDistance11FromMutualCouplingEvent.class)
	public void handle( UnAssignDistance11FromMutualCouplingEvent event) {
	    LOGGER.info("handling UnAssignDistance11FromMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    MutualCoupling entity = unAssignDistance11( event.getMutualCouplingId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindMutualCoupling( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllMutualCoupling( entity );
	}

    /*
     * @param	event AssignDistance12ToMutualCouplingEvent
     */
    @EventHandler( payloadType=AssignDistance12ToMutualCouplingEvent.class)
    public void handle( AssignDistance12ToMutualCouplingEvent event) {
	    LOGGER.info("handling AssignDistance12ToMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    MutualCoupling entity = assignDistance12( event.getMutualCouplingId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindMutualCoupling( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );
    }
    

	/*
	 * @param	event UnAssignDistance12FromMutualCouplingEvent
	 */
	@EventHandler( payloadType=UnAssignDistance12FromMutualCouplingEvent.class)
	public void handle( UnAssignDistance12FromMutualCouplingEvent event) {
	    LOGGER.info("handling UnAssignDistance12FromMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    MutualCoupling entity = unAssignDistance12( event.getMutualCouplingId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindMutualCoupling( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllMutualCoupling( entity );
	}

    /*
     * @param	event AssignDistance21ToMutualCouplingEvent
     */
    @EventHandler( payloadType=AssignDistance21ToMutualCouplingEvent.class)
    public void handle( AssignDistance21ToMutualCouplingEvent event) {
	    LOGGER.info("handling AssignDistance21ToMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    MutualCoupling entity = assignDistance21( event.getMutualCouplingId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindMutualCoupling( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );
    }
    

	/*
	 * @param	event UnAssignDistance21FromMutualCouplingEvent
	 */
	@EventHandler( payloadType=UnAssignDistance21FromMutualCouplingEvent.class)
	public void handle( UnAssignDistance21FromMutualCouplingEvent event) {
	    LOGGER.info("handling UnAssignDistance21FromMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    MutualCoupling entity = unAssignDistance21( event.getMutualCouplingId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindMutualCoupling( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllMutualCoupling( entity );
	}

    /*
     * @param	event AssignDistance22ToMutualCouplingEvent
     */
    @EventHandler( payloadType=AssignDistance22ToMutualCouplingEvent.class)
    public void handle( AssignDistance22ToMutualCouplingEvent event) {
	    LOGGER.info("handling AssignDistance22ToMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    MutualCoupling entity = assignDistance22( event.getMutualCouplingId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindMutualCoupling( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );
    }
    

	/*
	 * @param	event UnAssignDistance22FromMutualCouplingEvent
	 */
	@EventHandler( payloadType=UnAssignDistance22FromMutualCouplingEvent.class)
	public void handle( UnAssignDistance22FromMutualCouplingEvent event) {
	    LOGGER.info("handling UnAssignDistance22FromMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    MutualCoupling entity = unAssignDistance22( event.getMutualCouplingId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindMutualCoupling( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllMutualCoupling( entity );
	}

    /*
     * @param	event AssignG0chToMutualCouplingEvent
     */
    @EventHandler( payloadType=AssignG0chToMutualCouplingEvent.class)
    public void handle( AssignG0chToMutualCouplingEvent event) {
	    LOGGER.info("handling AssignG0chToMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    MutualCoupling entity = assignG0ch( event.getMutualCouplingId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindMutualCoupling( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );
    }
    

	/*
	 * @param	event UnAssignG0chFromMutualCouplingEvent
	 */
	@EventHandler( payloadType=UnAssignG0chFromMutualCouplingEvent.class)
	public void handle( UnAssignG0chFromMutualCouplingEvent event) {
	    LOGGER.info("handling UnAssignG0chFromMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    MutualCoupling entity = unAssignG0ch( event.getMutualCouplingId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindMutualCoupling( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllMutualCoupling( entity );
	}

    /*
     * @param	event AssignR0ToMutualCouplingEvent
     */
    @EventHandler( payloadType=AssignR0ToMutualCouplingEvent.class)
    public void handle( AssignR0ToMutualCouplingEvent event) {
	    LOGGER.info("handling AssignR0ToMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    MutualCoupling entity = assignR0( event.getMutualCouplingId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindMutualCoupling( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );
    }
    

	/*
	 * @param	event UnAssignR0FromMutualCouplingEvent
	 */
	@EventHandler( payloadType=UnAssignR0FromMutualCouplingEvent.class)
	public void handle( UnAssignR0FromMutualCouplingEvent event) {
	    LOGGER.info("handling UnAssignR0FromMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    MutualCoupling entity = unAssignR0( event.getMutualCouplingId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindMutualCoupling( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllMutualCoupling( entity );
	}

    /*
     * @param	event AssignX0ToMutualCouplingEvent
     */
    @EventHandler( payloadType=AssignX0ToMutualCouplingEvent.class)
    public void handle( AssignX0ToMutualCouplingEvent event) {
	    LOGGER.info("handling AssignX0ToMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    MutualCoupling entity = assignX0( event.getMutualCouplingId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindMutualCoupling( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );
    }
    

	/*
	 * @param	event UnAssignX0FromMutualCouplingEvent
	 */
	@EventHandler( payloadType=UnAssignX0FromMutualCouplingEvent.class)
	public void handle( UnAssignX0FromMutualCouplingEvent event) {
	    LOGGER.info("handling UnAssignX0FromMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    MutualCoupling entity = unAssignX0( event.getMutualCouplingId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindMutualCoupling( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllMutualCoupling( entity );
	}

    /*
     * @param	event AssignSecond_TerminalToMutualCouplingEvent
     */
    @EventHandler( payloadType=AssignSecond_TerminalToMutualCouplingEvent.class)
    public void handle( AssignSecond_TerminalToMutualCouplingEvent event) {
	    LOGGER.info("handling AssignSecond_TerminalToMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    MutualCoupling entity = assignSecond_Terminal( event.getMutualCouplingId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindMutualCoupling( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );
    }
    

	/*
	 * @param	event UnAssignSecond_TerminalFromMutualCouplingEvent
	 */
	@EventHandler( payloadType=UnAssignSecond_TerminalFromMutualCouplingEvent.class)
	public void handle( UnAssignSecond_TerminalFromMutualCouplingEvent event) {
	    LOGGER.info("handling UnAssignSecond_TerminalFromMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    MutualCoupling entity = unAssignSecond_Terminal( event.getMutualCouplingId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindMutualCoupling( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllMutualCoupling( entity );
	}

    /*
     * @param	event AssignFirst_TerminalToMutualCouplingEvent
     */
    @EventHandler( payloadType=AssignFirst_TerminalToMutualCouplingEvent.class)
    public void handle( AssignFirst_TerminalToMutualCouplingEvent event) {
	    LOGGER.info("handling AssignFirst_TerminalToMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    MutualCoupling entity = assignFirst_Terminal( event.getMutualCouplingId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindMutualCoupling( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllMutualCoupling( entity );
    }
    

	/*
	 * @param	event UnAssignFirst_TerminalFromMutualCouplingEvent
	 */
	@EventHandler( payloadType=UnAssignFirst_TerminalFromMutualCouplingEvent.class)
	public void handle( UnAssignFirst_TerminalFromMutualCouplingEvent event) {
	    LOGGER.info("handling UnAssignFirst_TerminalFromMutualCouplingEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    MutualCoupling entity = unAssignFirst_Terminal( event.getMutualCouplingId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindMutualCoupling( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllMutualCoupling( entity );
	}




    /**
     * Method to retrieve the MutualCoupling via an MutualCouplingPrimaryKey.
     * @param 	id Long
     * @return 	MutualCoupling
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public MutualCoupling handle( FindMutualCouplingQuery query ) 
    throws ProcessingException, IllegalArgumentException {
    	return find( query.getFilter().getMutualCouplingId() );
    }
    
    /**
     * Method to retrieve a collection of all MutualCouplings
     *
     * @param	query	FindAllMutualCouplingQuery 
     * @return 	List<MutualCoupling> 
     * @exception ProcessingException Thrown if any problems
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public List<MutualCoupling> handle( FindAllMutualCouplingQuery query ) throws ProcessingException {
    	return findAll( query );
    }


	/**
	 * emit to subscription queries of type FindMutualCoupling, 
	 * but only if the id matches
	 * 
	 * @param		entity	MutualCoupling
	 */
	protected void emitFindMutualCoupling( MutualCoupling entity ) {
		LOGGER.info("handling emitFindMutualCoupling" );
		
	    queryUpdateEmitter.emit(FindMutualCouplingQuery.class,
	                            query -> query.getFilter().getMutualCouplingId().equals(entity.getMutualCouplingId()),
	                            entity);
	}
	
	/**
	 * unconditionally emit to subscription queries of type FindAllMutualCoupling
	 * 
	 * @param		entity	MutualCoupling
	 */
	protected void emitFindAllMutualCoupling( MutualCoupling entity ) {
		LOGGER.info("handling emitFindAllMutualCoupling" );
		
	    queryUpdateEmitter.emit(FindAllMutualCouplingQuery.class,
	                            query -> true,
	                            entity);
	}


	//--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
	private final QueryUpdateEmitter queryUpdateEmitter;
    private static final Logger LOGGER 	= Logger.getLogger(MutualCouplingProjector.class.getName());

}
