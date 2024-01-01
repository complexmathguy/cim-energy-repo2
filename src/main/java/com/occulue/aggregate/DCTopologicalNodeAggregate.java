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
 * Aggregate handler for DCTopologicalNode as outlined for the CQRS pattern, all write responsibilities 
 * related to DCTopologicalNode are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class DCTopologicalNodeAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public DCTopologicalNodeAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public DCTopologicalNodeAggregate(CreateDCTopologicalNodeCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateDCTopologicalNodeCommand" );
    	CreateDCTopologicalNodeEvent event = new CreateDCTopologicalNodeEvent(command.getDCTopologicalNodeId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateDCTopologicalNodeCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateDCTopologicalNodeCommand" );
    	UpdateDCTopologicalNodeEvent event = new UpdateDCTopologicalNodeEvent(command.getDCTopologicalNodeId(), command.getDCEquipmentContainer());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteDCTopologicalNodeCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteDCTopologicalNodeCommand" );
        apply(new DeleteDCTopologicalNodeEvent(command.getDCTopologicalNodeId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignDCEquipmentContainerToDCTopologicalNodeCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDCEquipmentContainerToDCTopologicalNodeCommand" );
    	
    	if (  dCEquipmentContainer != null && dCEquipmentContainer.getDCEquipmentContainerId() == command.getAssignment().getDCEquipmentContainerId() )
    		throw new ProcessingException( "DCEquipmentContainer already assigned with id " + command.getAssignment().getDCEquipmentContainerId() );  
    		
        apply(new AssignDCEquipmentContainerToDCTopologicalNodeEvent(command.getDCTopologicalNodeId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDCEquipmentContainerFromDCTopologicalNodeCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDCEquipmentContainerFromDCTopologicalNodeCommand" );

    	if (  dCEquipmentContainer == null )
    		throw new ProcessingException( "DCEquipmentContainer already has nothing assigned." );  

    	apply(new UnAssignDCEquipmentContainerFromDCTopologicalNodeEvent(command.getDCTopologicalNodeId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateDCTopologicalNodeEvent event) {	
    	LOGGER.info( "Event sourcing CreateDCTopologicalNodeEvent" );
    	this.dCTopologicalNodeId = event.getDCTopologicalNodeId();
    }
    
    @EventSourcingHandler
    void on(UpdateDCTopologicalNodeEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.dCEquipmentContainer = event.getDCEquipmentContainer();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignDCEquipmentContainerToDCTopologicalNodeEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDCEquipmentContainerToDCTopologicalNodeEvent" );
    	this.dCEquipmentContainer = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDCEquipmentContainerFromDCTopologicalNodeEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDCEquipmentContainerFromDCTopologicalNodeEvent" );
		this.dCEquipmentContainer = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID dCTopologicalNodeId;
    
    private DCEquipmentContainer dCEquipmentContainer = null;
    private DCTopologicalIsland dCTopologicalNodes = null;

    private static final Logger LOGGER 	= Logger.getLogger(DCTopologicalNodeAggregate.class.getName());
}
