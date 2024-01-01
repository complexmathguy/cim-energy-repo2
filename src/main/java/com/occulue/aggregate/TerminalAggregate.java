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
 * Aggregate handler for Terminal as outlined for the CQRS pattern, all write responsibilities 
 * related to Terminal are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class TerminalAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public TerminalAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public TerminalAggregate(CreateTerminalCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateTerminalCommand" );
    	CreateTerminalEvent event = new CreateTerminalEvent(command.getTerminalId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateTerminalCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateTerminalCommand" );
    	UpdateTerminalEvent event = new UpdateTerminalEvent(command.getTerminalId(), command.getConductingEquipment());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteTerminalCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteTerminalCommand" );
        apply(new DeleteTerminalEvent(command.getTerminalId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignConductingEquipmentToTerminalCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignConductingEquipmentToTerminalCommand" );
    	
    	if (  conductingEquipment != null && conductingEquipment.getConductingEquipmentId() == command.getAssignment().getConductingEquipmentId() )
    		throw new ProcessingException( "ConductingEquipment already assigned with id " + command.getAssignment().getConductingEquipmentId() );  
    		
        apply(new AssignConductingEquipmentToTerminalEvent(command.getTerminalId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignConductingEquipmentFromTerminalCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignConductingEquipmentFromTerminalCommand" );

    	if (  conductingEquipment == null )
    		throw new ProcessingException( "ConductingEquipment already has nothing assigned." );  

    	apply(new UnAssignConductingEquipmentFromTerminalEvent(command.getTerminalId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateTerminalEvent event) {	
    	LOGGER.info( "Event sourcing CreateTerminalEvent" );
    	this.terminalId = event.getTerminalId();
    }
    
    @EventSourcingHandler
    void on(UpdateTerminalEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.conductingEquipment = event.getConductingEquipment();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignConductingEquipmentToTerminalEvent event ) {	
    	LOGGER.info( "Event sourcing AssignConductingEquipmentToTerminalEvent" );
    	this.conductingEquipment = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignConductingEquipmentFromTerminalEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignConductingEquipmentFromTerminalEvent" );
		this.conductingEquipment = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID terminalId;
    
    private ConductingEquipment conductingEquipment = null;
    private ACDCConverter pccTerminal = null;
    private MutualCoupling second_Terminal = null;
    private MutualCoupling first_Terminal = null;
    private RegulatingControl terminal = null;

    private static final Logger LOGGER 	= Logger.getLogger(TerminalAggregate.class.getName());
}
