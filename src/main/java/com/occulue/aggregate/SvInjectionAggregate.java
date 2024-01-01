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
 * Aggregate handler for SvInjection as outlined for the CQRS pattern, all write responsibilities 
 * related to SvInjection are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class SvInjectionAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public SvInjectionAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public SvInjectionAggregate(CreateSvInjectionCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateSvInjectionCommand" );
    	CreateSvInjectionEvent event = new CreateSvInjectionEvent(command.getSvInjectionId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateSvInjectionCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateSvInjectionCommand" );
    	UpdateSvInjectionEvent event = new UpdateSvInjectionEvent(command.getSvInjectionId(), command.getPInjection(), command.getQInjection(), command.getTopologicalNode());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteSvInjectionCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteSvInjectionCommand" );
        apply(new DeleteSvInjectionEvent(command.getSvInjectionId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignPInjectionToSvInjectionCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPInjectionToSvInjectionCommand" );
    	
    	if (  pInjection != null && pInjection.getActivePowerId() == command.getAssignment().getActivePowerId() )
    		throw new ProcessingException( "PInjection already assigned with id " + command.getAssignment().getActivePowerId() );  
    		
        apply(new AssignPInjectionToSvInjectionEvent(command.getSvInjectionId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPInjectionFromSvInjectionCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPInjectionFromSvInjectionCommand" );

    	if (  pInjection == null )
    		throw new ProcessingException( "PInjection already has nothing assigned." );  

    	apply(new UnAssignPInjectionFromSvInjectionEvent(command.getSvInjectionId()));
    }
    @CommandHandler
    public void handle(AssignQInjectionToSvInjectionCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignQInjectionToSvInjectionCommand" );
    	
    	if (  qInjection != null && qInjection.getReactivePowerId() == command.getAssignment().getReactivePowerId() )
    		throw new ProcessingException( "QInjection already assigned with id " + command.getAssignment().getReactivePowerId() );  
    		
        apply(new AssignQInjectionToSvInjectionEvent(command.getSvInjectionId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignQInjectionFromSvInjectionCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignQInjectionFromSvInjectionCommand" );

    	if (  qInjection == null )
    		throw new ProcessingException( "QInjection already has nothing assigned." );  

    	apply(new UnAssignQInjectionFromSvInjectionEvent(command.getSvInjectionId()));
    }
    @CommandHandler
    public void handle(AssignTopologicalNodeToSvInjectionCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTopologicalNodeToSvInjectionCommand" );
    	
    	if (  topologicalNode != null && topologicalNode.getTopologicalNodeId() == command.getAssignment().getTopologicalNodeId() )
    		throw new ProcessingException( "TopologicalNode already assigned with id " + command.getAssignment().getTopologicalNodeId() );  
    		
        apply(new AssignTopologicalNodeToSvInjectionEvent(command.getSvInjectionId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTopologicalNodeFromSvInjectionCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTopologicalNodeFromSvInjectionCommand" );

    	if (  topologicalNode == null )
    		throw new ProcessingException( "TopologicalNode already has nothing assigned." );  

    	apply(new UnAssignTopologicalNodeFromSvInjectionEvent(command.getSvInjectionId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateSvInjectionEvent event) {	
    	LOGGER.info( "Event sourcing CreateSvInjectionEvent" );
    	this.svInjectionId = event.getSvInjectionId();
    }
    
    @EventSourcingHandler
    void on(UpdateSvInjectionEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.pInjection = event.getPInjection();
        this.qInjection = event.getQInjection();
        this.topologicalNode = event.getTopologicalNode();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignPInjectionToSvInjectionEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPInjectionToSvInjectionEvent" );
    	this.pInjection = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPInjectionFromSvInjectionEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPInjectionFromSvInjectionEvent" );
		this.pInjection = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignQInjectionToSvInjectionEvent event ) {	
    	LOGGER.info( "Event sourcing AssignQInjectionToSvInjectionEvent" );
    	this.qInjection = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignQInjectionFromSvInjectionEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignQInjectionFromSvInjectionEvent" );
		this.qInjection = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTopologicalNodeToSvInjectionEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTopologicalNodeToSvInjectionEvent" );
    	this.topologicalNode = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTopologicalNodeFromSvInjectionEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTopologicalNodeFromSvInjectionEvent" );
		this.topologicalNode = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID svInjectionId;
    
    private ActivePower pInjection = null;
    private ReactivePower qInjection = null;
    private TopologicalNode topologicalNode = null;

    private static final Logger LOGGER 	= Logger.getLogger(SvInjectionAggregate.class.getName());
}
