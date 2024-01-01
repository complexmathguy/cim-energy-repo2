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
 * Aggregate handler for CurveData as outlined for the CQRS pattern, all write responsibilities 
 * related to CurveData are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class CurveDataAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public CurveDataAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public CurveDataAggregate(CreateCurveDataCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateCurveDataCommand" );
    	CreateCurveDataEvent event = new CreateCurveDataEvent(command.getCurveDataId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateCurveDataCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateCurveDataCommand" );
    	UpdateCurveDataEvent event = new UpdateCurveDataEvent(command.getCurveDataId(), command.getXvalue(), command.getY1value(), command.getY2value(), command.getCurve());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteCurveDataCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteCurveDataCommand" );
        apply(new DeleteCurveDataEvent(command.getCurveDataId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignXvalueToCurveDataCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignXvalueToCurveDataCommand" );
    	
    	if (  xvalue != null && xvalue.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "Xvalue already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignXvalueToCurveDataEvent(command.getCurveDataId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignXvalueFromCurveDataCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignXvalueFromCurveDataCommand" );

    	if (  xvalue == null )
    		throw new ProcessingException( "Xvalue already has nothing assigned." );  

    	apply(new UnAssignXvalueFromCurveDataEvent(command.getCurveDataId()));
    }
    @CommandHandler
    public void handle(AssignY1valueToCurveDataCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignY1valueToCurveDataCommand" );
    	
    	if (  y1value != null && y1value.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "Y1value already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignY1valueToCurveDataEvent(command.getCurveDataId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignY1valueFromCurveDataCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignY1valueFromCurveDataCommand" );

    	if (  y1value == null )
    		throw new ProcessingException( "Y1value already has nothing assigned." );  

    	apply(new UnAssignY1valueFromCurveDataEvent(command.getCurveDataId()));
    }
    @CommandHandler
    public void handle(AssignY2valueToCurveDataCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignY2valueToCurveDataCommand" );
    	
    	if (  y2value != null && y2value.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "Y2value already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignY2valueToCurveDataEvent(command.getCurveDataId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignY2valueFromCurveDataCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignY2valueFromCurveDataCommand" );

    	if (  y2value == null )
    		throw new ProcessingException( "Y2value already has nothing assigned." );  

    	apply(new UnAssignY2valueFromCurveDataEvent(command.getCurveDataId()));
    }
    @CommandHandler
    public void handle(AssignCurveToCurveDataCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignCurveToCurveDataCommand" );
    	
    	if (  curve != null && curve.getCurveId() == command.getAssignment().getCurveId() )
    		throw new ProcessingException( "Curve already assigned with id " + command.getAssignment().getCurveId() );  
    		
        apply(new AssignCurveToCurveDataEvent(command.getCurveDataId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignCurveFromCurveDataCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignCurveFromCurveDataCommand" );

    	if (  curve == null )
    		throw new ProcessingException( "Curve already has nothing assigned." );  

    	apply(new UnAssignCurveFromCurveDataEvent(command.getCurveDataId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateCurveDataEvent event) {	
    	LOGGER.info( "Event sourcing CreateCurveDataEvent" );
    	this.curveDataId = event.getCurveDataId();
    }
    
    @EventSourcingHandler
    void on(UpdateCurveDataEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.xvalue = event.getXvalue();
        this.y1value = event.getY1value();
        this.y2value = event.getY2value();
        this.curve = event.getCurve();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignXvalueToCurveDataEvent event ) {	
    	LOGGER.info( "Event sourcing AssignXvalueToCurveDataEvent" );
    	this.xvalue = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignXvalueFromCurveDataEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignXvalueFromCurveDataEvent" );
		this.xvalue = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignY1valueToCurveDataEvent event ) {	
    	LOGGER.info( "Event sourcing AssignY1valueToCurveDataEvent" );
    	this.y1value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignY1valueFromCurveDataEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignY1valueFromCurveDataEvent" );
		this.y1value = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignY2valueToCurveDataEvent event ) {	
    	LOGGER.info( "Event sourcing AssignY2valueToCurveDataEvent" );
    	this.y2value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignY2valueFromCurveDataEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignY2valueFromCurveDataEvent" );
		this.y2value = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignCurveToCurveDataEvent event ) {	
    	LOGGER.info( "Event sourcing AssignCurveToCurveDataEvent" );
    	this.curve = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignCurveFromCurveDataEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignCurveFromCurveDataEvent" );
		this.curve = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID curveDataId;
    
    private Simple_Float xvalue = null;
    private Simple_Float y1value = null;
    private Simple_Float y2value = null;
    private Curve curve = null;

    private static final Logger LOGGER 	= Logger.getLogger(CurveDataAggregate.class.getName());
}
