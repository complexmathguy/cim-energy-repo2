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

public class DiscreteValueValidator {
		
	/**
	 * default constructor
	 */
	protected DiscreteValueValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public DiscreteValueValidator getInstance() {
		return new DiscreteValueValidator();
	}
		
	/**
	 * handles creation validation for a DiscreteValue
	 */
	public void validate( CreateDiscreteValueCommand discreteValue )throws Exception {
		Assert.notNull( discreteValue, "CreateDiscreteValueCommand should not be null" );
//		Assert.isNull( discreteValue.getDiscreteValueId(), "CreateDiscreteValueCommand identifier should be null" );
	}

	/**
	 * handles update validation for a DiscreteValue
	 */
	public void validate( UpdateDiscreteValueCommand discreteValue ) throws Exception {
		Assert.notNull( discreteValue, "UpdateDiscreteValueCommand should not be null" );
		Assert.notNull( discreteValue.getDiscreteValueId(), "UpdateDiscreteValueCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a DiscreteValue
	 */
    public void validate( DeleteDiscreteValueCommand discreteValue ) throws Exception {
		Assert.notNull( discreteValue, "{commandAlias} should not be null" );
		Assert.notNull( discreteValue.getDiscreteValueId(), "DeleteDiscreteValueCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a DiscreteValue
	 */
	public void validate( DiscreteValueFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "DiscreteValueFetchOneSummary should not be null" );
		Assert.notNull( summary.getDiscreteValueId(), "DiscreteValueFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Value validation for a DiscreteValue
	 * 
	 * @param	command AssignValueToDiscreteValueCommand
	 */	
	public void validate( AssignValueToDiscreteValueCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueToDiscreteValueCommand should not be null" );
		Assert.notNull( command.getDiscreteValueId(), "AssignValueToDiscreteValueCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignValueToDiscreteValueCommand assignment should not be null" );
	}

	/**
	 * handles unassign Value validation for a DiscreteValue
	 * 
	 * @param	command UnAssignValueFromDiscreteValueCommand
	 */	
	public void validate( UnAssignValueFromDiscreteValueCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignValueFromDiscreteValueCommand should not be null" );
		Assert.notNull( command.getDiscreteValueId(), "UnAssignValueFromDiscreteValueCommand identifier should not be null" );
	}
	/**
	 * handles assign Discrete validation for a DiscreteValue
	 * 
	 * @param	command AssignDiscreteToDiscreteValueCommand
	 */	
	public void validate( AssignDiscreteToDiscreteValueCommand command ) throws Exception {
		Assert.notNull( command, "AssignDiscreteToDiscreteValueCommand should not be null" );
		Assert.notNull( command.getDiscreteValueId(), "AssignDiscreteToDiscreteValueCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDiscreteToDiscreteValueCommand assignment should not be null" );
	}

	/**
	 * handles unassign Discrete validation for a DiscreteValue
	 * 
	 * @param	command UnAssignDiscreteFromDiscreteValueCommand
	 */	
	public void validate( UnAssignDiscreteFromDiscreteValueCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDiscreteFromDiscreteValueCommand should not be null" );
		Assert.notNull( command.getDiscreteValueId(), "UnAssignDiscreteFromDiscreteValueCommand identifier should not be null" );
	}


}
