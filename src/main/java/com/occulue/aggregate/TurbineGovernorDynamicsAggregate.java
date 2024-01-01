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
 * Aggregate handler for TurbineGovernorDynamics as outlined for the CQRS pattern, all write responsibilities 
 * related to TurbineGovernorDynamics are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class TurbineGovernorDynamicsAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public TurbineGovernorDynamicsAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public TurbineGovernorDynamicsAggregate(CreateTurbineGovernorDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateTurbineGovernorDynamicsCommand" );
    	CreateTurbineGovernorDynamicsEvent event = new CreateTurbineGovernorDynamicsEvent(command.getTurbineGovernorDynamicsId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateTurbineGovernorDynamicsCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateTurbineGovernorDynamicsCommand" );
    	UpdateTurbineGovernorDynamicsEvent event = new UpdateTurbineGovernorDynamicsEvent(command.getTurbineGovernorDynamicsId(), command.getAsynchronousMachineDynamics(), command.getSynchronousMachineDynamics());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteTurbineGovernorDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteTurbineGovernorDynamicsCommand" );
        apply(new DeleteTurbineGovernorDynamicsEvent(command.getTurbineGovernorDynamicsId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignAsynchronousMachineDynamicsToTurbineGovernorDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignAsynchronousMachineDynamicsToTurbineGovernorDynamicsCommand" );
    	
    	if (  asynchronousMachineDynamics != null && asynchronousMachineDynamics.getAsynchronousMachineDynamicsId() == command.getAssignment().getAsynchronousMachineDynamicsId() )
    		throw new ProcessingException( "AsynchronousMachineDynamics already assigned with id " + command.getAssignment().getAsynchronousMachineDynamicsId() );  
    		
        apply(new AssignAsynchronousMachineDynamicsToTurbineGovernorDynamicsEvent(command.getTurbineGovernorDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignAsynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignAsynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand" );

    	if (  asynchronousMachineDynamics == null )
    		throw new ProcessingException( "AsynchronousMachineDynamics already has nothing assigned." );  

    	apply(new UnAssignAsynchronousMachineDynamicsFromTurbineGovernorDynamicsEvent(command.getTurbineGovernorDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignSynchronousMachineDynamicsToTurbineGovernorDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSynchronousMachineDynamicsToTurbineGovernorDynamicsCommand" );
    	
    	if (  synchronousMachineDynamics != null && synchronousMachineDynamics.getSynchronousMachineDynamicsId() == command.getAssignment().getSynchronousMachineDynamicsId() )
    		throw new ProcessingException( "SynchronousMachineDynamics already assigned with id " + command.getAssignment().getSynchronousMachineDynamicsId() );  
    		
        apply(new AssignSynchronousMachineDynamicsToTurbineGovernorDynamicsEvent(command.getTurbineGovernorDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand" );

    	if (  synchronousMachineDynamics == null )
    		throw new ProcessingException( "SynchronousMachineDynamics already has nothing assigned." );  

    	apply(new UnAssignSynchronousMachineDynamicsFromTurbineGovernorDynamicsEvent(command.getTurbineGovernorDynamicsId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateTurbineGovernorDynamicsEvent event) {	
    	LOGGER.info( "Event sourcing CreateTurbineGovernorDynamicsEvent" );
    	this.turbineGovernorDynamicsId = event.getTurbineGovernorDynamicsId();
    }
    
    @EventSourcingHandler
    void on(UpdateTurbineGovernorDynamicsEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.asynchronousMachineDynamics = event.getAsynchronousMachineDynamics();
        this.synchronousMachineDynamics = event.getSynchronousMachineDynamics();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignAsynchronousMachineDynamicsToTurbineGovernorDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignAsynchronousMachineDynamicsToTurbineGovernorDynamicsEvent" );
    	this.asynchronousMachineDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignAsynchronousMachineDynamicsFromTurbineGovernorDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignAsynchronousMachineDynamicsFromTurbineGovernorDynamicsEvent" );
		this.asynchronousMachineDynamics = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignSynchronousMachineDynamicsToTurbineGovernorDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSynchronousMachineDynamicsToTurbineGovernorDynamicsEvent" );
    	this.synchronousMachineDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSynchronousMachineDynamicsFromTurbineGovernorDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSynchronousMachineDynamicsFromTurbineGovernorDynamicsEvent" );
		this.synchronousMachineDynamics = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID turbineGovernorDynamicsId;
    
    private AsynchronousMachineDynamics asynchronousMachineDynamics = null;
    private SynchronousMachineDynamics synchronousMachineDynamics = null;
    private TurbineLoadControllerDynamics turbineGovernorDynamics = null;

    private static final Logger LOGGER 	= Logger.getLogger(TurbineGovernorDynamicsAggregate.class.getName());
}
