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

public class SvTapStepValidator {
		
	/**
	 * default constructor
	 */
	protected SvTapStepValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public SvTapStepValidator getInstance() {
		return new SvTapStepValidator();
	}
		
	/**
	 * handles creation validation for a SvTapStep
	 */
	public void validate( CreateSvTapStepCommand svTapStep )throws Exception {
		Assert.notNull( svTapStep, "CreateSvTapStepCommand should not be null" );
//		Assert.isNull( svTapStep.getSvTapStepId(), "CreateSvTapStepCommand identifier should be null" );
	}

	/**
	 * handles update validation for a SvTapStep
	 */
	public void validate( UpdateSvTapStepCommand svTapStep ) throws Exception {
		Assert.notNull( svTapStep, "UpdateSvTapStepCommand should not be null" );
		Assert.notNull( svTapStep.getSvTapStepId(), "UpdateSvTapStepCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a SvTapStep
	 */
    public void validate( DeleteSvTapStepCommand svTapStep ) throws Exception {
		Assert.notNull( svTapStep, "{commandAlias} should not be null" );
		Assert.notNull( svTapStep.getSvTapStepId(), "DeleteSvTapStepCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a SvTapStep
	 */
	public void validate( SvTapStepFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "SvTapStepFetchOneSummary should not be null" );
		Assert.notNull( summary.getSvTapStepId(), "SvTapStepFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Position validation for a SvTapStep
	 * 
	 * @param	command AssignPositionToSvTapStepCommand
	 */	
	public void validate( AssignPositionToSvTapStepCommand command ) throws Exception {
		Assert.notNull( command, "AssignPositionToSvTapStepCommand should not be null" );
		Assert.notNull( command.getSvTapStepId(), "AssignPositionToSvTapStepCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPositionToSvTapStepCommand assignment should not be null" );
	}

	/**
	 * handles unassign Position validation for a SvTapStep
	 * 
	 * @param	command UnAssignPositionFromSvTapStepCommand
	 */	
	public void validate( UnAssignPositionFromSvTapStepCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPositionFromSvTapStepCommand should not be null" );
		Assert.notNull( command.getSvTapStepId(), "UnAssignPositionFromSvTapStepCommand identifier should not be null" );
	}
	/**
	 * handles assign TapChanger validation for a SvTapStep
	 * 
	 * @param	command AssignTapChangerToSvTapStepCommand
	 */	
	public void validate( AssignTapChangerToSvTapStepCommand command ) throws Exception {
		Assert.notNull( command, "AssignTapChangerToSvTapStepCommand should not be null" );
		Assert.notNull( command.getSvTapStepId(), "AssignTapChangerToSvTapStepCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTapChangerToSvTapStepCommand assignment should not be null" );
	}

	/**
	 * handles unassign TapChanger validation for a SvTapStep
	 * 
	 * @param	command UnAssignTapChangerFromSvTapStepCommand
	 */	
	public void validate( UnAssignTapChangerFromSvTapStepCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTapChangerFromSvTapStepCommand should not be null" );
		Assert.notNull( command.getSvTapStepId(), "UnAssignTapChangerFromSvTapStepCommand identifier should not be null" );
	}


}
