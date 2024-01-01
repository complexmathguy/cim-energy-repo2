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

public class SvPowerFlowValidator {
		
	/**
	 * default constructor
	 */
	protected SvPowerFlowValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public SvPowerFlowValidator getInstance() {
		return new SvPowerFlowValidator();
	}
		
	/**
	 * handles creation validation for a SvPowerFlow
	 */
	public void validate( CreateSvPowerFlowCommand svPowerFlow )throws Exception {
		Assert.notNull( svPowerFlow, "CreateSvPowerFlowCommand should not be null" );
//		Assert.isNull( svPowerFlow.getSvPowerFlowId(), "CreateSvPowerFlowCommand identifier should be null" );
	}

	/**
	 * handles update validation for a SvPowerFlow
	 */
	public void validate( UpdateSvPowerFlowCommand svPowerFlow ) throws Exception {
		Assert.notNull( svPowerFlow, "UpdateSvPowerFlowCommand should not be null" );
		Assert.notNull( svPowerFlow.getSvPowerFlowId(), "UpdateSvPowerFlowCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a SvPowerFlow
	 */
    public void validate( DeleteSvPowerFlowCommand svPowerFlow ) throws Exception {
		Assert.notNull( svPowerFlow, "{commandAlias} should not be null" );
		Assert.notNull( svPowerFlow.getSvPowerFlowId(), "DeleteSvPowerFlowCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a SvPowerFlow
	 */
	public void validate( SvPowerFlowFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "SvPowerFlowFetchOneSummary should not be null" );
		Assert.notNull( summary.getSvPowerFlowId(), "SvPowerFlowFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign P validation for a SvPowerFlow
	 * 
	 * @param	command AssignPToSvPowerFlowCommand
	 */	
	public void validate( AssignPToSvPowerFlowCommand command ) throws Exception {
		Assert.notNull( command, "AssignPToSvPowerFlowCommand should not be null" );
		Assert.notNull( command.getSvPowerFlowId(), "AssignPToSvPowerFlowCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPToSvPowerFlowCommand assignment should not be null" );
	}

	/**
	 * handles unassign P validation for a SvPowerFlow
	 * 
	 * @param	command UnAssignPFromSvPowerFlowCommand
	 */	
	public void validate( UnAssignPFromSvPowerFlowCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPFromSvPowerFlowCommand should not be null" );
		Assert.notNull( command.getSvPowerFlowId(), "UnAssignPFromSvPowerFlowCommand identifier should not be null" );
	}
	/**
	 * handles assign Q validation for a SvPowerFlow
	 * 
	 * @param	command AssignQToSvPowerFlowCommand
	 */	
	public void validate( AssignQToSvPowerFlowCommand command ) throws Exception {
		Assert.notNull( command, "AssignQToSvPowerFlowCommand should not be null" );
		Assert.notNull( command.getSvPowerFlowId(), "AssignQToSvPowerFlowCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignQToSvPowerFlowCommand assignment should not be null" );
	}

	/**
	 * handles unassign Q validation for a SvPowerFlow
	 * 
	 * @param	command UnAssignQFromSvPowerFlowCommand
	 */	
	public void validate( UnAssignQFromSvPowerFlowCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignQFromSvPowerFlowCommand should not be null" );
		Assert.notNull( command.getSvPowerFlowId(), "UnAssignQFromSvPowerFlowCommand identifier should not be null" );
	}
	/**
	 * handles assign Terminal validation for a SvPowerFlow
	 * 
	 * @param	command AssignTerminalToSvPowerFlowCommand
	 */	
	public void validate( AssignTerminalToSvPowerFlowCommand command ) throws Exception {
		Assert.notNull( command, "AssignTerminalToSvPowerFlowCommand should not be null" );
		Assert.notNull( command.getSvPowerFlowId(), "AssignTerminalToSvPowerFlowCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTerminalToSvPowerFlowCommand assignment should not be null" );
	}

	/**
	 * handles unassign Terminal validation for a SvPowerFlow
	 * 
	 * @param	command UnAssignTerminalFromSvPowerFlowCommand
	 */	
	public void validate( UnAssignTerminalFromSvPowerFlowCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTerminalFromSvPowerFlowCommand should not be null" );
		Assert.notNull( command.getSvPowerFlowId(), "UnAssignTerminalFromSvPowerFlowCommand identifier should not be null" );
	}


}
