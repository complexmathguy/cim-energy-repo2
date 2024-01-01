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
 * Aggregate handler for EnergySource as outlined for the CQRS pattern, all write responsibilities 
 * related to EnergySource are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class EnergySourceAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public EnergySourceAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public EnergySourceAggregate(CreateEnergySourceCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateEnergySourceCommand" );
    	CreateEnergySourceEvent event = new CreateEnergySourceEvent(command.getEnergySourceId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateEnergySourceCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateEnergySourceCommand" );
    	UpdateEnergySourceEvent event = new UpdateEnergySourceEvent(command.getEnergySourceId(), command.getEnergySchedulingType());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteEnergySourceCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteEnergySourceCommand" );
        apply(new DeleteEnergySourceEvent(command.getEnergySourceId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignEnergySchedulingTypeToEnergySourceCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignEnergySchedulingTypeToEnergySourceCommand" );
    	
    	if (  energySchedulingType != null && energySchedulingType.getEnergySchedulingTypeId() == command.getAssignment().getEnergySchedulingTypeId() )
    		throw new ProcessingException( "EnergySchedulingType already assigned with id " + command.getAssignment().getEnergySchedulingTypeId() );  
    		
        apply(new AssignEnergySchedulingTypeToEnergySourceEvent(command.getEnergySourceId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignEnergySchedulingTypeFromEnergySourceCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignEnergySchedulingTypeFromEnergySourceCommand" );

    	if (  energySchedulingType == null )
    		throw new ProcessingException( "EnergySchedulingType already has nothing assigned." );  

    	apply(new UnAssignEnergySchedulingTypeFromEnergySourceEvent(command.getEnergySourceId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateEnergySourceEvent event) {	
    	LOGGER.info( "Event sourcing CreateEnergySourceEvent" );
    	this.energySourceId = event.getEnergySourceId();
    }
    
    @EventSourcingHandler
    void on(UpdateEnergySourceEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.energySchedulingType = event.getEnergySchedulingType();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignEnergySchedulingTypeToEnergySourceEvent event ) {	
    	LOGGER.info( "Event sourcing AssignEnergySchedulingTypeToEnergySourceEvent" );
    	this.energySchedulingType = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignEnergySchedulingTypeFromEnergySourceEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignEnergySchedulingTypeFromEnergySourceEvent" );
		this.energySchedulingType = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID energySourceId;
    
    private EnergySchedulingType energySchedulingType = null;
    private WindTurbineType3or4Dynamics energySource = null;

    private static final Logger LOGGER 	= Logger.getLogger(EnergySourceAggregate.class.getName());
}
