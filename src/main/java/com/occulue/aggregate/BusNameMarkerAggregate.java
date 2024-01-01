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
 * Aggregate handler for BusNameMarker as outlined for the CQRS pattern, all write responsibilities 
 * related to BusNameMarker are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class BusNameMarkerAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public BusNameMarkerAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public BusNameMarkerAggregate(CreateBusNameMarkerCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateBusNameMarkerCommand" );
    	CreateBusNameMarkerEvent event = new CreateBusNameMarkerEvent(command.getBusNameMarkerId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateBusNameMarkerCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateBusNameMarkerCommand" );
    	UpdateBusNameMarkerEvent event = new UpdateBusNameMarkerEvent(command.getBusNameMarkerId(), command.getPriority(), command.getReportingGroup());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteBusNameMarkerCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteBusNameMarkerCommand" );
        apply(new DeleteBusNameMarkerEvent(command.getBusNameMarkerId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignPriorityToBusNameMarkerCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPriorityToBusNameMarkerCommand" );
    	
    	if (  priority != null && priority.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "Priority already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignPriorityToBusNameMarkerEvent(command.getBusNameMarkerId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPriorityFromBusNameMarkerCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPriorityFromBusNameMarkerCommand" );

    	if (  priority == null )
    		throw new ProcessingException( "Priority already has nothing assigned." );  

    	apply(new UnAssignPriorityFromBusNameMarkerEvent(command.getBusNameMarkerId()));
    }
    @CommandHandler
    public void handle(AssignReportingGroupToBusNameMarkerCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignReportingGroupToBusNameMarkerCommand" );
    	
    	if (  reportingGroup != null && reportingGroup.getReportingGroupId() == command.getAssignment().getReportingGroupId() )
    		throw new ProcessingException( "ReportingGroup already assigned with id " + command.getAssignment().getReportingGroupId() );  
    		
        apply(new AssignReportingGroupToBusNameMarkerEvent(command.getBusNameMarkerId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignReportingGroupFromBusNameMarkerCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignReportingGroupFromBusNameMarkerCommand" );

    	if (  reportingGroup == null )
    		throw new ProcessingException( "ReportingGroup already has nothing assigned." );  

    	apply(new UnAssignReportingGroupFromBusNameMarkerEvent(command.getBusNameMarkerId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateBusNameMarkerEvent event) {	
    	LOGGER.info( "Event sourcing CreateBusNameMarkerEvent" );
    	this.busNameMarkerId = event.getBusNameMarkerId();
    }
    
    @EventSourcingHandler
    void on(UpdateBusNameMarkerEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.priority = event.getPriority();
        this.reportingGroup = event.getReportingGroup();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignPriorityToBusNameMarkerEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPriorityToBusNameMarkerEvent" );
    	this.priority = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPriorityFromBusNameMarkerEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPriorityFromBusNameMarkerEvent" );
		this.priority = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignReportingGroupToBusNameMarkerEvent event ) {	
    	LOGGER.info( "Event sourcing AssignReportingGroupToBusNameMarkerEvent" );
    	this.reportingGroup = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignReportingGroupFromBusNameMarkerEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignReportingGroupFromBusNameMarkerEvent" );
		this.reportingGroup = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID busNameMarkerId;
    
    private IntegerProxy priority = null;
    private ReportingGroup reportingGroup = null;
    private ACDCTerminal busNameMarker = null;

    private static final Logger LOGGER 	= Logger.getLogger(BusNameMarkerAggregate.class.getName());
}
