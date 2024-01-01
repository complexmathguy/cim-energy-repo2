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

public class TurbineGovernorDynamicsValidator {
		
	/**
	 * default constructor
	 */
	protected TurbineGovernorDynamicsValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public TurbineGovernorDynamicsValidator getInstance() {
		return new TurbineGovernorDynamicsValidator();
	}
		
	/**
	 * handles creation validation for a TurbineGovernorDynamics
	 */
	public void validate( CreateTurbineGovernorDynamicsCommand turbineGovernorDynamics )throws Exception {
		Assert.notNull( turbineGovernorDynamics, "CreateTurbineGovernorDynamicsCommand should not be null" );
//		Assert.isNull( turbineGovernorDynamics.getTurbineGovernorDynamicsId(), "CreateTurbineGovernorDynamicsCommand identifier should be null" );
	}

	/**
	 * handles update validation for a TurbineGovernorDynamics
	 */
	public void validate( UpdateTurbineGovernorDynamicsCommand turbineGovernorDynamics ) throws Exception {
		Assert.notNull( turbineGovernorDynamics, "UpdateTurbineGovernorDynamicsCommand should not be null" );
		Assert.notNull( turbineGovernorDynamics.getTurbineGovernorDynamicsId(), "UpdateTurbineGovernorDynamicsCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a TurbineGovernorDynamics
	 */
    public void validate( DeleteTurbineGovernorDynamicsCommand turbineGovernorDynamics ) throws Exception {
		Assert.notNull( turbineGovernorDynamics, "{commandAlias} should not be null" );
		Assert.notNull( turbineGovernorDynamics.getTurbineGovernorDynamicsId(), "DeleteTurbineGovernorDynamicsCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a TurbineGovernorDynamics
	 */
	public void validate( TurbineGovernorDynamicsFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "TurbineGovernorDynamicsFetchOneSummary should not be null" );
		Assert.notNull( summary.getTurbineGovernorDynamicsId(), "TurbineGovernorDynamicsFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign AsynchronousMachineDynamics validation for a TurbineGovernorDynamics
	 * 
	 * @param	command AssignAsynchronousMachineDynamicsToTurbineGovernorDynamicsCommand
	 */	
	public void validate( AssignAsynchronousMachineDynamicsToTurbineGovernorDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignAsynchronousMachineDynamicsToTurbineGovernorDynamicsCommand should not be null" );
		Assert.notNull( command.getTurbineGovernorDynamicsId(), "AssignAsynchronousMachineDynamicsToTurbineGovernorDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignAsynchronousMachineDynamicsToTurbineGovernorDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign AsynchronousMachineDynamics validation for a TurbineGovernorDynamics
	 * 
	 * @param	command UnAssignAsynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand
	 */	
	public void validate( UnAssignAsynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignAsynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand should not be null" );
		Assert.notNull( command.getTurbineGovernorDynamicsId(), "UnAssignAsynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign SynchronousMachineDynamics validation for a TurbineGovernorDynamics
	 * 
	 * @param	command AssignSynchronousMachineDynamicsToTurbineGovernorDynamicsCommand
	 */	
	public void validate( AssignSynchronousMachineDynamicsToTurbineGovernorDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignSynchronousMachineDynamicsToTurbineGovernorDynamicsCommand should not be null" );
		Assert.notNull( command.getTurbineGovernorDynamicsId(), "AssignSynchronousMachineDynamicsToTurbineGovernorDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSynchronousMachineDynamicsToTurbineGovernorDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign SynchronousMachineDynamics validation for a TurbineGovernorDynamics
	 * 
	 * @param	command UnAssignSynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand
	 */	
	public void validate( UnAssignSynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand should not be null" );
		Assert.notNull( command.getTurbineGovernorDynamicsId(), "UnAssignSynchronousMachineDynamicsFromTurbineGovernorDynamicsCommand identifier should not be null" );
	}


}
