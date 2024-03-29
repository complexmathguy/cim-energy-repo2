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
 * Aggregate handler for WindGenTurbineType2IEC as outlined for the CQRS pattern, all write responsibilities 
 * related to WindGenTurbineType2IEC are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindGenTurbineType2IECAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindGenTurbineType2IECAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindGenTurbineType2IECAggregate(CreateWindGenTurbineType2IECCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindGenTurbineType2IECCommand" );
    	CreateWindGenTurbineType2IECEvent event = new CreateWindGenTurbineType2IECEvent(command.getWindGenTurbineType2IECId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindGenTurbineType2IECCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindGenTurbineType2IECCommand" );
    	UpdateWindGenTurbineType2IECEvent event = new UpdateWindGenTurbineType2IECEvent(command.getWindGenTurbineType2IECId(), command.getWindContRotorRIEC(), command.getWindPitchContEmulIEC());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindGenTurbineType2IECCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindGenTurbineType2IECCommand" );
        apply(new DeleteWindGenTurbineType2IECEvent(command.getWindGenTurbineType2IECId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignWindContRotorRIECToWindGenTurbineType2IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindContRotorRIECToWindGenTurbineType2IECCommand" );
    	
    	if (  windContRotorRIEC != null && windContRotorRIEC.getWindContRotorRIECId() == command.getAssignment().getWindContRotorRIECId() )
    		throw new ProcessingException( "WindContRotorRIEC already assigned with id " + command.getAssignment().getWindContRotorRIECId() );  
    		
        apply(new AssignWindContRotorRIECToWindGenTurbineType2IECEvent(command.getWindGenTurbineType2IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindContRotorRIECFromWindGenTurbineType2IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindContRotorRIECFromWindGenTurbineType2IECCommand" );

    	if (  windContRotorRIEC == null )
    		throw new ProcessingException( "WindContRotorRIEC already has nothing assigned." );  

    	apply(new UnAssignWindContRotorRIECFromWindGenTurbineType2IECEvent(command.getWindGenTurbineType2IECId()));
    }
    @CommandHandler
    public void handle(AssignWindPitchContEmulIECToWindGenTurbineType2IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindPitchContEmulIECToWindGenTurbineType2IECCommand" );
    	
    	if (  windPitchContEmulIEC != null && windPitchContEmulIEC.getWindPitchContEmulIECId() == command.getAssignment().getWindPitchContEmulIECId() )
    		throw new ProcessingException( "WindPitchContEmulIEC already assigned with id " + command.getAssignment().getWindPitchContEmulIECId() );  
    		
        apply(new AssignWindPitchContEmulIECToWindGenTurbineType2IECEvent(command.getWindGenTurbineType2IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindPitchContEmulIECFromWindGenTurbineType2IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindPitchContEmulIECFromWindGenTurbineType2IECCommand" );

    	if (  windPitchContEmulIEC == null )
    		throw new ProcessingException( "WindPitchContEmulIEC already has nothing assigned." );  

    	apply(new UnAssignWindPitchContEmulIECFromWindGenTurbineType2IECEvent(command.getWindGenTurbineType2IECId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindGenTurbineType2IECEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindGenTurbineType2IECEvent" );
    	this.windGenTurbineType2IECId = event.getWindGenTurbineType2IECId();
    }
    
    @EventSourcingHandler
    void on(UpdateWindGenTurbineType2IECEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.windContRotorRIEC = event.getWindContRotorRIEC();
        this.windPitchContEmulIEC = event.getWindPitchContEmulIEC();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignWindContRotorRIECToWindGenTurbineType2IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindContRotorRIECToWindGenTurbineType2IECEvent" );
    	this.windContRotorRIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindContRotorRIECFromWindGenTurbineType2IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindContRotorRIECFromWindGenTurbineType2IECEvent" );
		this.windContRotorRIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindPitchContEmulIECToWindGenTurbineType2IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindPitchContEmulIECToWindGenTurbineType2IECEvent" );
    	this.windPitchContEmulIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindPitchContEmulIECFromWindGenTurbineType2IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindPitchContEmulIECFromWindGenTurbineType2IECEvent" );
		this.windPitchContEmulIEC = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windGenTurbineType2IECId;
    
    private WindContRotorRIEC windContRotorRIEC = null;
    private WindPitchContEmulIEC windPitchContEmulIEC = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindGenTurbineType2IECAggregate.class.getName());
}
