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
 * Aggregate handler for MeasurementValue as outlined for the CQRS pattern, all write responsibilities 
 * related to MeasurementValue are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class MeasurementValueAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public MeasurementValueAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public MeasurementValueAggregate(CreateMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateMeasurementValueCommand" );
    	CreateMeasurementValueEvent event = new CreateMeasurementValueEvent(command.getMeasurementValueId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateMeasurementValueCommand" );
    	UpdateMeasurementValueEvent event = new UpdateMeasurementValueEvent(command.getMeasurementValueId(), command.getSensorAccuracy(), command.getTimeStamp(), command.getMeasurementValueSource());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteMeasurementValueCommand" );
        apply(new DeleteMeasurementValueEvent(command.getMeasurementValueId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignSensorAccuracyToMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSensorAccuracyToMeasurementValueCommand" );
    	
    	if (  sensorAccuracy != null && sensorAccuracy.getPerCentId() == command.getAssignment().getPerCentId() )
    		throw new ProcessingException( "SensorAccuracy already assigned with id " + command.getAssignment().getPerCentId() );  
    		
        apply(new AssignSensorAccuracyToMeasurementValueEvent(command.getMeasurementValueId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSensorAccuracyFromMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSensorAccuracyFromMeasurementValueCommand" );

    	if (  sensorAccuracy == null )
    		throw new ProcessingException( "SensorAccuracy already has nothing assigned." );  

    	apply(new UnAssignSensorAccuracyFromMeasurementValueEvent(command.getMeasurementValueId()));
    }
    @CommandHandler
    public void handle(AssignTimeStampToMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTimeStampToMeasurementValueCommand" );
    	
    	if (  timeStamp != null && timeStamp.getDateTimeId() == command.getAssignment().getDateTimeId() )
    		throw new ProcessingException( "TimeStamp already assigned with id " + command.getAssignment().getDateTimeId() );  
    		
        apply(new AssignTimeStampToMeasurementValueEvent(command.getMeasurementValueId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTimeStampFromMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTimeStampFromMeasurementValueCommand" );

    	if (  timeStamp == null )
    		throw new ProcessingException( "TimeStamp already has nothing assigned." );  

    	apply(new UnAssignTimeStampFromMeasurementValueEvent(command.getMeasurementValueId()));
    }
    @CommandHandler
    public void handle(AssignMeasurementValueSourceToMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignMeasurementValueSourceToMeasurementValueCommand" );
    	
    	if (  measurementValueSource != null && measurementValueSource.getMeasurementValueSourceId() == command.getAssignment().getMeasurementValueSourceId() )
    		throw new ProcessingException( "MeasurementValueSource already assigned with id " + command.getAssignment().getMeasurementValueSourceId() );  
    		
        apply(new AssignMeasurementValueSourceToMeasurementValueEvent(command.getMeasurementValueId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignMeasurementValueSourceFromMeasurementValueCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignMeasurementValueSourceFromMeasurementValueCommand" );

    	if (  measurementValueSource == null )
    		throw new ProcessingException( "MeasurementValueSource already has nothing assigned." );  

    	apply(new UnAssignMeasurementValueSourceFromMeasurementValueEvent(command.getMeasurementValueId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateMeasurementValueEvent event) {	
    	LOGGER.info( "Event sourcing CreateMeasurementValueEvent" );
    	this.measurementValueId = event.getMeasurementValueId();
    }
    
    @EventSourcingHandler
    void on(UpdateMeasurementValueEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.sensorAccuracy = event.getSensorAccuracy();
        this.timeStamp = event.getTimeStamp();
        this.measurementValueSource = event.getMeasurementValueSource();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignSensorAccuracyToMeasurementValueEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSensorAccuracyToMeasurementValueEvent" );
    	this.sensorAccuracy = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSensorAccuracyFromMeasurementValueEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSensorAccuracyFromMeasurementValueEvent" );
		this.sensorAccuracy = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTimeStampToMeasurementValueEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTimeStampToMeasurementValueEvent" );
    	this.timeStamp = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTimeStampFromMeasurementValueEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTimeStampFromMeasurementValueEvent" );
		this.timeStamp = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignMeasurementValueSourceToMeasurementValueEvent event ) {	
    	LOGGER.info( "Event sourcing AssignMeasurementValueSourceToMeasurementValueEvent" );
    	this.measurementValueSource = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignMeasurementValueSourceFromMeasurementValueEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignMeasurementValueSourceFromMeasurementValueEvent" );
		this.measurementValueSource = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID measurementValueId;
    
    private PerCent sensorAccuracy = null;
    private DateTime timeStamp = null;
    private MeasurementValueSource measurementValueSource = null;
    private MeasurementValueQuality measurementValue = null;

    private static final Logger LOGGER 	= Logger.getLogger(MeasurementValueAggregate.class.getName());
}
