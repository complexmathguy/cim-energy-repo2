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
 * Aggregate handler for Equipment as outlined for the CQRS pattern, all write responsibilities 
 * related to Equipment are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class EquipmentAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public EquipmentAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public EquipmentAggregate(CreateEquipmentCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateEquipmentCommand" );
    	CreateEquipmentEvent event = new CreateEquipmentEvent(command.getEquipmentId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateEquipmentCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateEquipmentCommand" );
    	UpdateEquipmentEvent event = new UpdateEquipmentEvent(command.getEquipmentId(), command.getEquipmentContainer());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteEquipmentCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteEquipmentCommand" );
        apply(new DeleteEquipmentEvent(command.getEquipmentId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignEquipmentContainerToEquipmentCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignEquipmentContainerToEquipmentCommand" );
    	
    	if (  equipmentContainer != null && equipmentContainer.getEquipmentContainerId() == command.getAssignment().getEquipmentContainerId() )
    		throw new ProcessingException( "EquipmentContainer already assigned with id " + command.getAssignment().getEquipmentContainerId() );  
    		
        apply(new AssignEquipmentContainerToEquipmentEvent(command.getEquipmentId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignEquipmentContainerFromEquipmentCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignEquipmentContainerFromEquipmentCommand" );

    	if (  equipmentContainer == null )
    		throw new ProcessingException( "EquipmentContainer already has nothing assigned." );  

    	apply(new UnAssignEquipmentContainerFromEquipmentEvent(command.getEquipmentId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateEquipmentEvent event) {	
    	LOGGER.info( "Event sourcing CreateEquipmentEvent" );
    	this.equipmentId = event.getEquipmentId();
    }
    
    @EventSourcingHandler
    void on(UpdateEquipmentEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.equipmentContainer = event.getEquipmentContainer();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignEquipmentContainerToEquipmentEvent event ) {	
    	LOGGER.info( "Event sourcing AssignEquipmentContainerToEquipmentEvent" );
    	this.equipmentContainer = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignEquipmentContainerFromEquipmentEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignEquipmentContainerFromEquipmentEvent" );
		this.equipmentContainer = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID equipmentId;
    
    private EquipmentContainer equipmentContainer = null;
    private OperationalLimitSet equipment = null;

    private static final Logger LOGGER 	= Logger.getLogger(EquipmentAggregate.class.getName());
}
