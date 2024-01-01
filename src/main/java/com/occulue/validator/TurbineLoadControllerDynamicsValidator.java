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

public class TurbineLoadControllerDynamicsValidator {
		
	/**
	 * default constructor
	 */
	protected TurbineLoadControllerDynamicsValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public TurbineLoadControllerDynamicsValidator getInstance() {
		return new TurbineLoadControllerDynamicsValidator();
	}
		
	/**
	 * handles creation validation for a TurbineLoadControllerDynamics
	 */
	public void validate( CreateTurbineLoadControllerDynamicsCommand turbineLoadControllerDynamics )throws Exception {
		Assert.notNull( turbineLoadControllerDynamics, "CreateTurbineLoadControllerDynamicsCommand should not be null" );
//		Assert.isNull( turbineLoadControllerDynamics.getTurbineLoadControllerDynamicsId(), "CreateTurbineLoadControllerDynamicsCommand identifier should be null" );
	}

	/**
	 * handles update validation for a TurbineLoadControllerDynamics
	 */
	public void validate( UpdateTurbineLoadControllerDynamicsCommand turbineLoadControllerDynamics ) throws Exception {
		Assert.notNull( turbineLoadControllerDynamics, "UpdateTurbineLoadControllerDynamicsCommand should not be null" );
		Assert.notNull( turbineLoadControllerDynamics.getTurbineLoadControllerDynamicsId(), "UpdateTurbineLoadControllerDynamicsCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a TurbineLoadControllerDynamics
	 */
    public void validate( DeleteTurbineLoadControllerDynamicsCommand turbineLoadControllerDynamics ) throws Exception {
		Assert.notNull( turbineLoadControllerDynamics, "{commandAlias} should not be null" );
		Assert.notNull( turbineLoadControllerDynamics.getTurbineLoadControllerDynamicsId(), "DeleteTurbineLoadControllerDynamicsCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a TurbineLoadControllerDynamics
	 */
	public void validate( TurbineLoadControllerDynamicsFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "TurbineLoadControllerDynamicsFetchOneSummary should not be null" );
		Assert.notNull( summary.getTurbineLoadControllerDynamicsId(), "TurbineLoadControllerDynamicsFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign TurbineGovernorDynamics validation for a TurbineLoadControllerDynamics
	 * 
	 * @param	command AssignTurbineGovernorDynamicsToTurbineLoadControllerDynamicsCommand
	 */	
	public void validate( AssignTurbineGovernorDynamicsToTurbineLoadControllerDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignTurbineGovernorDynamicsToTurbineLoadControllerDynamicsCommand should not be null" );
		Assert.notNull( command.getTurbineLoadControllerDynamicsId(), "AssignTurbineGovernorDynamicsToTurbineLoadControllerDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTurbineGovernorDynamicsToTurbineLoadControllerDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign TurbineGovernorDynamics validation for a TurbineLoadControllerDynamics
	 * 
	 * @param	command UnAssignTurbineGovernorDynamicsFromTurbineLoadControllerDynamicsCommand
	 */	
	public void validate( UnAssignTurbineGovernorDynamicsFromTurbineLoadControllerDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTurbineGovernorDynamicsFromTurbineLoadControllerDynamicsCommand should not be null" );
		Assert.notNull( command.getTurbineLoadControllerDynamicsId(), "UnAssignTurbineGovernorDynamicsFromTurbineLoadControllerDynamicsCommand identifier should not be null" );
	}


}
