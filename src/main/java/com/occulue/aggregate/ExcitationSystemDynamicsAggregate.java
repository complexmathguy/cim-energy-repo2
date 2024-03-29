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
 * Aggregate handler for ExcitationSystemDynamics as outlined for the CQRS pattern, all write responsibilities 
 * related to ExcitationSystemDynamics are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ExcitationSystemDynamicsAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ExcitationSystemDynamicsAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ExcitationSystemDynamicsAggregate(CreateExcitationSystemDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateExcitationSystemDynamicsCommand" );
    	CreateExcitationSystemDynamicsEvent event = new CreateExcitationSystemDynamicsEvent(command.getExcitationSystemDynamicsId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateExcitationSystemDynamicsCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateExcitationSystemDynamicsCommand" );
    	UpdateExcitationSystemDynamicsEvent event = new UpdateExcitationSystemDynamicsEvent(command.getExcitationSystemDynamicsId(), command.getSynchronousMachineDynamics());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteExcitationSystemDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteExcitationSystemDynamicsCommand" );
        apply(new DeleteExcitationSystemDynamicsEvent(command.getExcitationSystemDynamicsId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignSynchronousMachineDynamicsToExcitationSystemDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSynchronousMachineDynamicsToExcitationSystemDynamicsCommand" );
    	
    	if (  synchronousMachineDynamics != null && synchronousMachineDynamics.getSynchronousMachineDynamicsId() == command.getAssignment().getSynchronousMachineDynamicsId() )
    		throw new ProcessingException( "SynchronousMachineDynamics already assigned with id " + command.getAssignment().getSynchronousMachineDynamicsId() );  
    		
        apply(new AssignSynchronousMachineDynamicsToExcitationSystemDynamicsEvent(command.getExcitationSystemDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSynchronousMachineDynamicsFromExcitationSystemDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSynchronousMachineDynamicsFromExcitationSystemDynamicsCommand" );

    	if (  synchronousMachineDynamics == null )
    		throw new ProcessingException( "SynchronousMachineDynamics already has nothing assigned." );  

    	apply(new UnAssignSynchronousMachineDynamicsFromExcitationSystemDynamicsEvent(command.getExcitationSystemDynamicsId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateExcitationSystemDynamicsEvent event) {	
    	LOGGER.info( "Event sourcing CreateExcitationSystemDynamicsEvent" );
    	this.excitationSystemDynamicsId = event.getExcitationSystemDynamicsId();
    }
    
    @EventSourcingHandler
    void on(UpdateExcitationSystemDynamicsEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.synchronousMachineDynamics = event.getSynchronousMachineDynamics();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignSynchronousMachineDynamicsToExcitationSystemDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSynchronousMachineDynamicsToExcitationSystemDynamicsEvent" );
    	this.synchronousMachineDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSynchronousMachineDynamicsFromExcitationSystemDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSynchronousMachineDynamicsFromExcitationSystemDynamicsEvent" );
		this.synchronousMachineDynamics = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID excitationSystemDynamicsId;
    
    private SynchronousMachineDynamics synchronousMachineDynamics = null;
    private OverexcitationLimiterDynamics excitationSystemDynamics = null;

    private static final Logger LOGGER 	= Logger.getLogger(ExcitationSystemDynamicsAggregate.class.getName());
}
