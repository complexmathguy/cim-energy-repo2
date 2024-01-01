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
 * Aggregate handler for Line as outlined for the CQRS pattern, all write responsibilities 
 * related to Line are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class LineAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public LineAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public LineAggregate(CreateLineCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateLineCommand" );
    	CreateLineEvent event = new CreateLineEvent(command.getLineId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateLineCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateLineCommand" );
    	UpdateLineEvent event = new UpdateLineEvent(command.getLineId(), command.getRegion());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteLineCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteLineCommand" );
        apply(new DeleteLineEvent(command.getLineId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignRegionToLineCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRegionToLineCommand" );
    	
    	if (  region != null && region.getSubGeographicalRegionId() == command.getAssignment().getSubGeographicalRegionId() )
    		throw new ProcessingException( "Region already assigned with id " + command.getAssignment().getSubGeographicalRegionId() );  
    		
        apply(new AssignRegionToLineEvent(command.getLineId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRegionFromLineCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRegionFromLineCommand" );

    	if (  region == null )
    		throw new ProcessingException( "Region already has nothing assigned." );  

    	apply(new UnAssignRegionFromLineEvent(command.getLineId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateLineEvent event) {	
    	LOGGER.info( "Event sourcing CreateLineEvent" );
    	this.lineId = event.getLineId();
    }
    
    @EventSourcingHandler
    void on(UpdateLineEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.region = event.getRegion();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignRegionToLineEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRegionToLineEvent" );
    	this.region = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRegionFromLineEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRegionFromLineEvent" );
		this.region = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID lineId;
    
    private SubGeographicalRegion region = null;

    private static final Logger LOGGER 	= Logger.getLogger(LineAggregate.class.getName());
}
