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

public class AnalogControlValidator {
		
	/**
	 * default constructor
	 */
	protected AnalogControlValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public AnalogControlValidator getInstance() {
		return new AnalogControlValidator();
	}
		
	/**
	 * handles creation validation for a AnalogControl
	 */
	public void validate( CreateAnalogControlCommand analogControl )throws Exception {
		Assert.notNull( analogControl, "CreateAnalogControlCommand should not be null" );
//		Assert.isNull( analogControl.getAnalogControlId(), "CreateAnalogControlCommand identifier should be null" );
	}

	/**
	 * handles update validation for a AnalogControl
	 */
	public void validate( UpdateAnalogControlCommand analogControl ) throws Exception {
		Assert.notNull( analogControl, "UpdateAnalogControlCommand should not be null" );
		Assert.notNull( analogControl.getAnalogControlId(), "UpdateAnalogControlCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a AnalogControl
	 */
    public void validate( DeleteAnalogControlCommand analogControl ) throws Exception {
		Assert.notNull( analogControl, "{commandAlias} should not be null" );
		Assert.notNull( analogControl.getAnalogControlId(), "DeleteAnalogControlCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a AnalogControl
	 */
	public void validate( AnalogControlFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "AnalogControlFetchOneSummary should not be null" );
		Assert.notNull( summary.getAnalogControlId(), "AnalogControlFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign MaxValue validation for a AnalogControl
	 * 
	 * @param	command AssignMaxValueToAnalogControlCommand
	 */	
	public void validate( AssignMaxValueToAnalogControlCommand command ) throws Exception {
		Assert.notNull( command, "AssignMaxValueToAnalogControlCommand should not be null" );
		Assert.notNull( command.getAnalogControlId(), "AssignMaxValueToAnalogControlCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignMaxValueToAnalogControlCommand assignment should not be null" );
	}

	/**
	 * handles unassign MaxValue validation for a AnalogControl
	 * 
	 * @param	command UnAssignMaxValueFromAnalogControlCommand
	 */	
	public void validate( UnAssignMaxValueFromAnalogControlCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignMaxValueFromAnalogControlCommand should not be null" );
		Assert.notNull( command.getAnalogControlId(), "UnAssignMaxValueFromAnalogControlCommand identifier should not be null" );
	}
	/**
	 * handles assign MinValue validation for a AnalogControl
	 * 
	 * @param	command AssignMinValueToAnalogControlCommand
	 */	
	public void validate( AssignMinValueToAnalogControlCommand command ) throws Exception {
		Assert.notNull( command, "AssignMinValueToAnalogControlCommand should not be null" );
		Assert.notNull( command.getAnalogControlId(), "AssignMinValueToAnalogControlCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignMinValueToAnalogControlCommand assignment should not be null" );
	}

	/**
	 * handles unassign MinValue validation for a AnalogControl
	 * 
	 * @param	command UnAssignMinValueFromAnalogControlCommand
	 */	
	public void validate( UnAssignMinValueFromAnalogControlCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignMinValueFromAnalogControlCommand should not be null" );
		Assert.notNull( command.getAnalogControlId(), "UnAssignMinValueFromAnalogControlCommand identifier should not be null" );
	}
	/**
	 * handles assign AnalogValue validation for a AnalogControl
	 * 
	 * @param	command AssignAnalogValueToAnalogControlCommand
	 */	
	public void validate( AssignAnalogValueToAnalogControlCommand command ) throws Exception {
		Assert.notNull( command, "AssignAnalogValueToAnalogControlCommand should not be null" );
		Assert.notNull( command.getAnalogControlId(), "AssignAnalogValueToAnalogControlCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignAnalogValueToAnalogControlCommand assignment should not be null" );
	}

	/**
	 * handles unassign AnalogValue validation for a AnalogControl
	 * 
	 * @param	command UnAssignAnalogValueFromAnalogControlCommand
	 */	
	public void validate( UnAssignAnalogValueFromAnalogControlCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignAnalogValueFromAnalogControlCommand should not be null" );
		Assert.notNull( command.getAnalogControlId(), "UnAssignAnalogValueFromAnalogControlCommand identifier should not be null" );
	}


}
