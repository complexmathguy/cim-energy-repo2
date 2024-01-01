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
 * Aggregate handler for AccumulatorLimit as outlined for the CQRS pattern, all write responsibilities 
 * related to AccumulatorLimit are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class AccumulatorLimitAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public AccumulatorLimitAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public AccumulatorLimitAggregate(CreateAccumulatorLimitCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateAccumulatorLimitCommand" );
    	CreateAccumulatorLimitEvent event = new CreateAccumulatorLimitEvent(command.getAccumulatorLimitId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateAccumulatorLimitCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateAccumulatorLimitCommand" );
    	UpdateAccumulatorLimitEvent event = new UpdateAccumulatorLimitEvent(command.getAccumulatorLimitId(), command.getValue(), command.getLimitSet());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteAccumulatorLimitCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteAccumulatorLimitCommand" );
        apply(new DeleteAccumulatorLimitEvent(command.getAccumulatorLimitId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignValueToAccumulatorLimitCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueToAccumulatorLimitCommand" );
    	
    	if (  value != null && value.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "Value already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignValueToAccumulatorLimitEvent(command.getAccumulatorLimitId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignValueFromAccumulatorLimitCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignValueFromAccumulatorLimitCommand" );

    	if (  value == null )
    		throw new ProcessingException( "Value already has nothing assigned." );  

    	apply(new UnAssignValueFromAccumulatorLimitEvent(command.getAccumulatorLimitId()));
    }

    // multiple association commands
    @CommandHandler
    public void handle(AssignLimitSetToAccumulatorLimitCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignLimitSetToAccumulatorLimitCommand" );
    	
    	if ( limitSet.contains( command.getAddTo() ) )
    		throw new ProcessingException( "LimitSet already contains an entity with id " + command.getAddTo().getAccumulatorLimitSetId() );

    	apply(new AssignLimitSetToAccumulatorLimitEvent(command.getAccumulatorLimitId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemoveLimitSetFromAccumulatorLimitCommand command) throws Exception {
    	LOGGER.info( "Handling command RemoveLimitSetFromAccumulatorLimitCommand" );
    	
    	if ( !limitSet.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "LimitSet does not contain an entity with id " + command.getRemoveFrom().getAccumulatorLimitSetId() );

        apply(new RemoveLimitSetFromAccumulatorLimitEvent(command.getAccumulatorLimitId(), command.getRemoveFrom()));
    }

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateAccumulatorLimitEvent event) {	
    	LOGGER.info( "Event sourcing CreateAccumulatorLimitEvent" );
    	this.accumulatorLimitId = event.getAccumulatorLimitId();
    }
    
    @EventSourcingHandler
    void on(UpdateAccumulatorLimitEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.value = event.getValue();
        this.limitSet = event.getLimitSet();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignValueToAccumulatorLimitEvent event ) {	
    	LOGGER.info( "Event sourcing AssignValueToAccumulatorLimitEvent" );
    	this.value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignValueFromAccumulatorLimitEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignValueFromAccumulatorLimitEvent" );
		this.value = null;
	}

	// multiple associations
    @EventSourcingHandler
    void on(AssignLimitSetToAccumulatorLimitEvent event ) {
    	LOGGER.info( "Event sourcing AssignLimitSetToAccumulatorLimitEvent" );
    	this.limitSet.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemoveLimitSetFromAccumulatorLimitEvent event ) {	
		LOGGER.info( "Event sourcing RemoveLimitSetFromAccumulatorLimitEvent" );
		this.limitSet.remove( event.getRemoveFrom() );
	}
	

    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID accumulatorLimitId;
    
    private IntegerProxy value = null;
    private Set<AccumulatorLimitSet> limitSet = new HashSet<>();

    private static final Logger LOGGER 	= Logger.getLogger(AccumulatorLimitAggregate.class.getName());
}
