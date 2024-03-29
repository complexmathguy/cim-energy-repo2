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
 * Aggregate handler for Bay as outlined for the CQRS pattern, all write responsibilities 
 * related to Bay are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class BayAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public BayAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public BayAggregate(CreateBayCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateBayCommand" );
    	CreateBayEvent event = new CreateBayEvent(command.getBayId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateBayCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateBayCommand" );
    	UpdateBayEvent event = new UpdateBayEvent(command.getBayId(), command.getVoltageLevel());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteBayCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteBayCommand" );
        apply(new DeleteBayEvent(command.getBayId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignVoltageLevelToBayCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignVoltageLevelToBayCommand" );
    	
    	if (  voltageLevel != null && voltageLevel.getVoltageLevelId() == command.getAssignment().getVoltageLevelId() )
    		throw new ProcessingException( "VoltageLevel already assigned with id " + command.getAssignment().getVoltageLevelId() );  
    		
        apply(new AssignVoltageLevelToBayEvent(command.getBayId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignVoltageLevelFromBayCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignVoltageLevelFromBayCommand" );

    	if (  voltageLevel == null )
    		throw new ProcessingException( "VoltageLevel already has nothing assigned." );  

    	apply(new UnAssignVoltageLevelFromBayEvent(command.getBayId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateBayEvent event) {	
    	LOGGER.info( "Event sourcing CreateBayEvent" );
    	this.bayId = event.getBayId();
    }
    
    @EventSourcingHandler
    void on(UpdateBayEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.voltageLevel = event.getVoltageLevel();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignVoltageLevelToBayEvent event ) {	
    	LOGGER.info( "Event sourcing AssignVoltageLevelToBayEvent" );
    	this.voltageLevel = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignVoltageLevelFromBayEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignVoltageLevelFromBayEvent" );
		this.voltageLevel = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID bayId;
    
    private VoltageLevel voltageLevel = null;

    private static final Logger LOGGER 	= Logger.getLogger(BayAggregate.class.getName());
}
