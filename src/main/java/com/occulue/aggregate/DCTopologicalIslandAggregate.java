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
 * Aggregate handler for DCTopologicalIsland as outlined for the CQRS pattern, all write responsibilities 
 * related to DCTopologicalIsland are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class DCTopologicalIslandAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public DCTopologicalIslandAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public DCTopologicalIslandAggregate(CreateDCTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateDCTopologicalIslandCommand" );
    	CreateDCTopologicalIslandEvent event = new CreateDCTopologicalIslandEvent(command.getDCTopologicalIslandId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateDCTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateDCTopologicalIslandCommand" );
    	UpdateDCTopologicalIslandEvent event = new UpdateDCTopologicalIslandEvent(command.getDCTopologicalIslandId(), command.getDCTopologicalNodes());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteDCTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteDCTopologicalIslandCommand" );
        apply(new DeleteDCTopologicalIslandEvent(command.getDCTopologicalIslandId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignDCTopologicalNodesToDCTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDCTopologicalNodesToDCTopologicalIslandCommand" );
    	
    	if (  dCTopologicalNodes != null && dCTopologicalNodes.getDCTopologicalNodeId() == command.getAssignment().getDCTopologicalNodeId() )
    		throw new ProcessingException( "DCTopologicalNodes already assigned with id " + command.getAssignment().getDCTopologicalNodeId() );  
    		
        apply(new AssignDCTopologicalNodesToDCTopologicalIslandEvent(command.getDCTopologicalIslandId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDCTopologicalNodesFromDCTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDCTopologicalNodesFromDCTopologicalIslandCommand" );

    	if (  dCTopologicalNodes == null )
    		throw new ProcessingException( "DCTopologicalNodes already has nothing assigned." );  

    	apply(new UnAssignDCTopologicalNodesFromDCTopologicalIslandEvent(command.getDCTopologicalIslandId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateDCTopologicalIslandEvent event) {	
    	LOGGER.info( "Event sourcing CreateDCTopologicalIslandEvent" );
    	this.dCTopologicalIslandId = event.getDCTopologicalIslandId();
    }
    
    @EventSourcingHandler
    void on(UpdateDCTopologicalIslandEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.dCTopologicalNodes = event.getDCTopologicalNodes();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignDCTopologicalNodesToDCTopologicalIslandEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDCTopologicalNodesToDCTopologicalIslandEvent" );
    	this.dCTopologicalNodes = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDCTopologicalNodesFromDCTopologicalIslandEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDCTopologicalNodesFromDCTopologicalIslandEvent" );
		this.dCTopologicalNodes = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID dCTopologicalIslandId;
    
    private DCTopologicalNode dCTopologicalNodes = null;

    private static final Logger LOGGER 	= Logger.getLogger(DCTopologicalIslandAggregate.class.getName());
}
