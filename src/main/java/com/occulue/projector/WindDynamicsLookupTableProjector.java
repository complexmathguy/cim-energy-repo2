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
 * Projector for WindDynamicsLookupTable as outlined for the CQRS pattern.  All event handling and query handling related to WindDynamicsLookupTable are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * Commands are handled by WindDynamicsLookupTableAggregate
 * 
 * @author your_name_here
 *
 */
//@ProcessingGroup("windDynamicsLookupTable")
@Component("windDynamicsLookupTable-projector")
public class WindDynamicsLookupTableProjector extends WindDynamicsLookupTableEntityProjector {
		
	// core constructor
	public WindDynamicsLookupTableProjector(WindDynamicsLookupTableRepository repository, QueryUpdateEmitter queryUpdateEmitter ) {
        super(repository);
        this.queryUpdateEmitter = queryUpdateEmitter;
    }	

	/*
     * @param	event CreateWindDynamicsLookupTableEvent
     */
    @EventHandler( payloadType=CreateWindDynamicsLookupTableEvent.class )
    public void handle( CreateWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling CreateWindDynamicsLookupTableEvent - " + event );
	    WindDynamicsLookupTable entity = new WindDynamicsLookupTable();
        entity.setWindDynamicsLookupTableId( event.getWindDynamicsLookupTableId() );
        entity.setLookupTableFunctionType( event.getLookupTableFunctionType() );
	    
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    create(entity);
        
        // ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindDynamicsLookupTable( entity );
    }

