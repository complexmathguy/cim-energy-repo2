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

public class TieFlowValidator {
		
	/**
	 * default constructor
	 */
	protected TieFlowValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public TieFlowValidator getInstance() {
		return new TieFlowValidator();
	}
		
	/**
	 * handles creation validation for a TieFlow
	 */
	public void validate( CreateTieFlowCommand tieFlow )throws Exception {
		Assert.notNull( tieFlow, "CreateTieFlowCommand should not be null" );
//		Assert.isNull( tieFlow.getTieFlowId(), "CreateTieFlowCommand identifier should be null" );
	}

	/**
	 * handles update validation for a TieFlow
	 */
	public void validate( UpdateTieFlowCommand tieFlow ) throws Exception {
		Assert.notNull( tieFlow, "UpdateTieFlowCommand should not be null" );
		Assert.notNull( tieFlow.getTieFlowId(), "UpdateTieFlowCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a TieFlow
	 */
    public void validate( DeleteTieFlowCommand tieFlow ) throws Exception {
		Assert.notNull( tieFlow, "{commandAlias} should not be null" );
		Assert.notNull( tieFlow.getTieFlowId(), "DeleteTieFlowCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a TieFlow
	 */
	public void validate( TieFlowFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "TieFlowFetchOneSummary should not be null" );
		Assert.notNull( summary.getTieFlowId(), "TieFlowFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign PositiveFlowIn validation for a TieFlow
	 * 
	 * @param	command AssignPositiveFlowInToTieFlowCommand
	 */	
	public void validate( AssignPositiveFlowInToTieFlowCommand command ) throws Exception {
		Assert.notNull( command, "AssignPositiveFlowInToTieFlowCommand should not be null" );
		Assert.notNull( command.getTieFlowId(), "AssignPositiveFlowInToTieFlowCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPositiveFlowInToTieFlowCommand assignment should not be null" );
	}

	/**
	 * handles unassign PositiveFlowIn validation for a TieFlow
	 * 
	 * @param	command UnAssignPositiveFlowInFromTieFlowCommand
	 */	
	public void validate( UnAssignPositiveFlowInFromTieFlowCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPositiveFlowInFromTieFlowCommand should not be null" );
		Assert.notNull( command.getTieFlowId(), "UnAssignPositiveFlowInFromTieFlowCommand identifier should not be null" );
	}
	/**
	 * handles assign ControlArea validation for a TieFlow
	 * 
	 * @param	command AssignControlAreaToTieFlowCommand
	 */	
	public void validate( AssignControlAreaToTieFlowCommand command ) throws Exception {
		Assert.notNull( command, "AssignControlAreaToTieFlowCommand should not be null" );
		Assert.notNull( command.getTieFlowId(), "AssignControlAreaToTieFlowCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignControlAreaToTieFlowCommand assignment should not be null" );
	}

	/**
	 * handles unassign ControlArea validation for a TieFlow
	 * 
	 * @param	command UnAssignControlAreaFromTieFlowCommand
	 */	
	public void validate( UnAssignControlAreaFromTieFlowCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignControlAreaFromTieFlowCommand should not be null" );
		Assert.notNull( command.getTieFlowId(), "UnAssignControlAreaFromTieFlowCommand identifier should not be null" );
	}
	/**
	 * handles assign Terminal validation for a TieFlow
	 * 
	 * @param	command AssignTerminalToTieFlowCommand
	 */	
	public void validate( AssignTerminalToTieFlowCommand command ) throws Exception {
		Assert.notNull( command, "AssignTerminalToTieFlowCommand should not be null" );
		Assert.notNull( command.getTieFlowId(), "AssignTerminalToTieFlowCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTerminalToTieFlowCommand assignment should not be null" );
	}

	/**
	 * handles unassign Terminal validation for a TieFlow
	 * 
	 * @param	command UnAssignTerminalFromTieFlowCommand
	 */	
	public void validate( UnAssignTerminalFromTieFlowCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTerminalFromTieFlowCommand should not be null" );
		Assert.notNull( command.getTieFlowId(), "UnAssignTerminalFromTieFlowCommand identifier should not be null" );
	}


}
