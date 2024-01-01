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
 * Aggregate handler for RaiseLowerCommand as outlined for the CQRS pattern, all write responsibilities 
 * related to RaiseLowerCommand are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class RaiseLowerCommandAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public RaiseLowerCommandAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public RaiseLowerCommandAggregate(CreateRaiseLowerCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateRaiseLowerCommandCommand" );
    	CreateRaiseLowerCommandEvent event = new CreateRaiseLowerCommandEvent(command.getRaiseLowerCommandId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateRaiseLowerCommandCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateRaiseLowerCommandCommand" );
    	UpdateRaiseLowerCommandEvent event = new UpdateRaiseLowerCommandEvent(command.getRaiseLowerCommandId(), command.getValueAliasSet());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteRaiseLowerCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteRaiseLowerCommandCommand" );
        apply(new DeleteRaiseLowerCommandEvent(command.getRaiseLowerCommandId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands

    // multiple association commands
    @CommandHandler
    public void handle(AssignValueAliasSetToRaiseLowerCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueAliasSetToRaiseLowerCommandCommand" );
    	
    	if ( valueAliasSet.contains( command.getAddTo() ) )
    		throw new ProcessingException( "ValueAliasSet already contains an entity with id " + command.getAddTo().getValueAliasSetId() );

    	apply(new AssignValueAliasSetToRaiseLowerCommandEvent(command.getRaiseLowerCommandId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemoveValueAliasSetFromRaiseLowerCommandCommand command) throws Exception {
    	LOGGER.info( "Handling command RemoveValueAliasSetFromRaiseLowerCommandCommand" );
    	
    	if ( !valueAliasSet.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "ValueAliasSet does not contain an entity with id " + command.getRemoveFrom().getValueAliasSetId() );

        apply(new RemoveValueAliasSetFromRaiseLowerCommandEvent(command.getRaiseLowerCommandId(), command.getRemoveFrom()));
    }

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateRaiseLowerCommandEvent event) {	
    	LOGGER.info( "Event sourcing CreateRaiseLowerCommandEvent" );
    	this.raiseLowerCommandId = event.getRaiseLowerCommandId();
    }
    
    @EventSourcingHandler
    void on(UpdateRaiseLowerCommandEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.valueAliasSet = event.getValueAliasSet();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------

	// multiple associations
    @EventSourcingHandler
    void on(AssignValueAliasSetToRaiseLowerCommandEvent event ) {
    	LOGGER.info( "Event sourcing AssignValueAliasSetToRaiseLowerCommandEvent" );
    	this.valueAliasSet.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemoveValueAliasSetFromRaiseLowerCommandEvent event ) {	
		LOGGER.info( "Event sourcing RemoveValueAliasSetFromRaiseLowerCommandEvent" );
		this.valueAliasSet.remove( event.getRemoveFrom() );
	}
	

    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID raiseLowerCommandId;
    
    private Set<ValueAliasSet> valueAliasSet = new HashSet<>();

    private static final Logger LOGGER 	= Logger.getLogger(RaiseLowerCommandAggregate.class.getName());
}