    /*
     * @param	event UpdateWindDynamicsLookupTableEvent
     */
    @EventHandler( payloadType=UpdateWindDynamicsLookupTableEvent.class )
    public void handle( UpdateWindDynamicsLookupTableEvent event) {
    	LOGGER.info("handling UpdateWindDynamicsLookupTableEvent - " + event );
    	
	    WindDynamicsLookupTable entity = new WindDynamicsLookupTable();
        entity.setWindDynamicsLookupTableId( event.getWindDynamicsLookupTableId() );
        entity.setInput( event.getInput() );
        entity.setLookupTableFunctionType( event.getLookupTableFunctionType() );
        entity.setOutput( event.getOutput() );
        entity.setSequence( event.getSequence() );
        entity.setWindContRotorRIEC( event.getWindContRotorRIEC() );
        entity.setWindContCurrLimIEC( event.getWindContCurrLimIEC() );
        entity.setWindPlantFreqPcontrolIEC( event.getWindPlantFreqPcontrolIEC() );
        entity.setWindContPType3IEC( event.getWindContPType3IEC() );
 
    	// ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		update(entity);

    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindWindDynamicsLookupTable( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindDynamicsLookupTable( entity );        
    }
    
    /*
     * @param	event DeleteWindDynamicsLookupTableEvent
     */
    @EventHandler( payloadType=DeleteWindDynamicsLookupTableEvent.class )
    public void handle( DeleteWindDynamicsLookupTableEvent event) {
    	LOGGER.info("handling DeleteWindDynamicsLookupTableEvent - " + event );
    	
    	// ------------------------------------------
    	// delete delegation
    	// ------------------------------------------
    	WindDynamicsLookupTable entity = delete( event.getWindDynamicsLookupTableId() );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindDynamicsLookupTable( entity );

    }    

    /*
     * @param	event AssignInputToWindDynamicsLookupTableEvent
     */
    @EventHandler( payloadType=AssignInputToWindDynamicsLookupTableEvent.class)
    public void handle( AssignInputToWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling AssignInputToWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = assignInput( event.getWindDynamicsLookupTableId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindWindDynamicsLookupTable( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindDynamicsLookupTable( entity );
    }
    

	/*
	 * @param	event UnAssignInputFromWindDynamicsLookupTableEvent
	 */
	@EventHandler( payloadType=UnAssignInputFromWindDynamicsLookupTableEvent.class)
	public void handle( UnAssignInputFromWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling UnAssignInputFromWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = unAssignInput( event.getWindDynamicsLookupTableId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindWindDynamicsLookupTable( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllWindDynamicsLookupTable( entity );
	}

    /*
     * @param	event AssignOutputToWindDynamicsLookupTableEvent
     */
    @EventHandler( payloadType=AssignOutputToWindDynamicsLookupTableEvent.class)
    public void handle( AssignOutputToWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling AssignOutputToWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = assignOutput( event.getWindDynamicsLookupTableId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindWindDynamicsLookupTable( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindDynamicsLookupTable( entity );
    }
    

	/*
	 * @param	event UnAssignOutputFromWindDynamicsLookupTableEvent
	 */
	@EventHandler( payloadType=UnAssignOutputFromWindDynamicsLookupTableEvent.class)
	public void handle( UnAssignOutputFromWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling UnAssignOutputFromWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = unAssignOutput( event.getWindDynamicsLookupTableId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindWindDynamicsLookupTable( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllWindDynamicsLookupTable( entity );
	}

    /*
     * @param	event AssignSequenceToWindDynamicsLookupTableEvent
     */
    @EventHandler( payloadType=AssignSequenceToWindDynamicsLookupTableEvent.class)
    public void handle( AssignSequenceToWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling AssignSequenceToWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = assignSequence( event.getWindDynamicsLookupTableId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindWindDynamicsLookupTable( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindDynamicsLookupTable( entity );
    }
    

	/*
	 * @param	event UnAssignSequenceFromWindDynamicsLookupTableEvent
	 */
	@EventHandler( payloadType=UnAssignSequenceFromWindDynamicsLookupTableEvent.class)
	public void handle( UnAssignSequenceFromWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling UnAssignSequenceFromWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = unAssignSequence( event.getWindDynamicsLookupTableId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindWindDynamicsLookupTable( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllWindDynamicsLookupTable( entity );
	}

    /*
     * @param	event AssignWindContRotorRIECToWindDynamicsLookupTableEvent
     */
    @EventHandler( payloadType=AssignWindContRotorRIECToWindDynamicsLookupTableEvent.class)
    public void handle( AssignWindContRotorRIECToWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling AssignWindContRotorRIECToWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = assignWindContRotorRIEC( event.getWindDynamicsLookupTableId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindWindDynamicsLookupTable( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindDynamicsLookupTable( entity );
    }
    

	/*
	 * @param	event UnAssignWindContRotorRIECFromWindDynamicsLookupTableEvent
	 */
	@EventHandler( payloadType=UnAssignWindContRotorRIECFromWindDynamicsLookupTableEvent.class)
	public void handle( UnAssignWindContRotorRIECFromWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling UnAssignWindContRotorRIECFromWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = unAssignWindContRotorRIEC( event.getWindDynamicsLookupTableId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindWindDynamicsLookupTable( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllWindDynamicsLookupTable( entity );
	}

    /*
     * @param	event AssignWindContCurrLimIECToWindDynamicsLookupTableEvent
     */
    @EventHandler( payloadType=AssignWindContCurrLimIECToWindDynamicsLookupTableEvent.class)
    public void handle( AssignWindContCurrLimIECToWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling AssignWindContCurrLimIECToWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = assignWindContCurrLimIEC( event.getWindDynamicsLookupTableId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindWindDynamicsLookupTable( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindDynamicsLookupTable( entity );
    }
    

	/*
	 * @param	event UnAssignWindContCurrLimIECFromWindDynamicsLookupTableEvent
	 */
	@EventHandler( payloadType=UnAssignWindContCurrLimIECFromWindDynamicsLookupTableEvent.class)
	public void handle( UnAssignWindContCurrLimIECFromWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling UnAssignWindContCurrLimIECFromWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = unAssignWindContCurrLimIEC( event.getWindDynamicsLookupTableId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindWindDynamicsLookupTable( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllWindDynamicsLookupTable( entity );
	}

    /*
     * @param	event AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableEvent
     */
    @EventHandler( payloadType=AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableEvent.class)
    public void handle( AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = assignWindPlantFreqPcontrolIEC( event.getWindDynamicsLookupTableId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindWindDynamicsLookupTable( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindDynamicsLookupTable( entity );
    }
    

	/*
	 * @param	event UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableEvent
	 */
	@EventHandler( payloadType=UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableEvent.class)
	public void handle( UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = unAssignWindPlantFreqPcontrolIEC( event.getWindDynamicsLookupTableId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindWindDynamicsLookupTable( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllWindDynamicsLookupTable( entity );
	}

    /*
     * @param	event AssignWindContPType3IECToWindDynamicsLookupTableEvent
     */
    @EventHandler( payloadType=AssignWindContPType3IECToWindDynamicsLookupTableEvent.class)
    public void handle( AssignWindContPType3IECToWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling AssignWindContPType3IECToWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = assignWindContPType3IEC( event.getWindDynamicsLookupTableId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindWindDynamicsLookupTable( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllWindDynamicsLookupTable( entity );
    }
    

	/*
	 * @param	event UnAssignWindContPType3IECFromWindDynamicsLookupTableEvent
	 */
	@EventHandler( payloadType=UnAssignWindContPType3IECFromWindDynamicsLookupTableEvent.class)
	public void handle( UnAssignWindContPType3IECFromWindDynamicsLookupTableEvent event) {
	    LOGGER.info("handling UnAssignWindContPType3IECFromWindDynamicsLookupTableEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    WindDynamicsLookupTable entity = unAssignWindContPType3IEC( event.getWindDynamicsLookupTableId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindWindDynamicsLookupTable( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllWindDynamicsLookupTable( entity );
	}




    /**
     * Method to retrieve the WindDynamicsLookupTable via an WindDynamicsLookupTablePrimaryKey.
     * @param 	id Long
     * @return 	WindDynamicsLookupTable
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public WindDynamicsLookupTable handle( FindWindDynamicsLookupTableQuery query ) 
    throws ProcessingException, IllegalArgumentException {
    	return find( query.getFilter().getWindDynamicsLookupTableId() );
    }
    
    /**
     * Method to retrieve a collection of all WindDynamicsLookupTables
     *
     * @param	query	FindAllWindDynamicsLookupTableQuery 
     * @return 	List<WindDynamicsLookupTable> 
     * @exception ProcessingException Thrown if any problems
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public List<WindDynamicsLookupTable> handle( FindAllWindDynamicsLookupTableQuery query ) throws ProcessingException {
    	return findAll( query );
    }


	/**
	 * emit to subscription queries of type FindWindDynamicsLookupTable, 
	 * but only if the id matches
	 * 
	 * @param		entity	WindDynamicsLookupTable
	 */
	protected void emitFindWindDynamicsLookupTable( WindDynamicsLookupTable entity ) {
		LOGGER.info("handling emitFindWindDynamicsLookupTable" );
		
	    queryUpdateEmitter.emit(FindWindDynamicsLookupTableQuery.class,
	                            query -> query.getFilter().getWindDynamicsLookupTableId().equals(entity.getWindDynamicsLookupTableId()),
	                            entity);
	}
	
	/**
	 * unconditionally emit to subscription queries of type FindAllWindDynamicsLookupTable
	 * 
	 * @param		entity	WindDynamicsLookupTable
	 */
	protected void emitFindAllWindDynamicsLookupTable( WindDynamicsLookupTable entity ) {
		LOGGER.info("handling emitFindAllWindDynamicsLookupTable" );
		
	    queryUpdateEmitter.emit(FindAllWindDynamicsLookupTableQuery.class,
	                            query -> true,
	                            entity);
	}


	//--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
	private final QueryUpdateEmitter queryUpdateEmitter;
    private static final Logger LOGGER 	= Logger.getLogger(WindDynamicsLookupTableProjector.class.getName());

}
