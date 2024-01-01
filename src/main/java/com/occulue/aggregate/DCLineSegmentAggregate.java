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
 * Aggregate handler for DCLineSegment as outlined for the CQRS pattern, all write responsibilities 
 * related to DCLineSegment are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class DCLineSegmentAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public DCLineSegmentAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public DCLineSegmentAggregate(CreateDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateDCLineSegmentCommand" );
    	CreateDCLineSegmentEvent event = new CreateDCLineSegmentEvent(command.getDCLineSegmentId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateDCLineSegmentCommand" );
    	UpdateDCLineSegmentEvent event = new UpdateDCLineSegmentEvent(command.getDCLineSegmentId(), command.getCapacitance(), command.getInductance(), command.getLength(), command.getResistance(), command.getPerLengthParameter());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteDCLineSegmentCommand" );
        apply(new DeleteDCLineSegmentEvent(command.getDCLineSegmentId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignCapacitanceToDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignCapacitanceToDCLineSegmentCommand" );
    	
    	if (  capacitance != null && capacitance.getCapacitanceId() == command.getAssignment().getCapacitanceId() )
    		throw new ProcessingException( "Capacitance already assigned with id " + command.getAssignment().getCapacitanceId() );  
    		
        apply(new AssignCapacitanceToDCLineSegmentEvent(command.getDCLineSegmentId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignCapacitanceFromDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignCapacitanceFromDCLineSegmentCommand" );

    	if (  capacitance == null )
    		throw new ProcessingException( "Capacitance already has nothing assigned." );  

    	apply(new UnAssignCapacitanceFromDCLineSegmentEvent(command.getDCLineSegmentId()));
    }
    @CommandHandler
    public void handle(AssignInductanceToDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignInductanceToDCLineSegmentCommand" );
    	
    	if (  inductance != null && inductance.getInductanceId() == command.getAssignment().getInductanceId() )
    		throw new ProcessingException( "Inductance already assigned with id " + command.getAssignment().getInductanceId() );  
    		
        apply(new AssignInductanceToDCLineSegmentEvent(command.getDCLineSegmentId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignInductanceFromDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignInductanceFromDCLineSegmentCommand" );

    	if (  inductance == null )
    		throw new ProcessingException( "Inductance already has nothing assigned." );  

    	apply(new UnAssignInductanceFromDCLineSegmentEvent(command.getDCLineSegmentId()));
    }
    @CommandHandler
    public void handle(AssignLengthToDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignLengthToDCLineSegmentCommand" );
    	
    	if (  length != null && length.getLengthId() == command.getAssignment().getLengthId() )
    		throw new ProcessingException( "Length already assigned with id " + command.getAssignment().getLengthId() );  
    		
        apply(new AssignLengthToDCLineSegmentEvent(command.getDCLineSegmentId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignLengthFromDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignLengthFromDCLineSegmentCommand" );

    	if (  length == null )
    		throw new ProcessingException( "Length already has nothing assigned." );  

    	apply(new UnAssignLengthFromDCLineSegmentEvent(command.getDCLineSegmentId()));
    }
    @CommandHandler
    public void handle(AssignResistanceToDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignResistanceToDCLineSegmentCommand" );
    	
    	if (  resistance != null && resistance.getResistanceId() == command.getAssignment().getResistanceId() )
    		throw new ProcessingException( "Resistance already assigned with id " + command.getAssignment().getResistanceId() );  
    		
        apply(new AssignResistanceToDCLineSegmentEvent(command.getDCLineSegmentId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignResistanceFromDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignResistanceFromDCLineSegmentCommand" );

    	if (  resistance == null )
    		throw new ProcessingException( "Resistance already has nothing assigned." );  

    	apply(new UnAssignResistanceFromDCLineSegmentEvent(command.getDCLineSegmentId()));
    }
    @CommandHandler
    public void handle(AssignPerLengthParameterToDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPerLengthParameterToDCLineSegmentCommand" );
    	
    	if (  perLengthParameter != null && perLengthParameter.getPerLengthDCLineParameterId() == command.getAssignment().getPerLengthDCLineParameterId() )
    		throw new ProcessingException( "PerLengthParameter already assigned with id " + command.getAssignment().getPerLengthDCLineParameterId() );  
    		
        apply(new AssignPerLengthParameterToDCLineSegmentEvent(command.getDCLineSegmentId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPerLengthParameterFromDCLineSegmentCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPerLengthParameterFromDCLineSegmentCommand" );

    	if (  perLengthParameter == null )
    		throw new ProcessingException( "PerLengthParameter already has nothing assigned." );  

    	apply(new UnAssignPerLengthParameterFromDCLineSegmentEvent(command.getDCLineSegmentId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateDCLineSegmentEvent event) {	
    	LOGGER.info( "Event sourcing CreateDCLineSegmentEvent" );
    	this.dCLineSegmentId = event.getDCLineSegmentId();
    }
    
    @EventSourcingHandler
    void on(UpdateDCLineSegmentEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.capacitance = event.getCapacitance();
        this.inductance = event.getInductance();
        this.length = event.getLength();
        this.resistance = event.getResistance();
        this.perLengthParameter = event.getPerLengthParameter();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignCapacitanceToDCLineSegmentEvent event ) {	
    	LOGGER.info( "Event sourcing AssignCapacitanceToDCLineSegmentEvent" );
    	this.capacitance = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignCapacitanceFromDCLineSegmentEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignCapacitanceFromDCLineSegmentEvent" );
		this.capacitance = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignInductanceToDCLineSegmentEvent event ) {	
    	LOGGER.info( "Event sourcing AssignInductanceToDCLineSegmentEvent" );
    	this.inductance = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignInductanceFromDCLineSegmentEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignInductanceFromDCLineSegmentEvent" );
		this.inductance = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignLengthToDCLineSegmentEvent event ) {	
    	LOGGER.info( "Event sourcing AssignLengthToDCLineSegmentEvent" );
    	this.length = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignLengthFromDCLineSegmentEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignLengthFromDCLineSegmentEvent" );
		this.length = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignResistanceToDCLineSegmentEvent event ) {	
    	LOGGER.info( "Event sourcing AssignResistanceToDCLineSegmentEvent" );
    	this.resistance = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignResistanceFromDCLineSegmentEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignResistanceFromDCLineSegmentEvent" );
		this.resistance = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignPerLengthParameterToDCLineSegmentEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPerLengthParameterToDCLineSegmentEvent" );
    	this.perLengthParameter = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPerLengthParameterFromDCLineSegmentEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPerLengthParameterFromDCLineSegmentEvent" );
		this.perLengthParameter = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID dCLineSegmentId;
    
    private Capacitance capacitance = null;
    private Inductance inductance = null;
    private Length length = null;
    private Resistance resistance = null;
    private PerLengthDCLineParameter perLengthParameter = null;

    private static final Logger LOGGER 	= Logger.getLogger(DCLineSegmentAggregate.class.getName());
}
