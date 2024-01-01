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
 * Aggregate handler for VoltageCompensatorDynamics as outlined for the CQRS pattern, all write responsibilities 
 * related to VoltageCompensatorDynamics are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class VoltageCompensatorDynamicsAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public VoltageCompensatorDynamicsAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public VoltageCompensatorDynamicsAggregate(CreateVoltageCompensatorDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateVoltageCompensatorDynamicsCommand" );
    	CreateVoltageCompensatorDynamicsEvent event = new CreateVoltageCompensatorDynamicsEvent(command.getVoltageCompensatorDynamicsId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateVoltageCompensatorDynamicsCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateVoltageCompensatorDynamicsCommand" );
    	UpdateVoltageCompensatorDynamicsEvent event = new UpdateVoltageCompensatorDynamicsEvent(command.getVoltageCompensatorDynamicsId(), command.getExcitationSystemDynamics());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteVoltageCompensatorDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteVoltageCompensatorDynamicsCommand" );
        apply(new DeleteVoltageCompensatorDynamicsEvent(command.getVoltageCompensatorDynamicsId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignExcitationSystemDynamicsToVoltageCompensatorDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignExcitationSystemDynamicsToVoltageCompensatorDynamicsCommand" );
    	
    	if (  excitationSystemDynamics != null && excitationSystemDynamics.getExcitationSystemDynamicsId() == command.getAssignment().getExcitationSystemDynamicsId() )
    		throw new ProcessingException( "ExcitationSystemDynamics already assigned with id " + command.getAssignment().getExcitationSystemDynamicsId() );  
    		
        apply(new AssignExcitationSystemDynamicsToVoltageCompensatorDynamicsEvent(command.getVoltageCompensatorDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignExcitationSystemDynamicsFromVoltageCompensatorDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignExcitationSystemDynamicsFromVoltageCompensatorDynamicsCommand" );

    	if (  excitationSystemDynamics == null )
    		throw new ProcessingException( "ExcitationSystemDynamics already has nothing assigned." );  

    	apply(new UnAssignExcitationSystemDynamicsFromVoltageCompensatorDynamicsEvent(command.getVoltageCompensatorDynamicsId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateVoltageCompensatorDynamicsEvent event) {	
    	LOGGER.info( "Event sourcing CreateVoltageCompensatorDynamicsEvent" );
    	this.voltageCompensatorDynamicsId = event.getVoltageCompensatorDynamicsId();
    }
    
    @EventSourcingHandler
    void on(UpdateVoltageCompensatorDynamicsEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.excitationSystemDynamics = event.getExcitationSystemDynamics();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignExcitationSystemDynamicsToVoltageCompensatorDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignExcitationSystemDynamicsToVoltageCompensatorDynamicsEvent" );
    	this.excitationSystemDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignExcitationSystemDynamicsFromVoltageCompensatorDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignExcitationSystemDynamicsFromVoltageCompensatorDynamicsEvent" );
		this.excitationSystemDynamics = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID voltageCompensatorDynamicsId;
    
    private RemoteInputSignal voltageCompensatorDynamics = null;
    private ExcitationSystemDynamics excitationSystemDynamics = null;

    private static final Logger LOGGER 	= Logger.getLogger(VoltageCompensatorDynamicsAggregate.class.getName());
}
