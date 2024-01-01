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
 * Aggregate handler for RemoteInputSignal as outlined for the CQRS pattern, all write responsibilities 
 * related to RemoteInputSignal are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class RemoteInputSignalAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public RemoteInputSignalAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public RemoteInputSignalAggregate(CreateRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateRemoteInputSignalCommand" );
    	CreateRemoteInputSignalEvent event = new CreateRemoteInputSignalEvent(command.getRemoteInputSignalId(), command.getRemoteSignalType());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateRemoteInputSignalCommand" );
    	UpdateRemoteInputSignalEvent event = new UpdateRemoteInputSignalEvent(command.getRemoteInputSignalId(), command.getRemoteSignalType(), command.getTerminal(), command.getDiscontinuousExcitationControlDynamics(), command.getPowerSystemStabilizerDynamics(), command.getVoltageCompensatorDynamics(), command.getUnderexcitationLimiterDynamics(), command.getPFVArControllerType1Dynamics());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteRemoteInputSignalCommand" );
        apply(new DeleteRemoteInputSignalEvent(command.getRemoteInputSignalId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignTerminalToRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTerminalToRemoteInputSignalCommand" );
    	
    	if (  terminal != null && terminal.getTerminalId() == command.getAssignment().getTerminalId() )
    		throw new ProcessingException( "Terminal already assigned with id " + command.getAssignment().getTerminalId() );  
    		
        apply(new AssignTerminalToRemoteInputSignalEvent(command.getRemoteInputSignalId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTerminalFromRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTerminalFromRemoteInputSignalCommand" );

    	if (  terminal == null )
    		throw new ProcessingException( "Terminal already has nothing assigned." );  

    	apply(new UnAssignTerminalFromRemoteInputSignalEvent(command.getRemoteInputSignalId()));
    }
    @CommandHandler
    public void handle(AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalCommand" );
    	
    	if (  discontinuousExcitationControlDynamics != null && discontinuousExcitationControlDynamics.getDiscontinuousExcitationControlDynamicsId() == command.getAssignment().getDiscontinuousExcitationControlDynamicsId() )
    		throw new ProcessingException( "DiscontinuousExcitationControlDynamics already assigned with id " + command.getAssignment().getDiscontinuousExcitationControlDynamicsId() );  
    		
        apply(new AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalEvent(command.getRemoteInputSignalId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalCommand" );

    	if (  discontinuousExcitationControlDynamics == null )
    		throw new ProcessingException( "DiscontinuousExcitationControlDynamics already has nothing assigned." );  

    	apply(new UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalEvent(command.getRemoteInputSignalId()));
    }
    @CommandHandler
    public void handle(AssignPowerSystemStabilizerDynamicsToRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPowerSystemStabilizerDynamicsToRemoteInputSignalCommand" );
    	
    	if (  powerSystemStabilizerDynamics != null && powerSystemStabilizerDynamics.getPowerSystemStabilizerDynamicsId() == command.getAssignment().getPowerSystemStabilizerDynamicsId() )
    		throw new ProcessingException( "PowerSystemStabilizerDynamics already assigned with id " + command.getAssignment().getPowerSystemStabilizerDynamicsId() );  
    		
        apply(new AssignPowerSystemStabilizerDynamicsToRemoteInputSignalEvent(command.getRemoteInputSignalId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalCommand" );

    	if (  powerSystemStabilizerDynamics == null )
    		throw new ProcessingException( "PowerSystemStabilizerDynamics already has nothing assigned." );  

    	apply(new UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalEvent(command.getRemoteInputSignalId()));
    }
    @CommandHandler
    public void handle(AssignVoltageCompensatorDynamicsToRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignVoltageCompensatorDynamicsToRemoteInputSignalCommand" );
    	
    	if (  voltageCompensatorDynamics != null && voltageCompensatorDynamics.getVoltageCompensatorDynamicsId() == command.getAssignment().getVoltageCompensatorDynamicsId() )
    		throw new ProcessingException( "VoltageCompensatorDynamics already assigned with id " + command.getAssignment().getVoltageCompensatorDynamicsId() );  
    		
        apply(new AssignVoltageCompensatorDynamicsToRemoteInputSignalEvent(command.getRemoteInputSignalId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalCommand" );

    	if (  voltageCompensatorDynamics == null )
    		throw new ProcessingException( "VoltageCompensatorDynamics already has nothing assigned." );  

    	apply(new UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalEvent(command.getRemoteInputSignalId()));
    }
    @CommandHandler
    public void handle(AssignUnderexcitationLimiterDynamicsToRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignUnderexcitationLimiterDynamicsToRemoteInputSignalCommand" );
    	
    	if (  underexcitationLimiterDynamics != null && underexcitationLimiterDynamics.getUnderexcitationLimiterDynamicsId() == command.getAssignment().getUnderexcitationLimiterDynamicsId() )
    		throw new ProcessingException( "UnderexcitationLimiterDynamics already assigned with id " + command.getAssignment().getUnderexcitationLimiterDynamicsId() );  
    		
        apply(new AssignUnderexcitationLimiterDynamicsToRemoteInputSignalEvent(command.getRemoteInputSignalId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalCommand" );

    	if (  underexcitationLimiterDynamics == null )
    		throw new ProcessingException( "UnderexcitationLimiterDynamics already has nothing assigned." );  

    	apply(new UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalEvent(command.getRemoteInputSignalId()));
    }
    @CommandHandler
    public void handle(AssignPFVArControllerType1DynamicsToRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPFVArControllerType1DynamicsToRemoteInputSignalCommand" );
    	
    	if (  pFVArControllerType1Dynamics != null && pFVArControllerType1Dynamics.getPFVArControllerType1DynamicsId() == command.getAssignment().getPFVArControllerType1DynamicsId() )
    		throw new ProcessingException( "PFVArControllerType1Dynamics already assigned with id " + command.getAssignment().getPFVArControllerType1DynamicsId() );  
    		
        apply(new AssignPFVArControllerType1DynamicsToRemoteInputSignalEvent(command.getRemoteInputSignalId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalCommand" );

    	if (  pFVArControllerType1Dynamics == null )
    		throw new ProcessingException( "PFVArControllerType1Dynamics already has nothing assigned." );  

    	apply(new UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalEvent(command.getRemoteInputSignalId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateRemoteInputSignalEvent event) {	
    	LOGGER.info( "Event sourcing CreateRemoteInputSignalEvent" );
    	this.remoteInputSignalId = event.getRemoteInputSignalId();
        this.remoteSignalType = event.getRemoteSignalType();
    }
    
    @EventSourcingHandler
    void on(UpdateRemoteInputSignalEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.remoteSignalType = event.getRemoteSignalType();
        this.terminal = event.getTerminal();
        this.discontinuousExcitationControlDynamics = event.getDiscontinuousExcitationControlDynamics();
        this.powerSystemStabilizerDynamics = event.getPowerSystemStabilizerDynamics();
        this.voltageCompensatorDynamics = event.getVoltageCompensatorDynamics();
        this.underexcitationLimiterDynamics = event.getUnderexcitationLimiterDynamics();
        this.pFVArControllerType1Dynamics = event.getPFVArControllerType1Dynamics();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignTerminalToRemoteInputSignalEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTerminalToRemoteInputSignalEvent" );
    	this.terminal = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTerminalFromRemoteInputSignalEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTerminalFromRemoteInputSignalEvent" );
		this.terminal = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalEvent" );
    	this.discontinuousExcitationControlDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalEvent" );
		this.discontinuousExcitationControlDynamics = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignPowerSystemStabilizerDynamicsToRemoteInputSignalEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPowerSystemStabilizerDynamicsToRemoteInputSignalEvent" );
    	this.powerSystemStabilizerDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalEvent" );
		this.powerSystemStabilizerDynamics = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignVoltageCompensatorDynamicsToRemoteInputSignalEvent event ) {	
    	LOGGER.info( "Event sourcing AssignVoltageCompensatorDynamicsToRemoteInputSignalEvent" );
    	this.voltageCompensatorDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalEvent" );
		this.voltageCompensatorDynamics = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignUnderexcitationLimiterDynamicsToRemoteInputSignalEvent event ) {	
    	LOGGER.info( "Event sourcing AssignUnderexcitationLimiterDynamicsToRemoteInputSignalEvent" );
    	this.underexcitationLimiterDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalEvent" );
		this.underexcitationLimiterDynamics = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignPFVArControllerType1DynamicsToRemoteInputSignalEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPFVArControllerType1DynamicsToRemoteInputSignalEvent" );
    	this.pFVArControllerType1Dynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalEvent" );
		this.pFVArControllerType1Dynamics = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID remoteInputSignalId;
    
    private RemoteSignalKind remoteSignalType;
    private Terminal terminal = null;
    private DiscontinuousExcitationControlDynamics discontinuousExcitationControlDynamics = null;
    private PowerSystemStabilizerDynamics powerSystemStabilizerDynamics = null;
    private VoltageCompensatorDynamics voltageCompensatorDynamics = null;
    private UnderexcitationLimiterDynamics underexcitationLimiterDynamics = null;
    private PFVArControllerType1Dynamics pFVArControllerType1Dynamics = null;
    private WindPlantDynamics remoteInputSignal = null;

    private static final Logger LOGGER 	= Logger.getLogger(RemoteInputSignalAggregate.class.getName());
}
