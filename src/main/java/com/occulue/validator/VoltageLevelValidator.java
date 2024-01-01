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

public class VoltageLevelValidator {
		
	/**
	 * default constructor
	 */
	protected VoltageLevelValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public VoltageLevelValidator getInstance() {
		return new VoltageLevelValidator();
	}
		
	/**
	 * handles creation validation for a VoltageLevel
	 */
	public void validate( CreateVoltageLevelCommand voltageLevel )throws Exception {
		Assert.notNull( voltageLevel, "CreateVoltageLevelCommand should not be null" );
//		Assert.isNull( voltageLevel.getVoltageLevelId(), "CreateVoltageLevelCommand identifier should be null" );
	}

	/**
	 * handles update validation for a VoltageLevel
	 */
	public void validate( UpdateVoltageLevelCommand voltageLevel ) throws Exception {
		Assert.notNull( voltageLevel, "UpdateVoltageLevelCommand should not be null" );
		Assert.notNull( voltageLevel.getVoltageLevelId(), "UpdateVoltageLevelCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a VoltageLevel
	 */
    public void validate( DeleteVoltageLevelCommand voltageLevel ) throws Exception {
		Assert.notNull( voltageLevel, "{commandAlias} should not be null" );
		Assert.notNull( voltageLevel.getVoltageLevelId(), "DeleteVoltageLevelCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a VoltageLevel
	 */
	public void validate( VoltageLevelFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "VoltageLevelFetchOneSummary should not be null" );
		Assert.notNull( summary.getVoltageLevelId(), "VoltageLevelFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign HighVoltageLimit validation for a VoltageLevel
	 * 
	 * @param	command AssignHighVoltageLimitToVoltageLevelCommand
	 */	
	public void validate( AssignHighVoltageLimitToVoltageLevelCommand command ) throws Exception {
		Assert.notNull( command, "AssignHighVoltageLimitToVoltageLevelCommand should not be null" );
		Assert.notNull( command.getVoltageLevelId(), "AssignHighVoltageLimitToVoltageLevelCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignHighVoltageLimitToVoltageLevelCommand assignment should not be null" );
	}

	/**
	 * handles unassign HighVoltageLimit validation for a VoltageLevel
	 * 
	 * @param	command UnAssignHighVoltageLimitFromVoltageLevelCommand
	 */	
	public void validate( UnAssignHighVoltageLimitFromVoltageLevelCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignHighVoltageLimitFromVoltageLevelCommand should not be null" );
		Assert.notNull( command.getVoltageLevelId(), "UnAssignHighVoltageLimitFromVoltageLevelCommand identifier should not be null" );
	}
	/**
	 * handles assign LowVoltageLimit validation for a VoltageLevel
	 * 
	 * @param	command AssignLowVoltageLimitToVoltageLevelCommand
	 */	
	public void validate( AssignLowVoltageLimitToVoltageLevelCommand command ) throws Exception {
		Assert.notNull( command, "AssignLowVoltageLimitToVoltageLevelCommand should not be null" );
		Assert.notNull( command.getVoltageLevelId(), "AssignLowVoltageLimitToVoltageLevelCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignLowVoltageLimitToVoltageLevelCommand assignment should not be null" );
	}

	/**
	 * handles unassign LowVoltageLimit validation for a VoltageLevel
	 * 
	 * @param	command UnAssignLowVoltageLimitFromVoltageLevelCommand
	 */	
	public void validate( UnAssignLowVoltageLimitFromVoltageLevelCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignLowVoltageLimitFromVoltageLevelCommand should not be null" );
		Assert.notNull( command.getVoltageLevelId(), "UnAssignLowVoltageLimitFromVoltageLevelCommand identifier should not be null" );
	}
	/**
	 * handles assign BaseVoltage validation for a VoltageLevel
	 * 
	 * @param	command AssignBaseVoltageToVoltageLevelCommand
	 */	
	public void validate( AssignBaseVoltageToVoltageLevelCommand command ) throws Exception {
		Assert.notNull( command, "AssignBaseVoltageToVoltageLevelCommand should not be null" );
		Assert.notNull( command.getVoltageLevelId(), "AssignBaseVoltageToVoltageLevelCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignBaseVoltageToVoltageLevelCommand assignment should not be null" );
	}

	/**
	 * handles unassign BaseVoltage validation for a VoltageLevel
	 * 
	 * @param	command UnAssignBaseVoltageFromVoltageLevelCommand
	 */	
	public void validate( UnAssignBaseVoltageFromVoltageLevelCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignBaseVoltageFromVoltageLevelCommand should not be null" );
		Assert.notNull( command.getVoltageLevelId(), "UnAssignBaseVoltageFromVoltageLevelCommand identifier should not be null" );
	}
	/**
	 * handles assign Substation validation for a VoltageLevel
	 * 
	 * @param	command AssignSubstationToVoltageLevelCommand
	 */	
	public void validate( AssignSubstationToVoltageLevelCommand command ) throws Exception {
		Assert.notNull( command, "AssignSubstationToVoltageLevelCommand should not be null" );
		Assert.notNull( command.getVoltageLevelId(), "AssignSubstationToVoltageLevelCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSubstationToVoltageLevelCommand assignment should not be null" );
	}

	/**
	 * handles unassign Substation validation for a VoltageLevel
	 * 
	 * @param	command UnAssignSubstationFromVoltageLevelCommand
	 */	
	public void validate( UnAssignSubstationFromVoltageLevelCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSubstationFromVoltageLevelCommand should not be null" );
		Assert.notNull( command.getVoltageLevelId(), "UnAssignSubstationFromVoltageLevelCommand identifier should not be null" );
	}


}
