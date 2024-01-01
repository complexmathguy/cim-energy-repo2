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
 * Aggregate handler for ControlAreaGeneratingUnit as outlined for the CQRS pattern, all write responsibilities 
 * related to ControlAreaGeneratingUnit are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ControlAreaGeneratingUnitAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ControlAreaGeneratingUnitAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ControlAreaGeneratingUnitAggregate(CreateControlAreaGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateControlAreaGeneratingUnitCommand" );
    	CreateControlAreaGeneratingUnitEvent event = new CreateControlAreaGeneratingUnitEvent(command.getControlAreaGeneratingUnitId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateControlAreaGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateControlAreaGeneratingUnitCommand" );
    	UpdateControlAreaGeneratingUnitEvent event = new UpdateControlAreaGeneratingUnitEvent(command.getControlAreaGeneratingUnitId(), command.getControlArea(), command.getGeneratingUnit());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteControlAreaGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteControlAreaGeneratingUnitCommand" );
        apply(new DeleteControlAreaGeneratingUnitEvent(command.getControlAreaGeneratingUnitId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignControlAreaToControlAreaGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignControlAreaToControlAreaGeneratingUnitCommand" );
    	
    	if (  controlArea != null && controlArea.getControlAreaId() == command.getAssignment().getControlAreaId() )
    		throw new ProcessingException( "ControlArea already assigned with id " + command.getAssignment().getControlAreaId() );  
    		
        apply(new AssignControlAreaToControlAreaGeneratingUnitEvent(command.getControlAreaGeneratingUnitId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignControlAreaFromControlAreaGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignControlAreaFromControlAreaGeneratingUnitCommand" );

    	if (  controlArea == null )
    		throw new ProcessingException( "ControlArea already has nothing assigned." );  

    	apply(new UnAssignControlAreaFromControlAreaGeneratingUnitEvent(command.getControlAreaGeneratingUnitId()));
    }
    @CommandHandler
    public void handle(AssignGeneratingUnitToControlAreaGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignGeneratingUnitToControlAreaGeneratingUnitCommand" );
    	
    	if (  generatingUnit != null && generatingUnit.getGeneratingUnitId() == command.getAssignment().getGeneratingUnitId() )
    		throw new ProcessingException( "GeneratingUnit already assigned with id " + command.getAssignment().getGeneratingUnitId() );  
    		
        apply(new AssignGeneratingUnitToControlAreaGeneratingUnitEvent(command.getControlAreaGeneratingUnitId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignGeneratingUnitFromControlAreaGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignGeneratingUnitFromControlAreaGeneratingUnitCommand" );

    	if (  generatingUnit == null )
    		throw new ProcessingException( "GeneratingUnit already has nothing assigned." );  

    	apply(new UnAssignGeneratingUnitFromControlAreaGeneratingUnitEvent(command.getControlAreaGeneratingUnitId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateControlAreaGeneratingUnitEvent event) {	
    	LOGGER.info( "Event sourcing CreateControlAreaGeneratingUnitEvent" );
    	this.controlAreaGeneratingUnitId = event.getControlAreaGeneratingUnitId();
    }
    
    @EventSourcingHandler
    void on(UpdateControlAreaGeneratingUnitEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.controlArea = event.getControlArea();
        this.generatingUnit = event.getGeneratingUnit();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignControlAreaToControlAreaGeneratingUnitEvent event ) {	
    	LOGGER.info( "Event sourcing AssignControlAreaToControlAreaGeneratingUnitEvent" );
    	this.controlArea = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignControlAreaFromControlAreaGeneratingUnitEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignControlAreaFromControlAreaGeneratingUnitEvent" );
		this.controlArea = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignGeneratingUnitToControlAreaGeneratingUnitEvent event ) {	
    	LOGGER.info( "Event sourcing AssignGeneratingUnitToControlAreaGeneratingUnitEvent" );
    	this.generatingUnit = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignGeneratingUnitFromControlAreaGeneratingUnitEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignGeneratingUnitFromControlAreaGeneratingUnitEvent" );
		this.generatingUnit = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID controlAreaGeneratingUnitId;
    
    private ControlArea controlArea = null;
    private GeneratingUnit generatingUnit = null;

    private static final Logger LOGGER 	= Logger.getLogger(ControlAreaGeneratingUnitAggregate.class.getName());
}
