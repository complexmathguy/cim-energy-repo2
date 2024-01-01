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
 * Aggregate handler for SeasonDayTypeSchedule as outlined for the CQRS pattern, all write responsibilities 
 * related to SeasonDayTypeSchedule are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class SeasonDayTypeScheduleAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public SeasonDayTypeScheduleAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public SeasonDayTypeScheduleAggregate(CreateSeasonDayTypeScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateSeasonDayTypeScheduleCommand" );
    	CreateSeasonDayTypeScheduleEvent event = new CreateSeasonDayTypeScheduleEvent(command.getSeasonDayTypeScheduleId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateSeasonDayTypeScheduleCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateSeasonDayTypeScheduleCommand" );
    	UpdateSeasonDayTypeScheduleEvent event = new UpdateSeasonDayTypeScheduleEvent(command.getSeasonDayTypeScheduleId(), command.getSeason(), command.getDayType());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteSeasonDayTypeScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteSeasonDayTypeScheduleCommand" );
        apply(new DeleteSeasonDayTypeScheduleEvent(command.getSeasonDayTypeScheduleId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignSeasonToSeasonDayTypeScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSeasonToSeasonDayTypeScheduleCommand" );
    	
    	if (  season != null && season.getSeasonId() == command.getAssignment().getSeasonId() )
    		throw new ProcessingException( "Season already assigned with id " + command.getAssignment().getSeasonId() );  
    		
        apply(new AssignSeasonToSeasonDayTypeScheduleEvent(command.getSeasonDayTypeScheduleId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSeasonFromSeasonDayTypeScheduleCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSeasonFromSeasonDayTypeScheduleCommand" );

    	if (  season == null )
    		throw new ProcessingException( "Season already has nothing assigned." );  

    	apply(new UnAssignSeasonFromSeasonDayTypeScheduleEvent(command.getSeasonDayTypeScheduleId()));
    }
    @CommandHandler
    public void handle(AssignDayTypeToSeasonDayTypeScheduleCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDayTypeToSeasonDayTypeScheduleCommand" );
    	
    	if (  dayType != null && dayType.getDayTypeId() == command.getAssignment().getDayTypeId() )
    		throw new ProcessingException( "DayType already assigned with id " + command.getAssignment().getDayTypeId() );  
    		
        apply(new AssignDayTypeToSeasonDayTypeScheduleEvent(command.getSeasonDayTypeScheduleId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDayTypeFromSeasonDayTypeScheduleCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDayTypeFromSeasonDayTypeScheduleCommand" );

    	if (  dayType == null )
    		throw new ProcessingException( "DayType already has nothing assigned." );  

    	apply(new UnAssignDayTypeFromSeasonDayTypeScheduleEvent(command.getSeasonDayTypeScheduleId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateSeasonDayTypeScheduleEvent event) {	
    	LOGGER.info( "Event sourcing CreateSeasonDayTypeScheduleEvent" );
    	this.seasonDayTypeScheduleId = event.getSeasonDayTypeScheduleId();
    }
    
    @EventSourcingHandler
    void on(UpdateSeasonDayTypeScheduleEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.season = event.getSeason();
        this.dayType = event.getDayType();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignSeasonToSeasonDayTypeScheduleEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSeasonToSeasonDayTypeScheduleEvent" );
    	this.season = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSeasonFromSeasonDayTypeScheduleEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSeasonFromSeasonDayTypeScheduleEvent" );
		this.season = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignDayTypeToSeasonDayTypeScheduleEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDayTypeToSeasonDayTypeScheduleEvent" );
    	this.dayType = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDayTypeFromSeasonDayTypeScheduleEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDayTypeFromSeasonDayTypeScheduleEvent" );
		this.dayType = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID seasonDayTypeScheduleId;
    
    private Season season = null;
    private DayType dayType = null;

    private static final Logger LOGGER 	= Logger.getLogger(SeasonDayTypeScheduleAggregate.class.getName());
}
