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

public class AnalogLimitValidator {
		
	/**
	 * default constructor
	 */
	protected AnalogLimitValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public AnalogLimitValidator getInstance() {
		return new AnalogLimitValidator();
	}
		
	/**
	 * handles creation validation for a AnalogLimit
	 */
	public void validate( CreateAnalogLimitCommand analogLimit )throws Exception {
		Assert.notNull( analogLimit, "CreateAnalogLimitCommand should not be null" );
//		Assert.isNull( analogLimit.getAnalogLimitId(), "CreateAnalogLimitCommand identifier should be null" );
	}

	/**
	 * handles update validation for a AnalogLimit
	 */
	public void validate( UpdateAnalogLimitCommand analogLimit ) throws Exception {
		Assert.notNull( analogLimit, "UpdateAnalogLimitCommand should not be null" );
		Assert.notNull( analogLimit.getAnalogLimitId(), "UpdateAnalogLimitCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a AnalogLimit
	 */
    public void validate( DeleteAnalogLimitCommand analogLimit ) throws Exception {
		Assert.notNull( analogLimit, "{commandAlias} should not be null" );
		Assert.notNull( analogLimit.getAnalogLimitId(), "DeleteAnalogLimitCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a AnalogLimit
	 */
	public void validate( AnalogLimitFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "AnalogLimitFetchOneSummary should not be null" );
		Assert.notNull( summary.getAnalogLimitId(), "AnalogLimitFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Value validation for a AnalogLimit
	 * 
	 * @param	command AssignValueToAnalogLimitCommand
	 */	
	public void validate( AssignValueToAnalogLimitCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueToAnalogLimitCommand should not be null" );
		Assert.notNull( command.getAnalogLimitId(), "AssignValueToAnalogLimitCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignValueToAnalogLimitCommand assignment should not be null" );
	}

	/**
	 * handles unassign Value validation for a AnalogLimit
	 * 
	 * @param	command UnAssignValueFromAnalogLimitCommand
	 */	
	public void validate( UnAssignValueFromAnalogLimitCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignValueFromAnalogLimitCommand should not be null" );
		Assert.notNull( command.getAnalogLimitId(), "UnAssignValueFromAnalogLimitCommand identifier should not be null" );
	}

	/**
	 * handles add to LimitSet validation for a AnalogLimit
	 * 
	 * @param	command AssignLimitSetToAnalogLimitCommand
	 */	
	public void validate( AssignLimitSetToAnalogLimitCommand command ) throws Exception {
		Assert.notNull( command, "AssignLimitSetToAnalogLimitCommand should not be null" );
		Assert.notNull( command.getAnalogLimitId(), "AssignLimitSetToAnalogLimitCommand identifier should not be null" );
		Assert.notNull( command.getAddTo(), "AssignLimitSetToAnalogLimitCommand addTo attribute should not be null" );
	}

	/**
	 * handles remove from LimitSet validation for a AnalogLimit
	 * 
	 * @param	command RemoveLimitSetFromAnalogLimitCommand
	 */	
	public void validate( RemoveLimitSetFromAnalogLimitCommand command ) throws Exception {
		Assert.notNull( command, "RemoveLimitSetFromAnalogLimitCommand should not be null" );
		Assert.notNull( command.getAnalogLimitId(), "RemoveLimitSetFromAnalogLimitCommand identifier should not be null" );
		Assert.notNull( command.getRemoveFrom(), "RemoveLimitSetFromAnalogLimitCommand removeFrom attribute should not be null" );
		Assert.notNull( command.getRemoveFrom().getAnalogLimitSetId(), "RemoveLimitSetFromAnalogLimitCommand removeFrom attribubte identifier should not be null" );
	}
	

}
