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
 * Aggregate handler for VoltageLevel as outlined for the CQRS pattern, all write responsibilities 
 * related to VoltageLevel are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class VoltageLevelAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public VoltageLevelAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public VoltageLevelAggregate(CreateVoltageLevelCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateVoltageLevelCommand" );
    	CreateVoltageLevelEvent event = new CreateVoltageLevelEvent(command.getVoltageLevelId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateVoltageLevelCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateVoltageLevelCommand" );
    	UpdateVoltageLevelEvent event = new UpdateVoltageLevelEvent(command.getVoltageLevelId(), command.getHighVoltageLimit(), command.getLowVoltageLimit(), command.getBaseVoltage(), command.getSubstation());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteVoltageLevelCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteVoltageLevelCommand" );
        apply(new DeleteVoltageLevelEvent(command.getVoltageLevelId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignHighVoltageLimitToVoltageLevelCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignHighVoltageLimitToVoltageLevelCommand" );
    	
    	if (  highVoltageLimit != null && highVoltageLimit.getVoltageId() == command.getAssignment().getVoltageId() )
    		throw new ProcessingException( "HighVoltageLimit already assigned with id " + command.getAssignment().getVoltageId() );  
    		
        apply(new AssignHighVoltageLimitToVoltageLevelEvent(command.getVoltageLevelId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignHighVoltageLimitFromVoltageLevelCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignHighVoltageLimitFromVoltageLevelCommand" );

    	if (  highVoltageLimit == null )
    		throw new ProcessingException( "HighVoltageLimit already has nothing assigned." );  

    	apply(new UnAssignHighVoltageLimitFromVoltageLevelEvent(command.getVoltageLevelId()));
    }
    @CommandHandler
    public void handle(AssignLowVoltageLimitToVoltageLevelCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignLowVoltageLimitToVoltageLevelCommand" );
    	
    	if (  lowVoltageLimit != null && lowVoltageLimit.getVoltageId() == command.getAssignment().getVoltageId() )
    		throw new ProcessingException( "LowVoltageLimit already assigned with id " + command.getAssignment().getVoltageId() );  
    		
        apply(new AssignLowVoltageLimitToVoltageLevelEvent(command.getVoltageLevelId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignLowVoltageLimitFromVoltageLevelCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignLowVoltageLimitFromVoltageLevelCommand" );

    	if (  lowVoltageLimit == null )
    		throw new ProcessingException( "LowVoltageLimit already has nothing assigned." );  

    	apply(new UnAssignLowVoltageLimitFromVoltageLevelEvent(command.getVoltageLevelId()));
    }
    @CommandHandler
    public void handle(AssignBaseVoltageToVoltageLevelCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignBaseVoltageToVoltageLevelCommand" );
    	
    	if (  baseVoltage != null && baseVoltage.getBaseVoltageId() == command.getAssignment().getBaseVoltageId() )
    		throw new ProcessingException( "BaseVoltage already assigned with id " + command.getAssignment().getBaseVoltageId() );  
    		
        apply(new AssignBaseVoltageToVoltageLevelEvent(command.getVoltageLevelId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignBaseVoltageFromVoltageLevelCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignBaseVoltageFromVoltageLevelCommand" );

    	if (  baseVoltage == null )
    		throw new ProcessingException( "BaseVoltage already has nothing assigned." );  

    	apply(new UnAssignBaseVoltageFromVoltageLevelEvent(command.getVoltageLevelId()));
    }
    @CommandHandler
    public void handle(AssignSubstationToVoltageLevelCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSubstationToVoltageLevelCommand" );
    	
    	if (  substation != null && substation.getSubstationId() == command.getAssignment().getSubstationId() )
    		throw new ProcessingException( "Substation already assigned with id " + command.getAssignment().getSubstationId() );  
    		
        apply(new AssignSubstationToVoltageLevelEvent(command.getVoltageLevelId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSubstationFromVoltageLevelCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSubstationFromVoltageLevelCommand" );

    	if (  substation == null )
    		throw new ProcessingException( "Substation already has nothing assigned." );  

    	apply(new UnAssignSubstationFromVoltageLevelEvent(command.getVoltageLevelId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateVoltageLevelEvent event) {	
    	LOGGER.info( "Event sourcing CreateVoltageLevelEvent" );
    	this.voltageLevelId = event.getVoltageLevelId();
    }
    
    @EventSourcingHandler
    void on(UpdateVoltageLevelEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.highVoltageLimit = event.getHighVoltageLimit();
        this.lowVoltageLimit = event.getLowVoltageLimit();
        this.baseVoltage = event.getBaseVoltage();
        this.substation = event.getSubstation();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignHighVoltageLimitToVoltageLevelEvent event ) {	
    	LOGGER.info( "Event sourcing AssignHighVoltageLimitToVoltageLevelEvent" );
    	this.highVoltageLimit = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignHighVoltageLimitFromVoltageLevelEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignHighVoltageLimitFromVoltageLevelEvent" );
		this.highVoltageLimit = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignLowVoltageLimitToVoltageLevelEvent event ) {	
    	LOGGER.info( "Event sourcing AssignLowVoltageLimitToVoltageLevelEvent" );
    	this.lowVoltageLimit = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignLowVoltageLimitFromVoltageLevelEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignLowVoltageLimitFromVoltageLevelEvent" );
		this.lowVoltageLimit = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignBaseVoltageToVoltageLevelEvent event ) {	
    	LOGGER.info( "Event sourcing AssignBaseVoltageToVoltageLevelEvent" );
    	this.baseVoltage = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignBaseVoltageFromVoltageLevelEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignBaseVoltageFromVoltageLevelEvent" );
		this.baseVoltage = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignSubstationToVoltageLevelEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSubstationToVoltageLevelEvent" );
    	this.substation = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSubstationFromVoltageLevelEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSubstationFromVoltageLevelEvent" );
		this.substation = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID voltageLevelId;
    
    private Bay voltageLevel = null;
    private Voltage highVoltageLimit = null;
    private Voltage lowVoltageLimit = null;
    private BaseVoltage baseVoltage = null;
    private Substation substation = null;

    private static final Logger LOGGER 	= Logger.getLogger(VoltageLevelAggregate.class.getName());
}
