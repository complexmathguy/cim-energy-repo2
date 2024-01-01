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
 * Aggregate handler for ValueToAlias as outlined for the CQRS pattern, all write responsibilities 
 * related to ValueToAlias are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ValueToAliasAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ValueToAliasAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ValueToAliasAggregate(CreateValueToAliasCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateValueToAliasCommand" );
    	CreateValueToAliasEvent event = new CreateValueToAliasEvent(command.getValueToAliasId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateValueToAliasCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateValueToAliasCommand" );
    	UpdateValueToAliasEvent event = new UpdateValueToAliasEvent(command.getValueToAliasId(), command.getValue(), command.getValueAliasSet());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteValueToAliasCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteValueToAliasCommand" );
        apply(new DeleteValueToAliasEvent(command.getValueToAliasId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignValueToValueToAliasCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueToValueToAliasCommand" );
    	
    	if (  value != null && value.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "Value already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignValueToValueToAliasEvent(command.getValueToAliasId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignValueFromValueToAliasCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignValueFromValueToAliasCommand" );

    	if (  value == null )
    		throw new ProcessingException( "Value already has nothing assigned." );  

    	apply(new UnAssignValueFromValueToAliasEvent(command.getValueToAliasId()));
    }

    // multiple association commands
    @CommandHandler
    public void handle(AssignValueAliasSetToValueToAliasCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueAliasSetToValueToAliasCommand" );
    	
    	if ( valueAliasSet.contains( command.getAddTo() ) )
    		throw new ProcessingException( "ValueAliasSet already contains an entity with id " + command.getAddTo().getValueAliasSetId() );

    	apply(new AssignValueAliasSetToValueToAliasEvent(command.getValueToAliasId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemoveValueAliasSetFromValueToAliasCommand command) throws Exception {
    	LOGGER.info( "Handling command RemoveValueAliasSetFromValueToAliasCommand" );
    	
    	if ( !valueAliasSet.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "ValueAliasSet does not contain an entity with id " + command.getRemoveFrom().getValueAliasSetId() );

        apply(new RemoveValueAliasSetFromValueToAliasEvent(command.getValueToAliasId(), command.getRemoveFrom()));
    }

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateValueToAliasEvent event) {	
    	LOGGER.info( "Event sourcing CreateValueToAliasEvent" );
    	this.valueToAliasId = event.getValueToAliasId();
    }
    
    @EventSourcingHandler
    void on(UpdateValueToAliasEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.value = event.getValue();
        this.valueAliasSet = event.getValueAliasSet();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignValueToValueToAliasEvent event ) {	
    	LOGGER.info( "Event sourcing AssignValueToValueToAliasEvent" );
    	this.value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignValueFromValueToAliasEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignValueFromValueToAliasEvent" );
		this.value = null;
	}

	// multiple associations
    @EventSourcingHandler
    void on(AssignValueAliasSetToValueToAliasEvent event ) {
    	LOGGER.info( "Event sourcing AssignValueAliasSetToValueToAliasEvent" );
    	this.valueAliasSet.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemoveValueAliasSetFromValueToAliasEvent event ) {	
		LOGGER.info( "Event sourcing RemoveValueAliasSetFromValueToAliasEvent" );
		this.valueAliasSet.remove( event.getRemoveFrom() );
	}
	

    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID valueToAliasId;
    
    private IntegerProxy value = null;
    private Set<ValueAliasSet> valueAliasSet = new HashSet<>();

    private static final Logger LOGGER 	= Logger.getLogger(ValueToAliasAggregate.class.getName());
}
