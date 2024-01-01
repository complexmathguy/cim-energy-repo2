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
 * Aggregate handler for EquivalentEquipment as outlined for the CQRS pattern, all write responsibilities 
 * related to EquivalentEquipment are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class EquivalentEquipmentAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public EquivalentEquipmentAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public EquivalentEquipmentAggregate(CreateEquivalentEquipmentCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateEquivalentEquipmentCommand" );
    	CreateEquivalentEquipmentEvent event = new CreateEquivalentEquipmentEvent(command.getEquivalentEquipmentId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateEquivalentEquipmentCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateEquivalentEquipmentCommand" );
    	UpdateEquivalentEquipmentEvent event = new UpdateEquivalentEquipmentEvent(command.getEquivalentEquipmentId(), command.getEquivalentNetwork());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteEquivalentEquipmentCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteEquivalentEquipmentCommand" );
        apply(new DeleteEquivalentEquipmentEvent(command.getEquivalentEquipmentId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignEquivalentNetworkToEquivalentEquipmentCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignEquivalentNetworkToEquivalentEquipmentCommand" );
    	
    	if (  equivalentNetwork != null && equivalentNetwork.getEquivalentNetworkId() == command.getAssignment().getEquivalentNetworkId() )
    		throw new ProcessingException( "EquivalentNetwork already assigned with id " + command.getAssignment().getEquivalentNetworkId() );  
    		
        apply(new AssignEquivalentNetworkToEquivalentEquipmentEvent(command.getEquivalentEquipmentId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignEquivalentNetworkFromEquivalentEquipmentCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignEquivalentNetworkFromEquivalentEquipmentCommand" );

    	if (  equivalentNetwork == null )
    		throw new ProcessingException( "EquivalentNetwork already has nothing assigned." );  

    	apply(new UnAssignEquivalentNetworkFromEquivalentEquipmentEvent(command.getEquivalentEquipmentId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateEquivalentEquipmentEvent event) {	
    	LOGGER.info( "Event sourcing CreateEquivalentEquipmentEvent" );
    	this.equivalentEquipmentId = event.getEquivalentEquipmentId();
    }
    
    @EventSourcingHandler
    void on(UpdateEquivalentEquipmentEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.equivalentNetwork = event.getEquivalentNetwork();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignEquivalentNetworkToEquivalentEquipmentEvent event ) {	
    	LOGGER.info( "Event sourcing AssignEquivalentNetworkToEquivalentEquipmentEvent" );
    	this.equivalentNetwork = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignEquivalentNetworkFromEquivalentEquipmentEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignEquivalentNetworkFromEquivalentEquipmentEvent" );
		this.equivalentNetwork = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID equivalentEquipmentId;
    
    private EquivalentNetwork equivalentNetwork = null;

    private static final Logger LOGGER 	= Logger.getLogger(EquivalentEquipmentAggregate.class.getName());
}
