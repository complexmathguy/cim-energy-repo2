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

public class ApparentPowerValidator {
		
	/**
	 * default constructor
	 */
	protected ApparentPowerValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public ApparentPowerValidator getInstance() {
		return new ApparentPowerValidator();
	}
		
	/**
	 * handles creation validation for a ApparentPower
	 */
	public void validate( CreateApparentPowerCommand apparentPower )throws Exception {
		Assert.notNull( apparentPower, "CreateApparentPowerCommand should not be null" );
//		Assert.isNull( apparentPower.getApparentPowerId(), "CreateApparentPowerCommand identifier should be null" );
	}

	/**
	 * handles update validation for a ApparentPower
	 */
	public void validate( UpdateApparentPowerCommand apparentPower ) throws Exception {
		Assert.notNull( apparentPower, "UpdateApparentPowerCommand should not be null" );
		Assert.notNull( apparentPower.getApparentPowerId(), "UpdateApparentPowerCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a ApparentPower
	 */
    public void validate( DeleteApparentPowerCommand apparentPower ) throws Exception {
		Assert.notNull( apparentPower, "{commandAlias} should not be null" );
		Assert.notNull( apparentPower.getApparentPowerId(), "DeleteApparentPowerCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a ApparentPower
	 */
	public void validate( ApparentPowerFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "ApparentPowerFetchOneSummary should not be null" );
		Assert.notNull( summary.getApparentPowerId(), "ApparentPowerFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Value validation for a ApparentPower
	 * 
	 * @param	command AssignValueToApparentPowerCommand
	 */	
	public void validate( AssignValueToApparentPowerCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueToApparentPowerCommand should not be null" );
		Assert.notNull( command.getApparentPowerId(), "AssignValueToApparentPowerCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignValueToApparentPowerCommand assignment should not be null" );
	}

	/**
	 * handles unassign Value validation for a ApparentPower
	 * 
	 * @param	command UnAssignValueFromApparentPowerCommand
	 */	
	public void validate( UnAssignValueFromApparentPowerCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignValueFromApparentPowerCommand should not be null" );
		Assert.notNull( command.getApparentPowerId(), "UnAssignValueFromApparentPowerCommand identifier should not be null" );
	}


}
