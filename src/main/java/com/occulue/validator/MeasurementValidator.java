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

public class MeasurementValidator {
		
	/**
	 * default constructor
	 */
	protected MeasurementValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public MeasurementValidator getInstance() {
		return new MeasurementValidator();
	}
		
	/**
	 * handles creation validation for a Measurement
	 */
	public void validate( CreateMeasurementCommand measurement )throws Exception {
		Assert.notNull( measurement, "CreateMeasurementCommand should not be null" );
//		Assert.isNull( measurement.getMeasurementId(), "CreateMeasurementCommand identifier should be null" );
	}

	/**
	 * handles update validation for a Measurement
	 */
	public void validate( UpdateMeasurementCommand measurement ) throws Exception {
		Assert.notNull( measurement, "UpdateMeasurementCommand should not be null" );
		Assert.notNull( measurement.getMeasurementId(), "UpdateMeasurementCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a Measurement
	 */
    public void validate( DeleteMeasurementCommand measurement ) throws Exception {
		Assert.notNull( measurement, "{commandAlias} should not be null" );
		Assert.notNull( measurement.getMeasurementId(), "DeleteMeasurementCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a Measurement
	 */
	public void validate( MeasurementFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "MeasurementFetchOneSummary should not be null" );
		Assert.notNull( summary.getMeasurementId(), "MeasurementFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign MeasurementType validation for a Measurement
	 * 
	 * @param	command AssignMeasurementTypeToMeasurementCommand
	 */	
	public void validate( AssignMeasurementTypeToMeasurementCommand command ) throws Exception {
		Assert.notNull( command, "AssignMeasurementTypeToMeasurementCommand should not be null" );
		Assert.notNull( command.getMeasurementId(), "AssignMeasurementTypeToMeasurementCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignMeasurementTypeToMeasurementCommand assignment should not be null" );
	}

	/**
	 * handles unassign MeasurementType validation for a Measurement
	 * 
	 * @param	command UnAssignMeasurementTypeFromMeasurementCommand
	 */	
	public void validate( UnAssignMeasurementTypeFromMeasurementCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignMeasurementTypeFromMeasurementCommand should not be null" );
		Assert.notNull( command.getMeasurementId(), "UnAssignMeasurementTypeFromMeasurementCommand identifier should not be null" );
	}
	/**
	 * handles assign PowerSystemResource validation for a Measurement
	 * 
	 * @param	command AssignPowerSystemResourceToMeasurementCommand
	 */	
	public void validate( AssignPowerSystemResourceToMeasurementCommand command ) throws Exception {
		Assert.notNull( command, "AssignPowerSystemResourceToMeasurementCommand should not be null" );
		Assert.notNull( command.getMeasurementId(), "AssignPowerSystemResourceToMeasurementCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPowerSystemResourceToMeasurementCommand assignment should not be null" );
	}

	/**
	 * handles unassign PowerSystemResource validation for a Measurement
	 * 
	 * @param	command UnAssignPowerSystemResourceFromMeasurementCommand
	 */	
	public void validate( UnAssignPowerSystemResourceFromMeasurementCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPowerSystemResourceFromMeasurementCommand should not be null" );
		Assert.notNull( command.getMeasurementId(), "UnAssignPowerSystemResourceFromMeasurementCommand identifier should not be null" );
	}
	/**
	 * handles assign Terminal validation for a Measurement
	 * 
	 * @param	command AssignTerminalToMeasurementCommand
	 */	
	public void validate( AssignTerminalToMeasurementCommand command ) throws Exception {
		Assert.notNull( command, "AssignTerminalToMeasurementCommand should not be null" );
		Assert.notNull( command.getMeasurementId(), "AssignTerminalToMeasurementCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTerminalToMeasurementCommand assignment should not be null" );
	}

	/**
	 * handles unassign Terminal validation for a Measurement
	 * 
	 * @param	command UnAssignTerminalFromMeasurementCommand
	 */	
	public void validate( UnAssignTerminalFromMeasurementCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTerminalFromMeasurementCommand should not be null" );
		Assert.notNull( command.getMeasurementId(), "UnAssignTerminalFromMeasurementCommand identifier should not be null" );
	}


}
