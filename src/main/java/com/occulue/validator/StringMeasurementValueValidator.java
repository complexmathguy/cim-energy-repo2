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

public class StringMeasurementValueValidator {
		
	/**
	 * default constructor
	 */
	protected StringMeasurementValueValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public StringMeasurementValueValidator getInstance() {
		return new StringMeasurementValueValidator();
	}
		
	/**
	 * handles creation validation for a StringMeasurementValue
	 */
	public void validate( CreateStringMeasurementValueCommand stringMeasurementValue )throws Exception {
		Assert.notNull( stringMeasurementValue, "CreateStringMeasurementValueCommand should not be null" );
//		Assert.isNull( stringMeasurementValue.getStringMeasurementValueId(), "CreateStringMeasurementValueCommand identifier should be null" );
	}

	/**
	 * handles update validation for a StringMeasurementValue
	 */
	public void validate( UpdateStringMeasurementValueCommand stringMeasurementValue ) throws Exception {
		Assert.notNull( stringMeasurementValue, "UpdateStringMeasurementValueCommand should not be null" );
		Assert.notNull( stringMeasurementValue.getStringMeasurementValueId(), "UpdateStringMeasurementValueCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a StringMeasurementValue
	 */
    public void validate( DeleteStringMeasurementValueCommand stringMeasurementValue ) throws Exception {
		Assert.notNull( stringMeasurementValue, "{commandAlias} should not be null" );
		Assert.notNull( stringMeasurementValue.getStringMeasurementValueId(), "DeleteStringMeasurementValueCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a StringMeasurementValue
	 */
	public void validate( StringMeasurementValueFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "StringMeasurementValueFetchOneSummary should not be null" );
		Assert.notNull( summary.getStringMeasurementValueId(), "StringMeasurementValueFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Value validation for a StringMeasurementValue
	 * 
	 * @param	command AssignValueToStringMeasurementValueCommand
	 */	
	public void validate( AssignValueToStringMeasurementValueCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueToStringMeasurementValueCommand should not be null" );
		Assert.notNull( command.getStringMeasurementValueId(), "AssignValueToStringMeasurementValueCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignValueToStringMeasurementValueCommand assignment should not be null" );
	}

	/**
	 * handles unassign Value validation for a StringMeasurementValue
	 * 
	 * @param	command UnAssignValueFromStringMeasurementValueCommand
	 */	
	public void validate( UnAssignValueFromStringMeasurementValueCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignValueFromStringMeasurementValueCommand should not be null" );
		Assert.notNull( command.getStringMeasurementValueId(), "UnAssignValueFromStringMeasurementValueCommand identifier should not be null" );
	}
	/**
	 * handles assign StringMeasurement validation for a StringMeasurementValue
	 * 
	 * @param	command AssignStringMeasurementToStringMeasurementValueCommand
	 */	
	public void validate( AssignStringMeasurementToStringMeasurementValueCommand command ) throws Exception {
		Assert.notNull( command, "AssignStringMeasurementToStringMeasurementValueCommand should not be null" );
		Assert.notNull( command.getStringMeasurementValueId(), "AssignStringMeasurementToStringMeasurementValueCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignStringMeasurementToStringMeasurementValueCommand assignment should not be null" );
	}

	/**
	 * handles unassign StringMeasurement validation for a StringMeasurementValue
	 * 
	 * @param	command UnAssignStringMeasurementFromStringMeasurementValueCommand
	 */	
	public void validate( UnAssignStringMeasurementFromStringMeasurementValueCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignStringMeasurementFromStringMeasurementValueCommand should not be null" );
		Assert.notNull( command.getStringMeasurementValueId(), "UnAssignStringMeasurementFromStringMeasurementValueCommand identifier should not be null" );
	}


}
