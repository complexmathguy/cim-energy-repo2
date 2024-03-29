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
 * Aggregate handler for PFVArControllerType1Dynamics as outlined for the CQRS pattern, all write responsibilities 
 * related to PFVArControllerType1Dynamics are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class PFVArControllerType1DynamicsAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public PFVArControllerType1DynamicsAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public PFVArControllerType1DynamicsAggregate(CreatePFVArControllerType1DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command CreatePFVArControllerType1DynamicsCommand" );
    	CreatePFVArControllerType1DynamicsEvent event = new CreatePFVArControllerType1DynamicsEvent(command.getPFVArControllerType1DynamicsId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdatePFVArControllerType1DynamicsCommand command) throws Exception {
    	LOGGER.info( "handling command UpdatePFVArControllerType1DynamicsCommand" );
    	UpdatePFVArControllerType1DynamicsEvent event = new UpdatePFVArControllerType1DynamicsEvent(command.getPFVArControllerType1DynamicsId(), command.getExcitationSystemDynamics());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeletePFVArControllerType1DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command DeletePFVArControllerType1DynamicsCommand" );
        apply(new DeletePFVArControllerType1DynamicsEvent(command.getPFVArControllerType1DynamicsId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignExcitationSystemDynamicsToPFVArControllerType1DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignExcitationSystemDynamicsToPFVArControllerType1DynamicsCommand" );
    	
    	if (  excitationSystemDynamics != null && excitationSystemDynamics.getExcitationSystemDynamicsId() == command.getAssignment().getExcitationSystemDynamicsId() )
    		throw new ProcessingException( "ExcitationSystemDynamics already assigned with id " + command.getAssignment().getExcitationSystemDynamicsId() );  
    		
        apply(new AssignExcitationSystemDynamicsToPFVArControllerType1DynamicsEvent(command.getPFVArControllerType1DynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignExcitationSystemDynamicsFromPFVArControllerType1DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignExcitationSystemDynamicsFromPFVArControllerType1DynamicsCommand" );

    	if (  excitationSystemDynamics == null )
    		throw new ProcessingException( "ExcitationSystemDynamics already has nothing assigned." );  

    	apply(new UnAssignExcitationSystemDynamicsFromPFVArControllerType1DynamicsEvent(command.getPFVArControllerType1DynamicsId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreatePFVArControllerType1DynamicsEvent event) {	
    	LOGGER.info( "Event sourcing CreatePFVArControllerType1DynamicsEvent" );
    	this.pFVArControllerType1DynamicsId = event.getPFVArControllerType1DynamicsId();
    }
    
    @EventSourcingHandler
    void on(UpdatePFVArControllerType1DynamicsEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.excitationSystemDynamics = event.getExcitationSystemDynamics();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignExcitationSystemDynamicsToPFVArControllerType1DynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignExcitationSystemDynamicsToPFVArControllerType1DynamicsEvent" );
    	this.excitationSystemDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignExcitationSystemDynamicsFromPFVArControllerType1DynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignExcitationSystemDynamicsFromPFVArControllerType1DynamicsEvent" );
		this.excitationSystemDynamics = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID pFVArControllerType1DynamicsId;
    
    private RemoteInputSignal pFVArControllerType1Dynamics = null;
    private ExcitationSystemDynamics excitationSystemDynamics = null;

    private static final Logger LOGGER 	= Logger.getLogger(PFVArControllerType1DynamicsAggregate.class.getName());
}
