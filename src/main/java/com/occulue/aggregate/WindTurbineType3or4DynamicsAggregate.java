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
 * Aggregate handler for WindTurbineType3or4Dynamics as outlined for the CQRS pattern, all write responsibilities 
 * related to WindTurbineType3or4Dynamics are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindTurbineType3or4DynamicsAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindTurbineType3or4DynamicsAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindTurbineType3or4DynamicsAggregate(CreateWindTurbineType3or4DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindTurbineType3or4DynamicsCommand" );
    	CreateWindTurbineType3or4DynamicsEvent event = new CreateWindTurbineType3or4DynamicsEvent(command.getWindTurbineType3or4DynamicsId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindTurbineType3or4DynamicsCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindTurbineType3or4DynamicsCommand" );
    	UpdateWindTurbineType3or4DynamicsEvent event = new UpdateWindTurbineType3or4DynamicsEvent(command.getWindTurbineType3or4DynamicsId(), command.getEnergySource(), command.getWindPlantDynamics(), command.getRemoteInputSignal());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindTurbineType3or4DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindTurbineType3or4DynamicsCommand" );
        apply(new DeleteWindTurbineType3or4DynamicsEvent(command.getWindTurbineType3or4DynamicsId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignEnergySourceToWindTurbineType3or4DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignEnergySourceToWindTurbineType3or4DynamicsCommand" );
    	
    	if (  energySource != null && energySource.getEnergySourceId() == command.getAssignment().getEnergySourceId() )
    		throw new ProcessingException( "EnergySource already assigned with id " + command.getAssignment().getEnergySourceId() );  
    		
        apply(new AssignEnergySourceToWindTurbineType3or4DynamicsEvent(command.getWindTurbineType3or4DynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignEnergySourceFromWindTurbineType3or4DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignEnergySourceFromWindTurbineType3or4DynamicsCommand" );

    	if (  energySource == null )
    		throw new ProcessingException( "EnergySource already has nothing assigned." );  

    	apply(new UnAssignEnergySourceFromWindTurbineType3or4DynamicsEvent(command.getWindTurbineType3or4DynamicsId()));
    }
    @CommandHandler
    public void handle(AssignWindPlantDynamicsToWindTurbineType3or4DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindPlantDynamicsToWindTurbineType3or4DynamicsCommand" );
    	
    	if (  windPlantDynamics != null && windPlantDynamics.getWindPlantDynamicsId() == command.getAssignment().getWindPlantDynamicsId() )
    		throw new ProcessingException( "WindPlantDynamics already assigned with id " + command.getAssignment().getWindPlantDynamicsId() );  
    		
        apply(new AssignWindPlantDynamicsToWindTurbineType3or4DynamicsEvent(command.getWindTurbineType3or4DynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindPlantDynamicsFromWindTurbineType3or4DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindPlantDynamicsFromWindTurbineType3or4DynamicsCommand" );

    	if (  windPlantDynamics == null )
    		throw new ProcessingException( "WindPlantDynamics already has nothing assigned." );  

    	apply(new UnAssignWindPlantDynamicsFromWindTurbineType3or4DynamicsEvent(command.getWindTurbineType3or4DynamicsId()));
    }
    @CommandHandler
    public void handle(AssignRemoteInputSignalToWindTurbineType3or4DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRemoteInputSignalToWindTurbineType3or4DynamicsCommand" );
    	
    	if (  remoteInputSignal != null && remoteInputSignal.getRemoteInputSignalId() == command.getAssignment().getRemoteInputSignalId() )
    		throw new ProcessingException( "RemoteInputSignal already assigned with id " + command.getAssignment().getRemoteInputSignalId() );  
    		
        apply(new AssignRemoteInputSignalToWindTurbineType3or4DynamicsEvent(command.getWindTurbineType3or4DynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRemoteInputSignalFromWindTurbineType3or4DynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRemoteInputSignalFromWindTurbineType3or4DynamicsCommand" );

    	if (  remoteInputSignal == null )
    		throw new ProcessingException( "RemoteInputSignal already has nothing assigned." );  

    	apply(new UnAssignRemoteInputSignalFromWindTurbineType3or4DynamicsEvent(command.getWindTurbineType3or4DynamicsId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindTurbineType3or4DynamicsEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindTurbineType3or4DynamicsEvent" );
    	this.windTurbineType3or4DynamicsId = event.getWindTurbineType3or4DynamicsId();
    }
    
    @EventSourcingHandler
    void on(UpdateWindTurbineType3or4DynamicsEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.energySource = event.getEnergySource();
        this.windPlantDynamics = event.getWindPlantDynamics();
        this.remoteInputSignal = event.getRemoteInputSignal();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignEnergySourceToWindTurbineType3or4DynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignEnergySourceToWindTurbineType3or4DynamicsEvent" );
    	this.energySource = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignEnergySourceFromWindTurbineType3or4DynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignEnergySourceFromWindTurbineType3or4DynamicsEvent" );
		this.energySource = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindPlantDynamicsToWindTurbineType3or4DynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindPlantDynamicsToWindTurbineType3or4DynamicsEvent" );
    	this.windPlantDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindPlantDynamicsFromWindTurbineType3or4DynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindPlantDynamicsFromWindTurbineType3or4DynamicsEvent" );
		this.windPlantDynamics = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignRemoteInputSignalToWindTurbineType3or4DynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRemoteInputSignalToWindTurbineType3or4DynamicsEvent" );
    	this.remoteInputSignal = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRemoteInputSignalFromWindTurbineType3or4DynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRemoteInputSignalFromWindTurbineType3or4DynamicsEvent" );
		this.remoteInputSignal = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windTurbineType3or4DynamicsId;
    
    private EnergySource energySource = null;
    private WindPlantDynamics windPlantDynamics = null;
    private RemoteInputSignal remoteInputSignal = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindTurbineType3or4DynamicsAggregate.class.getName());
}
