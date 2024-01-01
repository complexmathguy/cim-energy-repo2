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
 * Aggregate handler for TapSchedule as outlined for the CQRS pattern, all write responsibilities 
 * related to TapSchedule are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class TapScheduleAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public TapScheduleAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public TapScheduleAggregate(CreateTapScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateTapScheduleCommand" );
    	CreateTapScheduleEvent event = new CreateTapScheduleEvent(command.getTapScheduleId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateTapScheduleCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateTapScheduleCommand" );
    	UpdateTapScheduleEvent event = new UpdateTapScheduleEvent(command.getTapScheduleId(), command.getTapChanger());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteTapScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteTapScheduleCommand" );
        apply(new DeleteTapScheduleEvent(command.getTapScheduleId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignTapChangerToTapScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTapChangerToTapScheduleCommand" );
    	
    	if (  tapChanger != null && tapChanger.getTapChangerId() == command.getAssignment().getTapChangerId() )
    		throw new ProcessingException( "TapChanger already assigned with id " + command.getAssignment().getTapChangerId() );  
    		
        apply(new AssignTapChangerToTapScheduleEvent(command.getTapScheduleId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTapChangerFromTapScheduleCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTapChangerFromTapScheduleCommand" );

    	if (  tapChanger == null )
    		throw new ProcessingException( "TapChanger already has nothing assigned." );  

    	apply(new UnAssignTapChangerFromTapScheduleEvent(command.getTapScheduleId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateTapScheduleEvent event) {	
    	LOGGER.info( "Event sourcing CreateTapScheduleEvent" );
    	this.tapScheduleId = event.getTapScheduleId();
    }
    
    @EventSourcingHandler
    void on(UpdateTapScheduleEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.tapChanger = event.getTapChanger();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignTapChangerToTapScheduleEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTapChangerToTapScheduleEvent" );
    	this.tapChanger = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTapChangerFromTapScheduleEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTapChangerFromTapScheduleEvent" );
		this.tapChanger = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID tapScheduleId;
    
    private TapChanger tapChanger = null;

    private static final Logger LOGGER 	= Logger.getLogger(TapScheduleAggregate.class.getName());
}
