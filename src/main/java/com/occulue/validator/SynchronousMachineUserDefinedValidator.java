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

public class SynchronousMachineUserDefinedValidator {
		
	/**
	 * default constructor
	 */
	protected SynchronousMachineUserDefinedValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public SynchronousMachineUserDefinedValidator getInstance() {
		return new SynchronousMachineUserDefinedValidator();
	}
		
	/**
	 * handles creation validation for a SynchronousMachineUserDefined
	 */
	public void validate( CreateSynchronousMachineUserDefinedCommand synchronousMachineUserDefined )throws Exception {
		Assert.notNull( synchronousMachineUserDefined, "CreateSynchronousMachineUserDefinedCommand should not be null" );
//		Assert.isNull( synchronousMachineUserDefined.getSynchronousMachineUserDefinedId(), "CreateSynchronousMachineUserDefinedCommand identifier should be null" );
	}

	/**
	 * handles update validation for a SynchronousMachineUserDefined
	 */
	public void validate( UpdateSynchronousMachineUserDefinedCommand synchronousMachineUserDefined ) throws Exception {
		Assert.notNull( synchronousMachineUserDefined, "UpdateSynchronousMachineUserDefinedCommand should not be null" );
		Assert.notNull( synchronousMachineUserDefined.getSynchronousMachineUserDefinedId(), "UpdateSynchronousMachineUserDefinedCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a SynchronousMachineUserDefined
	 */
    public void validate( DeleteSynchronousMachineUserDefinedCommand synchronousMachineUserDefined ) throws Exception {
		Assert.notNull( synchronousMachineUserDefined, "{commandAlias} should not be null" );
		Assert.notNull( synchronousMachineUserDefined.getSynchronousMachineUserDefinedId(), "DeleteSynchronousMachineUserDefinedCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a SynchronousMachineUserDefined
	 */
	public void validate( SynchronousMachineUserDefinedFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "SynchronousMachineUserDefinedFetchOneSummary should not be null" );
		Assert.notNull( summary.getSynchronousMachineUserDefinedId(), "SynchronousMachineUserDefinedFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Proprietary validation for a SynchronousMachineUserDefined
	 * 
	 * @param	command AssignProprietaryToSynchronousMachineUserDefinedCommand
	 */	
	public void validate( AssignProprietaryToSynchronousMachineUserDefinedCommand command ) throws Exception {
		Assert.notNull( command, "AssignProprietaryToSynchronousMachineUserDefinedCommand should not be null" );
		Assert.notNull( command.getSynchronousMachineUserDefinedId(), "AssignProprietaryToSynchronousMachineUserDefinedCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignProprietaryToSynchronousMachineUserDefinedCommand assignment should not be null" );
	}

	/**
	 * handles unassign Proprietary validation for a SynchronousMachineUserDefined
	 * 
	 * @param	command UnAssignProprietaryFromSynchronousMachineUserDefinedCommand
	 */	
	public void validate( UnAssignProprietaryFromSynchronousMachineUserDefinedCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignProprietaryFromSynchronousMachineUserDefinedCommand should not be null" );
		Assert.notNull( command.getSynchronousMachineUserDefinedId(), "UnAssignProprietaryFromSynchronousMachineUserDefinedCommand identifier should not be null" );
	}


}
