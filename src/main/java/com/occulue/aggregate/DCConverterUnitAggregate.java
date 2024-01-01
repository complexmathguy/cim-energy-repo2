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
 * Aggregate handler for DCConverterUnit as outlined for the CQRS pattern, all write responsibilities 
 * related to DCConverterUnit are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class DCConverterUnitAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public DCConverterUnitAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public DCConverterUnitAggregate(CreateDCConverterUnitCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateDCConverterUnitCommand" );
    	CreateDCConverterUnitEvent event = new CreateDCConverterUnitEvent(command.getDCConverterUnitId(), command.getOperationMode());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateDCConverterUnitCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateDCConverterUnitCommand" );
    	UpdateDCConverterUnitEvent event = new UpdateDCConverterUnitEvent(command.getDCConverterUnitId(), command.getOperationMode(), command.getSubstation());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteDCConverterUnitCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteDCConverterUnitCommand" );
        apply(new DeleteDCConverterUnitEvent(command.getDCConverterUnitId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignSubstationToDCConverterUnitCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSubstationToDCConverterUnitCommand" );
    	
    	if (  substation != null && substation.getSubstationId() == command.getAssignment().getSubstationId() )
    		throw new ProcessingException( "Substation already assigned with id " + command.getAssignment().getSubstationId() );  
    		
        apply(new AssignSubstationToDCConverterUnitEvent(command.getDCConverterUnitId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSubstationFromDCConverterUnitCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSubstationFromDCConverterUnitCommand" );

    	if (  substation == null )
    		throw new ProcessingException( "Substation already has nothing assigned." );  

    	apply(new UnAssignSubstationFromDCConverterUnitEvent(command.getDCConverterUnitId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateDCConverterUnitEvent event) {	
    	LOGGER.info( "Event sourcing CreateDCConverterUnitEvent" );
    	this.dCConverterUnitId = event.getDCConverterUnitId();
        this.operationMode = event.getOperationMode();
    }
    
    @EventSourcingHandler
    void on(UpdateDCConverterUnitEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.operationMode = event.getOperationMode();
        this.substation = event.getSubstation();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignSubstationToDCConverterUnitEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSubstationToDCConverterUnitEvent" );
    	this.substation = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSubstationFromDCConverterUnitEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSubstationFromDCConverterUnitEvent" );
		this.substation = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID dCConverterUnitId;
    
    private DCConverterOperatingModeKind operationMode;
    private Substation substation = null;

    private static final Logger LOGGER 	= Logger.getLogger(DCConverterUnitAggregate.class.getName());
}
