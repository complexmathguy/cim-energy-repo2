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
 * Projector for Command as outlined for the CQRS pattern.  All event handling and query handling related to Command are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * Commands are handled by CommandAggregate
 * 
 * @author your_name_here
 *
 */
//@ProcessingGroup("command")
@Component("command-projector")
public class CommandProjector extends CommandEntityProjector {
		
	// core constructor
	public CommandProjector(CommandRepository repository, QueryUpdateEmitter queryUpdateEmitter ) {
        super(repository);
        this.queryUpdateEmitter = queryUpdateEmitter;
    }	

	/*
     * @param	event CreateCommandEvent
     */
    @EventHandler( payloadType=CreateCommandEvent.class )
    public void handle( CreateCommandEvent event) {
	    LOGGER.info("handling CreateCommandEvent - " + event );
	    Command entity = new Command();
        entity.setCommandId( event.getCommandId() );
	    
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    create(entity);
        
        // ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllCommand( entity );
    }

    /*
     * @param	event UpdateCommandEvent
     */
    @EventHandler( payloadType=UpdateCommandEvent.class )
    public void handle( UpdateCommandEvent event) {
    	LOGGER.info("handling UpdateCommandEvent - " + event );
    	
	    Command entity = new Command();
        entity.setCommandId( event.getCommandId() );
        entity.setNormalValue( event.getNormalValue() );
        entity.setValue( event.getValue() );
        entity.setValueAliasSet( event.getValueAliasSet() );
        entity.setDiscreteValue( event.getDiscreteValue() );
 
    	// ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		update(entity);

    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindCommand( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllCommand( entity );        
    }
    
    /*
     * @param	event DeleteCommandEvent
     */
    @EventHandler( payloadType=DeleteCommandEvent.class )
    public void handle( DeleteCommandEvent event) {
    	LOGGER.info("handling DeleteCommandEvent - " + event );
    	
    	// ------------------------------------------
    	// delete delegation
    	// ------------------------------------------
    	Command entity = delete( event.getCommandId() );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllCommand( entity );

    }    

    /*
     * @param	event AssignNormalValueToCommandEvent
     */
    @EventHandler( payloadType=AssignNormalValueToCommandEvent.class)
    public void handle( AssignNormalValueToCommandEvent event) {
	    LOGGER.info("handling AssignNormalValueToCommandEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    Command entity = assignNormalValue( event.getCommandId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindCommand( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllCommand( entity );
    }
    

	/*
	 * @param	event UnAssignNormalValueFromCommandEvent
	 */
	@EventHandler( payloadType=UnAssignNormalValueFromCommandEvent.class)
	public void handle( UnAssignNormalValueFromCommandEvent event) {
	    LOGGER.info("handling UnAssignNormalValueFromCommandEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    Command entity = unAssignNormalValue( event.getCommandId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindCommand( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllCommand( entity );
	}

    /*
     * @param	event AssignValueToCommandEvent
     */
    @EventHandler( payloadType=AssignValueToCommandEvent.class)
    public void handle( AssignValueToCommandEvent event) {
	    LOGGER.info("handling AssignValueToCommandEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    Command entity = assignValue( event.getCommandId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindCommand( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllCommand( entity );
    }
    

	/*
	 * @param	event UnAssignValueFromCommandEvent
	 */
	@EventHandler( payloadType=UnAssignValueFromCommandEvent.class)
	public void handle( UnAssignValueFromCommandEvent event) {
	    LOGGER.info("handling UnAssignValueFromCommandEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    Command entity = unAssignValue( event.getCommandId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindCommand( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllCommand( entity );
	}


    /*
     * @param	event AssignValueAliasSetToCommandEvent
     */
    @EventHandler( payloadType=AssignValueAliasSetToCommandEvent.class)
    public void handle( AssignValueAliasSetToCommandEvent event) {
	    LOGGER.info("handling AssignValueAliasSetToCommandEvent - " + event );
	    
	    // ------------------------------------------
    	// delegate to addTo 
    	// ------------------------------------------ 
	    Command entity = addToValueAliasSet(event.getCommandId(), event.getAddTo() );
        
    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindCommand( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllCommand( entity );
    }
    

/*
 * @param	event RemoveValueAliasSetFromCommandEvent
 */
@EventHandler( payloadType=RemoveValueAliasSetFromCommandEvent.class)
public void handle( RemoveValueAliasSetFromCommandEvent event) {
    LOGGER.info("handling RemoveValueAliasSetFromCommandEvent - " + event );

    Command entity = removeFromValueAliasSet(event.getCommandId(), event.getRemoveFrom() );
    
	// ------------------------------------------
	// emit to subscribers that find one
	// ------------------------------------------    	
    emitFindCommand( entity );

	// ------------------------------------------
	// emit to subscribers that find all
	// ------------------------------------------    	
    emitFindAllCommand( entity );
}

    /*
     * @param	event AssignDiscreteValueToCommandEvent
     */
    @EventHandler( payloadType=AssignDiscreteValueToCommandEvent.class)
    public void handle( AssignDiscreteValueToCommandEvent event) {
	    LOGGER.info("handling AssignDiscreteValueToCommandEvent - " + event );
	    
	    // ------------------------------------------
    	// delegate to addTo 
    	// ------------------------------------------ 
	    Command entity = addToDiscreteValue(event.getCommandId(), event.getAddTo() );
        
    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindCommand( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllCommand( entity );
    }
    

/*
 * @param	event RemoveDiscreteValueFromCommandEvent
 */
@EventHandler( payloadType=RemoveDiscreteValueFromCommandEvent.class)
public void handle( RemoveDiscreteValueFromCommandEvent event) {
    LOGGER.info("handling RemoveDiscreteValueFromCommandEvent - " + event );

    Command entity = removeFromDiscreteValue(event.getCommandId(), event.getRemoveFrom() );
    
	// ------------------------------------------
	// emit to subscribers that find one
	// ------------------------------------------    	
    emitFindCommand( entity );

	// ------------------------------------------
	// emit to subscribers that find all
	// ------------------------------------------    	
    emitFindAllCommand( entity );
}



    /**
     * Method to retrieve the Command via an CommandPrimaryKey.
     * @param 	id Long
     * @return 	Command
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public Command handle( FindCommandQuery query ) 
    throws ProcessingException, IllegalArgumentException {
    	return find( query.getFilter().getCommandId() );
    }
    
    /**
     * Method to retrieve a collection of all Commands
     *
     * @param	query	FindAllCommandQuery 
     * @return 	List<Command> 
     * @exception ProcessingException Thrown if any problems
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public List<Command> handle( FindAllCommandQuery query ) throws ProcessingException {
    	return findAll( query );
    }


	/**
	 * emit to subscription queries of type FindCommand, 
	 * but only if the id matches
	 * 
	 * @param		entity	Command
	 */
	protected void emitFindCommand( Command entity ) {
		LOGGER.info("handling emitFindCommand" );
		
	    queryUpdateEmitter.emit(FindCommandQuery.class,
	                            query -> query.getFilter().getCommandId().equals(entity.getCommandId()),
	                            entity);
	}
	
	/**
	 * unconditionally emit to subscription queries of type FindAllCommand
	 * 
	 * @param		entity	Command
	 */
	protected void emitFindAllCommand( Command entity ) {
		LOGGER.info("handling emitFindAllCommand" );
		
	    queryUpdateEmitter.emit(FindAllCommandQuery.class,
	                            query -> true,
	                            entity);
	}


	//--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
	private final QueryUpdateEmitter queryUpdateEmitter;
    private static final Logger LOGGER 	= Logger.getLogger(CommandProjector.class.getName());

}
