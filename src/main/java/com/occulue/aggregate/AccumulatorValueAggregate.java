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
 * Aggregate handler for AccumulatorValue as outlined for the CQRS pattern, all write responsibilities 
 * related to AccumulatorValue are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class AccumulatorValueAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public AccumulatorValueAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public AccumulatorValueAggregate(CreateAccumulatorValueCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateAccumulatorValueCommand" );
    	CreateAccumulatorValueEvent event = new CreateAccumulatorValueEvent(command.getAccumulatorValueId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateAccumulatorValueCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateAccumulatorValueCommand" );
    	UpdateAccumulatorValueEvent event = new UpdateAccumulatorValueEvent(command.getAccumulatorValueId(), command.getValue(), command.getAccumulator());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteAccumulatorValueCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteAccumulatorValueCommand" );
        apply(new DeleteAccumulatorValueEvent(command.getAccumulatorValueId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignValueToAccumulatorValueCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueToAccumulatorValueCommand" );
    	
    	if (  value != null && value.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "Value already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignValueToAccumulatorValueEvent(command.getAccumulatorValueId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignValueFromAccumulatorValueCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignValueFromAccumulatorValueCommand" );

    	if (  value == null )
    		throw new ProcessingException( "Value already has nothing assigned." );  

    	apply(new UnAssignValueFromAccumulatorValueEvent(command.getAccumulatorValueId()));
    }
    @CommandHandler
    public void handle(AssignAccumulatorToAccumulatorValueCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignAccumulatorToAccumulatorValueCommand" );
    	
    	if (  accumulator != null && accumulator.getAccumulatorId() == command.getAssignment().getAccumulatorId() )
    		throw new ProcessingException( "Accumulator already assigned with id " + command.getAssignment().getAccumulatorId() );  
    		
        apply(new AssignAccumulatorToAccumulatorValueEvent(command.getAccumulatorValueId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignAccumulatorFromAccumulatorValueCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignAccumulatorFromAccumulatorValueCommand" );

    	if (  accumulator == null )
    		throw new ProcessingException( "Accumulator already has nothing assigned." );  

    	apply(new UnAssignAccumulatorFromAccumulatorValueEvent(command.getAccumulatorValueId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateAccumulatorValueEvent event) {	
    	LOGGER.info( "Event sourcing CreateAccumulatorValueEvent" );
    	this.accumulatorValueId = event.getAccumulatorValueId();
    }
    
    @EventSourcingHandler
    void on(UpdateAccumulatorValueEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.value = event.getValue();
        this.accumulator = event.getAccumulator();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignValueToAccumulatorValueEvent event ) {	
    	LOGGER.info( "Event sourcing AssignValueToAccumulatorValueEvent" );
    	this.value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignValueFromAccumulatorValueEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignValueFromAccumulatorValueEvent" );
		this.value = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignAccumulatorToAccumulatorValueEvent event ) {	
    	LOGGER.info( "Event sourcing AssignAccumulatorToAccumulatorValueEvent" );
    	this.accumulator = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignAccumulatorFromAccumulatorValueEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignAccumulatorFromAccumulatorValueEvent" );
		this.accumulator = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID accumulatorValueId;
    
    private AccumulatorReset accumulatorValue = null;
    private IntegerProxy value = null;
    private Accumulator accumulator = null;

    private static final Logger LOGGER 	= Logger.getLogger(AccumulatorValueAggregate.class.getName());
}
