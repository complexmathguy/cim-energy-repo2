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
 * Aggregate handler for DCNode as outlined for the CQRS pattern, all write responsibilities 
 * related to DCNode are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class DCNodeAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public DCNodeAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public DCNodeAggregate(CreateDCNodeCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateDCNodeCommand" );
    	CreateDCNodeEvent event = new CreateDCNodeEvent(command.getDCNodeId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateDCNodeCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateDCNodeCommand" );
    	UpdateDCNodeEvent event = new UpdateDCNodeEvent(command.getDCNodeId(), command.getDCEquipmentContainer());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteDCNodeCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteDCNodeCommand" );
        apply(new DeleteDCNodeEvent(command.getDCNodeId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignDCEquipmentContainerToDCNodeCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDCEquipmentContainerToDCNodeCommand" );
    	
    	if (  dCEquipmentContainer != null && dCEquipmentContainer.getDCEquipmentContainerId() == command.getAssignment().getDCEquipmentContainerId() )
    		throw new ProcessingException( "DCEquipmentContainer already assigned with id " + command.getAssignment().getDCEquipmentContainerId() );  
    		
        apply(new AssignDCEquipmentContainerToDCNodeEvent(command.getDCNodeId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDCEquipmentContainerFromDCNodeCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDCEquipmentContainerFromDCNodeCommand" );

    	if (  dCEquipmentContainer == null )
    		throw new ProcessingException( "DCEquipmentContainer already has nothing assigned." );  

    	apply(new UnAssignDCEquipmentContainerFromDCNodeEvent(command.getDCNodeId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateDCNodeEvent event) {	
    	LOGGER.info( "Event sourcing CreateDCNodeEvent" );
    	this.dCNodeId = event.getDCNodeId();
    }
    
    @EventSourcingHandler
    void on(UpdateDCNodeEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.dCEquipmentContainer = event.getDCEquipmentContainer();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignDCEquipmentContainerToDCNodeEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDCEquipmentContainerToDCNodeEvent" );
    	this.dCEquipmentContainer = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDCEquipmentContainerFromDCNodeEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDCEquipmentContainerFromDCNodeEvent" );
		this.dCEquipmentContainer = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID dCNodeId;
    
    private DCBaseTerminal dCNode = null;
    private DCEquipmentContainer dCEquipmentContainer = null;

    private static final Logger LOGGER 	= Logger.getLogger(DCNodeAggregate.class.getName());
}
