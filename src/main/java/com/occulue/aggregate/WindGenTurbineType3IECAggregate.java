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
 * Aggregate handler for WindGenTurbineType3IEC as outlined for the CQRS pattern, all write responsibilities 
 * related to WindGenTurbineType3IEC are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindGenTurbineType3IECAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindGenTurbineType3IECAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindGenTurbineType3IECAggregate(CreateWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindGenTurbineType3IECCommand" );
    	CreateWindGenTurbineType3IECEvent event = new CreateWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindGenTurbineType3IECCommand" );
    	UpdateWindGenTurbineType3IECEvent event = new UpdateWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId(), command.getDipmax(), command.getDiqmax(), command.getWindMechIEC(), command.getWindContPitchAngleIEC(), command.getWindAeroLinearIEC(), command.getWindContPType3IEC());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindGenTurbineType3IECCommand" );
        apply(new DeleteWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignDipmaxToWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDipmaxToWindGenTurbineType3IECCommand" );
    	
    	if (  dipmax != null && dipmax.getPUId() == command.getAssignment().getPUId() )
    		throw new ProcessingException( "Dipmax already assigned with id " + command.getAssignment().getPUId() );  
    		
        apply(new AssignDipmaxToWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDipmaxFromWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDipmaxFromWindGenTurbineType3IECCommand" );

    	if (  dipmax == null )
    		throw new ProcessingException( "Dipmax already has nothing assigned." );  

    	apply(new UnAssignDipmaxFromWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId()));
    }
    @CommandHandler
    public void handle(AssignDiqmaxToWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDiqmaxToWindGenTurbineType3IECCommand" );
    	
    	if (  diqmax != null && diqmax.getPUId() == command.getAssignment().getPUId() )
    		throw new ProcessingException( "Diqmax already assigned with id " + command.getAssignment().getPUId() );  
    		
        apply(new AssignDiqmaxToWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDiqmaxFromWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDiqmaxFromWindGenTurbineType3IECCommand" );

    	if (  diqmax == null )
    		throw new ProcessingException( "Diqmax already has nothing assigned." );  

    	apply(new UnAssignDiqmaxFromWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId()));
    }
    @CommandHandler
    public void handle(AssignWindMechIECToWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindMechIECToWindGenTurbineType3IECCommand" );
    	
    	if (  windMechIEC != null && windMechIEC.getWindMechIECId() == command.getAssignment().getWindMechIECId() )
    		throw new ProcessingException( "WindMechIEC already assigned with id " + command.getAssignment().getWindMechIECId() );  
    		
        apply(new AssignWindMechIECToWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindMechIECFromWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindMechIECFromWindGenTurbineType3IECCommand" );

    	if (  windMechIEC == null )
    		throw new ProcessingException( "WindMechIEC already has nothing assigned." );  

    	apply(new UnAssignWindMechIECFromWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId()));
    }
    @CommandHandler
    public void handle(AssignWindContPitchAngleIECToWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindContPitchAngleIECToWindGenTurbineType3IECCommand" );
    	
    	if (  windContPitchAngleIEC != null && windContPitchAngleIEC.getWindContPitchAngleIECId() == command.getAssignment().getWindContPitchAngleIECId() )
    		throw new ProcessingException( "WindContPitchAngleIEC already assigned with id " + command.getAssignment().getWindContPitchAngleIECId() );  
    		
        apply(new AssignWindContPitchAngleIECToWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindContPitchAngleIECFromWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindContPitchAngleIECFromWindGenTurbineType3IECCommand" );

    	if (  windContPitchAngleIEC == null )
    		throw new ProcessingException( "WindContPitchAngleIEC already has nothing assigned." );  

    	apply(new UnAssignWindContPitchAngleIECFromWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId()));
    }
    @CommandHandler
    public void handle(AssignWindAeroLinearIECToWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindAeroLinearIECToWindGenTurbineType3IECCommand" );
    	
    	if (  windAeroLinearIEC != null && windAeroLinearIEC.getWindAeroLinearIECId() == command.getAssignment().getWindAeroLinearIECId() )
    		throw new ProcessingException( "WindAeroLinearIEC already assigned with id " + command.getAssignment().getWindAeroLinearIECId() );  
    		
        apply(new AssignWindAeroLinearIECToWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindAeroLinearIECFromWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindAeroLinearIECFromWindGenTurbineType3IECCommand" );

    	if (  windAeroLinearIEC == null )
    		throw new ProcessingException( "WindAeroLinearIEC already has nothing assigned." );  

    	apply(new UnAssignWindAeroLinearIECFromWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId()));
    }
    @CommandHandler
    public void handle(AssignWindContPType3IECToWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindContPType3IECToWindGenTurbineType3IECCommand" );
    	
    	if (  windContPType3IEC != null && windContPType3IEC.getWindContPType3IECId() == command.getAssignment().getWindContPType3IECId() )
    		throw new ProcessingException( "WindContPType3IEC already assigned with id " + command.getAssignment().getWindContPType3IECId() );  
    		
        apply(new AssignWindContPType3IECToWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindContPType3IECFromWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindContPType3IECFromWindGenTurbineType3IECCommand" );

    	if (  windContPType3IEC == null )
    		throw new ProcessingException( "WindContPType3IEC already has nothing assigned." );  

    	apply(new UnAssignWindContPType3IECFromWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindGenTurbineType3IECEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindGenTurbineType3IECEvent" );
    	this.windGenTurbineType3IECId = event.getWindGenTurbineType3IECId();
    }
    
    @EventSourcingHandler
    void on(UpdateWindGenTurbineType3IECEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.dipmax = event.getDipmax();
        this.diqmax = event.getDiqmax();
        this.windMechIEC = event.getWindMechIEC();
        this.windContPitchAngleIEC = event.getWindContPitchAngleIEC();
        this.windAeroLinearIEC = event.getWindAeroLinearIEC();
        this.windContPType3IEC = event.getWindContPType3IEC();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignDipmaxToWindGenTurbineType3IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDipmaxToWindGenTurbineType3IECEvent" );
    	this.dipmax = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDipmaxFromWindGenTurbineType3IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDipmaxFromWindGenTurbineType3IECEvent" );
		this.dipmax = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignDiqmaxToWindGenTurbineType3IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDiqmaxToWindGenTurbineType3IECEvent" );
    	this.diqmax = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDiqmaxFromWindGenTurbineType3IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDiqmaxFromWindGenTurbineType3IECEvent" );
		this.diqmax = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindMechIECToWindGenTurbineType3IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindMechIECToWindGenTurbineType3IECEvent" );
    	this.windMechIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindMechIECFromWindGenTurbineType3IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindMechIECFromWindGenTurbineType3IECEvent" );
		this.windMechIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindContPitchAngleIECToWindGenTurbineType3IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindContPitchAngleIECToWindGenTurbineType3IECEvent" );
    	this.windContPitchAngleIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindContPitchAngleIECFromWindGenTurbineType3IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindContPitchAngleIECFromWindGenTurbineType3IECEvent" );
		this.windContPitchAngleIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindAeroLinearIECToWindGenTurbineType3IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindAeroLinearIECToWindGenTurbineType3IECEvent" );
    	this.windAeroLinearIEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindAeroLinearIECFromWindGenTurbineType3IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindAeroLinearIECFromWindGenTurbineType3IECEvent" );
		this.windAeroLinearIEC = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindContPType3IECToWindGenTurbineType3IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindContPType3IECToWindGenTurbineType3IECEvent" );
    	this.windContPType3IEC = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindContPType3IECFromWindGenTurbineType3IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindContPType3IECFromWindGenTurbineType3IECEvent" );
		this.windContPType3IEC = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windGenTurbineType3IECId;
    
    private PU dipmax = null;
    private PU diqmax = null;
    private WindMechIEC windMechIEC = null;
    private WindContPitchAngleIEC windContPitchAngleIEC = null;
    private WindAeroLinearIEC windAeroLinearIEC = null;
    private WindContPType3IEC windContPType3IEC = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindGenTurbineType3IECAggregate.class.getName());
}
