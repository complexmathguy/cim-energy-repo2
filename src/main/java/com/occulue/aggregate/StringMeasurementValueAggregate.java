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
 * Aggregate handler for StringMeasurementValue as outlined for the CQRS pattern, all write responsibilities 
 * related to StringMeasurementValue are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class StringMeasurementValueAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public StringMeasurementValueAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public StringMeasurementValueAggregate(CreateStringMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateStringMeasurementValueCommand" );
    	CreateStringMeasurementValueEvent event = new CreateStringMeasurementValueEvent(command.getStringMeasurementValueId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateStringMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateStringMeasurementValueCommand" );
    	UpdateStringMeasurementValueEvent event = new UpdateStringMeasurementValueEvent(command.getStringMeasurementValueId(), command.getValue(), command.getStringMeasurement());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteStringMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteStringMeasurementValueCommand" );
        apply(new DeleteStringMeasurementValueEvent(command.getStringMeasurementValueId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignValueToStringMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueToStringMeasurementValueCommand" );
    	
    	if (  value != null && value.getStringProxyId() == command.getAssignment().getStringProxyId() )
    		throw new ProcessingException( "Value already assigned with id " + command.getAssignment().getStringProxyId() );  
    		
        apply(new AssignValueToStringMeasurementValueEvent(command.getStringMeasurementValueId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignValueFromStringMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignValueFromStringMeasurementValueCommand" );

    	if (  value == null )
    		throw new ProcessingException( "Value already has nothing assigned." );  

    	apply(new UnAssignValueFromStringMeasurementValueEvent(command.getStringMeasurementValueId()));
    }
    @CommandHandler
    public void handle(AssignStringMeasurementToStringMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignStringMeasurementToStringMeasurementValueCommand" );
    	
    	if (  stringMeasurement != null && stringMeasurement.getStringMeasurementId() == command.getAssignment().getStringMeasurementId() )
    		throw new ProcessingException( "StringMeasurement already assigned with id " + command.getAssignment().getStringMeasurementId() );  
    		
        apply(new AssignStringMeasurementToStringMeasurementValueEvent(command.getStringMeasurementValueId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignStringMeasurementFromStringMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignStringMeasurementFromStringMeasurementValueCommand" );

    	if (  stringMeasurement == null )
    		throw new ProcessingException( "StringMeasurement already has nothing assigned." );  

    	apply(new UnAssignStringMeasurementFromStringMeasurementValueEvent(command.getStringMeasurementValueId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateStringMeasurementValueEvent event) {	
    	LOGGER.info( "Event sourcing CreateStringMeasurementValueEvent" );
    	this.stringMeasurementValueId = event.getStringMeasurementValueId();
    }
    
    @EventSourcingHandler
    void on(UpdateStringMeasurementValueEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.value = event.getValue();
        this.stringMeasurement = event.getStringMeasurement();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignValueToStringMeasurementValueEvent event ) {	
    	LOGGER.info( "Event sourcing AssignValueToStringMeasurementValueEvent" );
    	this.value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignValueFromStringMeasurementValueEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignValueFromStringMeasurementValueEvent" );
		this.value = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignStringMeasurementToStringMeasurementValueEvent event ) {	
    	LOGGER.info( "Event sourcing AssignStringMeasurementToStringMeasurementValueEvent" );
    	this.stringMeasurement = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignStringMeasurementFromStringMeasurementValueEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignStringMeasurementFromStringMeasurementValueEvent" );
		this.stringMeasurement = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID stringMeasurementValueId;
    
    private StringProxy value = null;
    private StringMeasurement stringMeasurement = null;

    private static final Logger LOGGER 	= Logger.getLogger(StringMeasurementValueAggregate.class.getName());
}
