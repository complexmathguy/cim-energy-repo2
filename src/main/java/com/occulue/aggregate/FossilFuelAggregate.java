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
 * Aggregate handler for FossilFuel as outlined for the CQRS pattern, all write responsibilities 
 * related to FossilFuel are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class FossilFuelAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public FossilFuelAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public FossilFuelAggregate(CreateFossilFuelCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateFossilFuelCommand" );
    	CreateFossilFuelEvent event = new CreateFossilFuelEvent(command.getFossilFuelId(), command.getFossilFuelType());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateFossilFuelCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateFossilFuelCommand" );
    	UpdateFossilFuelEvent event = new UpdateFossilFuelEvent(command.getFossilFuelId(), command.getFossilFuelType(), command.getThermalGeneratingUnit());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteFossilFuelCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteFossilFuelCommand" );
        apply(new DeleteFossilFuelEvent(command.getFossilFuelId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignThermalGeneratingUnitToFossilFuelCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignThermalGeneratingUnitToFossilFuelCommand" );
    	
    	if (  thermalGeneratingUnit != null && thermalGeneratingUnit.getThermalGeneratingUnitId() == command.getAssignment().getThermalGeneratingUnitId() )
    		throw new ProcessingException( "ThermalGeneratingUnit already assigned with id " + command.getAssignment().getThermalGeneratingUnitId() );  
    		
        apply(new AssignThermalGeneratingUnitToFossilFuelEvent(command.getFossilFuelId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignThermalGeneratingUnitFromFossilFuelCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignThermalGeneratingUnitFromFossilFuelCommand" );

    	if (  thermalGeneratingUnit == null )
    		throw new ProcessingException( "ThermalGeneratingUnit already has nothing assigned." );  

    	apply(new UnAssignThermalGeneratingUnitFromFossilFuelEvent(command.getFossilFuelId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateFossilFuelEvent event) {	
    	LOGGER.info( "Event sourcing CreateFossilFuelEvent" );
    	this.fossilFuelId = event.getFossilFuelId();
        this.fossilFuelType = event.getFossilFuelType();
    }
    
    @EventSourcingHandler
    void on(UpdateFossilFuelEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.fossilFuelType = event.getFossilFuelType();
        this.thermalGeneratingUnit = event.getThermalGeneratingUnit();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignThermalGeneratingUnitToFossilFuelEvent event ) {	
    	LOGGER.info( "Event sourcing AssignThermalGeneratingUnitToFossilFuelEvent" );
    	this.thermalGeneratingUnit = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignThermalGeneratingUnitFromFossilFuelEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignThermalGeneratingUnitFromFossilFuelEvent" );
		this.thermalGeneratingUnit = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID fossilFuelId;
    
    private FuelType fossilFuelType;
    private ThermalGeneratingUnit thermalGeneratingUnit = null;

    private static final Logger LOGGER 	= Logger.getLogger(FossilFuelAggregate.class.getName());
}
