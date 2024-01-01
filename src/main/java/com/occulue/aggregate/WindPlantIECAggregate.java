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
 * Aggregate handler for WindPlantIEC as outlined for the CQRS pattern, all write responsibilities 
 * related to WindPlantIEC are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindPlantIECAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindPlantIECAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindPlantIECAggregate(CreateWindPlantIECCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindPlantIECCommand" );
    	CreateWindPlantIECEvent event = new CreateWindPlantIECEvent(command.getWindPlantIECId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindPlantIECCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindPlantIECCommand" );
    	UpdateWindPlantIECEvent event = new UpdateWindPlantIECEvent(command.getWindPlantIECId(), command.getWindPlantFreqPcontrolIEC(), command.getWindPlantReactiveControlIEC());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindPlantIECCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindPlantIECCommand" );
        apply(new DeleteWindPlantIECEvent(command.getWindPlantIECId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignWindPlantFreqPcontrolIECToWindPlantIECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindPlantFreqPcontrolIECToWindPlantIECCommand" );
    	
    	if (  windPlantFreqPcontrolIEC != null && windPlantFreqPcontrolIEC.getWindPlantFreqPcontrolIECId() == command.getAssignment().getWindPlantFreqPcontrolIECId() )
    		throw new ProcessingException( "WindPlantFreqPcontrolIEC already assigned with id " + command.getAssignment().getWindPlantFreqPcontrolIECId() );  
    		
        apply(new AssignWindPlantFreqPcontrolIECToWindPlantIECEvent(command.getWindPlantIECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindPlantFreqPcontrolIECFromWindPlantIECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindPlantFreqPcontrolIECFromWindPlantIECCommand" );

    	if (  windPlantFreqPcontrolIEC == null )
    		throw new ProcessingException( "WindPlantFreqPcontrolIEC already has nothing assigned." );  

    	apply(new UnAssignWindPlantFreqPcontrolIECFromWindPlantIECEvent(command.getWindPlantIECId()));
    }
    @CommandHandler
    public void handle(AssignWindPlantReactiveControlIECToWindPlantIECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindPlantReactiveControlIECToWindPlantIECCommand" );
    	
    	if (  windPlantReactiveControlIEC != null && windPlantReactiveControlIEC.getWindPlantReactiveControlIECId() == command.getAssignment().getWindPlantReactiveControlIECId() )
    		throw new ProcessingException( "WindPlantReactiveControlIEC already assigned with id " + command.getAssignment().getWindPlantReactiveControlIECId() );  
    		
        apply(new AssignWindPlantReactiveControlIECToWindPlantIECEvent(command.getWindPlantIECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindPlantReactiveControlIECFromWindPlantIECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindPlantReactiveControlIECFromWindPlantIECCommand" );

    	if (  windPlantReactiveControlIEC == null )
    		throw new ProcessingException( "WindPlantReactiveControlIEC already has nothing assigned." );  

    	apply(new UnAssignWindPlantReactiveControlIECFromWindPlantIECEvent(command.getWindPlantIECId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindPlantIECEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindPlantIECEvent" );
    	this.windPlantIECId = event.getWindPlantIECId();
    }
    
    @EventSourcingHandler
    void on(UpdateWindPlantIECEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.windPlantFreqPcontrolIEC = event.getWindPlantFreqPcontrolIEC();
        this.windPlantReactiveControlIEC = event.getWindPlantReactiveControlIEC();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignWindPlantFreqPcontrolIECToWindPlantIECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindPlantFreqPcontrolIECToWindPlantIECEvent" );
    	this.windPlantFreqPcontrolIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindPlantFreqPcontrolIECFromWindPlantIECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindPlantFreqPcontrolIECFromWindPlantIECEvent" );
		this.windPlantFreqPcontrolIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindPlantReactiveControlIECToWindPlantIECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindPlantReactiveControlIECToWindPlantIECEvent" );
    	this.windPlantReactiveControlIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindPlantReactiveControlIECFromWindPlantIECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindPlantReactiveControlIECFromWindPlantIECEvent" );
		this.windPlantReactiveControlIEC = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windPlantIECId;
    
    private WindPlantFreqPcontrolIEC windPlantFreqPcontrolIEC = null;
    private WindPlantReactiveControlIEC windPlantReactiveControlIEC = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindPlantIECAggregate.class.getName());
}
