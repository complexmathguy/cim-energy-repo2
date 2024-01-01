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
 * Aggregate handler for WindTurbineType3or4IEC as outlined for the CQRS pattern, all write responsibilities 
 * related to WindTurbineType3or4IEC are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindTurbineType3or4IECAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindTurbineType3or4IECAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindTurbineType3or4IECAggregate(CreateWindTurbineType3or4IECCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindTurbineType3or4IECCommand" );
    	CreateWindTurbineType3or4IECEvent event = new CreateWindTurbineType3or4IECEvent(command.getWindTurbineType3or4IECId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindTurbineType3or4IECCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindTurbineType3or4IECCommand" );
    	UpdateWindTurbineType3or4IECEvent event = new UpdateWindTurbineType3or4IECEvent(command.getWindTurbineType3or4IECId(), command.getWindProtectionIEC(), command.getWindContCurrLimIEC(), command.getWIndContQIEC());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindTurbineType3or4IECCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindTurbineType3or4IECCommand" );
        apply(new DeleteWindTurbineType3or4IECEvent(command.getWindTurbineType3or4IECId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignWindProtectionIECToWindTurbineType3or4IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindProtectionIECToWindTurbineType3or4IECCommand" );
    	
    	if (  windProtectionIEC != null && windProtectionIEC.getWindProtectionIECId() == command.getAssignment().getWindProtectionIECId() )
    		throw new ProcessingException( "WindProtectionIEC already assigned with id " + command.getAssignment().getWindProtectionIECId() );  
    		
        apply(new AssignWindProtectionIECToWindTurbineType3or4IECEvent(command.getWindTurbineType3or4IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindProtectionIECFromWindTurbineType3or4IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindProtectionIECFromWindTurbineType3or4IECCommand" );

    	if (  windProtectionIEC == null )
    		throw new ProcessingException( "WindProtectionIEC already has nothing assigned." );  

    	apply(new UnAssignWindProtectionIECFromWindTurbineType3or4IECEvent(command.getWindTurbineType3or4IECId()));
    }
    @CommandHandler
    public void handle(AssignWindContCurrLimIECToWindTurbineType3or4IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindContCurrLimIECToWindTurbineType3or4IECCommand" );
    	
    	if (  windContCurrLimIEC != null && windContCurrLimIEC.getWindContCurrLimIECId() == command.getAssignment().getWindContCurrLimIECId() )
    		throw new ProcessingException( "WindContCurrLimIEC already assigned with id " + command.getAssignment().getWindContCurrLimIECId() );  
    		
        apply(new AssignWindContCurrLimIECToWindTurbineType3or4IECEvent(command.getWindTurbineType3or4IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindContCurrLimIECFromWindTurbineType3or4IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindContCurrLimIECFromWindTurbineType3or4IECCommand" );

    	if (  windContCurrLimIEC == null )
    		throw new ProcessingException( "WindContCurrLimIEC already has nothing assigned." );  

    	apply(new UnAssignWindContCurrLimIECFromWindTurbineType3or4IECEvent(command.getWindTurbineType3or4IECId()));
    }
    @CommandHandler
    public void handle(AssignWIndContQIECToWindTurbineType3or4IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWIndContQIECToWindTurbineType3or4IECCommand" );
    	
    	if (  wIndContQIEC != null && wIndContQIEC.getWindContQIECId() == command.getAssignment().getWindContQIECId() )
    		throw new ProcessingException( "WIndContQIEC already assigned with id " + command.getAssignment().getWindContQIECId() );  
    		
        apply(new AssignWIndContQIECToWindTurbineType3or4IECEvent(command.getWindTurbineType3or4IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWIndContQIECFromWindTurbineType3or4IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWIndContQIECFromWindTurbineType3or4IECCommand" );

    	if (  wIndContQIEC == null )
    		throw new ProcessingException( "WIndContQIEC already has nothing assigned." );  

    	apply(new UnAssignWIndContQIECFromWindTurbineType3or4IECEvent(command.getWindTurbineType3or4IECId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindTurbineType3or4IECEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindTurbineType3or4IECEvent" );
    	this.windTurbineType3or4IECId = event.getWindTurbineType3or4IECId();
    }
    
    @EventSourcingHandler
    void on(UpdateWindTurbineType3or4IECEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.windProtectionIEC = event.getWindProtectionIEC();
        this.windContCurrLimIEC = event.getWindContCurrLimIEC();
        this.wIndContQIEC = event.getWIndContQIEC();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignWindProtectionIECToWindTurbineType3or4IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindProtectionIECToWindTurbineType3or4IECEvent" );
    	this.windProtectionIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindProtectionIECFromWindTurbineType3or4IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindProtectionIECFromWindTurbineType3or4IECEvent" );
		this.windProtectionIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindContCurrLimIECToWindTurbineType3or4IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindContCurrLimIECToWindTurbineType3or4IECEvent" );
    	this.windContCurrLimIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindContCurrLimIECFromWindTurbineType3or4IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindContCurrLimIECFromWindTurbineType3or4IECEvent" );
		this.windContCurrLimIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWIndContQIECToWindTurbineType3or4IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWIndContQIECToWindTurbineType3or4IECEvent" );
    	this.wIndContQIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWIndContQIECFromWindTurbineType3or4IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWIndContQIECFromWindTurbineType3or4IECEvent" );
		this.wIndContQIEC = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windTurbineType3or4IECId;
    
    private WindProtectionIEC windProtectionIEC = null;
    private WindContCurrLimIEC windContCurrLimIEC = null;
    private WindContQIEC wIndContQIEC = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindTurbineType3or4IECAggregate.class.getName());
}
