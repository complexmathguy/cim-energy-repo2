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

public class AsynchronousMachineUserDefinedValidator {
		
	/**
	 * default constructor
	 */
	protected AsynchronousMachineUserDefinedValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public AsynchronousMachineUserDefinedValidator getInstance() {
		return new AsynchronousMachineUserDefinedValidator();
	}
		
	/**
	 * handles creation validation for a AsynchronousMachineUserDefined
	 */
	public void validate( CreateAsynchronousMachineUserDefinedCommand asynchronousMachineUserDefined )throws Exception {
		Assert.notNull( asynchronousMachineUserDefined, "CreateAsynchronousMachineUserDefinedCommand should not be null" );
//		Assert.isNull( asynchronousMachineUserDefined.getAsynchronousMachineUserDefinedId(), "CreateAsynchronousMachineUserDefinedCommand identifier should be null" );
	}

	/**
	 * handles update validation for a AsynchronousMachineUserDefined
	 */
	public void validate( UpdateAsynchronousMachineUserDefinedCommand asynchronousMachineUserDefined ) throws Exception {
		Assert.notNull( asynchronousMachineUserDefined, "UpdateAsynchronousMachineUserDefinedCommand should not be null" );
		Assert.notNull( asynchronousMachineUserDefined.getAsynchronousMachineUserDefinedId(), "UpdateAsynchronousMachineUserDefinedCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a AsynchronousMachineUserDefined
	 */
    public void validate( DeleteAsynchronousMachineUserDefinedCommand asynchronousMachineUserDefined ) throws Exception {
		Assert.notNull( asynchronousMachineUserDefined, "{commandAlias} should not be null" );
		Assert.notNull( asynchronousMachineUserDefined.getAsynchronousMachineUserDefinedId(), "DeleteAsynchronousMachineUserDefinedCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a AsynchronousMachineUserDefined
	 */
	public void validate( AsynchronousMachineUserDefinedFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "AsynchronousMachineUserDefinedFetchOneSummary should not be null" );
		Assert.notNull( summary.getAsynchronousMachineUserDefinedId(), "AsynchronousMachineUserDefinedFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Proprietary validation for a AsynchronousMachineUserDefined
	 * 
	 * @param	command AssignProprietaryToAsynchronousMachineUserDefinedCommand
	 */	
	public void validate( AssignProprietaryToAsynchronousMachineUserDefinedCommand command ) throws Exception {
		Assert.notNull( command, "AssignProprietaryToAsynchronousMachineUserDefinedCommand should not be null" );
		Assert.notNull( command.getAsynchronousMachineUserDefinedId(), "AssignProprietaryToAsynchronousMachineUserDefinedCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignProprietaryToAsynchronousMachineUserDefinedCommand assignment should not be null" );
	}

	/**
	 * handles unassign Proprietary validation for a AsynchronousMachineUserDefined
	 * 
	 * @param	command UnAssignProprietaryFromAsynchronousMachineUserDefinedCommand
	 */	
	public void validate( UnAssignProprietaryFromAsynchronousMachineUserDefinedCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignProprietaryFromAsynchronousMachineUserDefinedCommand should not be null" );
		Assert.notNull( command.getAsynchronousMachineUserDefinedId(), "UnAssignProprietaryFromAsynchronousMachineUserDefinedCommand identifier should not be null" );
	}


}
