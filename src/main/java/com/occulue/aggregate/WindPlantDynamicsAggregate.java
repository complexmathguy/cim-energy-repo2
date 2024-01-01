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
 * Aggregate handler for WindPlantDynamics as outlined for the CQRS pattern, all write responsibilities 
 * related to WindPlantDynamics are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindPlantDynamicsAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindPlantDynamicsAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindPlantDynamicsAggregate(CreateWindPlantDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindPlantDynamicsCommand" );
    	CreateWindPlantDynamicsEvent event = new CreateWindPlantDynamicsEvent(command.getWindPlantDynamicsId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindPlantDynamicsCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindPlantDynamicsCommand" );
    	UpdateWindPlantDynamicsEvent event = new UpdateWindPlantDynamicsEvent(command.getWindPlantDynamicsId(), command.getRemoteInputSignal());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindPlantDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindPlantDynamicsCommand" );
        apply(new DeleteWindPlantDynamicsEvent(command.getWindPlantDynamicsId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignRemoteInputSignalToWindPlantDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRemoteInputSignalToWindPlantDynamicsCommand" );
    	
    	if (  remoteInputSignal != null && remoteInputSignal.getRemoteInputSignalId() == command.getAssignment().getRemoteInputSignalId() )
    		throw new ProcessingException( "RemoteInputSignal already assigned with id " + command.getAssignment().getRemoteInputSignalId() );  
    		
        apply(new AssignRemoteInputSignalToWindPlantDynamicsEvent(command.getWindPlantDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRemoteInputSignalFromWindPlantDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRemoteInputSignalFromWindPlantDynamicsCommand" );

    	if (  remoteInputSignal == null )
    		throw new ProcessingException( "RemoteInputSignal already has nothing assigned." );  

    	apply(new UnAssignRemoteInputSignalFromWindPlantDynamicsEvent(command.getWindPlantDynamicsId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindPlantDynamicsEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindPlantDynamicsEvent" );
    	this.windPlantDynamicsId = event.getWindPlantDynamicsId();
    }
    
    @EventSourcingHandler
    void on(UpdateWindPlantDynamicsEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.remoteInputSignal = event.getRemoteInputSignal();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignRemoteInputSignalToWindPlantDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRemoteInputSignalToWindPlantDynamicsEvent" );
    	this.remoteInputSignal = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRemoteInputSignalFromWindPlantDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRemoteInputSignalFromWindPlantDynamicsEvent" );
		this.remoteInputSignal = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windPlantDynamicsId;
    
    private RemoteInputSignal remoteInputSignal = null;
    private WindTurbineType3or4Dynamics windPlantDynamics = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindPlantDynamicsAggregate.class.getName());
}
