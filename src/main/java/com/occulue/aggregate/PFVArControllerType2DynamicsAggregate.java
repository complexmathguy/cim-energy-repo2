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
 * Aggregate handler for PFVArControllerType2Dynamics as outlined for the CQRS pattern, all write responsibilities 
 * related to PFVArControllerType2Dynamics are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class PFVArControllerType2DynamicsAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public PFVArControllerType2DynamicsAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public PFVArControllerType2DynamicsAggregate(CreatePFVArControllerType2DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command CreatePFVArControllerType2DynamicsCommand" );
    	CreatePFVArControllerType2DynamicsEvent event = new CreatePFVArControllerType2DynamicsEvent(command.getPFVArControllerType2DynamicsId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdatePFVArControllerType2DynamicsCommand command) throws Exception {
    	LOGGER.info( "handling command UpdatePFVArControllerType2DynamicsCommand" );
    	UpdatePFVArControllerType2DynamicsEvent event = new UpdatePFVArControllerType2DynamicsEvent(command.getPFVArControllerType2DynamicsId(), command.getExcitationSystemDynamics());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeletePFVArControllerType2DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command DeletePFVArControllerType2DynamicsCommand" );
        apply(new DeletePFVArControllerType2DynamicsEvent(command.getPFVArControllerType2DynamicsId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignExcitationSystemDynamicsToPFVArControllerType2DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignExcitationSystemDynamicsToPFVArControllerType2DynamicsCommand" );
    	
    	if (  excitationSystemDynamics != null && excitationSystemDynamics.getExcitationSystemDynamicsId() == command.getAssignment().getExcitationSystemDynamicsId() )
    		throw new ProcessingException( "ExcitationSystemDynamics already assigned with id " + command.getAssignment().getExcitationSystemDynamicsId() );  
    		
        apply(new AssignExcitationSystemDynamicsToPFVArControllerType2DynamicsEvent(command.getPFVArControllerType2DynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignExcitationSystemDynamicsFromPFVArControllerType2DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignExcitationSystemDynamicsFromPFVArControllerType2DynamicsCommand" );

    	if (  excitationSystemDynamics == null )
    		throw new ProcessingException( "ExcitationSystemDynamics already has nothing assigned." );  

    	apply(new UnAssignExcitationSystemDynamicsFromPFVArControllerType2DynamicsEvent(command.getPFVArControllerType2DynamicsId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreatePFVArControllerType2DynamicsEvent event) {	
    	LOGGER.info( "Event sourcing CreatePFVArControllerType2DynamicsEvent" );
    	this.pFVArControllerType2DynamicsId = event.getPFVArControllerType2DynamicsId();
    }
    
    @EventSourcingHandler
    void on(UpdatePFVArControllerType2DynamicsEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.excitationSystemDynamics = event.getExcitationSystemDynamics();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignExcitationSystemDynamicsToPFVArControllerType2DynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignExcitationSystemDynamicsToPFVArControllerType2DynamicsEvent" );
    	this.excitationSystemDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignExcitationSystemDynamicsFromPFVArControllerType2DynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignExcitationSystemDynamicsFromPFVArControllerType2DynamicsEvent" );
		this.excitationSystemDynamics = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID pFVArControllerType2DynamicsId;
    
    private ExcitationSystemDynamics excitationSystemDynamics = null;

    private static final Logger LOGGER 	= Logger.getLogger(PFVArControllerType2DynamicsAggregate.class.getName());
}
