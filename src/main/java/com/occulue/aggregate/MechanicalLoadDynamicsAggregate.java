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
 * Aggregate handler for MechanicalLoadDynamics as outlined for the CQRS pattern, all write responsibilities 
 * related to MechanicalLoadDynamics are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class MechanicalLoadDynamicsAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public MechanicalLoadDynamicsAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public MechanicalLoadDynamicsAggregate(CreateMechanicalLoadDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateMechanicalLoadDynamicsCommand" );
    	CreateMechanicalLoadDynamicsEvent event = new CreateMechanicalLoadDynamicsEvent(command.getMechanicalLoadDynamicsId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateMechanicalLoadDynamicsCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateMechanicalLoadDynamicsCommand" );
    	UpdateMechanicalLoadDynamicsEvent event = new UpdateMechanicalLoadDynamicsEvent(command.getMechanicalLoadDynamicsId(), command.getSynchronousMachineDynamics(), command.getAsynchronousMachineDynamics());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteMechanicalLoadDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteMechanicalLoadDynamicsCommand" );
        apply(new DeleteMechanicalLoadDynamicsEvent(command.getMechanicalLoadDynamicsId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignSynchronousMachineDynamicsToMechanicalLoadDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSynchronousMachineDynamicsToMechanicalLoadDynamicsCommand" );
    	
    	if (  synchronousMachineDynamics != null && synchronousMachineDynamics.getSynchronousMachineDynamicsId() == command.getAssignment().getSynchronousMachineDynamicsId() )
    		throw new ProcessingException( "SynchronousMachineDynamics already assigned with id " + command.getAssignment().getSynchronousMachineDynamicsId() );  
    		
        apply(new AssignSynchronousMachineDynamicsToMechanicalLoadDynamicsEvent(command.getMechanicalLoadDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand" );

    	if (  synchronousMachineDynamics == null )
    		throw new ProcessingException( "SynchronousMachineDynamics already has nothing assigned." );  

    	apply(new UnAssignSynchronousMachineDynamicsFromMechanicalLoadDynamicsEvent(command.getMechanicalLoadDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignAsynchronousMachineDynamicsToMechanicalLoadDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignAsynchronousMachineDynamicsToMechanicalLoadDynamicsCommand" );
    	
    	if (  asynchronousMachineDynamics != null && asynchronousMachineDynamics.getAsynchronousMachineDynamicsId() == command.getAssignment().getAsynchronousMachineDynamicsId() )
    		throw new ProcessingException( "AsynchronousMachineDynamics already assigned with id " + command.getAssignment().getAsynchronousMachineDynamicsId() );  
    		
        apply(new AssignAsynchronousMachineDynamicsToMechanicalLoadDynamicsEvent(command.getMechanicalLoadDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignAsynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignAsynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand" );

    	if (  asynchronousMachineDynamics == null )
    		throw new ProcessingException( "AsynchronousMachineDynamics already has nothing assigned." );  

    	apply(new UnAssignAsynchronousMachineDynamicsFromMechanicalLoadDynamicsEvent(command.getMechanicalLoadDynamicsId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateMechanicalLoadDynamicsEvent event) {	
    	LOGGER.info( "Event sourcing CreateMechanicalLoadDynamicsEvent" );
    	this.mechanicalLoadDynamicsId = event.getMechanicalLoadDynamicsId();
    }
    
    @EventSourcingHandler
    void on(UpdateMechanicalLoadDynamicsEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.synchronousMachineDynamics = event.getSynchronousMachineDynamics();
        this.asynchronousMachineDynamics = event.getAsynchronousMachineDynamics();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignSynchronousMachineDynamicsToMechanicalLoadDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSynchronousMachineDynamicsToMechanicalLoadDynamicsEvent" );
    	this.synchronousMachineDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSynchronousMachineDynamicsFromMechanicalLoadDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSynchronousMachineDynamicsFromMechanicalLoadDynamicsEvent" );
		this.synchronousMachineDynamics = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignAsynchronousMachineDynamicsToMechanicalLoadDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignAsynchronousMachineDynamicsToMechanicalLoadDynamicsEvent" );
    	this.asynchronousMachineDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignAsynchronousMachineDynamicsFromMechanicalLoadDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignAsynchronousMachineDynamicsFromMechanicalLoadDynamicsEvent" );
		this.asynchronousMachineDynamics = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID mechanicalLoadDynamicsId;
    
    private SynchronousMachineDynamics synchronousMachineDynamics = null;
    private AsynchronousMachineDynamics asynchronousMachineDynamics = null;

    private static final Logger LOGGER 	= Logger.getLogger(MechanicalLoadDynamicsAggregate.class.getName());
}
