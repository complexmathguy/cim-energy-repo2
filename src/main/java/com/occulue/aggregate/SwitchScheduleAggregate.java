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
 * Aggregate handler for SwitchSchedule as outlined for the CQRS pattern, all write responsibilities 
 * related to SwitchSchedule are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class SwitchScheduleAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public SwitchScheduleAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public SwitchScheduleAggregate(CreateSwitchScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateSwitchScheduleCommand" );
    	CreateSwitchScheduleEvent event = new CreateSwitchScheduleEvent(command.getSwitchScheduleId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateSwitchScheduleCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateSwitchScheduleCommand" );
    	UpdateSwitchScheduleEvent event = new UpdateSwitchScheduleEvent(command.getSwitchScheduleId(), command.getSwitchIt());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteSwitchScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteSwitchScheduleCommand" );
        apply(new DeleteSwitchScheduleEvent(command.getSwitchScheduleId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignSwitchItToSwitchScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSwitchItToSwitchScheduleCommand" );
    	
    	if (  switchIt != null && switchIt.getSwitchProxyId() == command.getAssignment().getSwitchProxyId() )
    		throw new ProcessingException( "SwitchIt already assigned with id " + command.getAssignment().getSwitchProxyId() );  
    		
        apply(new AssignSwitchItToSwitchScheduleEvent(command.getSwitchScheduleId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSwitchItFromSwitchScheduleCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSwitchItFromSwitchScheduleCommand" );

    	if (  switchIt == null )
    		throw new ProcessingException( "SwitchIt already has nothing assigned." );  

    	apply(new UnAssignSwitchItFromSwitchScheduleEvent(command.getSwitchScheduleId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateSwitchScheduleEvent event) {	
    	LOGGER.info( "Event sourcing CreateSwitchScheduleEvent" );
    	this.switchScheduleId = event.getSwitchScheduleId();
    }
    
    @EventSourcingHandler
    void on(UpdateSwitchScheduleEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.switchIt = event.getSwitchIt();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignSwitchItToSwitchScheduleEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSwitchItToSwitchScheduleEvent" );
    	this.switchIt = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSwitchItFromSwitchScheduleEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSwitchItFromSwitchScheduleEvent" );
		this.switchIt = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID switchScheduleId;
    
    private SwitchProxy switchIt = null;

    private static final Logger LOGGER 	= Logger.getLogger(SwitchScheduleAggregate.class.getName());
}
