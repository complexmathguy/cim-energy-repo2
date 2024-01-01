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

public class MeasurementValueValidator {
		
	/**
	 * default constructor
	 */
	protected MeasurementValueValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public MeasurementValueValidator getInstance() {
		return new MeasurementValueValidator();
	}
		
	/**
	 * handles creation validation for a MeasurementValue
	 */
	public void validate( CreateMeasurementValueCommand measurementValue )throws Exception {
		Assert.notNull( measurementValue, "CreateMeasurementValueCommand should not be null" );
//		Assert.isNull( measurementValue.getMeasurementValueId(), "CreateMeasurementValueCommand identifier should be null" );
	}

	/**
	 * handles update validation for a MeasurementValue
	 */
	public void validate( UpdateMeasurementValueCommand measurementValue ) throws Exception {
		Assert.notNull( measurementValue, "UpdateMeasurementValueCommand should not be null" );
		Assert.notNull( measurementValue.getMeasurementValueId(), "UpdateMeasurementValueCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a MeasurementValue
	 */
    public void validate( DeleteMeasurementValueCommand measurementValue ) throws Exception {
		Assert.notNull( measurementValue, "{commandAlias} should not be null" );
		Assert.notNull( measurementValue.getMeasurementValueId(), "DeleteMeasurementValueCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a MeasurementValue
	 */
	public void validate( MeasurementValueFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "MeasurementValueFetchOneSummary should not be null" );
		Assert.notNull( summary.getMeasurementValueId(), "MeasurementValueFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign SensorAccuracy validation for a MeasurementValue
	 * 
	 * @param	command AssignSensorAccuracyToMeasurementValueCommand
	 */	
	public void validate( AssignSensorAccuracyToMeasurementValueCommand command ) throws Exception {
		Assert.notNull( command, "AssignSensorAccuracyToMeasurementValueCommand should not be null" );
		Assert.notNull( command.getMeasurementValueId(), "AssignSensorAccuracyToMeasurementValueCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSensorAccuracyToMeasurementValueCommand assignment should not be null" );
	}

	/**
	 * handles unassign SensorAccuracy validation for a MeasurementValue
	 * 
	 * @param	command UnAssignSensorAccuracyFromMeasurementValueCommand
	 */	
	public void validate( UnAssignSensorAccuracyFromMeasurementValueCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSensorAccuracyFromMeasurementValueCommand should not be null" );
		Assert.notNull( command.getMeasurementValueId(), "UnAssignSensorAccuracyFromMeasurementValueCommand identifier should not be null" );
	}
	/**
	 * handles assign TimeStamp validation for a MeasurementValue
	 * 
	 * @param	command AssignTimeStampToMeasurementValueCommand
	 */	
	public void validate( AssignTimeStampToMeasurementValueCommand command ) throws Exception {
		Assert.notNull( command, "AssignTimeStampToMeasurementValueCommand should not be null" );
		Assert.notNull( command.getMeasurementValueId(), "AssignTimeStampToMeasurementValueCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTimeStampToMeasurementValueCommand assignment should not be null" );
	}

	/**
	 * handles unassign TimeStamp validation for a MeasurementValue
	 * 
	 * @param	command UnAssignTimeStampFromMeasurementValueCommand
	 */	
	public void validate( UnAssignTimeStampFromMeasurementValueCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTimeStampFromMeasurementValueCommand should not be null" );
		Assert.notNull( command.getMeasurementValueId(), "UnAssignTimeStampFromMeasurementValueCommand identifier should not be null" );
	}
	/**
	 * handles assign MeasurementValueSource validation for a MeasurementValue
	 * 
	 * @param	command AssignMeasurementValueSourceToMeasurementValueCommand
	 */	
	public void validate( AssignMeasurementValueSourceToMeasurementValueCommand command ) throws Exception {
		Assert.notNull( command, "AssignMeasurementValueSourceToMeasurementValueCommand should not be null" );
		Assert.notNull( command.getMeasurementValueId(), "AssignMeasurementValueSourceToMeasurementValueCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignMeasurementValueSourceToMeasurementValueCommand assignment should not be null" );
	}

	/**
	 * handles unassign MeasurementValueSource validation for a MeasurementValue
	 * 
	 * @param	command UnAssignMeasurementValueSourceFromMeasurementValueCommand
	 */	
	public void validate( UnAssignMeasurementValueSourceFromMeasurementValueCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignMeasurementValueSourceFromMeasurementValueCommand should not be null" );
		Assert.notNull( command.getMeasurementValueId(), "UnAssignMeasurementValueSourceFromMeasurementValueCommand identifier should not be null" );
	}


}
