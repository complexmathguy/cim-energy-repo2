package com.occulue.aggregate;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

/**
 * Aggregate handler for ConformLoad as outlined for the CQRS pattern, all write responsibilities 
 * related to ConformLoad are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ConformLoadAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ConformLoadAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ConformLoadAggregate(CreateConformLoadCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateConformLoadCommand" );
    	CreateConformLoadEvent event = new CreateConformLoadEvent(command.getConformLoadId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateConformLoadCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateConformLoadCommand" );
    	UpdateConformLoadEvent event = new UpdateConformLoadEvent(command.getConformLoadId(), command.getLoadGroup());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteConformLoadCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteConformLoadCommand" );
        apply(new DeleteConformLoadEvent(command.getConformLoadId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignLoadGroupToConformLoadCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignLoadGroupToConformLoadCommand" );
    	
    	if (  loadGroup != null && loadGroup.getConformLoadGroupId() == command.getAssignment().getConformLoadGroupId() )
    		throw new ProcessingException( "LoadGroup already assigned with id " + command.getAssignment().getConformLoadGroupId() );  
    		
        apply(new AssignLoadGroupToConformLoadEvent(command.getConformLoadId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignLoadGroupFromConformLoadCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignLoadGroupFromConformLoadCommand" );

    	if (  loadGroup == null )
    		throw new ProcessingException( "LoadGroup already has nothing assigned." );  

    	apply(new UnAssignLoadGroupFromConformLoadEvent(command.getConformLoadId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateConformLoadEvent event) {	
    	LOGGER.info( "Event sourcing CreateConformLoadEvent" );
    	this.conformLoadId = event.getConformLoadId();
    }
    
    @EventSourcingHandler
    void on(UpdateConformLoadEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.loadGroup = event.getLoadGroup();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignLoadGroupToConformLoadEvent event ) {	
    	LOGGER.info( "Event sourcing AssignLoadGroupToConformLoadEvent" );
    	this.loadGroup = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignLoadGroupFromConformLoadEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignLoadGroupFromConformLoadEvent" );
		this.loadGroup = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID conformLoadId;
    
    private ConformLoadGroup loadGroup = null;

    private static final Logger LOGGER 	= Logger.getLogger(ConformLoadAggregate.class.getName());
}
