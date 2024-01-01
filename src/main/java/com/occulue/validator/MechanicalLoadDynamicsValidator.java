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

public class MechanicalLoadDynamicsValidator {
		
	/**
	 * default constructor
	 */
	protected MechanicalLoadDynamicsValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public MechanicalLoadDynamicsValidator getInstance() {
		return new MechanicalLoadDynamicsValidator();
	}
		
	/**
	 * handles creation validation for a MechanicalLoadDynamics
	 */
	public void validate( CreateMechanicalLoadDynamicsCommand mechanicalLoadDynamics )throws Exception {
		Assert.notNull( mechanicalLoadDynamics, "CreateMechanicalLoadDynamicsCommand should not be null" );
//		Assert.isNull( mechanicalLoadDynamics.getMechanicalLoadDynamicsId(), "CreateMechanicalLoadDynamicsCommand identifier should be null" );
	}

	/**
	 * handles update validation for a MechanicalLoadDynamics
	 */
	public void validate( UpdateMechanicalLoadDynamicsCommand mechanicalLoadDynamics ) throws Exception {
		Assert.notNull( mechanicalLoadDynamics, "UpdateMechanicalLoadDynamicsCommand should not be null" );
		Assert.notNull( mechanicalLoadDynamics.getMechanicalLoadDynamicsId(), "UpdateMechanicalLoadDynamicsCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a MechanicalLoadDynamics
	 */
    public void validate( DeleteMechanicalLoadDynamicsCommand mechanicalLoadDynamics ) throws Exception {
		Assert.notNull( mechanicalLoadDynamics, "{commandAlias} should not be null" );
		Assert.notNull( mechanicalLoadDynamics.getMechanicalLoadDynamicsId(), "DeleteMechanicalLoadDynamicsCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a MechanicalLoadDynamics
	 */
	public void validate( MechanicalLoadDynamicsFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "MechanicalLoadDynamicsFetchOneSummary should not be null" );
		Assert.notNull( summary.getMechanicalLoadDynamicsId(), "MechanicalLoadDynamicsFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign SynchronousMachineDynamics validation for a MechanicalLoadDynamics
	 * 
	 * @param	command AssignSynchronousMachineDynamicsToMechanicalLoadDynamicsCommand
	 */	
	public void validate( AssignSynchronousMachineDynamicsToMechanicalLoadDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignSynchronousMachineDynamicsToMechanicalLoadDynamicsCommand should not be null" );
		Assert.notNull( command.getMechanicalLoadDynamicsId(), "AssignSynchronousMachineDynamicsToMechanicalLoadDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSynchronousMachineDynamicsToMechanicalLoadDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign SynchronousMachineDynamics validation for a MechanicalLoadDynamics
	 * 
	 * @param	command UnAssignSynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand
	 */	
	public void validate( UnAssignSynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand should not be null" );
		Assert.notNull( command.getMechanicalLoadDynamicsId(), "UnAssignSynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign AsynchronousMachineDynamics validation for a MechanicalLoadDynamics
	 * 
	 * @param	command AssignAsynchronousMachineDynamicsToMechanicalLoadDynamicsCommand
	 */	
	public void validate( AssignAsynchronousMachineDynamicsToMechanicalLoadDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignAsynchronousMachineDynamicsToMechanicalLoadDynamicsCommand should not be null" );
		Assert.notNull( command.getMechanicalLoadDynamicsId(), "AssignAsynchronousMachineDynamicsToMechanicalLoadDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignAsynchronousMachineDynamicsToMechanicalLoadDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign AsynchronousMachineDynamics validation for a MechanicalLoadDynamics
	 * 
	 * @param	command UnAssignAsynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand
	 */	
	public void validate( UnAssignAsynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignAsynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand should not be null" );
		Assert.notNull( command.getMechanicalLoadDynamicsId(), "UnAssignAsynchronousMachineDynamicsFromMechanicalLoadDynamicsCommand identifier should not be null" );
	}


}
