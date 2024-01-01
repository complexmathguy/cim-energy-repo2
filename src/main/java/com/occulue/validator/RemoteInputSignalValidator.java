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

public class RemoteInputSignalValidator {
		
	/**
	 * default constructor
	 */
	protected RemoteInputSignalValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public RemoteInputSignalValidator getInstance() {
		return new RemoteInputSignalValidator();
	}
		
	/**
	 * handles creation validation for a RemoteInputSignal
	 */
	public void validate( CreateRemoteInputSignalCommand remoteInputSignal )throws Exception {
		Assert.notNull( remoteInputSignal, "CreateRemoteInputSignalCommand should not be null" );
//		Assert.isNull( remoteInputSignal.getRemoteInputSignalId(), "CreateRemoteInputSignalCommand identifier should be null" );
	}

	/**
	 * handles update validation for a RemoteInputSignal
	 */
	public void validate( UpdateRemoteInputSignalCommand remoteInputSignal ) throws Exception {
		Assert.notNull( remoteInputSignal, "UpdateRemoteInputSignalCommand should not be null" );
		Assert.notNull( remoteInputSignal.getRemoteInputSignalId(), "UpdateRemoteInputSignalCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a RemoteInputSignal
	 */
    public void validate( DeleteRemoteInputSignalCommand remoteInputSignal ) throws Exception {
		Assert.notNull( remoteInputSignal, "{commandAlias} should not be null" );
		Assert.notNull( remoteInputSignal.getRemoteInputSignalId(), "DeleteRemoteInputSignalCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a RemoteInputSignal
	 */
	public void validate( RemoteInputSignalFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "RemoteInputSignalFetchOneSummary should not be null" );
		Assert.notNull( summary.getRemoteInputSignalId(), "RemoteInputSignalFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Terminal validation for a RemoteInputSignal
	 * 
	 * @param	command AssignTerminalToRemoteInputSignalCommand
	 */	
	public void validate( AssignTerminalToRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "AssignTerminalToRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "AssignTerminalToRemoteInputSignalCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTerminalToRemoteInputSignalCommand assignment should not be null" );
	}

	/**
	 * handles unassign Terminal validation for a RemoteInputSignal
	 * 
	 * @param	command UnAssignTerminalFromRemoteInputSignalCommand
	 */	
	public void validate( UnAssignTerminalFromRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTerminalFromRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "UnAssignTerminalFromRemoteInputSignalCommand identifier should not be null" );
	}
	/**
	 * handles assign DiscontinuousExcitationControlDynamics validation for a RemoteInputSignal
	 * 
	 * @param	command AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalCommand
	 */	
	public void validate( AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalCommand assignment should not be null" );
	}

	/**
	 * handles unassign DiscontinuousExcitationControlDynamics validation for a RemoteInputSignal
	 * 
	 * @param	command UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalCommand
	 */	
	public void validate( UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalCommand identifier should not be null" );
	}
	/**
	 * handles assign PowerSystemStabilizerDynamics validation for a RemoteInputSignal
	 * 
	 * @param	command AssignPowerSystemStabilizerDynamicsToRemoteInputSignalCommand
	 */	
	public void validate( AssignPowerSystemStabilizerDynamicsToRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "AssignPowerSystemStabilizerDynamicsToRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "AssignPowerSystemStabilizerDynamicsToRemoteInputSignalCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPowerSystemStabilizerDynamicsToRemoteInputSignalCommand assignment should not be null" );
	}

	/**
	 * handles unassign PowerSystemStabilizerDynamics validation for a RemoteInputSignal
	 * 
	 * @param	command UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalCommand
	 */	
	public void validate( UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalCommand identifier should not be null" );
	}
	/**
	 * handles assign VoltageCompensatorDynamics validation for a RemoteInputSignal
	 * 
	 * @param	command AssignVoltageCompensatorDynamicsToRemoteInputSignalCommand
	 */	
	public void validate( AssignVoltageCompensatorDynamicsToRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "AssignVoltageCompensatorDynamicsToRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "AssignVoltageCompensatorDynamicsToRemoteInputSignalCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignVoltageCompensatorDynamicsToRemoteInputSignalCommand assignment should not be null" );
	}

	/**
	 * handles unassign VoltageCompensatorDynamics validation for a RemoteInputSignal
	 * 
	 * @param	command UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalCommand
	 */	
	public void validate( UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalCommand identifier should not be null" );
	}
	/**
	 * handles assign UnderexcitationLimiterDynamics validation for a RemoteInputSignal
	 * 
	 * @param	command AssignUnderexcitationLimiterDynamicsToRemoteInputSignalCommand
	 */	
	public void validate( AssignUnderexcitationLimiterDynamicsToRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "AssignUnderexcitationLimiterDynamicsToRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "AssignUnderexcitationLimiterDynamicsToRemoteInputSignalCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignUnderexcitationLimiterDynamicsToRemoteInputSignalCommand assignment should not be null" );
	}

	/**
	 * handles unassign UnderexcitationLimiterDynamics validation for a RemoteInputSignal
	 * 
	 * @param	command UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalCommand
	 */	
	public void validate( UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalCommand identifier should not be null" );
	}
	/**
	 * handles assign PFVArControllerType1Dynamics validation for a RemoteInputSignal
	 * 
	 * @param	command AssignPFVArControllerType1DynamicsToRemoteInputSignalCommand
	 */	
	public void validate( AssignPFVArControllerType1DynamicsToRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "AssignPFVArControllerType1DynamicsToRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "AssignPFVArControllerType1DynamicsToRemoteInputSignalCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPFVArControllerType1DynamicsToRemoteInputSignalCommand assignment should not be null" );
	}

	/**
	 * handles unassign PFVArControllerType1Dynamics validation for a RemoteInputSignal
	 * 
	 * @param	command UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalCommand
	 */	
	public void validate( UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalCommand should not be null" );
		Assert.notNull( command.getRemoteInputSignalId(), "UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalCommand identifier should not be null" );
	}


}
