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
 * Aggregate handler for SubGeographicalRegion as outlined for the CQRS pattern, all write responsibilities 
 * related to SubGeographicalRegion are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class SubGeographicalRegionAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public SubGeographicalRegionAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public SubGeographicalRegionAggregate(CreateSubGeographicalRegionCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateSubGeographicalRegionCommand" );
    	CreateSubGeographicalRegionEvent event = new CreateSubGeographicalRegionEvent(command.getSubGeographicalRegionId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateSubGeographicalRegionCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateSubGeographicalRegionCommand" );
    	UpdateSubGeographicalRegionEvent event = new UpdateSubGeographicalRegionEvent(command.getSubGeographicalRegionId(), command.getRegion());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteSubGeographicalRegionCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteSubGeographicalRegionCommand" );
        apply(new DeleteSubGeographicalRegionEvent(command.getSubGeographicalRegionId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignRegionToSubGeographicalRegionCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRegionToSubGeographicalRegionCommand" );
    	
    	if (  region != null && region.getGeographicalRegionId() == command.getAssignment().getGeographicalRegionId() )
    		throw new ProcessingException( "Region already assigned with id " + command.getAssignment().getGeographicalRegionId() );  
    		
        apply(new AssignRegionToSubGeographicalRegionEvent(command.getSubGeographicalRegionId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRegionFromSubGeographicalRegionCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRegionFromSubGeographicalRegionCommand" );

    	if (  region == null )
    		throw new ProcessingException( "Region already has nothing assigned." );  

    	apply(new UnAssignRegionFromSubGeographicalRegionEvent(command.getSubGeographicalRegionId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateSubGeographicalRegionEvent event) {	
    	LOGGER.info( "Event sourcing CreateSubGeographicalRegionEvent" );
    	this.subGeographicalRegionId = event.getSubGeographicalRegionId();
    }
    
    @EventSourcingHandler
    void on(UpdateSubGeographicalRegionEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.region = event.getRegion();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignRegionToSubGeographicalRegionEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRegionToSubGeographicalRegionEvent" );
    	this.region = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRegionFromSubGeographicalRegionEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRegionFromSubGeographicalRegionEvent" );
		this.region = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID subGeographicalRegionId;
    
    private GeographicalRegion region = null;

    private static final Logger LOGGER 	= Logger.getLogger(SubGeographicalRegionAggregate.class.getName());
}
