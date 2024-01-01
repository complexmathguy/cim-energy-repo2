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
 * Aggregate handler for SvTapStep as outlined for the CQRS pattern, all write responsibilities 
 * related to SvTapStep are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class SvTapStepAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public SvTapStepAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public SvTapStepAggregate(CreateSvTapStepCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateSvTapStepCommand" );
    	CreateSvTapStepEvent event = new CreateSvTapStepEvent(command.getSvTapStepId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateSvTapStepCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateSvTapStepCommand" );
    	UpdateSvTapStepEvent event = new UpdateSvTapStepEvent(command.getSvTapStepId(), command.getPosition(), command.getTapChanger());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteSvTapStepCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteSvTapStepCommand" );
        apply(new DeleteSvTapStepEvent(command.getSvTapStepId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignPositionToSvTapStepCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPositionToSvTapStepCommand" );
    	
    	if (  position != null && position.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "Position already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignPositionToSvTapStepEvent(command.getSvTapStepId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPositionFromSvTapStepCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPositionFromSvTapStepCommand" );

    	if (  position == null )
    		throw new ProcessingException( "Position already has nothing assigned." );  

    	apply(new UnAssignPositionFromSvTapStepEvent(command.getSvTapStepId()));
    }
    @CommandHandler
    public void handle(AssignTapChangerToSvTapStepCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTapChangerToSvTapStepCommand" );
    	
    	if (  tapChanger != null && tapChanger.getTapChangerId() == command.getAssignment().getTapChangerId() )
    		throw new ProcessingException( "TapChanger already assigned with id " + command.getAssignment().getTapChangerId() );  
    		
        apply(new AssignTapChangerToSvTapStepEvent(command.getSvTapStepId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTapChangerFromSvTapStepCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTapChangerFromSvTapStepCommand" );

    	if (  tapChanger == null )
    		throw new ProcessingException( "TapChanger already has nothing assigned." );  

    	apply(new UnAssignTapChangerFromSvTapStepEvent(command.getSvTapStepId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateSvTapStepEvent event) {	
    	LOGGER.info( "Event sourcing CreateSvTapStepEvent" );
    	this.svTapStepId = event.getSvTapStepId();
    }
    
    @EventSourcingHandler
    void on(UpdateSvTapStepEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.position = event.getPosition();
        this.tapChanger = event.getTapChanger();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignPositionToSvTapStepEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPositionToSvTapStepEvent" );
    	this.position = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPositionFromSvTapStepEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPositionFromSvTapStepEvent" );
		this.position = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTapChangerToSvTapStepEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTapChangerToSvTapStepEvent" );
    	this.tapChanger = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTapChangerFromSvTapStepEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTapChangerFromSvTapStepEvent" );
		this.tapChanger = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID svTapStepId;
    
    private Simple_Float position = null;
    private TapChanger tapChanger = null;

    private static final Logger LOGGER 	= Logger.getLogger(SvTapStepAggregate.class.getName());
}
