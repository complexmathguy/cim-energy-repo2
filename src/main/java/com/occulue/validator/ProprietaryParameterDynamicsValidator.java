/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.validator;

import org.springframework.util.Assert;

import com.occulue.api.*;

public class ProprietaryParameterDynamicsValidator {
		
	/**
	 * default constructor
	 */
	protected ProprietaryParameterDynamicsValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public ProprietaryParameterDynamicsValidator getInstance() {
		return new ProprietaryParameterDynamicsValidator();
	}
		
	/**
	 * handles creation validation for a ProprietaryParameterDynamics
	 */
	public void validate( CreateProprietaryParameterDynamicsCommand proprietaryParameterDynamics )throws Exception {
		Assert.notNull( proprietaryParameterDynamics, "CreateProprietaryParameterDynamicsCommand should not be null" );
//		Assert.isNull( proprietaryParameterDynamics.getProprietaryParameterDynamicsId(), "CreateProprietaryParameterDynamicsCommand identifier should be null" );
	}

	/**
	 * handles update validation for a ProprietaryParameterDynamics
	 */
	public void validate( UpdateProprietaryParameterDynamicsCommand proprietaryParameterDynamics ) throws Exception {
		Assert.notNull( proprietaryParameterDynamics, "UpdateProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( proprietaryParameterDynamics.getProprietaryParameterDynamicsId(), "UpdateProprietaryParameterDynamicsCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a ProprietaryParameterDynamics
	 */
    public void validate( DeleteProprietaryParameterDynamicsCommand proprietaryParameterDynamics ) throws Exception {
		Assert.notNull( proprietaryParameterDynamics, "{commandAlias} should not be null" );
		Assert.notNull( proprietaryParameterDynamics.getProprietaryParameterDynamicsId(), "DeleteProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a ProprietaryParameterDynamics
	 */
	public void validate( ProprietaryParameterDynamicsFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "ProprietaryParameterDynamicsFetchOneSummary should not be null" );
		Assert.notNull( summary.getProprietaryParameterDynamicsId(), "ProprietaryParameterDynamicsFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign BooleanParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignBooleanParameterValueToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignBooleanParameterValueToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignBooleanParameterValueToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignBooleanParameterValueToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignBooleanParameterValueToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign BooleanParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign FloatParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignFloatParameterValueToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignFloatParameterValueToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignFloatParameterValueToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignFloatParameterValueToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignFloatParameterValueToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign FloatParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign IntegerParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignIntegerParameterValueToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignIntegerParameterValueToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignIntegerParameterValueToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignIntegerParameterValueToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignIntegerParameterValueToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign IntegerParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign ParameterNumber validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignParameterNumberToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignParameterNumberToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignParameterNumberToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignParameterNumberToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignParameterNumberToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign ParameterNumber validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignParameterNumberFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignParameterNumberFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignParameterNumberFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignParameterNumberFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign PFVArControllerType1UserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign PFVArControllerType1UserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign VoltageCompensatorUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign VoltageCompensatorUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign MechanicalLoadUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign MechanicalLoadUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign ExcitationSystemUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign ExcitationSystemUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign AsynchronousMachineUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign AsynchronousMachineUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign DiscontinuousExcitationControlUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign DiscontinuousExcitationControlUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign TurbineGovernorUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign TurbineGovernorUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign VoltageAdjusterUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign VoltageAdjusterUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign SynchronousMachineUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign SynchronousMachineUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign UnderexcitationLimiterUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign UnderexcitationLimiterUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign TurbineLoadControllerUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign TurbineLoadControllerUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign OverexcitationLimiterUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign OverexcitationLimiterUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign PowerSystemStabilizerUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign PowerSystemStabilizerUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign LoadUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignLoadUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignLoadUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignLoadUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignLoadUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignLoadUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign LoadUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignLoadUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignLoadUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignLoadUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignLoadUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign PFVArControllerType2UserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign PFVArControllerType2UserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign WindType3or4UserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignWindType3or4UserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignWindType3or4UserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindType3or4UserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignWindType3or4UserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindType3or4UserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindType3or4UserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign WindPlantUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignWindPlantUserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignWindPlantUserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindPlantUserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignWindPlantUserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindPlantUserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindPlantUserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign WindType1or2UserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignWindType1or2UserDefinedToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignWindType1or2UserDefinedToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindType1or2UserDefinedToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignWindType1or2UserDefinedToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindType1or2UserDefinedToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindType1or2UserDefined validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}


}
