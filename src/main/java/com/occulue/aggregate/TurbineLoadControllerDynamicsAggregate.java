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
 * Aggregate handler for TurbineLoadControllerDynamics as outlined for the CQRS pattern, all write responsibilities 
 * related to TurbineLoadControllerDynamics are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class TurbineLoadControllerDynamicsAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public TurbineLoadControllerDynamicsAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public TurbineLoadControllerDynamicsAggregate(CreateTurbineLoadControllerDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateTurbineLoadControllerDynamicsCommand" );
    	CreateTurbineLoadControllerDynamicsEvent event = new CreateTurbineLoadControllerDynamicsEvent(command.getTurbineLoadControllerDynamicsId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateTurbineLoadControllerDynamicsCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateTurbineLoadControllerDynamicsCommand" );
    	UpdateTurbineLoadControllerDynamicsEvent event = new UpdateTurbineLoadControllerDynamicsEvent(command.getTurbineLoadControllerDynamicsId(), command.getTurbineGovernorDynamics());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteTurbineLoadControllerDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteTurbineLoadControllerDynamicsCommand" );
        apply(new DeleteTurbineLoadControllerDynamicsEvent(command.getTurbineLoadControllerDynamicsId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignTurbineGovernorDynamicsToTurbineLoadControllerDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTurbineGovernorDynamicsToTurbineLoadControllerDynamicsCommand" );
    	
    	if (  turbineGovernorDynamics != null && turbineGovernorDynamics.getTurbineGovernorDynamicsId() == command.getAssignment().getTurbineGovernorDynamicsId() )
    		throw new ProcessingException( "TurbineGovernorDynamics already assigned with id " + command.getAssignment().getTurbineGovernorDynamicsId() );  
    		
        apply(new AssignTurbineGovernorDynamicsToTurbineLoadControllerDynamicsEvent(command.getTurbineLoadControllerDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTurbineGovernorDynamicsFromTurbineLoadControllerDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTurbineGovernorDynamicsFromTurbineLoadControllerDynamicsCommand" );

    	if (  turbineGovernorDynamics == null )
    		throw new ProcessingException( "TurbineGovernorDynamics already has nothing assigned." );  

    	apply(new UnAssignTurbineGovernorDynamicsFromTurbineLoadControllerDynamicsEvent(command.getTurbineLoadControllerDynamicsId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateTurbineLoadControllerDynamicsEvent event) {	
    	LOGGER.info( "Event sourcing CreateTurbineLoadControllerDynamicsEvent" );
    	this.turbineLoadControllerDynamicsId = event.getTurbineLoadControllerDynamicsId();
    }
    
    @EventSourcingHandler
    void on(UpdateTurbineLoadControllerDynamicsEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.turbineGovernorDynamics = event.getTurbineGovernorDynamics();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignTurbineGovernorDynamicsToTurbineLoadControllerDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTurbineGovernorDynamicsToTurbineLoadControllerDynamicsEvent" );
    	this.turbineGovernorDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTurbineGovernorDynamicsFromTurbineLoadControllerDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTurbineGovernorDynamicsFromTurbineLoadControllerDynamicsEvent" );
		this.turbineGovernorDynamics = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID turbineLoadControllerDynamicsId;
    
    private TurbineGovernorDynamics turbineGovernorDynamics = null;

    private static final Logger LOGGER 	= Logger.getLogger(TurbineLoadControllerDynamicsAggregate.class.getName());
}
