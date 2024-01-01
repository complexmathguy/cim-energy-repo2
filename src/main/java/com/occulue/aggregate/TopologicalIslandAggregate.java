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
 * Aggregate handler for TopologicalIsland as outlined for the CQRS pattern, all write responsibilities 
 * related to TopologicalIsland are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class TopologicalIslandAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public TopologicalIslandAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public TopologicalIslandAggregate(CreateTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateTopologicalIslandCommand" );
    	CreateTopologicalIslandEvent event = new CreateTopologicalIslandEvent(command.getTopologicalIslandId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateTopologicalIslandCommand" );
    	UpdateTopologicalIslandEvent event = new UpdateTopologicalIslandEvent(command.getTopologicalIslandId(), command.getTopologicalNodes(), command.getAngleRefTopologicalNode());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteTopologicalIslandCommand" );
        apply(new DeleteTopologicalIslandEvent(command.getTopologicalIslandId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignTopologicalNodesToTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTopologicalNodesToTopologicalIslandCommand" );
    	
    	if (  topologicalNodes != null && topologicalNodes.getTopologicalNodeId() == command.getAssignment().getTopologicalNodeId() )
    		throw new ProcessingException( "TopologicalNodes already assigned with id " + command.getAssignment().getTopologicalNodeId() );  
    		
        apply(new AssignTopologicalNodesToTopologicalIslandEvent(command.getTopologicalIslandId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTopologicalNodesFromTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTopologicalNodesFromTopologicalIslandCommand" );

    	if (  topologicalNodes == null )
    		throw new ProcessingException( "TopologicalNodes already has nothing assigned." );  

    	apply(new UnAssignTopologicalNodesFromTopologicalIslandEvent(command.getTopologicalIslandId()));
    }
    @CommandHandler
    public void handle(AssignAngleRefTopologicalNodeToTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignAngleRefTopologicalNodeToTopologicalIslandCommand" );
    	
    	if (  angleRefTopologicalNode != null && angleRefTopologicalNode.getTopologicalNodeId() == command.getAssignment().getTopologicalNodeId() )
    		throw new ProcessingException( "AngleRefTopologicalNode already assigned with id " + command.getAssignment().getTopologicalNodeId() );  
    		
        apply(new AssignAngleRefTopologicalNodeToTopologicalIslandEvent(command.getTopologicalIslandId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignAngleRefTopologicalNodeFromTopologicalIslandCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignAngleRefTopologicalNodeFromTopologicalIslandCommand" );

    	if (  angleRefTopologicalNode == null )
    		throw new ProcessingException( "AngleRefTopologicalNode already has nothing assigned." );  

    	apply(new UnAssignAngleRefTopologicalNodeFromTopologicalIslandEvent(command.getTopologicalIslandId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateTopologicalIslandEvent event) {	
    	LOGGER.info( "Event sourcing CreateTopologicalIslandEvent" );
    	this.topologicalIslandId = event.getTopologicalIslandId();
    }
    
    @EventSourcingHandler
    void on(UpdateTopologicalIslandEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.topologicalNodes = event.getTopologicalNodes();
        this.angleRefTopologicalNode = event.getAngleRefTopologicalNode();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignTopologicalNodesToTopologicalIslandEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTopologicalNodesToTopologicalIslandEvent" );
    	this.topologicalNodes = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTopologicalNodesFromTopologicalIslandEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTopologicalNodesFromTopologicalIslandEvent" );
		this.topologicalNodes = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignAngleRefTopologicalNodeToTopologicalIslandEvent event ) {	
    	LOGGER.info( "Event sourcing AssignAngleRefTopologicalNodeToTopologicalIslandEvent" );
    	this.angleRefTopologicalNode = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignAngleRefTopologicalNodeFromTopologicalIslandEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignAngleRefTopologicalNodeFromTopologicalIslandEvent" );
		this.angleRefTopologicalNode = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID topologicalIslandId;
    
    private TopologicalNode topologicalNodes = null;
    private TopologicalNode angleRefTopologicalNode = null;

    private static final Logger LOGGER 	= Logger.getLogger(TopologicalIslandAggregate.class.getName());
}
