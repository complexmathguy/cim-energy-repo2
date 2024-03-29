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
 * Aggregate handler for VisibilityLayer as outlined for the CQRS pattern, all write responsibilities 
 * related to VisibilityLayer are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class VisibilityLayerAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public VisibilityLayerAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public VisibilityLayerAggregate(CreateVisibilityLayerCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateVisibilityLayerCommand" );
    	CreateVisibilityLayerEvent event = new CreateVisibilityLayerEvent(command.getVisibilityLayerId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateVisibilityLayerCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateVisibilityLayerCommand" );
    	UpdateVisibilityLayerEvent event = new UpdateVisibilityLayerEvent(command.getVisibilityLayerId(), command.getDrawingOrder(), command.getVisibleObjects());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteVisibilityLayerCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteVisibilityLayerCommand" );
        apply(new DeleteVisibilityLayerEvent(command.getVisibilityLayerId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignDrawingOrderToVisibilityLayerCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDrawingOrderToVisibilityLayerCommand" );
    	
    	if (  drawingOrder != null && drawingOrder.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "DrawingOrder already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignDrawingOrderToVisibilityLayerEvent(command.getVisibilityLayerId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDrawingOrderFromVisibilityLayerCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDrawingOrderFromVisibilityLayerCommand" );

    	if (  drawingOrder == null )
    		throw new ProcessingException( "DrawingOrder already has nothing assigned." );  

    	apply(new UnAssignDrawingOrderFromVisibilityLayerEvent(command.getVisibilityLayerId()));
    }
    @CommandHandler
    public void handle(AssignVisibleObjectsToVisibilityLayerCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignVisibleObjectsToVisibilityLayerCommand" );
    	
    	if (  visibleObjects != null && visibleObjects.getDiagramObjectId() == command.getAssignment().getDiagramObjectId() )
    		throw new ProcessingException( "VisibleObjects already assigned with id " + command.getAssignment().getDiagramObjectId() );  
    		
        apply(new AssignVisibleObjectsToVisibilityLayerEvent(command.getVisibilityLayerId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignVisibleObjectsFromVisibilityLayerCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignVisibleObjectsFromVisibilityLayerCommand" );

    	if (  visibleObjects == null )
    		throw new ProcessingException( "VisibleObjects already has nothing assigned." );  

    	apply(new UnAssignVisibleObjectsFromVisibilityLayerEvent(command.getVisibilityLayerId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateVisibilityLayerEvent event) {	
    	LOGGER.info( "Event sourcing CreateVisibilityLayerEvent" );
    	this.visibilityLayerId = event.getVisibilityLayerId();
    }
    
    @EventSourcingHandler
    void on(UpdateVisibilityLayerEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.drawingOrder = event.getDrawingOrder();
        this.visibleObjects = event.getVisibleObjects();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignDrawingOrderToVisibilityLayerEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDrawingOrderToVisibilityLayerEvent" );
    	this.drawingOrder = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDrawingOrderFromVisibilityLayerEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDrawingOrderFromVisibilityLayerEvent" );
		this.drawingOrder = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignVisibleObjectsToVisibilityLayerEvent event ) {	
    	LOGGER.info( "Event sourcing AssignVisibleObjectsToVisibilityLayerEvent" );
    	this.visibleObjects = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignVisibleObjectsFromVisibilityLayerEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignVisibleObjectsFromVisibilityLayerEvent" );
		this.visibleObjects = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID visibilityLayerId;
    
    private IntegerProxy drawingOrder = null;
    private DiagramObject visibleObjects = null;

    private static final Logger LOGGER 	= Logger.getLogger(VisibilityLayerAggregate.class.getName());
}
