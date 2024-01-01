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
 * Aggregate handler for RatioTapChangerTablePoint as outlined for the CQRS pattern, all write responsibilities 
 * related to RatioTapChangerTablePoint are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class RatioTapChangerTablePointAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public RatioTapChangerTablePointAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public RatioTapChangerTablePointAggregate(CreateRatioTapChangerTablePointCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateRatioTapChangerTablePointCommand" );
    	CreateRatioTapChangerTablePointEvent event = new CreateRatioTapChangerTablePointEvent(command.getRatioTapChangerTablePointId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateRatioTapChangerTablePointCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateRatioTapChangerTablePointCommand" );
    	UpdateRatioTapChangerTablePointEvent event = new UpdateRatioTapChangerTablePointEvent(command.getRatioTapChangerTablePointId(), command.getRatioTapChangerTable());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteRatioTapChangerTablePointCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteRatioTapChangerTablePointCommand" );
        apply(new DeleteRatioTapChangerTablePointEvent(command.getRatioTapChangerTablePointId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignRatioTapChangerTableToRatioTapChangerTablePointCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRatioTapChangerTableToRatioTapChangerTablePointCommand" );
    	
    	if (  ratioTapChangerTable != null && ratioTapChangerTable.getRatioTapChangerTableId() == command.getAssignment().getRatioTapChangerTableId() )
    		throw new ProcessingException( "RatioTapChangerTable already assigned with id " + command.getAssignment().getRatioTapChangerTableId() );  
    		
        apply(new AssignRatioTapChangerTableToRatioTapChangerTablePointEvent(command.getRatioTapChangerTablePointId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRatioTapChangerTableFromRatioTapChangerTablePointCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRatioTapChangerTableFromRatioTapChangerTablePointCommand" );

    	if (  ratioTapChangerTable == null )
    		throw new ProcessingException( "RatioTapChangerTable already has nothing assigned." );  

    	apply(new UnAssignRatioTapChangerTableFromRatioTapChangerTablePointEvent(command.getRatioTapChangerTablePointId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateRatioTapChangerTablePointEvent event) {	
    	LOGGER.info( "Event sourcing CreateRatioTapChangerTablePointEvent" );
    	this.ratioTapChangerTablePointId = event.getRatioTapChangerTablePointId();
    }
    
    @EventSourcingHandler
    void on(UpdateRatioTapChangerTablePointEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.ratioTapChangerTable = event.getRatioTapChangerTable();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignRatioTapChangerTableToRatioTapChangerTablePointEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRatioTapChangerTableToRatioTapChangerTablePointEvent" );
    	this.ratioTapChangerTable = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRatioTapChangerTableFromRatioTapChangerTablePointEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRatioTapChangerTableFromRatioTapChangerTablePointEvent" );
		this.ratioTapChangerTable = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID ratioTapChangerTablePointId;
    
    private RatioTapChangerTable ratioTapChangerTable = null;

    private static final Logger LOGGER 	= Logger.getLogger(RatioTapChangerTablePointAggregate.class.getName());
}
