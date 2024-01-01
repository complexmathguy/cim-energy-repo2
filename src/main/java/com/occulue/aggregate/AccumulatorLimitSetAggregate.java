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
 * Aggregate handler for AccumulatorLimitSet as outlined for the CQRS pattern, all write responsibilities 
 * related to AccumulatorLimitSet are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class AccumulatorLimitSetAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public AccumulatorLimitSetAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public AccumulatorLimitSetAggregate(CreateAccumulatorLimitSetCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateAccumulatorLimitSetCommand" );
    	CreateAccumulatorLimitSetEvent event = new CreateAccumulatorLimitSetEvent(command.getAccumulatorLimitSetId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateAccumulatorLimitSetCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateAccumulatorLimitSetCommand" );
    	UpdateAccumulatorLimitSetEvent event = new UpdateAccumulatorLimitSetEvent(command.getAccumulatorLimitSetId(), command.getMeasurements());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteAccumulatorLimitSetCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteAccumulatorLimitSetCommand" );
        apply(new DeleteAccumulatorLimitSetEvent(command.getAccumulatorLimitSetId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignMeasurementsToAccumulatorLimitSetCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignMeasurementsToAccumulatorLimitSetCommand" );
    	
    	if (  measurements != null && measurements.getAccumulatorId() == command.getAssignment().getAccumulatorId() )
    		throw new ProcessingException( "Measurements already assigned with id " + command.getAssignment().getAccumulatorId() );  
    		
        apply(new AssignMeasurementsToAccumulatorLimitSetEvent(command.getAccumulatorLimitSetId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignMeasurementsFromAccumulatorLimitSetCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignMeasurementsFromAccumulatorLimitSetCommand" );

    	if (  measurements == null )
    		throw new ProcessingException( "Measurements already has nothing assigned." );  

    	apply(new UnAssignMeasurementsFromAccumulatorLimitSetEvent(command.getAccumulatorLimitSetId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateAccumulatorLimitSetEvent event) {	
    	LOGGER.info( "Event sourcing CreateAccumulatorLimitSetEvent" );
    	this.accumulatorLimitSetId = event.getAccumulatorLimitSetId();
    }
    
    @EventSourcingHandler
    void on(UpdateAccumulatorLimitSetEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.measurements = event.getMeasurements();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignMeasurementsToAccumulatorLimitSetEvent event ) {	
    	LOGGER.info( "Event sourcing AssignMeasurementsToAccumulatorLimitSetEvent" );
    	this.measurements = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignMeasurementsFromAccumulatorLimitSetEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignMeasurementsFromAccumulatorLimitSetEvent" );
		this.measurements = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID accumulatorLimitSetId;
    
    private Set<AccumulatorLimit> limitSet = new HashSet<>();
    private Accumulator measurements = null;

    private static final Logger LOGGER 	= Logger.getLogger(AccumulatorLimitSetAggregate.class.getName());
}
