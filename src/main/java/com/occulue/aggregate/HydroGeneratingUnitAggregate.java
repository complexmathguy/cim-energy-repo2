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
 * Aggregate handler for HydroGeneratingUnit as outlined for the CQRS pattern, all write responsibilities 
 * related to HydroGeneratingUnit are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class HydroGeneratingUnitAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public HydroGeneratingUnitAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public HydroGeneratingUnitAggregate(CreateHydroGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateHydroGeneratingUnitCommand" );
    	CreateHydroGeneratingUnitEvent event = new CreateHydroGeneratingUnitEvent(command.getHydroGeneratingUnitId(), command.getEnergyConversionCapability());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateHydroGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateHydroGeneratingUnitCommand" );
    	UpdateHydroGeneratingUnitEvent event = new UpdateHydroGeneratingUnitEvent(command.getHydroGeneratingUnitId(), command.getEnergyConversionCapability(), command.getHydroPowerPlant());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteHydroGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteHydroGeneratingUnitCommand" );
        apply(new DeleteHydroGeneratingUnitEvent(command.getHydroGeneratingUnitId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignHydroPowerPlantToHydroGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignHydroPowerPlantToHydroGeneratingUnitCommand" );
    	
    	if (  hydroPowerPlant != null && hydroPowerPlant.getHydroPowerPlantId() == command.getAssignment().getHydroPowerPlantId() )
    		throw new ProcessingException( "HydroPowerPlant already assigned with id " + command.getAssignment().getHydroPowerPlantId() );  
    		
        apply(new AssignHydroPowerPlantToHydroGeneratingUnitEvent(command.getHydroGeneratingUnitId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignHydroPowerPlantFromHydroGeneratingUnitCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignHydroPowerPlantFromHydroGeneratingUnitCommand" );

    	if (  hydroPowerPlant == null )
    		throw new ProcessingException( "HydroPowerPlant already has nothing assigned." );  

    	apply(new UnAssignHydroPowerPlantFromHydroGeneratingUnitEvent(command.getHydroGeneratingUnitId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateHydroGeneratingUnitEvent event) {	
    	LOGGER.info( "Event sourcing CreateHydroGeneratingUnitEvent" );
    	this.hydroGeneratingUnitId = event.getHydroGeneratingUnitId();
        this.energyConversionCapability = event.getEnergyConversionCapability();
    }
    
    @EventSourcingHandler
    void on(UpdateHydroGeneratingUnitEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.energyConversionCapability = event.getEnergyConversionCapability();
        this.hydroPowerPlant = event.getHydroPowerPlant();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignHydroPowerPlantToHydroGeneratingUnitEvent event ) {	
    	LOGGER.info( "Event sourcing AssignHydroPowerPlantToHydroGeneratingUnitEvent" );
    	this.hydroPowerPlant = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignHydroPowerPlantFromHydroGeneratingUnitEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignHydroPowerPlantFromHydroGeneratingUnitEvent" );
		this.hydroPowerPlant = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID hydroGeneratingUnitId;
    
    private HydroEnergyConversionKind energyConversionCapability;
    private HydroPowerPlant hydroPowerPlant = null;

    private static final Logger LOGGER 	= Logger.getLogger(HydroGeneratingUnitAggregate.class.getName());
}
