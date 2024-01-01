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
 * Aggregate handler for WindDynamicsLookupTable as outlined for the CQRS pattern, all write responsibilities 
 * related to WindDynamicsLookupTable are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindDynamicsLookupTableAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindDynamicsLookupTableAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindDynamicsLookupTableAggregate(CreateWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindDynamicsLookupTableCommand" );
    	CreateWindDynamicsLookupTableEvent event = new CreateWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getLookupTableFunctionType());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindDynamicsLookupTableCommand" );
    	UpdateWindDynamicsLookupTableEvent event = new UpdateWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getInput(), command.getLookupTableFunctionType(), command.getOutput(), command.getSequence(), command.getWindContRotorRIEC(), command.getWindContCurrLimIEC(), command.getWindPlantFreqPcontrolIEC(), command.getWindContPType3IEC());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindDynamicsLookupTableCommand" );
        apply(new DeleteWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignInputToWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignInputToWindDynamicsLookupTableCommand" );
    	
    	if (  input != null && input.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "Input already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignInputToWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignInputFromWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignInputFromWindDynamicsLookupTableCommand" );

    	if (  input == null )
    		throw new ProcessingException( "Input already has nothing assigned." );  

    	apply(new UnAssignInputFromWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }
    @CommandHandler
    public void handle(AssignOutputToWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignOutputToWindDynamicsLookupTableCommand" );
    	
    	if (  output != null && output.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "Output already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignOutputToWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignOutputFromWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignOutputFromWindDynamicsLookupTableCommand" );

    	if (  output == null )
    		throw new ProcessingException( "Output already has nothing assigned." );  

    	apply(new UnAssignOutputFromWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }
    @CommandHandler
    public void handle(AssignSequenceToWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSequenceToWindDynamicsLookupTableCommand" );
    	
    	if (  sequence != null && sequence.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "Sequence already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignSequenceToWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSequenceFromWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSequenceFromWindDynamicsLookupTableCommand" );

    	if (  sequence == null )
    		throw new ProcessingException( "Sequence already has nothing assigned." );  

    	apply(new UnAssignSequenceFromWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }
    @CommandHandler
    public void handle(AssignWindContRotorRIECToWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindContRotorRIECToWindDynamicsLookupTableCommand" );
    	
    	if (  windContRotorRIEC != null && windContRotorRIEC.getWindContRotorRIECId() == command.getAssignment().getWindContRotorRIECId() )
    		throw new ProcessingException( "WindContRotorRIEC already assigned with id " + command.getAssignment().getWindContRotorRIECId() );  
    		
        apply(new AssignWindContRotorRIECToWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindContRotorRIECFromWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindContRotorRIECFromWindDynamicsLookupTableCommand" );

    	if (  windContRotorRIEC == null )
    		throw new ProcessingException( "WindContRotorRIEC already has nothing assigned." );  

    	apply(new UnAssignWindContRotorRIECFromWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }
    @CommandHandler
    public void handle(AssignWindContCurrLimIECToWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindContCurrLimIECToWindDynamicsLookupTableCommand" );
    	
    	if (  windContCurrLimIEC != null && windContCurrLimIEC.getWindContCurrLimIECId() == command.getAssignment().getWindContCurrLimIECId() )
    		throw new ProcessingException( "WindContCurrLimIEC already assigned with id " + command.getAssignment().getWindContCurrLimIECId() );  
    		
        apply(new AssignWindContCurrLimIECToWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindContCurrLimIECFromWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindContCurrLimIECFromWindDynamicsLookupTableCommand" );

    	if (  windContCurrLimIEC == null )
    		throw new ProcessingException( "WindContCurrLimIEC already has nothing assigned." );  

    	apply(new UnAssignWindContCurrLimIECFromWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }
    @CommandHandler
    public void handle(AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableCommand" );
    	
    	if (  windPlantFreqPcontrolIEC != null && windPlantFreqPcontrolIEC.getWindPlantFreqPcontrolIECId() == command.getAssignment().getWindPlantFreqPcontrolIECId() )
    		throw new ProcessingException( "WindPlantFreqPcontrolIEC already assigned with id " + command.getAssignment().getWindPlantFreqPcontrolIECId() );  
    		
        apply(new AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableCommand" );

    	if (  windPlantFreqPcontrolIEC == null )
    		throw new ProcessingException( "WindPlantFreqPcontrolIEC already has nothing assigned." );  

    	apply(new UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }
    @CommandHandler
    public void handle(AssignWindContPType3IECToWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindContPType3IECToWindDynamicsLookupTableCommand" );
    	
    	if (  windContPType3IEC != null && windContPType3IEC.getWindContPType3IECId() == command.getAssignment().getWindContPType3IECId() )
    		throw new ProcessingException( "WindContPType3IEC already assigned with id " + command.getAssignment().getWindContPType3IECId() );  
    		
        apply(new AssignWindContPType3IECToWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindContPType3IECFromWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindContPType3IECFromWindDynamicsLookupTableCommand" );

    	if (  windContPType3IEC == null )
    		throw new ProcessingException( "WindContPType3IEC already has nothing assigned." );  

    	apply(new UnAssignWindContPType3IECFromWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindDynamicsLookupTableEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindDynamicsLookupTableEvent" );
    	this.windDynamicsLookupTableId = event.getWindDynamicsLookupTableId();
        this.lookupTableFunctionType = event.getLookupTableFunctionType();
    }
    
    @EventSourcingHandler
    void on(UpdateWindDynamicsLookupTableEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.input = event.getInput();
        this.lookupTableFunctionType = event.getLookupTableFunctionType();
        this.output = event.getOutput();
        this.sequence = event.getSequence();
        this.windContRotorRIEC = event.getWindContRotorRIEC();
        this.windContCurrLimIEC = event.getWindContCurrLimIEC();
        this.windPlantFreqPcontrolIEC = event.getWindPlantFreqPcontrolIEC();
        this.windContPType3IEC = event.getWindContPType3IEC();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignInputToWindDynamicsLookupTableEvent event ) {	
    	LOGGER.info( "Event sourcing AssignInputToWindDynamicsLookupTableEvent" );
    	this.input = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignInputFromWindDynamicsLookupTableEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignInputFromWindDynamicsLookupTableEvent" );
		this.input = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignOutputToWindDynamicsLookupTableEvent event ) {	
    	LOGGER.info( "Event sourcing AssignOutputToWindDynamicsLookupTableEvent" );
    	this.output = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignOutputFromWindDynamicsLookupTableEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignOutputFromWindDynamicsLookupTableEvent" );
		this.output = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignSequenceToWindDynamicsLookupTableEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSequenceToWindDynamicsLookupTableEvent" );
    	this.sequence = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSequenceFromWindDynamicsLookupTableEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSequenceFromWindDynamicsLookupTableEvent" );
		this.sequence = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindContRotorRIECToWindDynamicsLookupTableEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindContRotorRIECToWindDynamicsLookupTableEvent" );
    	this.windContRotorRIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindContRotorRIECFromWindDynamicsLookupTableEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindContRotorRIECFromWindDynamicsLookupTableEvent" );
		this.windContRotorRIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindContCurrLimIECToWindDynamicsLookupTableEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindContCurrLimIECToWindDynamicsLookupTableEvent" );
    	this.windContCurrLimIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindContCurrLimIECFromWindDynamicsLookupTableEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindContCurrLimIECFromWindDynamicsLookupTableEvent" );
		this.windContCurrLimIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableEvent" );
    	this.windPlantFreqPcontrolIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableEvent" );
		this.windPlantFreqPcontrolIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindContPType3IECToWindDynamicsLookupTableEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindContPType3IECToWindDynamicsLookupTableEvent" );
    	this.windContPType3IEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindContPType3IECFromWindDynamicsLookupTableEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindContPType3IECFromWindDynamicsLookupTableEvent" );
		this.windContPType3IEC = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windDynamicsLookupTableId;
    
    private WindLookupTableFunctionKind lookupTableFunctionType;
    private Simple_Float input = null;
    private Simple_Float output = null;
    private IntegerProxy sequence = null;
    private WindContRotorRIEC windContRotorRIEC = null;
    private WindContCurrLimIEC windContCurrLimIEC = null;
    private WindPlantFreqPcontrolIEC windPlantFreqPcontrolIEC = null;
    private WindContPType3IEC windContPType3IEC = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindDynamicsLookupTableAggregate.class.getName());
}
