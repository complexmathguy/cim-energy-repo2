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
 * Aggregate handler for Command as outlined for the CQRS pattern, all write responsibilities 
 * related to Command are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class CommandAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public CommandAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public CommandAggregate(CreateCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateCommandCommand" );
    	CreateCommandEvent event = new CreateCommandEvent(command.getCommandId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateCommandCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateCommandCommand" );
    	UpdateCommandEvent event = new UpdateCommandEvent(command.getCommandId(), command.getNormalValue(), command.getValue(), command.getValueAliasSet(), command.getDiscreteValue());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteCommandCommand" );
        apply(new DeleteCommandEvent(command.getCommandId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignNormalValueToCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignNormalValueToCommandCommand" );
    	
    	if (  normalValue != null && normalValue.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "NormalValue already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignNormalValueToCommandEvent(command.getCommandId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignNormalValueFromCommandCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignNormalValueFromCommandCommand" );

    	if (  normalValue == null )
    		throw new ProcessingException( "NormalValue already has nothing assigned." );  

    	apply(new UnAssignNormalValueFromCommandEvent(command.getCommandId()));
    }
    @CommandHandler
    public void handle(AssignValueToCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueToCommandCommand" );
    	
    	if (  value != null && value.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "Value already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignValueToCommandEvent(command.getCommandId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignValueFromCommandCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignValueFromCommandCommand" );

    	if (  value == null )
    		throw new ProcessingException( "Value already has nothing assigned." );  

    	apply(new UnAssignValueFromCommandEvent(command.getCommandId()));
    }

    // multiple association commands
    @CommandHandler
    public void handle(AssignValueAliasSetToCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueAliasSetToCommandCommand" );
    	
    	if ( valueAliasSet.contains( command.getAddTo() ) )
    		throw new ProcessingException( "ValueAliasSet already contains an entity with id " + command.getAddTo().getValueAliasSetId() );

    	apply(new AssignValueAliasSetToCommandEvent(command.getCommandId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemoveValueAliasSetFromCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command RemoveValueAliasSetFromCommandCommand" );
    	
    	if ( !valueAliasSet.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "ValueAliasSet does not contain an entity with id " + command.getRemoveFrom().getValueAliasSetId() );

        apply(new RemoveValueAliasSetFromCommandEvent(command.getCommandId(), command.getRemoveFrom()));
    }
    @CommandHandler
    public void handle(AssignDiscreteValueToCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDiscreteValueToCommandCommand" );
    	
    	if ( discreteValue.contains( command.getAddTo() ) )
    		throw new ProcessingException( "DiscreteValue already contains an entity with id " + command.getAddTo().getDiscreteValueId() );

    	apply(new AssignDiscreteValueToCommandEvent(command.getCommandId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemoveDiscreteValueFromCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command RemoveDiscreteValueFromCommandCommand" );
    	
    	if ( !discreteValue.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "DiscreteValue does not contain an entity with id " + command.getRemoveFrom().getDiscreteValueId() );

        apply(new RemoveDiscreteValueFromCommandEvent(command.getCommandId(), command.getRemoveFrom()));
    }

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateCommandEvent event) {	
    	LOGGER.info( "Event sourcing CreateCommandEvent" );
    	this.commandId = event.getCommandId();
    }
    
    @EventSourcingHandler
    void on(UpdateCommandEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.normalValue = event.getNormalValue();
        this.value = event.getValue();
        this.valueAliasSet = event.getValueAliasSet();
        this.discreteValue = event.getDiscreteValue();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignNormalValueToCommandEvent event ) {	
    	LOGGER.info( "Event sourcing AssignNormalValueToCommandEvent" );
    	this.normalValue = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignNormalValueFromCommandEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignNormalValueFromCommandEvent" );
		this.normalValue = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignValueToCommandEvent event ) {	
    	LOGGER.info( "Event sourcing AssignValueToCommandEvent" );
    	this.value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignValueFromCommandEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignValueFromCommandEvent" );
		this.value = null;
	}

	// multiple associations
    @EventSourcingHandler
    void on(AssignValueAliasSetToCommandEvent event ) {
    	LOGGER.info( "Event sourcing AssignValueAliasSetToCommandEvent" );
    	this.valueAliasSet.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemoveValueAliasSetFromCommandEvent event ) {	
		LOGGER.info( "Event sourcing RemoveValueAliasSetFromCommandEvent" );
		this.valueAliasSet.remove( event.getRemoveFrom() );
	}
	
	// multiple associations
    @EventSourcingHandler
    void on(AssignDiscreteValueToCommandEvent event ) {
    	LOGGER.info( "Event sourcing AssignDiscreteValueToCommandEvent" );
    	this.discreteValue.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemoveDiscreteValueFromCommandEvent event ) {	
		LOGGER.info( "Event sourcing RemoveDiscreteValueFromCommandEvent" );
		this.discreteValue.remove( event.getRemoveFrom() );
	}
	

    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID commandId;
    
    private IntegerProxy normalValue = null;
    private IntegerProxy value = null;
    private Set<ValueAliasSet> valueAliasSet = new HashSet<>();
    private Set<DiscreteValue> discreteValue = new HashSet<>();

    private static final Logger LOGGER 	= Logger.getLogger(CommandAggregate.class.getName());
}
