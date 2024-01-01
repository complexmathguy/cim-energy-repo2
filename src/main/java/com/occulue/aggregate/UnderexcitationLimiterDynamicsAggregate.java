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
 * Aggregate handler for UnderexcitationLimiterDynamics as outlined for the CQRS pattern, all write responsibilities 
 * related to UnderexcitationLimiterDynamics are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class UnderexcitationLimiterDynamicsAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public UnderexcitationLimiterDynamicsAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public UnderexcitationLimiterDynamicsAggregate(CreateUnderexcitationLimiterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateUnderexcitationLimiterDynamicsCommand" );
    	CreateUnderexcitationLimiterDynamicsEvent event = new CreateUnderexcitationLimiterDynamicsEvent(command.getUnderexcitationLimiterDynamicsId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateUnderexcitationLimiterDynamicsCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateUnderexcitationLimiterDynamicsCommand" );
    	UpdateUnderexcitationLimiterDynamicsEvent event = new UpdateUnderexcitationLimiterDynamicsEvent(command.getUnderexcitationLimiterDynamicsId(), command.getExcitationSystemDynamics());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteUnderexcitationLimiterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteUnderexcitationLimiterDynamicsCommand" );
        apply(new DeleteUnderexcitationLimiterDynamicsEvent(command.getUnderexcitationLimiterDynamicsId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignExcitationSystemDynamicsToUnderexcitationLimiterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignExcitationSystemDynamicsToUnderexcitationLimiterDynamicsCommand" );
    	
    	if (  excitationSystemDynamics != null && excitationSystemDynamics.getExcitationSystemDynamicsId() == command.getAssignment().getExcitationSystemDynamicsId() )
    		throw new ProcessingException( "ExcitationSystemDynamics already assigned with id " + command.getAssignment().getExcitationSystemDynamicsId() );  
    		
        apply(new AssignExcitationSystemDynamicsToUnderexcitationLimiterDynamicsEvent(command.getUnderexcitationLimiterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignExcitationSystemDynamicsFromUnderexcitationLimiterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignExcitationSystemDynamicsFromUnderexcitationLimiterDynamicsCommand" );

    	if (  excitationSystemDynamics == null )
    		throw new ProcessingException( "ExcitationSystemDynamics already has nothing assigned." );  

    	apply(new UnAssignExcitationSystemDynamicsFromUnderexcitationLimiterDynamicsEvent(command.getUnderexcitationLimiterDynamicsId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateUnderexcitationLimiterDynamicsEvent event) {	
    	LOGGER.info( "Event sourcing CreateUnderexcitationLimiterDynamicsEvent" );
    	this.underexcitationLimiterDynamicsId = event.getUnderexcitationLimiterDynamicsId();
    }
    
    @EventSourcingHandler
    void on(UpdateUnderexcitationLimiterDynamicsEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.excitationSystemDynamics = event.getExcitationSystemDynamics();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignExcitationSystemDynamicsToUnderexcitationLimiterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignExcitationSystemDynamicsToUnderexcitationLimiterDynamicsEvent" );
    	this.excitationSystemDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignExcitationSystemDynamicsFromUnderexcitationLimiterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignExcitationSystemDynamicsFromUnderexcitationLimiterDynamicsEvent" );
		this.excitationSystemDynamics = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID underexcitationLimiterDynamicsId;
    
    private RemoteInputSignal underexcitationLimiterDynamics = null;
    private ExcitationSystemDynamics excitationSystemDynamics = null;

    private static final Logger LOGGER 	= Logger.getLogger(UnderexcitationLimiterDynamicsAggregate.class.getName());
}
