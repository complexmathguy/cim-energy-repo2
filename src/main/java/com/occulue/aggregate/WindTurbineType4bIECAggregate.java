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
 * Aggregate handler for WindTurbineType4bIEC as outlined for the CQRS pattern, all write responsibilities 
 * related to WindTurbineType4bIEC are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindTurbineType4bIECAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindTurbineType4bIECAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindTurbineType4bIECAggregate(CreateWindTurbineType4bIECCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindTurbineType4bIECCommand" );
    	CreateWindTurbineType4bIECEvent event = new CreateWindTurbineType4bIECEvent(command.getWindTurbineType4bIECId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindTurbineType4bIECCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindTurbineType4bIECCommand" );
    	UpdateWindTurbineType4bIECEvent event = new UpdateWindTurbineType4bIECEvent(command.getWindTurbineType4bIECId(), command.getWindMechIEC(), command.getWindContPType4bIEC());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindTurbineType4bIECCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindTurbineType4bIECCommand" );
        apply(new DeleteWindTurbineType4bIECEvent(command.getWindTurbineType4bIECId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignWindMechIECToWindTurbineType4bIECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindMechIECToWindTurbineType4bIECCommand" );
    	
    	if (  windMechIEC != null && windMechIEC.getWindMechIECId() == command.getAssignment().getWindMechIECId() )
    		throw new ProcessingException( "WindMechIEC already assigned with id " + command.getAssignment().getWindMechIECId() );  
    		
        apply(new AssignWindMechIECToWindTurbineType4bIECEvent(command.getWindTurbineType4bIECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindMechIECFromWindTurbineType4bIECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindMechIECFromWindTurbineType4bIECCommand" );

    	if (  windMechIEC == null )
    		throw new ProcessingException( "WindMechIEC already has nothing assigned." );  

    	apply(new UnAssignWindMechIECFromWindTurbineType4bIECEvent(command.getWindTurbineType4bIECId()));
    }
    @CommandHandler
    public void handle(AssignWindContPType4bIECToWindTurbineType4bIECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindContPType4bIECToWindTurbineType4bIECCommand" );
    	
    	if (  windContPType4bIEC != null && windContPType4bIEC.getWindContPType4bIECId() == command.getAssignment().getWindContPType4bIECId() )
    		throw new ProcessingException( "WindContPType4bIEC already assigned with id " + command.getAssignment().getWindContPType4bIECId() );  
    		
        apply(new AssignWindContPType4bIECToWindTurbineType4bIECEvent(command.getWindTurbineType4bIECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindContPType4bIECFromWindTurbineType4bIECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindContPType4bIECFromWindTurbineType4bIECCommand" );

    	if (  windContPType4bIEC == null )
    		throw new ProcessingException( "WindContPType4bIEC already has nothing assigned." );  

    	apply(new UnAssignWindContPType4bIECFromWindTurbineType4bIECEvent(command.getWindTurbineType4bIECId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindTurbineType4bIECEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindTurbineType4bIECEvent" );
    	this.windTurbineType4bIECId = event.getWindTurbineType4bIECId();
    }
    
    @EventSourcingHandler
    void on(UpdateWindTurbineType4bIECEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.windMechIEC = event.getWindMechIEC();
        this.windContPType4bIEC = event.getWindContPType4bIEC();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignWindMechIECToWindTurbineType4bIECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindMechIECToWindTurbineType4bIECEvent" );
    	this.windMechIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindMechIECFromWindTurbineType4bIECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindMechIECFromWindTurbineType4bIECEvent" );
		this.windMechIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindContPType4bIECToWindTurbineType4bIECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindContPType4bIECToWindTurbineType4bIECEvent" );
    	this.windContPType4bIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindContPType4bIECFromWindTurbineType4bIECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindContPType4bIECFromWindTurbineType4bIECEvent" );
		this.windContPType4bIEC = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windTurbineType4bIECId;
    
    private WindMechIEC windMechIEC = null;
    private WindContPType4bIEC windContPType4bIEC = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindTurbineType4bIECAggregate.class.getName());
}
