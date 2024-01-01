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
 * Aggregate handler for ACDCConverterDCTerminal as outlined for the CQRS pattern, all write responsibilities 
 * related to ACDCConverterDCTerminal are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ACDCConverterDCTerminalAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ACDCConverterDCTerminalAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ACDCConverterDCTerminalAggregate(CreateACDCConverterDCTerminalCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateACDCConverterDCTerminalCommand" );
    	CreateACDCConverterDCTerminalEvent event = new CreateACDCConverterDCTerminalEvent(command.getACDCConverterDCTerminalId(), command.getPolarity());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateACDCConverterDCTerminalCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateACDCConverterDCTerminalCommand" );
    	UpdateACDCConverterDCTerminalEvent event = new UpdateACDCConverterDCTerminalEvent(command.getACDCConverterDCTerminalId(), command.getPolarity(), command.getDCConductingEquipment());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteACDCConverterDCTerminalCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteACDCConverterDCTerminalCommand" );
        apply(new DeleteACDCConverterDCTerminalEvent(command.getACDCConverterDCTerminalId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignDCConductingEquipmentToACDCConverterDCTerminalCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDCConductingEquipmentToACDCConverterDCTerminalCommand" );
    	
    	if (  dCConductingEquipment != null && dCConductingEquipment.getACDCConverterId() == command.getAssignment().getACDCConverterId() )
    		throw new ProcessingException( "DCConductingEquipment already assigned with id " + command.getAssignment().getACDCConverterId() );  
    		
        apply(new AssignDCConductingEquipmentToACDCConverterDCTerminalEvent(command.getACDCConverterDCTerminalId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDCConductingEquipmentFromACDCConverterDCTerminalCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDCConductingEquipmentFromACDCConverterDCTerminalCommand" );

    	if (  dCConductingEquipment == null )
    		throw new ProcessingException( "DCConductingEquipment already has nothing assigned." );  

    	apply(new UnAssignDCConductingEquipmentFromACDCConverterDCTerminalEvent(command.getACDCConverterDCTerminalId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateACDCConverterDCTerminalEvent event) {	
    	LOGGER.info( "Event sourcing CreateACDCConverterDCTerminalEvent" );
    	this.aCDCConverterDCTerminalId = event.getACDCConverterDCTerminalId();
        this.polarity = event.getPolarity();
    }
    
    @EventSourcingHandler
    void on(UpdateACDCConverterDCTerminalEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.polarity = event.getPolarity();
        this.dCConductingEquipment = event.getDCConductingEquipment();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignDCConductingEquipmentToACDCConverterDCTerminalEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDCConductingEquipmentToACDCConverterDCTerminalEvent" );
    	this.dCConductingEquipment = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDCConductingEquipmentFromACDCConverterDCTerminalEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDCConductingEquipmentFromACDCConverterDCTerminalEvent" );
		this.dCConductingEquipment = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID aCDCConverterDCTerminalId;
    
    private DCPolarityKind polarity;
    private ACDCConverter dCConductingEquipment = null;

    private static final Logger LOGGER 	= Logger.getLogger(ACDCConverterDCTerminalAggregate.class.getName());
}
