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

public class PerCentValidator {
		
	/**
	 * default constructor
	 */
	protected PerCentValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public PerCentValidator getInstance() {
		return new PerCentValidator();
	}
		
	/**
	 * handles creation validation for a PerCent
	 */
	public void validate( CreatePerCentCommand perCent )throws Exception {
		Assert.notNull( perCent, "CreatePerCentCommand should not be null" );
//		Assert.isNull( perCent.getPerCentId(), "CreatePerCentCommand identifier should be null" );
	}

	/**
	 * handles update validation for a PerCent
	 */
	public void validate( UpdatePerCentCommand perCent ) throws Exception {
		Assert.notNull( perCent, "UpdatePerCentCommand should not be null" );
		Assert.notNull( perCent.getPerCentId(), "UpdatePerCentCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a PerCent
	 */
    public void validate( DeletePerCentCommand perCent ) throws Exception {
		Assert.notNull( perCent, "{commandAlias} should not be null" );
		Assert.notNull( perCent.getPerCentId(), "DeletePerCentCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a PerCent
	 */
	public void validate( PerCentFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "PerCentFetchOneSummary should not be null" );
		Assert.notNull( summary.getPerCentId(), "PerCentFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Value validation for a PerCent
	 * 
	 * @param	command AssignValueToPerCentCommand
	 */	
	public void validate( AssignValueToPerCentCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueToPerCentCommand should not be null" );
		Assert.notNull( command.getPerCentId(), "AssignValueToPerCentCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignValueToPerCentCommand assignment should not be null" );
	}

	/**
	 * handles unassign Value validation for a PerCent
	 * 
	 * @param	command UnAssignValueFromPerCentCommand
	 */	
	public void validate( UnAssignValueFromPerCentCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignValueFromPerCentCommand should not be null" );
		Assert.notNull( command.getPerCentId(), "UnAssignValueFromPerCentCommand identifier should not be null" );
	}


}
