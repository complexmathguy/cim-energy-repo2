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
 * Aggregate handler for Location as outlined for the CQRS pattern, all write responsibilities 
 * related to Location are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class LocationAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public LocationAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public LocationAggregate(CreateLocationCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateLocationCommand" );
    	CreateLocationEvent event = new CreateLocationEvent(command.getLocationId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateLocationCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateLocationCommand" );
    	UpdateLocationEvent event = new UpdateLocationEvent(command.getLocationId(), command.getPowerSystemResources(), command.getCoordinateSystem());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteLocationCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteLocationCommand" );
        apply(new DeleteLocationEvent(command.getLocationId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignPowerSystemResourcesToLocationCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPowerSystemResourcesToLocationCommand" );
    	
    	if (  powerSystemResources != null && powerSystemResources.getPowerSystemResourceId() == command.getAssignment().getPowerSystemResourceId() )
    		throw new ProcessingException( "PowerSystemResources already assigned with id " + command.getAssignment().getPowerSystemResourceId() );  
    		
        apply(new AssignPowerSystemResourcesToLocationEvent(command.getLocationId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPowerSystemResourcesFromLocationCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPowerSystemResourcesFromLocationCommand" );

    	if (  powerSystemResources == null )
    		throw new ProcessingException( "PowerSystemResources already has nothing assigned." );  

    	apply(new UnAssignPowerSystemResourcesFromLocationEvent(command.getLocationId()));
    }
    @CommandHandler
    public void handle(AssignCoordinateSystemToLocationCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignCoordinateSystemToLocationCommand" );
    	
    	if (  coordinateSystem != null && coordinateSystem.getCoordinateSystemId() == command.getAssignment().getCoordinateSystemId() )
    		throw new ProcessingException( "CoordinateSystem already assigned with id " + command.getAssignment().getCoordinateSystemId() );  
    		
        apply(new AssignCoordinateSystemToLocationEvent(command.getLocationId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignCoordinateSystemFromLocationCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignCoordinateSystemFromLocationCommand" );

    	if (  coordinateSystem == null )
    		throw new ProcessingException( "CoordinateSystem already has nothing assigned." );  

    	apply(new UnAssignCoordinateSystemFromLocationEvent(command.getLocationId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateLocationEvent event) {	
    	LOGGER.info( "Event sourcing CreateLocationEvent" );
    	this.locationId = event.getLocationId();
    }
    
    @EventSourcingHandler
    void on(UpdateLocationEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.powerSystemResources = event.getPowerSystemResources();
        this.coordinateSystem = event.getCoordinateSystem();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignPowerSystemResourcesToLocationEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPowerSystemResourcesToLocationEvent" );
    	this.powerSystemResources = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPowerSystemResourcesFromLocationEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPowerSystemResourcesFromLocationEvent" );
		this.powerSystemResources = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignCoordinateSystemToLocationEvent event ) {	
    	LOGGER.info( "Event sourcing AssignCoordinateSystemToLocationEvent" );
    	this.coordinateSystem = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignCoordinateSystemFromLocationEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignCoordinateSystemFromLocationEvent" );
		this.coordinateSystem = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID locationId;
    
    private PowerSystemResource powerSystemResources = null;
    private CoordinateSystem coordinateSystem = null;
    private PositionPoint location = null;

    private static final Logger LOGGER 	= Logger.getLogger(LocationAggregate.class.getName());
}
