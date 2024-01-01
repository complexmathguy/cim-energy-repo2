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

public class AccumulatorLimitSetValidator {
		
	/**
	 * default constructor
	 */
	protected AccumulatorLimitSetValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public AccumulatorLimitSetValidator getInstance() {
		return new AccumulatorLimitSetValidator();
	}
		
	/**
	 * handles creation validation for a AccumulatorLimitSet
	 */
	public void validate( CreateAccumulatorLimitSetCommand accumulatorLimitSet )throws Exception {
		Assert.notNull( accumulatorLimitSet, "CreateAccumulatorLimitSetCommand should not be null" );
//		Assert.isNull( accumulatorLimitSet.getAccumulatorLimitSetId(), "CreateAccumulatorLimitSetCommand identifier should be null" );
	}

	/**
	 * handles update validation for a AccumulatorLimitSet
	 */
	public void validate( UpdateAccumulatorLimitSetCommand accumulatorLimitSet ) throws Exception {
		Assert.notNull( accumulatorLimitSet, "UpdateAccumulatorLimitSetCommand should not be null" );
		Assert.notNull( accumulatorLimitSet.getAccumulatorLimitSetId(), "UpdateAccumulatorLimitSetCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a AccumulatorLimitSet
	 */
    public void validate( DeleteAccumulatorLimitSetCommand accumulatorLimitSet ) throws Exception {
		Assert.notNull( accumulatorLimitSet, "{commandAlias} should not be null" );
		Assert.notNull( accumulatorLimitSet.getAccumulatorLimitSetId(), "DeleteAccumulatorLimitSetCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a AccumulatorLimitSet
	 */
	public void validate( AccumulatorLimitSetFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "AccumulatorLimitSetFetchOneSummary should not be null" );
		Assert.notNull( summary.getAccumulatorLimitSetId(), "AccumulatorLimitSetFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Measurements validation for a AccumulatorLimitSet
	 * 
	 * @param	command AssignMeasurementsToAccumulatorLimitSetCommand
	 */	
	public void validate( AssignMeasurementsToAccumulatorLimitSetCommand command ) throws Exception {
		Assert.notNull( command, "AssignMeasurementsToAccumulatorLimitSetCommand should not be null" );
		Assert.notNull( command.getAccumulatorLimitSetId(), "AssignMeasurementsToAccumulatorLimitSetCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignMeasurementsToAccumulatorLimitSetCommand assignment should not be null" );
	}

	/**
	 * handles unassign Measurements validation for a AccumulatorLimitSet
	 * 
	 * @param	command UnAssignMeasurementsFromAccumulatorLimitSetCommand
	 */	
	public void validate( UnAssignMeasurementsFromAccumulatorLimitSetCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignMeasurementsFromAccumulatorLimitSetCommand should not be null" );
		Assert.notNull( command.getAccumulatorLimitSetId(), "UnAssignMeasurementsFromAccumulatorLimitSetCommand identifier should not be null" );
	}


}
