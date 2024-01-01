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
 * Aggregate handler for TransformerEnd as outlined for the CQRS pattern, all write responsibilities 
 * related to TransformerEnd are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class TransformerEndAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public TransformerEndAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public TransformerEndAggregate(CreateTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateTransformerEndCommand" );
    	CreateTransformerEndEvent event = new CreateTransformerEndEvent(command.getTransformerEndId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateTransformerEndCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateTransformerEndCommand" );
    	UpdateTransformerEndEvent event = new UpdateTransformerEndEvent(command.getTransformerEndId(), command.getEndNumber(), command.getGrounded(), command.getRground(), command.getXground(), command.getBaseVoltage(), command.getTerminal());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteTransformerEndCommand" );
        apply(new DeleteTransformerEndEvent(command.getTransformerEndId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignEndNumberToTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignEndNumberToTransformerEndCommand" );
    	
    	if (  endNumber != null && endNumber.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "EndNumber already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignEndNumberToTransformerEndEvent(command.getTransformerEndId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignEndNumberFromTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignEndNumberFromTransformerEndCommand" );

    	if (  endNumber == null )
    		throw new ProcessingException( "EndNumber already has nothing assigned." );  

    	apply(new UnAssignEndNumberFromTransformerEndEvent(command.getTransformerEndId()));
    }
    @CommandHandler
    public void handle(AssignGroundedToTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignGroundedToTransformerEndCommand" );
    	
    	if (  grounded != null && grounded.getBooleanProxyId() == command.getAssignment().getBooleanProxyId() )
    		throw new ProcessingException( "Grounded already assigned with id " + command.getAssignment().getBooleanProxyId() );  
    		
        apply(new AssignGroundedToTransformerEndEvent(command.getTransformerEndId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignGroundedFromTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignGroundedFromTransformerEndCommand" );

    	if (  grounded == null )
    		throw new ProcessingException( "Grounded already has nothing assigned." );  

    	apply(new UnAssignGroundedFromTransformerEndEvent(command.getTransformerEndId()));
    }
    @CommandHandler
    public void handle(AssignRgroundToTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRgroundToTransformerEndCommand" );
    	
    	if (  rground != null && rground.getResistanceId() == command.getAssignment().getResistanceId() )
    		throw new ProcessingException( "Rground already assigned with id " + command.getAssignment().getResistanceId() );  
    		
        apply(new AssignRgroundToTransformerEndEvent(command.getTransformerEndId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRgroundFromTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRgroundFromTransformerEndCommand" );

    	if (  rground == null )
    		throw new ProcessingException( "Rground already has nothing assigned." );  

    	apply(new UnAssignRgroundFromTransformerEndEvent(command.getTransformerEndId()));
    }
    @CommandHandler
    public void handle(AssignXgroundToTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignXgroundToTransformerEndCommand" );
    	
    	if (  xground != null && xground.getReactanceId() == command.getAssignment().getReactanceId() )
    		throw new ProcessingException( "Xground already assigned with id " + command.getAssignment().getReactanceId() );  
    		
        apply(new AssignXgroundToTransformerEndEvent(command.getTransformerEndId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignXgroundFromTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignXgroundFromTransformerEndCommand" );

    	if (  xground == null )
    		throw new ProcessingException( "Xground already has nothing assigned." );  

    	apply(new UnAssignXgroundFromTransformerEndEvent(command.getTransformerEndId()));
    }
    @CommandHandler
    public void handle(AssignBaseVoltageToTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignBaseVoltageToTransformerEndCommand" );
    	
    	if (  baseVoltage != null && baseVoltage.getBaseVoltageId() == command.getAssignment().getBaseVoltageId() )
    		throw new ProcessingException( "BaseVoltage already assigned with id " + command.getAssignment().getBaseVoltageId() );  
    		
        apply(new AssignBaseVoltageToTransformerEndEvent(command.getTransformerEndId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignBaseVoltageFromTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignBaseVoltageFromTransformerEndCommand" );

    	if (  baseVoltage == null )
    		throw new ProcessingException( "BaseVoltage already has nothing assigned." );  

    	apply(new UnAssignBaseVoltageFromTransformerEndEvent(command.getTransformerEndId()));
    }
    @CommandHandler
    public void handle(AssignTerminalToTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTerminalToTransformerEndCommand" );
    	
    	if (  terminal != null && terminal.getTerminalId() == command.getAssignment().getTerminalId() )
    		throw new ProcessingException( "Terminal already assigned with id " + command.getAssignment().getTerminalId() );  
    		
        apply(new AssignTerminalToTransformerEndEvent(command.getTransformerEndId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTerminalFromTransformerEndCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTerminalFromTransformerEndCommand" );

    	if (  terminal == null )
    		throw new ProcessingException( "Terminal already has nothing assigned." );  

    	apply(new UnAssignTerminalFromTransformerEndEvent(command.getTransformerEndId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateTransformerEndEvent event) {	
    	LOGGER.info( "Event sourcing CreateTransformerEndEvent" );
    	this.transformerEndId = event.getTransformerEndId();
    }
    
    @EventSourcingHandler
    void on(UpdateTransformerEndEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.endNumber = event.getEndNumber();
        this.grounded = event.getGrounded();
        this.rground = event.getRground();
        this.xground = event.getXground();
        this.baseVoltage = event.getBaseVoltage();
        this.terminal = event.getTerminal();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignEndNumberToTransformerEndEvent event ) {	
    	LOGGER.info( "Event sourcing AssignEndNumberToTransformerEndEvent" );
    	this.endNumber = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignEndNumberFromTransformerEndEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignEndNumberFromTransformerEndEvent" );
		this.endNumber = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignGroundedToTransformerEndEvent event ) {	
    	LOGGER.info( "Event sourcing AssignGroundedToTransformerEndEvent" );
    	this.grounded = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignGroundedFromTransformerEndEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignGroundedFromTransformerEndEvent" );
		this.grounded = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignRgroundToTransformerEndEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRgroundToTransformerEndEvent" );
    	this.rground = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRgroundFromTransformerEndEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRgroundFromTransformerEndEvent" );
		this.rground = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignXgroundToTransformerEndEvent event ) {	
    	LOGGER.info( "Event sourcing AssignXgroundToTransformerEndEvent" );
    	this.xground = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignXgroundFromTransformerEndEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignXgroundFromTransformerEndEvent" );
		this.xground = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignBaseVoltageToTransformerEndEvent event ) {	
    	LOGGER.info( "Event sourcing AssignBaseVoltageToTransformerEndEvent" );
    	this.baseVoltage = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignBaseVoltageFromTransformerEndEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignBaseVoltageFromTransformerEndEvent" );
		this.baseVoltage = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTerminalToTransformerEndEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTerminalToTransformerEndEvent" );
    	this.terminal = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTerminalFromTransformerEndEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTerminalFromTransformerEndEvent" );
		this.terminal = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID transformerEndId;
    
    private PhaseTapChanger transformerEnd = null;
    private IntegerProxy endNumber = null;
    private BooleanProxy grounded = null;
    private Resistance rground = null;
    private Reactance xground = null;
    private BaseVoltage baseVoltage = null;
    private Terminal terminal = null;

    private static final Logger LOGGER 	= Logger.getLogger(TransformerEndAggregate.class.getName());
}
