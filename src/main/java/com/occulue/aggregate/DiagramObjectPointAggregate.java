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
 * Aggregate handler for DiagramObjectPoint as outlined for the CQRS pattern, all write responsibilities 
 * related to DiagramObjectPoint are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class DiagramObjectPointAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public DiagramObjectPointAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public DiagramObjectPointAggregate(CreateDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateDiagramObjectPointCommand" );
    	CreateDiagramObjectPointEvent event = new CreateDiagramObjectPointEvent(command.getDiagramObjectPointId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateDiagramObjectPointCommand" );
    	UpdateDiagramObjectPointEvent event = new UpdateDiagramObjectPointEvent(command.getDiagramObjectPointId(), command.getSequenceNumber(), command.getXPosition(), command.getYPosition(), command.getZPosition(), command.getDiagramObject(), command.getDiagramObjectGluePoint());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteDiagramObjectPointCommand" );
        apply(new DeleteDiagramObjectPointEvent(command.getDiagramObjectPointId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignSequenceNumberToDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSequenceNumberToDiagramObjectPointCommand" );
    	
    	if (  sequenceNumber != null && sequenceNumber.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "SequenceNumber already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignSequenceNumberToDiagramObjectPointEvent(command.getDiagramObjectPointId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSequenceNumberFromDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSequenceNumberFromDiagramObjectPointCommand" );

    	if (  sequenceNumber == null )
    		throw new ProcessingException( "SequenceNumber already has nothing assigned." );  

    	apply(new UnAssignSequenceNumberFromDiagramObjectPointEvent(command.getDiagramObjectPointId()));
    }
    @CommandHandler
    public void handle(AssignXPositionToDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignXPositionToDiagramObjectPointCommand" );
    	
    	if (  xPosition != null && xPosition.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "XPosition already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignXPositionToDiagramObjectPointEvent(command.getDiagramObjectPointId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignXPositionFromDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignXPositionFromDiagramObjectPointCommand" );

    	if (  xPosition == null )
    		throw new ProcessingException( "XPosition already has nothing assigned." );  

    	apply(new UnAssignXPositionFromDiagramObjectPointEvent(command.getDiagramObjectPointId()));
    }
    @CommandHandler
    public void handle(AssignYPositionToDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignYPositionToDiagramObjectPointCommand" );
    	
    	if (  yPosition != null && yPosition.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "YPosition already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignYPositionToDiagramObjectPointEvent(command.getDiagramObjectPointId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignYPositionFromDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignYPositionFromDiagramObjectPointCommand" );

    	if (  yPosition == null )
    		throw new ProcessingException( "YPosition already has nothing assigned." );  

    	apply(new UnAssignYPositionFromDiagramObjectPointEvent(command.getDiagramObjectPointId()));
    }
    @CommandHandler
    public void handle(AssignZPositionToDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignZPositionToDiagramObjectPointCommand" );
    	
    	if (  zPosition != null && zPosition.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "ZPosition already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignZPositionToDiagramObjectPointEvent(command.getDiagramObjectPointId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignZPositionFromDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignZPositionFromDiagramObjectPointCommand" );

    	if (  zPosition == null )
    		throw new ProcessingException( "ZPosition already has nothing assigned." );  

    	apply(new UnAssignZPositionFromDiagramObjectPointEvent(command.getDiagramObjectPointId()));
    }
    @CommandHandler
    public void handle(AssignDiagramObjectToDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDiagramObjectToDiagramObjectPointCommand" );
    	
    	if (  diagramObject != null && diagramObject.getDiagramObjectId() == command.getAssignment().getDiagramObjectId() )
    		throw new ProcessingException( "DiagramObject already assigned with id " + command.getAssignment().getDiagramObjectId() );  
    		
        apply(new AssignDiagramObjectToDiagramObjectPointEvent(command.getDiagramObjectPointId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDiagramObjectFromDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDiagramObjectFromDiagramObjectPointCommand" );

    	if (  diagramObject == null )
    		throw new ProcessingException( "DiagramObject already has nothing assigned." );  

    	apply(new UnAssignDiagramObjectFromDiagramObjectPointEvent(command.getDiagramObjectPointId()));
    }
    @CommandHandler
    public void handle(AssignDiagramObjectGluePointToDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDiagramObjectGluePointToDiagramObjectPointCommand" );
    	
    	if (  diagramObjectGluePoint != null && diagramObjectGluePoint.getDiagramObjectGluePointId() == command.getAssignment().getDiagramObjectGluePointId() )
    		throw new ProcessingException( "DiagramObjectGluePoint already assigned with id " + command.getAssignment().getDiagramObjectGluePointId() );  
    		
        apply(new AssignDiagramObjectGluePointToDiagramObjectPointEvent(command.getDiagramObjectPointId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDiagramObjectGluePointFromDiagramObjectPointCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDiagramObjectGluePointFromDiagramObjectPointCommand" );

    	if (  diagramObjectGluePoint == null )
    		throw new ProcessingException( "DiagramObjectGluePoint already has nothing assigned." );  

    	apply(new UnAssignDiagramObjectGluePointFromDiagramObjectPointEvent(command.getDiagramObjectPointId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateDiagramObjectPointEvent event) {	
    	LOGGER.info( "Event sourcing CreateDiagramObjectPointEvent" );
    	this.diagramObjectPointId = event.getDiagramObjectPointId();
    }
    
    @EventSourcingHandler
    void on(UpdateDiagramObjectPointEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.sequenceNumber = event.getSequenceNumber();
        this.xPosition = event.getXPosition();
        this.yPosition = event.getYPosition();
        this.zPosition = event.getZPosition();
        this.diagramObject = event.getDiagramObject();
        this.diagramObjectGluePoint = event.getDiagramObjectGluePoint();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignSequenceNumberToDiagramObjectPointEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSequenceNumberToDiagramObjectPointEvent" );
    	this.sequenceNumber = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSequenceNumberFromDiagramObjectPointEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSequenceNumberFromDiagramObjectPointEvent" );
		this.sequenceNumber = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignXPositionToDiagramObjectPointEvent event ) {	
    	LOGGER.info( "Event sourcing AssignXPositionToDiagramObjectPointEvent" );
    	this.xPosition = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignXPositionFromDiagramObjectPointEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignXPositionFromDiagramObjectPointEvent" );
		this.xPosition = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignYPositionToDiagramObjectPointEvent event ) {	
    	LOGGER.info( "Event sourcing AssignYPositionToDiagramObjectPointEvent" );
    	this.yPosition = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignYPositionFromDiagramObjectPointEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignYPositionFromDiagramObjectPointEvent" );
		this.yPosition = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignZPositionToDiagramObjectPointEvent event ) {	
    	LOGGER.info( "Event sourcing AssignZPositionToDiagramObjectPointEvent" );
    	this.zPosition = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignZPositionFromDiagramObjectPointEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignZPositionFromDiagramObjectPointEvent" );
		this.zPosition = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignDiagramObjectToDiagramObjectPointEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDiagramObjectToDiagramObjectPointEvent" );
    	this.diagramObject = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDiagramObjectFromDiagramObjectPointEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDiagramObjectFromDiagramObjectPointEvent" );
		this.diagramObject = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignDiagramObjectGluePointToDiagramObjectPointEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDiagramObjectGluePointToDiagramObjectPointEvent" );
    	this.diagramObjectGluePoint = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDiagramObjectGluePointFromDiagramObjectPointEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDiagramObjectGluePointFromDiagramObjectPointEvent" );
		this.diagramObjectGluePoint = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID diagramObjectPointId;
    
    private IntegerProxy sequenceNumber = null;
    private Simple_Float xPosition = null;
    private Simple_Float yPosition = null;
    private Simple_Float zPosition = null;
    private DiagramObject diagramObject = null;
    private DiagramObjectGluePoint diagramObjectGluePoint = null;

    private static final Logger LOGGER 	= Logger.getLogger(DiagramObjectPointAggregate.class.getName());
}
