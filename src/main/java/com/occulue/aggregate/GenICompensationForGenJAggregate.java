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
 * Aggregate handler for GenICompensationForGenJ as outlined for the CQRS pattern, all write responsibilities 
 * related to GenICompensationForGenJ are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class GenICompensationForGenJAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public GenICompensationForGenJAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public GenICompensationForGenJAggregate(CreateGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateGenICompensationForGenJCommand" );
    	CreateGenICompensationForGenJEvent event = new CreateGenICompensationForGenJEvent(command.getGenICompensationForGenJId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateGenICompensationForGenJCommand" );
    	UpdateGenICompensationForGenJEvent event = new UpdateGenICompensationForGenJEvent(command.getGenICompensationForGenJId(), command.getRcij(), command.getXcij(), command.getSynchronousMachineDynamics(), command.getVcompIEEEType2());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteGenICompensationForGenJCommand" );
        apply(new DeleteGenICompensationForGenJEvent(command.getGenICompensationForGenJId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignRcijToGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRcijToGenICompensationForGenJCommand" );
    	
    	if (  rcij != null && rcij.getPUId() == command.getAssignment().getPUId() )
    		throw new ProcessingException( "Rcij already assigned with id " + command.getAssignment().getPUId() );  
    		
        apply(new AssignRcijToGenICompensationForGenJEvent(command.getGenICompensationForGenJId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRcijFromGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRcijFromGenICompensationForGenJCommand" );

    	if (  rcij == null )
    		throw new ProcessingException( "Rcij already has nothing assigned." );  

    	apply(new UnAssignRcijFromGenICompensationForGenJEvent(command.getGenICompensationForGenJId()));
    }
    @CommandHandler
    public void handle(AssignXcijToGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignXcijToGenICompensationForGenJCommand" );
    	
    	if (  xcij != null && xcij.getPUId() == command.getAssignment().getPUId() )
    		throw new ProcessingException( "Xcij already assigned with id " + command.getAssignment().getPUId() );  
    		
        apply(new AssignXcijToGenICompensationForGenJEvent(command.getGenICompensationForGenJId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignXcijFromGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignXcijFromGenICompensationForGenJCommand" );

    	if (  xcij == null )
    		throw new ProcessingException( "Xcij already has nothing assigned." );  

    	apply(new UnAssignXcijFromGenICompensationForGenJEvent(command.getGenICompensationForGenJId()));
    }
    @CommandHandler
    public void handle(AssignSynchronousMachineDynamicsToGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSynchronousMachineDynamicsToGenICompensationForGenJCommand" );
    	
    	if (  synchronousMachineDynamics != null && synchronousMachineDynamics.getSynchronousMachineDynamicsId() == command.getAssignment().getSynchronousMachineDynamicsId() )
    		throw new ProcessingException( "SynchronousMachineDynamics already assigned with id " + command.getAssignment().getSynchronousMachineDynamicsId() );  
    		
        apply(new AssignSynchronousMachineDynamicsToGenICompensationForGenJEvent(command.getGenICompensationForGenJId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSynchronousMachineDynamicsFromGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSynchronousMachineDynamicsFromGenICompensationForGenJCommand" );

    	if (  synchronousMachineDynamics == null )
    		throw new ProcessingException( "SynchronousMachineDynamics already has nothing assigned." );  

    	apply(new UnAssignSynchronousMachineDynamicsFromGenICompensationForGenJEvent(command.getGenICompensationForGenJId()));
    }
    @CommandHandler
    public void handle(AssignVcompIEEEType2ToGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignVcompIEEEType2ToGenICompensationForGenJCommand" );
    	
    	if (  vcompIEEEType2 != null && vcompIEEEType2.getVCompIEEEType2Id() == command.getAssignment().getVCompIEEEType2Id() )
    		throw new ProcessingException( "VcompIEEEType2 already assigned with id " + command.getAssignment().getVCompIEEEType2Id() );  
    		
        apply(new AssignVcompIEEEType2ToGenICompensationForGenJEvent(command.getGenICompensationForGenJId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignVcompIEEEType2FromGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignVcompIEEEType2FromGenICompensationForGenJCommand" );

    	if (  vcompIEEEType2 == null )
    		throw new ProcessingException( "VcompIEEEType2 already has nothing assigned." );  

    	apply(new UnAssignVcompIEEEType2FromGenICompensationForGenJEvent(command.getGenICompensationForGenJId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateGenICompensationForGenJEvent event) {	
    	LOGGER.info( "Event sourcing CreateGenICompensationForGenJEvent" );
    	this.genICompensationForGenJId = event.getGenICompensationForGenJId();
    }
    
    @EventSourcingHandler
    void on(UpdateGenICompensationForGenJEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.rcij = event.getRcij();
        this.xcij = event.getXcij();
        this.synchronousMachineDynamics = event.getSynchronousMachineDynamics();
        this.vcompIEEEType2 = event.getVcompIEEEType2();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignRcijToGenICompensationForGenJEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRcijToGenICompensationForGenJEvent" );
    	this.rcij = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRcijFromGenICompensationForGenJEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRcijFromGenICompensationForGenJEvent" );
		this.rcij = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignXcijToGenICompensationForGenJEvent event ) {	
    	LOGGER.info( "Event sourcing AssignXcijToGenICompensationForGenJEvent" );
    	this.xcij = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignXcijFromGenICompensationForGenJEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignXcijFromGenICompensationForGenJEvent" );
		this.xcij = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignSynchronousMachineDynamicsToGenICompensationForGenJEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSynchronousMachineDynamicsToGenICompensationForGenJEvent" );
    	this.synchronousMachineDynamics = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSynchronousMachineDynamicsFromGenICompensationForGenJEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSynchronousMachineDynamicsFromGenICompensationForGenJEvent" );
		this.synchronousMachineDynamics = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignVcompIEEEType2ToGenICompensationForGenJEvent event ) {	
    	LOGGER.info( "Event sourcing AssignVcompIEEEType2ToGenICompensationForGenJEvent" );
    	this.vcompIEEEType2 = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignVcompIEEEType2FromGenICompensationForGenJEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignVcompIEEEType2FromGenICompensationForGenJEvent" );
		this.vcompIEEEType2 = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID genICompensationForGenJId;
    
    private PU rcij = null;
    private PU xcij = null;
    private SynchronousMachineDynamics synchronousMachineDynamics = null;
    private VCompIEEEType2 vcompIEEEType2 = null;

    private static final Logger LOGGER 	= Logger.getLogger(GenICompensationForGenJAggregate.class.getName());
}
