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
 * Aggregate handler for SvVoltage as outlined for the CQRS pattern, all write responsibilities 
 * related to SvVoltage are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class SvVoltageAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public SvVoltageAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public SvVoltageAggregate(CreateSvVoltageCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateSvVoltageCommand" );
    	CreateSvVoltageEvent event = new CreateSvVoltageEvent(command.getSvVoltageId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateSvVoltageCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateSvVoltageCommand" );
    	UpdateSvVoltageEvent event = new UpdateSvVoltageEvent(command.getSvVoltageId(), command.getAngle(), command.getV(), command.getTopologicalNode());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteSvVoltageCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteSvVoltageCommand" );
        apply(new DeleteSvVoltageEvent(command.getSvVoltageId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignAngleToSvVoltageCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignAngleToSvVoltageCommand" );
    	
    	if (  angle != null && angle.getAngleDegreesId() == command.getAssignment().getAngleDegreesId() )
    		throw new ProcessingException( "Angle already assigned with id " + command.getAssignment().getAngleDegreesId() );  
    		
        apply(new AssignAngleToSvVoltageEvent(command.getSvVoltageId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignAngleFromSvVoltageCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignAngleFromSvVoltageCommand" );

    	if (  angle == null )
    		throw new ProcessingException( "Angle already has nothing assigned." );  

    	apply(new UnAssignAngleFromSvVoltageEvent(command.getSvVoltageId()));
    }
    @CommandHandler
    public void handle(AssignVToSvVoltageCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignVToSvVoltageCommand" );
    	
    	if (  v != null && v.getVoltageId() == command.getAssignment().getVoltageId() )
    		throw new ProcessingException( "V already assigned with id " + command.getAssignment().getVoltageId() );  
    		
        apply(new AssignVToSvVoltageEvent(command.getSvVoltageId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignVFromSvVoltageCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignVFromSvVoltageCommand" );

    	if (  v == null )
    		throw new ProcessingException( "V already has nothing assigned." );  

    	apply(new UnAssignVFromSvVoltageEvent(command.getSvVoltageId()));
    }
    @CommandHandler
    public void handle(AssignTopologicalNodeToSvVoltageCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTopologicalNodeToSvVoltageCommand" );
    	
    	if (  topologicalNode != null && topologicalNode.getTopologicalNodeId() == command.getAssignment().getTopologicalNodeId() )
    		throw new ProcessingException( "TopologicalNode already assigned with id " + command.getAssignment().getTopologicalNodeId() );  
    		
        apply(new AssignTopologicalNodeToSvVoltageEvent(command.getSvVoltageId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTopologicalNodeFromSvVoltageCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTopologicalNodeFromSvVoltageCommand" );

    	if (  topologicalNode == null )
    		throw new ProcessingException( "TopologicalNode already has nothing assigned." );  

    	apply(new UnAssignTopologicalNodeFromSvVoltageEvent(command.getSvVoltageId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateSvVoltageEvent event) {	
    	LOGGER.info( "Event sourcing CreateSvVoltageEvent" );
    	this.svVoltageId = event.getSvVoltageId();
    }
    
    @EventSourcingHandler
    void on(UpdateSvVoltageEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.angle = event.getAngle();
        this.v = event.getV();
        this.topologicalNode = event.getTopologicalNode();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignAngleToSvVoltageEvent event ) {	
    	LOGGER.info( "Event sourcing AssignAngleToSvVoltageEvent" );
    	this.angle = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignAngleFromSvVoltageEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignAngleFromSvVoltageEvent" );
		this.angle = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignVToSvVoltageEvent event ) {	
    	LOGGER.info( "Event sourcing AssignVToSvVoltageEvent" );
    	this.v = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignVFromSvVoltageEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignVFromSvVoltageEvent" );
		this.v = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTopologicalNodeToSvVoltageEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTopologicalNodeToSvVoltageEvent" );
    	this.topologicalNode = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTopologicalNodeFromSvVoltageEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTopologicalNodeFromSvVoltageEvent" );
		this.topologicalNode = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID svVoltageId;
    
    private AngleDegrees angle = null;
    private Voltage v = null;
    private TopologicalNode topologicalNode = null;

    private static final Logger LOGGER 	= Logger.getLogger(SvVoltageAggregate.class.getName());
}
