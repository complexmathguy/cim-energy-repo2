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

public class TransformerEndValidator {
		
	/**
	 * default constructor
	 */
	protected TransformerEndValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public TransformerEndValidator getInstance() {
		return new TransformerEndValidator();
	}
		
	/**
	 * handles creation validation for a TransformerEnd
	 */
	public void validate( CreateTransformerEndCommand transformerEnd )throws Exception {
		Assert.notNull( transformerEnd, "CreateTransformerEndCommand should not be null" );
//		Assert.isNull( transformerEnd.getTransformerEndId(), "CreateTransformerEndCommand identifier should be null" );
	}

	/**
	 * handles update validation for a TransformerEnd
	 */
	public void validate( UpdateTransformerEndCommand transformerEnd ) throws Exception {
		Assert.notNull( transformerEnd, "UpdateTransformerEndCommand should not be null" );
		Assert.notNull( transformerEnd.getTransformerEndId(), "UpdateTransformerEndCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a TransformerEnd
	 */
    public void validate( DeleteTransformerEndCommand transformerEnd ) throws Exception {
		Assert.notNull( transformerEnd, "{commandAlias} should not be null" );
		Assert.notNull( transformerEnd.getTransformerEndId(), "DeleteTransformerEndCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a TransformerEnd
	 */
	public void validate( TransformerEndFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "TransformerEndFetchOneSummary should not be null" );
		Assert.notNull( summary.getTransformerEndId(), "TransformerEndFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign EndNumber validation for a TransformerEnd
	 * 
	 * @param	command AssignEndNumberToTransformerEndCommand
	 */	
	public void validate( AssignEndNumberToTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "AssignEndNumberToTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "AssignEndNumberToTransformerEndCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignEndNumberToTransformerEndCommand assignment should not be null" );
	}

	/**
	 * handles unassign EndNumber validation for a TransformerEnd
	 * 
	 * @param	command UnAssignEndNumberFromTransformerEndCommand
	 */	
	public void validate( UnAssignEndNumberFromTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignEndNumberFromTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "UnAssignEndNumberFromTransformerEndCommand identifier should not be null" );
	}
	/**
	 * handles assign Grounded validation for a TransformerEnd
	 * 
	 * @param	command AssignGroundedToTransformerEndCommand
	 */	
	public void validate( AssignGroundedToTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "AssignGroundedToTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "AssignGroundedToTransformerEndCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignGroundedToTransformerEndCommand assignment should not be null" );
	}

	/**
	 * handles unassign Grounded validation for a TransformerEnd
	 * 
	 * @param	command UnAssignGroundedFromTransformerEndCommand
	 */	
	public void validate( UnAssignGroundedFromTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignGroundedFromTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "UnAssignGroundedFromTransformerEndCommand identifier should not be null" );
	}
	/**
	 * handles assign Rground validation for a TransformerEnd
	 * 
	 * @param	command AssignRgroundToTransformerEndCommand
	 */	
	public void validate( AssignRgroundToTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "AssignRgroundToTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "AssignRgroundToTransformerEndCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRgroundToTransformerEndCommand assignment should not be null" );
	}

	/**
	 * handles unassign Rground validation for a TransformerEnd
	 * 
	 * @param	command UnAssignRgroundFromTransformerEndCommand
	 */	
	public void validate( UnAssignRgroundFromTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRgroundFromTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "UnAssignRgroundFromTransformerEndCommand identifier should not be null" );
	}
	/**
	 * handles assign Xground validation for a TransformerEnd
	 * 
	 * @param	command AssignXgroundToTransformerEndCommand
	 */	
	public void validate( AssignXgroundToTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "AssignXgroundToTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "AssignXgroundToTransformerEndCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignXgroundToTransformerEndCommand assignment should not be null" );
	}

	/**
	 * handles unassign Xground validation for a TransformerEnd
	 * 
	 * @param	command UnAssignXgroundFromTransformerEndCommand
	 */	
	public void validate( UnAssignXgroundFromTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignXgroundFromTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "UnAssignXgroundFromTransformerEndCommand identifier should not be null" );
	}
	/**
	 * handles assign BaseVoltage validation for a TransformerEnd
	 * 
	 * @param	command AssignBaseVoltageToTransformerEndCommand
	 */	
	public void validate( AssignBaseVoltageToTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "AssignBaseVoltageToTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "AssignBaseVoltageToTransformerEndCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignBaseVoltageToTransformerEndCommand assignment should not be null" );
	}

	/**
	 * handles unassign BaseVoltage validation for a TransformerEnd
	 * 
	 * @param	command UnAssignBaseVoltageFromTransformerEndCommand
	 */	
	public void validate( UnAssignBaseVoltageFromTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignBaseVoltageFromTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "UnAssignBaseVoltageFromTransformerEndCommand identifier should not be null" );
	}
	/**
	 * handles assign Terminal validation for a TransformerEnd
	 * 
	 * @param	command AssignTerminalToTransformerEndCommand
	 */	
	public void validate( AssignTerminalToTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "AssignTerminalToTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "AssignTerminalToTransformerEndCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTerminalToTransformerEndCommand assignment should not be null" );
	}

	/**
	 * handles unassign Terminal validation for a TransformerEnd
	 * 
	 * @param	command UnAssignTerminalFromTransformerEndCommand
	 */	
	public void validate( UnAssignTerminalFromTransformerEndCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTerminalFromTransformerEndCommand should not be null" );
		Assert.notNull( command.getTransformerEndId(), "UnAssignTerminalFromTransformerEndCommand identifier should not be null" );
	}


}
