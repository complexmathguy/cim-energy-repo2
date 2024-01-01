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
 * Aggregate handler for OperationalLimit as outlined for the CQRS pattern, all write responsibilities 
 * related to OperationalLimit are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class OperationalLimitAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public OperationalLimitAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public OperationalLimitAggregate(CreateOperationalLimitCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateOperationalLimitCommand" );
    	CreateOperationalLimitEvent event = new CreateOperationalLimitEvent(command.getOperationalLimitId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateOperationalLimitCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateOperationalLimitCommand" );
    	UpdateOperationalLimitEvent event = new UpdateOperationalLimitEvent(command.getOperationalLimitId(), command.getOperationalLimitType(), command.getOperationalLimitSet());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteOperationalLimitCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteOperationalLimitCommand" );
        apply(new DeleteOperationalLimitEvent(command.getOperationalLimitId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignOperationalLimitTypeToOperationalLimitCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignOperationalLimitTypeToOperationalLimitCommand" );
    	
    	if (  operationalLimitType != null && operationalLimitType.getOperationalLimitTypeId() == command.getAssignment().getOperationalLimitTypeId() )
    		throw new ProcessingException( "OperationalLimitType already assigned with id " + command.getAssignment().getOperationalLimitTypeId() );  
    		
        apply(new AssignOperationalLimitTypeToOperationalLimitEvent(command.getOperationalLimitId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignOperationalLimitTypeFromOperationalLimitCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignOperationalLimitTypeFromOperationalLimitCommand" );

    	if (  operationalLimitType == null )
    		throw new ProcessingException( "OperationalLimitType already has nothing assigned." );  

    	apply(new UnAssignOperationalLimitTypeFromOperationalLimitEvent(command.getOperationalLimitId()));
    }

    // multiple association commands
    @CommandHandler
    public void handle(AssignOperationalLimitSetToOperationalLimitCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignOperationalLimitSetToOperationalLimitCommand" );
    	
    	if ( operationalLimitSet.contains( command.getAddTo() ) )
    		throw new ProcessingException( "OperationalLimitSet already contains an entity with id " + command.getAddTo().getOperationalLimitSetId() );

    	apply(new AssignOperationalLimitSetToOperationalLimitEvent(command.getOperationalLimitId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemoveOperationalLimitSetFromOperationalLimitCommand command) throws Exception {
    	LOGGER.info( "Handling command RemoveOperationalLimitSetFromOperationalLimitCommand" );
    	
    	if ( !operationalLimitSet.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "OperationalLimitSet does not contain an entity with id " + command.getRemoveFrom().getOperationalLimitSetId() );

        apply(new RemoveOperationalLimitSetFromOperationalLimitEvent(command.getOperationalLimitId(), command.getRemoveFrom()));
    }

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateOperationalLimitEvent event) {	
    	LOGGER.info( "Event sourcing CreateOperationalLimitEvent" );
    	this.operationalLimitId = event.getOperationalLimitId();
    }
    
    @EventSourcingHandler
    void on(UpdateOperationalLimitEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.operationalLimitType = event.getOperationalLimitType();
        this.operationalLimitSet = event.getOperationalLimitSet();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignOperationalLimitTypeToOperationalLimitEvent event ) {	
    	LOGGER.info( "Event sourcing AssignOperationalLimitTypeToOperationalLimitEvent" );
    	this.operationalLimitType = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignOperationalLimitTypeFromOperationalLimitEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignOperationalLimitTypeFromOperationalLimitEvent" );
		this.operationalLimitType = null;
	}

	// multiple associations
    @EventSourcingHandler
    void on(AssignOperationalLimitSetToOperationalLimitEvent event ) {
    	LOGGER.info( "Event sourcing AssignOperationalLimitSetToOperationalLimitEvent" );
    	this.operationalLimitSet.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemoveOperationalLimitSetFromOperationalLimitEvent event ) {	
		LOGGER.info( "Event sourcing RemoveOperationalLimitSetFromOperationalLimitEvent" );
		this.operationalLimitSet.remove( event.getRemoveFrom() );
	}
	

    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID operationalLimitId;
    
    private OperationalLimitType operationalLimitType = null;
    private Set<OperationalLimitSet> operationalLimitSet = new HashSet<>();

    private static final Logger LOGGER 	= Logger.getLogger(OperationalLimitAggregate.class.getName());
}
