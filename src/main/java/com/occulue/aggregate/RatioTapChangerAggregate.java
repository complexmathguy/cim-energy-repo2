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
 * Aggregate handler for RatioTapChanger as outlined for the CQRS pattern, all write responsibilities 
 * related to RatioTapChanger are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class RatioTapChangerAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public RatioTapChangerAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public RatioTapChangerAggregate(CreateRatioTapChangerCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateRatioTapChangerCommand" );
    	CreateRatioTapChangerEvent event = new CreateRatioTapChangerEvent(command.getRatioTapChangerId(), command.getTculControlMode());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateRatioTapChangerCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateRatioTapChangerCommand" );
    	UpdateRatioTapChangerEvent event = new UpdateRatioTapChangerEvent(command.getRatioTapChangerId(), command.getStepVoltageIncrement(), command.getTculControlMode(), command.getTransformerEnd(), command.getRatioTapChangerTable());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteRatioTapChangerCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteRatioTapChangerCommand" );
        apply(new DeleteRatioTapChangerEvent(command.getRatioTapChangerId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignStepVoltageIncrementToRatioTapChangerCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignStepVoltageIncrementToRatioTapChangerCommand" );
    	
    	if (  stepVoltageIncrement != null && stepVoltageIncrement.getPerCentId() == command.getAssignment().getPerCentId() )
    		throw new ProcessingException( "StepVoltageIncrement already assigned with id " + command.getAssignment().getPerCentId() );  
    		
        apply(new AssignStepVoltageIncrementToRatioTapChangerEvent(command.getRatioTapChangerId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignStepVoltageIncrementFromRatioTapChangerCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignStepVoltageIncrementFromRatioTapChangerCommand" );

    	if (  stepVoltageIncrement == null )
    		throw new ProcessingException( "StepVoltageIncrement already has nothing assigned." );  

    	apply(new UnAssignStepVoltageIncrementFromRatioTapChangerEvent(command.getRatioTapChangerId()));
    }
    @CommandHandler
    public void handle(AssignTransformerEndToRatioTapChangerCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTransformerEndToRatioTapChangerCommand" );
    	
    	if (  transformerEnd != null && transformerEnd.getTransformerEndId() == command.getAssignment().getTransformerEndId() )
    		throw new ProcessingException( "TransformerEnd already assigned with id " + command.getAssignment().getTransformerEndId() );  
    		
        apply(new AssignTransformerEndToRatioTapChangerEvent(command.getRatioTapChangerId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTransformerEndFromRatioTapChangerCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTransformerEndFromRatioTapChangerCommand" );

    	if (  transformerEnd == null )
    		throw new ProcessingException( "TransformerEnd already has nothing assigned." );  

    	apply(new UnAssignTransformerEndFromRatioTapChangerEvent(command.getRatioTapChangerId()));
    }
    @CommandHandler
    public void handle(AssignRatioTapChangerTableToRatioTapChangerCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRatioTapChangerTableToRatioTapChangerCommand" );
    	
    	if (  ratioTapChangerTable != null && ratioTapChangerTable.getRatioTapChangerTableId() == command.getAssignment().getRatioTapChangerTableId() )
    		throw new ProcessingException( "RatioTapChangerTable already assigned with id " + command.getAssignment().getRatioTapChangerTableId() );  
    		
        apply(new AssignRatioTapChangerTableToRatioTapChangerEvent(command.getRatioTapChangerId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRatioTapChangerTableFromRatioTapChangerCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRatioTapChangerTableFromRatioTapChangerCommand" );

    	if (  ratioTapChangerTable == null )
    		throw new ProcessingException( "RatioTapChangerTable already has nothing assigned." );  

    	apply(new UnAssignRatioTapChangerTableFromRatioTapChangerEvent(command.getRatioTapChangerId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateRatioTapChangerEvent event) {	
    	LOGGER.info( "Event sourcing CreateRatioTapChangerEvent" );
    	this.ratioTapChangerId = event.getRatioTapChangerId();
        this.tculControlMode = event.getTculControlMode();
    }
    
    @EventSourcingHandler
    void on(UpdateRatioTapChangerEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.stepVoltageIncrement = event.getStepVoltageIncrement();
        this.tculControlMode = event.getTculControlMode();
        this.transformerEnd = event.getTransformerEnd();
        this.ratioTapChangerTable = event.getRatioTapChangerTable();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignStepVoltageIncrementToRatioTapChangerEvent event ) {	
    	LOGGER.info( "Event sourcing AssignStepVoltageIncrementToRatioTapChangerEvent" );
    	this.stepVoltageIncrement = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignStepVoltageIncrementFromRatioTapChangerEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignStepVoltageIncrementFromRatioTapChangerEvent" );
		this.stepVoltageIncrement = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTransformerEndToRatioTapChangerEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTransformerEndToRatioTapChangerEvent" );
    	this.transformerEnd = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTransformerEndFromRatioTapChangerEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTransformerEndFromRatioTapChangerEvent" );
		this.transformerEnd = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignRatioTapChangerTableToRatioTapChangerEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRatioTapChangerTableToRatioTapChangerEvent" );
    	this.ratioTapChangerTable = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRatioTapChangerTableFromRatioTapChangerEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRatioTapChangerTableFromRatioTapChangerEvent" );
		this.ratioTapChangerTable = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID ratioTapChangerId;
    
    private TransformerControlMode tculControlMode;
    private PerCent stepVoltageIncrement = null;
    private TransformerEnd transformerEnd = null;
    private RatioTapChangerTable ratioTapChangerTable = null;

    private static final Logger LOGGER 	= Logger.getLogger(RatioTapChangerAggregate.class.getName());
}
