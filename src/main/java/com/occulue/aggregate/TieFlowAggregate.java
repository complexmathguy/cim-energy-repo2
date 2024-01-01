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
 * Aggregate handler for TieFlow as outlined for the CQRS pattern, all write responsibilities 
 * related to TieFlow are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class TieFlowAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public TieFlowAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public TieFlowAggregate(CreateTieFlowCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateTieFlowCommand" );
    	CreateTieFlowEvent event = new CreateTieFlowEvent(command.getTieFlowId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateTieFlowCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateTieFlowCommand" );
    	UpdateTieFlowEvent event = new UpdateTieFlowEvent(command.getTieFlowId(), command.getPositiveFlowIn(), command.getControlArea(), command.getTerminal());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteTieFlowCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteTieFlowCommand" );
        apply(new DeleteTieFlowEvent(command.getTieFlowId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignPositiveFlowInToTieFlowCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPositiveFlowInToTieFlowCommand" );
    	
    	if (  positiveFlowIn != null && positiveFlowIn.getBooleanProxyId() == command.getAssignment().getBooleanProxyId() )
    		throw new ProcessingException( "PositiveFlowIn already assigned with id " + command.getAssignment().getBooleanProxyId() );  
    		
        apply(new AssignPositiveFlowInToTieFlowEvent(command.getTieFlowId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPositiveFlowInFromTieFlowCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPositiveFlowInFromTieFlowCommand" );

    	if (  positiveFlowIn == null )
    		throw new ProcessingException( "PositiveFlowIn already has nothing assigned." );  

    	apply(new UnAssignPositiveFlowInFromTieFlowEvent(command.getTieFlowId()));
    }
    @CommandHandler
    public void handle(AssignControlAreaToTieFlowCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignControlAreaToTieFlowCommand" );
    	
    	if (  controlArea != null && controlArea.getControlAreaId() == command.getAssignment().getControlAreaId() )
    		throw new ProcessingException( "ControlArea already assigned with id " + command.getAssignment().getControlAreaId() );  
    		
        apply(new AssignControlAreaToTieFlowEvent(command.getTieFlowId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignControlAreaFromTieFlowCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignControlAreaFromTieFlowCommand" );

    	if (  controlArea == null )
    		throw new ProcessingException( "ControlArea already has nothing assigned." );  

    	apply(new UnAssignControlAreaFromTieFlowEvent(command.getTieFlowId()));
    }
    @CommandHandler
    public void handle(AssignTerminalToTieFlowCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTerminalToTieFlowCommand" );
    	
    	if (  terminal != null && terminal.getTerminalId() == command.getAssignment().getTerminalId() )
    		throw new ProcessingException( "Terminal already assigned with id " + command.getAssignment().getTerminalId() );  
    		
        apply(new AssignTerminalToTieFlowEvent(command.getTieFlowId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTerminalFromTieFlowCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTerminalFromTieFlowCommand" );

    	if (  terminal == null )
    		throw new ProcessingException( "Terminal already has nothing assigned." );  

    	apply(new UnAssignTerminalFromTieFlowEvent(command.getTieFlowId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateTieFlowEvent event) {	
    	LOGGER.info( "Event sourcing CreateTieFlowEvent" );
    	this.tieFlowId = event.getTieFlowId();
    }
    
    @EventSourcingHandler
    void on(UpdateTieFlowEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.positiveFlowIn = event.getPositiveFlowIn();
        this.controlArea = event.getControlArea();
        this.terminal = event.getTerminal();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignPositiveFlowInToTieFlowEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPositiveFlowInToTieFlowEvent" );
    	this.positiveFlowIn = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPositiveFlowInFromTieFlowEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPositiveFlowInFromTieFlowEvent" );
		this.positiveFlowIn = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignControlAreaToTieFlowEvent event ) {	
    	LOGGER.info( "Event sourcing AssignControlAreaToTieFlowEvent" );
    	this.controlArea = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignControlAreaFromTieFlowEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignControlAreaFromTieFlowEvent" );
		this.controlArea = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTerminalToTieFlowEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTerminalToTieFlowEvent" );
    	this.terminal = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTerminalFromTieFlowEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTerminalFromTieFlowEvent" );
		this.terminal = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID tieFlowId;
    
    private BooleanProxy positiveFlowIn = null;
    private ControlArea controlArea = null;
    private Terminal terminal = null;

    private static final Logger LOGGER 	= Logger.getLogger(TieFlowAggregate.class.getName());
}
