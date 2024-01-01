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
 * Aggregate handler for Substation as outlined for the CQRS pattern, all write responsibilities 
 * related to Substation are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class SubstationAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public SubstationAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public SubstationAggregate(CreateSubstationCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateSubstationCommand" );
    	CreateSubstationEvent event = new CreateSubstationEvent(command.getSubstationId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateSubstationCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateSubstationCommand" );
    	UpdateSubstationEvent event = new UpdateSubstationEvent(command.getSubstationId(), command.getRegion());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteSubstationCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteSubstationCommand" );
        apply(new DeleteSubstationEvent(command.getSubstationId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignRegionToSubstationCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRegionToSubstationCommand" );
    	
    	if (  region != null && region.getSubGeographicalRegionId() == command.getAssignment().getSubGeographicalRegionId() )
    		throw new ProcessingException( "Region already assigned with id " + command.getAssignment().getSubGeographicalRegionId() );  
    		
        apply(new AssignRegionToSubstationEvent(command.getSubstationId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRegionFromSubstationCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRegionFromSubstationCommand" );

    	if (  region == null )
    		throw new ProcessingException( "Region already has nothing assigned." );  

    	apply(new UnAssignRegionFromSubstationEvent(command.getSubstationId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateSubstationEvent event) {	
    	LOGGER.info( "Event sourcing CreateSubstationEvent" );
    	this.substationId = event.getSubstationId();
    }
    
    @EventSourcingHandler
    void on(UpdateSubstationEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.region = event.getRegion();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignRegionToSubstationEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRegionToSubstationEvent" );
    	this.region = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRegionFromSubstationEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRegionFromSubstationEvent" );
		this.region = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID substationId;
    
    private DCConverterUnit substation = null;
    private SubGeographicalRegion region = null;

    private static final Logger LOGGER 	= Logger.getLogger(SubstationAggregate.class.getName());
}
