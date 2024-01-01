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
 * Aggregate handler for WindTurbineType4aIEC as outlined for the CQRS pattern, all write responsibilities 
 * related to WindTurbineType4aIEC are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindTurbineType4aIECAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindTurbineType4aIECAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindTurbineType4aIECAggregate(CreateWindTurbineType4aIECCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindTurbineType4aIECCommand" );
    	CreateWindTurbineType4aIECEvent event = new CreateWindTurbineType4aIECEvent(command.getWindTurbineType4aIECId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindTurbineType4aIECCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindTurbineType4aIECCommand" );
    	UpdateWindTurbineType4aIECEvent event = new UpdateWindTurbineType4aIECEvent(command.getWindTurbineType4aIECId(), command.getWindContPType4aIEC());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindTurbineType4aIECCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindTurbineType4aIECCommand" );
        apply(new DeleteWindTurbineType4aIECEvent(command.getWindTurbineType4aIECId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignWindContPType4aIECToWindTurbineType4aIECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindContPType4aIECToWindTurbineType4aIECCommand" );
    	
    	if (  windContPType4aIEC != null && windContPType4aIEC.getWindContPType4aIECId() == command.getAssignment().getWindContPType4aIECId() )
    		throw new ProcessingException( "WindContPType4aIEC already assigned with id " + command.getAssignment().getWindContPType4aIECId() );  
    		
        apply(new AssignWindContPType4aIECToWindTurbineType4aIECEvent(command.getWindTurbineType4aIECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindContPType4aIECFromWindTurbineType4aIECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindContPType4aIECFromWindTurbineType4aIECCommand" );

    	if (  windContPType4aIEC == null )
    		throw new ProcessingException( "WindContPType4aIEC already has nothing assigned." );  

    	apply(new UnAssignWindContPType4aIECFromWindTurbineType4aIECEvent(command.getWindTurbineType4aIECId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindTurbineType4aIECEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindTurbineType4aIECEvent" );
    	this.windTurbineType4aIECId = event.getWindTurbineType4aIECId();
    }
    
    @EventSourcingHandler
    void on(UpdateWindTurbineType4aIECEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.windContPType4aIEC = event.getWindContPType4aIEC();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignWindContPType4aIECToWindTurbineType4aIECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindContPType4aIECToWindTurbineType4aIECEvent" );
    	this.windContPType4aIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindContPType4aIECFromWindTurbineType4aIECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindContPType4aIECFromWindTurbineType4aIECEvent" );
		this.windContPType4aIEC = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windTurbineType4aIECId;
    
    private WindContPType4aIEC windContPType4aIEC = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindTurbineType4aIECAggregate.class.getName());
}
