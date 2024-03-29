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
 * Aggregate handler for NonConformLoadSchedule as outlined for the CQRS pattern, all write responsibilities 
 * related to NonConformLoadSchedule are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class NonConformLoadScheduleAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public NonConformLoadScheduleAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public NonConformLoadScheduleAggregate(CreateNonConformLoadScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateNonConformLoadScheduleCommand" );
    	CreateNonConformLoadScheduleEvent event = new CreateNonConformLoadScheduleEvent(command.getNonConformLoadScheduleId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateNonConformLoadScheduleCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateNonConformLoadScheduleCommand" );
    	UpdateNonConformLoadScheduleEvent event = new UpdateNonConformLoadScheduleEvent(command.getNonConformLoadScheduleId(), command.getNonConformLoadGroup());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteNonConformLoadScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteNonConformLoadScheduleCommand" );
        apply(new DeleteNonConformLoadScheduleEvent(command.getNonConformLoadScheduleId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignNonConformLoadGroupToNonConformLoadScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignNonConformLoadGroupToNonConformLoadScheduleCommand" );
    	
    	if (  nonConformLoadGroup != null && nonConformLoadGroup.getNonConformLoadGroupId() == command.getAssignment().getNonConformLoadGroupId() )
    		throw new ProcessingException( "NonConformLoadGroup already assigned with id " + command.getAssignment().getNonConformLoadGroupId() );  
    		
        apply(new AssignNonConformLoadGroupToNonConformLoadScheduleEvent(command.getNonConformLoadScheduleId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignNonConformLoadGroupFromNonConformLoadScheduleCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignNonConformLoadGroupFromNonConformLoadScheduleCommand" );

    	if (  nonConformLoadGroup == null )
    		throw new ProcessingException( "NonConformLoadGroup already has nothing assigned." );  

    	apply(new UnAssignNonConformLoadGroupFromNonConformLoadScheduleEvent(command.getNonConformLoadScheduleId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateNonConformLoadScheduleEvent event) {	
    	LOGGER.info( "Event sourcing CreateNonConformLoadScheduleEvent" );
    	this.nonConformLoadScheduleId = event.getNonConformLoadScheduleId();
    }
    
    @EventSourcingHandler
    void on(UpdateNonConformLoadScheduleEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.nonConformLoadGroup = event.getNonConformLoadGroup();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignNonConformLoadGroupToNonConformLoadScheduleEvent event ) {	
    	LOGGER.info( "Event sourcing AssignNonConformLoadGroupToNonConformLoadScheduleEvent" );
    	this.nonConformLoadGroup = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignNonConformLoadGroupFromNonConformLoadScheduleEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignNonConformLoadGroupFromNonConformLoadScheduleEvent" );
		this.nonConformLoadGroup = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID nonConformLoadScheduleId;
    
    private NonConformLoadGroup nonConformLoadGroup = null;

    private static final Logger LOGGER 	= Logger.getLogger(NonConformLoadScheduleAggregate.class.getName());
}
