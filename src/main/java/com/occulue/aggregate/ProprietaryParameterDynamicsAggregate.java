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
 * Aggregate handler for ProprietaryParameterDynamics as outlined for the CQRS pattern, all write responsibilities 
 * related to ProprietaryParameterDynamics are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ProprietaryParameterDynamicsAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ProprietaryParameterDynamicsAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ProprietaryParameterDynamicsAggregate(CreateProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateProprietaryParameterDynamicsCommand" );
    	CreateProprietaryParameterDynamicsEvent event = new CreateProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateProprietaryParameterDynamicsCommand" );
    	UpdateProprietaryParameterDynamicsEvent event = new UpdateProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getBooleanParameterValue(), command.getFloatParameterValue(), command.getIntegerParameterValue(), command.getParameterNumber(), command.getPFVArControllerType1UserDefined(), command.getVoltageCompensatorUserDefined(), command.getMechanicalLoadUserDefined(), command.getExcitationSystemUserDefined(), command.getAsynchronousMachineUserDefined(), command.getDiscontinuousExcitationControlUserDefined(), command.getTurbineGovernorUserDefined(), command.getVoltageAdjusterUserDefined(), command.getSynchronousMachineUserDefined(), command.getUnderexcitationLimiterUserDefined(), command.getTurbineLoadControllerUserDefined(), command.getOverexcitationLimiterUserDefined(), command.getPowerSystemStabilizerUserDefined(), command.getLoadUserDefined(), command.getPFVArControllerType2UserDefined(), command.getWindType3or4UserDefined(), command.getWindPlantUserDefined(), command.getWindType1or2UserDefined());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteProprietaryParameterDynamicsCommand" );
        apply(new DeleteProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignBooleanParameterValueToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignBooleanParameterValueToProprietaryParameterDynamicsCommand" );
    	
    	if (  booleanParameterValue != null && booleanParameterValue.getBooleanProxyId() == command.getAssignment().getBooleanProxyId() )
    		throw new ProcessingException( "BooleanParameterValue already assigned with id " + command.getAssignment().getBooleanProxyId() );  
    		
        apply(new AssignBooleanParameterValueToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand" );

    	if (  booleanParameterValue == null )
    		throw new ProcessingException( "BooleanParameterValue already has nothing assigned." );  

    	apply(new UnAssignBooleanParameterValueFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignFloatParameterValueToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignFloatParameterValueToProprietaryParameterDynamicsCommand" );
    	
    	if (  floatParameterValue != null && floatParameterValue.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "FloatParameterValue already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignFloatParameterValueToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand" );

    	if (  floatParameterValue == null )
    		throw new ProcessingException( "FloatParameterValue already has nothing assigned." );  

    	apply(new UnAssignFloatParameterValueFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignIntegerParameterValueToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignIntegerParameterValueToProprietaryParameterDynamicsCommand" );
    	
    	if (  integerParameterValue != null && integerParameterValue.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "IntegerParameterValue already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignIntegerParameterValueToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand" );

    	if (  integerParameterValue == null )
    		throw new ProcessingException( "IntegerParameterValue already has nothing assigned." );  

    	apply(new UnAssignIntegerParameterValueFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignParameterNumberToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignParameterNumberToProprietaryParameterDynamicsCommand" );
    	
    	if (  parameterNumber != null && parameterNumber.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "ParameterNumber already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignParameterNumberToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignParameterNumberFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignParameterNumberFromProprietaryParameterDynamicsCommand" );

    	if (  parameterNumber == null )
    		throw new ProcessingException( "ParameterNumber already has nothing assigned." );  

    	apply(new UnAssignParameterNumberFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  pFVArControllerType1UserDefined != null && pFVArControllerType1UserDefined.getPFVArControllerType1UserDefinedId() == command.getAssignment().getPFVArControllerType1UserDefinedId() )
    		throw new ProcessingException( "PFVArControllerType1UserDefined already assigned with id " + command.getAssignment().getPFVArControllerType1UserDefinedId() );  
    		
        apply(new AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  pFVArControllerType1UserDefined == null )
    		throw new ProcessingException( "PFVArControllerType1UserDefined already has nothing assigned." );  

    	apply(new UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  voltageCompensatorUserDefined != null && voltageCompensatorUserDefined.getVoltageCompensatorUserDefinedId() == command.getAssignment().getVoltageCompensatorUserDefinedId() )
    		throw new ProcessingException( "VoltageCompensatorUserDefined already assigned with id " + command.getAssignment().getVoltageCompensatorUserDefinedId() );  
    		
        apply(new AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  voltageCompensatorUserDefined == null )
    		throw new ProcessingException( "VoltageCompensatorUserDefined already has nothing assigned." );  

    	apply(new UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  mechanicalLoadUserDefined != null && mechanicalLoadUserDefined.getMechanicalLoadUserDefinedId() == command.getAssignment().getMechanicalLoadUserDefinedId() )
    		throw new ProcessingException( "MechanicalLoadUserDefined already assigned with id " + command.getAssignment().getMechanicalLoadUserDefinedId() );  
    		
        apply(new AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  mechanicalLoadUserDefined == null )
    		throw new ProcessingException( "MechanicalLoadUserDefined already has nothing assigned." );  

    	apply(new UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  excitationSystemUserDefined != null && excitationSystemUserDefined.getExcitationSystemUserDefinedId() == command.getAssignment().getExcitationSystemUserDefinedId() )
    		throw new ProcessingException( "ExcitationSystemUserDefined already assigned with id " + command.getAssignment().getExcitationSystemUserDefinedId() );  
    		
        apply(new AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  excitationSystemUserDefined == null )
    		throw new ProcessingException( "ExcitationSystemUserDefined already has nothing assigned." );  

    	apply(new UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  asynchronousMachineUserDefined != null && asynchronousMachineUserDefined.getAsynchronousMachineUserDefinedId() == command.getAssignment().getAsynchronousMachineUserDefinedId() )
    		throw new ProcessingException( "AsynchronousMachineUserDefined already assigned with id " + command.getAssignment().getAsynchronousMachineUserDefinedId() );  
    		
        apply(new AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  asynchronousMachineUserDefined == null )
    		throw new ProcessingException( "AsynchronousMachineUserDefined already has nothing assigned." );  

    	apply(new UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  discontinuousExcitationControlUserDefined != null && discontinuousExcitationControlUserDefined.getDiscontinuousExcitationControlUserDefinedId() == command.getAssignment().getDiscontinuousExcitationControlUserDefinedId() )
    		throw new ProcessingException( "DiscontinuousExcitationControlUserDefined already assigned with id " + command.getAssignment().getDiscontinuousExcitationControlUserDefinedId() );  
    		
        apply(new AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  discontinuousExcitationControlUserDefined == null )
    		throw new ProcessingException( "DiscontinuousExcitationControlUserDefined already has nothing assigned." );  

    	apply(new UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  turbineGovernorUserDefined != null && turbineGovernorUserDefined.getTurbineGovernorUserDefinedId() == command.getAssignment().getTurbineGovernorUserDefinedId() )
    		throw new ProcessingException( "TurbineGovernorUserDefined already assigned with id " + command.getAssignment().getTurbineGovernorUserDefinedId() );  
    		
        apply(new AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  turbineGovernorUserDefined == null )
    		throw new ProcessingException( "TurbineGovernorUserDefined already has nothing assigned." );  

    	apply(new UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  voltageAdjusterUserDefined != null && voltageAdjusterUserDefined.getVoltageAdjusterUserDefinedId() == command.getAssignment().getVoltageAdjusterUserDefinedId() )
    		throw new ProcessingException( "VoltageAdjusterUserDefined already assigned with id " + command.getAssignment().getVoltageAdjusterUserDefinedId() );  
    		
        apply(new AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  voltageAdjusterUserDefined == null )
    		throw new ProcessingException( "VoltageAdjusterUserDefined already has nothing assigned." );  

    	apply(new UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  synchronousMachineUserDefined != null && synchronousMachineUserDefined.getSynchronousMachineUserDefinedId() == command.getAssignment().getSynchronousMachineUserDefinedId() )
    		throw new ProcessingException( "SynchronousMachineUserDefined already assigned with id " + command.getAssignment().getSynchronousMachineUserDefinedId() );  
    		
        apply(new AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  synchronousMachineUserDefined == null )
    		throw new ProcessingException( "SynchronousMachineUserDefined already has nothing assigned." );  

    	apply(new UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  underexcitationLimiterUserDefined != null && underexcitationLimiterUserDefined.getUnderexcitationLimiterUserDefinedId() == command.getAssignment().getUnderexcitationLimiterUserDefinedId() )
    		throw new ProcessingException( "UnderexcitationLimiterUserDefined already assigned with id " + command.getAssignment().getUnderexcitationLimiterUserDefinedId() );  
    		
        apply(new AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  underexcitationLimiterUserDefined == null )
    		throw new ProcessingException( "UnderexcitationLimiterUserDefined already has nothing assigned." );  

    	apply(new UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  turbineLoadControllerUserDefined != null && turbineLoadControllerUserDefined.getTurbineLoadControllerUserDefinedId() == command.getAssignment().getTurbineLoadControllerUserDefinedId() )
    		throw new ProcessingException( "TurbineLoadControllerUserDefined already assigned with id " + command.getAssignment().getTurbineLoadControllerUserDefinedId() );  
    		
        apply(new AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  turbineLoadControllerUserDefined == null )
    		throw new ProcessingException( "TurbineLoadControllerUserDefined already has nothing assigned." );  

    	apply(new UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  overexcitationLimiterUserDefined != null && overexcitationLimiterUserDefined.getOverexcitationLimiterUserDefinedId() == command.getAssignment().getOverexcitationLimiterUserDefinedId() )
    		throw new ProcessingException( "OverexcitationLimiterUserDefined already assigned with id " + command.getAssignment().getOverexcitationLimiterUserDefinedId() );  
    		
        apply(new AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  overexcitationLimiterUserDefined == null )
    		throw new ProcessingException( "OverexcitationLimiterUserDefined already has nothing assigned." );  

    	apply(new UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  powerSystemStabilizerUserDefined != null && powerSystemStabilizerUserDefined.getPowerSystemStabilizerUserDefinedId() == command.getAssignment().getPowerSystemStabilizerUserDefinedId() )
    		throw new ProcessingException( "PowerSystemStabilizerUserDefined already assigned with id " + command.getAssignment().getPowerSystemStabilizerUserDefinedId() );  
    		
        apply(new AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  powerSystemStabilizerUserDefined == null )
    		throw new ProcessingException( "PowerSystemStabilizerUserDefined already has nothing assigned." );  

    	apply(new UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignLoadUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignLoadUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  loadUserDefined != null && loadUserDefined.getLoadUserDefinedId() == command.getAssignment().getLoadUserDefinedId() )
    		throw new ProcessingException( "LoadUserDefined already assigned with id " + command.getAssignment().getLoadUserDefinedId() );  
    		
        apply(new AssignLoadUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignLoadUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignLoadUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  loadUserDefined == null )
    		throw new ProcessingException( "LoadUserDefined already has nothing assigned." );  

    	apply(new UnAssignLoadUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  pFVArControllerType2UserDefined != null && pFVArControllerType2UserDefined.getPFVArControllerType2UserDefinedId() == command.getAssignment().getPFVArControllerType2UserDefinedId() )
    		throw new ProcessingException( "PFVArControllerType2UserDefined already assigned with id " + command.getAssignment().getPFVArControllerType2UserDefinedId() );  
    		
        apply(new AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  pFVArControllerType2UserDefined == null )
    		throw new ProcessingException( "PFVArControllerType2UserDefined already has nothing assigned." );  

    	apply(new UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignWindType3or4UserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindType3or4UserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  windType3or4UserDefined != null && windType3or4UserDefined.getWindType3or4UserDefinedId() == command.getAssignment().getWindType3or4UserDefinedId() )
    		throw new ProcessingException( "WindType3or4UserDefined already assigned with id " + command.getAssignment().getWindType3or4UserDefinedId() );  
    		
        apply(new AssignWindType3or4UserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  windType3or4UserDefined == null )
    		throw new ProcessingException( "WindType3or4UserDefined already has nothing assigned." );  

    	apply(new UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignWindPlantUserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindPlantUserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  windPlantUserDefined != null && windPlantUserDefined.getWindPlantUserDefinedId() == command.getAssignment().getWindPlantUserDefinedId() )
    		throw new ProcessingException( "WindPlantUserDefined already assigned with id " + command.getAssignment().getWindPlantUserDefinedId() );  
    		
        apply(new AssignWindPlantUserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  windPlantUserDefined == null )
    		throw new ProcessingException( "WindPlantUserDefined already has nothing assigned." );  

    	apply(new UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }
    @CommandHandler
    public void handle(AssignWindType1or2UserDefinedToProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignWindType1or2UserDefinedToProprietaryParameterDynamicsCommand" );
    	
    	if (  windType1or2UserDefined != null && windType1or2UserDefined.getWindType1or2UserDefinedId() == command.getAssignment().getWindType1or2UserDefinedId() )
    		throw new ProcessingException( "WindType1or2UserDefined already assigned with id " + command.getAssignment().getWindType1or2UserDefinedId() );  
    		
        apply(new AssignWindType1or2UserDefinedToProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsCommand" );

    	if (  windType1or2UserDefined == null )
    		throw new ProcessingException( "WindType1or2UserDefined already has nothing assigned." );  

    	apply(new UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsEvent(command.getProprietaryParameterDynamicsId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateProprietaryParameterDynamicsEvent event) {	
    	LOGGER.info( "Event sourcing CreateProprietaryParameterDynamicsEvent" );
    	this.proprietaryParameterDynamicsId = event.getProprietaryParameterDynamicsId();
    }
    
    @EventSourcingHandler
    void on(UpdateProprietaryParameterDynamicsEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.booleanParameterValue = event.getBooleanParameterValue();
        this.floatParameterValue = event.getFloatParameterValue();
        this.integerParameterValue = event.getIntegerParameterValue();
        this.parameterNumber = event.getParameterNumber();
        this.pFVArControllerType1UserDefined = event.getPFVArControllerType1UserDefined();
        this.voltageCompensatorUserDefined = event.getVoltageCompensatorUserDefined();
        this.mechanicalLoadUserDefined = event.getMechanicalLoadUserDefined();
        this.excitationSystemUserDefined = event.getExcitationSystemUserDefined();
        this.asynchronousMachineUserDefined = event.getAsynchronousMachineUserDefined();
        this.discontinuousExcitationControlUserDefined = event.getDiscontinuousExcitationControlUserDefined();
        this.turbineGovernorUserDefined = event.getTurbineGovernorUserDefined();
        this.voltageAdjusterUserDefined = event.getVoltageAdjusterUserDefined();
        this.synchronousMachineUserDefined = event.getSynchronousMachineUserDefined();
        this.underexcitationLimiterUserDefined = event.getUnderexcitationLimiterUserDefined();
        this.turbineLoadControllerUserDefined = event.getTurbineLoadControllerUserDefined();
        this.overexcitationLimiterUserDefined = event.getOverexcitationLimiterUserDefined();
        this.powerSystemStabilizerUserDefined = event.getPowerSystemStabilizerUserDefined();
        this.loadUserDefined = event.getLoadUserDefined();
        this.pFVArControllerType2UserDefined = event.getPFVArControllerType2UserDefined();
        this.windType3or4UserDefined = event.getWindType3or4UserDefined();
        this.windPlantUserDefined = event.getWindPlantUserDefined();
        this.windType1or2UserDefined = event.getWindType1or2UserDefined();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignBooleanParameterValueToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignBooleanParameterValueToProprietaryParameterDynamicsEvent" );
    	this.booleanParameterValue = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignBooleanParameterValueFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignBooleanParameterValueFromProprietaryParameterDynamicsEvent" );
		this.booleanParameterValue = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignFloatParameterValueToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignFloatParameterValueToProprietaryParameterDynamicsEvent" );
    	this.floatParameterValue = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignFloatParameterValueFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignFloatParameterValueFromProprietaryParameterDynamicsEvent" );
		this.floatParameterValue = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignIntegerParameterValueToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignIntegerParameterValueToProprietaryParameterDynamicsEvent" );
    	this.integerParameterValue = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignIntegerParameterValueFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignIntegerParameterValueFromProprietaryParameterDynamicsEvent" );
		this.integerParameterValue = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignParameterNumberToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignParameterNumberToProprietaryParameterDynamicsEvent" );
    	this.parameterNumber = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignParameterNumberFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignParameterNumberFromProprietaryParameterDynamicsEvent" );
		this.parameterNumber = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsEvent" );
    	this.pFVArControllerType1UserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsEvent" );
		this.pFVArControllerType1UserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.voltageCompensatorUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.voltageCompensatorUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.mechanicalLoadUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.mechanicalLoadUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.excitationSystemUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.excitationSystemUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.asynchronousMachineUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.asynchronousMachineUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.discontinuousExcitationControlUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.discontinuousExcitationControlUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.turbineGovernorUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.turbineGovernorUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.voltageAdjusterUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.voltageAdjusterUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.synchronousMachineUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.synchronousMachineUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.underexcitationLimiterUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.underexcitationLimiterUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.turbineLoadControllerUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.turbineLoadControllerUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.overexcitationLimiterUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.overexcitationLimiterUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.powerSystemStabilizerUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.powerSystemStabilizerUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignLoadUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignLoadUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.loadUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignLoadUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignLoadUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.loadUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsEvent" );
    	this.pFVArControllerType2UserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsEvent" );
		this.pFVArControllerType2UserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindType3or4UserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindType3or4UserDefinedToProprietaryParameterDynamicsEvent" );
    	this.windType3or4UserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsEvent" );
		this.windType3or4UserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindPlantUserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindPlantUserDefinedToProprietaryParameterDynamicsEvent" );
    	this.windPlantUserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsEvent" );
		this.windPlantUserDefined = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignWindType1or2UserDefinedToProprietaryParameterDynamicsEvent event ) {	
    	LOGGER.info( "Event sourcing AssignWindType1or2UserDefinedToProprietaryParameterDynamicsEvent" );
    	this.windType1or2UserDefined = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsEvent" );
		this.windType1or2UserDefined = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID proprietaryParameterDynamicsId;
    
    private BooleanProxy booleanParameterValue = null;
    private Simple_Float floatParameterValue = null;
    private IntegerProxy integerParameterValue = null;
    private IntegerProxy parameterNumber = null;
    private PFVArControllerType1UserDefined pFVArControllerType1UserDefined = null;
    private VoltageCompensatorUserDefined voltageCompensatorUserDefined = null;
    private MechanicalLoadUserDefined mechanicalLoadUserDefined = null;
    private ExcitationSystemUserDefined excitationSystemUserDefined = null;
    private AsynchronousMachineUserDefined asynchronousMachineUserDefined = null;
    private DiscontinuousExcitationControlUserDefined discontinuousExcitationControlUserDefined = null;
    private TurbineGovernorUserDefined turbineGovernorUserDefined = null;
    private VoltageAdjusterUserDefined voltageAdjusterUserDefined = null;
    private SynchronousMachineUserDefined synchronousMachineUserDefined = null;
    private UnderexcitationLimiterUserDefined underexcitationLimiterUserDefined = null;
    private TurbineLoadControllerUserDefined turbineLoadControllerUserDefined = null;
    private OverexcitationLimiterUserDefined overexcitationLimiterUserDefined = null;
    private PowerSystemStabilizerUserDefined powerSystemStabilizerUserDefined = null;
    private LoadUserDefined loadUserDefined = null;
    private PFVArControllerType2UserDefined pFVArControllerType2UserDefined = null;
    private WindType3or4UserDefined windType3or4UserDefined = null;
    private WindPlantUserDefined windPlantUserDefined = null;
    private WindType1or2UserDefined windType1or2UserDefined = null;

    private static final Logger LOGGER 	= Logger.getLogger(ProprietaryParameterDynamicsAggregate.class.getName());
}
