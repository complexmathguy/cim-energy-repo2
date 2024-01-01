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
 * Aggregate handler for HydroPump as outlined for the CQRS pattern, all write responsibilities 
 * related to HydroPump are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class HydroPumpAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public HydroPumpAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public HydroPumpAggregate(CreateHydroPumpCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateHydroPumpCommand" );
    	CreateHydroPumpEvent event = new CreateHydroPumpEvent(command.getHydroPumpId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateHydroPumpCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateHydroPumpCommand" );
    	UpdateHydroPumpEvent event = new UpdateHydroPumpEvent(command.getHydroPumpId(), command.getRotatingMachine(), command.getHydroPowerPlant());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteHydroPumpCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteHydroPumpCommand" );
        apply(new DeleteHydroPumpEvent(command.getHydroPumpId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignRotatingMachineToHydroPumpCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRotatingMachineToHydroPumpCommand" );
    	
    	if (  rotatingMachine != null && rotatingMachine.getRotatingMachineId() == command.getAssignment().getRotatingMachineId() )
    		throw new ProcessingException( "RotatingMachine already assigned with id " + command.getAssignment().getRotatingMachineId() );  
    		
        apply(new AssignRotatingMachineToHydroPumpEvent(command.getHydroPumpId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRotatingMachineFromHydroPumpCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRotatingMachineFromHydroPumpCommand" );

    	if (  rotatingMachine == null )
    		throw new ProcessingException( "RotatingMachine already has nothing assigned." );  

    	apply(new UnAssignRotatingMachineFromHydroPumpEvent(command.getHydroPumpId()));
    }
    @CommandHandler
    public void handle(AssignHydroPowerPlantToHydroPumpCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignHydroPowerPlantToHydroPumpCommand" );
    	
    	if (  hydroPowerPlant != null && hydroPowerPlant.getHydroPowerPlantId() == command.getAssignment().getHydroPowerPlantId() )
    		throw new ProcessingException( "HydroPowerPlant already assigned with id " + command.getAssignment().getHydroPowerPlantId() );  
    		
        apply(new AssignHydroPowerPlantToHydroPumpEvent(command.getHydroPumpId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignHydroPowerPlantFromHydroPumpCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignHydroPowerPlantFromHydroPumpCommand" );

    	if (  hydroPowerPlant == null )
    		throw new ProcessingException( "HydroPowerPlant already has nothing assigned." );  

    	apply(new UnAssignHydroPowerPlantFromHydroPumpEvent(command.getHydroPumpId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateHydroPumpEvent event) {	
    	LOGGER.info( "Event sourcing CreateHydroPumpEvent" );
    	this.hydroPumpId = event.getHydroPumpId();
    }
    
    @EventSourcingHandler
    void on(UpdateHydroPumpEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.rotatingMachine = event.getRotatingMachine();
        this.hydroPowerPlant = event.getHydroPowerPlant();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignRotatingMachineToHydroPumpEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRotatingMachineToHydroPumpEvent" );
    	this.rotatingMachine = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRotatingMachineFromHydroPumpEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRotatingMachineFromHydroPumpEvent" );
		this.rotatingMachine = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignHydroPowerPlantToHydroPumpEvent event ) {	
    	LOGGER.info( "Event sourcing AssignHydroPowerPlantToHydroPumpEvent" );
    	this.hydroPowerPlant = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignHydroPowerPlantFromHydroPumpEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignHydroPowerPlantFromHydroPumpEvent" );
		this.hydroPowerPlant = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID hydroPumpId;
    
    private RotatingMachine rotatingMachine = null;
    private HydroPowerPlant hydroPowerPlant = null;

    private static final Logger LOGGER 	= Logger.getLogger(HydroPumpAggregate.class.getName());
}
