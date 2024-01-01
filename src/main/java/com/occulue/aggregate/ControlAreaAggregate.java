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
 * Aggregate handler for ControlArea as outlined for the CQRS pattern, all write responsibilities 
 * related to ControlArea are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ControlAreaAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ControlAreaAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ControlAreaAggregate(CreateControlAreaCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateControlAreaCommand" );
    	CreateControlAreaEvent event = new CreateControlAreaEvent(command.getControlAreaId(), command.getType());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateControlAreaCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateControlAreaCommand" );
    	UpdateControlAreaEvent event = new UpdateControlAreaEvent(command.getControlAreaId(), command.getType(), command.getEnergyArea());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteControlAreaCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteControlAreaCommand" );
        apply(new DeleteControlAreaEvent(command.getControlAreaId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignEnergyAreaToControlAreaCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignEnergyAreaToControlAreaCommand" );
    	
    	if (  energyArea != null && energyArea.getEnergyAreaId() == command.getAssignment().getEnergyAreaId() )
    		throw new ProcessingException( "EnergyArea already assigned with id " + command.getAssignment().getEnergyAreaId() );  
    		
        apply(new AssignEnergyAreaToControlAreaEvent(command.getControlAreaId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignEnergyAreaFromControlAreaCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignEnergyAreaFromControlAreaCommand" );

    	if (  energyArea == null )
    		throw new ProcessingException( "EnergyArea already has nothing assigned." );  

    	apply(new UnAssignEnergyAreaFromControlAreaEvent(command.getControlAreaId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateControlAreaEvent event) {	
    	LOGGER.info( "Event sourcing CreateControlAreaEvent" );
    	this.controlAreaId = event.getControlAreaId();
        this.type = event.getType();
    }
    
    @EventSourcingHandler
    void on(UpdateControlAreaEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.type = event.getType();
        this.energyArea = event.getEnergyArea();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignEnergyAreaToControlAreaEvent event ) {	
    	LOGGER.info( "Event sourcing AssignEnergyAreaToControlAreaEvent" );
    	this.energyArea = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignEnergyAreaFromControlAreaEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignEnergyAreaFromControlAreaEvent" );
		this.energyArea = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID controlAreaId;
    
    private ControlAreaTypeKind type;
    private EnergyArea energyArea = null;
    private ControlAreaGeneratingUnit controlArea = null;

    private static final Logger LOGGER 	= Logger.getLogger(ControlAreaAggregate.class.getName());
}
