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

public class GrossToNetActivePowerCurveValidator {
		
	/**
	 * default constructor
	 */
	protected GrossToNetActivePowerCurveValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public GrossToNetActivePowerCurveValidator getInstance() {
		return new GrossToNetActivePowerCurveValidator();
	}
		
	/**
	 * handles creation validation for a GrossToNetActivePowerCurve
	 */
	public void validate( CreateGrossToNetActivePowerCurveCommand grossToNetActivePowerCurve )throws Exception {
		Assert.notNull( grossToNetActivePowerCurve, "CreateGrossToNetActivePowerCurveCommand should not be null" );
//		Assert.isNull( grossToNetActivePowerCurve.getGrossToNetActivePowerCurveId(), "CreateGrossToNetActivePowerCurveCommand identifier should be null" );
	}

	/**
	 * handles update validation for a GrossToNetActivePowerCurve
	 */
	public void validate( UpdateGrossToNetActivePowerCurveCommand grossToNetActivePowerCurve ) throws Exception {
		Assert.notNull( grossToNetActivePowerCurve, "UpdateGrossToNetActivePowerCurveCommand should not be null" );
		Assert.notNull( grossToNetActivePowerCurve.getGrossToNetActivePowerCurveId(), "UpdateGrossToNetActivePowerCurveCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a GrossToNetActivePowerCurve
	 */
    public void validate( DeleteGrossToNetActivePowerCurveCommand grossToNetActivePowerCurve ) throws Exception {
		Assert.notNull( grossToNetActivePowerCurve, "{commandAlias} should not be null" );
		Assert.notNull( grossToNetActivePowerCurve.getGrossToNetActivePowerCurveId(), "DeleteGrossToNetActivePowerCurveCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a GrossToNetActivePowerCurve
	 */
	public void validate( GrossToNetActivePowerCurveFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "GrossToNetActivePowerCurveFetchOneSummary should not be null" );
		Assert.notNull( summary.getGrossToNetActivePowerCurveId(), "GrossToNetActivePowerCurveFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign GeneratingUnit validation for a GrossToNetActivePowerCurve
	 * 
	 * @param	command AssignGeneratingUnitToGrossToNetActivePowerCurveCommand
	 */	
	public void validate( AssignGeneratingUnitToGrossToNetActivePowerCurveCommand command ) throws Exception {
		Assert.notNull( command, "AssignGeneratingUnitToGrossToNetActivePowerCurveCommand should not be null" );
		Assert.notNull( command.getGrossToNetActivePowerCurveId(), "AssignGeneratingUnitToGrossToNetActivePowerCurveCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignGeneratingUnitToGrossToNetActivePowerCurveCommand assignment should not be null" );
	}

	/**
	 * handles unassign GeneratingUnit validation for a GrossToNetActivePowerCurve
	 * 
	 * @param	command UnAssignGeneratingUnitFromGrossToNetActivePowerCurveCommand
	 */	
	public void validate( UnAssignGeneratingUnitFromGrossToNetActivePowerCurveCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignGeneratingUnitFromGrossToNetActivePowerCurveCommand should not be null" );
		Assert.notNull( command.getGrossToNetActivePowerCurveId(), "UnAssignGeneratingUnitFromGrossToNetActivePowerCurveCommand identifier should not be null" );
	}


}
