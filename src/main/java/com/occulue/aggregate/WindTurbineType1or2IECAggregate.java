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
 * Aggregate handler for WindTurbineType1or2IEC as outlined for the CQRS pattern, all write responsibilities 
 * related to WindTurbineType1or2IEC are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindTurbineType1or2IECAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindTurbineType1or2IECAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindTurbineType1or2IECAggregate(CreateWindTurbineType1or2IECCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindTurbineType1or2IECCommand" );
    	CreateWindTurbineType1or2IECEvent event = new CreateWindTurbineType1or2IECEvent(command.getWindTurbineType1or2IECId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindTurbineType1or2IECCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindTurbineType1or2IECCommand" );
    	UpdateWindTurbineType1or2IECEvent event = new UpdateWindTurbineType1or2IECEvent(command.getWindTurbineType1or2IECId(), command.getWindProtectionIEC(), command.getWindMechIEC());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindTurbineType1or2IECCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindTurbineType1or2IECCommand" );
        apply(new DeleteWindTurbineType1or2IECEvent(command.getWindTurbineType1or2IECId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignWindProtectionIECToWindTurbineType1or2IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindProtectionIECToWindTurbineType1or2IECCommand" );
    	
    	if (  windProtectionIEC != null && windProtectionIEC.getWindProtectionIECId() == command.getAssignment().getWindProtectionIECId() )
    		throw new ProcessingException( "WindProtectionIEC already assigned with id " + command.getAssignment().getWindProtectionIECId() );  
    		
        apply(new AssignWindProtectionIECToWindTurbineType1or2IECEvent(command.getWindTurbineType1or2IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindProtectionIECFromWindTurbineType1or2IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindProtectionIECFromWindTurbineType1or2IECCommand" );

    	if (  windProtectionIEC == null )
    		throw new ProcessingException( "WindProtectionIEC already has nothing assigned." );  

    	apply(new UnAssignWindProtectionIECFromWindTurbineType1or2IECEvent(command.getWindTurbineType1or2IECId()));
    }
    @CommandHandler
    public void handle(AssignWindMechIECToWindTurbineType1or2IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindMechIECToWindTurbineType1or2IECCommand" );
    	
    	if (  windMechIEC != null && windMechIEC.getWindMechIECId() == command.getAssignment().getWindMechIECId() )
    		throw new ProcessingException( "WindMechIEC already assigned with id " + command.getAssignment().getWindMechIECId() );  
    		
        apply(new AssignWindMechIECToWindTurbineType1or2IECEvent(command.getWindTurbineType1or2IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindMechIECFromWindTurbineType1or2IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindMechIECFromWindTurbineType1or2IECCommand" );

    	if (  windMechIEC == null )
    		throw new ProcessingException( "WindMechIEC already has nothing assigned." );  

    	apply(new UnAssignWindMechIECFromWindTurbineType1or2IECEvent(command.getWindTurbineType1or2IECId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindTurbineType1or2IECEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindTurbineType1or2IECEvent" );
    	this.windTurbineType1or2IECId = event.getWindTurbineType1or2IECId();
    }
    
    @EventSourcingHandler
    void on(UpdateWindTurbineType1or2IECEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.windProtectionIEC = event.getWindProtectionIEC();
        this.windMechIEC = event.getWindMechIEC();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignWindProtectionIECToWindTurbineType1or2IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindProtectionIECToWindTurbineType1or2IECEvent" );
    	this.windProtectionIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindProtectionIECFromWindTurbineType1or2IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindProtectionIECFromWindTurbineType1or2IECEvent" );
		this.windProtectionIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindMechIECToWindTurbineType1or2IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindMechIECToWindTurbineType1or2IECEvent" );
    	this.windMechIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindMechIECFromWindTurbineType1or2IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindMechIECFromWindTurbineType1or2IECEvent" );
		this.windMechIEC = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windTurbineType1or2IECId;
    
    private WindProtectionIEC windProtectionIEC = null;
    private WindMechIEC windMechIEC = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindTurbineType1or2IECAggregate.class.getName());
}
