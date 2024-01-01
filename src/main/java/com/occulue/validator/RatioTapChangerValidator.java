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

public class RatioTapChangerValidator {
		
	/**
	 * default constructor
	 */
	protected RatioTapChangerValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public RatioTapChangerValidator getInstance() {
		return new RatioTapChangerValidator();
	}
		
	/**
	 * handles creation validation for a RatioTapChanger
	 */
	public void validate( CreateRatioTapChangerCommand ratioTapChanger )throws Exception {
		Assert.notNull( ratioTapChanger, "CreateRatioTapChangerCommand should not be null" );
//		Assert.isNull( ratioTapChanger.getRatioTapChangerId(), "CreateRatioTapChangerCommand identifier should be null" );
	}

	/**
	 * handles update validation for a RatioTapChanger
	 */
	public void validate( UpdateRatioTapChangerCommand ratioTapChanger ) throws Exception {
		Assert.notNull( ratioTapChanger, "UpdateRatioTapChangerCommand should not be null" );
		Assert.notNull( ratioTapChanger.getRatioTapChangerId(), "UpdateRatioTapChangerCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a RatioTapChanger
	 */
    public void validate( DeleteRatioTapChangerCommand ratioTapChanger ) throws Exception {
		Assert.notNull( ratioTapChanger, "{commandAlias} should not be null" );
		Assert.notNull( ratioTapChanger.getRatioTapChangerId(), "DeleteRatioTapChangerCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a RatioTapChanger
	 */
	public void validate( RatioTapChangerFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "RatioTapChangerFetchOneSummary should not be null" );
		Assert.notNull( summary.getRatioTapChangerId(), "RatioTapChangerFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign StepVoltageIncrement validation for a RatioTapChanger
	 * 
	 * @param	command AssignStepVoltageIncrementToRatioTapChangerCommand
	 */	
	public void validate( AssignStepVoltageIncrementToRatioTapChangerCommand command ) throws Exception {
		Assert.notNull( command, "AssignStepVoltageIncrementToRatioTapChangerCommand should not be null" );
		Assert.notNull( command.getRatioTapChangerId(), "AssignStepVoltageIncrementToRatioTapChangerCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignStepVoltageIncrementToRatioTapChangerCommand assignment should not be null" );
	}

	/**
	 * handles unassign StepVoltageIncrement validation for a RatioTapChanger
	 * 
	 * @param	command UnAssignStepVoltageIncrementFromRatioTapChangerCommand
	 */	
	public void validate( UnAssignStepVoltageIncrementFromRatioTapChangerCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignStepVoltageIncrementFromRatioTapChangerCommand should not be null" );
		Assert.notNull( command.getRatioTapChangerId(), "UnAssignStepVoltageIncrementFromRatioTapChangerCommand identifier should not be null" );
	}
	/**
	 * handles assign TransformerEnd validation for a RatioTapChanger
	 * 
	 * @param	command AssignTransformerEndToRatioTapChangerCommand
	 */	
	public void validate( AssignTransformerEndToRatioTapChangerCommand command ) throws Exception {
		Assert.notNull( command, "AssignTransformerEndToRatioTapChangerCommand should not be null" );
		Assert.notNull( command.getRatioTapChangerId(), "AssignTransformerEndToRatioTapChangerCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTransformerEndToRatioTapChangerCommand assignment should not be null" );
	}

	/**
	 * handles unassign TransformerEnd validation for a RatioTapChanger
	 * 
	 * @param	command UnAssignTransformerEndFromRatioTapChangerCommand
	 */	
	public void validate( UnAssignTransformerEndFromRatioTapChangerCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTransformerEndFromRatioTapChangerCommand should not be null" );
		Assert.notNull( command.getRatioTapChangerId(), "UnAssignTransformerEndFromRatioTapChangerCommand identifier should not be null" );
	}
	/**
	 * handles assign RatioTapChangerTable validation for a RatioTapChanger
	 * 
	 * @param	command AssignRatioTapChangerTableToRatioTapChangerCommand
	 */	
	public void validate( AssignRatioTapChangerTableToRatioTapChangerCommand command ) throws Exception {
		Assert.notNull( command, "AssignRatioTapChangerTableToRatioTapChangerCommand should not be null" );
		Assert.notNull( command.getRatioTapChangerId(), "AssignRatioTapChangerTableToRatioTapChangerCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRatioTapChangerTableToRatioTapChangerCommand assignment should not be null" );
	}

	/**
	 * handles unassign RatioTapChangerTable validation for a RatioTapChanger
	 * 
	 * @param	command UnAssignRatioTapChangerTableFromRatioTapChangerCommand
	 */	
	public void validate( UnAssignRatioTapChangerTableFromRatioTapChangerCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRatioTapChangerTableFromRatioTapChangerCommand should not be null" );
		Assert.notNull( command.getRatioTapChangerId(), "UnAssignRatioTapChangerTableFromRatioTapChangerCommand identifier should not be null" );
	}


}
