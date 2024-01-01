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
 * Aggregate handler for OperationalLimitSet as outlined for the CQRS pattern, all write responsibilities 
 * related to OperationalLimitSet are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class OperationalLimitSetAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public OperationalLimitSetAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public OperationalLimitSetAggregate(CreateOperationalLimitSetCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateOperationalLimitSetCommand" );
    	CreateOperationalLimitSetEvent event = new CreateOperationalLimitSetEvent(command.getOperationalLimitSetId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateOperationalLimitSetCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateOperationalLimitSetCommand" );
    	UpdateOperationalLimitSetEvent event = new UpdateOperationalLimitSetEvent(command.getOperationalLimitSetId(), command.getEquipment(), command.getTerminal());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteOperationalLimitSetCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteOperationalLimitSetCommand" );
        apply(new DeleteOperationalLimitSetEvent(command.getOperationalLimitSetId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignEquipmentToOperationalLimitSetCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignEquipmentToOperationalLimitSetCommand" );
    	
    	if (  equipment != null && equipment.getEquipmentId() == command.getAssignment().getEquipmentId() )
    		throw new ProcessingException( "Equipment already assigned with id " + command.getAssignment().getEquipmentId() );  
    		
        apply(new AssignEquipmentToOperationalLimitSetEvent(command.getOperationalLimitSetId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignEquipmentFromOperationalLimitSetCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignEquipmentFromOperationalLimitSetCommand" );

    	if (  equipment == null )
    		throw new ProcessingException( "Equipment already has nothing assigned." );  

    	apply(new UnAssignEquipmentFromOperationalLimitSetEvent(command.getOperationalLimitSetId()));
    }
    @CommandHandler
    public void handle(AssignTerminalToOperationalLimitSetCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTerminalToOperationalLimitSetCommand" );
    	
    	if (  terminal != null && terminal.getACDCTerminalId() == command.getAssignment().getACDCTerminalId() )
    		throw new ProcessingException( "Terminal already assigned with id " + command.getAssignment().getACDCTerminalId() );  
    		
        apply(new AssignTerminalToOperationalLimitSetEvent(command.getOperationalLimitSetId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTerminalFromOperationalLimitSetCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTerminalFromOperationalLimitSetCommand" );

    	if (  terminal == null )
    		throw new ProcessingException( "Terminal already has nothing assigned." );  

    	apply(new UnAssignTerminalFromOperationalLimitSetEvent(command.getOperationalLimitSetId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateOperationalLimitSetEvent event) {	
    	LOGGER.info( "Event sourcing CreateOperationalLimitSetEvent" );
    	this.operationalLimitSetId = event.getOperationalLimitSetId();
    }
    
    @EventSourcingHandler
    void on(UpdateOperationalLimitSetEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.equipment = event.getEquipment();
        this.terminal = event.getTerminal();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignEquipmentToOperationalLimitSetEvent event ) {	
    	LOGGER.info( "Event sourcing AssignEquipmentToOperationalLimitSetEvent" );
    	this.equipment = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignEquipmentFromOperationalLimitSetEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignEquipmentFromOperationalLimitSetEvent" );
		this.equipment = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTerminalToOperationalLimitSetEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTerminalToOperationalLimitSetEvent" );
    	this.terminal = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTerminalFromOperationalLimitSetEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTerminalFromOperationalLimitSetEvent" );
		this.terminal = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID operationalLimitSetId;
    
    private Set<OperationalLimit> operationalLimitSet = new HashSet<>();
    private Equipment equipment = null;
    private ACDCTerminal terminal = null;

    private static final Logger LOGGER 	= Logger.getLogger(OperationalLimitSetAggregate.class.getName());
}
