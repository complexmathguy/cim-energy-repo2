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

public class ExcitationSystemDynamicsValidator {
		
	/**
	 * default constructor
	 */
	protected ExcitationSystemDynamicsValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public ExcitationSystemDynamicsValidator getInstance() {
		return new ExcitationSystemDynamicsValidator();
	}
		
	/**
	 * handles creation validation for a ExcitationSystemDynamics
	 */
	public void validate( CreateExcitationSystemDynamicsCommand excitationSystemDynamics )throws Exception {
		Assert.notNull( excitationSystemDynamics, "CreateExcitationSystemDynamicsCommand should not be null" );
//		Assert.isNull( excitationSystemDynamics.getExcitationSystemDynamicsId(), "CreateExcitationSystemDynamicsCommand identifier should be null" );
	}

	/**
	 * handles update validation for a ExcitationSystemDynamics
	 */
	public void validate( UpdateExcitationSystemDynamicsCommand excitationSystemDynamics ) throws Exception {
		Assert.notNull( excitationSystemDynamics, "UpdateExcitationSystemDynamicsCommand should not be null" );
		Assert.notNull( excitationSystemDynamics.getExcitationSystemDynamicsId(), "UpdateExcitationSystemDynamicsCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a ExcitationSystemDynamics
	 */
    public void validate( DeleteExcitationSystemDynamicsCommand excitationSystemDynamics ) throws Exception {
		Assert.notNull( excitationSystemDynamics, "{commandAlias} should not be null" );
		Assert.notNull( excitationSystemDynamics.getExcitationSystemDynamicsId(), "DeleteExcitationSystemDynamicsCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a ExcitationSystemDynamics
	 */
	public void validate( ExcitationSystemDynamicsFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "ExcitationSystemDynamicsFetchOneSummary should not be null" );
		Assert.notNull( summary.getExcitationSystemDynamicsId(), "ExcitationSystemDynamicsFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign SynchronousMachineDynamics validation for a ExcitationSystemDynamics
	 * 
	 * @param	command AssignSynchronousMachineDynamicsToExcitationSystemDynamicsCommand
	 */	
	public void validate( AssignSynchronousMachineDynamicsToExcitationSystemDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignSynchronousMachineDynamicsToExcitationSystemDynamicsCommand should not be null" );
		Assert.notNull( command.getExcitationSystemDynamicsId(), "AssignSynchronousMachineDynamicsToExcitationSystemDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSynchronousMachineDynamicsToExcitationSystemDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign SynchronousMachineDynamics validation for a ExcitationSystemDynamics
	 * 
	 * @param	command UnAssignSynchronousMachineDynamicsFromExcitationSystemDynamicsCommand
	 */	
	public void validate( UnAssignSynchronousMachineDynamicsFromExcitationSystemDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSynchronousMachineDynamicsFromExcitationSystemDynamicsCommand should not be null" );
		Assert.notNull( command.getExcitationSystemDynamicsId(), "UnAssignSynchronousMachineDynamicsFromExcitationSystemDynamicsCommand identifier should not be null" );
	}


}
