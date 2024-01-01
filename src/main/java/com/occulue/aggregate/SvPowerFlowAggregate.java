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
 * Aggregate handler for SvPowerFlow as outlined for the CQRS pattern, all write responsibilities 
 * related to SvPowerFlow are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class SvPowerFlowAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public SvPowerFlowAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public SvPowerFlowAggregate(CreateSvPowerFlowCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateSvPowerFlowCommand" );
    	CreateSvPowerFlowEvent event = new CreateSvPowerFlowEvent(command.getSvPowerFlowId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateSvPowerFlowCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateSvPowerFlowCommand" );
    	UpdateSvPowerFlowEvent event = new UpdateSvPowerFlowEvent(command.getSvPowerFlowId(), command.getP(), command.getQ(), command.getTerminal());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteSvPowerFlowCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteSvPowerFlowCommand" );
        apply(new DeleteSvPowerFlowEvent(command.getSvPowerFlowId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignPToSvPowerFlowCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPToSvPowerFlowCommand" );
    	
    	if (  p != null && p.getActivePowerId() == command.getAssignment().getActivePowerId() )
    		throw new ProcessingException( "P already assigned with id " + command.getAssignment().getActivePowerId() );  
    		
        apply(new AssignPToSvPowerFlowEvent(command.getSvPowerFlowId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPFromSvPowerFlowCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPFromSvPowerFlowCommand" );

    	if (  p == null )
    		throw new ProcessingException( "P already has nothing assigned." );  

    	apply(new UnAssignPFromSvPowerFlowEvent(command.getSvPowerFlowId()));
    }
    @CommandHandler
    public void handle(AssignQToSvPowerFlowCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignQToSvPowerFlowCommand" );
    	
    	if (  q != null && q.getReactivePowerId() == command.getAssignment().getReactivePowerId() )
    		throw new ProcessingException( "Q already assigned with id " + command.getAssignment().getReactivePowerId() );  
    		
        apply(new AssignQToSvPowerFlowEvent(command.getSvPowerFlowId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignQFromSvPowerFlowCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignQFromSvPowerFlowCommand" );

    	if (  q == null )
    		throw new ProcessingException( "Q already has nothing assigned." );  

    	apply(new UnAssignQFromSvPowerFlowEvent(command.getSvPowerFlowId()));
    }
    @CommandHandler
    public void handle(AssignTerminalToSvPowerFlowCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTerminalToSvPowerFlowCommand" );
    	
    	if (  terminal != null && terminal.getTerminalId() == command.getAssignment().getTerminalId() )
    		throw new ProcessingException( "Terminal already assigned with id " + command.getAssignment().getTerminalId() );  
    		
        apply(new AssignTerminalToSvPowerFlowEvent(command.getSvPowerFlowId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTerminalFromSvPowerFlowCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTerminalFromSvPowerFlowCommand" );

    	if (  terminal == null )
    		throw new ProcessingException( "Terminal already has nothing assigned." );  

    	apply(new UnAssignTerminalFromSvPowerFlowEvent(command.getSvPowerFlowId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateSvPowerFlowEvent event) {	
    	LOGGER.info( "Event sourcing CreateSvPowerFlowEvent" );
    	this.svPowerFlowId = event.getSvPowerFlowId();
    }
    
    @EventSourcingHandler
    void on(UpdateSvPowerFlowEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.p = event.getP();
        this.q = event.getQ();
        this.terminal = event.getTerminal();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignPToSvPowerFlowEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPToSvPowerFlowEvent" );
    	this.p = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPFromSvPowerFlowEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPFromSvPowerFlowEvent" );
		this.p = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignQToSvPowerFlowEvent event ) {	
    	LOGGER.info( "Event sourcing AssignQToSvPowerFlowEvent" );
    	this.q = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignQFromSvPowerFlowEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignQFromSvPowerFlowEvent" );
		this.q = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTerminalToSvPowerFlowEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTerminalToSvPowerFlowEvent" );
    	this.terminal = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTerminalFromSvPowerFlowEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTerminalFromSvPowerFlowEvent" );
		this.terminal = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID svPowerFlowId;
    
    private ActivePower p = null;
    private ReactivePower q = null;
    private Terminal terminal = null;

    private static final Logger LOGGER 	= Logger.getLogger(SvPowerFlowAggregate.class.getName());
}
