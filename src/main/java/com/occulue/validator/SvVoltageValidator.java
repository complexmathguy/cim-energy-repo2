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

public class SvVoltageValidator {
		
	/**
	 * default constructor
	 */
	protected SvVoltageValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public SvVoltageValidator getInstance() {
		return new SvVoltageValidator();
	}
		
	/**
	 * handles creation validation for a SvVoltage
	 */
	public void validate( CreateSvVoltageCommand svVoltage )throws Exception {
		Assert.notNull( svVoltage, "CreateSvVoltageCommand should not be null" );
//		Assert.isNull( svVoltage.getSvVoltageId(), "CreateSvVoltageCommand identifier should be null" );
	}

	/**
	 * handles update validation for a SvVoltage
	 */
	public void validate( UpdateSvVoltageCommand svVoltage ) throws Exception {
		Assert.notNull( svVoltage, "UpdateSvVoltageCommand should not be null" );
		Assert.notNull( svVoltage.getSvVoltageId(), "UpdateSvVoltageCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a SvVoltage
	 */
    public void validate( DeleteSvVoltageCommand svVoltage ) throws Exception {
		Assert.notNull( svVoltage, "{commandAlias} should not be null" );
		Assert.notNull( svVoltage.getSvVoltageId(), "DeleteSvVoltageCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a SvVoltage
	 */
	public void validate( SvVoltageFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "SvVoltageFetchOneSummary should not be null" );
		Assert.notNull( summary.getSvVoltageId(), "SvVoltageFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Angle validation for a SvVoltage
	 * 
	 * @param	command AssignAngleToSvVoltageCommand
	 */	
	public void validate( AssignAngleToSvVoltageCommand command ) throws Exception {
		Assert.notNull( command, "AssignAngleToSvVoltageCommand should not be null" );
		Assert.notNull( command.getSvVoltageId(), "AssignAngleToSvVoltageCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignAngleToSvVoltageCommand assignment should not be null" );
	}

	/**
	 * handles unassign Angle validation for a SvVoltage
	 * 
	 * @param	command UnAssignAngleFromSvVoltageCommand
	 */	
	public void validate( UnAssignAngleFromSvVoltageCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignAngleFromSvVoltageCommand should not be null" );
		Assert.notNull( command.getSvVoltageId(), "UnAssignAngleFromSvVoltageCommand identifier should not be null" );
	}
	/**
	 * handles assign V validation for a SvVoltage
	 * 
	 * @param	command AssignVToSvVoltageCommand
	 */	
	public void validate( AssignVToSvVoltageCommand command ) throws Exception {
		Assert.notNull( command, "AssignVToSvVoltageCommand should not be null" );
		Assert.notNull( command.getSvVoltageId(), "AssignVToSvVoltageCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignVToSvVoltageCommand assignment should not be null" );
	}

	/**
	 * handles unassign V validation for a SvVoltage
	 * 
	 * @param	command UnAssignVFromSvVoltageCommand
	 */	
	public void validate( UnAssignVFromSvVoltageCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignVFromSvVoltageCommand should not be null" );
		Assert.notNull( command.getSvVoltageId(), "UnAssignVFromSvVoltageCommand identifier should not be null" );
	}
	/**
	 * handles assign TopologicalNode validation for a SvVoltage
	 * 
	 * @param	command AssignTopologicalNodeToSvVoltageCommand
	 */	
	public void validate( AssignTopologicalNodeToSvVoltageCommand command ) throws Exception {
		Assert.notNull( command, "AssignTopologicalNodeToSvVoltageCommand should not be null" );
		Assert.notNull( command.getSvVoltageId(), "AssignTopologicalNodeToSvVoltageCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTopologicalNodeToSvVoltageCommand assignment should not be null" );
	}

	/**
	 * handles unassign TopologicalNode validation for a SvVoltage
	 * 
	 * @param	command UnAssignTopologicalNodeFromSvVoltageCommand
	 */	
	public void validate( UnAssignTopologicalNodeFromSvVoltageCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTopologicalNodeFromSvVoltageCommand should not be null" );
		Assert.notNull( command.getSvVoltageId(), "UnAssignTopologicalNodeFromSvVoltageCommand identifier should not be null" );
	}


}
